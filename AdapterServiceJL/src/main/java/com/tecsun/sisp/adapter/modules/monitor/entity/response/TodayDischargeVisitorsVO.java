package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/*
 * 今日出院人次
 */
public class TodayDischargeVisitorsVO {
	
	private long dischargeNum;		// 出院人次
	
	private String hospitalName;	// 医院名称


	/**
	 * @return the dischargeNum
	 */
	public long getDischargeNum() {
		return dischargeNum;
	}

	/**
	 * @param dischargeNum the dischargeNum to set
	 */
	public void setDischargeNum(long dischargeNum) {
		this.dischargeNum = dischargeNum;
	}

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
	

}
