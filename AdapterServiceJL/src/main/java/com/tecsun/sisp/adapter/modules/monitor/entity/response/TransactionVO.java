package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 交易情况
 */
public class TransactionVO {
	
	private String serviceCategory;			// 业务类型
	
	private String transactionTime;			// 交易时间
	
	private String insuredPerson;			// 参保人
	
	private String medicalInsitituation;	// 医疗机构

	/**
	 * @return the serviceCategory
	 */
	public String getServiceCategory() {
		return serviceCategory;
	}

	/**
	 * @param serviceCategory the serviceCategory to set
	 */
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	/**
	 * @return the transactionTime
	 */
	public String getTransactionTime() {
		return transactionTime;
	}

	/**
	 * @param transactionTime the transactionTime to set
	 */
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	/**
	 * @return the insuredPerson
	 */
	public String getInsuredPerson() {
		return insuredPerson;
	}

	/**
	 * @param insuredPerson the insuredPerson to set
	 */
	public void setInsuredPerson(String insuredPerson) {
		this.insuredPerson = insuredPerson;
	}

	/**
	 * @return the medicalInsitituation
	 */
	public String getMedicalInsitituation() {
		return medicalInsitituation;
	}

	/**
	 * @param medicalInsitituation the medicalInsitituation to set
	 */
	public void setMedicalInsitituation(String medicalInsitituation) {
		this.medicalInsitituation = medicalInsitituation;
	}
	
}
