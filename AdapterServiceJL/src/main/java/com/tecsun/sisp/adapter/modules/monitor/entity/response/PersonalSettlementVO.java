package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 当前系统时间产生结算的个人信息（个人编号、住院号、服务机构代码）
 */
public class PersonalSettlementVO {
	
	private String personalNo;			// 个人编号
	
	private String hospitalNo;			// 住院号
	
	private String fixedInstitutionNo;	// 服务机构代码

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
	 * @return the hospitalNo
	 */
	public String getHospitalNo() {
		return hospitalNo;
	}

	/**
	 * @param hospitalNo the hospitalNo to set
	 */
	public void setHospitalNo(String hospitalNo) {
		this.hospitalNo = hospitalNo;
	}

	/**
	 * @return the fixedInstitutionNo
	 */
	public String getFixedInstitutionNo() {
		return fixedInstitutionNo;
	}

	/**
	 * @param fixedInstitutionNo the fixedInstitutionNo to set
	 */
	public void setFixedInstitutionNo(String fixedInstitutionNo) {
		this.fixedInstitutionNo = fixedInstitutionNo;
	}
	
	
	

}
