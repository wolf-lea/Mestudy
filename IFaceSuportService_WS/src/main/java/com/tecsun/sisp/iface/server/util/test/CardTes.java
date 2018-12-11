package com.tecsun.sisp.iface.server.util.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DG on 2015/10/19.
 */
public class CardTes {
    public static void main(String[] args)throws Exception{

        //String url = "http://localhost:8080/Henan";
        String url = "http://192.168.1.52:8080/Henan";
        //String url = "http://61.28.113.182:4040/Henan";
        //String url = "http://42.51.7.161:8081/Henan";

        String devId = "123456";

        JSONObject json1 = new JSONObject();
        json1.put("devId",devId);

        JSONObject json2 = new JSONObject();
        json2.put("aac002","412829198906262818");
        json2.put("aac003","张崇太");

        JSONObject json3 = new JSONObject();
        json3.put("aac002","412829198906262818");
        json3.put("aac003","张崇太");
        json3.put("aaz500","A55662979");

        /*JSONObject json4 = new JSONObject();
        json4.put("aac002","410181199011075026");
        json4.put("aac003","白萌");
*/
        //登陆
        String login = (String)getWebClient(url + "/iface/common/login",json1,String.class);
        System.out.println("登录》》》"+login);

        JSONObject jsonLogin = JSON.parseObject(login);
        String token = jsonLogin.getJSONObject("data").getString("token");

        String cardInfo = (String)getWebClient(url + "/iface/card/cardInfo" + "?devId=" + devId + "&token=" + token,json2,String.class);
        System.out.println("1》》》"+cardInfo);

        String cardStatus = (String)getWebClient(url + "/iface/card/cardStatus" + "?devId=" + devId + "&token=" + token,json2,String.class);
        System.out.println("2》》》"+cardStatus);

        String cardProgress = (String)getWebClient(url + "/iface/card/cardProgress" + "?devId=" + devId + "&token=" + token,json2,String.class);
        System.out.println("3》》》"+cardProgress);

        /*String cardLossReport = (String)getWebClient(url + "/iface/card/cardLossReport" + "?devId=" + devId + "&token=" + token,json2,String.class);
        System.out.println("4》》》"+cardLossReport);*/
    }

    public static String sendSms(String content,String telNumber) {
        String sendResult = "";
        try {
            URL url = new URL("http://192.168.1.19:8082/sms/send");
            String postData ="jsonParam=[{'content': '"+content+"', " +
                    "'mobile':'"+telNumber+"','organizationId':'-100','schoolName':'德生学校','smsType': '18','fixSupplierId':''}]";
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(4 * 1000);// 超时时间为4秒
            connection.setDoOutput(true);// 允许连接提交信息
            connection.setRequestMethod("POST");// 网页提交方式“POST”
            connection.setRequestProperty("Charset", "UTF-8");
            OutputStream os = connection.getOutputStream();
            os.write(postData.getBytes("utf-8"));
            os.close();
            os = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            sendResult = in.readLine();
            in.close();
            in = null;
        } catch (Exception e) {
            System.out.println(e);
        }
        return sendResult;
    }

    public static Object getWebClient(String url, JSONObject json, Class resultClass){
        Client client = new Client();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class,json.toString());
        return response.getEntity(resultClass);
    }

}

