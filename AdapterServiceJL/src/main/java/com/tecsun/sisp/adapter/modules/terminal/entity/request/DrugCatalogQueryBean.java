package com.tecsun.sisp.adapter.modules.terminal.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

public class DrugCatalogQueryBean extends SecQueryBean {
	
	private String drugNo;			// 药品编码
	
	private String drugPinYinCode;	// 药品拼音码
	
	private String drugChineseName;	// 药品中文名称
	

	/**
	 * @return the drugNo
	 */
	public String getDrugNo() {
		return drugNo;
	}

	/**
	 * @param drugNo the drugNo to set
	 */
	public void setDrugNo(String drugNo) {
		this.drugNo = drugNo;
	}

	/**
	 * @return the drugPinYinCode
	 */
	public String getDrugPinYinCode() {
		return drugPinYinCode;
	}

	/**
	 * @param drugPinYinCode the drugPinYinCode to set
	 */
	public void setDrugPinYinCode(String drugPinYinCode) {
		this.drugPinYinCode = drugPinYinCode;
	}

	/**
	 * @return the drugChineseName
	 */
	public String getDrugChineseName() {
		return drugChineseName;
	}

	/**
	 * @param drugChineseName the drugChineseName to set
	 */
	public void setDrugChineseName(String drugChineseName) {
		this.drugChineseName = drugChineseName;
	}
	
	
	

}
