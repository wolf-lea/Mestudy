package com.tecsun.sisp.iface.server.controller.common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.common.util.JsonMapper;
import com.tecsun.sisp.iface.common.util.Result;
import com.tecsun.sisp.iface.common.vo.common.param.LoginBean;
import com.tecsun.sisp.iface.common.vo.common.param.ThirdUserBean;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.util.CommUtil;
import com.tecsun.sisp.iface.server.util.DictionaryUtil;
import com.tecsun.sisp.iface.server.util.JedisUtil;

@Controller
@RequestMapping(value = "/iface/user")
public class UserController extends BaseController {

    public final static Logger logger = Logger.getLogger(UserController.class);

    
    @RequestMapping(value = "/getAreadId", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getAreadId(@RequestBody LoginBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
    	String aaz001 = "";
    	String result = Constants.RESULT_MESSAGE_SUCCESS;
    	try {
			aaz001 = getThirdAreaId(request.getParameter("method"), request.getParameter("tokenId"), aaz001);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return result(result, "", aaz001);
    }
    /**
     * 登录验证
     *
     * @param bean
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result checkLogin(@RequestBody LoginBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        String type = bean.getChannelcode();
        String result = Constants.RESULT_MESSAGE_ERROR;
        String message = "登录失败";
        String token = "";
        String ip = "";
        try {
        	String userName = bean.getUsername();
        	String pwd = bean.getPassword();
            if (type.equals("SelfService")) {//由于无用户登录操作，需前台定义定时更新任务
                if (userName.equals(CommUtil.generateValue("SelfService")) && pwd.equals(CommUtil.generateValue("SelfService"))) {
                    token = Constants.TOKENNAME + CommUtil.getUUID();
                    ip = CommUtil.getIpAddr(request);
                    JedisUtil.setValue(token, ip, Constants.SELFSERVICETIMEOUT);
                    result = Constants.RESULT_MESSAGE_SUCCESS;
                    message = type + " 登陆成功!";
                    return result(result, message, token);
                } else {
                    logger.info("token is :"+token+ "============ip is: "+ip);
                    logger.error("自助终端登录：用户名或密码错误！");
                }
            } else if (type.equals("TSB")) {
                if (userName.equals(CommUtil.generateValue("TSB")) && pwd.equals(CommUtil.generateValue("TSB"))) {
                    token = Constants.TOKENNAME + CommUtil.getUUID();
                    ip = CommUtil.getIpAddr(request);
                    JedisUtil.setValue(token,ip);
                    result = Constants.RESULT_MESSAGE_SUCCESS;
                    message = type + " 登陆成功!";
                    return result(result, message, token);
                } else {
                    logger.info("token is :"+token+ "============ip is: "+ip);
                    logger.error("德生宝登录：用户名或密码错误！");
                }
            } else if (type.equals("WeChat")) {
                if (userName.equals(CommUtil.generateValue("WeChat")) && pwd.equals(CommUtil.generateValue("WeChat"))) {
                    token = Constants.TOKENNAME + CommUtil.getUUID();
                    ip = CommUtil.getIpAddr(request);
                    JedisUtil.setValue(token,ip);
                    result = Constants.RESULT_MESSAGE_SUCCESS;
                    message = type + " 登陆成功!";
                    return result(result, message, token);
                } else {
                    logger.info("token is :"+token+ "============ip is: "+ip);
                    logger.error("微信登录：用户名或密码错误！");
                }
            } else if(type.equals("ThirdSystem")){  //第三方公司签到
            	ThirdUserBean userbean = userLogin(userName, pwd);
            	if(userbean == null){
            		logger.error("第三方公司登录：用户名或密码错误！");
            	 	message =  " 登陆失败,用户名或密码错误！";
            	}else{
            		token = Constants.TOKENNAME + CommUtil.getUUID();
            		ip = CommUtil.getIpAddr(request);
                    JedisUtil.setValue(token,ip);
                    //向缓存中添加用户id，以及区域
                    String userkey = Constants.USERKEY + token;
                    String userAreaid = Constants.USERAREAID + token;
                    JedisUtil.setValue(userkey,Long.toString(userbean.getId()));
                    JedisUtil.setValue(userAreaid,Long.toString(userbean.getAreaid()));
                    result = Constants.RESULT_MESSAGE_SUCCESS;
                    message = type + " 登陆成功!";
                    return result(result, message, token);
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("token is :"+token+ "============ip is: "+ip);
            logger.error(type + " 登录失败！ /n 异常信息："+e.getMessage());
        }
        logger.info(message);
        logger.info("token is :"+token+ "============ip is: "+ip);
        return result(result, message, bean);
    }
    
   

    private ThirdUserBean userLogin(String userName , String pwd) throws IOException{
    	JsonObject json = new JsonObject();
    	json.addProperty("userName", userName);
    	json.addProperty("userPwd", pwd);
    	String url = DictionaryUtil.getHost(Constants.INTERFACE_USERLOGIN);
    	String result = (String) getWebClient(url, json, String.class);
    	Map map = JsonMapper.buildNormalMapper().fromJson(result, Map.class);
    	Map data = (Map) map.get("result");
    	if(data == null) return null;
    	else {
    		ThirdUserBean bean = new ThirdUserBean();
    		bean.setId(Long.parseLong(data.get("id").toString()));
    		bean.setAreaid(Long.parseLong(data.get("areaId").toString()));
    		return bean;
    	}
    }

}
