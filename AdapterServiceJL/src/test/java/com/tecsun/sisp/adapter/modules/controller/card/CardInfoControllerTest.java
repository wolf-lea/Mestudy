package com.tecsun.sisp.adapter.modules.controller.card;

import static org.junit.Assert.assertEquals;

import com.tecsun.sisp.adapter.common.https.HttpClientUtil;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardInfoBean;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.DictionaryUtil;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.JsonMapper;
import com.tecsun.sisp.adapter.common.util.Result;


public class CardInfoControllerTest {

	private static Logger logger = LoggerFactory.getLogger(CardInfoControllerTest.class);

	//private static String URI = "http://127.0.0.1:8080/AdapterService";
    private static String URI = "http://127.0.0.1:81";

    //查询制卡进度
    @Test
    public void getCardProgress1(){
        try {
            String url = HttpClientUtil.getHost("/adapter/card/getCardProgress");
            CardInfoBean bean = new CardInfoBean();
            bean.setSfzh("450803199212066317");
            bean.setXm("胡丹蒙");
            String result = HttpClientUtil.getHttpData(url , bean , true);
            System.out.println("操作结果："+result);
        } catch (Exception e){
            System.out.println("错误信息："+e);
        }
    }
	@Test
	public void getCardProgress() {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "制卡进度查询失败";
		
		JsonObject json = new JsonObject();
        json.addProperty("sfzh", "430582199009130123");
        json.addProperty("xm", "张三");
        Object jsonData = "";
        try {
        	
        	String CARD_CARDPROGTRSS="/adapter/card/getCardProgress";//制卡进度查询
        	
            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
            
            logger.info("result:"+JsonHelper.javaBeanToJson(data));
            
            if(StringUtils.isNotEmpty(String.valueOf(data))) {
                statusCode = data.getStatusCode();
                message = data.getMessage();
                jsonData = data.getData();
            }
        } catch (Exception e) {
            logger.error("制卡进度查询失败:",e);
        }

        assertEquals(message, statusCode, "200");
	}
	
	@Test
	public void setLoss() {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "挂失失败";
		
		JsonObject json = new JsonObject();
		json.addProperty("sfzh", "430582199009130123");
        json.addProperty("xm", "张三");
        Object jsonData = "";
        try {
        	
        	String CARD_CARDPROGTRSS="/adapter/card/setLoss";//挂失
        	
            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
            
            logger.info("result:"+JsonHelper.javaBeanToJson(data));
            
            if(StringUtils.isNotEmpty(String.valueOf(data))) {
                statusCode = data.getStatusCode();
                message = data.getMessage();
                jsonData = data.getData();
            }
        } catch (Exception e) {
            logger.error("挂失失败:",e);
        }

        assertEquals(message, statusCode, "200");
	}

	@Test
	public void setHanging() {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "解挂失败";
		
		JsonObject json = new JsonObject();
		json.addProperty("sfzh", "430582199009130123");
        json.addProperty("xm", "张三");
        json.addProperty("sbkh", "4305821990X");
        Object jsonData = "";
        try {
        	
        	String CARD_CARDPROGTRSS="/adapter/card/setHanging";//解挂
        	
            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
            
            logger.info("result:"+JsonHelper.javaBeanToJson(data));
            
            if(StringUtils.isNotEmpty(String.valueOf(data))) {
                statusCode = data.getStatusCode();
                message = data.getMessage();
                jsonData = data.getData();
            }
        } catch (Exception e) {
            logger.error("解挂失败:",e);
        }

        assertEquals(message, statusCode, "200");
	}
	
	@Test
	public void setStart() {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "社保卡激活失败";
		
		JsonObject json = new JsonObject();
		json.addProperty("sfzh", "430582199009130123");
        json.addProperty("xm", "张三");
        json.addProperty("sbkh", "4305821990X");
        Object jsonData = "";
        try {
        	
        	String CARD_CARDPROGTRSS="/adapter/card/setStart";//社保卡激活
        	
            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
            
            logger.info("result:"+JsonHelper.javaBeanToJson(data));
            
            if(StringUtils.isNotEmpty(String.valueOf(data))) {
                statusCode = data.getStatusCode();
                message = data.getMessage();
                jsonData = data.getData();
            }
        } catch (Exception e) {
            logger.error("社保卡激活失败:",e);
        }

        assertEquals(message, statusCode, "200");
	}

}
