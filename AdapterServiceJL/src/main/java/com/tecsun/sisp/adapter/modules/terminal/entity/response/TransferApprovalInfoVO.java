package com.tecsun.sisp.adapter.modules.terminal.entity.response;
/**
 *	转外审批
 */
public class TransferApprovalInfoVO {
	
	private String personalNo;			// 个人编号 
	
	private String approvalCategory;	// 审批类别 
	
	private String approvalDiseaseName;	// 审批疾病名称
	
	private String startTime;			// 开始时间
	
	private String approvalDesignatedHospital;// 审批定点医院
	
	private String transferDesignatedHospital;// 转外定点医院
	
	private String approvalFlag;		// 审批标志

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
	 * @return the approvalCategory
	 */
	public String getApprovalCategory() {
		return approvalCategory;
	}

	/**
	 * @param approvalCategory the approvalCategory to set
	 */
	public void setApprovalCategory(String approvalCategory) {
		this.approvalCategory = approvalCategory;
	}

	/**
	 * @return the approvalDiseaseName
	 */
	public String getApprovalDiseaseName() {
		return approvalDiseaseName;
	}

	/**
	 * @param approvalDiseaseName the approvalDiseaseName to set
	 */
	public void setApprovalDiseaseName(String approvalDiseaseName) {
		this.approvalDiseaseName = approvalDiseaseName;
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
	 * @return the approvalDesignatedHospital
	 */
	public String getApprovalDesignatedHospital() {
		return approvalDesignatedHospital;
	}

	/**
	 * @param approvalDesignatedHospital the approvalDesignatedHospital to set
	 */
	public void setApprovalDesignatedHospital(String approvalDesignatedHospital) {
		this.approvalDesignatedHospital = approvalDesignatedHospital;
	}

	/**
	 * @return the transferDesignatedHospital
	 */
	public String getTransferDesignatedHospital() {
		return transferDesignatedHospital;
	}

	/**
	 * @param transferDesignatedHospital the transferDesignatedHospital to set
	 */
	public void setTransferDesignatedHospital(String transferDesignatedHospital) {
		this.transferDesignatedHospital = transferDesignatedHospital;
	}

	/**
	 * @return the approvalFlag
	 */
	public String getApprovalFlag() {
		return approvalFlag;
	}

	/**
	 * @param approvalFlag the approvalFlag to set
	 */
	public void setApprovalFlag(String approvalFlag) {
		this.approvalFlag = approvalFlag;
	}
	
	
	
	

}
