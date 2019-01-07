package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;
import com.tecsun.sisp.adapter.common.util.CommUtil;

public class FiveInsuranceVO {

	@Field(empty="-")  
	private String jnze;//缴纳总额	
	@Field(empty="-")  
	private String dwjnhj;//单位缴纳合计
	@Field(empty="-")
	private String grjnhj;//个人缴纳合计	
	
	public String getJnze() {
		if(jnze == null|| jnze.isEmpty()){
			//jnze = "-";
			return jnze;
		}
		return CommUtil.formatNumberic(jnze);
	}
	public void setJnze(String jnze) {
		this.jnze = jnze;
	}
	public String getDwjnhj(){ 
		if(dwjnhj == null|| dwjnhj.isEmpty()){
			//dwjnhj = "-";
			return dwjnhj;
		}
		return CommUtil.formatNumberic(dwjnhj);
	}
	public void setDwjnhj(String dwjnhj) {
		this.dwjnhj = dwjnhj;
	}
	public String getGrjnhj() {
		if(grjnhj == null|| grjnhj.isEmpty()){
			//grjnhj = "-";
			return grjnhj;
		}
			
		return CommUtil.formatNumberic(grjnhj);
	}
	public void setGrjnhj(String grjnhj) {
		this.grjnhj = grjnhj;
	}
	
}
