package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 当年就医统计---结算费用
 */
public class CurrentYearSettlementFeeVO {
	
	private double settlementFee;	// 结算费用
	
	private String currentMonth;	// 当前月

	public CurrentYearSettlementFeeVO() {}

	public CurrentYearSettlementFeeVO(double settlementFee, String currentMonth) {
		this.settlementFee = settlementFee;
		this.currentMonth = currentMonth;
	}

	/**
	 * @return the settlementFee
	 */
	public double getSettlementFee() {
		return settlementFee;
	}

	/**
	 * @param settlementFee the settlementFee to set
	 */
	public void setSettlementFee(double settlementFee) {
		this.settlementFee = settlementFee;
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
		long temp;
		temp = Double.doubleToLongBits(settlementFee);
		result = prime * result + (int) (temp ^ (temp >>> 32));*/
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
		CurrentYearSettlementFeeVO other = (CurrentYearSettlementFeeVO) obj;
		if (currentMonth == null) {
			if (other.currentMonth != null) {
				return false;
			}
		} else if (!currentMonth.equals(other.currentMonth)) {
			return false;
		}
		if (Double.doubleToLongBits(settlementFee) > Double.doubleToLongBits(other.settlementFee)) {
			return false;
		}
		return true;
	}

	
}
