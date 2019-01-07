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
import com.tecsun.sisp.adapter.modules.card.entity.request.CardReplaceBean;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年9月12日 下午4:10:47
* 说明：
*/
@Controller
@RequestMapping(value = "/adapter/cardReplace")
public class CardReplaceController extends BaseController{
	
	public final static Logger logger = Logger.getLogger(CardGrantController.class);
	  
	  //发卡系统url
	  private String faka_url = Config.getInstance().get("faka_service_url");
	  
	  //发卡系统port
	   private Integer faka_port= Integer.parseInt(Config.getInstance().get("faka_service_port"));
	   
	   /**
	     * 查询补换卡人员信息接口
	     * @param bean
	     * @return
	     */
	   @RequestMapping(value = "/getReplaceCardInfo", method = RequestMethod.POST, produces = "application/json")
	    @ResponseBody
	    public Result getReplaceCardInfo(@RequestBody CardReplaceBean bean,@Context HttpServletRequest request) {
	        logger.info("---------------CardReplaceController: getReplaceCardInfo---------------");
	        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
			
	        try {
	        	String url = faka_url+Constants.GET_REPLACE_CARDINFO;
	        	logger.info("url="+url);
	        	String result = "";
	            if(!url.startsWith("https:")){
	    		 result = (String) getWebClientExp(url, JsonUtil.getJsonGetReplaceCardInfo(bean), String.class);
	            }else{
	            result = HttpClientUtil.getDataExp(url, JsonUtil.getJsonGetReplaceCardInfo(bean).toJSONString(), true, true, faka_port).toString();
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
	            logger.error("查询补换卡人员信息失败:", e);
	        }
	        return this.error("查询补换卡人员信息失败");
	    }
	   
	   
	   /**
	     * 获取制卡数据接口
	     * @param bean
	     * @return
	     */
	   @RequestMapping(value = "/makeReplaceCard", method = RequestMethod.POST, produces = "application/json")
	    @ResponseBody
	    public Result makeReplaceCard(@RequestBody CardReplaceBean bean,@Context HttpServletRequest request) {
	        logger.info("---------------CardReplaceController: makeReplaceCard---------------");
	        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
			
	        try {
	        	String url = faka_url+Constants.MAKE_REPLACE_CARD;
	        	logger.info("url="+url);
	        	String result = "";
	            if(!url.startsWith("https:")){
	    		 result = (String) getWebClientExp(url, JsonUtil.getJsonMakeReplaceCard(bean), String.class);
	            }else{
	            result = HttpClientUtil.getDataExp(url, JsonUtil.getJsonMakeReplaceCard(bean).toJSONString(), true, true, faka_port).toString();
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
	            logger.error("获取制卡数据失败:", e);
	        }
	        return this.error("获取制卡数据失败");
	    }

	   /**
	     * (补换卡) 即制卡回盘接口
	     * @param bean
	     * @return
	     */
	   @RequestMapping(value = "/replyCardInfo", method = RequestMethod.POST, produces = "application/json")
	    @ResponseBody
	    public Result replyCardInfo(@RequestBody CardReplaceBean bean,@Context HttpServletRequest request) {
	        logger.info("---------------CardReplaceController: replyCardInfo---------------");
	        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
	        
	        if(StringUtils.isNotEmpty(bean.getPhotoUrl())){
	        	bean.setPhotoUrl(bean.getPhotoUrl()+".jpg");
	        }
			
	        try {
	        	String url = faka_url+Constants.REPLY_CARDINFO;
	        	logger.info("url="+url);
	        	String result = "";
	        	bean.setRkwd("9666604020101");
	            if(!url.startsWith("https:")){
	    		 result = (String) getWebClientExp(url, JsonUtil.getJsonReplyCardInfo(bean), String.class);
	            }else{
	            result = HttpClientUtil.getDataExp(url, JsonUtil.getJsonReplyCardInfo(bean).toJSONString(), true, true, faka_port).toString();
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
	            logger.error("即制卡回盘失败:", e);
	        }
	        return this.error("即制卡回盘失败");
	    }

	   
	   
	   /**
	     * 查询卡片是否入库接口
	     * @param bean
	     * @return
	     */
	   @RequestMapping(value = "/checkCardInfoByIdcard", method = RequestMethod.POST, produces = "application/json")
	    @ResponseBody
	    public Result checkCardInfoByIdcard(@RequestBody CardReplaceBean bean,@Context HttpServletRequest request) {
	        logger.info("---------------CardReplaceController: checkCardInfoByIdcard---------------");
	        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
			
	        try {
	        	String url = faka_url+Constants.CHECK_CARDINFO_BY_IDCARD;
	        	logger.info("url="+url);
	        	String result = "";
	            if(!url.startsWith("https:")){
	    		 result = (String) getWebClientExp(url, JsonUtil.getJsonCheckCardInfoByIdcard(bean), String.class);
	            }else{
	            result = HttpClientUtil.getDataExp(url, JsonUtil.getJsonCheckCardInfoByIdcard(bean).toJSONString(), true, true, faka_port).toString();
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
	            logger.error("查询卡片是否入库失败:", e);
	        }
	        return this.error("查询卡片是否入库失败");
	    }

}
