package com.tecsun.sisp.adapter.modules.card.entity.response;
/**
* @author  wunuanchan
* @version 
* 创建时间：2018年1月5日 下午4:50:11
* 说明：
*/

public class NetInfoVO {
	
	private String id;//主键id
	private String name;//网点名称
	private String address;//网点地址
	private String longitude;//经度
	private String latitude;//纬度
	private String telephone;//联系电话
	private String traffic;//交通路线
	private String code;//网点编码
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
	
	
	
	

}
