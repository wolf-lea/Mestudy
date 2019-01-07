package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 近24小时交易信息---出院人次
 */
public class RealTimeDischargeVisitorsVO {
	
	private int discharge;		// 出院人次

	private String currentHour;	// 当前时间点

	public RealTimeDischargeVisitorsVO() {}

	public RealTimeDischargeVisitorsVO( String currentHour, int discharge) {
		this.discharge = discharge;
		this.currentHour = currentHour;
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
	 * @return the currentHour
	 */
	public String getCurrentHour() {
		return currentHour;
	}

	/**
	 * @param currentHour the currentHour to set
	 */
	public void setCurrentHour(String currentHour) {
		this.currentHour = currentHour;
	}

	@Override
	public int hashCode() {
		/*final int prime = 31;
		int result = 1;
		result = prime * result + ((currentHour == null) ? 0 : currentHour.hashCode());
		result = prime * result + (int) (discharge ^ (discharge >>> 32));*/
		return currentHour.hashCode();
	}

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
		RealTimeDischargeVisitorsVO other = (RealTimeDischargeVisitorsVO) obj;
		if (currentHour == null) {
			if (other.currentHour != null) {
				return false;
			}
		} else if (!currentHour.equals(other.currentHour)) {
			return false;
		}
		if (discharge > other.discharge) {
			return false;
		}
		return true;
	}

	
}
