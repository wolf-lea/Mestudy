package com.tecsun.sisp.iface.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

public class test {
	 public static void main(String[] args) {
		  OutputStream os = null;
	        BufferedReader in =null;
	        String  content ="test123";
	        //String newPhoneNo ="15011918072";
	        // String newPhoneNo ="18988566633";
	       String newPhoneNo ="13060863383";
	        String organizationName ="孝感人社";
	        try {
				URL url = new URL("http://10.132.1.78:8080/sms/send");
				String postData = "jsonParam=[{'content': '"+content+"', 'mobile':'"+newPhoneNo+"', 'organizationName':'"+organizationName+"'}]";
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setConnectTimeout(4 * 1000);// 超时时间为4秒
	            connection.setDoOutput(true);// 允许连接提交信息
	            connection.setRequestMethod("POST");// 网页提交方式“POST”
	            connection.setRequestProperty("Charset", "UTF-8");
	            os = connection.getOutputStream();
	            os.write(postData.getBytes());
	           /* os.close();
	            os = null;*/
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	            String line;
	            while ((line = in.readLine()) != null)
	            	System.out.println(line);
	           /* in.close();
	            in = null;*/
			} catch (Exception e) {
				e.printStackTrace();
				//logger.error("发送短信失败！");
				System.out.println("发送短信失败");
			}finally{
			if (os != null) {
				try {
					os.close();
					os = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
			}
	}


}
