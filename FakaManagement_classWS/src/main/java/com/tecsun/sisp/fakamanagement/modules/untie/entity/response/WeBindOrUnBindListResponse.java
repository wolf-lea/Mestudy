package com.tecsun.sisp.fakamanagement.modules.untie.entity.response;
/**
 * 
 * @Title: 微信绑定解绑
 * @company :TECSUN
 * @developer: ZENGYUNHUA
 *	@date 2018年9月6日 下午3:16:55
 *	@version 1.0
 */
public class WeBindOrUnBindListResponse {
	
	private String statusCode;
	private String message;
	private Object data;

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	

}
