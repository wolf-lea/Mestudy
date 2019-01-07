package com.tecsun.sisp.adapter.modules.so.entity.response;

import org.springframework.beans.factory.annotation.Value;

import com.tecsun.sisp.adapter.common.annotation.Field;

/**个人参保信息VO
 * Created by danmeng on 2016/8/9.
 */
public class InsuranceVO {
	
	@Field(empty="无")  
    private    String  xm; 	//姓名
	@Field(empty="无")  
	private    String  sex;//性别
	@Field(empty="无")  
	private    String  nation;//民族
    @Field(empty="无")  
    private    String  sfzh;//身份证号
    @Field(empty="无")  
    private    String  personStatus;//人员状态
    @Field(empty="无")  
    private	   String  sbkh;//社保卡号
    @Field(empty="无")  
    private	   String  companyName;//单位名称
    @Field(empty="无")  
    private	   String  workDate;//参加工作日期
    
    
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getPersonStatus() {
		return personStatus;
	}
	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}
	public String getSbkh() {
		return sbkh;
	}
	public void setSbkh(String sbkh) {
		this.sbkh = sbkh;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
    
    
    
    
    
    
    
}

