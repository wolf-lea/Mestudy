package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;
import com.tecsun.sisp.adapter.common.util.CommUtil;

public class MARecordVO {

	@Field(empty="无")  
	private String xm; 	//姓名
	@Field(empty="无")  
	private String jsrq; //结算日期
	@Field(empty="无")  
	private String fwjgmc; //服务机构名称
	@Field(empty="-")  
	private String yllb; //医疗类别
	@Field(empty="-")  
	private String zhzf; //	账户支付
	@Field(empty="无")  
	private String djh; //单据号
	@Field(empty="-")  
	private String tczf; //统筹支付
	@Field(empty="-")  
	private String xjzf; //现金支付
	@Field(empty="-")  
	private String gwybz; //公务员补助
	@Field(empty="-")  
	private String bjdxzf; //保健对象支付
	@Field(empty="-")  
	private String lxtczf; //离休统筹支付
	@Field(empty="-")  
	private String ddyljgfd; //定点医疗机构负担
	@Field(empty="-")  
	private String debxzf; //大额保险支付
	@Field(empty="-")  
	private String fyze; //费用总额
	
	
	
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	public String getFwjgmc() {
		return fwjgmc;
	}
	public void setFwjgmc(String fwjgmc) {
		this.fwjgmc = fwjgmc;
	}
	public String getYllb() {
		return yllb;
	}
	public void setYllb(String yllb) {
		this.yllb = yllb;
	}
	public String getZhzf() {
		return zhzf;
	}
	public void setZhzf(String zhzf) {
		this.zhzf = zhzf;
	}
	public String getDjh() {
		return djh;
	}
	public void setDjh(String djh) {
		this.djh = djh;
	}
	public String getTczf() {
		return tczf;
	}
	public void setTczf(String tczf) {
		this.tczf = tczf;
	}
	public String getXjzf() {
		return xjzf;
	}
	public void setXjzf(String xjzf) {
		this.xjzf = xjzf;
	}
	public String getGwybz() {
		return gwybz;
	}
	public void setGwybz(String gwybz) {
		this.gwybz = gwybz;
	}
	public String getBjdxzf() {
		return bjdxzf;
	}
	public void setBjdxzf(String bjdxzf) {
		this.bjdxzf = bjdxzf;
	}
	public String getLxtczf() {
		return lxtczf;
	}
	public void setLxtczf(String lxtczf) {
		this.lxtczf = lxtczf;
	}
	public String getDdyljgfd() {
		return ddyljgfd;
	}
	public void setDdyljgfd(String ddyljgfd) {
		this.ddyljgfd = ddyljgfd;
	}
	public String getDebxzf() {
		return debxzf;
	}
	public void setDebxzf(String debxzf) {
		this.debxzf = debxzf;
	}
	public String getFyze() {
		return fyze;
	}
	public void setFyze(String fyze) {
		this.fyze = fyze;
	}
	
	
	
	
}
