package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 今日情况---医院
 */
public class HospitalSituationVO {
	
	private Integer dischargeNum;	// 出院人次

	private Double consumption;		// 消费

	/**
	 * @return the dischargeNum
	 */
	public Integer getDischargeNum() {
		return dischargeNum;
	}

	/**
	 * @param dischargeNum the dischargeNum to set
	 */
	public void setDischargeNum(Integer dischargeNum) {
		this.dischargeNum = dischargeNum;
	}

	/**
	 * @return the consumption
	 */
	public Double getConsumption() {
		return consumption;
	}

	/**
	 * @param consumption the consumption to set
	 */
	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}

	
}
