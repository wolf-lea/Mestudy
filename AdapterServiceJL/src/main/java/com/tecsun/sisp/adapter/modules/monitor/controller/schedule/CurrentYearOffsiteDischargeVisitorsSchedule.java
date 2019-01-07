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
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearDischargeVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;

/**
 * 当年就医统计---异地就医出院人次统计数据写入redis
 */
public class CurrentYearOffsiteDischargeVisitorsSchedule {
	
	private static Logger logger = LoggerFactory.getLogger(CurrentYearOffsiteDischargeVisitorsSchedule.class);

	@Autowired
	private MonitorServiceImpl monitorServiceImpl;
	
	
	public void saveCurrentYearOffsiteDischargeVisitorsData() {
		
		// 当年时间
		String currentYear = DateUtil.format(new Date(), "yyyy");
		logger.info("....定时任务....." + currentYear + "年异地就医出院人次统计数据写入redis.....开始执行.......");
		long startTime = DateUtil.currentSeconds();
		
		MedicalStatisticsQueryBean queryBean = new MedicalStatisticsQueryBean();
		queryBean.setCurrentYear(currentYear);
		
		List<CurrentYearDischargeVisitorsVO> datas = new ArrayList<>();
		List<CurrentYearDischargeVisitorsVO> compareDatas = new ArrayList<>();
		
		// 手动将compareDatas所有月份的数据置为0
		for (int index = 0; index < 12; index++) {
			CurrentYearDischargeVisitorsVO vo = new CurrentYearDischargeVisitorsVO();
			if (index < 9) {
				vo.setDischarge(0);
				vo.setCurrentMonth(currentYear + "/0" + String.valueOf(index + 1));
			} else {
				vo.setDischarge(0);
				vo.setCurrentMonth(currentYear + "/" + String.valueOf(index + 1));
			}
			compareDatas.add(index, vo);
		}
		
		try {
			JedisUtil.delValue("offsiteDischargeStatistics");
			// 获取统计数据
			datas = monitorServiceImpl.getCurrentYearOffsiteDischargeVisitors4Other(queryBean);
			
			// compareDatas与datas两个集合取并集，某些月份没有查询到数据的就置为0，便于接口后续处理每个月份对应的数据
			compareDatas.removeAll(datas);
			compareDatas.addAll(datas);
			
			// 按照月份将compareDatas数据排序
			Collections.sort(compareDatas, new Comparator<CurrentYearDischargeVisitorsVO>() {

				@Override
				public int compare(CurrentYearDischargeVisitorsVO vo1, CurrentYearDischargeVisitorsVO vo2) {
					int firstMonth = Integer.valueOf(vo1.getCurrentMonth().substring(5, 7));
					int nextMonth = Integer.valueOf(vo2.getCurrentMonth().substring(5, 7));
					return firstMonth - nextMonth;
				}
			});

			if (CollUtil.isNotEmpty(compareDatas)) {
				// 异地就医出院人次数据写入redis
				String jsonData = JsonMapper.toNormalJson(compareDatas);
				logger.info("********" + currentYear + "年异地就医出院人次统计数据" + jsonData + "********");
				JedisUtil.setValue("offsiteDischargeStatistics", jsonData, 24*60);
			}
			
			long endTime = DateUtil.currentSeconds();
			logger.info("........." + currentYear + "年异地就医出院人次统计数据成功写入redis.........");
			logger.info("........." + currentYear + "年异地就医出院人次统计数据写入redis耗时" + (endTime - startTime) + "秒.........");
			
		} catch (Exception e) {
			logger.info(currentYear + "年异地就医出院人次统计数据写入redis异常", e);
		}
		
	}

	
}
