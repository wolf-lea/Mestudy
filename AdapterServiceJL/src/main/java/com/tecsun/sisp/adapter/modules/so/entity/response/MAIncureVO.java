package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;
import com.tecsun.sisp.adapter.common.util.CommUtil;

public class MAIncureVO{

	@Field(empty="-")  
	private String grybzhye; //个人医保账户余额

	public String getGrybzhye() {
		if(grybzhye == null || grybzhye.isEmpty())
			return grybzhye;
		
		return CommUtil.formatNumberic(grybzhye);
	}

	public void setGrybzhye(String grybzhye) {
		this.grybzhye = grybzhye;
	}
	
}
