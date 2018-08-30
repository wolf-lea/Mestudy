package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;

public class EIPayRecordVO extends FiveInsuranceRecordVO {

	@Field(empty="无")  
	private String xz;	//险种

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}
	
}