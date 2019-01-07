package com.tecsun.sisp.adapter.modules.terminal.entity.response;

/**
 * 
 * @Title: 工伤定点医院出参
 * @company :TECSUN
 * @developer: ZENGYUNHUA
 *	@date 2018年8月24日 下午3:05:37
 *	@version 1.0
 */
public class OccupationalInjuryHospitalVo {
	
	private String personalNo;		//个人编号
	
	private String auditType;		//审批类别
	
	private String startTime;		// 开始时间
	
	private String endTime;			// 终止时间
	
	private String auditDesignatedHospital;//审批定点医院
	
	private String auditFlag;		//审批标志
	
	private String operationDate;	//经办日期

	/**
	 * @return the personalNo
	 */
	public String getPersonalNo() {
		return personalNo;
	}

	/**
	 * @param personalNo the personalNo to set
	 */
	public void setPersonalNo(String personalNo) {
		this.personalNo = personalNo;
	}

	/**
	 * @return the auditType
	 */
	public String getAuditType() {
		return auditType;
	}

	/**
	 * @param auditType the auditType to set
	 */
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the auditDesignatedHospital
	 */
	public String getAuditDesignatedHospital() {
		return auditDesignatedHospital;
	}

	/**
	 * @param auditDesignatedHospital the auditDesignatedHospital to set
	 */
	public void setAuditDesignatedHospital(String auditDesignatedHospital) {
		this.auditDesignatedHospital = auditDesignatedHospital;
	}

	/**
	 * @return the auditFlag
	 */
	public String getAuditFlag() {
		return auditFlag;
	}

	/**
	 * @param auditFlag the auditFlag to set
	 */
	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
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
	
	 


}
