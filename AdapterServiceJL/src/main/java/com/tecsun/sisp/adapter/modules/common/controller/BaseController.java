package com.tecsun.sisp.adapter.modules.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.tecsun.sisp.adapter.common.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

/**
 *  控制器基类
 * @author tan
 *
 */
public abstract class BaseController {


	protected  int pageno;
	protected  int pagesize;
	protected  HttpServletRequest request;

	public static String RESULT_MESSAGE_SUCCESS="200";  //成功
    public static String RESULT_MESSAGE_ERROR="0"; //失败

	public void set(HttpServletRequest request){
		this.request = request;
		this.pagesize = getIntParameter("pagesize", 15);
		this.pageno =  getIntParameter("pageno", 1);
	}

	public void set(HttpServletRequest request , Object obj){
		this.set(request);
	}
	public Result result(String result, String message) {
        return result(result, message, null);
    }

    public Result result(String result, String message, Object obj) {
        return new Result(result, message, obj);
    }
    
    public Result ok(long total, String message, Object obj) {
        return result(RESULT_MESSAGE_SUCCESS, total, message, obj);
    }

	public Result ok(String message) {
		return result(RESULT_MESSAGE_SUCCESS, 0, message, null);
	}

    public Result ok(String message, Object obj) {
        return result(RESULT_MESSAGE_SUCCESS, 0, message, obj);
    }

	public Result error(String message) {
		return result(RESULT_MESSAGE_ERROR, message, null);
	}

    public Result error(String message, Object obj) {
        return result(RESULT_MESSAGE_ERROR, message, obj);
    }
    
    public Result result(String result, long total, String message, Object obj) {
        return new Result(result, total, message, obj);
    }
	

	public int getIntParameter(String key , int defaultValue){
		if(key != null && !"".equals(key)){
			String str = request.getParameter(key);
			if(str!=null && !"".equals(str)){
				if(request.getParameter(key).matches("[0-9]+")){
					return Integer.parseInt(request.getParameter(key));
				}
			}
		}
		return defaultValue;
	}


	/**
	 * iface接口统一方法
	 *
	 * @Title: getWebClient
	 * @param  url  访问接口路径
	 * @param  json 传值
	 * @param  resultClass 返回的格式
	 * @return Object    返回类型
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getWebClient(String url , JsonObject json , Class resultClass){

		ClientConfig cc = new DefaultClientConfig();
		Client client = Client.create(cc);
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(ClientResponse.class, json.toString());
		client.destroy();

		return response.getEntity(resultClass);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getWebClientExp(String url , JSONObject json , Class resultClass){

		ClientConfig cc = new DefaultClientConfig();
		Client client = Client.create(cc);
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(ClientResponse.class, json.toString());
		client.destroy();

		return response.getEntity(resultClass);
	}
	
	/**
	 *  统一form表单接口调用
	 *  1.传值form表单
	 *  2.接收JSON格式返回
	 * @param url
	 * @param form
	 * @param resultClass
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getWebClient(String url , Form form , Class resultClass ){
		ClientConfig cc = new DefaultClientConfig();
		Client client = Client.create(cc);
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		client.destroy();
		
		return response.getEntity(resultClass);
	}

	/**
	 * 生成32位编码
	 *
	 * @return string
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
}
