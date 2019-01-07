package com.tecsun.sisp.adapter.modules.card.controller;

import com.google.gson.JsonObject;
import com.tecsun.sisp.adapter.common.https.HttpClientUtil;
import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.card.entity.request.*;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardStoreInfoVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CsspApplyInfoVo;
import com.tecsun.sisp.adapter.modules.card.entity.response.CsspCardPickUpVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.FastCardVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.NetInfoVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.VerifyRecordVo;
import com.tecsun.sisp.adapter.modules.card.service.impl.CardGrantServiceImpl;
//import com.tecsun.sisp.adapter.modules.card.service.impl.CardInfoServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.request.PersonBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.common.entity.response.DictionaryVO;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;


import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
* @author  wunuanchan
* @version 
* 创建时间：2017年12月19日 上午9:43:20
* 说明：
*/

/**
 * 
 * 社保卡制卡进度查询
 */
@Controller
@RequestMapping(value = "/adapter/card")
public class CardInfoController extends BaseController {

    public final static Logger logger = Logger.getLogger(CardInfoController.class);

    private String user = Config.getInstance().get("card.user");
    private String password = Config.getInstance().get("card.pwd");

    //像素公司接口url
    public static String PIXEL_URL = Config.getInstance().get("pixel_url");
    //社保卡申请是否允许重复申请
    public static String CARD_APPLY_IS_RE = Config.getInstance().get("card_apply_is_re");
    
    //发卡系统url
    private String faka_url = Config.getInstance().get("faka_service_url");
    //发卡系统port
    private Integer faka_port= Integer.parseInt(Config.getInstance().get("faka_service_port"));
   


    

   /* @Autowired
    CardInfoServiceImpl cardInfoService;*/
    @Autowired
    private CommServiceImpl commService;
    
    @Autowired
	private CardGrantServiceImpl cardGrantService;

    /**
     * 社保卡制卡进度查询
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getCardProgress", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardProgress(@RequestBody CardInfoBean bean)  {
        logger.info("---------------CardInfoController: getCardProgress---------------");
        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

        try {
            if (StringUtils.isNotEmpty(bean.getSfzh()) && StringUtils.isNotEmpty(bean.getXm())) {
                // 查询之前是否在平台申请
              /*  List<CsspApplyBean> personList = cardInfoService.isExistApplyPersonInfo4Other(bean.getXm(), bean.getSfzh());
                if(personList != null && !personList.isEmpty()){
                    //存在记录 并且未审核
                    if (personList.get(0) != null && ("0".equals(personList.get(0).getShbz()))) {
                        String date = personList.get(0).getCreateTime();
                        Map<String, String> map = new HashMap<>();
                        map.put("AAZ500", "");
                        map.put("AAC002", bean.getSfzh());
                        map.put("AAC003", bean.getXm());
                        map.put("APPLYTIME", date.split("\\.")[0]);
                        map.put("BANKTIME0", "");
                        map.put("BANKFINISHTIME0", "");
                        map.put("INSURETIME", "");
                        map.put("INSUREFINISHTIME", "");
                        map.put("PROVINCETIME", "");
                        map.put("CITYTIME", "");
                        map.put("GETTIME", "");
                        return result(Constants.RESULT_MESSAGE_SUCCESS, "制卡进度查询成功", map);
                    }
                }*/

                // 调用卡管9.3接口入参
                String idCard = bean.getSfzh();//身份证号
                String name = bean.getXm();//姓名
                String cityCode = idCard.substring(0, 4) + "00";//所属城市编码
                // 组装入参格式
                String[] param = {user, password, idCard, name, cityCode};
                // 调用卡管9.3接口
                Map<String, String> map = InvokeUtil.invoke("getAZ03", param);
                // 系统按aab301+aac002进行查询，然后比对aac003姓名，返回类XML串，
                // 当<ERR>域返回“OK”时信息有效，否则<ERR>域为出错信息。
                // <VALIDTAG>域为负数时表示制卡失败，<REMARKS>中是失败原因。
                if (map.containsKey("ERR")) {
                    if (Constants.CARD_ERR_OK.equals(map.get("ERR"))) {
                    	// 判断返回的数据中城市是否是吉林市
                    	if ( !"220200".equals(map.get("AAB301")) && !"220001".equals(map.get("AAB301")) ) {
                    		String city = DictionaryUtil.getDictName(Constants.AAB301, map.get("AAB301"));
                    		StringBuilder builder = new StringBuilder();
                    		builder.append("您的社会保障卡采集信息地址为 【");
                    		builder.append(city);
                    		builder.append("】，请您联系【");
                    		builder.append(city);
                    		builder.append("】人力资源和社会保障信息中心");
                    		return this.error(builder.toString());  	   
                    	}
                    	String bankServiceInfo =  DictionaryUtil.getDictName("BANK_SERVICE_INFO",map.get("AAE008"));
                        if ("-".equals(map.get("VALIDTAG").substring(0, 1))) {
                        	Map card = fieldConvertingByCardProgress(map);
                            FastCardVO vo = JsonHelper.mapToJavaBean(card, FastCardVO.class);
                            String cardFailReason = map.get("REMARKS");
                            if (cardFailReason != null) {
                        		vo.setSolveWay(DictionaryUtil.getDictName(Constants.CARD_FAIL, cardFailReason));
                        	}
                            vo.setIsPrint("0");vo.setIsPush("0");vo.setIsTake("否");
                            vo.setBankServiceInfo(bankServiceInfo);
                            return this.result(Constants.RESULT_MESSAGE_SUCCESS, map.get("REMARKS"),vo);
                        }
                   String AAE008 = map.get("AAE008");     
                   Map card = fieldConvertingByCardProgress(map);
                    FastCardVO vo = JsonHelper.mapToJavaBean(card, FastCardVO.class);
                    vo.setBankServiceInfo(bankServiceInfo);
                   // 调用卡管9.3接口入参  --结束

                  //判断是否限制银行
                	List<DictionaryVO> dictVO = commService.isExitBank4Sisp(AAE008);
                	if(dictVO.size()>0){
                        //非限制银行
                		//判断是否发行系统有数据     		
                		//调用发行系统查询是否有数据  --开始   
                        String url = faka_url+Constants.QUERY_CARD_STORE+"?pagesize=10&pageno=1";
                        CardGrantBean grantBean  = new CardGrantBean();
                        grantBean.setIdcard(idCard); grantBean.setName(name); 
                        String result = "";
                        if(!url.startsWith("https:")){ 
                        result = (String) getWebClientExp(url, JsonUtil.getJsonCardStore(grantBean), String.class);
                        }else{
                        result = HttpClientUtil.getDataExp(url, JsonUtil.getJsonCardStore(grantBean).toJSONString(), true, true, faka_port).toString();
                         }
                   		logger.info("---------------"+result+"---------------");
                   		Map<String, Object> maps = JsonHelper.jsonToMap(result);
                   		String code = (String) maps.get("statusCode");
                   		List<CardStoreInfoVO> list = null;
                   		String data = JsonMapper.buildNormalMapper().toJson(maps.get("data"));
                   		if("200".equals(code)){
                   			if(StringUtils.isNotEmpty(data)){
                   				 list =(List<CardStoreInfoVO>) JsonHelper.jsonToList(data,CardStoreInfoVO.class);
                   				 String fkwd = "";
                   				if(list.size()>0){
                   					fkwd = list.get(0).getFkwd();
                   				}
                   				 if(list.size()>0&&!"123456".equals(fkwd)){
                                      //发行系统有数据  
                                     	 if(StringUtils.isNotEmpty(list.get(0).getFkwd())){
                       						 NetInfoVO voNet = cardGrantService.queryNetInfo4Cssp(list.get(0).getFkwd());//查网点地址
                       						 NetInfoVO nameNet = cardGrantService.queryOrgInfo4Sisp(list.get(0).getFkwd());//查网点名称
                       						 StringBuffer netName = new StringBuffer();
                       						 if(nameNet!=null){
                       							netName.append(vo.getAAE008());	
                          					    netName.append("【");
                          					    netName.append(nameNet.getName()==null?"":nameNet.getName()+"】");		 
                          						vo.setNetName(netName.toString()); 
                       						 }			 
                       						 if(voNet!=null){ 
                       						 vo.setNetAdress(voNet.getAddress()==null?"":voNet.getAddress());
                       						 vo.setTelephone(voNet.getTelephone()==null?"":voNet.getTelephone());
                       						 vo.setLongitude(voNet.getLongitude()==null?"":voNet.getLongitude());
                       						 vo.setLatitude(voNet.getLatitude()==null?"":voNet.getLatitude());
                       						 }else{
                       						 vo.setNetAdress("暂无网点地址，请联系银行客服");
                       						 }
                       					 }
                                     	 if(list.get(0).getStatus()==7||list.get(0).getStatus()==1){ 
                                     		 vo.setIsPrint("0");
                                     	 }else {
                                     		 vo.setIsTake("是");
                                     	     vo.setIsPrint("1");
                                     	 }
                                     	 if(list.get(0).getStatus()==2) vo.setIsPush("1");else vo.setIsPush("0");
                                     	 vo.setRkwz(list.get(0).getCfwz());
                                     	 vo.setStatus(list.get(0).getStatus());
                                     	 vo.setId(list.get(0).getId());
                                     	 vo.setCcid(list.get(0).getCcid());

                                     }else{
                		//发行系统无数据，查询卡管9.4接口    
                        // 组装入参格式
                        String[] params = {user, password, idCard, name, cityCode};
                        // 调用第三方接口
                        Map<String, String> mapI = InvokeUtil.invoke("getAC01", params);
                        // 系统按aab301+aac002进行查询，然后比对aac003姓名，返回类XML串，
                        // 当<ERR>域返回“OK”时信息有效，否则<ERR>域为出错信息。
                        if (mapI.containsKey("ERR")) {
                            if (Constants.CARD_ERR_OK.equals(mapI.get("ERR"))) {
                            	 if(!"".equals(vo.getCITYTIME())){
                            	 StringBuffer netName = new StringBuffer();
          					     netName.append(vo.getAAE008());	
          					     netName.append("【");	
                            	 String AAB004 = mapI.get("AAB004");
                            	 netName.append(AAB004+"】");
                            	 vo.setNetName(netName.toString());
          						 vo.setNetAdress("请与本单位劳资人员联系"); 	 
                            	 }
                            	 vo.setIsPrint("0");vo.setIsPush("0");vo.setIsTake("否");
                            } else {
                                return this.result(Constants.RESULT_MESSAGE_SUCCESS, mapI.get("ERR"));
                            }
                        }
                          }
                   			}
                   	}else{
                  			return this.error((String)maps.get("message"));
                  		}
                	}else{
                		//限制银行
                		vo.setIsPrint("0");vo.setIsPush("0");
                    	 vo.setNetName(vo.getAAE008());
                	}
                   		 return this.result(Constants.RESULT_MESSAGE_SUCCESS, "制卡进度查询成功", vo);
                    
                 }else {
                         return this.result(Constants.RESULT_MESSAGE_SUCCESS, map.get("ERR"));
                       }
                }
                  }else {
                        return this.result(Constants.RESULT_MESSAGE_PARAM, "身份证号或姓名不能为空");
                    }
            } catch (Exception e) {
                    logger.error("制卡进度查询失败:", e);
                }
                return this.error("制卡进度查询失败");
     }
    
    
    /**
     * 卡数据查询
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getCardInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardInfo(@RequestBody CardInfoBean bean) {
        logger.info("---------------CardInfoController: getCardInfo---------------");
        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

        try {
            if (StringUtils.isNotEmpty(bean.getSfzh()) && StringUtils.isNotEmpty(bean.getXm())) {
               
                // 调用第三方接口入参
                String idCard = bean.getSfzh();//身份证号
                String name = bean.getXm();//姓名
                String cityCode = idCard.substring(0, 4) + "00";//所属城市编码
                // 组装入参格式
                String[] param = {user, password, idCard, name, cityCode};
                // 调用第三方接口
                Map<String, String> map = InvokeUtil.invoke("getAC01", param);
                // 系统按aab301+aac002进行查询，然后比对aac003姓名，返回类XML串，
                // 当<ERR>域返回“OK”时信息有效，否则<ERR>域为出错信息。
                if (map.containsKey("ERR")) {
                    if (Constants.CARD_ERR_OK.equals(map.get("ERR"))) {
                    	Map card = convertCardGetMessage(map);
                        return this.result(Constants.RESULT_MESSAGE_SUCCESS, "卡数据查询成功", card);
                    } else {
                        return this.result(Constants.RESULT_MESSAGE_SUCCESS, map.get("ERR"));
                    }
                }
            } else {
                return this.result(Constants.RESULT_MESSAGE_PARAM, "身份证号或姓名不能为空");
            }
        } catch (Exception e) {
            logger.error("卡数据查询失败:", e);
        }
        return this.error("卡数据查询失败");
    }
    
    
    /**
     * 制卡信息（包括照片）
     * @param bean
     * @return
     */
    @RequestMapping(value = "/cardGetMessage", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result cardGetMessage(@RequestBody CardInfoBean bean) {
        logger.info("---------------CardInfoController: cardGetMessage---------------");
        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

        try {
            if (StringUtils.isNotEmpty(bean.getSfzh()) && StringUtils.isNotEmpty(bean.getXm())) {
               
                // 调用第三方接口入参
                String idCard = bean.getSfzh();//身份证号
                String name = bean.getXm();//姓名
                String cityCode = idCard.substring(0, 4) + "00";//所属城市编码
                // 组装入参格式
                String[] param = {user, password, idCard, name, cityCode};
                // 调用第三方接口
                Map<String, String> map = InvokeUtil.invoke("getData", param);
                // 系统按aab301+aac002进行查询，然后比对aac003姓名，返回类XML串，
                // 当<ERR>域返回“OK”时信息有效，否则<ERR>域为出错信息。
                // <VALIDTAG>域为负数时表示制卡失败，<REMARKS>中是失败原因。
                if (map.containsKey("ERR")) {
                    if (Constants.CARD_ERR_OK.equals(map.get("ERR"))) {
                    	Map card = convertCardGetMessage(map);
                        return this.result(Constants.RESULT_MESSAGE_SUCCESS, "制卡信息查询成功", card);
                    } else {
                        return this.result(Constants.RESULT_MESSAGE_SUCCESS, map.get("ERR"));
                    }
                }
            } else {
                return this.result(Constants.RESULT_MESSAGE_PARAM, "身份证号或姓名不能为空");
            }
        } catch (Exception e) {
            logger.error("制卡信息查询失败:", e);
        }
        return this.error("制卡信息查询失败");
    }
    
    
    /**
     * 社保卡激活setStart
     */
    @RequestMapping(value = "/setStart", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result setStart(@RequestBody CardInfoBean bean) throws Exception {

        logger.info("---------------CardInfoController: setStart---------------");
        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

        if (StringUtils.isEmpty(bean.getSfzh()) && StringUtils.isEmpty(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "身份证号或姓名不能为空");
        }
        // 调用第三方接口入参
        String idCard = bean.getSfzh();//身份证号
        String name = bean.getXm();//姓名
        String cityCode = idCard.substring(0, 4) + "00";//所属城市编码
        String sbkh = null;//社保卡号

        //获取社保卡号与城市代码
        String[] param = {user, password, idCard, name, cityCode};
        try {
            Map<String, String> map = InvokeUtil.invoke("getAC01", param);
            if (map.containsKey("ERR")) {
                if (Constants.CARD_ERR_OK.equals(map.get("ERR"))) {
                    sbkh = map.get("AAZ500");//社保卡
                    cityCode = map.get("AAB301");//城市代码
                } else {
                    return this.result(Constants.RESULT_MESSAGE_ERROR, map.get("ERR"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return error("获取基本信息失败");
        }
        try {
            // 临时挂失入参
            String val = "<操作*>领卡启用</操作*>" + "<用户名*>" + user + "</用户名*>" +
                    "<密码*>" + password + "</密码*>" + parser("所属城市*", cityCode) +
                    parser("社保卡号*", sbkh) + parser("社会保障号码*", idCard) + parser("姓名*", name);
            // 调用临时挂失接口
            Map<String, String> res = InvokeUtil.invoke("allDsjk", new String[]{val});
            if (Constants.CARD_ERR_00.equals(res.get("ERR"))) {
                return this.result(Constants.RESULT_MESSAGE_SUCCESS, "社保卡激活成功");
            } else if (Constants.CARD_ERR_01.equals(res.get("ERR"))) {
                return this.result(Constants.RESULT_MESSAGE_ERROR, "此社保卡已激活，无需重复激活！");
            } else {
                return this.result(Constants.RESULT_MESSAGE_ERROR, res.get("ERR"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("社保卡激活异常", e);
        }
        return this.error("社保卡激活失败");
    }
    
    /**
     * 解除挂失setHanging
     */
    @RequestMapping(value = "/setHanging", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result setHanging(@RequestBody CardInfoBean bean) throws Exception {

        logger.info("---------------CardInfoController: setHanging---------------");
        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

        if (StringUtils.isEmpty(bean.getSfzh()) && StringUtils.isEmpty(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "身份证号或姓名不能为空");
        }
        // 调用第三方接口入参
        String idCard = bean.getSfzh();//身份证号
        String name = bean.getXm();//姓名
        String cityCode = idCard.substring(0, 4) + "00";//所属城市编码
        String sbkh = null;//社保卡号

        //获取社保卡号与城市代码
        String[] param = {user, password, idCard, name, cityCode};
        try {
            Map<String, String> map = InvokeUtil.invoke("getAC01", param);
            if (map.containsKey("ERR")) {
                if (Constants.CARD_ERR_OK.equals(map.get("ERR"))) {
                    sbkh = map.get("AAZ500");//社保卡
                    cityCode = map.get("AAB301");//城市代码
                } else {
                    return this.result(Constants.RESULT_MESSAGE_ERROR, map.get("ERR"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return error("获取基本信息失败");
        }
        try {
            // 临时挂失入参
            String val = "<操作*>解除挂失</操作*>" + "<用户名*>" + user + "</用户名*>" +
                    "<密码*>" + password + "</密码*>" + parser("所属城市*", cityCode) +
                    parser("社保卡号*", sbkh) + parser("社会保障号码*", idCard) + parser("姓名*", name);
            // 调用临时挂失接口
            Map<String, String> res = InvokeUtil.invoke("allDsjk", new String[]{val});
            if (Constants.CARD_ERR_00.equals(res.get("ERR"))) {
                return this.result(Constants.RESULT_MESSAGE_SUCCESS, "解除挂失成功");
            } else if (Constants.CARD_ERR_01.equals(res.get("ERR"))) {
                return this.result(Constants.RESULT_MESSAGE_ERROR, "此社保卡已经是正常状态，无需解除挂失！");
            } else {
                return this.result(Constants.RESULT_MESSAGE_ERROR, res.get("ERR"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解除挂失异常" , e);
        }
        return this.error("解除挂失失败");
    }



    /**
     * 临时挂失setLoss
     */
    @RequestMapping(value = "/setLoss", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result setLoss(@RequestBody CardInfoBean bean) throws Exception {

        logger.info("---------------CardInfoController: setLoss---------------");
        logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

        if (StringUtils.isEmpty(bean.getSfzh()) && StringUtils.isEmpty(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "身份证号或姓名不能为空");
        }
        // 调用第三方接口入参
        String idCard = bean.getSfzh();//身份证号
        String name = bean.getXm();//姓名
        String cityCode = idCard.substring(0, 4) + "00";//所属城市编码
        String sbkh = null;//社保卡号

        //获取社保卡号与城市代码
        String[] param = {user, password, idCard, name, cityCode};
        try {
            Map<String, String> map = InvokeUtil.invoke("getAC01", param);
            if (map.containsKey("ERR")) {
                if (Constants.CARD_ERR_OK.equals(map.get("ERR"))) {
                    sbkh = map.get("AAZ500");//社保卡
                    cityCode = map.get("AAB301");//城市代码
                } else {
                    return this.result(Constants.RESULT_MESSAGE_ERROR, map.get("ERR"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return error("获取基本信息失败");
        }
        try {
            // 临时挂失入参
            String val = "<操作*>临时挂失</操作*>" + "<用户名*>" + user + "</用户名*>" +
                    "<密码*>" + password + "</密码*>" + parser("所属城市*", cityCode) +
                    parser("社保卡号*", sbkh) + parser("社会保障号码*", idCard) + parser("姓名*", name);
            // 调用临时挂失接口
            Map<String, String> res = InvokeUtil.invoke("allDsjk", new String[]{val});
            if (Constants.CARD_ERR_00.equals(res.get("ERR"))) {
                return this.result(Constants.RESULT_MESSAGE_SUCCESS, "临时挂失成功");
            } else if (Constants.CARD_ERR_01.equals(res.get("ERR"))) {
                return this.result(Constants.RESULT_MESSAGE_ERROR, "此卡已经为临时挂失状态，无需重复挂失");
            } else {
                return this.result(Constants.RESULT_MESSAGE_ERROR, res.get("ERR"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("临时挂失异常" ,e);
        }
        return this.error("临时挂失失败");
    }

    /**
     * 获取个人基本信息
     * @param bean
     * @return
     */
   /* @RequestMapping(value = "/getPersonMassge", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getPersonMassge(@RequestBody CardInfoBean bean) {
        try {
            CardForThreeBean cardForThreeBean = cardInfoService.getPersonMassage4Other(bean);
            return ok("查询个人信息成功", cardForThreeBean);
        } catch (Exception e) {
            logger.error("查询个人信息失败：", e);
        }
        return error("查询个人信息失败");
    }*/

    /**
     * canApply
     * 查询参保人是否可以申领社保卡
     * 本市户籍的可以办理（不管有没有在本市参保）
     * 已经在本市办理过社保卡的，不给办理
     * 外市户籍，在本市参保，可以办理【若无接口，则由当地网点人员根据用户提供的参保证明进行人工判断】
     * 外市户籍，不在本市参保，不给办理【若无接口，则由当地网点人员根据用户提供的参保证明进行人工判断】
     * statusCode
     * 200 可以申领
     * 201 参保人姓名或身份证号不能为空
     * 301 已申领，请勿重复申领
     * 302 制卡中（查询制卡进度（各地市制卡进度接口）获取）
     * 303 未参保，没有本地参保的记录
     * 0 查询参保人信息失败
     */
    /*@SuppressWarnings("unused")
    @RequestMapping(value = "/canApply", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result canApply(@RequestBody SecQueryBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "查询失败";
        String sfzh = bean.getSfzh();
        String xm = bean.getXm();
        if (StringUtils.isNotEmpty(sfzh) && StringUtils.isNotEmpty(xm)) {
            try {
//                社保卡申请是否允许重复申请,若不允许--新申领功能不允许重复
                if (!Constants.CONFIG_YES.equals(CARD_APPLY_IS_RE)) {
                    // 查询之前是否在本地已申请
                    List<CsspApplyBean> personList = cardInfoService.isExistApplyPersonInfo4Other(bean.getXm(), bean.getSfzh());
                    if(personList != null && !personList.isEmpty()){
                        //存在记录 并且未审核或审核中（除去审核失败）
                        if (personList.get(0) != null && !("3".equals(personList.get(0).getShbz()))) {
                            String date = personList.get(0).getCreateTime();
                            statusCode = "301";
                            message = "证件号" + bean.getSfzh() + "于" + date + "已申领，请勿重复申领";
                            return result(statusCode, message);
                        }
                    }
                    if (!"301".equals(statusCode)) {
                        // 查询制卡进度 是否存在申请时间
                        CardInfoBean cardInfoBean = new CardInfoBean();
                        cardInfoBean.setSfzh(sfzh);
                        cardInfoBean.setXm(xm);
                        // 根据各地市制卡进度接口修改开始
                        Result res = getCardProgress(cardInfoBean);
                        if (Constants.RESULT_MESSAGE_SUCCESS.equals(res.getStatusCode()) &&
                                res.getData() != null) {
                            JSONObject jsonObject = JSONObject.fromObject(res.getData());
                            if (StringUtils.isNotEmpty(jsonObject.getString("APPLYTIME"))){
                                message = "制卡中";
                                statusCode = "302";
                            }
                        }
                        // 根据各地市制卡进度接口修改结束
                    }
                }
                if (Constants.RESULT_MESSAGE_ERROR.equals(statusCode)) {
                    // 查询是否本市户籍是否已参保或其他参保条件是否满足，根据各地市提供接口判断
                    boolean status = true;//满足申领条件
                    if (status) {
                        message = "可以申领";
                        statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    } else {
                        message = "未参保";//或其他参保条件未满足
                        statusCode = "303";
                    }
                }
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                logger.error("查询参保人信息异常bean:\t" + JsonHelper.javaBeanToJson(bean));
                logger.error("查询参保人信息异常：", e);

                message = "查询参保人信息失败";
            }
        } else {
            message = "参保人姓名或身份证号不能为空";
            statusCode = Constants.RESULT_MESSAGE_EMPTY;
        }
        return result(statusCode, message);
    }

    *//**
     * checkCertValidity
     * 检测证件是否过期
     * 查询方式1、调用第三方接口获取证件有效期、2、前端传入（默认）
     *//*
    @RequestMapping(value = "/checkCertValidity", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result checkCertValidity(@RequestBody CsspApplyBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "检测失败";
        String sfzh = bean.getSfzh();
        String xm = bean.getXm();
        if (StringUtils.isNotEmpty(sfzh) && StringUtils.isNotEmpty(xm)) {
            try {
                //调用第三方接口获取证件有效期
                boolean status = true;
                if (status) {
                    message = "证件有效";
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                } else {
                    message = "证件有效期已过";
                }
                //前端传入（默认）
                if (StringUtils.isNotEmpty(bean.getCertValidity())) {
                    //查询证件有效期
                    long dayDiff = CommUtil.dayDiff(bean.getCertValidity(), CommUtil.getNowDateLongStr("yyyyMMdd"), "yyyyMMdd");
                    if (dayDiff < 0) {
                        message = "证件有效期已过";
                        statusCode = Constants.RESULT_MESSAGE_ERROR;
                    }
                }
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                logger.error("检测证件是否过期异常bean:\t" + JsonHelper.javaBeanToJson(bean));
                logger.error("检测证件是否过期异常：", e);
                message = "检测失败";
            }
        } else {
            message = "参保人姓名或身份证号不能为空";
            statusCode = Constants.RESULT_MESSAGE_EMPTY;
        }
        return result(statusCode, message);
    }


    *//**
     * 根据各地市要求选择：社保卡申领：相片处理需一段时间(此时参保人相片第三方处理尚未返回)，调用此方法把个人申领信息存入本地数据库,
     * 等待定时器扫描上传 根据实际情况改写此接口，入参出参不变 各地市提供接口
     * 把采集到的数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO
     * 把采集到的数据存到社保卡申请信息表 T_YTH_APPLYCARD_INFO
     * 性别	01男性;02女性;03未知
     * 民族存数据库为编码
     *//*
    @RequestMapping(value = "/applyCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result applyCard(@RequestBody CsspApplyBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "申领失败";
        CsspCardPickUpVO csspCardPickupVO = new CsspCardPickUpVO();
        //身份证&姓名
        if (StringUtils.isEmpty(bean.getSfzh()) && StringUtils.isEmpty(bean.getXm())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "姓名或身份证不能为空");
        }
        if (StringUtils.isEmpty(bean.getDhlx())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择电话类型");
        }
        if (StringUtils.isEmpty(bean.getPhone())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "电话号码不能为空");
        }
        if (StringUtils.isEmpty(bean.getBankCode())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择银行信息");
        }
        if (StringUtils.isEmpty(bean.getSex())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "性别不能为空");
        }
        if (StringUtils.isEmpty(bean.getGuoji())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "国籍不能为空");
        }else {
            bean.setGuoji(Constants.GJ_Code.get(bean.getGuoji()));
        }
        if (StringUtils.isEmpty(bean.getCertValidity())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "证件有效期不能为空");
        }else if(bean.getCertValidity().length() > 8){
            return result(Constants.RESULT_MESSAGE_EMPTY, "证件有效期的格式为YYYYMMDD");
        }
        // 民族
        String nation = bean.getNation();
        if (StringUtils.isNotEmpty(nation)) {
            nation = StringUtils.isNotBlank(nation) ? nation.replace("族", "") : "";
            bean.setNation(Constants.NATION_Code.get(nation));
        } else
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择民族");
        if (StringUtils.isEmpty(bean.getChannelcode())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "渠道类型不能为空");
        }
        if (StringUtils.isEmpty(bean.getCertType())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "证件类型不能为空");//证件类型身份证（户口簿）
        }
        //个人相片、身份证正、反面
        if (bean.getPhotoBuzId() == 0 || bean.getPicupId() == 0 || bean.getPicdownId() == 0) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "个人相片、身份证正、反面照不能为空");
        }
        String birth = null;
        try {//出生日期
            if (StringUtils.isBlank(bean.getBirthday()))
                return result(Constants.RESULT_MESSAGE_EMPTY, "出生日期不能为空");
            if(StringUtils.isNotBlank(bean.getBirthday()) && bean.getBirthday().length()>8) {
                birth = bean.getBirthday();
                String[] str = bean.getBirthday().split("-");
                bean.setBirthday(str[0] + str[1] + str[2]);
            }
            // 查询之前是否在平台申请
            List<CsspApplyBean> personList = cardInfoService.isExistApplyPersonInfo4Other(bean.getXm(), bean.getSfzh());
            if(personList != null && !personList.isEmpty()){
                bean.setPersonId(personList.get(0).getPersonId());
                // 社保卡申请是否允许重复申请,若不允许--新申领功能不允许重复
                if(!Constants.CONFIG_YES.equals(CARD_APPLY_IS_RE)) {
                    //存在记录 并且未审核或审核中（除去审核失败）
                    if (personList.get(0) != null && !("3".equals(personList.get(0).getShbz()))) {
                        String date = personList.get(0).getCreateTime();
                        message = "证件号" + bean.getSfzh() + "于" + date + "已申领，请勿重复申领";
                        return result(Constants.RESULT_MESSAGE_ERROR, message);
                    }
                }
            }
            //参保人相片信息（相片未处理）
            String filePath = null;
            String picPath = commService.photoIsExist4Cssp(bean.getPhotoBuzId(), Constants.PICTURE_TYPE_101);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                message = "参保人相片图片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPhotoBuzId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }else {//修改照片命名为 身份证号+姓名+.jpg
                boolean is = false;
                File file = new File(picPath);
                String fileName = bean.getSfzh() + bean.getXm();//新文件名
                String fileRootPath = Config.getInstance().get("picture_path_100");//文件根目录
                filePath = fileRootPath + "/" + fileName + ".jpg";//文件完整路径
                File newFile = new File(filePath);
                if (newFile.exists()) {
                    logger.error("图片已经存在" + filePath);
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
                    String newFilePath = fileRootPath + "/" + fileName + df.format(new Date()) + ".jpg";//文件完整路径
                    is = newFile.renameTo(new File(newFilePath));//重命名
                    if(!is) {
                        FileUtils.copyFile(newFile, new File(newFilePath));
                    }
                }
                logger.info("图片重命名为" + filePath);
                //复制图片到要求的目录  /image
                FileUtils.copyFile(file, new File(filePath));

            }
            //身份证正面信息
            String picUpPath = commService.photoIsExist4Cssp(bean.getPicupId(), Constants.PICTURE_TYPE_103);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picUpPath)) {
                message = "身份证正面图片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicupId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            //身份证反面信息
            String picDownPath = commService.photoIsExist4Cssp(bean.getPicdownId(), Constants.PICTURE_TYPE_104);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picDownPath)) {
                message = "身份证反面图片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicdownId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            bean.setPhotoSource("01");
            bean.setIsUpload("N");
            bean.setUploadNum(0);
            int status = 0;
            PersonBean personBean = new PersonBean();
            BeanUtils.copyProperties(bean, personBean);
            personBean.setPhone(bean.getPhone());
            personBean.setBirthday(birth);
            PersonBean person = commService.getBasicPersonInfo4Cssp(bean.getSfzh(), bean.getXm());
            if(person != null){
                personBean.setPersonId(person.getPersonId());
            }
            if (personBean.getPersonId() != 0) {
                // 把个人基本信息表 T_YTH_BASIC_PERSON_INFO更新为采集到的数据
                status = commService.updatePersonInfo4Cssp(personBean);
                if (status > 0) status = 0;
                else
                    return this.result(Constants.RESULT_MESSAGE_ERROR, "保存个人信息出错");
            }
            if (personBean.getPersonId() == 0) {
                // 把采集到的数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO
                status = commService.insertPersonInfo4Cssp(personBean);
                if (status > 0) bean.setPersonId(personBean.getPersonId());
                status = 0;
            }
            //存在审核失败的记录则修改
            Map<String, Object> map = new HashMap<>();
            map.put("aac002", bean.getSfzh());
            map.put("name", bean.getSfzh() + bean.getXm() + ".jpg");
            map.put("path", filePath);
            map.put("aae011", bean.getJbjg());
            map.put("yab003", bean.getJbjg());

            bean.setSqzt("0");//申请状态
            bean.setShbz("0");//审核状态
            bean.setScbz("1");//上传标志
            if (personList.size() > 0 && ("3".equals(personList.get(0).getShbz()))) {
                status = cardInfoService.updatePersonPhoto4Other(map);
                if(status < 0) return error("申领失败");
                status = cardInfoService.updatePersonInfo4Other(bean);
                if(status < 0) return error("申领失败");
            }else {//否则新增
                //保存采集数据到松原数据库人员照片表
                status = cardInfoService.insertPersonPhoto4Other(map);
                if(status < 0) return error("申领失败");
                //保存采集数据到松原数据库人员信息表
                status = cardInfoService.insertPersonInfo4Other(bean);
                if(status < 0) return error("申领失败");
            }
            // 把采集到的数据存入本地社保卡申请信息表
            if (personBean.getPersonId() != 0) {
                if (StringUtils.isNotBlank(bean.getAddrType()) || StringUtils.isNotBlank(bean.getCardAddress())) {
                    status = cardInfoService.insertCardApplyAddr4Cssp(bean);// 把采集到的数据存入社保卡申请领卡地址表
                    if (status < 0) {
                        message = "保存领卡信息失败";
                        return result(statusCode, message);
                    }
                }
                bean.setRemark(bean.getChannelcode());//备注为申请渠道
                status = cardInfoService.insertCardApplyInfo4Cssp(bean);
                if (status > 0) return ok("提交申领成功");
            } else {
                logger.error("applyCard 保存个人基本信息失败" + JsonHelper.javaBeanToJson(bean));
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "申领失败";
            }
        } catch (Exception e) {
            logger.error("applyCard 保存数据错误：" + JsonHelper.javaBeanToJson(bean));
            logger.error("保存数据错误", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "申领失败";
        }
        return result(statusCode, message);
    }

    *//**
     * 快速处理照片接口 1.只检测是否符合标准，不进行处理
     *
     *@param bean
     * @return
     *//*
    @RequestMapping(value = "/fastProcessPic", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result fastProcessPic(@RequestBody PixelBean bean) {
        //String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            if ( StringUtils.isEmpty(bean.getPicType())) {
                return result(Constants.RESULT_MESSAGE_EMPTY, "照片规格不可为空", null);
            }
            String photoBase64 = "";
            if (StringUtils.isNotBlank(bean.getBase64String())) {
                photoBase64 = bean.getBase64String();
            } else if (bean.getPicId() != 0) {
                String picPath = commService.photoIsExist4Cssp(bean.getPicId(), Constants.PICTURE_TYPE_101);
                if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                    message = "用户图片不存在";
                    logger.error( " picId=" + bean.getPicId() + message);
                    return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                }
                photoBase64 = ImageChangeUtil.getImageStr(picPath);
            } else
                return result(Constants.RESULT_MESSAGE_EMPTY, "图片信息不可为空", null);
            JsonObject json = new JsonObject();
            json.addProperty("base64String", photoBase64);
            json.addProperty("pic_type", bean.getPicType());
            String url = PIXEL_URL + "fastProcessPic";
            String result = (String) getWebClient(url, json, String.class);
            Map<String, Object> map = JsonHelper.jsonToMap(result);
            return result((String) map.get("statusCode"), (String) map.get("message"), null);
        } catch (Exception e) {
            logger.error("快速处理照片fastProcessPic-bean:\t" + JsonHelper.javaBeanToJson(bean), e);
            return error("服务器异常，请稍后再试!", null);
        }
    }*/

    /**
     * 照片处理返回接口
     *@param bean
     * @return
     */
//    @RequestMapping(value = "/callbackPicInfo", method = RequestMethod.POST, produces = "application/json")
//    @ResponseBody
//    public Result callbackPicInfo(@RequestBody PixelBean bean, @Context HttpServletRequest request) {
//        try {
//            if (org.apache.commons.lang3.StringUtils.isEmpty(bean.getBusId())
//                    || org.apache.commons.lang3.StringUtils.isEmpty(bean.getStatus())) {
//                return new Result("300", "入参不正确，入参不全", null);
//            }
//            if (!ImageChangeUtil.isIntegerByString(bean.getBusId()))
//                return new Result("300", "入参不正确，入参不全", null);
//            String status = bean.getStatus();
//            if (!status.equals(Constants.PIXEL_BUSINESS_STATUS_01)
//                    && !status.equals(Constants.PIXEL_BUSINESS_STATUS_02))
//                return new Result("300", "入参不正确，入参不全", null);
//            if (status.equals(Constants.PIXEL_BUSINESS_STATUS_01)) {
//                if (org.apache.commons.lang3.StringUtils.isEmpty(bean.getBase64String())) {
//                    return new Result("300", "入参不正确，入参不全", null);
//                }
//            }
//            // 首先查询这个图片id是否存在
//            PicBean picBean = new PicBean();
//            picBean.setPicId(Long.parseLong(bean.getBusId()));
//            picBean = commService.getPicture4Cssp(picBean);
//            if (picBean == null || picBean.getPicId() == 0) return new Result("300", "业务ID不存在", null);
//            // 保存图片
//            picBean.setPicBase64(bean.getBase64String());
//            picBean.setPicType(Constants.PICTURE_TYPE_102);
//            long picId = commService.updatePicture4Cssp(picBean);
//
//            //相片（已处理）信息修改关联业务信息
//            PicBusBean perBusBean = new PicBusBean();
//            perBusBean.setPicId(picId);
//            perBusBean.setPicStatus(status);
//            perBusBean.setPicType(Constants.PICTURE_TYPE_102);
//            perBusBean.setPicMessage(bean.getMessage());
//            commService.updatePictureBus4Cssp(perBusBean);
//            return ok("回调成功", null);
//        } catch (Exception e) {
//            logger.info("uploadPicInfo-bean:\t" + JsonHelper.javaBeanToJson(bean),e);
//            logger.error("服务器异常，请稍后再试!", e);
//            return error("服务器异常，请稍后再试!", null);
//        }
//    }

    /**
     * 查询失败的审核记录
     *@param bean
     * @return
     */
  /*  @RequestMapping(value = "/getVerifyRecord", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getVerifyRecord(@RequestBody PixelBean bean) {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功";
        try {
            Page<VerifyRecordVo> page = cardInfoService.getVerifyRecord4Other(bean);//查询出审核失败的记录 数据来源为测试数据  正式数据来源为第三方卡管
            //根据查询出来的记录 更新申领表中申领记录的状态
            List<VerifyRecordVo> data = page.getData();
            if(data != null && data.size()>0){
                for (VerifyRecordVo verifyRecordVo : data) {
                    List<CsspApplyBean> list = cardInfoService.isExistApplyPersonInfo4Other(verifyRecordVo.getXm(), verifyRecordVo.getSfzh());
                    if(list != null && list.size()>0){
                        for (CsspApplyBean csspApplyBean : list) {
                            if(csspApplyBean.getSfzh().equals(verifyRecordVo.getSfzh()) && csspApplyBean.getXm().equals(verifyRecordVo.getXm())){
                                verifyRecordVo.setRecordId(csspApplyBean.getApplyId());
                                verifyRecordVo.setPicId(csspApplyBean.getPhotoBuzId());
                            }
                        }
                    }
                }
            }
            return result(statusCode,message,page);

        } catch (Exception e) {
            logger.error("查询审核失败记录异常-bean:\t" + JsonHelper.javaBeanToJson(bean), e);
            return error("服务器异常，请稍后再试!", null);
        }
    }

    *//**
     * 社保卡申请-查询申请详情
     * @param bean
     * @return
     *//*
    @RequestMapping(value = "/getApplyCardInfoById", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getApplyCardInfoById(@RequestBody ApplyInfoBean bean) {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功";
        try {
            CsspApplyInfoVo applyInfo= cardInfoService.getApplyCardInfoById4Cssp(bean.getApplyId());
            return result(statusCode,message,applyInfo);
        } catch (Exception e) {
            logger.error("查询记录异常-bean:\t" + JsonHelper.javaBeanToJson(bean), e);
            return error("服务器异常，请稍后再试!", null);
        }
    }

    *//**
     * 社保动态
     * @param bean
     * @return
     * @throws Exception 
     *//*
    @RequestMapping(value = "/getSBDT", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getSBDT(@RequestBody SbdtBean bean) {
        try {
            Page<SbdtBean> sbdt = cardInfoService.getSbdt4Cssp(bean);
            return ok("获取社保动态成功", sbdt);
        } catch (Exception e) {
            logger.error("获取社保动态异常-bean:\t" + JsonHelper.javaBeanToJson(bean), e);
        }
        return error("获取社保动态失败");
    }

*/
    //制卡进度字段转换
    public Map<String,String> fieldConvertingByCardProgress(Map<String, String> map) throws Exception {
        if (map == null) {
            return null;
        }
        map.put("APPLYTIME", changeTime(map.get("APPLYTIME")));
        map.put("BANKTIME0", changeTime(map.get("BANKTIME0")));
        map.put("BANKFINISHTIME0", changeTime(map.get("BANKFINISHTIME0")));
        map.put("INSURETIME", changeTime(map.get("INSURETIME")));
        map.put("INSUREFINISHTIME", changeTime(map.get("INSUREFINISHTIME")));
        map.put("PROVINCETIME", changeTime(map.get("PROVINCETIME")));
        map.put("CITYTIME", changeTime(map.get("CITYTIME")));
        map.put("GETTIME", changeTime(map.get("GETTIME")));
        map.put("AAE008", DictionaryUtil.getDictName(Constants.AAE008,map.get("AAE008")));
        return map;
    } 
    
  //制卡数据字段转换
    public Map<String,String> convertCardGetMessage(Map<String, String> map) throws Exception {
        if (map == null) {
            return null;
        }
        map.put("AAC009", DictionaryUtil.getDictName(Constants.AAC009,map.get("AAC009")));
        map.put("AAC008", Constants.AAC008.get(map.get("AAC008")));
        map.put("AAC005", DictionaryUtil.getDictName(Constants.AAC005,map.get("AAC005").length()==1?"0"+map.get("AAC005"):map.get("AAC005")));
        map.put("AAC004", Constants.AAC004.get(map.get("AAC004")));
        map.put("GJ", Constants.GJ.get(map.get("GJ")));
        map.put("ZJLX", Constants.ZJLX.get(map.get("ZJLX")));
        map.put("JHRZJLX", Constants.ZJLX.get(map.get("JHRZJLX")));
      
        return map;
    }  

    // 拼装报文
    public String parser(String top, String str) {
        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            str = "";
        }
        String result = String.format("<%s>%s</%s>", top, str, top);
        return result;
    }

    //时间格式转换
    private String changeTime(String time) {
        String str = time;
        if (str != null && str != "" && str.length() == 14) {
            str =time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8) +
                    " " + time.substring(8, 10) + ":" + time.substring(10, 12) + ":" + time.substring(12, 14);
        }
        if (str != null && str != "" && str.length() == 15) {
            str =time.substring(0, 1)+time.substring(1, 5) + "-" + time.substring(5, 7) + "-" + time.substring(7, 9) +
                    " " + time.substring(9, 11) + ":" + time.substring(11, 13) + ":" + time.substring(13, 15);
        }
        return str;
    }
    
   
}
