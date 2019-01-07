package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年2月1日 下午3:10:16
* 说明：生育医疗结算信息
*/

public class BISettlementVO {
	
	private String grbh;//个人编号
	private String rylb;//人员类别
	private String ddjgmc;//定点机构名称
	private String yllb;//医疗类别
	@Field(empty="-")
	private String jsrq;//结算日期
	@Field(empty="0")
	private String ylfze;//医疗费总额
	@Field(empty="0")
	private String zhzf;//账户支付
	@Field(empty="0")
	private String xjzf;//现金支付
	@Field(empty="0")
	private String sytczf;//生育统筹支付
	
	public String getGrbh() {
		return grbh;
	}
	public void setGrbh(String grbh) {
		this.grbh = grbh;
	}
	public String getRylb() {
		return rylb;
	}
	public void setRylb(String rylb) {
		this.rylb = rylb;
	}
	public String getDdjgmc() {
		return ddjgmc;
	}
	public void setDdjgmc(String ddjgmc) {
		this.ddjgmc = ddjgmc;
	}
	public String getYllb() {
		return yllb;
	}
	public void setYllb(String yllb) {
		this.yllb = yllb;
	}
	public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	public String getYlfze() {
		return ylfze;
	}
	public void setYlfze(String ylfze) {
		this.ylfze = ylfze;
	}
	public String getZhzf() {
		return zhzf;
	}
	public void setZhzf(String zhzf) {
		this.zhzf = zhzf;
	}
	public String getXjzf() {
		return xjzf;
	}
	public void setXjzf(String xjzf) {
		this.xjzf = xjzf;
	}
	public String getSytczf() {
		return sytczf;
	}
	public void setSytczf(String sytczf) {
		this.sytczf = sytczf;
	}
	
	
	

}
