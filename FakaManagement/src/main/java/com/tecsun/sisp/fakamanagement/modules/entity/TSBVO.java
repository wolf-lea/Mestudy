package com.tecsun.sisp.fakamanagement.modules.entity;

public class TSBVO {
	
	 private String 	channelcode;	//渠道编码
    private String 	deviceid;		//设备编码
    private String 	tokenid;		//权限验证码
    private String	loginuserid;
	public String getChannelcode() {
		return channelcode;
	}
	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getTokenid() {
		return tokenid;
	}
	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
	public String getLoginuserid() {
		return loginuserid;
	}
	public void setLoginuserid(String loginuserid) {
		this.loginuserid = loginuserid;
	}
	

}
