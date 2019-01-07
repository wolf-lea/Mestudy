package com.tecsun.sisp.adapter.modules.terminal.entity.response;
/**
 *	药品目录信息
 */
public class DrugCatalogInfoVO {
	
	private String drugNo;			// 药品编码
	
	private String drugChineseName;	// 中文名称
	
	private String drugEnglishName;	// 英文名称
	
	private String chargeCategory;	// 收费类别
	
	private String chargeLevel;		// 收费等级
	
	private String pinYinCode;		// 拼音码
	
	private String formulation;		// 剂型
	
	private String specification;	// 规格

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

	/**
	 * @return the drugEnglishName
	 */
	public String getDrugEnglishName() {
		return drugEnglishName;
	}

	/**
	 * @param drugEnglishName the drugEnglishName to set
	 */
	public void setDrugEnglishName(String drugEnglishName) {
		this.drugEnglishName = drugEnglishName;
	}

	/**
	 * @return the chargeCategory
	 */
	public String getChargeCategory() {
		return chargeCategory;
	}

	/**
	 * @param chargeCategory the chargeCategory to set
	 */
	public void setChargeCategory(String chargeCategory) {
		this.chargeCategory = chargeCategory;
	}

	/**
	 * @return the chargeLevel
	 */
	public String getChargeLevel() {
		return chargeLevel;
	}

	/**
	 * @param chargeLevel the chargeLevel to set
	 */
	public void setChargeLevel(String chargeLevel) {
		this.chargeLevel = chargeLevel;
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
	 * @return the formulation
	 */
	public String getFormulation() {
		return formulation;
	}

	/**
	 * @param formulation the formulation to set
	 */
	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}

	/**
	 * @return the specification
	 */
	public String getSpecification() {
		return specification;
	}

	/**
	 * @param specification the specification to set
	 */
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	

}
