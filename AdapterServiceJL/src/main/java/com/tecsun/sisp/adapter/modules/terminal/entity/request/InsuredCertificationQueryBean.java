package com.tecsun.sisp.adapter.modules.terminal.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

public class InsuredCertificationQueryBean extends SecQueryBean{
	
	private String name;				// 姓名
	
	private String idCard;				// 身份证号
	
	private String message;				// 查询个人信息返回信息
	
	private String printTime;			// 打印时间
	
	private String printFlag;			// 标志
	
	private String printFrequency;		// 打印次数
	
	private String insuredStatus;		// 参保状态
	
	private String personnelStatus; 	// 人员状态（城镇居民、在职）
	
	private String medicalInsuranceNo;	// 医保编号

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the printTime
	 */
	public String getPrintTime() {
		return printTime;
	}

	/**
	 * @param printTime the printTime to set
	 */
	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}

	/**
	 * @return the printFlag
	 */
	public String getPrintFlag() {
		return printFlag;
	}

	/**
	 * @param printFlag the printFlag to set
	 */
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	/**
	 * @return the printFrequency
	 */
	public String getPrintFrequency() {
		return printFrequency;
	}

	/**
	 * @param printFrequency the printFrequency to set
	 */
	public void setPrintFrequency(String printFrequency) {
		this.printFrequency = printFrequency;
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
	 * @return the personnelStatus
	 */
	public String getPersonnelStatus() {
		return personnelStatus;
	}

	/**
	 * @param personnelStatus the personnelStatus to set
	 */
	public void setPersonnelStatus(String personnelStatus) {
		this.personnelStatus = personnelStatus;
	}

	/**
	 * @return the medicalInsuranceNo
	 */
	public String getMedicalInsuranceNo() {
		return medicalInsuranceNo;
	}

	/**
	 * @param medicalInsuranceNo the medicalInsuranceNo to set
	 */
	public void setMedicalInsuranceNo(String medicalInsuranceNo) {
		this.medicalInsuranceNo = medicalInsuranceNo;
	}
	
	
	


}
