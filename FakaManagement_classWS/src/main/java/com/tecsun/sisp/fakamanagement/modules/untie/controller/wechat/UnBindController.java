package com.tecsun.sisp.fakamanagement.modules.untie.controller.wechat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.Constants;
import com.tecsun.sisp.fakamanagement.common.JsonHelper;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.common.client.HttpClientUtil;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.untie.entity.request.UnBindRequestBean;
import com.tecsun.sisp.fakamanagement.modules.untie.entity.response.UnbindOrBindResponseList;
import com.tecsun.sisp.fakamanagement.modules.untie.entity.response.WeBindOrUnBindListResponse;
import com.tecsun.sisp.fakamanagement.modules.untie.entity.response.WeChatDetailsResponseList;
import com.tecsun.sisp.fakamanagement.modules.untie.service.impl.wechat.UnBindServiceImpl;

/**
 * 
 * @Title: 微信解绑控制类
 * @company :TECSUN
 * @developer: ZENGYUNHUA
 *	@date 2018年9月4日 上午11:13:39
 *	@version 1.0
 */
@Controller
@RequestMapping(value="/faka/unbind")
public class UnBindController extends BaseController{
	
	public final static Logger logger = Logger.getLogger(UnBindController.class);
	
	@Autowired
	private UnBindServiceImpl unbindService;

	 /**
	  * 
	  * @Title:解绑绑定列表接口
	  * @company :TECSUN
	  * @developer: ZENGYUNHUA
	  *	@date 2018年9月5日 下午5:11:08
	  *	@version 1.0
	  */
	@RequestMapping(value = "/unbindOrBindList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result unbindOrBindList(@RequestBody UnBindRequestBean bean) {
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "操作成功";
		logger.info("微信绑定解绑列表unbindOrBindList接口入参bean"
				+ JsonHelper.javaBeanToJson(bean));
		if(StringUtils.isBlank(bean.getChannelcode())){
			return error("参数不可为空!");
		}
		try {
			String url = Config.getInstance().get("UNBINDORBINDLIST");
			// 调用接口方法，用post请求方式调用
			String unbindOrBindValue =  HttpClientUtil.getData(url, bean, true, false, 0);
			if (unbindOrBindValue != null && unbindOrBindValue.length() > 0) {
				logger.info("解绑/绑定列表unbindOrBindList接口，出参bean:\t..........."
						+ unbindOrBindValue);
				UnbindOrBindResponseList unbindorbindresponse = JSON
						.parseObject(unbindOrBindValue,
								UnbindOrBindResponseList.class);
				if (null != unbindorbindresponse
						&& !"".equals(unbindorbindresponse)) {
					message = unbindorbindresponse.getMessage();
					if ("200".equals(unbindorbindresponse.getStatusCode())) {
						return result(result, message,unbindorbindresponse.getData());
					} else {
						message = "暂未获取到相关的数据";
						result = Constants.RESULT_MESSAGE_ERROR;
						logger.error("调用解绑/绑定列表接口失败，返回值不是200");
						return result(result, message);
					}
				} else {
					message = "暂未获取到相关的数据";
					result = Constants.RESULT_MESSAGE_ERROR;
					logger.error("调用解绑/绑定列表接口失败，解析返回对象为空");
					return result(result, message);
				}
			} else {
				message = "暂未获取到相关的数据";
				result = Constants.RESULT_MESSAGE_ERROR;
				logger.error("调用解绑/绑定列表接口失败，返回值空");
				return result(result, message);
			}
			
		} catch (Exception e) {
			message = "暂未获取到相关的数据";
			result = Constants.RESULT_MESSAGE_ERROR;
			e.printStackTrace();
			logger.error("调用解绑/绑定列表接口：unbindOrBindList接口失败");
		}
		return result(result, message);

	}

	
	/**
	 * 
	 * @Title:查询微信账号信息详情
	 * @company :TECSUN
	 * @developer: ZENGYUNHUA
	 *	@date 2018年9月6日 下午2:36:20
	 *	@version 1.0
	 */
	@RequestMapping(value = "/wechatDetails", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result wechatDetails(@RequestBody UnBindRequestBean bean)
			throws Exception {
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "操作成功";
		logger.info("微信详情查询，入参bean:\t..........."
				+ JsonHelper.javaBeanToJson(bean));
		if(StringUtils.isBlank(bean.getChannelcode())){
			return error("渠道类型不可为空!");
		}
		if(StringUtils.isBlank(bean.getAccountId())){
			return error("accountId参数缺失!");
		}
		try {
			// 获取接口地址；
			String url = Config.getInstance().get("WECHATDETAIL");
			// 调用接口方法POST
			String wechatDetailsValue = HttpClientUtil.getData(url, bean, true, false, 0);
			if (wechatDetailsValue != null && wechatDetailsValue.length() > 0) {
				logger.info("微信详情查询，出参bean:\t..........." + wechatDetailsValue);
				WeChatDetailsResponseList weChatDetailsResponse = JSON
						.parseObject(wechatDetailsValue,
								WeChatDetailsResponseList.class);
				if (null != weChatDetailsResponse
						&& !"".equals(weChatDetailsResponse)) {
					message = weChatDetailsResponse.getMessage();
					if ("200".equals(weChatDetailsResponse.getStatusCode())) {
						return result(result, message,
								weChatDetailsResponse.getData());
					} else {
						message = "暂未获取到相关的数据";
						result = Constants.RESULT_MESSAGE_ERROR;
						logger.error("调用wechatDetails接口失败，返回值不是200");
						return result(result, message);
					}
				} else {
					message = "暂未获取到相关的数据";
					result = Constants.RESULT_MESSAGE_ERROR;
					logger.error("调用wechatDetails接口失败，解析返回对象为空");
					return result(result, message);
				}
			} else {
				message = "暂未获取到相关的数据";
				result = Constants.RESULT_MESSAGE_ERROR;
				logger.error("调用wechatDetails接口失败，返回值空");
				return result(result, message);
			}
		} catch (Exception e) {
			message = "暂未获取到相关的数据";
			result = Constants.RESULT_MESSAGE_ERROR;
			e.printStackTrace();
			logger.error("微信详情：wechatDetails接口失败");
		}
		return result(result, message);
	}
	
	/**
	 * 
	 * @Title:绑定解绑
	 * @company :TECSUN
	 * @developer: ZENGYUNHUA
	 *	@date 2018年9月6日 下午2:40:29
	 *	@version 1.0
	 */
	@RequestMapping(value = "/bindOrUnBind", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result bindOrUnBind(@RequestBody UnBindRequestBean bean)
			throws Exception {
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "操作成功";
		logger.info("绑定解绑，入参bean:\t..........."
				+ JsonHelper.javaBeanToJson(bean));
		if (StringUtils.isBlank(bean.getChannelcode())) {
			return error("渠道类型不可为空!");
		}
		if (StringUtils.isBlank(bean.getAccountId())) {
			return error("唯一编号accountId不可为空!");
		}
		if (StringUtils.isBlank(bean.getIsSuccessBind())) {
			return error("请选择解绑或绑定");
		}
		try {
			// 获取接口地址；
			String url = Config.getInstance().get("WECHATBINDORUNBIND");
			// 调用接口方法POST
			String wechatbindValue = HttpClientUtil.getData(url, bean, true,
					false, 0);
			if (wechatbindValue != null && wechatbindValue.length() > 0) {
				logger.info("微信详情查询，出参bean:\t..........." + wechatbindValue);
				WeBindOrUnBindListResponse weChatBindOrUnbindResponse = JSON
						.parseObject(wechatbindValue,
								WeBindOrUnBindListResponse.class);
				if (null != weChatBindOrUnbindResponse
						&& !"".equals(weChatBindOrUnbindResponse)) {
					message = weChatBindOrUnbindResponse.getMessage();
					if ("200"
							.equals(weChatBindOrUnbindResponse.getStatusCode())) {
						return result(result, message,
								weChatBindOrUnbindResponse.getData());
					} else {
						message = "暂未获取到相关的数据";
						result = Constants.RESULT_MESSAGE_ERROR;
						logger.error("调用bindOrUnBind接口失败，返回值不是200");
						return result(result, message);
					}
				} else {
					message = "暂未获取到相关的数据";
					result = Constants.RESULT_MESSAGE_ERROR;
					logger.error("调用bindOrUnBind接口失败，解析返回对象为空");
					return result(result, message);
				}
			} else {
				message = "暂未获取到相关的数据";
				result = Constants.RESULT_MESSAGE_ERROR;
				logger.error("调用bindOrUnBind接口失败，返回值空");
				return result(result, message);
			}
		} catch (Exception e) {
			message = "暂未获取到相关的数据";
			result = Constants.RESULT_MESSAGE_ERROR;
			e.printStackTrace();
			logger.error("微信绑定解绑：bindOrUnBind接口失败");
		}
		return result(result, message);
	
	}
	
	

}
