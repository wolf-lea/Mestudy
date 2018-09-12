package com.tecsun.sisp.iface.common.vo;

import java.sql.Date;

public class PersonalApply {
	
	 private String certNum;
	 private String name;
	 private String soCardNum;
	 private Date applytime;
	 private String lkwd;
	 
	 private String agentName;
	 private String rphotopath;
	 private String agentcertnum;
	 
	 
	 
	
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getRphotopath() {
		return rphotopath;
	}
	public void setRphotopath(String rphotopath) {
		this.rphotopath = rphotopath;
	}
	public String getAgentcertnum() {
		return agentcertnum;
	}
	public void setAgentcertnum(String agentcertnum) {
		this.agentcertnum = agentcertnum;
	}
	/*private String 	channelcode;	//渠道编码
	 
	 
	public String getChannelcode() {
		return channelcode;
	}
	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}*/
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSoCardNum() {
		return soCardNum;
	}
	public void setSoCardNum(String soCardNum) {
		this.soCardNum = soCardNum;
	}
	
	public Date getApplytime() {
		return applytime;
	}
	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}
	public String getLkwd() {
		return lkwd;
	}
	public void setLkwd(String lkwd) {
		this.lkwd = lkwd;
	}
	 
	 
	 
	
	
	
	

}
