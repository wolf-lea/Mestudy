package com.tecsun.sisp.iface.server.controller.version;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.common.util.JedisUtil;
import com.tecsun.sisp.iface.common.util.JsonMapper;
import com.tecsun.sisp.iface.common.vo.common.DistinctBean;
import com.tecsun.sisp.iface.common.vo.version.result.Result;
import com.tecsun.sisp.iface.server.util.DictionaryUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *  控制器基类
 * @author tan
 *
 */
public abstract class BaseController {


	protected  int pageno;
	protected  int pagesize;
	protected  HttpServletRequest request;

	public static String RESULT_MESSAGE_SUCCESS="200";  //成功
    public static String RESULT_MESSAGE_ERROR="0"; //失败

	public void set(HttpServletRequest request){
		this.request = request;
		this.pagesize = getIntParameter("pagesize", 15);
		this.pageno =  getIntParameter("pageno", 1);
	}

	public void set(HttpServletRequest request , Object obj){
		this.set(request);
	}
	public Result result(String result, String message) {
        return result(result, message, null);
    }

    public Result result(String result, String message, Object obj) {
        return new Result(result, message, obj);
    }
    

    public Result ok(String message, Object obj) {
        return result(RESULT_MESSAGE_SUCCESS, 0, message, obj);
    }

    public Result error(String message, Object obj) {
        return result(RESULT_MESSAGE_ERROR, message, obj);
    }
    
    /**
     * 调用接口失败，返回失败原因
     * @param message
     * @return
     */
    protected Result error(String message) {
        return result(Constants.RESULT_MESSAGE_ERROR, message, null);
    }
    
    public Result result(String result, long total, String message, Object obj) {
        return new Result(result, total, message, obj);
    }
	
    
    public Result ok(long total, String message, Object obj) {
        return result(RESULT_MESSAGE_SUCCESS, total, message, obj);
    }

	public int getIntParameter(String key , int defaultValue){
		if(key != null && !"".equals(key)){
			String str = request.getParameter(key);
			if(str!=null && !"".equals(str)){
				if(request.getParameter(key).matches("[0-9]+")){
					return Integer.parseInt(request.getParameter(key));
				}
			}
		}
		return defaultValue;
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

    /**
     * his接口统一方法
     *医院调用
     * @Title: getHisClient
     * @param  url  访问接口路径
     * @throws
     */
    public static Object getHisClient(String url, Class resultClass){
        Client client = new Client();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).
                type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

        return response.getEntity(resultClass);
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
	 * 生成32位编码
	 *
	 * @return string
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	
	/**
	 * 获取第三方接口区域
	 * @param method
	 * @param areaid
	 * @return
	 * @throws Exception 
	 */
	public String getThirdAreaId(String method , String token , String areaid) throws Exception{
		if(!StringUtils.isBlank(method)) {
			String userAreaid = Constants.USERAREAID + token;
			areaid = JedisUtil.getValue(userAreaid);
			Form form = new Form();
			form.add("distinctid", areaid);
			String url = DictionaryUtil.getHost(Constants.QUERYDISTINCT_URL);
			String result = (String) getWebClient(url, form, String.class);
			Map map = JsonMapper.buildNormalMapper().fromJson(result, Map.class);
			String statusCode = map.get("statusCode").toString();
			if(statusCode.equals("200")){
				List<DistinctBean> data = (List<DistinctBean>) map.get("result");
		    	if(data.isEmpty()) return areaid;
		    	Map<String,Object> hashMap = new HashMap<String,Object>();
		    	hashMap = (Map<String, Object>) data.get(0);
		    	areaid = hashMap.get("code").toString();
			}
			return areaid;
		}
		return areaid;
	}
	  /**
     * 调用接口失败，返回失败错误码及信息
     * @param statusCode
     * @param message
     * @return
     */
    protected Result fail(String statusCode,String message) {
        return result(statusCode,message,null);
    }

}
