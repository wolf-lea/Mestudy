package com.tecsun.sisp.adapter.modules.common.entity.request;

/**
 * 获取网点
 */
public class BranchBean extends SecQueryBean{

	private double latitude;	//纬度
	private double longitude;	//经度
	private String servicesCode;//业务编码
	
	private String orgCode;//银行编码
	private String areaCode;//区县编码

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getServicesCode() {
		return servicesCode;
	}

	public void setServicesCode(String servicesCode) {
		this.servicesCode = servicesCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	
}
