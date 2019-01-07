package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 今日情况---药店
 */
public class PharmacySituationVO {
	
	private Double consumption;		   		// 消费
	
	private Integer purchaseOfMedicines;   // 购药人次

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

	/**
	 * @return the purchaseOfMedicines
	 */
	public Integer getPurchaseOfMedicines() {
		return purchaseOfMedicines;
	}

	/**
	 * @param purchaseOfMedicines the purchaseOfMedicines to set
	 */
	public void setPurchaseOfMedicines(Integer purchaseOfMedicines) {
		this.purchaseOfMedicines = purchaseOfMedicines;
	}


}
