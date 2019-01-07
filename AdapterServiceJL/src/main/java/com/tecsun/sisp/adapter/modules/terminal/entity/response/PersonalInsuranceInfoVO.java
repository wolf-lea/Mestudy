package com.tecsun.sisp.adapter.modules.terminal.entity.response;
/**
 *	个人参保信息 
 */
public class PersonalInsuranceInfoVO {
	
	private String persoanlNo;		// 个人编号
	
	private String companyNo;		// 单位编号
	
	private String insuranceType;	// 险种
	
	private String insuredStatus;	// 参保状态
	
	private String startDate;		// 开始年月
	
	private String endDate;			// 终止年月
	
	private String operationDate;	// 经办日期
	
	private String monthOrYear;//缴费月数或年数

	/**
	 * @return the persoanlNo
	 */
	public String getPersoanlNo() {
		return persoanlNo;
	}

	/**
	 * @param persoanlNo the persoanlNo to set
	 */
	public void setPersoanlNo(String persoanlNo) {
		this.persoanlNo = persoanlNo;
	}

	/**
	 * @return the companyNo
	 */
	public String getCompanyNo() {
		return companyNo;
	}

	/**
	 * @param companyNo the companyNo to set
	 */
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	/**
	 * @return the insuranceType
	 */
	public String getInsuranceType() {
		return insuranceType;
	}

	/**
	 * @param insuranceType the insuranceType to set
	 */
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	/**
	 * @return the insuredStatus
	 */
	public String getInsuredStatus() {
		return insuredStatus;
	}

	/**
	 * @param insuredStatus the insuredStatus to set
	 */
	public void setInsuredStatus(String insuredStatus) {
		this.insuredStatus = insuredStatus;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the operationDate
	 */
	public String getOperationDate() {
		return operationDate;
	}

	/**
	 * @param operationDate the operationDate to set
	 */
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	public String getMonthOrYear() {
		return monthOrYear;
	}

	public void setMonthOrYear(String monthOrYear) {
		this.monthOrYear = monthOrYear;
	}
	
	
	
	
	
}