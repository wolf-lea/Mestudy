package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/*
 * 今日出院结算费用
 */
public class TodaySettlementFeeVO {
	
	private String hospitalName;	// 医院名称
	
	private double settlementFee;	// 结算费用

	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * @param hospitalName the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
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
	
	

}
