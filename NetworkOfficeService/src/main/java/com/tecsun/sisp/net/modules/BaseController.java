package com.tecsun.sisp.net.modules;


import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.tecsun.sisp.net.common.Constants;
import com.tecsun.sisp.net.common.DictionaryUtil;
import com.tecsun.sisp.net.common.JedisUtil;
import com.tecsun.sisp.net.common.JsonMapper;
import com.tecsun.sisp.net.common.Result;
import com.tecsun.sisp.net.common.vo.faceverify.ResultVerify;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

/**
 * Created by zhangqingjie on 15-5-8.
 */

public abstract class BaseController {

    public Result result(String result, String message) {
        return result(result, message, null);
    }

    public Result result(String result, String message, Object obj) {
        return new Result(result, message, obj);
    }
    
    public ResultVerify resultVerify(String result, String message) {
        return resultVerify(result, message, null);
    }

    public ResultVerify resultVerify(String result, String message, Object obj) {
        return new ResultVerify(result, message, obj);
    }
    
    public ResultVerify resultVerify(String result, String message, Object obj , long total) {
        return new ResultVerify(result, message, obj , total);
    }
    
    public ResultVerify ok(long total, String message, Object obj) {
        return resultVerify(Constants.RESULT_MESSAGE_SUCCESS, message, obj , total);
    }

    public ResultVerify ok(String message, Object obj) {
        return resultVerify(Constants.RESULT_MESSAGE_SUCCESS, message, obj , 0);
    }

    public ResultVerify error(String message, Object obj) {
        return resultVerify(Constants.RESULT_MESSAGE_ERROR, message, obj);
    }
    
    /**
     * 生成32位编码
     * @return string
     */
    public static String createToken() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    /**
     * MD5加密
     * @param s
     * @return
     */
    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
