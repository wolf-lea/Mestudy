package com.tecsun.sisp.adapter.modules.monitor.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * 医院实时结算情况入参
 */
public class HospitalRealTimeSettlementQueryBean extends SecQueryBean {
	
	private String currentTime; 	// 当前时间

	private String hospitalNo;		// 就诊流水号
	
	private String personalNo;		// 个人编号
	
	private String fixedInstitutionNo;// 机构编号
	
	private String areaFlag;		// 地区标识(offSite表示异地    local表示本地)

	/**
	 * @return the currentTime
	 */
	public String getCurrentTime() {
		return currentTime;
	}

	/**
	 * @param currentTime the currentTime to set
	 */
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
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

	/**
	 * @return the areaFlag
	 */
	public String getAreaFlag() {
		return areaFlag;
	}

	/**
	 * @param areaFlag the areaFlag to set
	 */
	public void setAreaFlag(String areaFlag) {
		this.areaFlag = areaFlag;
	}
	
	
}
