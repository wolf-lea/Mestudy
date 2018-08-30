package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;
import com.tecsun.sisp.adapter.common.util.CommUtil;

public class MARecordVO {

	@Field(empty="无")  
	private String jysj; 					//交易时间
	@Field(empty="无")  
	private String fyfsd;					//费用发生地
	@Field(empty="无")  
	private String lx;						//类型
	@Field(empty="-")  
	private String zfy;						//总费用
	@Field(empty="-")  
	private String tczhzf;					//统筹账户支付
	@Field(empty="-")  
	private String grzhzf;					//个人账户支付
	@Field(empty="-")  
	private String zf;						//自付

	public String getJysj() {
		return jysj;
	}
	public void setJysj(String jysj) {
		this.jysj = jysj;
	}
	public String getFyfsd() {
		return fyfsd;
	}
	public void setFyfsd(String fyfsd) {
		this.fyfsd = fyfsd;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getZfy() {
		
		return CommUtil.formatNumberic(zfy);
	}
	public void setZfy(String zfy) {
		this.zfy = zfy;
	}
	public String getTczhzf() {
		return CommUtil.formatNumberic(tczhzf);
	}
	public void setTczhzf(String tczhzf) {
		this.tczhzf = tczhzf;
	}
	public String getGrzhzf() {
		return CommUtil.formatNumberic(grzhzf);
	}
	public void setGrzhzf(String grzhzf) {
		this.grzhzf = grzhzf;
	}
	public String getZf() {
		return CommUtil.formatNumberic(zf);
	}
	public void setZf(String zf) {
		this.zf = zf;
	}
}
