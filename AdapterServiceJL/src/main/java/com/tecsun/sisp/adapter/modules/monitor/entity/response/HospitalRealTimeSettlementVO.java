package com.tecsun.sisp.adapter.modules.monitor.entity.response;


import com.tecsun.sisp.adapter.common.util.Page;

/*
 * 医院实时结算情况
 */
public class HospitalRealTimeSettlementVO {
	
	 private FundPaymentVO fundPayment;			// 基金支付信息
	 
	 private PersonalInfoVO personalInfo;		// 个人信息
	 
	 private MedicalTreatmentSettlementVO treatmentSettlement;	// 就医结算信息

	/**
	 * @return the fundPayment
	 */
	public FundPaymentVO getFundPayment() {
		return fundPayment;
	}

	/**
	 * @param fundPayment the fundPayment to set
	 */
	public void setFundPayment(FundPaymentVO fundPayment) {
		this.fundPayment = fundPayment;
	}

	/**
	 * @return the personalInfo
	 */
	public PersonalInfoVO getPersonalInfo() {
		return personalInfo;
	}

	/**
	 * @param personalInfo the personalInfo to set
	 */
	public void setPersonalInfo(PersonalInfoVO personalInfo) {
		this.personalInfo = personalInfo;
	}

	/**
	 * @return the treatmentSettlement
	 */
	public MedicalTreatmentSettlementVO getTreatmentSettlement() {
		return treatmentSettlement;
	}

	/**
	 * @param treatmentSettlement the treatmentSettlement to set
	 */
	public void setTreatmentSettlement(MedicalTreatmentSettlementVO treatmentSettlement) {
		this.treatmentSettlement = treatmentSettlement;
	}

    
}   