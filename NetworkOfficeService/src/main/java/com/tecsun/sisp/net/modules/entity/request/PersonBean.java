package com.tecsun.sisp.net.modules.entity.request;

//个人基本信息变更
public class PersonBean {
	
	
	private long id;
	
	
	private String  name; 
	private String  idcard;
	private String  phone;  
	private String  sex;   
	private String  address; 
	private String  sfzUrl; 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSfzUrl() {
		return sfzUrl;
	}

	public void setSfzUrl(String sfzUrl) {
		this.sfzUrl = sfzUrl;
	}
	
	
	

}
