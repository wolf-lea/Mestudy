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
import com.tecsun.sisp.adapter.modules.monitor.entity.response.RealTimeSettlementFeeVO;
import com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;

/**
 * 近24小时交易信息---异地就医结算费用统计数据写入redis 
 */
public class RealTimeOffsiteSettlementFeeSchedule {
	
	private static Logger logger = LoggerFactory.getLogger(RealTimeOffsiteSettlementFeeSchedule.class);

	@Autowired
	private MonitorServiceImpl monitorServiceImpl;
	
	
	public void saveRealTimeOffsiteSettlementFeeData() {
		
		logger.info("....定时任务.....近24小时交易信息异地就医结算费用统计数据写入redis.....开始执行.......");
		long beginTime = DateUtil.currentSeconds();
		
		// 查询前一天24小时内交易信息  00:00:00 ~ 23:59:59
		String startTime = DateUtil.format(DateUtil.yesterday(), "yyyy/MM/dd 00:00:00");
		String endTime = DateUtil.format(DateUtil.yesterday(), "yyyy/MM/dd 23:59:59");
		
		RealTimeTransactionQueryBean queryBean = new RealTimeTransactionQueryBean();
		queryBean.setStartTime(startTime);
		queryBean.setEndTime(endTime);
		
		List<RealTimeSettlementFeeVO> datas = new ArrayList<>();
		List<RealTimeSettlementFeeVO> compareDatas = new ArrayList<>();
		
		// compareDatas添加数据，compareDatas默认里面各时间段（0~23）的结算费用数据都为0
		for (int index = 0; index < 24; index++) {
			compareDatas.add(index, new RealTimeSettlementFeeVO(String.valueOf(index), 0.0));
		}
		
		try {
			// 清除缓存中过期数据
			JedisUtil.delValue("offsiteRealTimeSettlementFee");
			datas = monitorServiceImpl.getRealTimeOffsiteSettlementFee4Other(queryBean);
			
			// 将compareDatas与datas取并集，将某些时间段没有结算费用数据置为0.0，最终所有时间段的数据全部存储在compareDatas
			compareDatas.removeAll(datas);
			compareDatas.addAll(datas);
			
			// Guava 将compareDatas转成map
			Map<String, RealTimeSettlementFeeVO> maps = Maps.uniqueIndex(compareDatas, new Function<RealTimeSettlementFeeVO, String>() {

				@Override
				public String apply(RealTimeSettlementFeeVO vo) {
					return vo.getCurrentHour();
				}
			});
			
			// 清空datas，重复利用
			datas.clear();
			double settlementFee = 0.0;
			for (int i = 0; i < 12; i++) {
				RealTimeSettlementFeeVO data = new RealTimeSettlementFeeVO();
				// 统计每两个小时的结算费用，转换费用单位         元--->万元         NumberUtil.round()
				settlementFee = maps.get(String.valueOf(i*2)).getSettlementFee() + maps.get(String.valueOf(i*2 + 1)).getSettlementFee();
				settlementFee = NumberUtil.round(settlementFee/10000, 2).doubleValue();
				data.setCurrentHour(String.valueOf(i));
				data.setSettlementFee(settlementFee);
				datas.add(i, data);
			}
			String jsonData = JsonMapper.toNormalJson(datas);
			logger.info("********近24小时交易信息异地就医结算费用统计数据 " + jsonData + "********");
			JedisUtil.setValue("offsiteRealTimeSettlementFee", jsonData, 24*60);
			
			long finishTime = DateUtil.currentSeconds();
			logger.info(".........近24小时交易信息异地就医结算费用统计数据成功写入redis.........");
			logger.info(".........近24小时交易信息异地就医结算费用统计数据写入redis耗时 " + (finishTime - beginTime) + " 秒.........");
			
		} catch (Exception e) {
			logger.info("近24小时交易信息异地就医结算费用统计数据写入redis异常", e);
		}
		
		
		
		
	}

}
