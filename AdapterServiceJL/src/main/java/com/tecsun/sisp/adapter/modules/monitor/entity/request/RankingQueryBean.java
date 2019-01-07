package com.tecsun.sisp.adapter.modules.monitor.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
/**
 * 当年结算排名入参
 */
public class RankingQueryBean extends SecQueryBean {
	
	private String currentYear;		// 当前年度

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

	
}
