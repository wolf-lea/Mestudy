package com.tecsun.sisp.adapter.modules.card.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;



import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import com.google.gson.JsonObject;
import com.tecsun.sisp.adapter.common.util.CommUtil;
import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.DictionaryUtil;
import com.tecsun.sisp.adapter.common.util.ImageChangeUtil;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.common.util.card.CardUtil;
import com.tecsun.sisp.adapter.modules.card.entity.request.AreaManage;
import com.tecsun.sisp.adapter.modules.card.entity.request.CsspApplyBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.PersonInfoVO;
import com.tecsun.sisp.adapter.modules.card.entity.request.PixelBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.ResultBusMakeDetal;
import com.tecsun.sisp.adapter.modules.card.entity.request.XgApplyBean;
import com.tecsun.sisp.adapter.modules.card.entity.response.BankDataVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardAccomplishVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardProgressVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CsspCardPickUpVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CsspPolicePhotoVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.DeviceRegistVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.SettingCardQuery;
import com.tecsun.sisp.adapter.modules.card.entity.response.SocialOrgVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.SssmInfoVO;
import com.tecsun.sisp.adapter.modules.card.service.impl.CsspServiceImpl;
import com.tecsun.sisp.adapter.modules.card.service.impl.SssmBusServiceImpl;
import com.tecsun.sisp.adapter.modules.card.service.impl.TestCardInfoServiceImpl;
import com.tecsun.sisp.adapter.modules.card.service.impl.TestCsspServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.request.PersonBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBusBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.common.entity.response.DictionaryVO;
import com.tecsun.sisp.adapter.modules.common.entity.response.PicVO;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.so.entity.response.TSBSssmVO;
import com.tecsun.sisp.adapter.modules.so.service.impl.SecQueryServiceImpl;

/**
 * 社保卡业务：社保卡申领功能 文档已出，请勿随意修改入参出参
 * 
 * @ClassName: CsspServiceController
 */
@Controller
@RequestMapping(value = "/adapter/cssp")
public class CsspServiceController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(CsspServiceController.class);

	@Autowired
	private CsspServiceImpl csspService;
	@Autowired
	private TestCsspServiceImpl testCsspService;
	@Autowired
	private TestCardInfoServiceImpl testCardInfoService;
	@Autowired
	private CommServiceImpl commService;
	@Autowired
	private SecQueryServiceImpl secQueryService;
	@Autowired
	private SssmBusServiceImpl sssmBusService;

	// 像素公司接口url
	public static String PIXEL_URL = Config.getInstance().get("pixel_url");
	// 社保卡申请是否允许重复申请
	public static String CARD_APPLY_IS_RE = Config.getInstance().get("card_apply_is_re");

	/**
	 * canApply 查询参保人是否可以申领社保卡 本市户籍的可以办理（不管有没有在本市参保） 已经在本市办理过社保卡的，不给办理
	 * 外市户籍，在本市参保，可以办理【若无接口，则由当地网点人员根据用户提供的参保证明进行人工判断】
	 * 外市户籍，不在本市参保，不给办理【若无接口，则由当地网点人员根据用户提供的参保证明进行人工判断】 statusCode 200 可以申领 201
	 * 参保人姓名或身份证号不能为空 301 已申领，请勿重复申领 302 制卡中（查询制卡进度（各地市制卡进度接口）获取） 303
	 * 未参保，没有本地参保的记录 0 查询参保人信息失败
	 */
	@RequestMapping(value = "/canApply", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result canApply(@RequestBody SecQueryBean bean) throws Exception {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "查询是否可以申领社保卡失败，请联系管理员";
		String sfzh = bean.getSfzh();
		String xm = bean.getXm();
		if (StringUtils.isNotEmpty(sfzh)&& StringUtils.isNotEmpty(xm)) {

			try {
				// 社保卡申请是否允许重复申请,若不允许--新申领功能不允许重复
				if (!Constants.CONFIG_YES.equals(CARD_APPLY_IS_RE)) {

					// 其他查询是否已申领社保卡方法开始
					// 调补换卡系统中的BASIC_PERSON_INFO表中是否有此人的信息，
					List<SssmInfoVO> list = sssmBusService.canApply4sssm(bean);
					// 对返回的list对象做判断 ， 若list对象大于0，说明有此人， 不能做申领；

					// 其他查询是否已申领社保卡方法结束
					// 查询之前是否在一体化平台申请bean.getXm()
					List<CsspApplyBean> personList = csspService.isExistApplyPersonInfo4Cssp(bean.getSfzh(),null);
					if (personList != null && !personList.isEmpty()
							&& personList.get(0).getApplyId() != 0) {
						String date = personList.get(0).getCreateTime();
						String channel = DictionaryUtil.getDictName(Constants.CHANNELCODE,
							personList.get(0).getRemark());// 备注被渠道类型
					message = "证件号" + bean.getSfzh() + "您于" + date + ",通过" + channel
							+ "渠道完成申领，请勿重复申领";
						statusCode = "301";
					} else if (!list.isEmpty() && list.size() > 0) {
						String date = list.get(0).getAddDate();
						String channel = DictionaryUtil.getDictName(Constants.CHANNELCODE, list
								.get(0).getApplyChannel());
						message = "证件号" + bean.getSfzh() + "您于" + date + ",通过" + channel
								+ "渠道完成申领，请勿重复申领";
						statusCode = "301";
					}
					if (!"301".equals(statusCode)) {
						// 根据各地市制卡进度接口修改开始
						System.out.println("=======申领资格查询-调卡管======");
						String result = CardUtil.getCardProgress(bean.getSfzh(), bean.getXm());
						if (StringUtils.isNotEmpty(result)) {
							result = (new StringBuilder("<shengting>")).append(result)
									.append("</shengting>").toString();
							Document document = DocumentHelper.parseText(result);
							Element element = document.getRootElement();
							Element description = element.element("ERR");
							String err = description.getText();
							SettingCardQuery cardQuery = new SettingCardQuery();
							if (err.equals("OK")) {
								cardQuery = CardUtil.getSettingCardQuery(element, err);
								//修改2018/03/21
								int _validtag = 0;
								try {
									_validtag = Integer.parseInt(cardQuery.getValidtag());
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (StringUtils.isEmpty(cardQuery.getApplytime()) || _validtag < 0) {
									statusCode = Constants.RESULT_MESSAGE_SUCCESS;
									message = "可以申领";
								} else {
									String applyTime = cardQuery.getApplytime();
									if (StringUtils.isNotEmpty(applyTime)) {
										SimpleDateFormat sdf = new SimpleDateFormat(
												"yyyyMMddHHmmss");
										SimpleDateFormat sdf1 = new SimpleDateFormat(
												"yyyy-MM-dd HH:mm:ss");
										applyTime = sdf1.format(sdf.parse(applyTime));
									}
									message = "证件号" + bean.getSfzh() + "您于" + applyTime
											+ "完成申领，请勿重复申领";
									statusCode = "301";
								}
							} else {
								statusCode = Constants.RESULT_MESSAGE_SUCCESS;
								message = "可以申领";
							}
						}
						// 根据各地市制卡进度接口修改结束
					}

				} else {
					// 允许重复申领
					logger.info("=====重复申领======");
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
					message = "可以重复申领(仅测试用)";
				}

			} catch (Exception e) {
				e.printStackTrace();
				statusCode = Constants.RESULT_MESSAGE_ERROR;
				logger.error("查询是否可申领信息异常bean:\t" + JsonHelper.javaBeanToJson(bean));
				logger.error("查询是否可申领信息信息异常：", e);

				message = "查询是否可申领信息失败";
			}
		} else {
			message = "参保人姓名或身份证号不能为空";
			statusCode = Constants.RESULT_MESSAGE_EMPTY;
		}
		return result(statusCode, message);
	}

	/**
	 * checkCertValidity 检测证件是否过期 查询方式1、调用第三方接口获取证件有效期、2、前端传入（默认）
	 */
	@RequestMapping(value = "/checkCertValidity", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result checkCertValidity(@RequestBody CsspApplyBean bean) throws Exception {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "检测失败";
		String sfzh = bean.getSfzh();
		String xm = bean.getXm();
		if (StringUtils.isNotEmpty(sfzh) && StringUtils.isNotEmpty(xm)) {
			try {
				// 调用第三方接口获取证件有效期
				boolean status = true;
				if (status) {
					message = "证件有效";
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
				} else {
					message = "证件有效期已过";
				}
				// 前端传入（默认）
				if (StringUtils.isNotEmpty(bean.getCertValidity())) {
					// 查询证件有效期
					long dayDiff = CommUtil.dayDiff(bean.getCertValidity(),
							CommUtil.getNowDateLongStr("yyyyMMdd"), "yyyyMMdd");
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

	// 获取银行信息 各地市提供接口
	@RequestMapping(value = "/getBank", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getBank(@RequestBody SecQueryBean bean) throws Exception {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "查询银行信息失败";
		try {
			if ((Constants.TSB).equals(bean.getChannelcode())) {
				if (StringUtils.isNotBlank(bean.getDeviceid())) {
					String code = commService.getCode4Sisp(bean.getDeviceid());
					if (StringUtils.isBlank(code)) {
						message = "设备要先与区域做绑定";
						statusCode = "301";
						return result(statusCode, message);
					}
				} else {
					message = "设备编码不能为空";
					statusCode = Constants.RESULT_MESSAGE_EMPTY;
					return result(statusCode, message);
				}
			}
			// 根据实际情况修改获取银行信息
			BankDataVO vo = new BankDataVO();
			List<BankDataVO> list = testCsspService.getBankData4Other(vo);
			List<BankDataVO> allBanks = new ArrayList<BankDataVO>(0);
			if (list != null && !list.isEmpty()) {
				allBanks = getCsspBankTree("0", list);
			}
			message = "查询银行信息成功";
			return result(Constants.RESULT_MESSAGE_SUCCESS, message, allBanks);
		} catch (Exception e) {
			logger.error("获取银行信息失败：", e);
		}
		return result(statusCode, message);
	}

	private List<BankDataVO> getCsspBankTree(String parentCode, List<BankDataVO> banks) {
		List<BankDataVO> list = new ArrayList<BankDataVO>();
		for (BankDataVO bank : banks) {
			if (parentCode.equals(bank.getParentCode())) {
				bank.setChildBanks(getCsspBankTree(bank.getBankCode(), banks));
				list.add(bank);
			}
		}
		return list;
	}

	/**
	 * getPolicePhotos获取公安库图片或省卡库图片 各地市提供接口
	 */
	@RequestMapping(value = "/getPolicePhotos", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getPolicePhotos(@RequestBody SecQueryBean bean) throws Exception {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "获取公安库图片失败";
		try {
			if (StringUtils.isNotBlank(bean.getSfzh())) {
				List<CsspPolicePhotoVO> list = testCsspService.getPolicePhotos4Other(bean);
				if (list != null && !list.isEmpty()) {
					message = "获取公安库图片成功";
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
					return result(statusCode, message, list.get(0));
				} else {
					message = "查无此人相片";
				}
			} else {
				message = "身份证号不能为空";
				statusCode = Constants.RESULT_MESSAGE_EMPTY;
			}
		} catch (Exception e) {
			logger.error("获取公安库图片失败", e);
		}
		return result(statusCode, message);
	}

	/**
	 * 根据各地市要求选择：社保卡申领：相片处理需一段时间(此时参保人相片第三方处理尚未返回)，调用此方法把个人申领信息存入本地数据库,
	 * 等待定时器扫描上传 根据实际情况改写此接口，入参出参不变 各地市提供接口 把采集到的数据存到公服个人基本信息表
	 * T_YTH_BASIC_PERSON_INFO 把采集到的数据存到社保卡申请信息表 T_YTH_APPLYCARD_INFO 性别
	 * 01男性;02女性;03未知 民族存数据库为编码
	 */
	@RequestMapping(value = "/applyCard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result applyCard(@RequestBody CsspApplyBean bean,HttpServletRequest request) throws Exception {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "申领失败";
		CsspCardPickUpVO csspCardPickupVO = new CsspCardPickUpVO();
		// 身份证&姓名
		if (StringUtils.isEmpty(bean.getSfzh()) && StringUtils.isEmpty(bean.getXm())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "姓名或身份证不能为空");
		}
		/*if (StringUtils.isEmpty(bean.getAccountProties())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "请选择户口性质");
		}*/
		if (StringUtils.isEmpty(bean.getSex())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "性别不能为空");
		}
		if (StringUtils.isEmpty(bean.getGuoji())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "国籍不能为空");
		}
		if (StringUtils.isEmpty(bean.getMobile())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "手机号码不能为空");
		}
		/*if (StringUtils.isEmpty(bean.getCertValidity())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "证件有效期不能为空");
		}*/
		if (StringUtils.isEmpty(bean.getDistinctId())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "区县不能为空");
		}
		if (!StringUtils.isEmpty(bean.getCertValidity())) {
			String cert = bean.getCertValidity();
			if (cert.length() == 17) {
				bean.setCertValidity(cert.substring(0, 4) + "." + cert.substring(4, 6) + "."
						+ cert.substring(6, 8) + "-" + cert.substring(9, 13) + "."
						+ cert.substring(13, 15) + "." + cert.substring(15, 17));
			}

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
		// 渠道类型：德生宝
		if ((Constants.TSB).equals(bean.getChannelcode())) {
			// 设备号
			if (StringUtils.isEmpty(bean.getDeviceid())) {
				return result(Constants.RESULT_MESSAGE_EMPTY, "设备号不能为空");
			}
			// 领卡地址
			if (StringUtils.isBlank(bean.getTsbAddress())) {
				bean.setCardAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
			} else
				bean.setCardAddress(bean.getTsbAddress());
			bean.setAddrType("3");
		}
		if (StringUtils.isEmpty(bean.getCertType())) {
			bean.setCertType("01");// 证件类型身份证（户口簿）
		}
		/*
		 * 根据项目确定 //签名信息 if (bean.getSignphotoId() == 0) { return
		 * result(Constants.RESULT_MESSAGE_EMPTY, "个人签名不能为空"); } //领卡地址 if
		 * (StringUtils.isBlank(bean.getTsbAddress())) { return
		 * result(Constants.RESULT_MESSAGE_EMPTY, "领卡地址不能为空"); } if
		 * (StringUtils.isEmpty(bean.getBankCode()) ||
		 * StringUtils.isEmpty(bean.getBankName())) { return
		 * result(Constants.RESULT_MESSAGE_EMPTY, "请选择银行信息"); } if
		 * (StringUtils.isEmpty(bean.getPersonType())) { return
		 * result(Constants.RESULT_MESSAGE_EMPTY, "请选择人员类别"); }
		 */
		// 个人相片、身份证正、反面
		if (bean.getPhotoBuzId() == 0 || bean.getPicupId() == 0 || bean.getPicdownId() == 0) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "个人相片、身份证正、反面照不能为空");
		}
		try {
			if (StringUtils.isBlank(bean.getBirthday()))
				bean.setBirthday(CommUtil.getBirthdayByCertNum(bean.getSfzh()));
			// 查询之前是否在平台申请
			// CsspApplyBean personInfo =
			// csspService.isExistApplyPersonInfo4Cssp(bean.getXm(),
			// bean.getSfzh());
			List<CsspApplyBean> personList = csspService.isExistApplyPersonInfo4Cssp(bean.getXm(),
					bean.getSfzh());
			if (personList != null && !personList.isEmpty()) {
				bean.setPersonId(personList.get(0).getPersonId());
				// 社保卡申请是否允许重复申请,若不允许--新申领功能不允许重复
				if (!Constants.CONFIG_YES.equals(CARD_APPLY_IS_RE)) {
					if (personList.get(0).getApplyId() != 0) {
						String date = personList.get(0).getCreateTime();
						message = "证件号" + bean.getSfzh() + "于" + date + "已申领，请勿重复申领";
						return result(Constants.RESULT_MESSAGE_ERROR, message);
					}
				}
			}
			// 参保人相片信息（相片未处理）
			PicBean picBean = new PicBean();
			picBean.setPicId(bean.getPhotoBuzId());
			picBean = commService.getPicture4Cssp(picBean);
			if (picBean == null || picBean.getPicId() == 0) {
				message = "参保人相片图片不存在";
				logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPhotoBuzId());
				return this.result(Constants.RESULT_MESSAGE_PARAM, message);
			}
			// 身份证正面信息
			String picUpPath = commService.photoIsExist4Cssp(bean.getPicupId(),
					Constants.PICTURE_TYPE_103);
			if (Constants.RESULT_MESSAGE_PARAM.equals(picUpPath)) {
				message = "身份证正面图片不存在";
				logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicupId());
				return this.result(Constants.RESULT_MESSAGE_PARAM, message);
			}
			// 身份证反面信息
			String picDownPath = commService.photoIsExist4Cssp(bean.getPicdownId(),
					Constants.PICTURE_TYPE_104);
			if (Constants.RESULT_MESSAGE_PARAM.equals(picDownPath)) {
				message = "身份证反面图片不存在";
				logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicdownId());
				return this.result(Constants.RESULT_MESSAGE_PARAM, message);
			}
			// 电子签名信息 德生宝端需检测
			if (bean.getSignphotoId() != 0) {
				String signPhotoPath = commService.photoIsExist4Cssp(bean.getSignphotoId(),
						Constants.PICTURE_TYPE_105);
				if (Constants.RESULT_MESSAGE_PARAM.equals(signPhotoPath)) {
					message = "电子签名图片不存在";
					logger.error(message + "：sfzh=" + bean.getSfzh() + " picId="
							+ bean.getSignphotoId());
					return this.result(Constants.RESULT_MESSAGE_PARAM, message);
				}
			}
			// 根据各地市要求 编写电子申领表
			/*
			 * bean.setScannPhoto("电子申领表"); if
			 * (StringUtils.isEmpty(bean.getScannPhoto())) { return
			 * result(Constants.RESULT_MESSAGE_EMPTY, "电子申领表不能为空"); }
			 */
			bean.setPhotoSource("02");
			bean.setIsUpload("N");
			bean.setUploadNum(0);
			int status = 0;
			PersonBean personBean = new PersonBean();
			BeanUtils.copyProperties(bean, personBean);
			personBean.setPhone(bean.getMobile());
			if (bean.getPersonId() != 0) {
				// 把个人基本信息表 T_YTH_BASIC_PERSON_INFO更新为采集到的数据
				status = commService.updatePersonInfo4Cssp(personBean);
				if (status > 0)
					status = 0;
				else
					return this.result(Constants.RESULT_MESSAGE_ERROR, "保存个人信息出错");
			}
			if (bean.getPersonId() == 0) {
				// 把采集到的数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO
				status = commService.insertPersonInfo4Cssp(personBean);
				if (status > 0)
					bean.setPersonId(personBean.getPersonId());
				status = 0;
			}
			// 把采集到的数据存入社保卡申请信息表
			if (bean.getPersonId() != 0) {
				if (StringUtils.isNotBlank(bean.getAddrType())
						|| StringUtils.isNotBlank(bean.getCardAddress())) {
					status = csspService.insertCardApplyAddr4Cssp(bean);// 把采集到的数据存入社保卡申请领卡地址表
					if (status < 0) {
						message = "保存领卡信息失败";
						return result(statusCode, message);
					}
				}
				bean.setRemark(bean.getChannelcode());// 备注为申请渠道
				status = csspService.insertCardApplyInfo4Cssp(bean);
				if (status > 0) {
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
					message = "提交申领成功";

					// 孝感--采集数据上传补换卡系统开始
					XgApplyBean applyBean = new XgApplyBean();
					applyBean.setName(bean.getXm());
					applyBean.setSex(bean.getSex().equals("03") ? "05" : bean.getSex());
					String org = "";
					SocialOrgVO slVO = new SocialOrgVO();
					slVO = sssmBusService.queryIssuingOrg4sssm(bean.getDistinctId());
					if (slVO == null) {
						slVO = new SocialOrgVO();
						slVO.setAddress("");
						slVO.setId("0");
						slVO.setSoName("-");
					}
					if (StringUtils.isNotEmpty(bean.getDistinctId())) {
						org = slVO.getSoName();
						applyBean.setCertIssuingOrg(org);
					}
					
					
					applyBean.setCertType("03");
					applyBean.setCertNum(bean.getSfzh());
					applyBean.setCertValidity(bean.getCertValidity());
					applyBean.setNation(bean.getNation());
					applyBean.setBirthday(bean.getBirthday());
					applyBean.setAddress(bean.getAddress());
					applyBean.setPhone(bean.getPhone());
					applyBean.setMobile(bean.getMobile());
					applyBean.setStatus("00");
					// applyBean.setAddDate(bean.getCreateTime());
					applyBean.setPersonType(bean.getPersonType());
					applyBean.setPersonStatus(bean.getPersonStatus());//人员状态
					applyBean.setAccountProties(bean.getAccountProties());
					applyBean.setZipCode(bean.getZipCode());
					applyBean.setGuoji(bean.getGuoji());
					applyBean.setIsEnabled("01");
					applyBean.setAddUserId(bean.getDistinctId());
					applyBean.setOperatorId(bean.getDistinctId());//2018/03/12
					// 参保人历史记录表
					applyBean.setBusType("01");// 申领
					applyBean.setBusStatus("103");// 业务已受理
					applyBean.setHisOperatorId(bean.getDistinctId());// 操作人
					// 个人申领业务表
					applyBean.setBusDataType("00");// 正常发起业务数据
					applyBean.setBankOpenType("01");// 银行开户类型 -- 新申领/新开户
					applyBean.setProcessDataType("01");// 表单数据处理 -- 本网点录入
					applyBean.setMakeCardType("00");// 非现场制卡
					applyBean.setApStatus("03");// 业务已受理
					applyBean.setNetType("02");// 网点类型 -- 社保机构
					
					DeviceRegistVO dv = csspService.querybyOrgId4Sisp(request.getParameter("deviceid"));
					//20180313下午加补换卡
					applyBean.setNetCode(dv.getOrgId());
				
					long i = Long.parseLong(dv.getOrgId());	
					TSBSssmVO tb = sssmBusService.getOrgInfo4Sssm(i);
					applyBean.setNetName(tb.getOrgName());
					
					//1.根据tsbid获取网点信息
					//2.填充补换卡相关网点字段
					int orgid =0;
					try {
						orgid=Integer.parseInt(dv.getOrgId());
						applyBean.setNetId(orgid);// 所属网点id
						
						applyBean.setCardIssueNetId(orgid);// 领卡网点ID
					} catch (Exception e) {
						// TODO: handle exception
					}
					applyBean.setChannel(bean.getRemark());
					if (StringUtils.isNotEmpty(bean.getAgentCertNo())) {
						applyBean.setApplyType("02");// 别人代办
					} else {
						applyBean.setApplyType("01");// 自行办理
					}

					applyBean.setIswritedata(1);// 是否网点已对申领表单录入数据 -- 已经录入
					applyBean.setCardIssueNetType("01");// 领卡网点类型 -- 社保机构

					Integer s = sssmBusService.insertBasePersonInfo4sssm(applyBean);

					if (s > 0) {
						applyBean.setPersonId(applyBean.getId());
						Integer r = sssmBusService.insertBusPersonalApply4sssm(applyBean);// 返回业务表主键id
						if (r > 0) {
							applyBean.setBusId(applyBean.getApId());
							Integer t = sssmBusService.insertBusHisInfo4sssm(applyBean);
							if (t > 0) {
								// 孝感--采集数据上传补换卡系统结束

								String receiveAddress = "";
								if (StringUtils.isNotEmpty(bean.getDistinctId())) {
									receiveAddress =   "(地址:" + bean.getInsuredAddr()
											+ ")";
								}
								csspCardPickupVO.setSfzh(bean.getSfzh());
								csspCardPickupVO.setXm(bean.getXm());
								csspCardPickupVO.setTsbAddress(receiveAddress);
								return result(statusCode, message, csspCardPickupVO);
							}
						}
					}
				}
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

	/**
	 * 根据各地市要求选择：社保卡申领：调用此方法把个人申领信息直接上传制卡(此时个人相片已存在,即时申请) 根据实际情况改写此接口，入参出参不变
	 * 各地市提供接口 把采集到的数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO 把采集到的数据存到社保卡申请信息表
	 * T_YTH_APPLYCARD_INFO 性别 01男性;02女性;03未知 民族存数据库为编码
	 * 如需通过定时器继续上传，则需将个人相片状态改为成功
	 */
	@RequestMapping(value = "/applyCardDirect", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result applyCardDirect(@RequestBody CsspApplyBean bean) throws Exception {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "申领失败";
		CsspCardPickUpVO csspCardPickupVO = new CsspCardPickUpVO();
		// 身份证&姓名
		if (StringUtils.isEmpty(bean.getSfzh()) && StringUtils.isEmpty(bean.getXm())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "姓名或身份证不能为空");
		}
		if (StringUtils.isEmpty(bean.getSex())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "性别不能为空");
		}
		if (StringUtils.isEmpty(bean.getAccountProties())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "请选择户口性质");
		}
		if (StringUtils.isEmpty(bean.getGuoji())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "国籍不能为空");
		}
		if (StringUtils.isEmpty(bean.getMobile())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "手机号码不能为空");
		}
		if (StringUtils.isEmpty(bean.getCertValidity())) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "证件有效期不能为空");
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
		if ((Constants.TSB).equals(bean.getChannelcode())) {
			// 设备号
			if (StringUtils.isEmpty(bean.getDeviceid())) {
				return result(Constants.RESULT_MESSAGE_EMPTY, "设备号不能为空");
			}
			// 领卡地址
			if (StringUtils.isBlank(bean.getTsbAddress())) {
				bean.setCardAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
			} else
				bean.setCardAddress(bean.getTsbAddress());
			bean.setAddrType("3");
		}
		if (StringUtils.isEmpty(bean.getCertType())) {
			bean.setCertType("01");// 证件类型身份证（户口簿）
		}
		/*
		 * 根据项目确定 //签名信息 if (bean.getSignphotoId() == 0) { return
		 * result(Constants.RESULT_MESSAGE_EMPTY, "个人签名不能为空"); } //领卡地址 if
		 * (StringUtils.isBlank(bean.getTsbAddress())) { return
		 * result(Constants.RESULT_MESSAGE_EMPTY, "领卡地址不能为空"); } if
		 * (StringUtils.isEmpty(bean.getBankCode()) ||
		 * StringUtils.isEmpty(bean.getBankName())) { return
		 * result(Constants.RESULT_MESSAGE_EMPTY, "请选择银行信息"); } if
		 * (StringUtils.isEmpty(bean.getPersonType())) { return
		 * result(Constants.RESULT_MESSAGE_EMPTY, "请选择人员类别"); }
		 */

		// 个人相片、身份证正、反面、签名信息
		if (bean.getPhotoBuzId() == 0 || bean.getPicupId() == 0 || bean.getPicdownId() == 0) {
			return result(Constants.RESULT_MESSAGE_EMPTY, "个人相片、身份证正、反面照不能为空");
		}
		try {// 出生日期
			if (StringUtils.isBlank(bean.getBirthday()) && "01".equals(bean.getCertType()))
				bean.setBirthday(CommUtil.getBirthdayByCertNum(bean.getSfzh()));
			if (StringUtils.isNotBlank(bean.getBirthday())) {
				try {
					CommUtil.getFormatDateString(bean.getBirthday(), "yyyy-MM-dd");
				} catch (Exception e) {
					return this
							.result(Constants.RESULT_MESSAGE_PARAM, "出生日期不符合格式:yyyy-MM-dd,请重新输入");
				}
			}
			// 查询之前是否在本平台申请
			List<CsspApplyBean> personList = csspService.isExistApplyPersonInfo4Cssp(bean.getXm(),
					bean.getSfzh());
			if (personList != null && !personList.isEmpty()) {
				bean.setPersonId(personList.get(0).getPersonId());
				if (!Constants.CONFIG_YES.equals(CARD_APPLY_IS_RE)) {
					if (personList.get(0).getApplyId() != 0) {
						String date = personList.get(0).getCreateTime();
						message = "证件号" + bean.getSfzh() + "于" + date + "已申领，请勿重复申领";
						return result(Constants.RESULT_MESSAGE_ERROR, message);
					}
				}
			}
			// 个人相片信息
			String perPhotoPath = commService.photoIsExist4Cssp(bean.getPhotoBuzId(),
					Constants.PICTURE_TYPE_102);
			if (Constants.RESULT_MESSAGE_PARAM.equals(perPhotoPath)) {
				message = "个人相片不存在";
				logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPhotoBuzId());
				return this.result(Constants.RESULT_MESSAGE_PARAM, message);
			} else {
				bean.setPhoto64String(ImageChangeUtil.getImageStr(perPhotoPath));
			}
			// 身份证正面信息
			String picUpPath = commService.photoIsExist4Cssp(bean.getPicupId(),
					Constants.PICTURE_TYPE_103);
			if (Constants.RESULT_MESSAGE_PARAM.equals(picUpPath)) {
				message = "身份证正面图片不存在";
				logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicupId());
				return this.result(Constants.RESULT_MESSAGE_PARAM, message);
			} else {
				bean.setIdcard64StringUp(ImageChangeUtil.getImageStr(picUpPath));
			}
			// 身份证反面信息
			String picDownPath = commService.photoIsExist4Cssp(bean.getPicdownId(),
					Constants.PICTURE_TYPE_104);
			if (Constants.RESULT_MESSAGE_PARAM.equals(picDownPath)) {
				message = "身份证反面图片不存在";
				logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicdownId());
				return this.result(Constants.RESULT_MESSAGE_PARAM, message);
			} else {
				bean.setIdcard64StringDown(ImageChangeUtil.getImageStr(picDownPath));
			}
			// 电子签名信息
			if (bean.getSignphotoId() != 0) {
				String signPhotoPath = commService.photoIsExist4Cssp(bean.getSignphotoId(),
						Constants.PICTURE_TYPE_105);
				if (Constants.RESULT_MESSAGE_PARAM.equals(signPhotoPath)) {
					message = "电子签名图片不存在";
					logger.error(message + "：sfzh=" + bean.getSfzh() + " picId="
							+ bean.getSignphotoId());
					return this.result(Constants.RESULT_MESSAGE_PARAM, message);
				} else {
					bean.setSign64String(ImageChangeUtil.getImageStr(signPhotoPath));
				}
			}
			// 根据各地市要求 编写电子申领表
			bean.setScannPhoto("电子申领表");
			if (StringUtils.isEmpty(bean.getScannPhoto())) {
				return result(Constants.RESULT_MESSAGE_EMPTY, "电子申领表不能为空");
			}

			// 调用接口上传制卡 返回 result ??????????????
			boolean result = true;
			if (result == true) {
				bean.setIsUpload("Y");
				bean.setUploadtime(CommUtil.getNowDateLongStr());
			} else {
				message = "申领失败";
				bean.setIsUpload("N");
				// 如需通过定时器 (UploadScedule) 继续上传，则需将个人相片状态改为成功
				// 定时上传社保卡申请数据：条件isUpload（N 表示尚未上传或失败）参保人相片状态（01 成功）
				PicBusBean perBusBean = new PicBusBean();
				perBusBean.setPicId(bean.getPhotoBuzId());
				perBusBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_01);
				perBusBean.setPicType(Constants.PICTURE_TYPE_102);
				commService.updatePictureBus4Cssp(perBusBean);
				//
			}
			bean.setPhotoSource("01");
			bean.setUploadNum(1);
			int status = 0;
			PersonBean personBean = new PersonBean();
			BeanUtils.copyProperties(bean, personBean);
			personBean.setPhone(bean.getMobile());
			if (bean.getPersonId() != 0) {
				// 把个人基本信息表 T_YTH_BASIC_PERSON_INFO更新为采集到的数据
				status = commService.updatePersonInfo4Cssp(personBean);
				if (status > 0)
					status = 0;
				else
					return this.result(Constants.RESULT_MESSAGE_ERROR, "保存个人信息出错");
			}
			if (bean.getPersonId() == 0) {
				// 把采集到的数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO
				status = commService.insertPersonInfo4Cssp(personBean);
				if (status > 0)
					bean.setPersonId(personBean.getPersonId());
				status = 0;
			}
			// 把采集到的数据存入社保卡申请信息表
			if (bean.getPersonId() != 0) {
				if (StringUtils.isNotBlank(bean.getAddrType())
						|| StringUtils.isNotBlank(bean.getCardAddress())) {
					status = csspService.insertCardApplyAddr4Cssp(bean);// 把采集到的数据存入社保卡申请领卡地址表
					if (status < 0) {
						message = "保存领卡信息失败";
						return result(statusCode, message);
					}
				}
				bean.setRemark(bean.getChannelcode());// 备注为申请渠道
				status = csspService.insertCardApplyInfo4Cssp(bean);
				if (status > 0) {
					// 测试 社保卡申请-存入制卡进度--项目使用可删除
					CardProgressVO vo = new CardProgressVO();
					vo.setSfzh(bean.getSfzh());
					vo.setXm(bean.getXm());
					vo.setApplytime(CommUtil.getNowDateLongStr());
					vo.setStatus("资料已成功提交，正在审核中");
					testCsspService.insertCardProgress4Other(vo);
					// 测试
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
					message = "申领成功";
					csspCardPickupVO.setSfzh(bean.getSfzh());
					csspCardPickupVO.setXm(bean.getXm());
					csspCardPickupVO.setTsbAddress(bean.getCardAddress());
					return result(statusCode, message, csspCardPickupVO);
				}
			} else {
				logger.error("applyCardDirect 保存个人基本信息失败" + JsonHelper.javaBeanToJson(bean));
				statusCode = Constants.RESULT_MESSAGE_ERROR;
				message = "申领失败";
			}
		} catch (Exception e) {
			logger.error("applyCardDirect 保存数据错误：" + JsonHelper.javaBeanToJson(bean));
			logger.error("保存数据错误", e);
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "申领失败";
		}
		return result(statusCode, message);
	}

	/**
	 * 快速处理照片接口 1.只检测是否符合标准，不进行处理
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/fastProcessPic", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result fastProcessPic(@RequestBody PixelBean bean) {
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "操作失败";
		try {
			if (StringUtils.isEmpty(bean.getPicType())) {
				return result(Constants.RESULT_MESSAGE_EMPTY, "照片规格不可为空", null);
			}
			String photoBase64 = "";
			if (StringUtils.isNotBlank(bean.getBase64String())) {
				photoBase64 = bean.getBase64String();
			} else if (bean.getPicId() != 0) {
				String picPath = commService.photoIsExist4Cssp(bean.getPicId(),
						Constants.PICTURE_TYPE_101);
				if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
					message = "用户图片不存在";
					logger.error(" picId=" + bean.getPicId() + message);
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
			System.out.println("fastProcessPic-result:" + result);
			Map<String, Object> map = JsonHelper.jsonToMap(result);
			return result((String) map.get("statusCode"), (String) map.get("message"), null);
		} catch (Exception e) {
			logger.error("快速处理照片fastProcessPic-bean:\t" + JsonHelper.javaBeanToJson(bean), e);
			return error("服务器异常，请稍后再试!", null);
		}
	}

	/**
	 * 上传照片接口 1、对图片进行预处理，像素公司调用callbackPicInfo返回处理图片
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/uploadPicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result uploadPicInfo(@RequestBody PixelBean bean) {
		try {
			String callbackUrl = Config.getInstance().get("callback_pic_url");
			bean.setCallbackUrl(callbackUrl);
			if (org.apache.commons.lang3.StringUtils.isEmpty(bean.getCallbackUrl())
					|| org.apache.commons.lang3.StringUtils.isEmpty(bean.getPicType())
					|| StringUtils.isEmpty(bean.getSfzh()) || StringUtils.isEmpty(bean.getXm())) {
				return result(Constants.RESULT_MESSAGE_EMPTY, "业务参数不全", null);
			}
			String statusCode = Constants.RESULT_MESSAGE_ERROR;
			String message = "操作失败";
			String photoBase64 = "";
			long picId = 0;
			long perPicId = 0;
			boolean flag = true;
			if (StringUtils.isNotBlank(bean.getBase64String())) {
				// 相片（待处理）
				PicBean picBean = new PicBean();
				picBean.setPicBase64(bean.getBase64String());
				picBean.setPicType(Constants.PICTURE_TYPE_101);
				picId = commService.uploadPicture4Cssp(picBean);
				if (picId == -1)
					return this.result(Constants.RESULT_MESSAGE_PARAM, "入参不正确，传入的图像信息有误");
				photoBase64 = bean.getBase64String();
			} else if (bean.getPicId() != 0) {
				picId = bean.getPicId();
				String picPath = commService.photoIsExist4Cssp(picId, Constants.PICTURE_TYPE_101);
				if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
					message = "用户图片不存在";
					logger.error("sfzh=" + bean.getSfzh() + " picId=" + picId + message);
					return this.result(Constants.RESULT_MESSAGE_PARAM, message);
				}
				photoBase64 = ImageChangeUtil.getImageStr(picPath);
				picId = bean.getPicId();
			} else {
				return result(Constants.RESULT_MESSAGE_EMPTY, "图片信息不可为空", null);
			}
			if (picId > 0) {
				PicBean perPicBean = new PicBean();
				perPicBean.setPicType(Constants.PICTURE_TYPE_102);
				perPicId = commService.insertPicture4Cssp(perPicBean);
				if (perPicId <= 0)
					return this.result(Constants.RESULT_MESSAGE_PARAM, "存储图片信息错误");
				// 待处理图片关联业务
				PicBusBean picBusBean = new PicBusBean();
				picBusBean.setPicId(picId);
				picBusBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_00);
				picBusBean.setPicType(Constants.PICTURE_TYPE_101);
				long picBusId = commService.insertPictureBus4Cssp(picBusBean);
				if (picBusId > 0) {
					// 调用像素接口预处理照片
					JsonObject json = new JsonObject();
					json.addProperty("base64String", photoBase64);
					json.addProperty("pic_type", bean.getPicType());
					json.addProperty("callbackUrl", bean.getCallbackUrl());
					json.addProperty("Bus_id", perPicId);// 返回信息修改相片id（已处理）
					String url = PIXEL_URL + "uploadPicInfo";
					String result = "";
					PersonBean personBean = commService.getBasicPersonInfo4Cssp(bean.getSfzh(),
							bean.getXm());
					long personId = 0;
					if (personBean != null)
						personId = personBean.getPersonId();
					try {
						result = (String) getWebClient(url, json, String.class);
					} catch (Exception e) {
						// 更新图片业务表中照片状态设置为预处理失败
						PicBusBean failedBean = new PicBusBean();
						failedBean.setPicId(picId);
						failedBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_04);
						commService.updatePictureBus4Cssp(failedBean);
						logger.error(
								"连接像素服务器失败:"
										+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
												.format(new Date()), e);
					}
					// 添加响应信息判断
					if ("".equals(result)) {
						flag = false;// 表示预处理失败
					}
					if (flag) {
						Map<String, Object> map = JsonHelper.jsonToMap(result);
						message = (String) map.get("message");
						if ((Constants.RESULT_MESSAGE_SUCCESS).equals(map.get("statusCode"))) {// 成功
							// 预存相片（已处理）信息关联业务、人员
							PicBusBean perBusBean = new PicBusBean();
							perBusBean.setPicId(perPicId);
							perBusBean.setPersonId(personId);
							perBusBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_00);
							perBusBean.setPicType(perPicBean.getPicType());
							commService.insertPersonPic4Cssp(perBusBean);
							commService.insertPictureBus4Cssp(perBusBean);
							message = "预处理成功";
							statusCode = (String) map.get("statusCode");
							// 预处理照片关联业务、人员
							commService.photoTOPersonAndBus4Cssp(picId, personId,
									Constants.PICTURE_TYPE_101, statusCode, message);
						}

						PicBusBean failedBean = new PicBusBean();
						failedBean.setPicId(picId);
						failedBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_03);
						commService.updatePictureBus4Cssp(failedBean);
					}
				}
			}
			String pic_status = Constants.PIXEL_BUSINESS_STATUS_03;
			if (!flag) {
				pic_status = Constants.PIXEL_BUSINESS_STATUS_04;
				message = "预处理失败";
			}
			csspService
					.insertTemInfo4Cssp(bean.getSfzh(), bean.getXm(), picId, pic_status, message);// 无论预处理失败或者成功，都保存上传的照片
			PicVO picVO = new PicVO();
			picVO.setPicId(perPicId);
			return new Result(statusCode, message, picVO);
		} catch (Exception e) {

			logger.info("uploadPicInfo-bean:\t" + JsonHelper.javaBeanToJson(bean), e);
			return error("服务器异常，请稍后再试!", null);
		}
	}

	/**
	 * 照片处理返回接口
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/callbackPicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result callbackPicInfo(@RequestBody PixelBean bean, @Context HttpServletRequest request) {
		 try {
	            if (org.apache.commons.lang3.StringUtils.isEmpty(bean.getBusId())
	                    || org.apache.commons.lang3.StringUtils.isEmpty(bean.getStatus())) {
	                return new Result("300", "入参不正确，入参不全", null);
	            }
	            if (!ImageChangeUtil.isIntegerByString(bean.getBusId()))
	                return new Result("300", "入参不正确，入参不全", null);
	            String status = bean.getStatus();
	            if(!Constants.PIXEL_BUSINESS_STATUS_01.equals(status)
	                    && !Constants.PIXEL_BUSINESS_STATUS_02.equals(status))
	                return new Result("300", "入参不正确，入参不全", null);
	            if (Constants.PIXEL_BUSINESS_STATUS_01.equals(status)) {
	                if (org.apache.commons.lang3.StringUtils.isEmpty(bean.getBase64String())) {
	                    return new Result("300", "入参不正确，入参不全", null);
	                }
	            }
	            // 首先查询这个图片id是否存在
	            PicBean picBean = new PicBean();
	            picBean.setPicId(Long.parseLong(bean.getBusId()));
	            picBean = commService.getPicture4Cssp(picBean);
	            if (picBean == null || picBean.getPicId() == 0) return new Result("300", "业务ID不存在", null);
	            if (Constants.PIXEL_BUSINESS_STATUS_01.equals(status)) {
	                // 保存图片
	                picBean.setPicBase64(bean.getBase64String());
	                picBean.setPicType(Constants.PICTURE_TYPE_102);
	                long picId = commService.updatePicture4Cssp(picBean);
	                if(picId<0){
	                    bean.setMessage(bean.getMessage()+",传入的图像信息有误,无法保存");
	                }
	            }
	            //相片（已处理）信息修改关联业务信息
	            PicBusBean perBusBean = new PicBusBean();
	            perBusBean.setPicId(Long.parseLong(bean.getBusId()));
	            perBusBean.setPicStatus(status);
	            perBusBean.setPicType(Constants.PICTURE_TYPE_102);
	            perBusBean.setPicMessage(bean.getMessage());
	            commService.updatePictureBus4Cssp(perBusBean);
	            return ok("回调成功", null);
	        } catch (Exception e) {
	            logger.info("uploadPicInfo-bean:\t" + JsonHelper.javaBeanToJson(bean),e);
	            logger.error("服务器异常，请稍后再试!", e);
	            return error("服务器异常，请稍后再试!", null);
	        }

	}

	// 获取孝感市区县相关信息(对接补换卡系统)
	@RequestMapping(value = "/getArea", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getArea(@RequestBody AreaManage bean) throws Exception {
		logger.info("---------------CsspServiceController: getArea---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String statusCode = Constants.RESULT_MESSAGE_ERROR;
		String message = "查询区县信息失败";

		List<AreaManage> result = new ArrayList<AreaManage>();

		if (bean.getRegionalcode() != null && !bean.getRegionalcode().equals("")) {
			result = sssmBusService.queryByParentId4sssm(bean.getRegionalcode());
			statusCode = Constants.RESULT_MESSAGE_SUCCESS;
			message = "查询区县信息成功";

		} else {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询区县信息失败————父级区域ID不能为空";
		}

		return result(statusCode, message, result);
	}

	/**
	 * 卡位置查询
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/getApplyCard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getApplyCard(@RequestBody PersonInfoVO bean) {
		ResultBusMakeDetal resultBusMakeDetal = new ResultBusMakeDetal();
		// String[] data = new String[] {};
		JSONObject data = new JSONObject();
		String kwz = "";
		if (StringUtils.isNotEmpty(bean.getCertNum()) && !" ".equals(bean.getCertNum())) {
			// 先去滞留卡中找卡位置
			resultBusMakeDetal = sssmBusService.getCardwz4sssm(bean.getCertNum()); // 用身份证号到滞留卡表中找条件regis为1对应的盒ID,箱ID，卡编号
			if (resultBusMakeDetal != null) {
				if (resultBusMakeDetal.getOrgAddress() != null
						&& resultBusMakeDetal.getBinNo() != null
						&& resultBusMakeDetal.getBoxNo() != null
						&& resultBusMakeDetal.getCardsn() != null) {
					if (resultBusMakeDetal.getOrgAddress().endsWith("网点")) {
						resultBusMakeDetal.setOrgAddress(resultBusMakeDetal.getOrgAddress()
								.substring(0, resultBusMakeDetal.getOrgAddress().length() - 2));
					}
					kwz = resultBusMakeDetal.getOrgAddress() + "网点" + resultBusMakeDetal.getBinNo()
							+ "箱" + resultBusMakeDetal.getBoxNo() + "盒"
							+ resultBusMakeDetal.getCardsn() + "张";
					if (resultBusMakeDetal.getBATCHNO() != null) {
						kwz = resultBusMakeDetal.getOrgAddress() + "网点"
								+ resultBusMakeDetal.getBinNo() + "箱"
								+ resultBusMakeDetal.getBoxNo() + "盒"
								+ resultBusMakeDetal.getCardsn() + "张"
								+ resultBusMakeDetal.getBATCHNO() + "批次";
					}
					String name = resultBusMakeDetal.getName();
					String certnum = resultBusMakeDetal.getCertNum();
					data.put("kwz", kwz);
					data.put("name", name);
					data.put("certnum", certnum);
					if (resultBusMakeDetal.getRegisStatus().equals("1")) {
						String kzt = "未申领";
						String kzg = "是";
						// data = new String[] { kwz, kzt, kzg, name, certnum };
						data.put("kzt", kzt);
						data.put("kzg", kzg);

						return result("200", "查找成功", data);
						// System.out.println("==="+page.getData().get(0)+page.getData().get(1)+page.getData().get(2));
					} else {
						// List<BusApplyPicVO>
						// applybeans=csspQueryService.getApplytime(bean.getCertNum());
						String kzt = "已申领";
						String kzg = "否";
						data.put("kzt", kzt);
						data.put("kzg", kzg);
						// data = new String[] { kwz, kzt, kzg, name, certnum };
						return result("201", "卡已发放", data);
						// System.out.println("==="+page.getData().get(0)+page.getData().get(1)+page.getData().get(2));
					}
				} else {
					return result("202", "查找失败");
				}
			} else {
				return result("202", "卡片不存在");
			}
		} else {
			return result("0", "未发现身份证信息！");
		}
	}

	
}
