package com.tecsun.sisp.adapter.modules.terminal.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

public class MedicalTreatmentQueryBean extends SecQueryBean {
	
	private String projectNo;			// 项目编码
	
	private String pinYinCode;			// 诊疗拼音码
	
	private String projectChineseName;	// 项目中文名称

	/**
	 * @return the projectNo
	 */
	public String getProjectNo() {
		return projectNo;
	}

	/**
	 * @param projectNo the projectNo to set
	 */
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	/**
	 * @return the pinYinCode
	 */
	public String getPinYinCode() {
		return pinYinCode;
	}

	/**
	 * @param pinYinCode the pinYinCode to set
	 */
	public void setPinYinCode(String pinYinCode) {
		this.pinYinCode = pinYinCode;
	}

	/**
	 * @return the projectChineseName
	 */
	public String getProjectChineseName() {
		return projectChineseName;
	}

	/**
	 * @param projectChineseName the projectChineseName to set
	 */
	public void setProjectChineseName(String projectChineseName) {
		this.projectChineseName = projectChineseName;
	}
	
	
	

}
