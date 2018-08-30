package com.tecsun.sisp.adapter.modules.card.entity.response;


/**
 * 
 * @Title: DeviceRegistVO
 * @company :TECSUN
 * @developer: ZENGYUNHUA
 *	@date 2018年3月27日 下午6:57:12
 *	@version 1.0
 */
public class DeviceRegistVO {
	
	
	
	private Integer csspBankId;//银行机构id
	private String orgId;//社保网点id
	private Integer areaId;//区域id
	
	
	public Integer getCsspBankId() {
		return csspBankId;
	}
	public void setCsspBankId(Integer csspBankId) {
		this.csspBankId = csspBankId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	
	

}
