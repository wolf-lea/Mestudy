package com.tecsun.sisp.adapter.modules.monitor.entity.response;

/*
 * 当年结算排名
 */
public class CurrentYearSettlementRankingVO {
	
	private String cityName;			//城市名称
	
    private double settlementAmount;	//结算金额

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the settlementAmount
	 */
	public double getSettlementAmount() {
		return settlementAmount;
	}

	/**
	 * @param settlementAmount the settlementAmount to set
	 */
	public void setSettlementAmount(double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}


}
