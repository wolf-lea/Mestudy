package com.tecsun.sisp.adapter.modules.terminal.entity.response;

/**
 * 
 * @Title: 单位参保信息出参
 * @company :TECSUN
 * @developer: ZENGYUNHUA
 *	@date 2018年8月23日 下午3:03:00
 *	@version 1.0
 */
public class CompanyInsuranceInfoVo {			

	private String companyNo;		//单位编号
	
	private String insuranceType;	//险种类型
	
	private  String insuredDate;	//参保日期
	
	private String insuredStatus;	//参保状态
	
	private String handleDate;		//经办日期
	
	private String overallPlanningArea;//统筹区

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
	 * @return the insuredDate
	 */
	public String getInsuredDate() {
		return insuredDate;
	}

	/**
	 * @param insuredDate the insuredDate to set
	 */
	public void setInsuredDate(String insuredDate) {
		this.insuredDate = insuredDate;
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
	 * @return the handleDate
	 */
	public String getHandleDate() {
		return handleDate;
	}

	/**
	 * @param handleDate the handleDate to set
	 */
	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}

	/**
	 * @return the overallPlanningArea
	 */
	public String getOverallPlanningArea() {
		return overallPlanningArea;
	}

	/**
	 * @param overallPlanningArea the overallPlanningArea to set
	 */
	public void setOverallPlanningArea(String overallPlanningArea) {
		this.overallPlanningArea = overallPlanningArea;
	}
	
	
	

}
