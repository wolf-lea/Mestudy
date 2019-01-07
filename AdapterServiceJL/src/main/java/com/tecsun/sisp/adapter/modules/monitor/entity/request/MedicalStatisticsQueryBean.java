package com.tecsun.sisp.adapter.modules.monitor.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
/**
 * 当年就医统计入参
 */
public class MedicalStatisticsQueryBean extends SecQueryBean{
	
	private String currentYear;		// 当前时间年份

	private String areaFlag;		// 地区标识(offSite表示异地    local表示本地)
	
	/**
	 * @return the currentYear
	 */
	public String getCurrentYear() {
		return currentYear;
	}

	/**
	 * @param currentYear the currentYear to set
	 */
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
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
