package com.tecsun.sisp.iface.server.controller.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.common.vo.Result;
import com.tecsun.sisp.iface.common.vo.SmsRequstBean;
import com.tecsun.sisp.iface.server.controller.BaseController;

@Controller
@RequestMapping(value = "/iface/sms")
public class SmsController  extends BaseController{
	public static final Logger logger = Logger.getLogger(SmsController.class);
	
	
	  @RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json")
	    @ResponseBody
	    public Result send(@RequestBody SmsRequstBean bean) {
	        String result = Constants.RESULT_MESSAGE_SUCCESS;
	        String message = "";
	        String wsResult = "";
	        String organizationName = Constants.ORGANIZATION_NAME;
	        String smsurl = Constants.SMSSENDURL;
	        try {
				organizationName= java.net.URLDecoder.decode(organizationName, "UTF-8"); 
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
	        String name= bean.getName();
	        String hospitalmc = bean.getHOSPITALMC();
	        String departmentmc =bean.getDEPARTMENTMC();
	        String doctormc = bean.getDOCTORMC();
	        String regdate = bean.getRegdate();
	        String regtime = bean.getREGTIME();
	        String newPhoneNo = bean.getNewPhoneNo();
	        String type= bean.getType();
	        String  content="";
	        if("1".equals(type)){
	        	content ="尊敬的"+name+"，您的预约挂号已成功。"+" 挂号信息:"+hospitalmc+","+departmentmc+","+doctormc+",预约时间"+regdate+regtime;
	        }else if("2".equals(type)){
	        	content ="尊敬的"+name+"，您的取消挂号已成功。"+" 挂号信息:"+hospitalmc+","+departmentmc+","+doctormc+",预约时间"+regdate+regtime;
	        }
	      
	        content =  this.toUtf8String(content);
	        organizationName = this.toUtf8String(organizationName);
	        
	        OutputStream os = null;
	        BufferedReader in =null;
	        try {
				URL url = new URL(smsurl);
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
	        return this.result(result, message, wsResult);
	    }
	  
	  
	  public static String toUtf8String(String s) {
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < s.length(); i++) {
	            char c = s.charAt(i);
	            if (c >= 0 && c <= 255) {
	                sb.append(c);
	            } else {
	                byte[] b;
	                try {
	                    b = Character.toString(c).getBytes("utf-8");
	                } catch (Exception ex) {
	                	logger.info("转码失败");
	                    b = new byte[0];
	                }
	                for (int j = 0; j < b.length; j++) {
	                    int k = b[j];
	                    if (k < 0)
	                        k += 256;
	                    sb.append("%" + Integer.toHexString(k).toUpperCase());
	                }
	            }
	        }
	        return sb.toString();
	  } 
	  
	
	  
	  
	  
	  
	  
	 
	  	
	  	/**
	  	 * 注册
	  	 * 
	  	 * 2018/05/4
	  	 */
		      




}
