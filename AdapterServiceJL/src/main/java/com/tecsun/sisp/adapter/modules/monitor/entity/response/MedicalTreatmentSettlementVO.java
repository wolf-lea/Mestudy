package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/*
 * 医院实时结算情况---就医结算
 */
public class MedicalTreatmentSettlementVO {
	private String admissionDate;		// 入院日期
	
	private String dischargeDate;		// 出院日期
	
    private String hospitalDays;		// 住院天数
    
	private String dischargeDiagnosis;	// 出院诊断
	
	private String totalFee;			// 费用总额
	
	private String fundPaymentFee;		// 基金支付
	
	private String fundRatio;			// 基金比例
	
	private String personalPayment;		// 个人支付
	
	private String coordinationScope;	// 统筹范围内
	
	private String payFirst;			// 乙类先自付
	
	private String overLimitPrice;		// 超限价自付
	
	private String personalCost;		// 个人自费

	/**
	 * @return the admissionDate
	 */
	public String getAdmissionDate() {
		return admissionDate;
	}

	/**
	 * @param admissionDate the admissionDate to set
	 */
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	/**
	 * @return the dischargeDate
	 */
	public String getDischargeDate() {
		return dischargeDate;
	}

	/**
	 * @param dischargeDate the dischargeDate to set
	 */
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	/**
	 * @return the hospitalDays
	 */
	public String getHospitalDays() {
		return hospitalDays;
	}

	/**
	 * @param hospitalDays the hospitalDays to set
	 */
	public void setHospitalDays(String hospitalDays) {
		this.hospitalDays = hospitalDays;
	}

	/**
	 * @return the dischargeDiagnosis
	 */
	public String getDischargeDiagnosis() {
		return dischargeDiagnosis;
	}

	/**
	 * @param dischargeDiagnosis the dischargeDiagnosis to set
	 */
	public void setDischargeDiagnosis(String dischargeDiagnosis) {
		this.dischargeDiagnosis = dischargeDiagnosis;
	}

	/**
	 * @return the totalFee
	 */
	public String getTotalFee() {
		return totalFee;
	}

	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * @return the fundPaymentFee
	 */
	public String getFundPaymentFee() {
		return fundPaymentFee;
	}

	/**
	 * @param fundPaymentFee the fundPaymentFee to set
	 */
	public void setFundPaymentFee(String fundPaymentFee) {
		this.fundPaymentFee = fundPaymentFee;
	}

	/**
	 * @return the fundRatio
	 */
	public String getFundRatio() {
		return fundRatio;
	}

	/**
	 * @param fundRatio the fundRatio to set
	 */
	public void setFundRatio(String fundRatio) {
		this.fundRatio = fundRatio;
	}

	/**
	 * @return the personalPayment
	 */
	public String getPersonalPayment() {
		return personalPayment;
	}

	/**
	 * @param personalPayment the personalPayment to set
	 */
	public void setPersonalPayment(String personalPayment) {
		this.personalPayment = personalPayment;
	}

	/**
	 * @return the coordinationScope
	 */
	public String getCoordinationScope() {
		return coordinationScope;
	}

	/**
	 * @param coordinationScope the coordinationScope to set
	 */
	public void setCoordinationScope(String coordinationScope) {
		this.coordinationScope = coordinationScope;
	}

	/**
	 * @return the payFirst
	 */
	public String getPayFirst() {
		return payFirst;
	}

	/**
	 * @param payFirst the payFirst to set
	 */
	public void setPayFirst(String payFirst) {
		this.payFirst = payFirst;
	}

	/**
	 * @return the overLimitPrice
	 */
	public String getOverLimitPrice() {
		return overLimitPrice;
	}

	/**
	 * @param overLimitPrice the overLimitPrice to set
	 */
	public void setOverLimitPrice(String overLimitPrice) {
		this.overLimitPrice = overLimitPrice;
	}

	/**
	 * @return the personalCost
	 */
	public String getPersonalCost() {
		return personalCost;
	}

	/**
	 * @param personalCost the personalCost to set
	 */
	public void setPersonalCost(String personalCost) {
		this.personalCost = personalCost;
	}
	
	
	
	
}
