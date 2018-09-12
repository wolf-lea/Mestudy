package com.tecsun.sisp.fakamanagement.modules.entity.result.statistics;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

public class StatisticsVO extends BaseVO {
	
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
