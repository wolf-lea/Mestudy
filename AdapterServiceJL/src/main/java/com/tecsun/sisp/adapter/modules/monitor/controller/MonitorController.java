package com.tecsun.sisp.adapter.modules.monitor.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.JedisUtil;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.HospitalQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.HospitalRealTimeSettlementQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.MedicalStatisticsQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.RealTimeTransactionQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.SituationQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.TransactionQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearDischargeVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearMedicalStatisticsVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearSettlementFeeVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearSettlementRankingVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearSettlementVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.FundPaymentVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.HospitalRealTimeSettlementVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.HospitalSituationVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.MedicalFeeDetailVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.MedicalTreatmentSettlementVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.PersonalInfoVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.PersonalSettlementVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.PharmacySituationVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.RealTimeDischargeVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.RealTimeSettlementFeeVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.RealTimeSettlementVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.RealTimeTransactionVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.TodayDischargeVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.TodaySettlementFeeVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.TodaySituationVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.TransactionVO;
import com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONUtil;

@Controller
@RequestMapping(value = "/adapter/monitor")
public class MonitorController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(MonitorController.class);

	@Autowired
	private MonitorServiceImpl monitorServiceImpl;
	
	private static final String LOCAL_AREA_FLAG = "local";
	
	
	/**
	 * 当年就医统计
	 * @param queryBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCurrentYearMedicalStatistics", method = RequestMethod.POST, produces = "application/json")
	public Result getCurrentYearMedicalStatistics(@RequestBody MedicalStatisticsQueryBean queryBean) {
		
		logger.info("---------------MonitorController: getCurrentYearMedicalStatistics---------------");
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		// 当年就医统计
		List<CurrentYearMedicalStatisticsVO> statisticsDatas = new ArrayList<>();
		// 结算费用   出院人次   结算人次
		List<CurrentYearSettlementFeeVO> fee = new ArrayList<>();
		List<CurrentYearDischargeVisitorsVO> discharge = new ArrayList<>();
		List<CurrentYearSettlementVisitorsVO> visitors = new ArrayList<>();
		
		// feeJsonStr:结算费用          dischargeJsonStr：出院人次            settlementJsonStr:结算人次 
		String feeJsonStr;
		String dischargeJsonStr;
		String settlementJsonStr;
		
		try {
			if (LOCAL_AREA_FLAG.equals(queryBean.getAreaFlag())) {
				// 从缓存中获取当年本地就医结算费用统计数据
				feeJsonStr = JedisUtil.getValue("localSettlementFeeStatistics");
				fee = JSONObject.parseArray(feeJsonStr, CurrentYearSettlementFeeVO.class);
				
				// 从缓存中获取当年本地就医出院人次统计数据
				dischargeJsonStr = JedisUtil.getValue("localDischargeStatistics");
				discharge = JSONObject.parseArray(dischargeJsonStr, CurrentYearDischargeVisitorsVO.class);
				
				// 从缓存中获取当年本地就医结算人次统计数据
				settlementJsonStr = JedisUtil.getValue("localSettlementStatistics");
				visitors = JSONObject.parseArray(settlementJsonStr, CurrentYearSettlementVisitorsVO.class);
				
				if (CollUtil.isNotEmpty(fee) && CollUtil.isNotEmpty(discharge) && CollUtil.isNotEmpty(visitors)) {
					for (int index = 0 ; index < fee.size(); index++) {
						CurrentYearMedicalStatisticsVO statisticsVo = new CurrentYearMedicalStatisticsVO();
						statisticsVo.setCurrentMonth(fee.get(index).getCurrentMonth());
						statisticsVo.setSettlementFee(fee.get(index).getSettlementFee());
						statisticsVo.setDischargeVisitors(discharge.get(index).getDischarge());
						statisticsVo.setSettlementVisitors(visitors.get(index).getSettlementNumber());
						statisticsDatas.add(index, statisticsVo);
					}
				}
			} else {
				// 从缓存中获取当年异地就医结算费用统计数据
				feeJsonStr = JedisUtil.getValue("offsiteSettlementFeeStatistics");
				fee = JSONObject.parseArray(feeJsonStr, CurrentYearSettlementFeeVO.class);
				
				// 从缓存中获取当年异地就医出院人次统计数据
				dischargeJsonStr = JedisUtil.getValue("offsiteDischargeStatistics");
				discharge = JSONObject.parseArray(dischargeJsonStr, CurrentYearDischargeVisitorsVO.class);
				
				// 从缓存中获取当年异地就医结算人次统计数据
				settlementJsonStr = JedisUtil.getValue("offsiteSettlementStatistics");
				visitors = JSONObject.parseArray(settlementJsonStr, CurrentYearSettlementVisitorsVO.class);
				
				if (CollUtil.isNotEmpty(fee) && CollUtil.isNotEmpty(discharge) && CollUtil.isNotEmpty(visitors)) {
					for (int index = 0 ; index < fee.size(); index++) {
						CurrentYearMedicalStatisticsVO statisticsVo = new CurrentYearMedicalStatisticsVO();
						statisticsVo.setCurrentMonth(fee.get(index).getCurrentMonth());
						statisticsVo.setSettlementFee(fee.get(index).getSettlementFee());
						statisticsVo.setDischargeVisitors(discharge.get(index).getDischarge());
						statisticsVo.setSettlementVisitors(visitors.get(index).getSettlementNumber());
						statisticsDatas.add(index, statisticsVo);
					}
				}

			}
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询当年就医统计数据异常：", e);
		}
		
		return new Result(result, message, statisticsDatas);
	}
	
	
	/**
	 * 今日情况
	 * @param queryBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTodaySituation", method = RequestMethod.POST, produces = "application/json")
	public Result getTodaySituation(@RequestBody SituationQueryBean queryBean) {
		
		logger.info("---------------MonitorController: getTodaySituation---------------");
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		TodaySituationVO todaySituation = new TodaySituationVO();
		HospitalSituationVO hospitalSituation = new HospitalSituationVO();
		PharmacySituationVO pharmacySituation = new PharmacySituationVO();
		
		String hospitalJsonStr;
		String pharmacyJsonStr;
		
		try {
			if (LOCAL_AREA_FLAG.equals(queryBean.getAreaFlag())) {
				// 从缓存中获取本地今日医院情况统计数据 
				hospitalJsonStr = JedisUtil.getValue("localHospitalSituation");
				hospitalSituation = JSONUtil.toBean(hospitalJsonStr, HospitalSituationVO.class);
				
				// 从缓存中获取本地今日药店情况统计数据 
				pharmacyJsonStr = JedisUtil.getValue("localPharmacySituation");
				pharmacySituation =  JSONUtil.toBean(pharmacyJsonStr, PharmacySituationVO.class);
				
			} else {
				// 从缓存中获取异地今日医院情况统计数据 
				hospitalJsonStr = JedisUtil.getValue("offsiteHospitalSituation");
				hospitalSituation = JSONUtil.toBean(hospitalJsonStr, HospitalSituationVO.class);
				
				// 从缓存中获取异地今日药店情况统计数据
				pharmacyJsonStr = JedisUtil.getValue("offSitePharmacySituation");
				pharmacySituation = JSONUtil.toBean(pharmacyJsonStr, PharmacySituationVO.class);
				
			}
			todaySituation.setHospitalSituation(hospitalSituation);
			todaySituation.setPharmacySituation(pharmacySituation);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询今日情况数据异常：", e);
		}
		
		return result(result, message, todaySituation);
	}
	
	
	/**
	 * 今日出院人次
	 * @param queryBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTodayDischargeVisitorsStatistics", method = RequestMethod.POST, produces = "application/json")
	public Result getTodayDischargeVisitorsStatistics(@RequestBody HospitalQueryBean queryBean) {
		
		logger.info("---------------MonitorController: getTodayDischargeVisitorsStatistics---------------");
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<TodayDischargeVisitorsVO> datas = new ArrayList<>();
		Page<TodayDischargeVisitorsVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());

		try {
			String jsonStr = JedisUtil.getValue("localDischarge");
			datas = JSONObject.parseArray(jsonStr, TodayDischargeVisitorsVO.class);
			if (CollUtil.isNotEmpty(datas)) {
				page.setData(datas);
			}
			
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询今日出院人次统计数据异常：", e);
		}
		
		return result(result, message, page);
	}
	
	
	/**
	 * 今日出院结算费用
	 * @param queryBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTodaySettlementFeeStatistics", method = RequestMethod.POST, produces = "application/json")
	public Result getTodaySettlementFeeStatistics(@RequestBody HospitalQueryBean queryBean) {
		
		logger.info("---------------MonitorController: getTodaySettlementFeeStatistics---------------");
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<TodaySettlementFeeVO> datas = new ArrayList<>();
		Page<TodaySettlementFeeVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			String jsonStr = JedisUtil.getValue("localDischargeSettlement");
			datas = JSONObject.parseArray(jsonStr, TodaySettlementFeeVO.class);
			if (CollUtil.isNotEmpty(datas)) {
				page.setData(datas);
			}
			
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询今日出院结算费用统计数据异常：", e);
		}
		
		return result(result, message, page);
	}
	
	
	/**
	 * 当年结算排名
	 * @param queryBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCurrentYearSettlementRanking", method = RequestMethod.POST, produces = "application/json")
	public Result getCurrentYearSettlementRanking(@RequestBody SituationQueryBean queryBean) {
		
		logger.info("---------------MonitorController: getCurrentYearSettlementRanking---------------");
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<CurrentYearSettlementRankingVO> datas = new ArrayList<>();
		Page<CurrentYearSettlementRankingVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			String rankingJsonStr = JedisUtil.getValue("offsiteSettlementRanking");
			datas = JSONObject.parseArray(rankingJsonStr, CurrentYearSettlementRankingVO.class);
			if (CollUtil.isNotEmpty(datas)) {
				page.setData(datas);
			}
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询当年结算排名统计数据异常：", e);
		}
		
		return result(result, message, page);
	}
	
	
	/**
	 * 近24小时交易信息
	 * @param queryBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRealTimeTransactionStatistics", method = RequestMethod.POST, produces = "application/json")
	public Result getRealTimeTransactionStatistics(@RequestBody RealTimeTransactionQueryBean queryBean) {
		
		logger.info("---------------MonitorController: getRealTimeTransactionStatistics---------------");
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		// 近24小时交易信息
		List<RealTimeTransactionVO> transaction = new ArrayList<>();
		
		// 结算费用   出院人次   结算人次
		List<RealTimeSettlementFeeVO> fee = new ArrayList<>();
		List<RealTimeSettlementVisitorsVO> visitors = new ArrayList<>();
		List<RealTimeDischargeVisitorsVO> discharge = new ArrayList<>();
		
		// feeJsonStr:结算费用          dischargeJsonStr：出院人次           settlementJsonStr:结算人次 
		String feeJsonStr;
		String dischargeJsonStr;
		String settlementJsonStr;
		
		try {
			if (LOCAL_AREA_FLAG.equals(queryBean.getAreaFlag())) {
				
				// 从缓存中获取本地就医结算费用统计数据
				feeJsonStr = JedisUtil.getValue("localRealTimeSettlementFee");
				fee = JSONObject.parseArray(feeJsonStr, RealTimeSettlementFeeVO.class);
				
				// 从缓存中获取本地就医出院人次统计数据 
				dischargeJsonStr = JedisUtil.getValue("localRealTimeDischarge");
				discharge = JSONObject.parseArray(dischargeJsonStr, RealTimeDischargeVisitorsVO.class);
				
				// 从缓存中获取本地就医结算人次统计数据
				settlementJsonStr = JedisUtil.getValue("localRealTimeSettlement");
				visitors = JSONObject.parseArray(settlementJsonStr, RealTimeSettlementVisitorsVO.class);
				
				if (CollUtil.isNotEmpty(fee) && CollUtil.isNotEmpty(discharge) && CollUtil.isNotEmpty(visitors)) {
					for (int index = 0; index < fee.size(); index++) {
						RealTimeTransactionVO transactionVo = new RealTimeTransactionVO();
						transactionVo.setCurrentHour(fee.get(index).getCurrentHour());
						transactionVo.setSettlementFee(fee.get(index).getSettlementFee());
						transactionVo.setDischargeVisitors(discharge.get(index).getDischarge());
						transactionVo.setSettlementVisitors(visitors.get(index).getSettlementNumber());
						transaction.add(index, transactionVo);
					}
				}
				
			} else {
				// 从缓存中获取异地就医结算费用统计数据
				feeJsonStr = JedisUtil.getValue("offsiteRealTimeSettlementFee");
				fee = JSONObject.parseArray(feeJsonStr, RealTimeSettlementFeeVO.class);
				
				// 从缓存中获取异地就医出院人次统计数据 
				dischargeJsonStr = JedisUtil.getValue("offsiteRealTimeDischarge");
				discharge = JSONObject.parseArray(dischargeJsonStr, RealTimeDischargeVisitorsVO.class);
				
				// 从缓存中获取异地就医结算人次统计数据
				settlementJsonStr = JedisUtil.getValue("offsiteRealTimeSettlement");
				visitors = JSONObject.parseArray(settlementJsonStr, RealTimeSettlementVisitorsVO.class);
				
				if (CollUtil.isNotEmpty(fee) && CollUtil.isNotEmpty(discharge) && CollUtil.isNotEmpty(visitors)) {
					for (int index = 0; index < fee.size(); index++) {
						RealTimeTransactionVO transactionVo = new RealTimeTransactionVO();
						transactionVo.setCurrentHour(fee.get(index).getCurrentHour());
						transactionVo.setSettlementFee(fee.get(index).getSettlementFee());
						transactionVo.setDischargeVisitors(discharge.get(index).getDischarge());
						transactionVo.setSettlementVisitors(visitors.get(index).getSettlementNumber());
						transaction.add(index, transactionVo);
					}
				}
			}
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询近24小时交易统计数据异常：", e);
		}
		
		return result(result, message, transaction);
	}
	
	
	/**
	 * 医院实时结算情况（合并接口）
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getHospitalRealTimeSettlementInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getHospitalRealTimeSettlementInfo(@RequestBody HospitalRealTimeSettlementQueryBean queryBean) {
		logger.info("---------------MonitorController: getHospitalRealTimeSettlementInfo---------------");
    	logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean) + "---------------");
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		// 测试查询耗时
		long startTime = DateUtil.currentSeconds();
		
		// 当前交易结算时间，注释掉便于前后台联调，后续部署上环境放开注释
		/*String currentTime = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN.replace("-", "/"));
		queryBean.setCurrentTime(currentTime);*/
		
		PersonalSettlementVO queryInfo = new PersonalSettlementVO();
		// 医院实时结算情况（个人信息、就医结算、基金支付、费用明细）
		PersonalInfoVO personalInfo = new PersonalInfoVO();
		PersonalInfoVO personalInsuranceInfo = new PersonalInfoVO();
		FundPaymentVO fundInfo = new FundPaymentVO();
		MedicalTreatmentSettlementVO medicalSettlementInfo = new MedicalTreatmentSettlementVO();
		HospitalRealTimeSettlementVO realTimeSettlementInfo = new HospitalRealTimeSettlementVO();
		
		try {
			// 获取当前产生结算记录的相关个人信息
			queryInfo = monitorServiceImpl.getCurrentPersonalNo4Other(queryBean);
			if (queryInfo != null) {
				queryBean.setPersonalNo( queryInfo.getPersonalNo() );
				queryBean.setHospitalNo( queryInfo.getHospitalNo() );
				queryBean.setFixedInstitutionNo( queryInfo.getFixedInstitutionNo() );
			}
			
			// 个人参保 信息
			personalInsuranceInfo = monitorServiceImpl.getPersonalInsuranceInfo4Other(queryBean);
			
			// 个人信息
			personalInfo = monitorServiceImpl.getPersonalInfo4Other(queryBean);
			if (personalInfo != null) {
				// 本地就医的话就医地统一是“吉林市中心医院”
				if (LOCAL_AREA_FLAG.equals(queryBean.getAreaFlag())) {
					personalInfo.setMedicalTreatmentCity("吉林市中心医院");
				}
				// 设置当前结算人参保信息
				if (personalInsuranceInfo != null) {
					personalInfo.setInsurance(personalInsuranceInfo.getInsurance());
				}
				realTimeSettlementInfo.setPersonalInfo(personalInfo);
			}
			
			// 就医结算
			medicalSettlementInfo = monitorServiceImpl.getMedicalSettelmentInfo4Other(queryBean);
			if (medicalSettlementInfo != null) {
				// 计算基金比例                   基金比例 = （总费用-自费）/费用总额
				double fundPaymentFee = Double.parseDouble( medicalSettlementInfo.getFundPaymentFee() );
				double totalFee = Double.parseDouble( medicalSettlementInfo.getTotalFee() );
				// 这里使用 NumberUtil.decimalFormat()计算百分比
				String fundRatio = NumberUtil.decimalFormat("#.##%", fundPaymentFee/totalFee);
				medicalSettlementInfo.setFundRatio(fundRatio);
				realTimeSettlementInfo.setTreatmentSettlement(medicalSettlementInfo);
			}
			// 基金支付
			fundInfo = monitorServiceImpl.getFundPaymentInfo4Other(queryBean);
			if (fundInfo != null) {
				realTimeSettlementInfo.setFundPayment(fundInfo);
			}
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询医院实时结算情况异常：", e);
		}
		
		long endTime = DateUtil.currentSeconds();
		logger.info("========医院实时结算情况查询耗时：" + (endTime - startTime) + "s========");
		
		return result(result, message, realTimeSettlementInfo);
	}
	
	
	/**
	 * 医院实时结算情况（费用明细）
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getMedicalFeeDetailInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMedicalFeeDetailInfo(@RequestBody HospitalRealTimeSettlementQueryBean queryBean) {
		logger.info("---------------MonitorController: getMedicalFeeDetailInfo---------------");
    	logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean) + "---------------");
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		// 测试查询耗时
		long startTime = DateUtil.currentSeconds();;
		
		// 当前交易结算时间，注释掉便于前后台联调，后续部署上环境放开注释
		/*String currentTime = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN.replace("-", "/"));
		queryBean.setCurrentTime(currentTime);*/
		
		double totalCost = 0.0;
		// 当前结算人信息（个人编号、住院号、服务机构代码）
		PersonalSettlementVO queryInfo = new PersonalSettlementVO();
		
		MedicalFeeDetailVO detailInfo = new MedicalFeeDetailVO();
		List<MedicalFeeDetailVO> datas = new ArrayList<>();
		Page<MedicalFeeDetailVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryInfo = monitorServiceImpl.getCurrentPersonalNo4Other(queryBean);
			if (queryInfo != null) {
				queryBean.setPersonalNo( queryInfo.getPersonalNo() );
				queryBean.setHospitalNo( queryInfo.getHospitalNo() );
			}
			// 获取费用明细
			datas = monitorServiceImpl.getMedicalFeeDetailInfo4Other(queryBean);
			if (datas != null && !datas.isEmpty()) {
				// 统计医疗费用总额
				for (int index = 0; index < datas.size(); index++) {
					detailInfo = datas.get(index);
					totalCost += detailInfo.getAmount();
					detailInfo.setTotalCost(totalCost);
					datas.set(index, detailInfo);
				}
				page.setData(datas);
				// 费用明细总计
				page.setCount(datas.size());
			}
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询费用明细信息异常：", e);
		}
		
		long endTime = DateUtil.currentSeconds();
		logger.info("========费用明细查询耗时：" + (endTime - startTime) + "s========");
		
		return result(result, message, page);
	}
	
	
	/**
	 * 实时交易情况
	 * @param queryBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRealTimeTransactionInfo", method = RequestMethod.POST, produces = "application/json")
	public Result getRealTimeTransactionInfo(@RequestBody TransactionQueryBean queryBean) {
		
		logger.info("---------------MonitorController: getRealTimeTransactionInfo---------------");
    	logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean) + "---------------");
    	
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		long startTime = DateUtil.currentSeconds();
		
		TransactionVO data = new TransactionVO();
		List<TransactionVO> datas = new ArrayList<>();
		
		try {
			// 查询实时交易情况
			datas = monitorServiceImpl.getRealTimeTransaction4Other(queryBean);
			if (datas.size() != 0 && !datas.isEmpty()) {
				for (int index = 0; index < datas.size(); index++) {
					data = datas.get(index);
					// 业务类型统一设置成"出院结算"
					data.setServiceCategory("出院结算");
				}
			}
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询实时交易情况异常：", e);
		}
		
		long endTime = DateUtil.currentSeconds();
		logger.info("========实时交易情况查询耗时" + (endTime-startTime) + "s========");
		
		return result(result, message, datas);
	}
	
	
	
	//<#################################################  暂时不用的接口 start  #################################################>
	/**
	 * 医院实时结算情况（个人信息）
	 * @param queryBean
	 * @return
	 */
	/*@RequestMapping(value = "/getPersonalInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getPersonalInfo(@RequestBody HospitalRealTimeSettlementQueryBean queryBean) {
		logger.info("---------------MonitorController: getPersonalInfo---------------");
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		long startTime = DateUtil.currentSeconds();
		
		String currentTime = "2018/5/10 8:47:13";
		queryBean.setCurrentTime(currentTime);
		
		PersonalInfoVO personalInfo = new PersonalInfoVO();
		PersonalSettlementVO queryInfo = new PersonalSettlementVO();
		
		try {
			// 获取当前最新结算记录个人编号、住院号、服务机构代码
			queryInfo = monitorServiceImpl.getCurrentPersonalNo4Other(queryBean);
			if (queryInfo != null) {
				queryBean.setPersonalNo( queryInfo.getPersonalNo() );
				queryBean.setHospitalNo( queryInfo.getHospitalNo() );
				queryBean.setFixedInstitutionNo( queryInfo.getFixedInstitutionNo() );
			}
			// 获取当前个人信息
			personalInfo = monitorServiceImpl.getPersonalInfo4Other(queryBean);
			if (personalInfo != null) {
				result = Constants.RESULT_MESSAGE_SUCCESS;
				message = "查询成功";
			}
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人信息异常：", e);
		}
		
		long endTime = DateUtil.currentSeconds();
		logger.info("############ 查询耗时： " + (endTime - startTime) +" s ############");
		
		return result(result, message, personalInfo);	
	}*/
	
	
	/**
	 * 医院实时结算情况（医疗结算信息）
	 * @param queryBean
	 * @return
	 */
	/*@RequestMapping(value = "/getMedicalSettlementInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMedicalSettlementInfo(@RequestBody HospitalRealTimeSettlementQueryBean queryBean) {
		logger.info("---------------MonitorController: getMedicalSettlementInfo---------------");
    	logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean)+"---------------");
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		// 测试查询耗时
		long startTime = DateUtil.currentSeconds();
		
		PersonalSettlementVO queryInfo = new PersonalSettlementVO();
		MedicalTreatmentSettlementVO medicalSettlementInfo = new MedicalTreatmentSettlementVO();
		
		try {
			// 获取当前最新结算记录个人编号、住院号
			queryInfo = monitorServiceImpl.getCurrentPersonalNo4Other(queryBean);
			if (queryInfo != null) {
				queryBean.setHospitalNo( queryInfo.getHospitalNo() );
				queryBean.setPersonalNo( queryInfo.getPersonalNo() );
			}
			// 获取医疗结算信息
			medicalSettlementInfo = monitorServiceImpl.getMedicalSettelmentInfo4Other(queryBean);
			if (medicalSettlementInfo != null) {
				// 计算基金比例                   基金比例 = （总费用-自费）/费用总额
				double fundPaymentFee = Double.parseDouble( medicalSettlementInfo.getFundPaymentFee() );
				double totalFee = Double.parseDouble( medicalSettlementInfo.getTotalFee() );
				// 这里使用NumberUtil.decimalFormat()计算百分比
				String fundRatio = NumberUtil.decimalFormat("#.##%", fundPaymentFee/totalFee);
				medicalSettlementInfo.setFundRatio(fundRatio);
			}
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询医疗结算信息异常：", e);
		}
		
		long endTime = DateUtil.currentSeconds();;
		logger.info("############ 查询耗时： " + (endTime - startTime) +" s ############");
		
		return result(result, message, medicalSettlementInfo);	
	}*/
	
	
	/**
	 * 医院实时结算情况（基金支付）
	 * @param queryBean
	 * @return
	 */
	/*@RequestMapping(value = "/getFundPaymentInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getFunPaymentInfo(@RequestBody HospitalRealTimeSettlementQueryBean queryBean) {
		logger.info("---------------MonitorController: getFundPaymentInfo---------------");
    	logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean)+"---------------");
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		// 测试查询耗时
		long startTime = DateUtil.currentSeconds();;
		
		String currentTime = "2018/5/10 8:47:13";
		queryBean.setCurrentTime(currentTime);
		
		PersonalSettlementVO queryInfo = new PersonalSettlementVO();
		FundPaymentVO fundInfo = new FundPaymentVO();
		
		try {
			// 获取当前最新结算记录个人编号、住院号
			queryInfo = monitorServiceImpl.getCurrentPersonalNo4Other(queryBean);
			if (queryInfo != null) {
				queryBean.setHospitalNo( queryInfo.getHospitalNo() );
				queryBean.setPersonalNo( queryInfo.getPersonalNo() );
			}
			// 获取基金支付信息
			fundInfo = monitorServiceImpl.getFundPaymentInfo4Other(queryBean);
			if (fundInfo != null) {
				result = Constants.RESULT_MESSAGE_SUCCESS;
				message = "查询成功";
			}
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询基金支付信息异常：", e);
		}
		
		long endTime = DateUtil.currentSeconds();;
		logger.info("############ 查询耗时： " + (endTime - startTime) +" s ############");
		
		return result(result, message, fundInfo);	
	}*/
	
	//<#################################################  暂时不用的接口 end    #################################################>

}
