package com.tecsun.sisp.adapter.modules.terminal.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

public class MedicalInstitutionQueryBean extends SecQueryBean{
	
	private String areaFlag;				// 区域标志（offSite-->省外    local-->省内）
	
	private String regionName;				// 所属地区行政区名称
	
	private String provinceName;			// 所属省级行政区名称
	
	private String medicalInstitutionNo;	// 医疗机构编码
	
	private String medicalInstitutionName;	// 医疗机构名称

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

	/**
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * @param regionName the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/**
	 * @return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * @param provinceName the provinceName to set
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 * @return the medicalInstitutionNo
	 */
	public String getMedicalInstitutionNo() {
		return medicalInstitutionNo;
	}

	/**
	 * @param medicalInstitutionNo the medicalInstitutionNo to set
	 */
	public void setMedicalInstitutionNo(String medicalInstitutionNo) {
		this.medicalInstitutionNo = medicalInstitutionNo;
	}

	/**
	 * @return the medicalInstitutionName
	 */
	public String getMedicalInstitutionName() {
		return medicalInstitutionName;
	}

	/**
	 * @param medicalInstitutionName the medicalInstitutionName to set
	 */
	public void setMedicalInstitutionName(String medicalInstitutionName) {
		this.medicalInstitutionName = medicalInstitutionName;
	}
	
	
	

}
