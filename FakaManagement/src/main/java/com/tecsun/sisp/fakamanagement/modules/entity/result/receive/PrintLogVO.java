package com.tecsun.sisp.fakamanagement.modules.entity.result.receive;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

public class PrintLogVO extends BaseVO{

	private Integer id;
	private String idcard;
	private String name;
	private String printtime;
	private Integer receivenum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrinttime() {
		return printtime;
	}
	public void setPrinttime(String printtime) {
		this.printtime = printtime;
	}
	public Integer getReceivenum() {
		return receivenum;
	}
	public void setReceivenum(Integer receivenum) {
		this.receivenum = receivenum;
	}
	
}
