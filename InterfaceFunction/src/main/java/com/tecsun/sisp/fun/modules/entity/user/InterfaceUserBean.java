package com.tecsun.sisp.fun.modules.entity.user;

public class InterfaceUserBean {

	private String userName;//接口用户名
	private long userStatus;//接口用户状态 -- 0为可用，1为不可用
	private String funCode;//接口功能编码
	private String funName;//接口功能名称
	private long funStatus;//接口功能状态 -- 0为可用，1为不可用
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(long userStatus) {
		this.userStatus = userStatus;
	}
	public String getFunCode() {
		return funCode;
	}
	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}
	public long getFunStatus() {
		return funStatus;
	}
	public void setFunStatus(long funStatus) {
		this.funStatus = funStatus;
	}
	
}
