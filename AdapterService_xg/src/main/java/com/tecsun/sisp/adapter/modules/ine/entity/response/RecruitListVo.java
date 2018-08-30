package com.tecsun.sisp.adapter.modules.ine.entity.response;


/**招工记录列表vo
 *@author sipeng
 *@email zsp_banyue@163.com
 *@date 2017年11月24日
 * 
 */
public class RecruitListVo extends IneBaseVo{
	
	private long ineCode ; //信息代号
	private String positionName;//岗位名称
	private String sal ; //工资信息
	private String area ;// 工作地点
	private String address;//工作地点详细地址
	private String amounts;
	private String updateTime ; //更新时间
	private String opType;//是否可报名 1表示可以 0表示不可以
	private String infoUrl; //信息url
	private String accountMethodName; //结算方式对应的中文
	
	private String workStartDate ;//工作开始日期
	private String workEndDate ;//工作开始日期
	private String workStartTime ;//工作开始时间
	private String workEndTime ;//工作结束时间
	
	private String xm;
	private String tel;
	
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
			this.opType = "报名";
		}else{
			this.opType = "已报名";
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
	public String getAmounts() {
		return amounts;
	}
	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}
	public String getWorkStartDate() {
		return workStartDate;
	}
	public void setWorkStartDate(String workStartDate) {
		this.workStartDate = workStartDate;
	}
	public String getWorkEndDate() {
		return workEndDate;
	}
	public void setWorkEndDate(String workEndDate) {
		this.workEndDate = workEndDate;
	}
	public String getWorkStartTime() {
		return workStartTime;
	}
	public void setWorkStartTime(String workStartTime) {
		this.workStartTime = workStartTime;
	}
	public String getWorkEndTime() {
		return workEndTime;
	}
	public void setWorkEndTime(String workEndTime) {
		this.workEndTime = workEndTime;
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
