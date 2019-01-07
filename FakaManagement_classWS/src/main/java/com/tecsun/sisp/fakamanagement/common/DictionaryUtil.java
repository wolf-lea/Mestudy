package com.tecsun.sisp.fakamanagement.common;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

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

/**
 * ClassName: DictionaryUtil
 * Description:
 * Author： thl
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



}
