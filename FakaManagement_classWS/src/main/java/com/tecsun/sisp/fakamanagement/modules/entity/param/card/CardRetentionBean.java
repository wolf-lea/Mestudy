package com.tecsun.sisp.fakamanagement.modules.entity.param.card;

import com.tecsun.sisp.fakamanagement.modules.entity.TSBVO;

public class CardRetentionBean extends TSBVO {
	
	private String name;
	private Integer cbid;
	private Integer[] ids;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	/**
	 * @return the cbid
	 */
	public Integer getCbid() {
		return cbid;
	}
	/**
	 * @param cbid the cbid to set
	 */
	public void setCbid(Integer cbid) {
		this.cbid = cbid;
	}
	
	

}
