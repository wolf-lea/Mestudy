package com.tecsun.sisp.fakamanagement.modules.entity.param.statistics;

import com.tecsun.sisp.fakamanagement.modules.entity.TSBVO;

public class FKWDBean extends TSBVO {
	
	private Integer cbid;
	private String orgcode;
	private String parentid;

	/**
	 * @return the parentid
	 */
	public String getParentid() {
		return parentid;
	}

	/**
	 * @param parentid the parentid to set
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Integer getCbid() {
		return cbid;
	}

	public void setCbid(Integer cbid) {
		this.cbid = cbid;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

}
