package com.tecsun.sisp.iface.server.util.test;

import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.tecsun.sisp.iface.common.util.JsonHelper;
import com.tecsun.sisp.iface.server.util.CommUtil;

public class Test {

	public static final String AAC002 = "411102197409250513";
	
//	public static final String AAC002 = "410105196309221030";
	
	public static final String AAC003 = "李志新";
	
	public static String DEVID = "864881021751423";
	
	public static final String PATH = "http://222.143.25.135/sisp";
	
	public static final boolean FLAG = false;
	
	public static String METHOD = "";
	
	public static void main(String[] args) {
		//1.登陆
//		JSONObject json = new JSONObject();
//		json.put("username", "hnkaifeng"); // 用户名
//		json.put("password", "625F1D3DDD81F6D52BDD95FCC4B2A6B9"); // 密码
//		json.put("channelcode", "ThirdSystem"); // 固定传值
//		String loginUrl = "http://222.143.25.135/sisp/iface/user/checkLogin";
//		//请求接口
//	    String loginResult = conn(loginUrl, json);
//	    //获取token值
//	    String token = JsonHelper.jsonToMap(loginResult).get("data").toString();//JsonHelper--这个类为解析json封装类
//	    System.out.println(token);
//	    
//	    //2.查询业务接口
//	    JSONObject json1 = new JSONObject();
//		json1.put("aac002",AAC002);
//	    String url = "http://222.143.25.135/sisp/iface/so/getEndowPayStandardPerson?tokenId="+token+"&method=getEndowPayStandardPerson";
//	    String result = conn(url, json1);
//	    System.out.println(result);
		
		if(FLAG) DEVID = "";
		String login = login();
		String token = JsonHelper.jsonToMap(login).get("data").toString();
//		String token  = "sisp_iface:token:402e987f3f974dd0b24787dca5afa635";
//	    getEndowInfoPerson(token);
//	    getEndowPayPerson(token);
//	    getEndowAccountPerson(token);
//	    getEndowAnnuityPerson(token);
//		getEndowPayStandardPerson(token);
//		cardStatus(token);
//		cardInfo(token);
//		syjfxxcx(token);
		yljfxxcx(token);
//		
//		cardProgress(token);
		
//		ydanzxxcx(token);
		
		
//		sendMessage();
	}
	
	public static String conn(String url , JSONObject json){
		 Client client = new Client();
		 WebResource webResource = client.resource(url);
		 ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
		        		.type(MediaType.APPLICATION_JSON_TYPE)
		        		.post(ClientResponse.class,json.toString());
		 return response.getEntity(String.class);
	}
	
	public static String syjfxxcx(String token){
		METHOD = "syjfxxcx";
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		 //城乡养老
        String getEndowInfoPerson = (String)getWebClient(PATH + "/iface/so/syjfxxcx" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("2》》》"+getEndowInfoPerson);
		return getEndowInfoPerson;
	}
	
	public static String yljfxxcx(String token){
		METHOD = "yljfxxcx";
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		 //城乡养老
        String getEndowInfoPerson = (String)getWebClient(PATH + "/iface/so/yljfxxcx" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("2》》》"+getEndowInfoPerson);
		return getEndowInfoPerson;
	}
	
	public static String ydanzxxcx(String token){
		METHOD = "getEndowPayPerson";
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		 //城乡养老
        String getEndowInfoPerson = (String)getWebClient(PATH + "/iface/so/ydanzxxcx" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("2》》》"+getEndowInfoPerson);
		return getEndowInfoPerson;
	}

	
	public static String login(){
		JSONObject json = new JSONObject();
		if(FLAG){
			json.put("username", "test1");
			json.put("password", "202CB962AC59075B964B07152D234B70");
			json.put("channelcode", "ThirdSystem");
		}else{
			json.put("username", CommUtil.generateValue("TSB"));
			json.put("password", CommUtil.generateValue("TSB"));
			json.put("channelcode", "TSB");
		}
		METHOD = "login";
		 //登陆
        String login = (String)getWebClient(PATH + "/iface/user/checkLogin",json,String.class);
        System.out.println("登录》》》"+login);
		return login;
	}
	
	public static String getEndowInfoPerson(String token){
		METHOD = "getEndowInfoPerson";
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		 //城乡养老
        String getEndowInfoPerson = (String)getWebClient(PATH + "/iface/so/getEndowInfoPerson" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("1》》》"+getEndowInfoPerson);
		return getEndowInfoPerson;
	}

	public static String getEndowPayPerson(String token){
		METHOD = "getEndowPayPerson";
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		 //城乡养老
        String getEndowInfoPerson = (String)getWebClient(PATH + "/iface/so/getEndowPayPerson" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("2》》》"+getEndowInfoPerson);
		return getEndowInfoPerson;
	}
	
	public static String getEndowAccountPerson(String token){
		METHOD = "getEndowAccountPerson";
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		 //城乡养老
        String getEndowInfoPerson = (String)getWebClient(PATH + "/iface/so/getEndowAccountPerson" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("3》》》"+getEndowInfoPerson);
		return getEndowInfoPerson;
	}
	
	public static String getEndowAnnuityPerson(String token){
		METHOD = "getEndowAnnuityPerson";
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		 //城乡养老
        String getEndowInfoPerson = (String)getWebClient(PATH + "/iface/so/getEndowAnnuityPerson" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("4》》》"+getEndowInfoPerson);
		return getEndowInfoPerson;
	}
	
	public static String getEndowPayStandardPerson(String token){
		METHOD = "getEndowPayStandardPerson";
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		 //城乡养老
        String getEndowInfoPerson = (String)getWebClient(PATH + "/iface/so/getEndowPayStandardPerson" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("5》》》"+getEndowInfoPerson);
		return getEndowInfoPerson;
	}
	
	public static String cardInfo(String token){
		METHOD = "cardInfo";
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		json.put("aac003",AAC003);
		 //城乡养老
        String cardInfo = (String)getWebClient(PATH + "/iface/card/cardInfo" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("6》》》"+cardInfo);
		return cardInfo;
	}
	
	public static String cardStatus(String token){
		METHOD = "cardStatus";
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		json.put("aac003",AAC003);
		 //城乡养老
        String cardInfo = (String)getWebClient(PATH + "/iface/card/cardStatus" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("7》》》"+cardInfo);
		return cardInfo;
	}
	
	public static String cardProgress(String token){
		METHOD = "cardProgress";
		JSONObject json = new JSONObject();
		json.put("aac002",AAC002);
		json.put("aac003",AAC003);
		 //城乡养老
        String cardInfo = (String)getWebClient(PATH + "/iface/card/cardProgress" + "?deviceid=" + DEVID + "&tokenId=" + token,json,String.class);
        System.out.println("9》》》"+cardInfo);
		return cardInfo;
	}
	
	
	public static String sendMessage(){
		String url = "http://sms.tecsunmall.com/sms/send";
		JSONObject json = new JSONObject();
		json.put("mobile", "18573175175");
		json.put("content", "测试1111");
		json.put("organizationName", "德生科技");
		String result = (String) getWebClient(url, json, String.class);
		System.out.println(result);
		return result;
	}
	
	
    public static Object getWebClient(String url, JSONObject json, Class resultClass){
    	if(FLAG){
    		if(url.contains("?")) url += "&method="+METHOD; else url += "?method="+METHOD;
    	}
        Client client = new Client();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
        		.type(MediaType.APPLICATION_JSON_TYPE)
        		.post(ClientResponse.class,json.toString());
        return response.getEntity(resultClass);
    }
  
}
