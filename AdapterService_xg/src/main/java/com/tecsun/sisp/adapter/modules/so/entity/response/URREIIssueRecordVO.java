package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;

public class URREIIssueRecordVO {

	@Field(empty="无")  
	private String	ffsj;   	//发放时间        
	@Field(empty="无")  
	private String  ffyh;   	//发放银行
	@Field(empty="无")  
	private String  ffzh;   	//发放账号
	@Field(empty="-")  
	private String  ffzje; 		//发放总金额     
	@Field(empty="-")  
	private String  jcylj;  	//基础养老金   
	@Field(empty="-")  
	private String  grzhylj;	//个人账户养老金
	@Field(empty="-")  
	private String  bfje;   	//补发金额        
	@Field(empty="-")  
	private String  tfje;   	//退发金额           

	public String getFfsj() {
		return ffsj;
	}
	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}
	public String getFfyh() {
		return ffyh;
	}
	public void setFfyh(String ffyh) {
		this.ffyh = ffyh;
	}
	public String getFfzh() {
		return ffzh;
	}
	public void setFfzh(String ffzh) {
		this.ffzh = ffzh;
	}
	public String getFfzje() {
		return ffzje;
	}
	public void setFfzje(String ffzje) {
		this.ffzje = ffzje;
	}
	public String getJcylj() {
		return jcylj;
	}
	public void setJcylj(String jcylj) {
		this.jcylj = jcylj;
	}
	public String getGrzhylj() {
		return grzhylj;
	}
	public void setGrzhylj(String grzhylj) {
		this.grzhylj = grzhylj;
	}
	public String getBfje() {
		return bfje;
	}
	public void setBfje(String bfje) {
		this.bfje = bfje;
	}
	public String getTfje() {
		return tfje;
	}
	public void setTfje(String tfje) {
		this.tfje = tfje;
	}
	
}
