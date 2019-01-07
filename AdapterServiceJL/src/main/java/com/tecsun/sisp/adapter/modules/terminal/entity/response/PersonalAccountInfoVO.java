package com.tecsun.sisp.adapter.modules.terminal.entity.response;
/**
 *	个人账户信息
 */
public class PersonalAccountInfoVO {
	
	private String personalNo;				// 个人编号
	
	private String year;					// 年度
	
	private String carryOverLastYear;		// 上年结转
	
	private String currentInjection;		// 本期注入
	
	private String accountBalance;			// 账户余额
	
	private String numberOfHospitalization;	// 住院次数
	
	private String totalMeidcalFee;			// 医疗费总额
	
	private String personalAccountPayment;	// 个人账户支付
	
	private String personalCashPayment;		// 个人现金支付
	
	private String medicalCoordinationPayment;// 医疗统筹支付
	
	private String outpatientChronicPayment;// 门诊慢性病统筹支付
	
	private String generalOutpatientPayment;// 普通门诊统筹支付
	
	private String civilServantOutpatientPayment;// 公务员门诊统筹支付
	
	private String civilServantHospitalPayment;// 公务员住院统筹支付
	
	private String careExpenses;			// 照顾费统筹支付
	
	private String workInjuryOverallPayment;// 工伤统筹支付
	
	private String fertilityCoordinationPayment;// 生育统筹支付
	
	private String accumulatedResidentDirectory;// 居民目录内累计
	
	private String accumulatedStudentDirectory;// 学生目录内累计
	
	private String stateOfHospital;			// 在院状态

	/**
	 * @return the personalNo
	 */
	public String getPersonalNo() {
		return personalNo;
	}

	/**
	 * @param personalNo the personalNo to set
	 */
	public void setPersonalNo(String personalNo) {
		this.personalNo = personalNo;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the carryOverLastYear
	 */
	public String getCarryOverLastYear() {
		return carryOverLastYear;
	}

	/**
	 * @param carryOverLastYear the carryOverLastYear to set
	 */
	public void setCarryOverLastYear(String carryOverLastYear) {
		this.carryOverLastYear = carryOverLastYear;
	}

	/**
	 * @return the currentInjection
	 */
	public String getCurrentInjection() {
		return currentInjection;
	}

	/**
	 * @param currentInjection the currentInjection to set
	 */
	public void setCurrentInjection(String currentInjection) {
		this.currentInjection = currentInjection;
	}

	/**
	 * @return the accountBalance
	 */
	public String getAccountBalance() {
		return accountBalance;
	}

	/**
	 * @param accountBalance the accountBalance to set
	 */
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * @return the numberOfHospitalization
	 */
	public String getNumberOfHospitalization() {
		return numberOfHospitalization;
	}

	/**
	 * @param numberOfHospitalization the numberOfHospitalization to set
	 */
	public void setNumberOfHospitalization(String numberOfHospitalization) {
		this.numberOfHospitalization = numberOfHospitalization;
	}

	/**
	 * @return the totalMeidcalFee
	 */
	public String getTotalMeidcalFee() {
		return totalMeidcalFee;
	}

	/**
	 * @param totalMeidcalFee the totalMeidcalFee to set
	 */
	public void setTotalMeidcalFee(String totalMeidcalFee) {
		this.totalMeidcalFee = totalMeidcalFee;
	}

	/**
	 * @return the personalAccountPayment
	 */
	public String getPersonalAccountPayment() {
		return personalAccountPayment;
	}

	/**
	 * @param personalAccountPayment the personalAccountPayment to set
	 */
	public void setPersonalAccountPayment(String personalAccountPayment) {
		this.personalAccountPayment = personalAccountPayment;
	}

	/**
	 * @return the personalCashPayment
	 */
	public String getPersonalCashPayment() {
		return personalCashPayment;
	}

	/**
	 * @param personalCashPayment the personalCashPayment to set
	 */
	public void setPersonalCashPayment(String personalCashPayment) {
		this.personalCashPayment = personalCashPayment;
	}

	/**
	 * @return the medicalCoordinationPayment
	 */
	public String getMedicalCoordinationPayment() {
		return medicalCoordinationPayment;
	}

	/**
	 * @param medicalCoordinationPayment the medicalCoordinationPayment to set
	 */
	public void setMedicalCoordinationPayment(String medicalCoordinationPayment) {
		this.medicalCoordinationPayment = medicalCoordinationPayment;
	}
	
	/**
	 * @return the outpatientChronicPayment
	 */
	public String getOutpatientChronicPayment() {
		return outpatientChronicPayment;
	}

	/**
	 * @param outpatientChronicPayment the outpatientChronicPayment to set
	 */
	public void setOutpatientChronicPayment(String outpatientChronicPayment) {
		this.outpatientChronicPayment = outpatientChronicPayment;
	}

	/**
	 * @return the generalOutpatientPayment
	 */
	public String getGeneralOutpatientPayment() {
		return generalOutpatientPayment;
	}

	/**
	 * @param generalOutpatientPayment the generalOutpatientPayment to set
	 */
	public void setGeneralOutpatientPayment(String generalOutpatientPayment) {
		this.generalOutpatientPayment = generalOutpatientPayment;
	}

	/**
	 * @return the civilServantOutpatientPayment
	 */
	public String getCivilServantOutpatientPayment() {
		return civilServantOutpatientPayment;
	}

	/**
	 * @param civilServantOutpatientPayment the civilServantOutpatientPayment to set
	 */
	public void setCivilServantOutpatientPayment(String civilServantOutpatientPayment) {
		this.civilServantOutpatientPayment = civilServantOutpatientPayment;
	}

	/**
	 * @return the civilServantHospitalPayment
	 */
	public String getCivilServantHospitalPayment() {
		return civilServantHospitalPayment;
	}

	/**
	 * @param civilServantHospitalPayment the civilServantHospitalPayment to set
	 */
	public void setCivilServantHospitalPayment(String civilServantHospitalPayment) {
		this.civilServantHospitalPayment = civilServantHospitalPayment;
	}

	/**
	 * @return the careExpenses
	 */
	public String getCareExpenses() {
		return careExpenses;
	}

	/**
	 * @param careExpenses the careExpenses to set
	 */
	public void setCareExpenses(String careExpenses) {
		this.careExpenses = careExpenses;
	}

	/**
	 * @return the workInjuryOverallPayment
	 */
	public String getWorkInjuryOverallPayment() {
		return workInjuryOverallPayment;
	}

	/**
	 * @param workInjuryOverallPayment the workInjuryOverallPayment to set
	 */
	public void setWorkInjuryOverallPayment(String workInjuryOverallPayment) {
		this.workInjuryOverallPayment = workInjuryOverallPayment;
	}

	/**
	 * @return the fertilityCoordinationPayment
	 */
	public String getFertilityCoordinationPayment() {
		return fertilityCoordinationPayment;
	}

	/**
	 * @param fertilityCoordinationPayment the fertilityCoordinationPayment to set
	 */
	public void setFertilityCoordinationPayment(String fertilityCoordinationPayment) {
		this.fertilityCoordinationPayment = fertilityCoordinationPayment;
	}

	/**
	 * @return the accumulatedResidentDirectory
	 */
	public String getAccumulatedResidentDirectory() {
		return accumulatedResidentDirectory;
	}

	/**
	 * @param accumulatedResidentDirectory the accumulatedResidentDirectory to set
	 */
	public void setAccumulatedResidentDirectory(String accumulatedResidentDirectory) {
		this.accumulatedResidentDirectory = accumulatedResidentDirectory;
	}

	/**
	 * @return the accumulatedStudentDirectory
	 */
	public String getAccumulatedStudentDirectory() {
		return accumulatedStudentDirectory;
	}

	/**
	 * @param accumulatedStudentDirectory the accumulatedStudentDirectory to set
	 */
	public void setAccumulatedStudentDirectory(String accumulatedStudentDirectory) {
		this.accumulatedStudentDirectory = accumulatedStudentDirectory;
	}

	/**
	 * @return the stateOfHospital
	 */
	public String getStateOfHospital() {
		return stateOfHospital;
	}

	/**
	 * @param stateOfHospital the stateOfHospital to set
	 */
	public void setStateOfHospital(String stateOfHospital) {
		this.stateOfHospital = stateOfHospital;
	}
	
	

}
