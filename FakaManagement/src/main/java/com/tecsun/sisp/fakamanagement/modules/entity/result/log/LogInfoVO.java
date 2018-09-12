package com.tecsun.sisp.fakamanagement.modules.entity.result.log;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

public class LogInfoVO extends BaseVO {
	
	private Integer logid  ;
	private Integer  ciid  ;
	private Integer  loginid ;
	private String  content;
	private String   time ;
	private String   name ;
	private String   cardid ;
	public Integer getLogid() {
		return logid;
	}
	public void setLogid(Integer logid) {
		this.logid = logid;
	}
	public Integer getCiid() {
		return ciid;
	}
	public void setCiid(Integer ciid) {
		this.ciid = ciid;
	}
	public Integer getLoginid() {
		return loginid;
	}
	public void setLoginid(Integer loginid) {
		this.loginid = loginid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the cardid
	 */
	public String getCardid() {
		return cardid;
	}
	/**
	 * @param cardid the cardid to set
	 */
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	
	

}
