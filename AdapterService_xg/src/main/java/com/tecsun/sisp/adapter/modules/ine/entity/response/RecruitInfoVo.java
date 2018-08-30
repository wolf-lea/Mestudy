package com.tecsun.sisp.adapter.modules.ine.entity.response;

import java.util.Date;

/**招工详情vo
 * @author sipeng
 * @email zsp_banyue@163.com
 * @date 2017年10月25日
 */
public class RecruitInfoVo extends IneBaseVo {
	
	private long ineCode ; //信息代号
	private String positionCode;//岗位 代号
	private String positionName;//岗位名称
	private String amounts;//招工人数
	private String accountMethod;//结算方式 编码
	private String sal ; //工资信息
	private String areaCode ; //工作地点  县级编号
	private String area ;// 工作区域
	private String address;//详细地址
	private String workStartDate ;//工作开始日期
	private String workEndDate ;//工作开始日期
	private String workStartTime ;//工作开始时间
	private String workEndTime ;//工作结束时间
	private String tel ; //联系方式
	private String eatWay ;//吃 包中餐、包中晚餐、包早中餐、包早晚餐、包三餐、不包  编码形式
	private String liveWay ;//住 包住、不包住  编码形式
	private String education ;//教育水平  编码形式
	private String eatWayName ;//吃 包中餐、包中晚餐、包早中餐、包早晚餐、包三餐、不包  编码对应的中文
	private String liveWayName ;//住 包住、不包住  编码对应的中文
	private String educationName ;//教育水平  编码对应的中文
	private String isWorkedName ;//是否参加过工作  是  否 
	private String isWorked ;//是否有经验
	private String years ; //工作年限
	private String remark ; //备注
	private Date updateTime ; //更新时间
	private Boolean isValid ; //是否有效
	private String xm;//发布者姓名
	//private String sfzh ; //发布者身份证号
	private String opType;//是否可报名 1表示可以 0表示不可以
	private String accountMethodName; //结算方式对应的中文
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
	public String getIsWorkedName() {
		return isWorkedName;
	}
	public void setIsWorkedName(String isWorkedName) {
		this.isWorkedName = isWorkedName;
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
	public String getAmounts() {
		return amounts;
	}
	public void setAmounts(String amounts) {
		this.amounts = amounts+"人";
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public String getEatWayName() {
		return eatWayName;
	}
	public void setEatWayName(String eatWayName) {
		this.eatWayName = eatWayName;
	}
	public String getLiveWayName() {
		return liveWayName;
	}
	public void setLiveWayName(String liveWayName) {
		this.liveWayName = liveWayName;
	}
	public String getEducationName() {
		return educationName;
	}
	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public String getAccountMethodName() {
		return accountMethodName;
	}
	public void setAccountMethodName(String accountMethodName) {
		this.accountMethodName = accountMethodName;
	}
	public String getIsWorked() {
		return isWorked;
	}
	public void setIsWorked(String isWorked) {
		this.isWorked = isWorked;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
}
