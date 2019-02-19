package com.tecsun.sisp.net.modules.entity.response;

//资料
public class InformationVo {
	
	
	private long cId;
	private String iname;
	private String iurl;

	private long id;//6-21加
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getcId() {
		return cId;
	}
	public void setcId(long cId) {
		this.cId = cId;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	public String getIurl() {
		return iurl;
	}
	public void setIurl(String iurl) {
		this.iurl = iurl;
	}
	
	

}
