package com.tecsun.sisp.adapter.common.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName: DictionaryUtil
 * Description:
 * Author： 张清洁
 * CreateTime： 2015年06月05日 12时:30分
 */
public class DictionaryUtil {

    private static Logger logger = LoggerFactory.getLogger(DictionaryUtil.class);

    public static String getHost(String uri) {
        Config config = Config.getInstance();
        return "http://" + config.get("inner_proxy_ip") + ":" +
                config.get("inner_proxy_port") + config.get("platform_context_path") + uri;
    }

    /**
     * post 请求
     * @param object 入参,json格式
     * @param resourceUrl  请求url
     * @return json格式数据
     */
    public static String postClientRequest(String object,String resourceUrl) {
        ClientConfig cc = new DefaultClientConfig();
        Client client = Client.create(cc);
        WebResource webResource = client.resource(resourceUrl);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, object);
        client.destroy();
        String result = response.getEntity(String.class);
        return result;
    }

    /**
     * post 请求
     * @param object 入参,json格式
     * @param resourceUrl  请求url
     * @return json格式数据
     */
    public static String postClientRequest(Form object,String resourceUrl) {
        ClientConfig cc = new DefaultClientConfig();
        Client client = Client.create(cc);
        WebResource webResource = client.resource(resourceUrl);
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, object);
        client.destroy();
        String result = response.getEntity(String.class);
        
        return result;
    }

    /**
     * get 请求
     * @param resourceUrl 请求URL
     * @return  json格式数据
     */
    public static String getClientRequest(String resourceUrl){
        ClientConfig cc = new DefaultClientConfig();
        Client client = Client.create(cc);
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        WebResource webResource = client.resource(resourceUrl);
        ClientResponse response = webResource.queryParams(queryParams).get(ClientResponse.class);
        client.destroy();
        String result = response.getEntity(String.class);
        return result;
    }

    /**
     * 根据大类和小类编码获取小类名称
     * @param groupId
     * @param code
     * @return
     * @throws Exception
     */
    public static String getDictName(String groupId,String code)throws Exception{
        try {
            if(StringUtils.isNotEmpty(groupId)&&StringUtils.isNotEmpty(code)) {
                String key= Constants.DICTIONGROUPKEY.replace("groupId", groupId);
                String result=JedisUtil.getValue(key);
                if(StringUtils.isNotBlank(result)) {
                    String[] dictionaryArray = result.split(",");
                    for (String bean : dictionaryArray) {
                        String[] dictbean = bean.split(":");
                        if (dictbean != null && dictbean.length == 2) {
                            if (bean.split(":")[0].equals(code)) {
                                return bean.split(":")[1];
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("字典组转换出错,groupId:" + groupId + ",code:" + code);
        }
        return "";
    }

    /**认证云专用
     * post 请求
     * @param jsondata 入参,json格式
     * @param resourceUrl  请求url
     * @return json格式数据
     */
    public static Map rzyPostClient(String jsondata,String resourceUrl) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("secureData", "\"" + jsondata + "\"");
        //使用客户的私钥签名
        String privateKeyPath = Config.getInstance().get("rzy.privatekey_path");
        String publicKeyPath = Config.getInstance().get("rzy.publickey_path");
        String clientId = Config.getInstance().get("rzy.clientid");
        Map<String, Object> datastr = new HashMap<String, Object>();
        String jsonData=null;
        try {
            File file = new File(privateKeyPath);
            if (!file.exists()) {
                datastr.put("statusCode", "-200");//私钥不存在
                datastr.put("message", "私钥文件不存在");//私钥不存在
                return datastr;
            }
            String signstr = RSAUtils.sign(jsondata, RSAUtils.loadPrivateKeyByFile(privateKeyPath));
            jsonObject.put("signature", signstr);
            UriComponentsBuilder u = UriComponentsBuilder.fromUriString(resourceUrl);
            URI u1 = u.queryParam("clientId", clientId).build().toUri();
            jsonData = DictionaryUtil.postClientRequest(jsonObject.toString(), String.valueOf(u1));
            HashMap jsonMap = JsonMapper.buildNormalMapper().fromJson(jsonData, HashMap.class);
            String secureData = (String) jsonMap.get("secureData");
            datastr = JsonMapper.buildNormalMapper().fromJson(secureData, HashMap.class);
        } catch (Exception e) {
            logger.error("认证云调用出错入参:" +jsondata);
            logger.error("认证云调用出错出参:" +jsonData);
            logger.error("认证云调用出错:" ,e);
            datastr.put("statusCode", "-400");
            datastr.put("message", "认证云调用出错");//认证云调用出错
        }
        return datastr;
    }

}
