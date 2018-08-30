package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;
import com.tecsun.sisp.adapter.common.util.CommUtil;

public class EIIssueBasicVO extends InsuranceVO {

	@Field(empty="-")  
	private String yljffzje; //	养老金发放总金额

	public String getYljffzje() {
		if(yljffzje == null || yljffzje.isEmpty())
			return yljffzje;
		
		return CommUtil.formatNumberic(yljffzje);
	}

	public void setYljffzje(String yljffzje) {
		this.yljffzje = yljffzje;
	}
}