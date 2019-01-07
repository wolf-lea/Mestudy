package com.tecsun.sisp.fakamanagement.modules.untie.entity.response;

import java.util.List;

/**
 * 
 * @Title: 微信详细出参集合
 * @company :TECSUN
 * @developer: ZENGYUNHUA
 *	@date 2018年9月5日 上午10:12:40
 *	@version 1.0
 */
public class WeChatDetailsResponseList {
	private WeChatDetailsResponseBean data;
	private String statusCode;
	private String message;
	
	
	public WeChatDetailsResponseBean getData() {
		return data;
	}
	public void setData(WeChatDetailsResponseBean data) {
		this.data = data;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
