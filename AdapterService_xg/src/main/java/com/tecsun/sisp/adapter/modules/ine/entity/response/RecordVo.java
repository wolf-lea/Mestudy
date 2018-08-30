package com.tecsun.sisp.adapter.modules.ine.entity.response;

import java.util.Date;

/**
 *@author sipeng
 *@email zsp_banyue@163.com
 *@date 2017年11月7日
 * 
 */
public class RecordVo extends IneBaseVo{
	
	private long recordId;//报名记录的id
	private long ineCode ; //信息代号
	private String positionCode;//岗位代码
	private String positionName;//岗位名称
	private String accountMethodName;//结算方式 
	private String sal ; //工资信息
	private String area ; //工作地点详细地址
	private Date createTime;//工作时间
	private String isValid;//报名/邀请的信息是否有效
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
	/*public String getAccountMethod() {
		return accountMethod;
	}
	public void setAccountMethod(String accountMethod) {
		if("0".equals(accountMethod)){
			this.accountMethod = "日结";
		}else if("1".equals(accountMethod)){
			this.accountMethod = "月结";
		}else if("2".equals(accountMethod)){
			this.accountMethod = "小时结";
		}else if("3".equals(accountMethod)){
			this.accountMethod = "完工结";
		}
	}*/
	
	public String getSal() {
		return sal;
	}
	public String getAccountMethodName() {
		return accountMethodName;
	}
	public void setAccountMethodName(String accountMethodName) {
		this.accountMethodName = accountMethodName;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		if("1".equals(isValid)){
			this.isValid = "有效";
		}else{
			this.isValid = "无效";
		}
		
	}
	public long getRecordId() {
		return recordId;
	}
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	
	
	
	
}
