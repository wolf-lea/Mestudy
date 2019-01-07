package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 当年就医统计---出院人次
 */
public class CurrentYearDischargeVisitorsVO {
	
	private int discharge;			// 出院人次
	
	private String currentMonth;	// 当前月份

	public CurrentYearDischargeVisitorsVO() {}
	
	public CurrentYearDischargeVisitorsVO(int discharge, String currentMonth) {
		this.discharge = discharge;
		this.currentMonth = currentMonth;
	}
	
	/**
	 * @return the discharge
	 */
	public int getDischarge() {
		return discharge;
	}

	/**
	 * @param discharge the discharge to set
	 */
	public void setDischarge(int discharge) {
		this.discharge = discharge;
	}

	/**
	 * @return the currentMonth
	 */
	public String getCurrentMonth() {
		return currentMonth;
	}

	/**
	 * @param currentMonth the currentMonth to set
	 */
	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		/*final int prime = 31;
		int result = 1;
		result = prime * result + ((currentMonth == null) ? 0 : currentMonth.hashCode());
		result = prime * result + discharge;*/
		return currentMonth.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
			
		if (getClass() != obj.getClass()) {
			return false;
		}
		CurrentYearDischargeVisitorsVO other = (CurrentYearDischargeVisitorsVO) obj;
		if (currentMonth == null) {
			if (other.currentMonth != null) {
				return false;
			}
		} else if (!currentMonth.equals(other.currentMonth)) {
			return false;
		}
		if (discharge > other.discharge) {
			return false;
		}
		return true;
	}
	
	
}
