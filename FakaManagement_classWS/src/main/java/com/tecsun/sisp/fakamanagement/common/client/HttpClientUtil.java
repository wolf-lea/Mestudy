package com.tecsun.sisp.fakamanagement.common.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.JsonMapper;

/**
 * ClassName: HttpClientUtil
 * Description:
 * Author： 张清洁
 * CreateTime： 2015年06月09日 12时:09分
 */
public class HttpClientUtil {

    private static final String APPLICATION_JSON = "application/json";

    public  static  String getHost(String uri){
        Config config= Config.getInstance();
        return  "http://"+config.get("inner_proxy_ip")+":"+
                config.get("inner_proxy_port")+config.get("platform_context_path")+uri;
    }

    /**
     * 获取Http请求中的JSON数据
     * @param url 请求的路径
     * @param bean 请求的数据
     * @param isPost 是否是POST （true：Post false：GET）
     * @return SON数据
     * @throws Exception
     */
    public  static  String getHttpData(String url,Object bean,boolean isPost) throws  Exception{
        return  getData(url,bean,isPost,false,0);
    }

    /**
     * 获取Https请求中的JSON数据
     * @param url 请求的路径
     * @param bean 请求的数据
     * @param isPost 是否是POST （true：Post false：GET）
     * @return SON数据
     * @throws Exception
     */
    public  static  String getHttpsData(String url,Object bean,boolean isPost,int port) throws  Exception{
        return  getData(url,bean,isPost,true,port);
    }

    public static final String getData(String url, Object bean, boolean isPost,boolean isVerify,int port) throws Exception {
        String result = null;
        HttpResponse response = null;
        String json = JsonMapper.buildNonEmptyMapper().toJson(bean);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        if (isVerify) {
            httpClient = new SSLClient(port);
        }
        if (isPost) {
            response = httpClient.execute(doPost(url, json));
        } else {
            response = httpClient.execute(doGet(url));
        }
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, HTTP.UTF_8);
            }
        }
        return result;
    }

    /**
     * POST请求
     * @param url 请求的路径
     * @param encoderJson
     * @return
     * @throws Exception
     */
    static HttpPost doPost(String url, String encoderJson) throws Exception {
        StringEntity se = new StringEntity(encoderJson, "UTF-8");
        se.setContentType(APPLICATION_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        httpPost.setEntity(se);
        return httpPost;
    }

    /**
     * GET请求
     * @param url 请求的路径
     * @return GET请求响应值
     */
    private static HttpGet doGet(String url) throws Exception{
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        return httpGet;
    }
}
