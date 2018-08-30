package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;

/**
 * 个人参保信息VO Created by danmeng on 2016/8/9.
 */
public class InsuranceVO {

	@Field(empty = "无")
	private String xm; // 姓名
	@Field(empty = "无")
	private String sfzh;// 身份证号
	@Field(empty = "无")
	private String cbzt;// 参保状态
	@Field(empty = "无")
	private String cbdw;// 参保单位
	@Field(empty = "无")
	private String cbsj;// 参保时间
	@Field(empty = "无")
	private String sbkh;// 社保卡号
	@Field(empty = "无")
	private String grxp;// 个人相片
	@Field(empty = "未知")
	private String gender;// 性别
	@Field(empty = "未知")
	private String identity;// 人员身份
	@Field(empty = "未知")
	private String personStatus;// 人员状态

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getCbzt() {
		return cbzt;
	}

	public void setCbzt(String cbzt) {
		this.cbzt = cbzt;
	}

	public String getCbdw() {
		return cbdw;
	}

	public void setCbdw(String cbdw) {
		this.cbdw = cbdw;
	}

	public String getCbsj() {
		return cbsj;
	}

	public void setCbsj(String cbsj) {
		this.cbsj = cbsj;
	}

	public String getSbkh() {
		return sbkh;
	}

	public void setSbkh(String sbkh) {
		this.sbkh = sbkh;
	}

	public String getGrxp() {
		return grxp;
	}

	public void setGrxp(String grxp) {
		this.grxp = grxp;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

}
