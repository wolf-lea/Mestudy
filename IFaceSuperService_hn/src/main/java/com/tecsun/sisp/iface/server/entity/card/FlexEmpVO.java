package com.tecsun.sisp.iface.server.entity.card;

public class FlexEmpVO{
	private String id; //主键
	private String name; //姓名
	private String idcard; //身份证号码
	private String recodsid; //档案编号
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
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getRecodsid() {
		return recodsid;
	}
	public void setRecodsid(String recodsid) {
		this.recodsid = recodsid;
	}

	
}
