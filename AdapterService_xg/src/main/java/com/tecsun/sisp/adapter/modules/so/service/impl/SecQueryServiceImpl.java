package com.tecsun.sisp.adapter.modules.so.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.so.entity.request.PaymentQueryBean;
import com.tecsun.sisp.adapter.modules.so.entity.response.BlanceOfMIAccountVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.EIIssueBasicVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.EIIssueRecordVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.FiveInsuranceRecordVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.FiveInsuranceVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.MAIncureVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.MARecordVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.MIRecordVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.TsInsuranceVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.TsgrcbxxVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.URREIIssueBasicVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.URREIIssueRecordVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.URREIPayBasicVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.URREIPayRecord;
import com.tecsun.sisp.adapter.modules.so.entity.response.UserCBInfoVO;

@Service("secQueryService")
public class SecQueryServiceImpl extends BaseService {

	private static Logger logger = LoggerFactory.getLogger(SecQueryServiceImpl.class);

	public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.so.service.impl.SecQueryServiceImpl.";

	/*-------孝感五险查询  start------*/
	/**
	 * 个人基本参保信息
	 * 
	 * @param bean
	 * @return
	 */
	public List<TsInsuranceVO> getPersonInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm()
				+ "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPersonInfo", bean);
	}

	/**
	 * 参保信息险种
	 * 
	 * @param bean
	 * @return
	 */
	public List<TsgrcbxxVO> getIncureTypeList4Other(PaymentQueryBean bean) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPersonSureInfo", bean);
	}

	/**
	 * 五险：查询所有在参保个人编号
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<TsInsuranceVO> getPersonCount4Other(PaymentQueryBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPersonCount", bean);
	}

	/**
	 * 单位或个人缴费合计
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<TsInsuranceVO> getPaySum4Other(SecQueryBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPaySum", bean);
	}

	/**
	 * 五险：查询参保的险种
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<TsInsuranceVO> getPersonCvrgInfo4Other(SecQueryBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPersonCvrgInfo", bean);
	}

	/**
	 * 城镇退休人员待遇信息
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<TsInsuranceVO> getQuitPayList4Other(SecQueryBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getQuitPayList", bean);
	}

	/**
	 * 医疗明细帐户列表
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<TsInsuranceVO> getHealthAcountList4Other(SecQueryBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getHealthAcountList", bean);
	}

	/**
	 * 医疗保险缴费合计
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<TsInsuranceVO> getHealthIncureySum4Other(SecQueryBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getInsureSum", bean);
	}

	/**
	 * 当前医疗余额
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<TsInsuranceVO> getylAccountYe4Other(SecQueryBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getylAccountYe", bean);
	}

	/**
	 * 五险：各险种保险列表查询
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<TsInsuranceVO> getIncureyList4Other(PaymentQueryBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPersonInsure", bean);
	}

	// 城镇职工退休个人编码
	public List<TsInsuranceVO> getQuitPayCode4Other(SecQueryBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getQuitPayCode", bean);
	}

	/*-------孝感五险查询  end------*/

	/**
	 * 人员参保信息
	 * 
	 * @param bean
	 * @return
	 */
	public List<UserCBInfoVO> getUserCBInfo4other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm()
				+ "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getUserCBInfo", bean);
	}

	/**
	 * 医疗保险(MI)缴费记录基本信息
	 * 
	 * @param bean
	 * @return
	 */
	public FiveInsuranceVO getMIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm()
				+ "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getMIPaymentBasicInfo", bean);
	}

	/**
	 * 医疗保险(MI)缴费记录
	 * 
	 * @param bean
	 * @return
	 */
	public List<MIRecordVO> getMIPaymentRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm() + ",ksny:"
				+ bean.getKsny() + ",jsny:" + bean.getJsny() + "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getMIPaymentRecord", bean);
	}

	/**
	 * 医疗账户(MA)基本信息
	 * 
	 * @param bean
	 * @return
	 */
	public MAIncureVO getMABasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm()
				+ "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getMABasicInfo", bean);
	}

	/**
	 * 医疗账户(MA)明细
	 * 
	 * @param bean
	 * @return
	 */
	public List<MARecordVO> getMADetail4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm() + ",ksny:"
				+ bean.getKsny() + ",jsny:" + bean.getJsny() + "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getMADetail", bean);
	}

	/**
	 * 养老保险(EI)缴费基本信息
	 * 
	 * @param bean
	 * @return
	 */
	public FiveInsuranceVO getEIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm()
				+ "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getEIPaymentBasicInfo", bean);
	}

	/**
	 * 养老保险(EI)缴费记录
	 * 
	 * @param bean
	 * @return
	 */
	// public List<EIPayRecordVO> getEIPaymentRecord4Other(PaymentQueryBean
	// bean) {
	// logger.info("===================sfzh:"+bean.getSfzh() +
	// ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+
	// ",jsny:"+bean.getJsny()+"===================");
	//
	// //获取测试数据
	// return
	// this.getSqlSessionTemplate().selectList(NAMESPACE+"getEIPaymentRecord",bean);
	// }

	/**
	 * 养老保险(EI)待遇发放基本信息
	 * 
	 * @param bean
	 * @return
	 */
	public EIIssueBasicVO getEIIssueBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm()
				+ "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getEIIssueBasicInfo", bean);
	}

	/**
	 * 养老保险(EI)待遇发放记录
	 * 
	 * @param bean
	 * @return
	 */
	public List<EIIssueRecordVO> getEIIssueRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm() + ",ksny:"
				+ bean.getKsny() + ",jsny:" + bean.getJsny() + "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getEIIssueRecord", bean);
	}

	/**
	 * 生育保险(BI)缴费基本信息
	 * 
	 * @param bean
	 * @return
	 */
	public FiveInsuranceVO getBIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm()
				+ "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getBIPaymentBasicInfo", bean);
	}

	/**
	 * 生育保险(BI)缴费记录
	 * 
	 * @param bean
	 * @return
	 */
	public List<FiveInsuranceRecordVO> getBIPaymentRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm() + ",ksny:"
				+ bean.getKsny() + ",jsny:" + bean.getJsny() + "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getBIPaymentRecord", bean);
	}

	/**
	 * 工伤保险(EII)缴费基本信息
	 * 
	 * @param bean
	 * @return
	 */
	public FiveInsuranceVO getEIIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm()
				+ "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getEIIPaymentBasicInfo", bean);
	}

	/**
	 * 工伤保险(EII)缴纳记录
	 * 
	 * @param bean
	 * @return
	 */
	public List<FiveInsuranceRecordVO> getEIIPaymentRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm() + ",ksny:"
				+ bean.getKsny() + ",jsny:" + bean.getJsny() + "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getEIIPaymentRecord", bean);
	}

	/**
	 * 失业保险(UEI)缴纳基本信息
	 * 
	 * @param bean
	 * @return
	 */
	public FiveInsuranceVO getUEIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm()
				+ "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getUEIPaymentBasicInfo", bean);
	}

	/**
	 * 失业保险(UEI)缴纳记录
	 * 
	 * @param bean
	 * @return
	 */
	public List<FiveInsuranceRecordVO> getUEIPaymentRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm() + ",ksny:"
				+ bean.getKsny() + ",jsny:" + bean.getJsny() + "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getUEIPaymentRecord", bean);
	}

	/**
	 * 城乡居民养老保险(URREI)缴纳基本信息
	 * 
	 * @param bean
	 * @return
	 */
	public URREIPayBasicVO getURREIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm()
				+ "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getURREIPaymentBasicInfo", bean);
	}

	/**
	 * 城乡居民养老保险(URREI)缴费历史纪录
	 * 
	 * @param bean
	 * @return
	 */
	public List<URREIPayRecord> getURREIPaymentRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm() + ",ksny:"
				+ bean.getKsny() + ",jsny:" + bean.getJsny() + "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getURREIPaymentRecord", bean);
	}

	/**
	 * 城乡居民养老保险(URREI)待遇发放基本信息
	 * 
	 * @param bean
	 * @return
	 */
	public URREIIssueBasicVO getURREIIssueBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm()
				+ "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getURREIIssueBasicInfo", bean);
	}

	/**
	 * 城乡居民养老保险(URREI)待遇发放记录
	 * 
	 * @param bean
	 * @return
	 */
	public List<URREIIssueRecordVO> getURREIIssueRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm() + ",ksny:"
				+ bean.getKsny() + ",jsny:" + bean.getJsny() + "===================");

		// 获取测试数据
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getURREIIssueRecord", bean);
	}

	/**
	 * 医保账户余额查询
	 * 
	 * @param
	 * @return
	 */
	public BlanceOfMIAccountVO getBalanceOfMIAccount4Other(String sfzh, String xm) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sfzh", sfzh);
		map.put("xm", xm);
		// 获取测试数据
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getBalanceOfMIAccount", map);
	}
}
