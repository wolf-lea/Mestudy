package com.tecsun.sisp.fakamanagement.modules.entity.result.log;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

public class LogInfoResultVO extends BaseVO {
	
	private String idcard  ;
	private String  cname ;
	private String  content;
	private String   time ;
	private String   name ;
	private String   cardid ;
	
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
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
