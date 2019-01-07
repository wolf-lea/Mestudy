package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/*
 * 医院实时结算情况（费用明细）
 */
public class MedicalFeeDetailVO {
	
	 private String hospitalProjectName;// 医院项目名称
	 
	 private double unitPrice;			// 单价
	 
	 private String quantity;			// 数量
	 
	 private double amount;				// 金额
	 
	 private double totalCost;			// 费用总计
	 

	/**
	 * @return the hospitalProjectName
	 */
	public String getHospitalProjectName() {
		return hospitalProjectName;
	}

	/**
	 * @param hospitalProjectName the hospitalProjectName to set
	 */
	public void setHospitalProjectName(String hospitalProjectName) {
		this.hospitalProjectName = hospitalProjectName;
	}
	
	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the totalCost
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}	 
	 
	 
}
