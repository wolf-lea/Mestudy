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
 * Created by DG on 20/8/2015.
 */
public class BirthTest {

    public static void main(String[] args)throws Exception{

        String url = "http://localhost:8080/Henan";
        //String url = "http://192.168.1.52:8080/Henan";

        String devId = "123456";

        JSONObject json1 = new JSONObject();
        json1.put("devId",devId);

        JSONObject json2 = new JSONObject();
        json2.put("personNo","410121198004190025");

        JSONObject json3 = new JSONObject();
        json3.put("personNo","410121198004190025");
        json3.put("pageNo",1);
        json3.put("pageSize",10);

        //登陆
        String login = (String)getWebClient(url + "/iface/common/login",json1,String.class);
        System.out.println("登录》》》"+login);

        JSONObject jsonLogin = JSON.parseObject(login);
        String token = jsonLogin.getJSONObject("data").getString("token");

        //生育
        String sygrjbxx = (String)getWebClient(url + "/iface/so/sygrjbxx" + "?devId=" + devId + "&token=" + token,json2,String.class);
        System.out.println("1》》》"+sygrjbxx);

        String syjfxxcx = (String)getWebClient(url + "/iface/so/syjfxxcx" + "?devId=" + devId + "&token=" + token,json3,String.class);
        System.out.println("2》》》"+syjfxxcx);

        String syylfyxxcx = (String)getWebClient(url + "/iface/so/syylfyxxcx" + "?devId=" + devId + "&token=" + token,json3,String.class);
        System.out.println("3》》》"+syylfyxxcx);

        String syjtxxcx = (String)getWebClient(url + "/iface/so/syjtxxcx" + "?devId=" + devId + "&token=" + token,json3,String.class);
        System.out.println("4》》》"+syjtxxcx);

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
