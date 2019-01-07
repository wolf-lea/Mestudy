package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 近24小时交易信息---结算费用
 */
public class RealTimeSettlementFeeVO {
	
	private String currentHour;		// 当前时间点
	
	private double settlementFee;	// 结算费用
	
	public RealTimeSettlementFeeVO() {}
	
	public RealTimeSettlementFeeVO(String currentHour, double settlementFee) {
		this.currentHour = currentHour;
		this.settlementFee = settlementFee;
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

	@Override
	public int hashCode() {
		/*final int prime = 31;
		int result = 1;
		result = prime * result + ((currentHour == null) ? 0 : currentHour.hashCode());
		long temp;
		temp = Double.doubleToLongBits(settlementFee);
		result = prime * result + (int) (temp ^ (temp >>> 32));*/
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
		RealTimeSettlementFeeVO other = (RealTimeSettlementFeeVO) obj;
		if (currentHour == null) {
			if (other.currentHour != null) {
				return false;
			}
		} else if (!currentHour.equals(other.currentHour)) {
			return false;
		}
		if (Double.doubleToLongBits(settlementFee) > Double.doubleToLongBits(other.settlementFee)) {
			return false;
		}
		return true;
	}
	
	
}
