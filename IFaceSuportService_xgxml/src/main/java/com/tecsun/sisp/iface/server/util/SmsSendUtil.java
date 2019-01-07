package com.tecsun.sisp.iface.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;
import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.common.vo.SmsRequstBean;


public class SmsSendUtil{
	public static final Logger logger = Logger.getLogger(SmsSendUtil.class);
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
	  
	  public static String SmsSend(SmsRequstBean bean) {
	        String result = Constants.RESULT_MESSAGE_SUCCESS;
	        String organizationName = Constants.ORGANIZATION_NAME;
	        String smsurl = Constants.SMSSENDURL;
	        String name= bean.getName();
	        String hospitalmc = bean.getHOSPITALMC();
	        String departmentmc =bean.getDEPARTMENTMC();
	        String doctormc = bean.getDOCTORMC();
	        String regdate = bean.getRegdate();
	        String newPhoneNo = bean.getNewPhoneNo();
	        String type= bean.getType();
            String random=bean.getRandom();
	        String  content="";
	        if("1".equals(type)){
                String regtime = bean.getREGTIME();
	        	content ="尊敬的"+name+"，您的预约挂号已成功。"+" 挂号信息:"+hospitalmc+","+departmentmc+","+doctormc+",预约时间"+regdate+regtime;
	        }else if("2".equals(type)){
                String regtime = bean.getREGTIME();
	        	content ="尊敬的"+name+"，您的取消挂号已成功。"+" 挂号信息:"+hospitalmc+","+departmentmc+","+doctormc+",预约时间"+regdate+regtime;
	        }else if("3".equals(type)){
                content ="尊敬的"+name+"，您的验证码为："+random+",请于两分钟内输入验证码,请勿向他人泄露,工作人员不会以任何方式向您索要短信验证码,谨防欺诈短信";
            }
	        content =  toUtf8String(content);
	        organizationName = toUtf8String(organizationName);
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
				logger.error("发送短信失败！");
				result=Constants.RESULT_MESSAGE_ERROR;
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
	        return result;
	    }
}
