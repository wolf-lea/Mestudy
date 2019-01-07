package com.tecsun.sisp.adapter.modules.terminal.entity.response;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
/**
 * 异地定点医院
 */
public class OffsiteMedicalInstitutionVO extends SecQueryBean {
	
	private String provinceNo;			// 所属省级行政区编码
	
	private String provinceName;		// 所属省级行政区名称
	
	private String regionNo;			// 所属地区行政区编码
	
	private String regionName;			// 所属地区行政区名称
	
	private String nationalFixedPointNo;// 国家定点编码
	
	private String fixedPointName;		// 定点名称
	
	private String medicalLevel;		// 医疗机构等级

	/**
	 * @return the provinceNo
	 */
	public String getProvinceNo() {
		return provinceNo;
	}

	/**
	 * @param provinceNo the provinceNo to set
	 */
	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
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
	 * @return the regionNo
	 */
	public String getRegionNo() {
		return regionNo;
	}

	/**
	 * @param regionNo the regionNo to set
	 */
	public void setRegionNo(String regionNo) {
		this.regionNo = regionNo;
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
	 * @return the nationalFixedPointNo
	 */
	public String getNationalFixedPointNo() {
		return nationalFixedPointNo;
	}

	/**
	 * @param nationalFixedPointNo the nationalFixedPointNo to set
	 */
	public void setNationalFixedPointNo(String nationalFixedPointNo) {
		this.nationalFixedPointNo = nationalFixedPointNo;
	}

	/**
	 * @return the fixedPointName
	 */
	public String getFixedPointName() {
		return fixedPointName;
	}

	/**
	 * @param fixedPointName the fixedPointName to set
	 */
	public void setFixedPointName(String fixedPointName) {
		this.fixedPointName = fixedPointName;
	}

	/**
	 * @return the medicalLevel
	 */
	public String getMedicalLevel() {
		return medicalLevel;
	}

	/**
	 * @param medicalLevel the medicalLevel to set
	 */
	public void setMedicalLevel(String medicalLevel) {
		this.medicalLevel = medicalLevel;
	}
	
	

}
