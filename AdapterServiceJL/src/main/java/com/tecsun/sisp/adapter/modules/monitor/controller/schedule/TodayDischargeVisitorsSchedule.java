package com.tecsun.sisp.adapter.modules.monitor.controller.schedule;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tecsun.sisp.adapter.common.util.JedisUtil;
import com.tecsun.sisp.adapter.common.util.JsonMapper;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.HospitalQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.TodayDischargeVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

/**
 * 今日出院人次统计数据写入redis
 */
public class TodayDischargeVisitorsSchedule {

	private static Logger logger = LoggerFactory.getLogger(TodayDischargeVisitorsSchedule.class);

	@Autowired
	private MonitorServiceImpl monitorServiceImpl;

	
	public void saveTodayDischargeVisitorsData() {
		
		String date = DateUtil.yesterday().toString(DatePattern.CHINESE_DATE_FORMAT);
		logger.info("....定时任务.....本地医院" + date + "出院人次统计数据写入redis.....开始执行.......");
		long startTime = DateUtil.currentSeconds();

		// 获取前一天时间
		String previousDate = DateUtil.yesterday().toDateStr().replace("-", "/");
		HospitalQueryBean queryBean = new HospitalQueryBean();
		queryBean.setCurrentDate(previousDate);

		List<TodayDischargeVisitorsVO> datas = new ArrayList<>();

		try {
			JedisUtil.delValue("localDischarge");
			datas = monitorServiceImpl.getTodayDischargeVisitors4Other(queryBean);
			if (datas != null && !datas.isEmpty()) {
				// 统计今日出院人次数据写入redis
				String jsonData = JsonMapper.toNormalJson(datas);
				logger.info("********本地医院" + date + "出院人次统计数据" + jsonData + "********");
				// 设置localDischarge键值有效期为24小时
				JedisUtil.setValue("localDischarge", jsonData, 24*60);
			}
			
			long endTime = DateUtil.currentSeconds();
			logger.info(".........本地医院" + date + "出院人次统计数据成功写入redis.........");
			logger.info(".........本地医院" + date + "出院人次统计数据写入redis耗时" + (endTime - startTime) + "秒.........");
			
		} catch (Exception e) {
			logger.info("本地医院" + date + "出院人次数据写入redis异常", e);
		}
		
	}

}
