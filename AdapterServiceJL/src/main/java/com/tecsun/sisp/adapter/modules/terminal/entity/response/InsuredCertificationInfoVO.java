package com.tecsun.sisp.adapter.modules.terminal.entity.response;
/**
 * 参保证明（参保状态为正常参保）
 * 
 * MaternityInsurance ---> MI （生育保险）
 * LargeMedicalInsurance ---> LMI （大额保险）
 * BasicMedicalInsurance ---> BMI （基本医疗保险）
 * EmploymentInjuryInsurance ---> EII （工伤保险）
 * ResidentsMedicalInsurance ---> RMI （居民医疗保险）
 */
public class InsuredCertificationInfoVO {
	
	private String name;			// 姓名
	
	private String gender;			// 性别
	
	private String idNumber;		// 身份证号
	
	private String birthDate;		// 出生日期
		
	private String companyName;		// 单位名称
	
	private String insuredStatus;	// 参保状态
	
	private String personnelStatus;	// 人员状态
	
	private String medicalInsuranceNo;	// 医保编号
	
	private String medicalInsuranceType; // 医疗保险类型
	
	private String insuredTimeOfBMI;	 //	基本医疗保险参保时间
	
	private String paymentStartTimeOfBMI;// 基本医疗保险缴费记录开始时间
	
	private String paymentEndTimeOfBMI;	 // 基本医疗保险缴费记录结束时间
	
	private String paymentMonthOfBMI;	 // 基本医疗保险实际缴费（年）月数
	
	private String treatmentFlagOfBMI;	 // 基本医疗保险享受待遇标志
	
	private String insuredTimeOfMI;		 //	生育保险参保时间	
	
	private String paymentStartTimeOfMI; // 生育保险缴费记录开始时间
	
	private String paymentEndTimeOfMI;	 // 生育保险缴费记录结束时间
	
	private String paymentMonthOfMI;	 // 生育保险实际缴费（年）月数
	
	private String treatmentFlagOfMI;	 // 生育保险享受待遇标志
	
	private String insuredTimeOfEII;	 //	工伤保险参保时间	
	
	private String paymentStartTimeOfEII;// 工伤保险缴费记录开始时间
	
	private String paymentEndTimeOfEII;	 // 工伤保险缴费记录结束时间
	
	private String paymentMonthOfEII;	 // 工伤保险实际缴费（年）月数
	
	private String treatmentFlagOfEII;	 // 工伤保险享受待遇标志
	
	private String insuredTimeOfLMI;	 //	大额保险参保时间
	
	private String paymentStartTimeOfLMI;// 大额保险缴费记录开始时间
	
	private String paymentEndTimeOfLMI;	 // 大额保险缴费记录结束时间
	
	private String paymentMonthOfLMI;	 // 大额保险实际缴费（年）月数
	
	private String treatmentFlagOfLMI;	 // 大额保险享受待遇标志
	
	private String insuredTimeOfRMI;	 // 居民医疗保险参保时间
	
	private String paymentStartTimeOfRMI;// 居民医疗保险缴费记录开始时间
	
	private String paymentEndTimeOfRMI;	 // 居民医疗保险缴费记录结束时间
	
	private String paymentMonthOfRMI;	 // 居民医疗保险实际缴费（年）月数
	
	private String treatmentFlagOfRMI;	 // 居民医疗保险享受待遇标志
	
	private String printTime;			 // 打印时间

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
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * @return the insuredStatus
	 */
	public String getInsuredStatus() {
		return insuredStatus;
	}

	/**
	 * @param insuredStatus the insuredStatus to set
	 */
	public void setInsuredStatus(String insuredStatus) {
		this.insuredStatus = insuredStatus;
	}

	/**
	 * @return the personnelStatus
	 */
	public String getPersonnelStatus() {
		return personnelStatus;
	}

	/**
	 * @param personnelStatus the personnelStatus to set
	 */
	public void setPersonnelStatus(String personnelStatus) {
		this.personnelStatus = personnelStatus;
	}

	/**
	 * @return the medicalInsuranceNo
	 */
	public String getMedicalInsuranceNo() {
		return medicalInsuranceNo;
	}

	/**
	 * @param medicalInsuranceNo the medicalInsuranceNo to set
	 */
	public void setMedicalInsuranceNo(String medicalInsuranceNo) {
		this.medicalInsuranceNo = medicalInsuranceNo;
	}

	/**
	 * @return the medicalInsuranceType
	 */
	public String getMedicalInsuranceType() {
		return medicalInsuranceType;
	}

	/**
	 * @param medicalInsuranceType the medicalInsuranceType to set
	 */
	public void setMedicalInsuranceType(String medicalInsuranceType) {
		this.medicalInsuranceType = medicalInsuranceType;
	}

	/**
	 * @return the insuredTimeOfBMI
	 */
	public String getInsuredTimeOfBMI() {
		return insuredTimeOfBMI;
	}

	/**
	 * @param insuredTimeOfBMI the insuredTimeOfBMI to set
	 */
	public void setInsuredTimeOfBMI(String insuredTimeOfBMI) {
		this.insuredTimeOfBMI = insuredTimeOfBMI;
	}

	/**
	 * @return the paymentStartTimeOfBMI
	 */
	public String getPaymentStartTimeOfBMI() {
		return paymentStartTimeOfBMI;
	}

	/**
	 * @param paymentStartTimeOfBMI the paymentStartTimeOfBMI to set
	 */
	public void setPaymentStartTimeOfBMI(String paymentStartTimeOfBMI) {
		this.paymentStartTimeOfBMI = paymentStartTimeOfBMI;
	}

	/**
	 * @return the paymentEndTimeOfBMI
	 */
	public String getPaymentEndTimeOfBMI() {
		return paymentEndTimeOfBMI;
	}

	/**
	 * @param paymentEndTimeOfBMI the paymentEndTimeOfBMI to set
	 */
	public void setPaymentEndTimeOfBMI(String paymentEndTimeOfBMI) {
		this.paymentEndTimeOfBMI = paymentEndTimeOfBMI;
	}

	/**
	 * @return the paymentMonthOfBMI
	 */
	public String getPaymentMonthOfBMI() {
		return paymentMonthOfBMI;
	}

	/**
	 * @param paymentMonthOfBMI the paymentMonthOfBMI to set
	 */
	public void setPaymentMonthOfBMI(String paymentMonthOfBMI) {
		this.paymentMonthOfBMI = paymentMonthOfBMI;
	}

	/**
	 * @return the treatmentFlagOfBMI
	 */
	public String getTreatmentFlagOfBMI() {
		return treatmentFlagOfBMI;
	}

	/**
	 * @param treatmentFlagOfBMI the treatmentFlagOfBMI to set
	 */
	public void setTreatmentFlagOfBMI(String treatmentFlagOfBMI) {
		this.treatmentFlagOfBMI = treatmentFlagOfBMI;
	}

	/**
	 * @return the insuredTimeOfMI
	 */
	public String getInsuredTimeOfMI() {
		return insuredTimeOfMI;
	}

	/**
	 * @param insuredTimeOfMI the insuredTimeOfMI to set
	 */
	public void setInsuredTimeOfMI(String insuredTimeOfMI) {
		this.insuredTimeOfMI = insuredTimeOfMI;
	}

	/**
	 * @return the paymentStartTimeOfMI
	 */
	public String getPaymentStartTimeOfMI() {
		return paymentStartTimeOfMI;
	}

	/**
	 * @param paymentStartTimeOfMI the paymentStartTimeOfMI to set
	 */
	public void setPaymentStartTimeOfMI(String paymentStartTimeOfMI) {
		this.paymentStartTimeOfMI = paymentStartTimeOfMI;
	}

	/**
	 * @return the paymentEndTimeOfMI
	 */
	public String getPaymentEndTimeOfMI() {
		return paymentEndTimeOfMI;
	}

	/**
	 * @param paymentEndTimeOfMI the paymentEndTimeOfMI to set
	 */
	public void setPaymentEndTimeOfMI(String paymentEndTimeOfMI) {
		this.paymentEndTimeOfMI = paymentEndTimeOfMI;
	}

	/**
	 * @return the paymentMonthOfMI
	 */
	public String getPaymentMonthOfMI() {
		return paymentMonthOfMI;
	}

	/**
	 * @param paymentMonthOfMI the paymentMonthOfMI to set
	 */
	public void setPaymentMonthOfMI(String paymentMonthOfMI) {
		this.paymentMonthOfMI = paymentMonthOfMI;
	}

	/**
	 * @return the treatmentFlagOfMI
	 */
	public String getTreatmentFlagOfMI() {
		return treatmentFlagOfMI;
	}

	/**
	 * @param treatmentFlagOfMI the treatmentFlagOfMI to set
	 */
	public void setTreatmentFlagOfMI(String treatmentFlagOfMI) {
		this.treatmentFlagOfMI = treatmentFlagOfMI;
	}

	/**
	 * @return the insuredTimeOfEII
	 */
	public String getInsuredTimeOfEII() {
		return insuredTimeOfEII;
	}

	/**
	 * @param insuredTimeOfEII the insuredTimeOfEII to set
	 */
	public void setInsuredTimeOfEII(String insuredTimeOfEII) {
		this.insuredTimeOfEII = insuredTimeOfEII;
	}

	/**
	 * @return the paymentStartTimeOfEII
	 */
	public String getPaymentStartTimeOfEII() {
		return paymentStartTimeOfEII;
	}

	/**
	 * @param paymentStartTimeOfEII the paymentStartTimeOfEII to set
	 */
	public void setPaymentStartTimeOfEII(String paymentStartTimeOfEII) {
		this.paymentStartTimeOfEII = paymentStartTimeOfEII;
	}

	/**
	 * @return the paymentEndTimeOfEII
	 */
	public String getPaymentEndTimeOfEII() {
		return paymentEndTimeOfEII;
	}

	/**
	 * @param paymentEndTimeOfEII the paymentEndTimeOfEII to set
	 */
	public void setPaymentEndTimeOfEII(String paymentEndTimeOfEII) {
		this.paymentEndTimeOfEII = paymentEndTimeOfEII;
	}

	/**
	 * @return the paymentMonthOfEII
	 */
	public String getPaymentMonthOfEII() {
		return paymentMonthOfEII;
	}

	/**
	 * @param paymentMonthOfEII the paymentMonthOfEII to set
	 */
	public void setPaymentMonthOfEII(String paymentMonthOfEII) {
		this.paymentMonthOfEII = paymentMonthOfEII;
	}

	/**
	 * @return the treatmentFlagOfEII
	 */
	public String getTreatmentFlagOfEII() {
		return treatmentFlagOfEII;
	}

	/**
	 * @param treatmentFlagOfEII the treatmentFlagOfEII to set
	 */
	public void setTreatmentFlagOfEII(String treatmentFlagOfEII) {
		this.treatmentFlagOfEII = treatmentFlagOfEII;
	}

	/**
	 * @return the insuredTimeOfLMI
	 */
	public String getInsuredTimeOfLMI() {
		return insuredTimeOfLMI;
	}

	/**
	 * @param insuredTimeOfLMI the insuredTimeOfLMI to set
	 */
	public void setInsuredTimeOfLMI(String insuredTimeOfLMI) {
		this.insuredTimeOfLMI = insuredTimeOfLMI;
	}

	/**
	 * @return the paymentStartTimeOfLMI
	 */
	public String getPaymentStartTimeOfLMI() {
		return paymentStartTimeOfLMI;
	}

	/**
	 * @param paymentStartTimeOfLMI the paymentStartTimeOfLMI to set
	 */
	public void setPaymentStartTimeOfLMI(String paymentStartTimeOfLMI) {
		this.paymentStartTimeOfLMI = paymentStartTimeOfLMI;
	}

	/**
	 * @return the paymentEndTimeOfLMI
	 */
	public String getPaymentEndTimeOfLMI() {
		return paymentEndTimeOfLMI;
	}

	/**
	 * @param paymentEndTimeOfLMI the paymentEndTimeOfLMI to set
	 */
	public void setPaymentEndTimeOfLMI(String paymentEndTimeOfLMI) {
		this.paymentEndTimeOfLMI = paymentEndTimeOfLMI;
	}
	
	/**
	 * @return the paymentMonthOfLMI
	 */
	public String getPaymentMonthOfLMI() {
		return paymentMonthOfLMI;
	}

	/**
	 * @param paymentMonthOfLMI the paymentMonthOfLMI to set
	 */
	public void setPaymentMonthOfLMI(String paymentMonthOfLMI) {
		this.paymentMonthOfLMI = paymentMonthOfLMI;
	}

	/**
	 * @return the treatmentFlagOfLMI
	 */
	public String getTreatmentFlagOfLMI() {
		return treatmentFlagOfLMI;
	}

	/**
	 * @param treatmentFlagOfLMI the treatmentFlagOfLMI to set
	 */
	public void setTreatmentFlagOfLMI(String treatmentFlagOfLMI) {
		this.treatmentFlagOfLMI = treatmentFlagOfLMI;
	}

	/**
	 * @return the insuredTimeOfRMI
	 */
	public String getInsuredTimeOfRMI() {
		return insuredTimeOfRMI;
	}

	/**
	 * @param insuredTimeOfRMI the insuredTimeOfRMI to set
	 */
	public void setInsuredTimeOfRMI(String insuredTimeOfRMI) {
		this.insuredTimeOfRMI = insuredTimeOfRMI;
	}

	/**
	 * @return the paymentStartTimeOfRMI
	 */
	public String getPaymentStartTimeOfRMI() {
		return paymentStartTimeOfRMI;
	}

	/**
	 * @param paymentStartTimeOfRMI the paymentStartTimeOfRMI to set
	 */
	public void setPaymentStartTimeOfRMI(String paymentStartTimeOfRMI) {
		this.paymentStartTimeOfRMI = paymentStartTimeOfRMI;
	}

	/**
	 * @return the paymentEndTimeOfRMI
	 */
	public String getPaymentEndTimeOfRMI() {
		return paymentEndTimeOfRMI;
	}

	/**
	 * @param paymentEndTimeOfRMI the paymentEndTimeOfRMI to set
	 */
	public void setPaymentEndTimeOfRMI(String paymentEndTimeOfRMI) {
		this.paymentEndTimeOfRMI = paymentEndTimeOfRMI;
	}

	/**
	 * @return the paymentMonthOfRMI
	 */
	public String getPaymentMonthOfRMI() {
		return paymentMonthOfRMI;
	}

	/**
	 * @param paymentMonthOfRMI the paymentMonthOfRMI to set
	 */
	public void setPaymentMonthOfRMI(String paymentMonthOfRMI) {
		this.paymentMonthOfRMI = paymentMonthOfRMI;
	}

	/**
	 * @return the treatmentFlagOfRMI
	 */
	public String getTreatmentFlagOfRMI() {
		return treatmentFlagOfRMI;
	}

	/**
	 * @param treatmentFlagOfRMI the treatmentFlagOfRMI to set
	 */
	public void setTreatmentFlagOfRMI(String treatmentFlagOfRMI) {
		this.treatmentFlagOfRMI = treatmentFlagOfRMI;
	}

	/**
	 * @return the printTime
	 */
	public String getPrintTime() {
		return printTime;
	}

	/**
	 * @param printTime the printTime to set
	 */
	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}
	
	
	

}
