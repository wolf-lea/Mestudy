package com.tecsun.sisp.adapter.modules.ine.entity.response;

import java.util.Date;

/**
 * 个人求职列表vo
 *@author sipeng
 *@email zsp_banyue@163.com
 *@date 2017年11月24日
 * 
 */
public class PersonalApplicationsVo extends IneBaseVo {
	
	private long ineCode ; //信息代号
	private String positionCode;
	private String positionName;
	private String sal ; //工资信息
	private String area ; //工作地点详细地址
	private Date updateTime ; //更新时间
	private String accountMethodName; //结算方式对应的中文
	private String address;//工作地点详细地址
	
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	
	
}
