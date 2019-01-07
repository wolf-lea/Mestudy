package com.tecsun.sisp.adapter.modules.monitor.controller.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tecsun.sisp.adapter.common.util.DictionaryUtil;
import com.tecsun.sisp.adapter.common.util.JedisUtil;
import com.tecsun.sisp.adapter.common.util.JsonMapper;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.RankingQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearSettlementRankingVO;
import com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;

/**
 * 当年结算排名统计数据写入redis
 */
public class CurrentYearSettlementRankingSchedule {

	private static Logger logger = LoggerFactory.getLogger(CurrentYearSettlementRankingSchedule.class);

	@Autowired
	private MonitorServiceImpl monitorServiceImpl;
	

	public void saveCurrentYearSettlementRankingData() {

		// 当前年度
		String currentYear = DateUtil.format(new Date(), "yyyy");
		logger.info("....定时任务....." + currentYear + "年结算排名统计数据写入redis.....开始执行.......");
		long startTime = DateUtil.currentSeconds();

		RankingQueryBean queryBean = new RankingQueryBean();
		queryBean.setCurrentYear(currentYear);

		CurrentYearSettlementRankingVO data = new CurrentYearSettlementRankingVO();
		List<CurrentYearSettlementRankingVO> datas = new ArrayList<>();

		try {
			JedisUtil.delValue("offsiteSettlementRanking");
			datas = monitorServiceImpl.getCurrentYearSettlementRanking4Other(queryBean);
			if (datas != null && !datas.isEmpty()) {
				for (int index = 0; index < datas.size(); index++) {
					
					data = datas.get(index);
					String cityCode = data.getCityName();
					// 查询数据字典，通过cityCode获取cityName
					cityCode = DictionaryUtil.getDictName("CITY_CODE", cityCode);
					data.setCityName(cityCode);
					// 转换结算费用金额        元 ---> 万元
					double settlementAmount = data.getSettlementAmount();
					settlementAmount = NumberUtil.round(settlementAmount/10000, 2).doubleValue();
					data.setSettlementAmount(settlementAmount);
				}
				String jsonData = JsonMapper.toNormalJson(datas);
				logger.info("********" + currentYear + "年结算排名统计数据" + jsonData + "********");
				JedisUtil.setValue("offsiteSettlementRanking", jsonData, 24*60);
			}
			
			long endTime = DateUtil.currentSeconds();;
			logger.info("........." + currentYear + "年结算排名统计数据成功写入redis.........");
			logger.info("........." + currentYear + "年结算排名统计数据写入redis耗时" + (endTime - startTime) + "秒.........");
			
		} catch (Exception e) {
			logger.info(currentYear + "年结算排名统计数据写入redis异常", e);
		}

	}


}
