package com.tecsun.sisp.iface.common.vo.faceverify;

public class IsCheckBean {

	private long type;    //区分 注册与验证 ，注册：1 验证为2
	private long isCheckType; //注册 ：  1为一张照片验证，2为三张照片验证 ；3为德生宝采集
	private String idCard;
	private String identifyId;
	private String token;
	
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}
	public long getIsCheckType() {
		return isCheckType;
	}
	public void setIsCheckType(long isCheckType) {
		this.isCheckType = isCheckType;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getIdentifyId() {
		return identifyId;
	}
	public void setIdentifyId(String identifyId) {
		this.identifyId = identifyId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
