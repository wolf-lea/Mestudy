package com.tecsun.sisp.net.modules.entity.response;

//机构出入参
public class OrgVo {
	private int orgId;
	private int orgCode;
	private String name;
	private int parentOrgId;
	private int distinctId ;
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public int getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(int orgCode) {
		this.orgCode = orgCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(int parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public int getDistinctId() {
		return distinctId;
	}
	public void setDistinctId(int distinctId) {
		this.distinctId = distinctId;
	}
	
	
	

}
