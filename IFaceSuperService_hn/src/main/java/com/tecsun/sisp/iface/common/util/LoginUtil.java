package com.tecsun.sisp.iface.common.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.representation.Form;

public class LoginUtil{
	/**
	 * 登陆验证
	 * @param url
	 * @param form
	 * @param header
	 * @return
	 */
	public static String checkLogin(String url,Form form,String header){
	    Client client = new Client();
	  //  DictionaryUtil du = new DictionaryUtil();
	    String  result = DictionaryUtil.postClient(form, url,header);
	    client.destroy();
	    return result;
	}
	
	/**
	 * 登陆成功后获取业务列表
	 * @param url
	 * @param form
	 * @param header1 为登陆时所用的header
	 * @param header2 为登陆成功后返回的code作为header2
	 * @return
	 */
	public static String getBuss(String url,Form form,String header1,String header2){
	    Client client = new Client();
	    String  result = DictionaryUtil.getClient(form, url,header1,header2);
	    client.destroy();
	    return result;
	}
}