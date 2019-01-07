package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;
import com.tecsun.sisp.adapter.common.util.CommUtil;

public class EIIssueRecordVO {

	@Field(empty="无")  
	private String ffsj;			//发放时间
	@Field(empty="无")  
	private String ffyh;			//发放银行
	@Field(empty="无")  
	private String ffzh;			//发放账号
	@Field(empty="-")  
	private String ffzje;			//发放总金额
	@Field(empty="-")  
	private String jcylj;			//基础养老金
	@Field(empty="-")  
	private String grzhylj;			//个人账户养老金
	@Field(empty="-")  
	private String gdxylj;			//过渡性养老金
	@Field(empty="-")  
	private String bfje;			//补发金额
	@Field(empty="-")  
	private String tfje;			//退发金额
	
	
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
		if(ffzje == null || ffzje.isEmpty())
			return ffzje;
		
		return CommUtil.formatNumberic(ffzje);
	}
	public void setFfzje(String ffzje) {
		this.ffzje = ffzje;
	}
	public String getJcylj() {
		if(jcylj == null || jcylj.isEmpty())
			return jcylj;
		
		return CommUtil.formatNumberic(jcylj);
	}
	public void setJcylj(String jcylj) {
		this.jcylj = jcylj;
	}
	public String getGrzhylj() {
		if(grzhylj == null || grzhylj.isEmpty())
			return grzhylj;
		
		return CommUtil.formatNumberic(grzhylj);
	}
	public void setGrzhylj(String grzhylj) {
		this.grzhylj = grzhylj;
	}
	public String getGdxylj() {
		if(gdxylj == null || gdxylj.isEmpty())
			return gdxylj;
		
		return CommUtil.formatNumberic(gdxylj);
	}
	public void setGdxylj(String gdxylj) {
		this.gdxylj = gdxylj;
	}
	public String getBfje() {
		if(bfje == null || bfje.isEmpty())
			return bfje;
		
		return CommUtil.formatNumberic(bfje);
	}
	public void setBfje(String bfje) {
		this.bfje = bfje;
	}
	public String getTfje() {
		if(tfje == null || tfje.isEmpty())
			return tfje;
		
		return CommUtil.formatNumberic(tfje);
	}
	public void setTfje(String tfje) {
		this.tfje = tfje;
	}

                           
	
	
}
