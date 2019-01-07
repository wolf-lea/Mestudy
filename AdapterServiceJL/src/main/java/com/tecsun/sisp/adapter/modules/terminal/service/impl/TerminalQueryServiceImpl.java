package com.tecsun.sisp.adapter.modules.terminal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.terminal.entity.request.DrugCatalogQueryBean;
import com.tecsun.sisp.adapter.modules.terminal.entity.request.InsuredCertificationQueryBean;
import com.tecsun.sisp.adapter.modules.terminal.entity.request.MedicalInstitutionQueryBean;
import com.tecsun.sisp.adapter.modules.terminal.entity.request.MedicalInsuranceQueryBean;
import com.tecsun.sisp.adapter.modules.terminal.entity.request.MedicalTreatmentQueryBean;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.DrugCatalogInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.InsuredCertificationInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.InsuredStatusInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.OccupationalInjuryHospitalVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.LocalMedicalInstitutionVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.MajorIllnessApprovalVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.MaternityMedicalSettlementVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.MedicalTreatmentProjectVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.OffsiteMedicalInstitutionVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalAccountInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalBlockadeInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalInsuranceInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalMedicalFeeInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalMedicalSettlementInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalPaymentInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.RemoteApprovalInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.ResidentPaymentInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.TransferApprovalInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.UninsuredCertificationInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PrintCertificationFrequencyVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.CompanyBlockadeInfoVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.CompanyInfoVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.CompanyInsuranceInfoVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.CompanyPaymentInfoVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.UnitPersonsVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.OccupationalInjuryMedicalSettlementVO;


@Service("TerminalQueryServiceImpl")
public class TerminalQueryServiceImpl extends BaseService {
	
	private static Logger logger = LoggerFactory.getLogger(TerminalQueryServiceImpl.class);
	
	public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.terminal.service.impl.TerminalQueryServiceImpl.";
	
	
	/**
	 * 个人基本信息
	 * @param queryBean
	 * @return
	 */
	public List<PersonalInfoVO> getPersonalInfo4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================idCard:" + queryBean.getIdCard() + ",name:" + queryBean.getName() 
		+ ",socialCardNo:" + queryBean.getSocialCardNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPersonalInfo", queryBean);
	}
	
	
	/**
	 * 个人缴费信息
	 * @param queryBean
	 * @return
	 */
	public List<PersonalPaymentInfoVO> getPersonalPaymentInfo4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================personalNo:" + queryBean.getPersonalNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPersonalPaymentInfo", queryBean);
	}
	
	
	/**
	 * 个人缴费信息
	 * @param queryBean
	 * @return
	 */
	public List<ResidentPaymentInfoVO> getResidentPaymentInfo4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================personalNo:" + queryBean.getPersonalNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getResidentPaymentInfo", queryBean);
	}
	
	
	/**
	 * 个人账户信息
	 * @param queryBean
	 * @return
	 */
	public PersonalAccountInfoVO getPersonalAccountInfo4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================personalNo:" + queryBean.getPersonalNo() + "===================");
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getPersonalAccountInfo", queryBean);
	}
	
	
	/**
	 * 个人封锁信息
	 * @param queryBean
	 * @return
	 */
	public List<PersonalBlockadeInfoVO> getPersonalBlockadeInfo4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================personalNo:" + queryBean.getPersonalNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPersonalBlockadeInfo", queryBean);
		
	}
	
	
	/**
	 * 个人参保信息
	 * @param queryBean
	 * @return
	 */
	public List<PersonalInsuranceInfoVO> getPersonalInsuranceInfo4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================personalNo:" + queryBean.getPersonalNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPersonalInsuranceInfo", queryBean);
	}
	
	
	/**
	 * 个人医疗费用列表信息
	 * @param queryBean
	 * @return
	 */
	public List<PersonalMedicalSettlementInfoVO> getPersonalMedicalFeeOverview4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================personalNo:" + queryBean.getPersonalNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPersonalMedicalFeeOverview", queryBean);
	}
	
	
	/**
	 * 个人医疗费用明细
	 * @param documnetNo 单据号
	 * @return
	 */
	public List<PersonalMedicalFeeInfoVO> getPersonalMedicalFeeDetail4Other(String documnetNo) {
		logger.info("===================documnetNo:" + documnetNo + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPersonalMeidcalFeeDetail", documnetNo);
	}
	
	
	/**
	 * 药品目录信息
	 * @param queryBean
	 * @return
	 */
	public List<DrugCatalogInfoVO> getDrugCatalogInfo4Other(DrugCatalogQueryBean queryBean) {
		logger.info("===================pinYinCode:" + queryBean.getDrugPinYinCode() + ",drugChineseName:" 
		+ queryBean.getDrugChineseName() + ",drugNo:" + queryBean.getDrugNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getDrugCatalogInfo", queryBean);
	}
	
	
	/**
	 * 诊疗项目信息
	 * @param queryBean
	 * @return
	 */
	public List<MedicalTreatmentProjectVO> getMedicalTreatmentProjectInfo4Other(MedicalTreatmentQueryBean queryBean) {
		logger.info("===================projectNo:" + queryBean.getProjectNo() + ",pinYinCode:" 
				+ queryBean.getPinYinCode() + ",projectChineseName:" + queryBean.getProjectChineseName() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getMedicalTreatmentProjectInfo", queryBean);
	}
	
	
	/**
	 * 查询所有省份名称
	 * @return
	 */
	public List<OffsiteMedicalInstitutionVO> getAllProvinceInfo4Other() {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getAllProvinceInfo");
	}
	
	
	/**
	 * 查询省份下面的市
	 * @param queryBean
	 * @return
	 */
	public List<OffsiteMedicalInstitutionVO> getCityInfo4Other(MedicalInstitutionQueryBean queryBean) {
		logger.info("===================provinceName:" + queryBean.getProvinceName() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCityInfo", queryBean);
	}
	
	
	/**
	 * 省内定点医疗机构
	 * @param queryBean
	 * @return
	 */
	public List<LocalMedicalInstitutionVO> getLocalMedicalInstitutionInfo4Other(MedicalInstitutionQueryBean queryBean) {
		logger.info("===================areaFlag:" + queryBean.getAreaFlag() + ",medicalInstitutionNo:" + queryBean.getMedicalInstitutionNo() 
					+ ",medicalInstitutionName:" + queryBean.getMedicalInstitutionName() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getLocalMedicalInstitutionInfo", queryBean);
	}
	
	
	/**
	 * 省外定点医疗机构
	 * @param queryBean
	 * @return
	 */
	public List<OffsiteMedicalInstitutionVO> getOffsiteMedicalInstitutionInfo4Other(MedicalInstitutionQueryBean queryBean) {
		logger.info("===================areaFlag:" + queryBean.getAreaFlag() + ",provinceName:" + queryBean.getProvinceName() 
					+ ",medicalInstitutionName:" + queryBean.getMedicalInstitutionName() +"===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getOffsiteMedicalInstitutionInfo", queryBean);
	}
	
	
	/**
	 * 工伤医疗结算
	 * @param queryBean
	 * @return
	 */
	public List<OccupationalInjuryMedicalSettlementVO> getOccupationalInjuryMedicalSettlementInfo4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================personalNo:" + queryBean.getPersonalNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getOccupationalInjuryMedicalSettlementInfo", queryBean);
	}
	
	
	/**
	 * 生育医疗结算
	 * @param queryBean
	 * @return
	 */
	public List<MaternityMedicalSettlementVO> getMaternityMedicalSettlementInfo4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================personalNo:" + queryBean.getPersonalNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getMaternityMedicalSettlementInfo", queryBean);
	}
	
	
	/**
	 * 大病慢病审批
	 * @param queryBean
	 * @return
	 */
	public List<MajorIllnessApprovalVO> getMajorIllnessApprovalInfo4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================personalNo:" + queryBean.getPersonalNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getMajorIllnessApprovalInfo", queryBean);
	}
	
	
	/**
	 * 异地审批
	 * @param queryBean
	 * @return
	 */
	public List<RemoteApprovalInfoVO> getRemoteApprovalInfo4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================personalNo:" + queryBean.getPersonalNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getRemoteApprovalInfo", queryBean);	
	}
	
	
	/**
	 * 转外审批
	 * @param queryBean
	 * @return
	 */
	public List<TransferApprovalInfoVO> getTransferApprovalInfo4Other(MedicalInsuranceQueryBean queryBean) {
		logger.info("===================personalNo:" + queryBean.getPersonalNo() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getTransferApprovalInfo", queryBean);	
	}
	
	
	/**
	 * 单位基本信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public CompanyInfoVo getCompanyInfo4Other(MedicalInsuranceQueryBean bean)throws Exception{
		logger.info("=======companyNo" + bean.getCompanyNo());
		CompanyInfoVo uivo = getSqlSessionTemplate().selectOne(NAMESPACE + "getCompanyInfo", bean);
		return uivo;
	}
	
	
	/**
	 * 单位参保信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<CompanyInsuranceInfoVo> getCompanyInsuranceInfo4Other(MedicalInsuranceQueryBean bean) throws Exception{
		return getSqlSessionTemplate().selectList(NAMESPACE + "getCompanyInsuranceInfo", bean);
	}
	
	
	/**
	 * 单位人员信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public Page<UnitPersonsVo> queryUnitPersons4other(MedicalInsuranceQueryBean bean) throws Exception{
		Page<UnitPersonsVo> page = new Page<>(bean.getPageno(),bean.getPagesize());
		bean.setPage(page);
		List<UnitPersonsVo> list = getSqlSessionTemplate().selectList(NAMESPACE + "queryUnitPersons", bean);
		if(list !=null && list.size()>0){
			page.setData(list);
		}
		return page;
	}
	
	
	/**
	 * 单位缴费信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public Page<CompanyPaymentInfoVo> getCompanyPaymentInfo4Other(MedicalInsuranceQueryBean bean) throws Exception{
		Page<CompanyPaymentInfoVo> page = new Page<>(bean.getPageno(),bean.getPagesize());
		bean.setPage(page);
		List<CompanyPaymentInfoVo> list = getSqlSessionTemplate().selectList(NAMESPACE + "getCompanyPaymentInfo", bean);
		if(list != null && list.size()>0)
			page.setData(list);
		return page;
	}
	
	
	/**
	 * 单位封锁信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public Page<CompanyBlockadeInfoVo> getCompanyBlockadeInfo4Other(MedicalInsuranceQueryBean bean) throws Exception{
		Page<CompanyBlockadeInfoVo> page = new Page<>(bean.getPageno(),bean.getPagesize());
		bean.setPage(page);
		List<CompanyBlockadeInfoVo> list = getSqlSessionTemplate().selectList(NAMESPACE + "getCompanyBlockadeInfo", bean);
		if(list != null && list.size()>0)
			page.setData(list);
		return page;
	}
	
	
	/**
	 * 工伤定点医院
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public Page<OccupationalInjuryHospitalVo> getOccupationalInjuryHospitalInfo4Other(MedicalInsuranceQueryBean bean) throws Exception{
		Page<OccupationalInjuryHospitalVo> page = new Page<>(bean.getPageno(),bean.getPagesize());
		bean.setPage(page);
		List<OccupationalInjuryHospitalVo> list = getSqlSessionTemplate().selectList(NAMESPACE + "getOccupationalInjuryHospitalInfo", bean);
		if(list != null && list.size()>0)
			page.setData(list);
		return page;
	}
	
	
	/**
	 * 查询当前参保人参保状态
	 * @param queryBean
	 * @return
	 */
	public List<InsuredStatusInfoVO> getInsuredStatusInfo4Other(InsuredCertificationQueryBean queryBean) {
		logger.info("===================idCard:" + queryBean.getIdCard() + ",personnelStatus:" + queryBean.getPersonnelStatus() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getInsuredStatusInfo", queryBean);
	}
	
	
	/**
	 * 参保人参保记录列表
	 * @param queryBean
	 * @return
	 */
	public List<InsuredCertificationInfoVO> getInsuredCertificationOverview4Other(InsuredCertificationQueryBean queryBean) {
		logger.info("===================idCard:" + queryBean.getIdCard() + ",personeelStatus:" + queryBean.getPersonnelStatus() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getInsuredCertificationOverview", queryBean);
	}
	
	
	/**
	 * 参保状态为'正常参保'参保证明详情
	 * @param queryBean
	 * @return
	 */
	public InsuredCertificationInfoVO getInsuredCertificationDetail4Other(InsuredCertificationQueryBean queryBean) {
		logger.info("===================idCard:" + queryBean.getIdCard() + ",personeelStatus:" + queryBean.getPersonnelStatus() 
		+ ",medicalInsuranceNo:" + queryBean.getMedicalInsuranceNo() + ",insuredStatus:" + queryBean.getInsuredStatus() + "===================");
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getInsuredCertificationDetail", queryBean);
	}
	
	
	/**
	 * 参保状态为'未参保'参保证明详情
	 * @param queryBean
	 * @return
	 */
	public UninsuredCertificationInfoVO getUninsuredCertificationDetail4Other(InsuredCertificationQueryBean queryBean) {
		logger.info("===================idCard:" + queryBean.getIdCard() + ",personeelStatus:" + queryBean.getPersonnelStatus() 
		+ ",medicalInsuranceNo:" + queryBean.getMedicalInsuranceNo() + ",insuredStatus:" + queryBean.getInsuredStatus() + "===================");
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getUninsuredCertificationDetail", queryBean);
	}
	
	
	/**
	 * 查询当月打印参保证明次数，当月打印参保次数超过三次就不能再次打印
	 * @return
	 */
	/*public List<CertificationPrintFrequencyVO> getPrintFrequency4Cssp(InsuredCertificationQueryBean queryBean) {
		logger.info("===================idCard:" + queryBean.getIdCard() + ",medicalInsuranceNo:" + queryBean.getMedicalInsuranceNo() 
		+ ",printTime:" + queryBean.getPrintTime() + "===================");
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPrintFrequency", queryBean);
	}*/
	
	
	/**
	 * 保存当查询人打印参保记录次数
	 * @param queryBean
	 * @return
	 */
	/*@Transactional("cssp")
	public int savePrintFrequency4Cssp(InsuredCertificationQueryBean queryBean) {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "savePrintFrequency", queryBean);
	}*/
	
	
	/**
	 * 更新打印参保证明次数记录
	 * @param queryBean
	 * @return
	 */
	/*@Transactional("cssp")
	public int updatePrintFrequency4Cssp(InsuredCertificationQueryBean queryBean) {
		return this.getSqlSessionTemplate().update(NAMESPACE + "updatePrintFrequency", queryBean);
	}*/
	
	
	/**
	 * 查询当前参保人当月打印参保证明次数，如果当月超过三次，就限制不能再次打印参保证明。
	 * @param queryBean
	 * @return
	 */
	@Transactional("cssp")
	public int insertOrUpdateFrequency4Cssp(InsuredCertificationQueryBean queryBean) {
		
	
		if ("FALSE".equals(queryBean.getPrintFlag())) {
			PrintCertificationFrequencyVO printRecord = new PrintCertificationFrequencyVO();
			printRecord = this.getSqlSessionTemplate().selectOne(NAMESPACE + "getPrintFrequency", queryBean);
			if (printRecord != null) {
				int frequency = Integer.valueOf(printRecord.getPrintFrequency());
				if (frequency >= 3) {
					return frequency;
				} 
			} else {
				return 0;
			}
		}
		
		
		if ("TRUE".equals(queryBean.getPrintFlag())) {
			PrintCertificationFrequencyVO pritnRecord = new PrintCertificationFrequencyVO();
			pritnRecord = this.getSqlSessionTemplate().selectOne(NAMESPACE + "getPrintFrequency", queryBean);
			if (pritnRecord != null) {
				Integer printFrequency = Integer.valueOf(pritnRecord.getPrintFrequency());
				if (printFrequency >= 3) {
					return printFrequency;
				} else {
					printFrequency ++;
					queryBean.setPrintFrequency(printFrequency.toString());
					return this.getSqlSessionTemplate().insert(NAMESPACE + "updatePrintFrequency", queryBean);
				}
			}
			int defaultFrequency = 1;
			queryBean.setPrintFrequency(String.valueOf(defaultFrequency));
			return this.getSqlSessionTemplate().insert(NAMESPACE + "savePrintFrequency", queryBean);
		}
		return 0;
	}
	

}
