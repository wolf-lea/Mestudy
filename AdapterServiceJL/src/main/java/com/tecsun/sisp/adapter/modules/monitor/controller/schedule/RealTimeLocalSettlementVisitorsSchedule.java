package com.tecsun.sisp.adapter.modules.monitor.controller.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.tecsun.sisp.adapter.common.util.JedisUtil;
import com.tecsun.sisp.adapter.common.util.JsonMapper;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.RealTimeTransactionQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.RealTimeSettlementVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl;

import cn.hutool.core.date.DateUtil;

/**
 * 近24小时交易信息---本地就医结算人次统计数据写入redis 
 */
public class RealTimeLocalSettlementVisitorsSchedule {
	
	private static Logger logger = LoggerFactory.getLogger(RealTimeLocalSettlementVisitorsSchedule.class);

	@Autowired
	private MonitorServiceImpl monitorServiceImpl;
	
	
	public void saveRealTimeLocalSettlementVisitorsData() {
		
		logger.info("....定时任务.....近24小时交易信息本地就医结算人次统计数据写入redis.....开始执行.......");
		long beginTime = DateUtil.currentSeconds();
		
		// 查询前一天24小时内交易信息  00:00:00 ~ 23:59:59
		String startTime = DateUtil.format(DateUtil.yesterday(), "yyyy/MM/dd 00:00:00");
		String endTime = DateUtil.format(DateUtil.yesterday(), "yyyy/MM/dd 23:59:59");
		
		RealTimeTransactionQueryBean queryBean = new RealTimeTransactionQueryBean();
		queryBean.setStartTime(startTime);
		queryBean.setEndTime(endTime);
		
		List<RealTimeSettlementVisitorsVO> datas = new ArrayList<>();
		List<RealTimeSettlementVisitorsVO> compareDatas = new ArrayList<>();
		
		// compareDatas添加数据，compareDatas默认里面各时间段（0~23）的结算人次数据都为0
		for (int index = 0; index < 24; index++) {
			compareDatas.add(index, new RealTimeSettlementVisitorsVO(String.valueOf(index), 0));
		}
		
		try {
			// 清除缓存中过期数据
			JedisUtil.delValue("localRealTimeSettlement");
			datas = monitorServiceImpl.getRealTimeLocalSettlementVisitors4Other(queryBean);
			
			// 将compareDatas与datas两个集合取并集，将某些时间段没有结算人次的数据置为0,最后数据全部存储在compareDatas
			compareDatas.removeAll(datas);
			compareDatas.addAll(datas);
			
			// 利用Guava 将compareDats 转成map,便于统计每两个小时的结算人次数据
			Map<String, RealTimeSettlementVisitorsVO> maps = Maps.uniqueIndex(compareDatas, new Function<RealTimeSettlementVisitorsVO, String>() {

				@Override
				public String apply(RealTimeSettlementVisitorsVO vo) {
					return vo.getCurrentHour();
				}

			});
			
			// 将datas清空，重复利用
			datas.clear();
			for (int i = 0; i < 12; i++) {
				RealTimeSettlementVisitorsVO data = new RealTimeSettlementVisitorsVO();
				// 统计每两个小时的结算人次数据
				Integer settlementNumber = maps.get(String.valueOf(i*2)).getSettlementNumber() + maps.get(String.valueOf(i*2 + 1)).getSettlementNumber();
				data.setCurrentHour(String.valueOf(i));
				data.setSettlementNumber(settlementNumber);
				datas.add(i, data);
			}
			
			String jsonData = JsonMapper.toNormalJson(datas);
			logger.info("********近24小时交易信息本地就医结算人次统计数据" + jsonData + "********");
			JedisUtil.setValue("localRealTimeDischarge", jsonData, 24*60);
			
			long finishTime = DateUtil.currentSeconds();
			logger.info(".........近24小时交易信息本地就医结算人次统计数据成功写入redis.........");
			logger.info(".........近24小时交易信息本地就医结算人次统计数据写入redis耗时" + (finishTime - beginTime) + "秒.........");
			
		} catch (Exception e) {
			logger.info("近24小时交易信息本地就医结算人次统计数据写入redis异常", e);
		}
		
	}
	

}
