package com.tecsun.sisp.adapter.modules.card.controller;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.common.util.card.CardUtil;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardInfoBean;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardAccomplishVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardAllInfo;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardProgressVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.SettingCardQuery;
import com.tecsun.sisp.adapter.modules.card.service.impl.CsspServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;

/**
 * 社保卡业务：临时挂失、解挂、社保卡激活、制卡进度查询 文档已出，请勿随意修改入参出参
 * 
 * @ClassName: CardInfoController
 */
@Controller
@RequestMapping(value = "/adapter/card")
public class CardInfoController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(CardInfoController.class);

	// 城市代码
	public static final String CITYNO = Config.getInstance().get("cityno");

	// @Autowired
	// private TestCardInfoServiceImpl testCardInfoService;
	//
	@Autowired
	private CsspServiceImpl csspService;

	@Autowired
	private CommServiceImpl commService;

	/**
	 * 制卡进度查询 传入身份证
	 */
	@RequestMapping(value = "/getCardProgress", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getCardProgress(@RequestBody CardInfoBean bean) throws Exception {

		logger.info("---------------CardInfoController: getCardProgress---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "制卡进度查询失败";
		CardProgressVO info = new CardProgressVO();
		try {
			if (StringUtils.isNotEmpty(bean.getSfzh()) && StringUtils.isNotEmpty(bean.getXm())) {
				String result = CardUtil.getCardProgress(bean.getSfzh(), bean.getXm());
				result = (new StringBuilder("<shengting>")).append(result).append("</shengting>")
						.toString();
				Document document = DocumentHelper.parseText(result);
				Element element = document.getRootElement();
				Element description = element.element("ERR");
				String err = description.getText();
				SettingCardQuery cardQuery = new SettingCardQuery();
				if (err.equals("OK")) {
					cardQuery = CardUtil.getSettingCardQuery(element, err);
					info.setXm(cardQuery.getName());
					info.setSbkh(cardQuery.getSbkh());
					info.setSfzh(cardQuery.getSfzh());
					info.setApplytime(cardQuery.getApplytime());
					info.setBanktime(cardQuery.getBanktime0());
					info.setCitytime(cardQuery.getCitytime());
					info.setGettime(cardQuery.getGettime());
					info.setInsuretime(cardQuery.getInsuretime());
					info.setStatus(cardQuery.getStatus());
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
					message = "制卡进度查询成功";
				} else {
					logger.info("制卡进度查询失败,具体原因:" + err);
					message = err;
				}
			} else {
				message = "身份证号或姓名不能为空";
				statusCode = Constants.RESULT_MESSAGE_ERROR;
			}
		} catch (Exception e) {
			logger.error("制卡进度查询失败:", e);
		}
		return result(statusCode, message, info);
	}

	/**
	 * 临时挂失setLoss
	 */
	@RequestMapping(value = "/setLoss", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result setLoss(@RequestBody CardInfoBean bean) throws Exception {

		logger.info("---------------CardInfoController: setLoss---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "临时挂失失败";
		try {
			if (StringUtils.isNotEmpty(bean.getSfzh()) && StringUtils.isNotEmpty(bean.getXm())
					&& StringUtils.isNotEmpty(bean.getSbkh())) {
				String result = CardUtil.setLossOrSetStart("setLoss", bean.getSbkh(),
						bean.getSfzh(), bean.getXm(), "");
				if (StringUtils.isEmpty(result)) {
					message = "调用省厅挂失接口异常";
				}
				if (result.equals("00")) {
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
					message = "临时挂失成功";
				} else if (result.equals("01")) {
					message = "已进行过临时挂失，请勿重复挂失";
				} else {
					message = result;
				}
			} else {
				message = "身份证号、姓名及社保卡号不能为空";
				statusCode = Constants.RESULT_MESSAGE_ERROR;
			}
		} catch (Exception e) {
			logger.error("临时挂失失败:", e);
		}
		return result(statusCode, message);
	}

	/**
	 * 解挂setHanging
	 */
	@RequestMapping(value = "/setHanging", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result setHanging(@RequestBody CardInfoBean bean) throws Exception {

		logger.info("---------------CardInfoController: setHanging---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "解挂失败";
		try {
			if (StringUtils.isNotEmpty(bean.getSfzh()) && StringUtils.isNotEmpty(bean.getXm())
					&& StringUtils.isNotEmpty(bean.getSbkh())) {
				String result = CardUtil.SetCard("03", bean.getSbkh(), bean.getSfzh(),
						bean.getXm(), "", "", "");
				if (StringUtils.isEmpty(result)) {
					message = "调用省厅挂失接口异常";
				}
				if (result.equals("00")) {
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
					message = "社保卡解挂成功";
				} else if (result.equals("01")) {
					message = "已进行过解挂，请勿重复操作";
				} else {
					message = result;
				}
			} else {
				message = "身份证号、姓名及社保卡号不能为空";
				statusCode = Constants.RESULT_MESSAGE_ERROR;
			}
		} catch (Exception e) {
			logger.error("解挂失败:", e);
		}
		return result(statusCode, message);
	}

	/**
	 * 社保卡激活 setStart 三种状态 200 成功激活 202 过去已激活 0 激活失败
	 */
	@RequestMapping(value = "/setStart", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result setStart(@RequestBody CardInfoBean bean) throws Exception {

		logger.info("---------------CardInfoController: setStart---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "社保卡激活失败";
		try {
			if (StringUtils.isNotEmpty(bean.getSfzh()) && StringUtils.isNotEmpty(bean.getXm())
					&& StringUtils.isNotEmpty(bean.getSbkh())) {
				String result = CardUtil.setLossOrSetStart("setStart", bean.getSbkh(),
						bean.getSfzh(), bean.getXm(), CITYNO);
				if (StringUtils.isEmpty(result)) {
					message = "调用省厅社保卡激活接口异常";
				}
				if (result.equals("00")) {
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
					message = "社保卡激活成功";
				} else if (result.equals("01")) {
					statusCode = "202";
					message = "社保卡已激活";
				} else {
					message = result;
				}
			} else {
				message = "身份证号、姓名及社保卡号不能为空";
				statusCode = Constants.RESULT_MESSAGE_ERROR;
			}
		} catch (Exception e) {
			logger.error("社保卡激活失败:", e);
		}
		return result(statusCode, message);
	}

	/**
	 * 卡状态查询（新增）
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/getCard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getCard(@RequestBody CardInfoBean bean) {
		logger.info("---------------CardInfoController: getCard---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "卡状态查询失败";
		try {
			if (StringUtils.isNotEmpty(bean.getSfzh()) && StringUtils.isNotEmpty(bean.getXm())
					&& StringUtils.isNotEmpty(bean.getSbkh())) {
				String result = CardUtil
						.getCardStatus(bean.getSbkh(), bean.getSfzh(), bean.getXm());
				if (StringUtils.isEmpty(result)) {
					result = Constants.RESULT_MESSAGE_ERROR;
					message = "调用省厅卡状态接口异常";
				}
				if (result.equals("OK")) {
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
					message = "卡状态正常";
				} else {
					logger.info("卡状态查询失败,具体原因:" + result);
					message = result;
				}
			} else {
				message = "身份证号、姓名、社保卡号都不能为空";
				statusCode = Constants.RESULT_MESSAGE_ERROR;
			}
		} catch (Exception e) {
			logger.error("卡状态查询失败:", e);
		}
		return result(statusCode, message, "");

	}

	/**
	 * 卡基础数据（包含个人照片） 社保卡号和身份证号码至少有一项不为空 (新增)
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCardAllInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getCardAllInfo(@RequestBody CardInfoBean bean) throws Exception {
		logger.info("---------------CardInfoController: getCardAllInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		bean.setAac002(bean.getSfzh());

		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "卡基础数据(含照片)查询失败";
		CardAllInfo info = new CardAllInfo();
		try {
			if (!StringUtils.isEmpty(bean.getSfzh()) || !StringUtils.isEmpty(bean.getSbkh())) {
				String result = CardUtil.getData(bean.getSfzh(), bean.getSbkh());

				result = (new StringBuilder("<shengting>")).append(result).append("</shengting>")
						.toString();

				Document document = DocumentHelper.parseText(result);
				Element element = document.getRootElement();
				Element description = element.element("ERR");
				String err = description.getText();
				if (err.equals("OK")) {
					info = CardUtil.getCardBaseInfo(element, err);
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
					message = "卡基础数据获取成功";
				} else {
					statusCode = Constants.RESULT_MESSAGE_ERROR;
					message = info.getErr();
				}
			} else {
				statusCode = Constants.RESULT_MESSAGE_ERROR;
				message = "社保卡号和身份证号码至少有一项不为空";
			}
		} catch (Exception e) {
			logger.error("卡状态查询失败:", e);
		}
		return result(statusCode, message, info);
	}

	/**
	 * 制卡信息查询
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/cardGetMessage", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result cardGetMessage(@RequestBody SecQueryBean bean) {
		// 入参校验
		if (StringUtils.isEmpty(bean.getXm()) || StringUtils.isEmpty(bean.getSfzh()))
			return error("姓名、身份证号不能为空");

		try {
			// 查询制卡信息
			CardAccomplishVO card = csspService.cardGetMessage4Cssp(bean);
			return ok("查询成功", card);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return error("查询失败");
	}

}
