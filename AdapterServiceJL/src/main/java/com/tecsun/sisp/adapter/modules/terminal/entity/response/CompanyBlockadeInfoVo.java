package com.tecsun.sisp.adapter.modules.terminal.entity.response;

/**
 * 
 * @Title: 单位封锁信息出参
 * @company :TECSUN
 * @developer: ZENGYUNHUA
 * @date 2018年8月24日 下午2:29:19
 * @version 1.0
 */
public class CompanyBlockadeInfoVo {

	private String companyNo; // 单位编号

	private String blockType; // 封锁类别

	private String insuranceType; // 险种

	private String blockadeReason; // 封锁原因

	private String blockadeFlag; // 封锁标志

	private String startTime; // 开始时间

	private String endTime; // 终止时间

	private String operationDate; // 经办日期

	private String coordinationArea; // 统筹区

	/**
	 * @return the companyNo
	 */
	public String getCompanyNo() {
		return companyNo;
	}

	/**
	 * @param companyNo
	 *            the companyNo to set
	 */
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	/**
	 * @return the blockType
	 */
	public String getBlockType() {
		return blockType;
	}

	/**
	 * @param blockType
	 *            the blockType to set
	 */
	public void setBlockType(String blockType) {
		this.blockType = blockType;
	}

	/**
	 * @return the insuranceType
	 */
	public String getInsuranceType() {
		return insuranceType;
	}

	/**
	 * @param insuranceType
	 *            the insuranceType to set
	 */
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	/**
	 * @return the blockadeReason
	 */
	public String getBlockadeReason() {
		return blockadeReason;
	}

	/**
	 * @param blockadeReason
	 *            the blockadeReason to set
	 */
	public void setBlockadeReason(String blockadeReason) {
		this.blockadeReason = blockadeReason;
	}

	/**
	 * @return the blockadeFlag
	 */
	public String getBlockadeFlag() {
		return blockadeFlag;
	}

	/**
	 * @param blockadeFlag
	 *            the blockadeFlag to set
	 */
	public void setBlockadeFlag(String blockadeFlag) {
		this.blockadeFlag = blockadeFlag;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
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
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the operationDate
	 */
	public String getOperationDate() {
		return operationDate;
	}

	/**
	 * @param operationDate
	 *            the operationDate to set
	 */
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	/**
	 * @return the coordinationArea
	 */
	public String getCoordinationArea() {
		return coordinationArea;
	}

	/**
	 * @param coordinationArea
	 *            the coordinationArea to set
	 */
	public void setCoordinationArea(String coordinationArea) {
		this.coordinationArea = coordinationArea;
	}

}
