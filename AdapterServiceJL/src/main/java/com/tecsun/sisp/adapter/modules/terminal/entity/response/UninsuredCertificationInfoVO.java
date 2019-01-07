package com.tecsun.sisp.adapter.modules.terminal.entity.response;
/**
 * 未参保证明（参保状态为未参保）
 */
public class UninsuredCertificationInfoVO {
	
	private String name;			// 姓名
	
	private String idNumber;		// 身份证号
	
	private String currentYear;		// 当前年
	
	private String currentMonth;	// 当前月
	
	private String currentDay;		// 当前日

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the currentYear
	 */
	public String getCurrentYear() {
		return currentYear;
	}

	/**
	 * @param currentYear the currentYear to set
	 */
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
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

	/**
	 * @return the currentDay
	 */
	public String getCurrentDay() {
		return currentDay;
	}

	/**
	 * @param currentDay the currentDay to set
	 */
	public void setCurrentDay(String currentDay) {
		this.currentDay = currentDay;
	}
	
	

}
