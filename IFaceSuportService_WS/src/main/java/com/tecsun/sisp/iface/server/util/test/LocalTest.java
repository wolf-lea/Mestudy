package com.tecsun.sisp.iface.server.util.test;

import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.tecsun.sisp.iface.server.util.CommUtil;

public class LocalTest {
	
	public static final String AAC002 = "410621195407150144";
	
	public static final String AAC003 = "王学萍";
	
	public static String DEVID = "864881021638505";
	
	public static final String PATH = "http://localhost:2222/ifacesuportservice";

	public static void main(String[] args) {
		cardStatus("");
		cardProgress("");
	}
	
	public static String login(){
		JSONObject json = new JSONObject();
		json.put("username", CommUtil.generateValue("TSB"));
		json.put("password", CommUtil.generateValue("TSB"));
		json.put("channelcode", "TSB");
		 //登陆
        String login = (String)getWebClient(PATH + "/iface/user/checkLogin",json,String.class);
        System.out.println("登录》》》"+login);
		return login;
	}
	
	public static String cardStatus(String token){
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		json.put("aac003",AAC003);
		 //城乡养老
        String cardInfo = (String)getWebClient(PATH + "/iface/card/cardStatus" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("7》》》"+cardInfo);
		return cardInfo;
	}
	
	public static String cardProgress(String token){
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		json.put("aac003",AAC003);
		 //城乡养老
        String cardInfo = (String)getWebClient(PATH + "/iface/card/cardProgress" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("8》》》"+cardInfo);
		return cardInfo;
	}
	
	
	  public static Object getWebClient(String url, JSONObject json, Class resultClass){
	        Client client = new Client();
	        WebResource webResource = client.resource(url);
	        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
	        		.type(MediaType.APPLICATION_JSON_TYPE)
	        		.post(ClientResponse.class,json.toString());
	        return response.getEntity(resultClass);
	    }
}
