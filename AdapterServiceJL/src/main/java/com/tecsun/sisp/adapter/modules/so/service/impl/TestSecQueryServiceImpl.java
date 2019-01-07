package com.tecsun.sisp.adapter.modules.so.service.impl;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.so.entity.request.DrugCatalogQueryBean;
import com.tecsun.sisp.adapter.modules.so.entity.request.HospitalQueryBean;
import com.tecsun.sisp.adapter.modules.so.entity.request.PaymentQueryBean;
import com.tecsun.sisp.adapter.modules.so.entity.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("TestSecQueryServiceImpl")
public class TestSecQueryServiceImpl extends BaseService{

	private static Logger logger = LoggerFactory.getLogger(TestSecQueryServiceImpl.class);
		
	public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.so.service.impl.TestSecQueryServiceImpl.";

	/**
	 * 个人基本参保信息
	 * @param bean
	 * @return
	 */
	public List<InsuranceVO> getBasicInsuredInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        
        //获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getBasicInsuredInfo",bean);
	}

	/**
	 * 参保信息险种
	 * @param bean
	 * @return
	 */
	public List<IncureTypeVO> getIncureTypeList4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getIncureTypeList", bean);
	}

	/**
	 * 人员参保信息
	 * @param bean
	 * @return
	 */
	public List<UserCBInfoVO> getUserCBInfo4other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE+"getUserCBInfo",bean);
	}
	
	/**
	 * 医疗保险(MI)缴费记录基本信息
	 * @param bean
	 * @return
	 */
	public FiveInsuranceVO getMIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getMIPaymentBasicInfo",bean);
	}

	/**
	 * 医疗保险(MI)缴费记录
	 * @param bean
	 * @return
	 */
	public List<MIRecordVO> getMIPaymentRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getMIPaymentRecord",bean);
	}
	
	/**
	 * 医疗账户(MA)基本信息
	 * @param bean
	 * @return
	 */
	public MAIncureVO getMABasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getMABasicInfo",bean);
	}

	/**
	 * 医疗账户(MA)明细
	 * @param bean
	 * @return
	 */
	public List<MARecordVO> getMADetail4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getMADetail",bean);
	}
	
	/**
	 * 个人医疗费用信息
	 * @param bean
	 * @return
	 */
	public List<MACostVO> getMACost4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getMACost",bean);
	}
	
	/**
	 * 养老保险(EI)缴费基本信息
	 * @param bean
	 * @return
	 */
	public FiveInsuranceVO getEIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getEIPaymentBasicInfo",bean);
	}

	/**
	 * 养老保险(EI)缴费记录
	 * @param bean
	 * @return
	 */
	public List<EIPayRecordVO> getEIPaymentRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getEIPaymentRecord",bean);
	}
	
	/**
	 * 养老保险(EI)待遇发放基本信息
	 * @param bean
	 * @return
	 */
	public EIIssueBasicVO getEIIssueBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getEIIssueBasicInfo",bean);
	}

	/**
	 * 养老保险(EI)待遇发放记录
	 * @param bean
	 * @return
	 */
	public List<EIIssueRecordVO> getEIIssueRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getEIIssueRecord",bean);
	}
	
	/**
	 * 生育保险(BI)缴费基本信息
	 * @param bean
	 * @return
	 */
	public FiveInsuranceVO getBIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getBIPaymentBasicInfo",bean);
	}

	/**
	 * 生育保险(BI)缴费记录
	 * @param bean
	 * @return
	 */
	public List<MIRecordVO> getBIPaymentRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getBIPaymentRecord",bean);
	}
	
	/**
	 * 工伤保险(EII)缴费基本信息
	 * @param bean
	 * @return
	 */
	public FiveInsuranceVO getEIIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getEIIPaymentBasicInfo",bean);
	}

	/**
	 * 工伤保险(EII)缴纳记录
	 * @param bean
	 * @return
	 */
	public List<MIRecordVO> getEIIPaymentRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getEIIPaymentRecord",bean);
	}
	
	/**
	 * 工伤医疗结算信息
	 * @param bean
	 * @return
	 */
	public List<EIISettlementVO> getEIISettlementInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getEIISettlementInfo",bean);
	}
	
	
	/**
	 * 失业保险(UEI)缴纳基本信息
	 * @param bean
	 * @return
	 */
	public FiveInsuranceVO getUEIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getUEIPaymentBasicInfo",bean);
	}

	/**
	 * 失业保险(UEI)缴纳记录
	 * @param bean
	 * @return
	 */
	public List<FiveInsuranceRecordVO> getUEIPaymentRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getUEIPaymentRecord",bean);
	}
	
	/**
	 * 城乡居民养老保险(URREI)缴纳基本信息
	 * @param bean
	 * @return
	 */
	public URREIPayBasicVO getURREIPaymentBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getURREIPaymentBasicInfo",bean);
	}

	/**
	 * 城乡居民养老保险(URREI)缴费历史纪录
	 * @param bean
	 * @return
	 */
	public List<URREIPayRecord> getURREIPaymentRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getURREIPaymentRecord",bean);
	}
	
	/**
	 * 城乡居民养老保险(URREI)待遇发放基本信息
	 * @param bean
	 * @return
	 */
	public URREIIssueBasicVO getURREIIssueBasicInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getURREIIssueBasicInfo",bean);
	}

	/**
	 * 城乡居民养老保险(URREI)待遇发放记录
	 * @param bean
	 * @return
	 */
	public List<URREIIssueRecordVO> getURREIIssueRecord4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");
        
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getURREIIssueRecord",bean);
	}


    /**
     * 医保账户余额查询
     * @param
     * @return
     */
    public BlanceOfMIAccountVO getBalanceOfMIAccount4Other(String sfzh,String xm) {
        Map map = new HashMap();
        map.put("sfzh",sfzh);
        map.put("xm",xm);
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getBalanceOfMIAccount",map);
    }
    
    
    /**
	 * 生育待遇发放信息
	 * @param bean
	 * @return
	 */
	public List<BITreatmentVO> getBITreatmentInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");      
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getBITreatmentInfo",bean);
	}
	
	/**
	 * 生育医疗结算信息
	 * @param bean
	 * @return
	 */
	public List<BISettlementVO> getBISettlementInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");      
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getBISettlementInfo",bean);
	}
	
	/**
	 * 城镇居民缴费信息
	 * @param bean
	 * @return
	 */
	public List<TRPaymentVO> getTRPaymentInfo4Other(PaymentQueryBean bean) {
		logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+ ",ksny:"+bean.getKsny()+ ",jsny:"+bean.getJsny()+"===================");      
		//获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getTRPaymentInfo",bean);
	}
	
	/**
	 * 定点医疗机构信息
	 * @param bean
	 * @return
	 */
	public List<DesignatedHospitalVO> getDesignatedHospitals4Other(HospitalQueryBean bean) {
		logger.info("===================jgmc:" + bean.getJgmc() + "===================");
		// 获取测试数据
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getDesignatedHospitals", bean);
	}
	
	/**
	 * 三级甲等医疗机构信息
	 */
	public List<DesignatedHospitalVO> getThirdClassDesignatedHospitals4Other(PaymentQueryBean bean) {
		// 获取测试数据
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getThirdClassDesignatedHospitals",bean);
	}
	
	/**
	 * 药品目录信息
	 * @param bean
	 * @return
	 */
	public List<DrugCatalogInformationVO> getDrugCatalogInformations4Other(DrugCatalogQueryBean bean) {
		logger.info("===================ypbm:" + bean.getYpbm() + ",zwmc:"+bean.getZwmc() + "==================="); 
		// 获取测试数据
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getDrugCatalogInformations", bean);
	}
}
