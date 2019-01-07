package com.tecsun.sisp.adapter.modules.terminal.entity.response;
/**
 * 参保证明打印次数
 */
public class PrintCertificationFrequencyVO {
	
	private String recordId;			// 唯一记录标识
	
	private String idCard;				// 身份证号
	
	private String name;				// 姓名
	
	private String medicalInsuranceNo;	// 医保编号
	
	private String printFrequency;		// 当月打印参保证明次数
	
	private String printTime;			// 打印时间

	/**
	 * @return the recordId
	 */
	public String getRecordId() {
		return recordId;
	}

	/**
	 * @param recordId the recordId to set
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
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
	

}
