package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;
import com.tecsun.sisp.adapter.common.util.CommUtil;

public class URREIPayBasicVO {

	@Field(empty="无")  
	private String xm;						// 姓名	       
	@Field(empty="无")  
	private String sfzh;					// 公民身份号码
	@Field(empty="无")  
	private String cbzt;					// 参保状态
	@Field(empty="-")  
	private String jnze;					// 缴纳总额	   
	@Field(empty="-")  
	private String ljjnnx;					// 累计缴费年限  

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
	public String getJnze() {
		if(jnze == null || jnze.isEmpty())
			return jnze;
		
		return CommUtil.formatNumberic(jnze);
	}
	public void setJnze(String jnze) {
		this.jnze = jnze;
	}
	public String getLjjnnx() {
		return ljjnnx;
	}
	public void setLjjnnx(String ljjnnx) {
		this.ljjnnx = ljjnnx;
	}
}
