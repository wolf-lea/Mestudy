package com.tecsun.sisp.adapter.modules.ine.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 *@author sipeng
 *@email zsp_banyue@163.com
 *@date 2017年10月30日
 * 
 */
public class InfoBean extends SecQueryBean{
	
	private long ineCode ; //信息代号
	private String positionCode;//岗位 代号
	private String accountMethod;//结算方式 
	private String sal ; //工资信息
	private String area ; //工作地点
	private String areaCode;//所选区域编码
	private String tel ; //联系方式
	private String eatWay ;//吃 包中餐、包中晚餐、包早中餐、包早晚餐、包三餐、不包
	private String liveWay ;//住 包住、不包住
	private String education ;//教育水平
	private String years ; //工作年限
	private String remark ; //备注
	private String workStartDate ;//工作开始日期
	private String workEndDate ;//工作开始日期
	private String workStartTime ;//工作开始时间
	private String workEndTime ;//工作结束时间
	private String queryType; // 信息种类  1表示招工信息  0表示求职信息
	private String amounts; //招工人数
	private String isWorked;//是否参加工作
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
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getAccountMethod() {
		return accountMethod;
	}
	public void setAccountMethod(String accountMethod) {
		this.accountMethod = accountMethod;
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
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEatWay() {
		return eatWay;
	}
	public void setEatWay(String eatWay) {
		this.eatWay = eatWay;
	}
	public String getLiveWay() {
		return liveWay;
	}
	public void setLiveWay(String liveWay) {
			this.liveWay = liveWay;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getAmounts() {
		return amounts;
	}
	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}
	public String getIsWorked() {
		return isWorked;
	}
	public void setIsWorked(String isWorked) {
			this.isWorked = isWorked;
	}
	
}
