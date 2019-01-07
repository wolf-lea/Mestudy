package com.tecsun.sisp.adapter.modules.terminal.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

public class MedicalInsuranceQueryBean extends SecQueryBean {

	private String name;		// 姓名
	
	private String idCard;		// 身份证号
	
	private String personalNo;	// 个人编号
	
	private String companyNo;	// 公司编号
	
	private String socialCardNo;	// 社保卡号

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
	 * @return the socialCardNo
	 */
	public String getSocialCardNo() {
		return socialCardNo;
	}

	/**
	 * @param socialCardNo the socialCardNo to set
	 */
	public void setSocialCardNo(String socialCardNo) {
		this.socialCardNo = socialCardNo;
	}
	

}
