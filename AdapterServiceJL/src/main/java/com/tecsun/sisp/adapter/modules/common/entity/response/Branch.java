package com.tecsun.sisp.adapter.modules.common.entity.response;

/**
 * 获取网点
 */
public class Branch {

	private String id;			//网点编码
	private String name;		//网点名称
	private String address;		//网点地址
	private double latitude;	//纬度
	private double longitude;	//经度
	private String telephone;	//联系电话
	private Object distance;	//距离
	private String traffic;		//交通路线

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

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

	public Object getDistance() {
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.0");
		return distance = (Double) distance < 1 ?
				(String.valueOf((Double)distance * 1000).split("\\.")[0] + "m")
				: (df.format(distance) + "km");
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
}
