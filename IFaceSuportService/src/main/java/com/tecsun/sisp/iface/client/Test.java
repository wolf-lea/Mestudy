package com.tecsun.sisp.iface.client;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.xml.rpc.ServiceException;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.tecsun.sisp.iface.common.util.JsonHelper;
import com.tecsun.sisp.iface.server.util.CommUtil;
import com.tecsun.sisp.iface.server.util.DictionaryUtil;
import com.tecsun.sisp.iface.server.util.webservice.InvokeCardService;

public class Test {

	public static void main(String[] args) throws MalformedURLException, RemoteException, ServiceException {
//		String username = CommUtil.generateValue("TSB");
//		String type = "TSB";
//		String devId = "864881020072540";
//		
//		String aac002 = "411521198912261987";
//		String aac003 = "甘经纬";
//		//登陆
//		String urllogin = DictionaryUtil.getHost("/iface/common/checkLogin");
//		JsonObject json = new JsonObject();
//		json.addProperty("username", username);
//		json.addProperty("password", username);
//		json.addProperty("type", type);
//		
//		String loginResult = (String) getWebClient(urllogin, json, String.class);
//		System.out.println(loginResult);
//		Map<String,Object> loginJsonResult = JsonHelper.jsonToMap(loginResult);
//		String token = loginJsonResult.get("data").toString();
//		
//		//生育保险个人参保信息
//		String sygrjbxxUrl = DictionaryUtil.getHost("/iface/card/cardInfo")+"?tokenId="+token+"&deviceid="+devId;
//		JsonObject json1 = new JsonObject();
//		json1.addProperty("channelcode", type);
//		json1.addProperty("deviceid", devId);
//		json1.addProperty("aac002", aac002);
//		json1.addProperty("aac003", aac003);
//		String sygrjbxxResult = (String) getWebClient(sygrjbxxUrl, json1, String.class);
//		System.out.println("sygrjbxxResult = "+sygrjbxxResult);
		
		
		
//		System.out.println(InvokeCardService.invoke("old getAC01", "41020419780227501X", "王彦涛", null,""));
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
}
