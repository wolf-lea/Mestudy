package com.tecsun.sisp.adapter.modules.monitor.controller.schedule;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tecsun.sisp.adapter.common.util.JedisUtil;
import com.tecsun.sisp.adapter.common.util.JsonMapper;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.HospitalQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.TodaySettlementFeeVO;
import com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
/**
 * 今日出院结算费用统计数据写入redis
 */
public class TodaySettlementFeeSchedule {
	
	private static Logger logger = LoggerFactory.getLogger(TodaySettlementFeeSchedule.class);
	 
	 @Autowired
	 private MonitorServiceImpl monitorServiceImpl;
	 
	 
	 public void saveTodaySettlementFeeData() {
		 
		 String date = DateUtil.yesterday().toString(DatePattern.CHINESE_DATE_FORMAT);
		 logger.info("....定时任务.....本地医院" + date + "出院结算费用统计数据写入redis.....开始执行.......");
		 long startTime = DateUtil.currentSeconds();
		 
		 // 获取前一天时间
		 String previousDate = DateUtil.yesterday().toDateStr().replace("-", "/");
		 
		 HospitalQueryBean queryBean = new HospitalQueryBean();
		 queryBean.setCurrentDate(previousDate);
		 
		 TodaySettlementFeeVO data = new TodaySettlementFeeVO();
		 List<TodaySettlementFeeVO> datas = new ArrayList<>();
		 
		 try {
			 JedisUtil.delValue("localDischargeSettlement");
			 datas = monitorServiceImpl.getTodaySettlementFee4Other(queryBean);
				if (datas != null && !datas.isEmpty()) {
					for (int index = 0; index < datas.size(); index++) {
						data = datas.get(index);
						double settlementFeeCount = data.getSettlementFee();
						// 转换消费金额单位          元 ---> 万元
						settlementFeeCount = NumberUtil.round(settlementFeeCount/10000, 2).doubleValue();
						data.setSettlementFee(settlementFeeCount);
						datas.set(index, data);
					}
					// 统计今日医院数据写入redis
					String jsonData = JsonMapper.toNormalJson(datas);
					logger.info("********本地医院" + date + "出院结算费用统计数据" + jsonData + "********");
					JedisUtil.setValue("localDischargeSettlement", jsonData, 24*60);
				}
				
				long endTime = DateUtil.currentSeconds();
				logger.info(".........本地医院" + date + "出院结算费用统计数据成功写入redis.........");
				logger.info(".........本地医院" + date + "出院结算费用统计数据写入redis耗时" + (endTime-startTime) + "秒.........");
				
			} catch (Exception e) {
				logger.info("本地医院" + date + "出院结算费用统计数据写入redis异常", e);
			}
		 
	 }
	

}
