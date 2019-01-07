package com.tecsun.sisp.adapter.modules.monitor.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
/**
 * 今日出院人次、今日出院结算费用入参
 */
public class HospitalQueryBean extends SecQueryBean {
	
	private String currentDate;		// 当前日期
	
	private String areaFlag;		// 地区标识(offSite表示异地    local表示本地)
	
	/**
	 * @return the currentDate
	 */
	public String getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param currentDate the currentDate to set
	 */
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

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
