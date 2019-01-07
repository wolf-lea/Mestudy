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
import com.tecsun.sisp.adapter.modules.monitor.entity.response.RealTimeDischargeVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl;

import cn.hutool.core.date.DateUtil;

/**
 * 近24小时交易信息---本地就医出院人次统计数据写入redis
 */
public class RealTimeLocalDischargeVisitorsSchedule {
	
	private static Logger logger = LoggerFactory.getLogger(RealTimeLocalDischargeVisitorsSchedule.class);

	@Autowired
	private MonitorServiceImpl monitorServiceImpl;
	
	
	public void saveRealTimeLocalDischargeVisitorsData() {
		
		logger.info("....定时任务.....近24小时交易信息本地就医出院人次统计数据写入redis.....开始执行.......");
		long beginTime = DateUtil.currentSeconds();
		
		// 查询前一天24小时内交易信息  00:00:00 ~ 23:59:59
		String startTime = DateUtil.format(DateUtil.yesterday(), "yyyy/MM/dd 00:00:00");
		String endTime = DateUtil.format(DateUtil.yesterday(), "yyyy/MM/dd 23:59:59");
		
		RealTimeTransactionQueryBean queryBean = new RealTimeTransactionQueryBean();
		queryBean.setStartTime(startTime);
		queryBean.setEndTime(endTime);
		
		List<RealTimeDischargeVisitorsVO> datas = new ArrayList<>();
		List<RealTimeDischargeVisitorsVO> compareDatas = new ArrayList<>();
		
		// compareDatas添加数据，compareDatas默认里面各时间段（0~23）的出院人次数据都为0
		for (int index = 0; index < 24; index++) {
			compareDatas.add(index, new RealTimeDischargeVisitorsVO(String.valueOf(index), 0));
		}
		
		try {
			// 清除缓存过期数据
			JedisUtil.delValue("localRealTimeDischarge");
			datas = monitorServiceImpl.getRealTimeLocalDischargeVisitors4Other(queryBean);
			
			// 两个集合取并集，将某些时间段没有数据就置为0，最终所有的数据全部存储在compareDatas
			compareDatas.removeAll(datas);
			compareDatas.addAll(datas);
			
			// 这里使用Guava   Maps将list转map，用以计算每两个小时的数据
			Map<String, RealTimeDischargeVisitorsVO> maps = Maps.uniqueIndex(compareDatas, new Function<RealTimeDischargeVisitorsVO, String>() {

				@Override
				public String apply(RealTimeDischargeVisitorsVO vo) {
					return vo.getCurrentHour();
				}

			});
			/*String mapJsonData = JsonMapper.toNormalJson(maps);
			logger.info("==========转换成map " + mapJsonData + "==========");*/
			
			// 将datas清空，重复利用
			datas.clear();
			for (int i = 0; i < 12; i++) {
				RealTimeDischargeVisitorsVO data = new RealTimeDischargeVisitorsVO();
				Integer discharge = maps.get(String.valueOf(i*2)).getDischarge() + maps.get(String.valueOf(i*2 + 1)).getDischarge();
				data.setCurrentHour(String.valueOf(i));
				data.setDischarge(discharge);
				datas.add(i, data);
			}
			String jsonData = JsonMapper.toNormalJson(datas);
			logger.info("********近24小时交易信息本地就医出院人次统计数据" + jsonData + "********");
			JedisUtil.setValue("localRealTimeDischarge", jsonData, 24*60);
			
			long finishTime = DateUtil.currentSeconds();
			logger.info(".........近24小时交易信息本地就医出院人次统计数据成功写入redis.........");
			logger.info(".........近24小时交易信息本地就医出院人次统计数据写入redis耗时" + (finishTime - beginTime) + "秒.........");
			
		} catch (Exception e) {
			logger.info("近24小时交易信息本地就医出院人次统计数据写入redis异常", e);
		}
		
	}

	
}
