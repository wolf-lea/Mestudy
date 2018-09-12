package com.tecsun.sisp.fakamanagement.modules.untie.entity.response;

import java.util.List;


/**
 * 
 * @Title: 绑定解绑出参列表集合
 * @company :TECSUN
 * @developer: ZENGYUNHUA
 *	@date 2018年9月5日 下午5:03:12
 *	@version 1.0
 */
public class UnbindOrBindResponseList {
	
	private UnbindOrBindResponseBean data;
	private String statusCode;
	private String message;
  
	
	public void setData(UnbindOrBindResponseBean data) {
		this.data = data;
	}

	public UnbindOrBindResponseBean getData() {
		return data;
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
