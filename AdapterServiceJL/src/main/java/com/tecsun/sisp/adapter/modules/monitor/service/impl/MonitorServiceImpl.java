package com.tecsun.sisp.adapter.modules.monitor.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.HospitalQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.HospitalRealTimeSettlementQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.MedicalStatisticsQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.PharmacyQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.RankingQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.RealTimeTransactionQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.request.TransactionQueryBean;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.TodaySettlementFeeVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.TodayDischargeVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearDischargeVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.FundPaymentVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.HospitalSituationVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.MedicalFeeDetailVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.MedicalTreatmentSettlementVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.PersonalInfoVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.PersonalSettlementVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.PharmacySituationVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.RealTimeDischargeVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.RealTimeSettlementFeeVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.RealTimeSettlementVisitorsVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.TransactionVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearSettlementFeeVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearSettlementRankingVO;
import com.tecsun.sisp.adapter.modules.monitor.entity.response.CurrentYearSettlementVisitorsVO;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年7月13日 上午9:35:27
* 说明：医保监控
*/
@Service("monitorServiceImpl")
public class MonitorServiceImpl extends BaseService{
	
	private static Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);
	
	public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.monitor.service.impl.MonitorServiceImpl.";
	
	
	/**
	 * 当年就医统计---本地就医出院人次统计
	 * @param queryBean
	 * @return
	 */
	public List<CurrentYearDischargeVisitorsVO> getCurrentYearLocalDischargeVisitors4Other(MedicalStatisticsQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCurrentYearLocalDischargeVisitors", queryBean);
	}
	
	
	/**
	 * 当年就医统计---异地就医出院人次统计
	 * @param queryBean
	 * @return
	 */
	public List<CurrentYearDischargeVisitorsVO> getCurrentYearOffsiteDischargeVisitors4Other(MedicalStatisticsQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCurrentYearOffsiteDischargeVisitors", queryBean);
	}
	
	
	/**
	 * 当年就医统计---本地就医结算人次统计
	 * @param queryBean
	 * @return
	 */
	public List<CurrentYearSettlementVisitorsVO> getCurrentYearLoclaSettlementVisitors4Other(MedicalStatisticsQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCurrentYearLocalSettlementVisitors", queryBean);
	}
	
	
	/**
	 * 当年就医统计---异地就医结算人次统计
	 * @param queryBean
	 * @return
	 */
	public List<CurrentYearSettlementVisitorsVO> getCurrentYearOffsiteSettlementVisitors4Other(MedicalStatisticsQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCurrentYearOffsiteSettlementVisitors", queryBean);
	}
	
	
	/**
	 * 当年就医统计---本地就医结算费用统计
	 * @param queryBean
	 * @return
	 */
	public List<CurrentYearSettlementFeeVO> getCurrentYearLocalSettelmentFee4Other(MedicalStatisticsQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCurrentYearLocalSttlementFee", queryBean);
	}
	
	
	/**
	 * 当年就医统计---异地就医结算费用统计
	 * @param queryBean
	 * @return
	 */
	public List<CurrentYearSettlementFeeVO> getCurrentYearOffsiteSettelmentFee4Other(MedicalStatisticsQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCurrentYearOffsiteSttlementFee", queryBean);
	}
	
	
	/**
	 * 今日情况---本地药店情况（统计今日人次和消费）
	 * @param queryBean
	 * @return
	 */
	public PharmacySituationVO getTodayLocalPharmacySituation4Other(PharmacyQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getTodayLocalPharmacySituation", queryBean);
	}
	
	
	/**
	 * 今日情况---异地药店情况（统计今日人次和消费）
	 * @param queryBean
	 * @return
	 */
	public PharmacySituationVO getTodayOffsitePharmacySituation4Other(PharmacyQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getTodayOffsitePharmacySituation", queryBean);
	}
	
	
	/**
	 * 今日情况---本地医院情况（统计出院人次和消费）
	 * @param queryBean
	 * @return
	 */
	public HospitalSituationVO getTodayLocalHospitalSituation4Other(HospitalQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getTodayLocalHospitalSituation", queryBean);
	}
	
	
	/**
	 * 今日情况---异地医院情况（统计出院人次和消费）
	 * @param queryBean
	 * @return
	 */
	public HospitalSituationVO getTodayOffsiteHospitalSituation4Other(HospitalQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getTodayOffsiteHospitalSituation", queryBean);
	}
	
	
	/**
	 * 今日出院人次（只统计本地）
	 * @param queryBean
	 * @return
	 */
	public List<TodayDischargeVisitorsVO> getTodayDischargeVisitors4Other(HospitalQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getTodayDischargeVisitors", queryBean);
	}
	
	
	/**
	 * 今日出院结算费用（只统计本地）
	 * @param queryBean
	 * @return
	 */
	public List<TodaySettlementFeeVO> getTodaySettlementFee4Other(HospitalQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getTodaySettlementFee", queryBean);
	}
	
	
	/**
	 * 当年结算排名
	 * @param queryBean
	 * @return
	 */
	public List<CurrentYearSettlementRankingVO> getCurrentYearSettlementRanking4Other(RankingQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCurrentYearSettlementRanking", queryBean);
	}
	
	
	/**
	 * 查询当前时间产生结算记录的个人编号及住院号
	 * @param queryBean
	 * @return
	 */
	public PersonalSettlementVO getCurrentPersonalNo4Other(HospitalRealTimeSettlementQueryBean queryBean) {
		logger.info("=============currentDate:" + queryBean.getCurrentTime() + "=============");
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getCurrentPersonalNo", queryBean);
	}
	
	
	/**
	 * 查询当前结算人参保信息
	 * @param queryBean
	 * @return
	 */
	public PersonalInfoVO getPersonalInsuranceInfo4Other(HospitalRealTimeSettlementQueryBean queryBean) {
		logger.info("=============personalNo:" + queryBean.getPersonalNo() + "=============");
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getPersonalInsuranceInfo", queryBean);
	}
	
	
	/**
	 * 医院实时结算情况（个人信息）
	 * @param queryBean
	 * @return
	 */
	public PersonalInfoVO getPersonalInfo4Other(HospitalRealTimeSettlementQueryBean queryBean) {
		logger.info("=============personalNo:" + queryBean.getPersonalNo() + ", hospitalNo:" + queryBean.getHospitalNo() + 
				", fixedInstitutionNo:" + queryBean.getFixedInstitutionNo() + "=============");
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getPersonalInfo", queryBean);
	}
	
	
	/**
	 * 医院实时结算情况（就医结算）
	 * @param queryBean
	 * @return
	 */
	public MedicalTreatmentSettlementVO getMedicalSettelmentInfo4Other(HospitalRealTimeSettlementQueryBean queryBean) {
		logger.info("=============personalNo:" + queryBean.getPersonalNo() + ", hospitalNo:" + queryBean.getHospitalNo() + 
				", fixedInstitutionNo:" + queryBean.getFixedInstitutionNo() + "=============");
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getMedicalSettlementInfo", queryBean);
	}
	
	
	/**
	 * 医院实时结算情况（基金支付）
	 * @param queryBean
	 * @return
	 */
	public FundPaymentVO getFundPaymentInfo4Other(HospitalRealTimeSettlementQueryBean queryBean) {
		logger.info("=============personalNo:" + queryBean.getPersonalNo() + ", hospitalNo:" + queryBean.getHospitalNo() + 
				", fixedInstitutionNo:" + queryBean.getFixedInstitutionNo() + "=============");
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getFundPaymentInfo", queryBean);
	}
	
	
	/**
	 * 医院实时结算情况（费用明细）
	 * @param queryBean
	 * @return
	 */
	public List<MedicalFeeDetailVO> getMedicalFeeDetailInfo4Other(HospitalRealTimeSettlementQueryBean queryBean) {
		logger.info("=============personalNo:" + queryBean.getPersonalNo() + ", hospitalNo:" + queryBean.getHospitalNo() + "=============");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getMedicalFeeDetailInfo", queryBean);
	}
	
	
	/**
	 * 实时交易情况
	 * @param queryBean
	 * @return
	 */
	public List<TransactionVO> getRealTimeTransaction4Other(TransactionQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getRealTimeTransaction", queryBean);
	}
	
	
	/**
	 * 近24小时交易信息---本地就医出院人次统计
	 * @param queryBean
	 * @return
	 */
	public List<RealTimeDischargeVisitorsVO> getRealTimeLocalDischargeVisitors4Other(RealTimeTransactionQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getRealTimeLocalDischargeVisitors", queryBean);
	}
	
	
	/**
	 * 近24小时交易信息---异地就医出院人次统计
	 * @param queryBean
	 * @return
	 */
	public List<RealTimeDischargeVisitorsVO> getRealTimeOffsiteDischargeVisitors4Other(RealTimeTransactionQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getRealTimeOffsiteDischargeVisitors", queryBean);
	}
	
	
	/**
	 * 近24小时交易信息---本地就医结算人次统计
	 * @param queryBean
	 * @return
	 */
	public List<RealTimeSettlementVisitorsVO> getRealTimeLocalSettlementVisitors4Other(RealTimeTransactionQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getRealTimeLocalSettlementVisitors", queryBean);
	}
	
	
	/**
	 * 近24小时交易信息---异地就医结算人次统计
	 * @param queryBean
	 * @return
	 */
	public List<RealTimeSettlementVisitorsVO> getRealTimeOffsiteSettlementVisitors4Other(RealTimeTransactionQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getRealTimeOffsiteSettlementVisitors", queryBean);
	}
	
	
	/**
	 * 近24小时交易信息---本地就医结算费用统计
	 * @param queryBean
	 * @return
	 */
	public List<RealTimeSettlementFeeVO> getRealTimeLocalSettlementFee4Other(RealTimeTransactionQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getRealTimeLocalSettlementFee", queryBean);
	}
	
	
	/**
	 * 近24小时交易信息---异地就医结算费用统计
	 * @param queryBean
	 * @return
	 */
	public List<RealTimeSettlementFeeVO> getRealTimeOffsiteSettlementFee4Other(RealTimeTransactionQueryBean queryBean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getRealTimeOffsiteSettlementFee", queryBean);
	}
	

}