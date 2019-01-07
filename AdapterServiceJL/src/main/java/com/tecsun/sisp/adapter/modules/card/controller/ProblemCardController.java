package com.tecsun.sisp.adapter.modules.card.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.adapter.common.https.HttpClientUtil;
import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.JsonUtil;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardProblemBean;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年1月6日 下午3:11:31
* 说明：
*/
@Controller
@RequestMapping(value = "/adapter/cardRetention")
public class ProblemCardController extends BaseController{
	
	 public final static Logger logger = Logger.getLogger(ProblemCardController.class);
	  
	  //发卡系统url
	  private String faka_url = Config.getInstance().get("faka_service_url");
	  
	  //发卡系统port
	  private Integer faka_port= Integer.parseInt(Config.getInstance().get("faka_service_port"));
	  
	  
	  /**
	     * 标记滞留卡
	     * @param bean
	     * @return
	     */
	    @RequestMapping(value = "/addRetentionCard", method = RequestMethod.POST, produces = "application/json")
	    @ResponseBody
	    public Result addRetentionCard(@RequestBody CardProblemBean bean,@Context HttpServletRequest request) {
	        logger.info("---------------CardInfoController: addRetentionCard---------------");
	        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
	      
	        if(null==bean.getIds()){
				return error("卡id为空");
			}
			Integer[] ids=bean.getIds();
			if(ids.length<=0){
				return error("卡id为空");
			}
	        try {
	        	String url =faka_url+Constants.ADD_RETENTION_CARD;
	        	logger.info("url="+url);
	        	 String result = "";
                 if(!url.startsWith("https:")){ 
	    		 result = (String) getWebClientExp(url, JsonUtil.getJsonProblemCard(bean), String.class);
                 }else{
                 result = HttpClientUtil.getDataExp(url, JsonUtil.getJsonProblemCard(bean).toJSONString(), true, true, faka_port).toString(); 
                 }
	    		logger.info("---------------"+result+"---------------");
	    		Map<String, Object> map = JsonHelper.jsonToMap(result);
	    		String code = (String) map.get("statusCode");
	    		if("200".equals(code)){
	    			return this.result(code,(Integer)map.get("total"),(String)map.get("message"),map.get("data"));
	    		}else{
	    			return this.error((String)map.get("message"));
	    		}
	           
	        } catch (Exception e) {
	            logger.error("标记滞留卡失败:", e);
	        }
	        return this.error("标记滞留卡失败");
	    }

	    /**
	     * 记录问题卡原因
	     * @param bean
	     * @return
	     */
	    @RequestMapping(value = "/addProblemCard", method = RequestMethod.POST, produces = "application/json")
	    @ResponseBody
	    public Result addProblemCard(@RequestBody CardProblemBean bean,@Context HttpServletRequest request) {
	        logger.info("---------------CardInfoController: addProblemCard---------------");
	        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
	      
	       
	        try {
	        	String url =faka_url+Constants.ADD_PROBLEM_CARD;
	        	logger.info("url="+url);
	        	 String result = "";
                 if(!url.startsWith("https:")){ 
	    		 result = (String) getWebClientExp(url, JsonUtil.getJsonRetentionCard(bean), String.class);
                 }else{
                 result = HttpClientUtil.getDataExp(url, JsonUtil.getJsonRetentionCard(bean).toJSONString(), true, true, faka_port).toString();	 
                 }
	    		logger.info("---------------"+result+"---------------");
	    		Map<String, Object> map = JsonHelper.jsonToMap(result);
	    		String code = (String) map.get("statusCode");
	    		if("200".equals(code)){
	    			return this.result(code,(Integer)map.get("total"),(String)map.get("message"),map.get("data"));
	    		}else{
	    			return this.error((String)map.get("message"));
	    		}
	           
	        } catch (Exception e) {
	            logger.error("记录问题卡原因失败:", e);
	        }
	        return this.error("记录问题卡原因失败");
	    }
	    
	    /**
	     * 查询问题卡
	     * @param bean
	     * @return
	     */
	    @RequestMapping(value = "/queryProblemCard", method = RequestMethod.POST, produces = "application/json")
	    @ResponseBody
	    public Result queryProblemCard(@RequestBody CardProblemBean bean,@Context HttpServletRequest request) {
	        logger.info("---------------CardInfoController: queryProblemCard---------------");
	        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
	    	String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
		    String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
	       
	        try {
	        	String url =faka_url+Constants.QUERY_PROBLEM_CARD+"?pagesize="
	                      +pagesize+"&pageno="+pageno;
	        	logger.info("url="+url);
	        	String result = "";
                if(!url.startsWith("https:")){ 
	    		result = (String) getWebClientExp(url, JsonUtil.getJsonRetentionCard(bean), String.class);
                }else{
                result = HttpClientUtil.getDataExp(url, JsonUtil.getJsonRetentionCard(bean).toJSONString(), true, true, faka_port).toString();	
                }
	    		logger.info("---------------"+result+"---------------");
	    		Map<String, Object> map = JsonHelper.jsonToMap(result);
	    		String code = (String) map.get("statusCode");
	    		if("200".equals(code)){
	    			return this.result(code,(Integer)map.get("total"),(String)map.get("message"),map.get("data"));
	    		}else{
	    			return this.error((String)map.get("message"));
	    		}
	           
	        } catch (Exception e) {
	            logger.error("查询问题卡失败:", e);
	        }
	        return this.error("查询问题卡失败");
	    }

	    /**
	     * 问题卡处理
	     * @param bean
	     * @return
	     */
	    @RequestMapping(value = "/handleProblemCard", method = RequestMethod.POST, produces = "application/json")
	    @ResponseBody
	    public Result handleProblemCard(@RequestBody CardProblemBean bean,@Context HttpServletRequest request) {
	        logger.info("---------------CardInfoController: handleProblemCard---------------");
	        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
	    	
	        try {
	        	String url =faka_url+Constants.HANDLE_PROBLEM_CARD;
	        	logger.info("url="+url);
	        	 String result = "";
                 if(!url.startsWith("https:")){ 
	    		 result = (String) getWebClientExp(url, JsonUtil.getJsonRetentionCard(bean), String.class);
                 }else{
                 result = HttpClientUtil.getDataExp(url, JsonUtil.getJsonRetentionCard(bean).toJSONString(), true, true, faka_port).toString(); 
                 }
	    		logger.info("---------------"+result+"---------------");
	    		Map<String, Object> map = JsonHelper.jsonToMap(result);
	    		String code = (String) map.get("statusCode");
	    		if("200".equals(code)){
	    			return this.result(code,(Integer)map.get("total"),(String)map.get("message"),map.get("data"));
	    		}else{
	    			return this.error((String)map.get("message"));
	    		}
	           
	        } catch (Exception e) {
	            logger.error("问题卡处理失败:", e);
	        }
	        return this.error("问题卡处理失败");
	    }

	    
	    /**
	     * 滞留卡转移
	     * @param bean
	     * @return
	     */
	    @RequestMapping(value = "/retentionCardChange", method = RequestMethod.POST, produces = "application/json")
	    @ResponseBody
	    public Result retentionCardChange(@RequestBody CardProblemBean bean,@Context HttpServletRequest request) {
	        logger.info("---------------CardInfoController: retentionCardChange---------------");
	        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
	    	
	        try {
	        	String url =faka_url+Constants.RETENTION_CARD_CHANGE;
	        	logger.info("url="+url);
	        	 String result = "";
                 if(!url.startsWith("https:")){
	    		 result = (String) getWebClientExp(url, JsonUtil.getJsonRetentionCardChange(bean), String.class);
                 }else{
                 result = HttpClientUtil.getDataExp(url,JsonUtil.getJsonRetentionCardChange(bean).toJSONString(), true, true, faka_port).toString();	 
                 }
                 logger.info("---------------"+result+"---------------");
	    		Map<String, Object> map = JsonHelper.jsonToMap(result);
	    		String code = (String) map.get("statusCode");
	    		if("200".equals(code)){
	    			return this.result(code,(Integer)map.get("total"),(String)map.get("message"),map.get("data"));
	    		}else{
	    			return this.error((String)map.get("message"));
	    		}
	           
	        } catch (Exception e) {
	            logger.error("滞留卡转移失败:", e);
	        }
	        return this.error("滞留卡转移失败");
	    }


}
