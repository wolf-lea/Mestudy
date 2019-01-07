package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年3月12日 下午3:57:16
* 说明：个人医疗费用信息
*/

public class MACostVO {
	
	@Field(empty="无")
	private String xm;//姓名
	@Field(empty="-") 
	private String jyrq;//交易日期
	@Field(empty="-") 
	private String sflx;//收费类型
	@Field(empty="-") 
	private String ypmc;//药品名称
	@Field(empty="-") 
	private String jx;//剂型 
	@Field(empty="-") 
	private String cfrq;//处方日期
	@Field(empty="-") 
	private String dj;//单价
	@Field(empty="-") 
	private String sl;//数量
	@Field(empty="-") 
	private String je;//金额
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getJyrq() {
		return jyrq;
	}
	public void setJyrq(String jyrq) {
		this.jyrq = jyrq;
	}
	public String getSflx() {
		return sflx;
	}
	public void setSflx(String sflx) {
		this.sflx = sflx;
	}
	public String getYpmc() {
		return ypmc;
	}
	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
	public String getJx() {
		return jx;
	}
	public void setJx(String jx) {
		this.jx = jx;
	}
	public String getCfrq() {
		return cfrq;
	}
	public void setCfrq(String cfrq) {
		this.cfrq = cfrq;
	}
	public String getDj() {
		return dj;
	}
	public void setDj(String dj) {
		this.dj = dj;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	
	
	
	

}
