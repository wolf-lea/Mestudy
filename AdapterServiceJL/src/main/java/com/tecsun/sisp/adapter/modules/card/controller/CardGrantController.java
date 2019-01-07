package com.tecsun.sisp.adapter.modules.card.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.adapter.common.https.HttpClientUtil;
import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.DictionaryUtil;
import com.tecsun.sisp.adapter.common.util.InvokeUtil;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.JsonMapper;
import com.tecsun.sisp.adapter.common.util.JsonUtil;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardGrantBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.EvaAndGrantBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.MinorCardGrantBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.PrefabCardBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.TempCardBean;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardStoreInfoVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.NetInfoVO;
import com.tecsun.sisp.adapter.modules.card.service.impl.CardGrantServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;

/**
 * @author wunuanchan
 * @version
 * 
 *          创建时间：2017年12月25日 下午2:50:28 说明：精准发放
 */
@Controller
@RequestMapping(value = "/adapter/cardRecevice")
public class CardGrantController extends BaseController {

	public final static Logger logger = Logger
			.getLogger(CardGrantController.class);

	private String user = Config.getInstance().get("card.user");
	private String password = Config.getInstance().get("card.pwd");
	// 发卡系统url
	private String faka_url = Config.getInstance().get("faka_service_url");

	// 发卡系统port
	private Integer faka_port = Integer.parseInt(Config.getInstance().get(
			"faka_service_port"));

	@Autowired
	private CardGrantServiceImpl cardGrantService;

	/**
	 * 通用卡信息查询接口
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryCardStore", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCardStore(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryCardStore---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pagesize"), "10");
		String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pageno"), "1");

		try {
			String url = faka_url + Constants.QUERY_CARD_STORE + "?pagesize="
					+ pagesize + "&pageno=" + pageno;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardStore(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardStore(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			List<CardStoreInfoVO> list = null;
			String data = JsonMapper.buildNormalMapper()
					.toJson(map.get("data"));
			if ("200".equals(code)) {
				if (StringUtils.isNotEmpty(data)) {
					list = (List<CardStoreInfoVO>) JsonHelper.jsonToList(data,
							CardStoreInfoVO.class);
					for (int i = 0; i < list.size(); i++) {
						if (StringUtils.isNotEmpty(list.get(i).getFkwd())) {
							NetInfoVO vo = cardGrantService
									.queryNetInfo4Cssp(list.get(i).getFkwd());
							if (vo != null) {
								list.get(i).setNetName(
										vo.getName() == null ? "" : vo
												.getName());
								list.get(i).setNetAdress(
										vo.getAddress() == null ? "" : vo
												.getAddress());
							}
						}
					}
				}
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), list);
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("通用卡信息查询失败:", e);
		}
		return this.error("通用卡信息查询失败");
	}

	/**
	 * 通用卡信息查询接口(网点发放公共账号下社保卡，单张发放， 个人领卡和代个人领卡查询卡信息的时候用)
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryCardStoreByPerson", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCardStoreByPerson(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryCardStoreByPerson---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pagesize"), "10");
		String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pageno"), "1");

		try {
			String url = faka_url + Constants.QUERY_CARD_STORE_PERSON
					+ "?pagesize=" + pagesize + "&pageno=" + pageno;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardStore(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardStore(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			List<CardStoreInfoVO> list = null;
			String data = JsonMapper.buildNormalMapper()
					.toJson(map.get("data"));
			if ("200".equals(code)) {
				if (StringUtils.isNotEmpty(data)) {
					list = (List<CardStoreInfoVO>) JsonHelper.jsonToList(data,
							CardStoreInfoVO.class);
					for (int i = 0; i < list.size(); i++) {
						if (StringUtils.isNotEmpty(list.get(i).getFkwd())) {
							NetInfoVO vo = cardGrantService
									.queryNetInfo4Cssp(list.get(i).getFkwd());
							if (vo != null) {
								list.get(i).setNetName(
										vo.getName() == null ? "" : vo
												.getName());
								list.get(i).setNetAdress(
										vo.getAddress() == null ? "" : vo
												.getAddress());
							}
						}
					}
				}
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), list);
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("通用卡信息查询失败:", e);
		}
		return this.error("通用卡信息查询失败");
	}

	/**
	 * 通用卡信息查询接口--单位领卡查询个人信息
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryCardStoreByCompanyPerson", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCardStoreByCompanyPerson(
			@RequestBody CardGrantBean bean, @Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryCardStore---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pagesize"), "10");
		String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pageno"), "1");

		try {
			String url = faka_url + Constants.QUERY_CARD_STORE_COMPANY_PERSON
					+ "?pagesize=" + pagesize + "&pageno=" + pageno;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardStore(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardStore(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			List<CardStoreInfoVO> list = null;
			String data = JsonMapper.buildNormalMapper()
					.toJson(map.get("data"));
			if ("200".equals(code)) {
				if (StringUtils.isNotEmpty(data)) {
					list = (List<CardStoreInfoVO>) JsonHelper.jsonToList(data,
							CardStoreInfoVO.class);
					for (int i = 0; i < list.size(); i++) {
						if (StringUtils.isNotEmpty(list.get(i).getFkwd())) {
							NetInfoVO vo = cardGrantService
									.queryNetInfo4Cssp(list.get(i).getFkwd());
							if (vo != null) {
								list.get(i).setNetName(
										vo.getName() == null ? "" : vo
												.getName());
								list.get(i).setNetAdress(
										vo.getAddress() == null ? "" : vo
												.getAddress());
							}
						}
					}
				}
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), list);
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("通用卡信息查询失败:", e);
		}
		return this.error("通用卡信息查询失败");
	}

	/**
	 * 上传领卡人照片接口
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/saveReceivePhotos", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result saveReceivePhotos(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: saveReceivePhotos---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		if (StringUtils.isEmpty(bean.getBase64code())) {
			return this.error("照片不能为空！");
		}
		if (StringUtils.isEmpty(bean.getType().toString())) {
			return this.error("照片类型不能为空");
		}
		if (StringUtils.isEmpty(bean.getPhotoname())) {
			return this.error("照片名称不能为空");
		}
		try {
			String url = faka_url + Constants.SAVE_RECEIVE_PHOTOS;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonReceivePhotos(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonReceivePhotos(bean).toJSONString(),
						true, true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("上传领卡人照片失败:", e);
		}
		return this.error("上传领卡人照片失败");
	}

	/**
	 * 领卡并记录接口
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/addCardReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result addCardReceive(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: addCardReceive---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		if (StringUtils.isEmpty(bean.getType().toString())) {
			return error("领卡类型为空");
		}
		if (null == bean.getIds()) {
			return error("卡id为空");
		}
		Integer[] ids = bean.getIds();
		if (ids.length <= 0) {
			return error("卡id为空");
		}
		try {
			String url = faka_url + Constants.ADD_CARD_RECEIVE;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardReceive(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardReceive(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");

			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("领卡并记录失败:", e);
		}
		return this.error("领卡并记录失败");
	}

	/**
	 * 查询领卡记录接口
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryCardReceiveLog", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCardReceiveLog(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryCardReceiveLog---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pagesize"), "10");
		String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pageno"), "1");

		try {
			String url = faka_url + Constants.QUERY_CARD_RECEIVE_LOG
					+ "?pagesize=" + pagesize + "&pageno=" + pageno;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardReceiveLog(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardReceiveLog(bean).toJSONString(),
						true, true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("查询领卡记录失败:", e);
		}
		return this.error("查询领卡记录失败");
	}

	/**
	 * 生成领卡流水号接口
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/addReceiveNum", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result addReceiveNum(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: addReceiveNum---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		if (null == bean.getIds()) {
			return error("卡id为空");
		}
		Integer[] ids = bean.getIds();
		if (ids.length <= 0) {
			return error("卡id为空");
		}
		if (StringUtils.isEmpty(bean.getName())) {
			return error("姓名不能为空");
		}
		if (StringUtils.isEmpty(bean.getIdcard())) {
			return error("身份证号码不能为空");
		}
		try {
			String url = faka_url + Constants.ADD_RECEIVE_NUM;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonReceiveNum(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonReceiveNum(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("生成领卡流水号失败:", e);
		}
		return this.error("生成领卡流水号失败");
	}

	/**
	 * 评价和领卡操作关联接口
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/evaluateAndGrant", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result evaluateAndGrant(@RequestBody CardGrantBean bean)
			throws Exception {
		if (StringUtils.isBlank(bean.getServiceNumber())) {
			return this.result(Constants.RESULT_MESSAGE_PARAM, "评价业务唯一编码不能为空");
		}
		if (null == bean.getCardReceiveIds()) {
			return error("发卡记录id不能为空");
		}
		Integer[] ids = bean.getCardReceiveIds();
		if (ids.length <= 0) {
			return error("发卡记录id不能为空");
		}
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "评价和领卡操作关联失败";
		try {
			List<EvaAndGrantBean> list = new ArrayList<EvaAndGrantBean>();
			for (int i = 0; i < ids.length; i++) {
				EvaAndGrantBean beanOne = new EvaAndGrantBean();
				beanOne.setServiceNumber(bean.getServiceNumber());
				beanOne.setCardReceiveId(ids[i].toString());
				list.add(beanOne);
			}
			cardGrantService.evaluateAndGrant4Cssp(list);
			statusCode = Constants.RESULT_MESSAGE_SUCCESS;
			message = "评价和领卡操作关联成功";
			return this.result(statusCode, message);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "评价和领卡操作关联失败";
			logger.error("评价和领卡操作关联失败:" + JsonHelper.javaBeanToJson(bean), e);
		}
		return this.result(statusCode, message);
	}

	/**
	 * 单位经办人查询卡信息接口
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryCardStoreByCompany", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCardStoreByCompany(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryCardStoreByCompany---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pagesize"), "10");
		String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pageno"), "1");

		try {
			String url = faka_url + Constants.QUERY_CARD_STORE_COMPANY
					+ "?pagesize=" + pagesize + "&pageno=" + pageno;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardStoreCompany(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardStoreCompany(bean).toJSONString(),
						true, true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("单位经办人查询卡信息失败:", e);
		}
		return this.error("单位经办人查询卡信息失败");
	}

	/**
	 * 查询下属网点列表
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/getFKWD", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getFKWD(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: getFKWD---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		try {
			String url = faka_url + Constants.GET_FKWD;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonGetFkwd(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonGetFkwd(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("查询下属网点列表失败:", e);
		}
		return this.error("查询下属网点列表失败");
	}

	/**
	 * 获取入库总量
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryStatisticsCardStore", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardStore(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryStatisticsCardStore---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		try {
			String url = faka_url + Constants.QUERY_STATISTICS_CARD_STORE;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardAccount(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardAccount(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						map.get("total").toString(), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("获取入库总量失败:", e);
		}
		return this.error("获取入库总量失败");
	}

	/**
	 * 2.获取入柜总量
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryStatisticsCardCabinet", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardCabinet(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryStatisticsCardCabinet---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		try {
			String url = faka_url + Constants.QUERY_STATISTICS_CARD_CABINET;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardAccount(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardAccount(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						map.get("total").toString(), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("获取入柜总量失败:", e);
		}
		return this.error("获取入柜总量失败");
	}

	/**
	 * 3.获取发卡总量
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryStatisticsCardReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardReceive(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryStatisticsCardReceive---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		try {
			String url = faka_url + Constants.QUERY_STATISTICS_CARD_RECEIVE;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardAccount(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardAccount(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						map.get("total").toString(), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("获取发卡总量失败:", e);
		}
		return this.error("获取发卡总量失败");
	}

	/**
	 * 获取问题卡总量
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryStatisticsCardProblem", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardProblem(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryStatisticsCardProblem---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		try {
			String url = faka_url + Constants.QUERY_STATISTICS_CARD_PROBLEM;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardAccount(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardAccount(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						map.get("total").toString(), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("获取问题卡总量失败:", e);
		}
		return this.error("获取问题卡总量失败");
	}

	/**
	 * 获取滞留卡总量
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryStatisticsCardretention", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardretention(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryStatisticsCardretention---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		try {
			String url = faka_url + Constants.QUERY_STATISTICS_CARD_RETENTION;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardAccount(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardAccount(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						map.get("total").toString(), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("获取滞留卡总量失败:", e);
		}
		return this.error("获取滞留卡总量失败");
	}

	/**
	 * 获取激活总量
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryStatisticsCardActivation", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardActivation(
			@RequestBody CardGrantBean bean, @Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryStatisticsCardActivation---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		try {
			String url = faka_url + Constants.QUERY_STATISTICS_CARD_ACTIVATION;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardAccount(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardAccount(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						map.get("total").toString(), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("获取激活总量失败:", e);
		}
		return this.error("获取激活总量失败");
	}

	/**
	 * 7.获取当天发卡总量
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryCurrentStatisticsCardReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCurrentStatisticsCardReceive(
			@RequestBody CardGrantBean bean, @Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryCurrentStatisticsCardReceive---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		try {
			String url = faka_url
					+ Constants.QUERY_CURRENT_STATISTICS_CARD_RECEIVE;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCardAccount(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCardAccount(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						map.get("total").toString(), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("获取当天发卡总量 失败:", e);
		}
		return this.error("获取当天发卡总量 失败");
	}

	/**
	 * 查询卡柜信息接口
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/getCabinets", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getCabinets(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: getCabinets---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pagesize"), "10");
		String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pageno"), "1");

		try {
			String url = faka_url + Constants.GET_CABINETS + "?pagesize="
					+ pagesize + "&pageno=" + pageno;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonCabinets(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonCabinets(bean).toJSONString(), true,
						true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("查询卡柜信息失败:", e);
		}
		return this.error("查询卡柜信息失败");
	}

	/**
	 * 入库信息查询接口
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/queryStorage", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStorage(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: queryStorage---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pagesize"), "10");
		String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(
				request.getParameter("pageno"), "1");

		try {
			String url = faka_url + Constants.QUERY_STORAGE + "?pagesize="
					+ pagesize + "&pageno=" + pageno;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonQueryStorage(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonQueryStorage(bean).toJSONString(),
						true, true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("查询入库信息失败:", e);
		}
		return this.error("查询入库信息失败");
	}

	/**
	 * 社保卡入库接口
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/selectStorage", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result selectStorage(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: selectStorage---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		try {
			String url = faka_url + Constants.SELECT_STORAGE;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonSelectStorage(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonSelectStorage(bean).toJSONString(),
						true, true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("社保卡入库失败:", e);
		}
		return this.error("社保卡入库失败");
	}

	/**
	 * 未成年人发卡入库
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/saveMinorCard", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result saveMinorCard(@RequestBody MinorCardGrantBean bean) {
		logger.info("---------------CardGrantController: saveMinorCard---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "保存未成年人发卡信息成功";

		if (StringUtils.isEmpty(bean.getName())
				&& StringUtils.isEmpty(bean.getIdcard()))
			return this.result(Constants.RESULT_MESSAGE_ERROR, "姓名和身份证不能为空！");
		if (StringUtils.isEmpty(bean.getCardid()))
			return this.result(Constants.RESULT_MESSAGE_ERROR, "社会保障卡卡号不能为空！");
		if (StringUtils.isEmpty(bean.getFkwd()))
			return this.result(Constants.RESULT_MESSAGE_ERROR, "发卡网点不能为空！");
		if (null == bean.getStatus())
			return this.result(Constants.RESULT_MESSAGE_ERROR, "卡状态不能为空！");

		// 调用第三方接口入参
		String idCard = bean.getIdcard();// 身份证号
		String name = bean.getName();// 姓名
		String cityCode = idCard.substring(0, 4) + "00";// 所属城市编码
		String sbkh = bean.getCardid();// 社保卡号

		try {
			// 获取卡数据
			String[] param = { user, password, idCard, name, cityCode };
			try {
				Map<String, String> map = InvokeUtil.invoke("getAC01", param);// 卡数据
				Map<String, String> map2 = InvokeUtil.invoke("getAZ03", param);// 卡进度
				if (map.containsKey("ERR") && map2.containsKey("ERR")) {
					if (Constants.CARD_ERR_OK.equals(map.get("ERR"))) {
						sbkh = map.get("AAZ500");// 社保卡
						bean.setReginalCode(map.get("AAB301"));// 城市代码
						bean.setBatchNo(map.get("BATCHNO"));// 批次号
						bean.setCompanyCode(map.get("AAB001"));// 单位名称
						bean.setCompanyName(map.get("AAB004"));// 单位编号
						bean.setBank(map.get("AAE008"));// 开户银行
						bean.setPhone(map.get("AAE005"));// 联系电话
						bean.setOldCfwz(map2.get("ZXWZ"));// 装箱位置
						bean.setXtZxwz(map.get("ZXWZ"));//系统装箱位置
					} else {
						return this.result(
								Constants.RESULT_MESSAGE_ERROR,
								"卡数据错误原因：" + map.get("ERR") + "卡进度错误原因："
										+ map2.get("ERR"));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return error("获取基本信息失败");
			}

			if (bean.getOldCfwz() == null ||"".equals(bean.getOldCfwz())) {
				return this.result(Constants.RESULT_MESSAGE_ERROR, "查询卡管数据失败");
			}
			Integer status = 0;
			Integer id = null;
			String fkjgwd = null; // 发卡机构网点
			Integer agentid = 0;
			bean.setPersonType(5); // 领卡类型：未成年人领卡。
			// (fkwd)user_id转换为org_code
			fkjgwd = cardGrantService.selectLogName4sisp(bean.getFkwd());
			if (fkjgwd != null && fkjgwd.length() != 0) {
				bean.setFkwd(fkjgwd);
				// 判断未成年人数据是否存在，存在则修改。不存在则插入保存
				id = cardGrantService.getCardInfoId4faka(bean);
				if (null!=bean.getDomiciliaryRegisterPhoto()&&!bean.getDomiciliaryRegisterPhoto().equals("")) {
					bean.setDomiciliaryRegisterPhoto(bean.getDomiciliaryRegisterPhoto()+".jpg");
				}
				if (null!=bean.getCardidPhoto()&&!bean.getCardidPhoto().equals("")) {
					bean.setPhoto(bean.getCardidPhoto()+".jpg");
				}
				/*if (null!=bean.getPhoto()&&!bean.getPhoto().equals("")) {
					bean.setPhoto(bean.getCardidPhoto()+".jpg");
				}*/
				if (null!=bean.getIdcardPhotoUp()&&!bean.getIdcardPhotoUp().equals("")) {
					bean.setIdcard(bean.getIdcard()+".jpg");
				}
				if (null!=bean.getIdcardPhotoDown()&&!bean.getIdcardPhotoDown().equals("")) {
					bean.setIdcardPhotoDown(bean.getIdcardPhotoDown()+".jpg");
				}
				if (null!=bean.getSignPhoto()&&!bean.getSignPhoto().equals("")) {
					bean.setSignPhoto(bean.getSignPhoto()+".jpg");
				}
				if (id != null) {
					status = cardGrantService.updateCardInfo4faka(bean);
					if (status > 0)
						status = 0;
					else
						return this.result(Constants.RESULT_MESSAGE_ERROR,
								"未成年人发卡基本信息修改失败");
				} else {// 插入基本信息到card_info表中
					status = cardGrantService
							.insertMinorCardBasicInfo4faka(bean);
					if (status > 0)
						status = 0;
					else
						return this.result(Constants.RESULT_MESSAGE_ERROR,
								"未成年人发卡基本信息已存在无需重复保存");
				}
				// 插入代领人信息到card_agent表中
				status = cardGrantService.insertInsteadCardInfo4faka(bean);
				if (status > 0)
					status = 0;
				else
					return this.result(Constants.RESULT_MESSAGE_ERROR,
							"未成年人发卡领卡人信息出错");
				/*// 获取agentid
				agentid = cardGrantService.selectAgentidbyIdcard4faka(bean);
				bean.setAgentId(agentid);*/
				// 插入领卡时间到card_receive
				status = cardGrantService.insertCardReceiveInfo4faka(bean);
				if (status > 0)
					status = 0;
				else
					return this.result(Constants.RESULT_MESSAGE_ERROR,
							"保存未成年人时间和照片出错");
			}
		} catch (Exception e) {
			logger.error("---------------保存未成年人信息出错---------------:", e);
		}
		// 获取社保卡号与城市代码
		String[] param = { user, password, idCard, name, cityCode };
		try {
			Map<String, String> map = InvokeUtil.invoke("getAC01", param);
			if (map.containsKey("ERR")) {
				if (Constants.CARD_ERR_OK.equals(map.get("ERR"))) {
					sbkh = map.get("AAZ500");// 社保卡
					cityCode = map.get("AAB301");// 城市代码
				} else {
					return this.result(Constants.RESULT_MESSAGE_ERROR,
							map.get("ERR"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return error("获取基本信息失败");
		}
		try {
			// 临时挂失入参
			String val = "<操作*>领卡启用</操作*>" + "<用户名*>" + user + "</用户名*>"
					+ "<密码*>" + password + "</密码*>" + parser("所属城市*", cityCode)
					+ parser("社保卡号*", sbkh) + parser("社会保障号码*", idCard)
					+ parser("姓名*", name);
			// 调用临时挂失接口
			Map<String, String> res = InvokeUtil.invoke("allDsjk",
					new String[] { val });
			if (Constants.CARD_ERR_00.equals(res.get("ERR"))) {
				bean.setStatus(7);// 状态7已发卡
				cardGrantService.updateCardStatus4faka(bean);
			} else if (Constants.CARD_ERR_01.equals(res.get("ERR"))) {
				bean.setStatus(7);// 状态7已发卡
				cardGrantService.updateCardStatus4faka(bean);
			} else {
				return this.result(Constants.RESULT_MESSAGE_ERROR,
						res.get("ERR"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("社保卡激活异常", e);
		}
		return this.result(statusCode, message);
	}

	/**
	 * 临时卡发卡入库
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/saveTempCard", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result saveTempCard(@RequestBody TempCardBean bean) {

		logger.info("---------------CardGrantController: saveTempCard---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "保存临时卡发卡信息成功";
		Integer status = 0;
		Integer id = null;
		Integer fkwd	= null;

		if (StringUtils.isEmpty(bean.getName()))
			return this.result(Constants.RESULT_MESSAGE_ERROR, "姓名不能为空！");
		if (StringUtils.isEmpty(bean.getIdcard()))
			return this.result(Constants.RESULT_MESSAGE_ERROR, "身份证不能为空！");
		if (StringUtils.isEmpty(bean.getDetailstatus()))
			return this.result(Constants.RESULT_MESSAGE_ERROR, "卡状态不能为空！");
		try {
			fkwd =cardGrantService.selectOrgId4sisp(bean.getRkwd().toString());
			if (fkwd != null) {
				// 判断临时卡编号是否已经存在数据库中，存在则修改。不存在则插入保存，TEMPCARD_DETAIL表
				bean.setRkwd(fkwd);
				id = cardGrantService.getDetailId4faka(bean.getTempCardNo());
				if (null!=bean.getPhoto()&&!bean.getPhoto().equals("")) {
					bean.setPhoto(bean.getPhoto()+".jpg");
				}
				if (null!=bean.getIdcardPhotoDown()&&!bean.getIdcardPhotoDown().equals("")) {
					bean.setIdcardPhotoDown(bean.getIdcardPhotoDown()+".jpg");
				}
				if (null!=bean.getIdcardPhotoUp()&&!bean.getIdcardPhotoUp().equals("")) {
					bean.setIdcardPhotoUp(bean.getIdcardPhotoUp()+".jpg");
				}
				if (null!=bean.getSignPhoto()&&!bean.getSignPhoto().equals("")) {
					bean.setSignPhoto(bean.getSignPhoto()+".jpg");
				}
				if (id != null) {
					// TEMPCARD_DETAIL表
					status = cardGrantService.updateDetail4faka(bean);
					if (status > 0)
						status = 0;
					else
						return this.result(Constants.RESULT_MESSAGE_ERROR,
								"修改临时卡发卡基本信息出错");
				} else {
					// 保存临时卡信息到TEMPCARD_DETAIL
					status = cardGrantService.insertTempCardToDetail4faka(bean);
					if (status > 0)
						status = 0;
					else
						return this.result(Constants.RESULT_MESSAGE_ERROR,
								"保存临时卡发卡基本信息出错");
				}
				// 保存临时卡信息到TEMPCARD_PERSON_INFO
				status = cardGrantService.inserTempCardToPersonInfo4faka(bean);
				if (status > 0)
					status = 0;
				else
					return this.result(Constants.RESULT_MESSAGE_ERROR,
							"保存临时卡发卡基本信息出错");
			} else {
				return this.result(Constants.RESULT_MESSAGE_ERROR, "查询不到对应的入库网点！");
			}
		} catch (Exception e) {
			logger.error("---------------操作失败---------------:", e);
		}
		return result(statusCode, message);
	}

	/**
	 * 预制卡发卡入库
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/savePrefabCard", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result savePrefabCard(@RequestBody PrefabCardBean bean) {

		logger.info("---------------CardGrantController: savePrefabCard---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "保存预制卡发卡信息成功";
		String rkwd = null;
		Integer status = 0;
		Integer id = null;
		Integer replaceCardId = null;

		if (StringUtils.isEmpty(bean.getRkwd()))
			return this.result(Constants.RESULT_MESSAGE_ERROR, "入库网点不能为空！");
		if (StringUtils.isEmpty(bean.getStatus()))
			return this.result(Constants.RESULT_MESSAGE_ERROR, "卡状态不能为空！");
		if (StringUtils.isEmpty(bean.getName()))
			return this.result(Constants.RESULT_MESSAGE_ERROR, "姓名不能为空！");
		if (StringUtils.isEmpty(bean.getIdcard()))
			return this.result(Constants.RESULT_MESSAGE_ERROR, "身份证不能为空！");
		if (StringUtils.isEmpty(bean.getCardid()))
			return this.result(Constants.RESULT_MESSAGE_ERROR, "社保卡号不能为空！");

		try {
			rkwd = cardGrantService.selectLogName4sisp(bean.getRkwd());
			if (rkwd == null ||"".equals(rkwd)) {
				return this.result(Constants.RESULT_MESSAGE_ERROR, "查询不到对应的入库网点！");
			}
			
			//处理照片加后缀
			if (null!=bean.getPhotoUrl()&&!bean.getPhotoUrl().equals("")) {
				bean.setPhotoUrl(bean.getPhotoUrl()+".jpg");
			}
			if (null!=bean.getIdcardPhotoDown()&&!bean.getIdcardPhotoDown().equals("")) {
				bean.setIdcardPhotoDown(bean.getIdcardPhotoDown()+".jpg");
			}
			if (null!=bean.getIdcardPhotoUp()&&!bean.getIdcardPhotoUp().equals("")) {
				bean.setIdcardPhotoUp(bean.getIdcardPhotoUp()+".jpg");
			}
			if (null!=bean.getSignPhoto()&&!bean.getSignPhoto().equals("")) {
				bean.setSignPhoto(bean.getSignPhoto()+".jpg");
			}
			status = cardGrantService.inserPrefabCardToPersonInfo4faka(bean);
			if (status > 0)
				status = 0;
			else
				return this.result(Constants.RESULT_MESSAGE_ERROR,
						"保存预制卡基本信息出错");
			// 根据社保卡号判断数据是否存在
			    bean.setRkwd(rkwd);
			    id = cardGrantService.getPrefabCardId4faka(bean.getCardid());
			if (id != null) {
				//修改replacecard_detail数据
				status = cardGrantService.updatePrefabCardId4faka(bean);
				if (status > 0)
					status = 0;
				else
					return this.result(Constants.RESULT_MESSAGE_ERROR,
							"修改预制卡基本信息出错");
			} else {
				// 保存预制卡信息到REPLACECARD_DETAIL
				status = cardGrantService.insertPrefabCardId4faka(bean);
				if (status > 0)
					status = 0;
				else
					return this.result(Constants.RESULT_MESSAGE_ERROR,
							"保存预制卡基本信息出错");
			}
			
			/*replaceCardId = cardGrantService.getPersonInfoId4faka(bean);
			if (replaceCardId != null) {
				// 修改预制卡信息到replacecard_person_info表中
				status = cardGrantService.updatePersonInfo4faka(bean);
				if (status > 0)
					status = 0;
				else
					return this.result(Constants.RESULT_MESSAGE_ERROR,
							"修改预制卡基本信息出错");
			}else{
				// 保存预制卡信息到replacecard_person_info表中
				status = cardGrantService.inserPrefabCardToPersonInfo4faka(bean);
				if (status > 0)
					status = 0;
				else
					return this.result(Constants.RESULT_MESSAGE_ERROR,
							"保存预制卡基本信息出错");
			}	*/
		} catch (Exception e) {
			logger.error("---------------操作失败---------------:", e);
		}
		// 调用第三方接口入参
		String idCard = bean.getIdcard();// 身份证号
		String name = bean.getName();// 姓名
		String cityCode = idCard.substring(0, 4) + "00";// 所属城市编码
		String sbkh = bean.getCardid();// 社保卡号

		// 获取社保卡号与城市代码
		String[] param = { user, password, idCard, name, cityCode };
		try {
			Map<String, String> map = InvokeUtil.invoke("getAC01", param);
			if (map.containsKey("ERR")) {
				if (Constants.CARD_ERR_OK.equals(map.get("ERR"))) {
					sbkh = map.get("AAZ500");// 社保卡
					cityCode = map.get("AAB301");// 城市代码
				} else {
					return this.result(Constants.RESULT_MESSAGE_ERROR,
							map.get("ERR"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return error("获取基本信息失败");
		}
		try {
			// 临时挂失入参
			String val = "<操作*>领卡启用</操作*>" + "<用户名*>" + user + "</用户名*>"
					+ "<密码*>" + password + "</密码*>" + parser("所属城市*", cityCode)
					+ parser("社保卡号*", sbkh) + parser("社会保障号码*", idCard)
					+ parser("姓名*", name);
			// 调用临时挂失接口
			Map<String, String> res = InvokeUtil.invoke("allDsjk",
					new String[] { val });
			if (Constants.CARD_ERR_00.equals(res.get("ERR"))) {
				bean.setStatus("01");// 状态01表示已发放
				cardGrantService.updateReplaceCardStatus4faka(bean);
				message = "预制卡发卡成功，社保卡激活成功";
			} else if (Constants.CARD_ERR_01.equals(res.get("ERR"))) {
				bean.setStatus("01");// 状态7已发卡
				cardGrantService.updateReplaceCardStatus4faka(bean);
				message = "预制卡发卡成功，社保卡已激活，无需重复激活！";
			} else {
				return this.result(Constants.RESULT_MESSAGE_ERROR,
						res.get("ERR"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("社保卡激活异常", e);
		}
		return result(statusCode,message);
	}

	/**
	 * 社保卡入库追回
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/storageRecover", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result storageRecover(@RequestBody CardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: storageRecover---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		try {
			String url = faka_url + Constants.SELECT_RECOUVER;
			logger.info("url=" + url);
			String result = "";
			if (!url.startsWith("https:")) {
				result = (String) getWebClientExp(url,
						JsonUtil.getJsonStorageRecover(bean), String.class);
			} else {
				result = HttpClientUtil.getDataExp(url,
						JsonUtil.getJsonStorageRecover(bean).toJSONString(),
						true, true, faka_port).toString();
			}
			logger.info("---------------" + result + "---------------");
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			String code = (String) map.get("statusCode");
			if ("200".equals(code)) {
				return this.result(code, (Integer) map.get("total"),
						(String) map.get("message"), map.get("data"));
			} else {
				return this.error((String) map.get("message"));
			}

		} catch (Exception e) {
			logger.error("社保卡入库追回失败:", e);
		}
		return this.error("社保卡入库追回失败");
	}
	

	/**
	 * 判断是否存在入库网点的数据
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/getCardNetworkInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getCardNetworkInfo(@RequestBody TempCardBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: getCardNetworkInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		
		Integer id = 0;
		Integer status = 0;
		
		if (bean.getRkwd() == null)
			return this.result(Constants.RESULT_MESSAGE_ERROR, "入库网点不能为空！");
		try {
			id = cardGrantService.selectOrgId4sisp(bean.getRkwd().toString());
			if (id == null ||"".equals(id)) {
				return this.result(Constants.RESULT_MESSAGE_ERROR, "查询不到对应的入库网点！");
			}else{
				//判断是否存在数据
				bean.setRkwd(id);
				status = cardGrantService.selectTempcardId4faka(bean);
				if (status > 0)
					status = 0;
				else
					return this.result(Constants.RESULT_MESSAGE_ERROR,
							"该网点不存在这临时卡数据");
				return this.result(Constants.RESULT_MESSAGE_SUCCESS, "存在对应的入库网点数据");
			}
		} catch (Exception e) {
			logger.error("查询入库网点信息失败:", e);
		}
		return this.error("查询入库网点信息失败");
	}

	
	
	/**
	 * 判断卡数据是否已发卡
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/isCardReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result isCardReceive(@RequestBody MinorCardGrantBean bean,
			@Context HttpServletRequest request) {
		logger.info("---------------CardGrantController: isCardReceive---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		if(StringUtils.isEmpty(bean.getName()))
			return this.error("姓名不能为空");
		if(StringUtils.isEmpty(bean.getIdcard()))
			return this.error("身份证号不能为空");
		if(StringUtils.isEmpty(bean.getCardid()))
			return this.error("社保卡号不能为空");
					
			
		try {
				//判断是否存在数据
			 List<MinorCardGrantBean> list= cardGrantService.selectIsCardReceive4faka(bean);
			 if(list.size()>0)
				 return this.error("社保卡已发卡，无需重复发卡");
		    else
		    	 return this.ok("社保卡可发卡");
		
		} catch (Exception e) {
			logger.error("查询卡数据是否已发卡失败:", e);
			return this.error("查询卡数据是否已发卡失败");
		}
		
	}
	// 拼装报文
	public String parser(String top, String str) {
		if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
			str = "";
		}
		String result = String.format("<%s>%s</%s>", top, str, top);
		return result;
	}

	// 制卡数据字段转换
	public Map<String, String> convertCardGetMessage(Map<String, String> map)
			throws Exception {
		if (map == null) {
			return null;
		}
		map.put("AAC009",
				DictionaryUtil.getDictName(Constants.AAC009, map.get("AAC009")));
		map.put("AAC008", Constants.AAC008.get(map.get("AAC008")));
		map.put("AAC005", DictionaryUtil.getDictName(
				Constants.AAC005,
				map.get("AAC005").length() == 1 ? "0" + map.get("AAC005") : map
						.get("AAC005")));
		map.put("AAC004", Constants.AAC004.get(map.get("AAC004")));
		map.put("GJ", Constants.GJ.get(map.get("GJ")));
		map.put("ZJLX", Constants.ZJLX.get(map.get("ZJLX")));
		map.put("JHRZJLX", Constants.ZJLX.get(map.get("JHRZJLX")));

		return map;
	}
}
