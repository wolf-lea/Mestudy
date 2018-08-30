package com.tecsun.sisp.adapter.modules.ine.entity.response;


/**
 * 求职列表vo
 *@author sipeng
 *@email zsp_banyue@163.com
 *@date 2017年11月24日
 * 
 */
public class ApplicationListVo extends IneBaseVo {
	
	private long ineCode ; //信息代号
	private String positionCode;
	private String positionName;
	private String sal ; //工资信息
	private String area ; //工作地点
	private String address;//工作地点详细地址
	private String updateTime ; //更新时间
	private String opType;//是否可以邀请 1表示可以 0表示不可以
	private String infoUrl; //信息url
	private String accountMethodName; //结算方式对应的中文
	
	private String tel;
	private String xm;
	
	private String isOwn;//是否是自己发布的 1 表示是  0 表示不是
	
	
	
	
	public String getIsOwn() {
		return isOwn;
	}
	public void setIsOwn(String isOwn) {
		if("1".equals(isOwn)){
			this.isOwn = "我的";
		}else{
			this.isOwn = "";
		}
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getIneCode() {
		return ineCode;
	}
	public void setIneCode(long ineCode) {
		this.ineCode = ineCode;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getSal() {
		return sal;
	}
	public void setSal(String sal) {
		this.sal = sal;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		if("1".equals(opType)){
			this.opType = "邀请";
		}else{
			this.opType = "已邀请";
		}
	}
	public String getInfoUrl() {
		return infoUrl;
	}
	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}
	public String getAccountMethodName() {
		return accountMethodName;
	}
	public void setAccountMethodName(String accountMethodName) {
		this.accountMethodName = accountMethodName;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
