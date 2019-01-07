package com.tecsun.sisp.adapter.modules.monitor.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

public class SituationQueryBean extends SecQueryBean {
	
	private String areaFlag;		// 地区标识(offSite表示异地    local表示本地)

	/**
	 * @return the areaFlag
	 */
	public String getAreaFlag() {
		return areaFlag;
	}

	/**
	 * @param areaFlag the areaFlag to set
	 */
	public void setAreaFlag(String areaFlag) {
		this.areaFlag = areaFlag;
	}
	

}
