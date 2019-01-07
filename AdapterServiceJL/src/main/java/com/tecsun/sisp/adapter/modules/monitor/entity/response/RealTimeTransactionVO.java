package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 近24小时交易信息
 */
public class RealTimeTransactionVO {
	
	private String currentHour;			// 当前时间点
	
	private double settlementFee;		// 结算费用
	
	private int dischargeVisitors;		// 出院人次
	
	private int settlementVisitors;		// 结算人次

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

	/**
	 * @return the dischargeVisitors
	 */
	public int getDischargeVisitors() {
		return dischargeVisitors;
	}

	/**
	 * @param dischargeVisitors the dischargeVisitors to set
	 */
	public void setDischargeVisitors(int dischargeVisitors) {
		this.dischargeVisitors = dischargeVisitors;
	}

	/**
	 * @return the settlementVisitors
	 */
	public int getSettlementVisitors() {
		return settlementVisitors;
	}

	/**
	 * @param settlementVisitors the settlementVisitors to set
	 */
	public void setSettlementVisitors(int settlementVisitors) {
		this.settlementVisitors = settlementVisitors;
	}
	

}
