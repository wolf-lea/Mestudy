package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年3月12日 下午6:33:30
* 说明：工伤医疗结算信息
*/

public class EIISettlementVO {
	
	@Field(empty="-") 
	private String ssq;//所属期
	@Field(empty="-")
	private String ddjgmc;//定点机构名称
	@Field(empty="-")
	private String ylfze;//医疗费总额
	@Field(empty="-")
	private String zhzf;//账户支付
	@Field(empty="-")
	private String xjzf;//现金支
	@Field(empty="-")
	private String gstczf;//工伤统筹支付
	
	
	public String getSsq() {
		return ssq;
	}
	public void setSsq(String ssq) {
		this.ssq = ssq;
	}
	public String getDdjgmc() {
		return ddjgmc;
	}
	public void setDdjgmc(String ddjgmc) {
		this.ddjgmc = ddjgmc;
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
	public String getGstczf() {
		return gstczf;
	}
	public void setGstczf(String gstczf) {
		this.gstczf = gstczf;
	}
	
	
	
	
     

}
