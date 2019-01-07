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
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearSettlementVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;

/**
 * 当年就医统计---异地就医结算人次统计数据写入redis
 */
public class CurrentYearOffsiteSettlementVisitorsSchedule {
	
	private static Logger logger = LoggerFactory.getLogger(CurrentYearOffsiteSettlementVisitorsSchedule.class);

	@Autowired
	private MonitorServiceImpl monitorServiceImpl;
	
	
	public void saveCurrentYearOffsiteSettlementVisitorsData() {
		
		// 当年时间
		String currentYear = DateUtil.format(new Date(), "yyyy");
		logger.info("....定时任务....." + currentYear + "年异地就医结算人次统计数据写入redis.....开始执行.......");
		long startTime = DateUtil.currentSeconds();
		
		MedicalStatisticsQueryBean queryBean = new MedicalStatisticsQueryBean();
		queryBean.setCurrentYear(currentYear);
		
		List<CurrentYearSettlementVisitorsVO> datas = new ArrayList<>();
		List<CurrentYearSettlementVisitorsVO> compareDatas = new ArrayList<>();
		
		for (int index = 0; index < 12; index++) {
			CurrentYearSettlementVisitorsVO vo = new CurrentYearSettlementVisitorsVO();
			if (index < 9) {
				vo.setSettlementNumber(0);
				vo.setCurrentMonth(currentYear + "/0" + String.valueOf(index + 1));
			} else {
				vo.setSettlementNumber(0);
				vo.setCurrentMonth(currentYear + "/" + String.valueOf(index + 1));
			}
			compareDatas.add(index, vo);
		}
		
		try {
			JedisUtil.delValue("offsiteSettlementStatistics");
			// 获取统计数据
			datas = monitorServiceImpl.getCurrentYearOffsiteSettlementVisitors4Other(queryBean);
			
			compareDatas.removeAll(datas);
			compareDatas.addAll(datas);
			
			Collections.sort(compareDatas, new Comparator<CurrentYearSettlementVisitorsVO>() {

				@Override
				public int compare(CurrentYearSettlementVisitorsVO vo1, CurrentYearSettlementVisitorsVO vo2) {
					int firstMonth = Integer.valueOf(vo1.getCurrentMonth().substring(5, 7));
					int nextMonth = Integer.valueOf(vo2.getCurrentMonth().substring(5, 7));
					return firstMonth - nextMonth;
				}
			});

			if (CollUtil.isNotEmpty(compareDatas)) {
				// 异地结算人次数据写入redis
				String jsonData = JsonMapper.toNormalJson(compareDatas);
				logger.info("********" + currentYear + "年异地就医结算人次统计数据" + jsonData + "********");
				JedisUtil.setValue("offsiteSettlementStatistics", jsonData, 24*60);
			}
			
			long endTime = DateUtil.currentSeconds();
			logger.info("........." + currentYear + "年异地就医结算人次统计数据成功写入redis.........");
			logger.info("........." + currentYear + "年异地就医结算人次统计数据写入redis耗时" + (endTime - startTime) + "秒.........");
			
		} catch (Exception e) {
			logger.info(currentYear + "年异地就医结算人次统计数据写入redis异常", e);
		}
		
	}
	

}
