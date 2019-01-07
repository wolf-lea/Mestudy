package com.tecsun.sisp.adapter.modules.monitor.controller.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tecsun.sisp.adapter.common.util.JedisUtil;
import com.tecsun.sisp.adapter.common.util.JsonMapper;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.HospitalQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.HospitalSituationVO;
import com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;

/**
 * 今日情况---异地医院出院人次及消费数据写入redis
 */
public class TodayOffsiteHospitalSituationSchedule {

	private static Logger logger = LoggerFactory.getLogger(TodayOffsiteHospitalSituationSchedule.class);

	@Autowired
	private MonitorServiceImpl monitorServiceImpl;
	

	public void saveTodayOffsiteHospitalSituationData() {
		
		String date = DateUtil.yesterday().toString(DatePattern.CHINESE_DATE_FORMAT);
		logger.info("....定时任务....." + date + "异地医院情况统计数据写入redis.....开始执行.......");
		long startTime = DateUtil.currentSeconds();

		// 获取前一天时间
		String previousDate = DateUtil.yesterday().toDateStr().replace("-", "/");

		HospitalQueryBean queryBean = new HospitalQueryBean();
		queryBean.setCurrentDate(previousDate);

		HospitalSituationVO data;
		String jsonData;

		try {
			JedisUtil.delValue("offsiteHospitalSituation");
			data = monitorServiceImpl.getTodayOffsiteHospitalSituation4Other(queryBean);
			if (data != null) {
				// 转换消费金额单位       元 ---> 万元
				double consumption = data.getConsumption();
				consumption = NumberUtil.round(consumption/10000, 2).doubleValue();
				data.setConsumption(consumption);
			} else {
				// 未查询到异地医院统计数据，手动设值，防止接口在获取缓存数据解析时空指针异常
				data = new HospitalSituationVO();
				data.setConsumption(0.0);
				data.setDischargeNum(0);
			}
			
			// 统计今日医院数据写入redis
			jsonData = JsonMapper.toNormalJson(data);
			logger.info("********" + date + "异地医院情况统计数据" + jsonData + "********");
			JedisUtil.setValue("offsiteHospitalSituation", jsonData, 24*60);
			
			long endTime = DateUtil.currentSeconds();
			logger.info("........." + date + "异地医院情况统计数据成功写入redis.........");
			logger.info("........." + date + "异地医院情况统计数据写入redis耗时" + (endTime - startTime) + "秒.........");
			
		} catch (Exception e) {
			logger.info(date + "异地医院情况统计数据写入redis异常", e);
		}
		
	}


}
