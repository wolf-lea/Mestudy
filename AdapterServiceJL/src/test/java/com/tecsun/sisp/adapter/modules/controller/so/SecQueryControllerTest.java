package com.tecsun.sisp.adapter.modules.controller.so;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.tecsun.sisp.adapter.common.util.CommUtil;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.DictionaryUtil;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.JsonMapper;
import com.tecsun.sisp.adapter.common.util.Result;

public class SecQueryControllerTest {
	private static Logger logger = LoggerFactory.getLogger(SecQueryControllerTest.class);

	private static String URI = "http://127.0.0.1:8080/AdapterService";
	
//	/**
//	 * 个人基本参保信息
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getBasicInsuredInfo() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "个人基本参保信息查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getBasicInsuredInfo";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("个人基本参保信息查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}

	/**
	 * 参保信息险种
	 * @param bean
	 * @return
	 */
	@Test
	public void getIncureTypeList() {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "参保信息险种查询失败";
        
		JsonObject json = new JsonObject();
        //json.addProperty("sfzh", "430582199009130123");
		//json.addProperty("xm", "张三");
        json.addProperty("sfzh", "430582199009130555");
		json.addProperty("xm", "李四");
		json.addProperty("pageno", "1");
        json.addProperty("pagesize", "20");
        Object jsonData = "";
        try {
        	logger.info("================="+json.toString());
        	String CARD_CARDPROGTRSS="/adapter/rest/getIncureTypeList";
        	
            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
            
            logger.info("result:"+JsonHelper.javaBeanToJson(data));
            
            if(StringUtils.isNotEmpty(String.valueOf(data))) {
                statusCode = data.getStatusCode();
                message = data.getMessage();
                jsonData = data.getData();
            }
        } catch (Exception e) {
            logger.error("参保信息险种查询失败:",e);
        }

        assertEquals(message, statusCode, "200");
	}
	
	/**
	 * 医疗保险(MI)缴费记录基本信息
	 * @param bean
	 * @return
	 */
	@Test
	public void getMIPaymentBasicInfo() {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "医疗保险(MI)缴费记录基本信息查询失败";
		
		JsonObject json = new JsonObject();
        json.addProperty("sfzh", "430582199009130123");
        json.addProperty("xm", "张三");
        Object jsonData = "";
        try {
        	
        	String CARD_CARDPROGTRSS="/adapter/rest/getMIPaymentBasicInfo";
        	
            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
            
            logger.info("result:"+JsonHelper.javaBeanToJson(data));
            
            if(StringUtils.isNotEmpty(String.valueOf(data))) {
                statusCode = data.getStatusCode();
                message = data.getMessage();
                jsonData = data.getData();
            }
        } catch (Exception e) {
            logger.error("医疗保险(MI)缴费记录基本信息查询失败:",e);
        }

        assertEquals(message, statusCode, "200");
	}
//	
//	/**
//	 * 医疗保险(MI)缴费记录
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getMIPaymentRecord() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "医疗保险(MI)缴费记录查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        json.addProperty("ksny", "2016-08");
//        json.addProperty("jsny", "2016-09");
//        json.addProperty("pageno", "1");
//        json.addProperty("pagesize", "50");
//        
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getMIPaymentRecord";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("医疗保险(MI)缴费记录查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
	
	/**
	 * 医疗账户(MA)基本信息
	 * @param bean
	 * @return
	 */
	@Test
	public void getMABasicInfo() {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "医疗账户(MA)基本信息查询失败";
		
		JsonObject json = new JsonObject();
        json.addProperty("sfzh", "430582199009130123");
        json.addProperty("xm", "张三");
        Object jsonData = "";
        try {
        	
        	String CARD_CARDPROGTRSS="/adapter/rest/getMABasicInfo";
        	
            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
            
            logger.info("result:"+JsonHelper.javaBeanToJson(data));
            
            if(StringUtils.isNotEmpty(String.valueOf(data))) {
                statusCode = data.getStatusCode();
                message = data.getMessage();
                jsonData = data.getData();
            }
        } catch (Exception e) {
            logger.error("医疗账户(MA)基本信息查询失败:",e);
        }

        assertEquals(message, statusCode, "200");
	}

	/**
	 * 医疗账户(MA)明细
	 * @param bean
	 * @return
	 */
	@Test
	public void getMADetail() {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "医疗账户(MA)明细查询失败";
		
		JsonObject json = new JsonObject();
        json.addProperty("sfzh", "430582199009130123");
        json.addProperty("xm", "张三");
        json.addProperty("ksny", "2016-06");
        json.addProperty("jsny", "2016-09");
        json.addProperty("pageno", "1");
        json.addProperty("pagesize", "50");
        
        Object jsonData = "";
        try {
        	
        	String CARD_CARDPROGTRSS="/adapter/rest/getMADetail";
        	
            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
            
            logger.info("result:"+JsonHelper.javaBeanToJson(data));
            
            if(StringUtils.isNotEmpty(String.valueOf(data))) {
                statusCode = data.getStatusCode();
                message = data.getMessage();
                jsonData = data.getData();
            }
        } catch (Exception e) {
            logger.error("医疗账户(MA)明细查询失败:",e);
        }

        assertEquals(message, statusCode, "200");
	}
//	
//	/**
//	 * 养老保险(EI)缴费基本信息
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getEIPaymentBasicInfo() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "养老保险(EI)缴费基本信息查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getEIPaymentBasicInfo";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("养老保险(EI)缴费基本信息查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//
//	/**
//	 * 养老保险(EI)缴费记录
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getEIPaymentRecord() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "养老保险(EI)缴费记录查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        json.addProperty("ksny", "2016-08");
//        json.addProperty("jsny", "2016-09");
//        json.addProperty("pageno", "1");
//        json.addProperty("pagesize", "50");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getEIPaymentRecord";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("养老保险(EI)缴费记录查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//	
//	/**
//	 * 养老保险(EI)待遇发放基本信息
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getEIIssueBasicInfo() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "养老保险(EI)待遇发放基本信息查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getEIIssueBasicInfo";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("养老保险(EI)待遇发放基本信息查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//
//	/**
//	 * 养老保险(EI)待遇发放记录
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getEIIssueRecord() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "养老保险(EI)待遇发放记录查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        json.addProperty("ksny", "2016-08");
//        json.addProperty("jsny", "2016-09");
//        json.addProperty("pageno", "1");
//        json.addProperty("pagesize", "50");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getEIIssueRecord";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("养老保险(EI)待遇发放记录查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//	
//	/**
//	 * 生育保险(BI)缴费基本信息
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getBIPaymentBasicInfo() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "生育保险(BI)缴费基本信息查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getBIPaymentBasicInfo";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("生育保险(BI)缴费基本信息查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//
//	/**
//	 * 生育保险(BI)缴费记录
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getBIPaymentRecord() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "生育保险(BI)缴费记录查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        json.addProperty("ksny", "2016-08");
//        json.addProperty("jsny", "2016-09");
//        json.addProperty("pageno", "1");
//        json.addProperty("pagesize", "50");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getBIPaymentRecord";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("生育保险(BI)缴费记录查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//	
//	/**
//	 * 工伤保险(EII)缴费基本信息
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getEIIPaymentBasicInfo() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "工伤保险(EII)缴费基本信息查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getEIIPaymentBasicInfo";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("工伤保险(EII)缴费基本信息查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//
//	/**
//	 * 工伤保险(EII)缴纳记录
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getEIIPaymentRecord() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "工伤保险(EII)缴纳记录查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        json.addProperty("ksny", "2016-08");
//        json.addProperty("jsny", "2016-09");
//        json.addProperty("pageno", "1");
//        json.addProperty("pagesize", "50");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getEIIPaymentRecord";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("工伤保险(EII)缴纳记录查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//	
//	/**
//	 * 失业保险(UEI)缴纳基本信息
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getUEIPaymentBasicInfo() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "失业保险(UEI)缴纳基本信息查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getUEIPaymentBasicInfo";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("失业保险(UEI)缴纳基本信息查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//
//	/**
//	 * 失业保险(UEI)缴纳记录
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getUEIPaymentRecord() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "失业保险(UEI)缴纳记录查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        json.addProperty("ksny", "2016-08");
//        json.addProperty("jsny", "2016-09");
//        json.addProperty("pageno", "1");
//        json.addProperty("pagesize", "50");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getUEIPaymentRecord";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("失业保险(UEI)缴纳记录查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//	
//	/**
//	 * 城乡居民养老保险(URREI)缴纳基本信息
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getURREIPaymentBasicInfo() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "城乡居民养老保险(URREI)缴纳基本信息查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getURREIPaymentBasicInfo";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("城乡居民养老保险(URREI)缴纳基本信息查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//
//	/**
//	 * 城乡居民养老保险(URREI)缴费历史纪录
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getURREIPaymentRecord() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "城乡居民养老保险(URREI)缴费历史纪录查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        json.addProperty("pageno", "1");
//        json.addProperty("pagesize", "50");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getURREIPaymentRecord";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("城乡居民养老保险(URREI)缴费历史纪录查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//	
//	/**
//	 * 城乡居民养老保险(URREI)待遇发放基本信息
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getURREIIssueBasicInfo() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "城乡居民养老保险(URREI)待遇发放基本信息查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getURREIIssueBasicInfo";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("城乡居民养老保险(URREI)待遇发放基本信息查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
//
//	/**
//	 * 城乡居民养老保险(URREI)待遇发放记录
//	 * @param bean
//	 * @return
//	 */
//	@Test
//	public void getURREIIssueRecord() {
//		String statusCode = Constants.RESULT_MESSAGE_ERROR;
//        String message = "城乡居民养老保险(URREI)待遇发放记录查询失败";
//		
//		JsonObject json = new JsonObject();
//        json.addProperty("sfzh", "430582199009130123");
//        json.addProperty("xm", "张三");
//        json.addProperty("ksny", "2016-08");
//        json.addProperty("jsny", "2016-09");
//        json.addProperty("pageno", "1");
//        json.addProperty("pagesize", "50");
//        Object jsonData = "";
//        try {
//        	
//        	String CARD_CARDPROGTRSS="/adapter/rest/getURREIIssueRecord";
//        	
//            jsonData = DictionaryUtil.postClientRequest(json.toString(), URI+CARD_CARDPROGTRSS);
//            Result data= JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
//            
//            logger.info("result:"+JsonHelper.javaBeanToJson(data));
//            
//            if(StringUtils.isNotEmpty(String.valueOf(data))) {
//                statusCode = data.getStatusCode();
//                message = data.getMessage();
//                jsonData = data.getData();
//            }
//        } catch (Exception e) {
//            logger.error("城乡居民养老保险(URREI)待遇发放记录查询失败:",e);
//        }
//
//        assertEquals(message, statusCode, "200");
//	}
	
}
