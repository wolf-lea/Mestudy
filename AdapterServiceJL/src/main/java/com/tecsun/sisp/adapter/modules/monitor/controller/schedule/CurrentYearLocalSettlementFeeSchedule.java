package com.tecsun.sisp.adapter.modules.monitor.controller.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tecsun.sisp.adapter.common.util.JedisUtil;
import com.tecsun.sisp.adapter.common.util.JsonMapper;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.MedicalStatisticsQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearSettlementFeeVO;
import com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;

/**
 * 当年就医结算---本地就医结算费用统计数据写入redis
 */
public class CurrentYearLocalSettlementFeeSchedule {
	
	private static Logger logger = LoggerFactory.getLogger(CurrentYearLocalSettlementFeeSchedule.class);

	@Autowired
	private MonitorServiceImpl monitorServiceImpl;
	
	
	public void saveCurrentYearLocalSettlementFeeData() {
		
		// 当年时间
		String currentYear = DateUtil.format(new Date(), "yyyy");
		logger.info("....定时任务....." + currentYear + "年本地就医结算费用统计数据写入redis.....开始执行.......");
		long startTime = DateUtil.currentSeconds();
		
		MedicalStatisticsQueryBean queryBean = new MedicalStatisticsQueryBean();
		queryBean.setCurrentYear(currentYear);
		
		CurrentYearSettlementFeeVO data = new CurrentYearSettlementFeeVO();
		List<CurrentYearSettlementFeeVO> datas = new ArrayList<>();
		List<CurrentYearSettlementFeeVO> compareDatas = new ArrayList<>();
		
		for (int index = 0; index < 12; index++) {
			CurrentYearSettlementFeeVO vo = new CurrentYearSettlementFeeVO();
			if (index < 9 ) {
				vo.setSettlementFee(0.0);
				vo.setCurrentMonth(currentYear + "/0" + String.valueOf(index + 1));
			} else {
				vo.setSettlementFee(0.0);
				vo.setCurrentMonth(currentYear + "/" + String.valueOf(index + 1));
			}
			compareDatas.add(index, vo);
		}
		
		try {
			JedisUtil.delValue("localSettlementFeeStatistics");
			// 获取数据
			datas = monitorServiceImpl.getCurrentYearLocalSettelmentFee4Other(queryBean);
			
			compareDatas.removeAll(datas);
			compareDatas.addAll(datas);
			
			// 将数据按照月份排序
			Collections.sort(compareDatas, new Comparator<CurrentYearSettlementFeeVO>() {

				@Override
				public int compare(CurrentYearSettlementFeeVO vo1, CurrentYearSettlementFeeVO vo2) {
					int firstMonth = Integer.valueOf(vo1.getCurrentMonth().substring(5, 7));
					int nextMonth = Integer.valueOf(vo2.getCurrentMonth().substring(5, 7));
					return firstMonth - nextMonth;
				}
			});
			
			if (CollUtil.isNotEmpty(compareDatas)) {
				for (int index = 0; index < compareDatas.size(); index++) {
					data = compareDatas.get(index);
					double totalFee = data.getSettlementFee();
					// 转换金额单位      元--->万元
					totalFee = NumberUtil.round(totalFee/10000, 2).doubleValue();
					data.setSettlementFee(totalFee);
					compareDatas.set(index, data);
				}
			}
			// 转换成json字符串
			String jsonData = JsonMapper.toNormalJson(compareDatas);
			logger.info("********" + currentYear + "年本地就医结算费用统计数据" + jsonData + "********");
			JedisUtil.setValue("localSettlementFeeStatistics", jsonData, 24*60);
			
			long endTime = DateUtil.currentSeconds();
			logger.info("........." + currentYear + "年本地就医结算费用统计数据成功写入redis.........");
			logger.info("........." + currentYear + "年本地就医结算费用统计数据写入redis耗时" + (endTime - startTime) + "秒.........");
			
		} catch (Exception e) {
			logger.info(currentYear + "年本地就医结算费用统计数据写入redis异常", e);
		}
		
	}
	

}
