package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 近24小时交易信息---结算人次
 */
public class RealTimeSettlementVisitorsVO {
	
	private String currentHour;		// 当前时间点
	
	private int settlementNumber;	// 结算人次
	
	public RealTimeSettlementVisitorsVO() {}

	public RealTimeSettlementVisitorsVO(String currentHour, int settlementNumber) {
		this.currentHour = currentHour;
		this.settlementNumber = settlementNumber;
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

	/**
	 * @return the settlementNumber
	 */
	public int getSettlementNumber() {
		return settlementNumber;
	}

	/**
	 * @param settlementNumber the settlementNumber to set
	 */
	public void setSettlementNumber(int settlementNumber) {
		this.settlementNumber = settlementNumber;
	}

	@Override
	public int hashCode() {
		/*final int prime = 31;
		int result = 1;
		result = prime * result + ((currentHour == null) ? 0 : currentHour.hashCode());
		result = prime * result + (int) (settlementNumber ^ (settlementNumber >>> 32));*/
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
		RealTimeSettlementVisitorsVO other = (RealTimeSettlementVisitorsVO) obj;
		if (currentHour == null) {
			if (other.currentHour != null) {
				return false;
			}
		} else if (!currentHour.equals(other.currentHour)) {
			return false;
		}
		if (settlementNumber > other.settlementNumber) {
			return false;
		}
		return true;
	}
	
	
}
