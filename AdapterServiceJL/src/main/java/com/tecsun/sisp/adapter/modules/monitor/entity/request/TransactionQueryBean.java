package com.tecsun.sisp.adapter.modules.monitor.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
/**
 * 实时交易入参
 */
public class TransactionQueryBean extends SecQueryBean {
	
	private String areaFlag;		// 地区标识(offSite表示异地    local表示本地)
	
	private String currentTime;		// 当前时间

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

	/**
	 * @return the currentTime
	 */
	public String getCurrentTime() {
		return currentTime;
	}

	/**
	 * @param currentTime the currentTime to set
	 */
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}


}
