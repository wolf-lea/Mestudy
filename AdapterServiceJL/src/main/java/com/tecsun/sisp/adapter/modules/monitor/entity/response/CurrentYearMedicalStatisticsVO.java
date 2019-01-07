package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 当年就医统计
 */
public class CurrentYearMedicalStatisticsVO {
	
	private String currentMonth;		// 当前月
	
	private double settlementFee;		// 结算费用
	
	private int dischargeVisitors;		// 出院人次
	
	private int settlementVisitors;		// 结算人次

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
