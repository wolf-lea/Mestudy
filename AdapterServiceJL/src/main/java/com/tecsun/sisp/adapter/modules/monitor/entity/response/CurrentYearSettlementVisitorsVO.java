package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 当年就医统计---结算人次
 */
public class CurrentYearSettlementVisitorsVO {
			
	private int settlementNumber;		// 结算人次
	
	private String currentMonth;		// 当前月

	public CurrentYearSettlementVisitorsVO() {}

	public CurrentYearSettlementVisitorsVO(int settlementNumber, String currentMonth) {
		this.settlementNumber = settlementNumber;
		this.currentMonth = currentMonth;
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

	@Override
	public int hashCode() {
		/*final int prime = 31;
		int result = 1;
		result = prime * result + ((currentMonth == null) ? 0 : currentMonth.hashCode());
		result = prime * result + settlementNumber;*/
		return currentMonth.hashCode();
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
		CurrentYearSettlementVisitorsVO other = (CurrentYearSettlementVisitorsVO) obj;
		if (currentMonth == null) {
			if (other.currentMonth != null) {
				return false;
			}
		} else if (!currentMonth.equals(other.currentMonth)) {
			return false;
		}
		if (settlementNumber > other.settlementNumber) {
			return false;
		}
		return true;
	}
	
	
}
