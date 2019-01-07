package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;
import com.tecsun.sisp.adapter.common.util.CommUtil;

public class FiveInsuranceRecordVO {

	@Field(empty="无")  
	private String ssq;//所属期
	@Field(empty="-")  
	private String jfjs;//缴费基数
	@Field(empty="-")  
	private String dwjn;//单位缴纳
	@Field(empty="-")  
	private String grjn;//个人缴纳
	@Field(empty="-")  
	private String jnze;//缴纳总额
	@Field(empty="-")  
	private String kx;//款项
	@Field(empty="-")  
	private String tcq;//统筹区
	@Field(empty="-")  
	private String jfbz;//缴费标志
	
	
	
	
	public String getSsq() {
		return ssq;
	}
	public void setSsq(String ssq) {
		this.ssq = ssq;
	}
	public String getJfjs() {
		if(jfjs == null || jfjs.isEmpty())
			return jfjs;
		
		return CommUtil.formatNumberic(jfjs);
	}
	public void setJfjs(String jfjs) {
		this.jfjs = jfjs;
	}
	public String getDwjn() {
		if(dwjn == null || dwjn.isEmpty())
			return dwjn;
		
		return CommUtil.formatNumberic(dwjn);
	}
	public void setDwjn(String dwjn) {
		this.dwjn = dwjn;
	}
	public String getGrjn() {
		if(grjn == null || grjn.isEmpty())
			return grjn;
		
		return CommUtil.formatNumberic(grjn);
	}
	public void setGrjn(String grjn) {
		this.grjn = grjn;
	}
	public String getJnze() {
		if(jnze == null || jnze.isEmpty())
			return jnze;
		
		return CommUtil.formatNumberic(jnze);
	}
	public void setJnze(String jnze) {
		this.jnze = jnze;
	}
	public String getKx() {
		return kx;
	}
	public void setKx(String kx) {
		this.kx = kx;
	}
	public String getTcq() {
		return tcq;
	}
	public void setTcq(String tcq) {
		this.tcq = tcq;
	}
	public String getJfbz() {
		return jfbz;
	}
	public void setJfbz(String jfbz) {
		this.jfbz = jfbz;
	}
	
	
}
