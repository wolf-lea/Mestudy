package com.tecsun.sisp.adapter.modules.monitor.entity.response;
/**
 * 今日情况
 */
public class TodaySituationVO {
	
	private HospitalSituationVO hospitalSituation;	 	// 医院情况
	
	private PharmacySituationVO pharmacySituation;		// 药店情况

	/**
	 * @return the hospitalSituation
	 */
	public HospitalSituationVO getHospitalSituation() {
		return hospitalSituation;
	}

	/**
	 * @param hospitalSituation the hospitalSituation to set
	 */
	public void setHospitalSituation(HospitalSituationVO hospitalSituation) {
		this.hospitalSituation = hospitalSituation;
	}

	/**
	 * @return the pharmacySituation
	 */
	public PharmacySituationVO getPharmacySituation() {
		return pharmacySituation;
	}

	/**
	 * @param pharmacySituation the pharmacySituation to set
	 */
	public void setPharmacySituation(PharmacySituationVO pharmacySituation) {
		this.pharmacySituation = pharmacySituation;
	}
	

}
