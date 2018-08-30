package com.tecsun.sisp.adapter.modules.card.entity.response;

public class CsspCardPickUpVO {
	
	private String sfzh;		//身份证号码	
	private String xm;			//姓名	
	private String tsbAddress;	//领卡地址	来源设备管理子系统，设备地址
	
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getTsbAddress() {
		return tsbAddress;
	}
	public void setTsbAddress(String tsbAddress) {
		this.tsbAddress = tsbAddress;
	}
}
