package com.tecsun.sisp.adapter.modules.so.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.adapter.common.util.BeanValidatorUtil;
import com.tecsun.sisp.adapter.common.util.CommUtil;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.so.entity.request.PaymentQueryBean;
import com.tecsun.sisp.adapter.modules.so.entity.response.BlanceOfMIAccountVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.EIIssueBasicVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.EIIssueRecordVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.EIPayRecordVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.FiveInsuranceRecordVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.FiveInsuranceVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.IncureTypeVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.InsuranceVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.MAIncureVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.MARecordVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.MIRecordVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.TsInsuranceVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.URREIIssueBasicVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.URREIIssueRecordVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.URREIPayBasicVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.URREIPayRecord;
import com.tecsun.sisp.adapter.modules.so.entity.response.UserCBInfoVO;
import com.tecsun.sisp.adapter.modules.so.service.impl.SecQueryServiceImpl;

/**
 * Created by danmeng on 2016/8/9. 社保查询业务接口、社保卡业务接口
 */
@SuppressWarnings("unchecked")
@Controller
@RequestMapping(value = "/adapter/rest")
public class SecQueryController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(SecQueryController.class);

	@Autowired
	private SecQueryServiceImpl secQueryService;

	/*
	 * 参保个人信息
	 */
	@RequestMapping(value = "/getBasicInsuredInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getBasicInsuredInfo(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------SecQueryController: getInsuranceInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String result = Constants.RESULT_MESSAGE_ERROR;
		String message = "查询失败";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");
		bean.setAac002(bean.getSfzh());
		// TsInsuranceVO tsInsuranceVO = new TsInsuranceVO();
		InsuranceVO insuranceVO = new InsuranceVO();
		try {
			List<TsInsuranceVO> list = new ArrayList<TsInsuranceVO>();
			list = secQueryService.getPersonInfo4Other(bean);
			if (list != null && list.size() > 0) {
				insuranceVO.setXm(list.get(0).getXm());
				insuranceVO.setSfzh(list.get(0).getSfzh());
				insuranceVO.setCbdw(list.get(0).getDwmc());
				insuranceVO.setGender(list.get(0).getXb());
				insuranceVO.setIdentity(list.get(0).getGrsf());
				insuranceVO.setPersonStatus(Constants.RYZT_STATUS.get(list.get(0).getRyzt()));
			}
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
			message = "查询成功";
			result = Constants.RESULT_MESSAGE_SUCCESS;
		} catch (Exception e) {
			message = "查询失败";
			logger.error("查询个人基本信息异常：", e);
		}
		return result(result, message, insuranceVO);
	}

	/*
	 * 查询参保信息险种
	 */
	@RequestMapping(value = "/getIncureTypeList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getIncureTypeList(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------SecQueryController: getIncureTypeList---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String result = Constants.RESULT_MESSAGE_ERROR;
		String message = "查询失败";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");
		bean.setAac002(bean.getSfzh());
		Page<IncureTypeVO> page = new Page<IncureTypeVO>(bean.getPageno(), bean.getPagesize());
		List<IncureTypeVO> list = new ArrayList<IncureTypeVO>();
		try {
			// bean.setPage(page);
			// 获取个人编号
			List<TsInsuranceVO> vo = secQueryService.getPersonInfo4Other(bean);
			if (vo != null && vo.size() > 0) {
				bean.setGrbh(vo.get(0).getGrbh());
				bean.setPage(page);
				List<TsInsuranceVO> cbxxlist = secQueryService.getPersonCvrgInfo4Other(bean);
				if (!cbxxlist.isEmpty()) {

					for (TsInsuranceVO tsvo : cbxxlist) {

						IncureTypeVO itVO = new IncureTypeVO();
						itVO.setCbxz(Constants.CBXZ_TYPE.get(tsvo.getXzlx()));
						itVO.setCbzt(Constants.CYT_CBZT_STATUS.get(tsvo.getCbzt()));
						itVO.setDwmc(tsvo.getDwmc());
						itVO.setJbsj(tsvo.getJbsj());
						itVO.setKsny(tsvo.getKsny());
						itVO.setZzny(tsvo.getZzny());
						list.add(itVO);
					}
					message = "查询成功";
					result = Constants.RESULT_MESSAGE_SUCCESS;
				}
			} else {
				message = "没有该人员的参保信息";
			}
			for (int i = 0; i < list.size(); i++) {
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			page.setData(list);
		} catch (Exception e) {
			message = "查询失败";
			logger.error("查询参保险种信息异常：", e);
		}
		return result(result, message, page);
	}

	@RequestMapping(value = "/getUserCBInfoForApp", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getUserCBInfoForApp(@RequestBody PaymentQueryBean bean) throws Exception {
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");

		Page<UserCBInfoVO> page = new Page<UserCBInfoVO>(bean.getPageno(), bean.getPagesize());
		List<UserCBInfoVO> list = new ArrayList<UserCBInfoVO>();
		try {
			bean.setPage(page);

			list = secQueryService.getUserCBInfo4other(bean);

			for (int i = 0; i < list.size(); i++) {
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}

			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：", e);
		}
		return result(result, message, page);

	}

	/**
	 * 医疗保险缴费基本信息(医疗缴费信息)
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMIPaymentBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMIPaymentBasicInfo(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------MIQueryController: getMIPaymentBasicInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		// insertTransLog(bean,
		// Constants.BUSINESSCODE.get("getMIPaymentBasicInfo"));
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");

		Page<FiveInsuranceRecordVO> page = new Page<FiveInsuranceRecordVO>(bean.getPageno(),
				bean.getPagesize());
		List<FiveInsuranceRecordVO> list = new ArrayList<FiveInsuranceRecordVO>();
		bean.setAae140("'31','32'");
		bean.setAac002(bean.getSfzh());
		try {
			// bean.setPage(page);
			List<TsInsuranceVO> grbhList = secQueryService.getPersonCount4Other(bean);
			if (grbhList.size() > 0) {
				bean.setPage(page);// 这句必须放在真正查询数据之前
				List<TsInsuranceVO> tsiList = secQueryService.getIncureyList4Other(bean);
				for (TsInsuranceVO vo : tsiList) {
					FiveInsuranceRecordVO recordVO = new FiveInsuranceRecordVO();
					recordVO.setSsq(vo.getSsq());
					recordVO.setJfjs(vo.getJfjs());
					recordVO.setJnze(vo.getSjje());
					recordVO.setCbdq(vo.getCbdq());
					recordVO.setXzlx(Constants.CBXZ_TYPE.get(vo.getXzlx()));

					recordVO.setDzrq(vo.getDzrq());
					recordVO.setSjje(vo.getSjje());
					recordVO.setKx(Constants.CBXZ_TYPE.get(vo.getXzlx()));
					list.add(recordVO);
				}
				message = "查询成功";
				result = Constants.RESULT_MESSAGE_SUCCESS;
			}
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：", e);
		}
		return result(result, message, page);
	}

	/**
	 * 医疗保险缴费记录
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMIPaymentRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMIPaymentRecord(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------MIQueryController: getMIPaymentRecord---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");

		Page<MIRecordVO> page = new Page<MIRecordVO>(bean.getPageno(), bean.getPagesize());
		List<MIRecordVO> list = new ArrayList<MIRecordVO>();
		try {
			bean.setPage(page);
			list = secQueryService.getMIPaymentRecord4Other(bean);

			for (int i = 0; i < list.size(); i++) {
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}

			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：", e);
		}
		return result(result, message, page);
	}

	/**
	 * 获取医疗账户(MA)基本信息()
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMABasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMABasicInfo(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------MAQueryController: getMABasicInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		// insertTransLog(bean, Constants.BUSINESSCODE.get("getMABasicInfo"));
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");

		MAIncureVO insuranceVO = new MAIncureVO();
		try {
			// insuranceVO.setXm("王胜波"); // 姓名
			// insuranceVO.setSfzh("422201198612142119"); // 身份证号码
			// insuranceVO.setGrybzhye("350.00"); // 个人医保账户余额
			insuranceVO = secQueryService.getMABasicInfo4Other(bean);
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：", e);
		}
		return result(statusCode, message, insuranceVO);
	}

	/**
	 * 获取医疗账户(MA)交易记录
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMADetail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMADetail(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------MAQueryController: getMADetail---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");

		Page<MARecordVO> page = new Page<MARecordVO>(bean.getPageno(), bean.getPagesize());
		List<TsInsuranceVO> list = new ArrayList<TsInsuranceVO>();
		List<MARecordVO> marlist = new ArrayList<MARecordVO>();
		bean.setAac002(bean.getSfzh());
		bean.setAae140("'31','32'");
		try {
			// bean.setPage(page);
			List<TsInsuranceVO> grbhList = secQueryService.getPersonCount4Other(bean);
			if (grbhList.size() <= 1) {
				bean.setPage(page);
				list = secQueryService.getHealthAcountList4Other(bean);
				for (TsInsuranceVO vo : list) {
					MARecordVO mvo = new MARecordVO();
					mvo.setJysj(vo.getYlrq());
					mvo.setLx(vo.getYllx());
					mvo.setFyfsd(vo.getYldd());
					mvo.setZfy(String.valueOf(vo.getYlje()));
					marlist.add(mvo);
				}
				message = "查询成功";
				result = Constants.RESULT_MESSAGE_SUCCESS;
			} else {
				result = Constants.RESULT_MESSAGE_EXCEPTION;
				message = bean.getAac002() + "存在多个个人编号，请到窗口合并";
			}

			page.setData(marlist);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：", e);
		}
		return result(result, message, page);
	}

	/**
	 * @author fuweifeng
	 * @date 2015-7-7
	 * @version
	 * @return
	 * @throws 医疗保险缴费合计
	 */
	@RequestMapping(value = "/getHealthIncureSum", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getHealthIncureSum(@RequestBody PaymentQueryBean bean) throws Exception {
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "操作成功";
		TsInsuranceVO tsInsuranceVO = new TsInsuranceVO();
		// 险别的基本信息；
		bean.setAae140("'31','32'");
		bean.setAac002(bean.getSfzh());
		List<TsInsuranceVO> list = secQueryService.getHealthIncureySum4Other(bean);
		if (!list.isEmpty() && list.size() > 0) {

			tsInsuranceVO.setXm(list.get(0).getXm());// 姓名；
			tsInsuranceVO.setSfzh(list.get(0).getSfzh());// 身份证号码；

			if (list.get(0).getYblx().startsWith(Constants.RYLB_CODE)) {// 医保类型；
				tsInsuranceVO.setYblx(Constants.RYLB_VALUE1);
			} else {
				tsInsuranceVO.setYblx(Constants.RYLB_VALUE2);
			}
		}
		// 医疗缴费合计
		List<TsInsuranceVO> sumList = secQueryService.getPaySum4Other(bean);
		if (!sumList.isEmpty() && sumList.size() > 0) {
			for (int i = 0; i < sumList.size(); i++) {

				// 基金来源=10，款项=20 为单位实缴； 基金来源=10，款项=10 为个人； 基金来源=20，款项=10 为 财政；
				if ("10".equals(sumList.get(i).getJjly()) && "20".equals(sumList.get(i).getKx())) { // 单位实缴
					tsInsuranceVO.setDwjfhj(sumList.get(i).getSjje());
				} else if ("10".equals(sumList.get(i).getJjly())
						&& "10".equals(sumList.get(i).getKx())) { // 财政
					tsInsuranceVO.setGrjfhj(sumList.get(i).getSjje());
				} else if ("20".equals(sumList.get(i).getJjly())
						&& "10".equals(sumList.get(i).getKx())) { // 个人实缴
					tsInsuranceVO.setCzhj(Double.valueOf(sumList.get(i).getSjje()));
				}
			}
		}
		// 个人医疗帐户余额；
		PaymentQueryBean queryBean = new PaymentQueryBean();
		queryBean.setAac002(bean.getAac002());
		List<TsInsuranceVO> grbhList = secQueryService.getPersonCount4Other(queryBean);
		String grbh = "";
		if (!grbhList.isEmpty() && grbhList.size() > 0) {
			for (int j = 0; j < grbhList.size(); j++) {
				if (j == grbhList.size() - 1) {
					grbh += "'" + grbhList.get(j).getGrbh() + "'";
				} else {
					grbh += "'" + grbhList.get(j).getGrbh() + "',";
				}
			}
			bean.setAac001(grbh);
			List<TsInsuranceVO> accountList = secQueryService.getylAccountYe4Other(bean);
			if (!accountList.isEmpty() && accountList.size() > 0) {
				tsInsuranceVO.setYlye(accountList.get(0).getYlye());
			}
		}
		// 参保状态
		if (grbhList.size() == 1) {
			bean.setAac001(grbhList.get(0).getGrbh());
			List<TsInsuranceVO> cbxzlist = secQueryService.getPersonCvrgInfo4Other(bean);
			if (!cbxzlist.isEmpty() && cbxzlist.size() > 0) {
				tsInsuranceVO.setCbzt(Constants.CYT_CBZT_STATUS.get(cbxzlist.get(0).getCbzt()));
			}
		}
		return result(result, message, tsInsuranceVO);
	}

	/**
	 * 获取养老保险(EI)缴费基本信息(缴费合计)
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEIPaymentBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getEIPaymentBasicInfo(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------EIQueryController: getEIPaymentBasicInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		// insertTransLog(bean, Constants.BUSINESSCODE.get("setStart"));
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");

		FiveInsuranceVO insuranceVO = new FiveInsuranceVO();
		try {
			bean.setAac002(bean.getSfzh());
			bean.setAae140("'11','12','15','14'");
			List<TsInsuranceVO> grbhList = secQueryService.getPersonCount4Other(bean);
			if (grbhList.size() == 1) {
				insuranceVO.setXm(grbhList.get(0).getXm());// 姓名；
				insuranceVO.setSfzh(bean.getSfzh());
				bean.setGrbh(grbhList.get(0).getGrbh());
				List<TsInsuranceVO> cbztlist = secQueryService.getPersonCvrgInfo4Other(bean);
				if (!cbztlist.isEmpty() && cbztlist.size() > 0) {
					insuranceVO.setCbzt(Constants.CYT_CBZT_STATUS.get(cbztlist.get(0).getCbzt()));// 参保状态
				}
			}

			// 养老缴费合计
			List<TsInsuranceVO> sumList = secQueryService.getPaySum4Other(bean);
			double count = 0;
			String grjf = "";
			String dwjf = "";
			if (!sumList.isEmpty() && sumList.size() > 0) {
				for (int i = 0; i < sumList.size(); i++) {
					if (Constants.DW_CODE.equals(sumList.get(i).getKx())) {
						dwjf = sumList.get(i).getSjje();// 单位缴纳
					} else if (Constants.GR_CODE.equals(sumList.get(i).getKx())) {
						grjf = sumList.get(i).getSjje();// 个人缴纳
					}

				}
				insuranceVO.setDwjnhj(dwjf);
				insuranceVO.setGrjnhj(grjf);
				count = Double.parseDouble(dwjf) + Double.parseDouble(grjf);
				insuranceVO.setJnze(String.valueOf(count));// 缴纳总额
				message = "查询成功";
				statusCode = Constants.RESULT_MESSAGE_SUCCESS;
			}

			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：", e);
		}
		return result(statusCode, message, insuranceVO);
	}

	/**
	 * 获取养老保险(EI)缴费记录
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEIPaymentRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getEIPaymentRecord(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------EIQueryController: getEIPaymentRecord---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String result = Constants.RESULT_MESSAGE_ERROR;
		String message = "查询失败";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");

		Page<EIPayRecordVO> page = new Page<EIPayRecordVO>(bean.getPageno(), bean.getPagesize());
		List<EIPayRecordVO> list = new ArrayList<EIPayRecordVO>();
		try {
			// bean.setPage(page);
			bean.setAac002(bean.getSfzh());
			bean.setAae140("'11','12','15','14'");
			List<TsInsuranceVO> grbhList = secQueryService.getPersonCount4Other(bean);
			if (grbhList.size() <= 1) {
				bean.setPage(page);
				List<TsInsuranceVO> EIlist = secQueryService.getIncureyList4Other(bean);
				if (!EIlist.isEmpty()) {
					for (TsInsuranceVO vo : EIlist) {
						EIPayRecordVO recordVO = new EIPayRecordVO();
						recordVO.setXz(Constants.CBXZ_TYPE.get(vo.getXzlx()));
						recordVO.setSsq(vo.getSsq());
						recordVO.setJfjs(vo.getJfjs());
						recordVO.setJnze(vo.getSjje());
						recordVO.setDzrq(vo.getDzrq());
						recordVO.setCbdq(vo.getCbdq());
						recordVO.setKx(Constants.MONEYITEMTYPE.get(vo.getKx()));
						recordVO.setSjje(vo.getSjje());
						list.add(recordVO);
						result = Constants.RESULT_MESSAGE_SUCCESS;
						message = "查询成功";
					}
				}
			} else {
				result = Constants.RESULT_MESSAGE_EXCEPTION;
				message = bean.getAac002() + "存在多个个人编号，请到窗口合并";
			}
			for (int i = 0; i < list.size(); i++) {
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}

			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：", e);
		}
		return result(result, message, page);
	}

	/**
	 * 获取养老保险(EI)待遇发放基本信息
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEIIssueBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getEIIssueBasicInfo(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------EIQueryController: getEIIssueBasicInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		// insertTransLog(bean, Constants.BUSINESSCODE.get("setStart"));
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");

		EIIssueBasicVO insuranceVO = new EIIssueBasicVO();
		try {
			// insuranceVO.setXm("王胜波"); // 姓名
			// insuranceVO.setSfzh("422201198612142119"); // 身份证号码
			// insuranceVO.setYljffzje("5000.00"); // 养老金发放总金额
			insuranceVO = secQueryService.getEIIssueBasicInfo4Other(bean);
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询养老基本信息异常：", e);
		}
		return result(statusCode, message, insuranceVO);
	}

	/**
	 * 获取养老保险(EI)待遇发放记录
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEIIssueRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getEIIssueRecord(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------EIQueryController: getEIIssueRecord---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");

		Page<EIIssueRecordVO> page = new Page<EIIssueRecordVO>(bean.getPageno(), bean.getPagesize());
		List<EIIssueRecordVO> list = new ArrayList<EIIssueRecordVO>();
		try {

			bean.setPage(page);
			list = secQueryService.getEIIssueRecord4Other(bean);

			for (int i = 0; i < list.size(); i++) {
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}

			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：", e);
		}
		return result(result, message, page);
	}

	/**
	 * 获取生育保险(BI)缴纳基本信息(缴费合计)
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBIPaymentBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getBIPaymentBasicInfo(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------BIQueryController: getBIPaymentBasicInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");

		FiveInsuranceVO insuranceVO = new FiveInsuranceVO();
		try {

			// 险别的基本信息；
			bean.setAac002(bean.getSfzh());
			bean.setAae140("'51'");// 生育保险险种
			List<TsInsuranceVO> grbhList = secQueryService.getPersonCount4Other(bean);
			if (grbhList.size() == 1) {
				bean.setGrbh(grbhList.get(0).getGrbh());
				List<TsInsuranceVO> list = secQueryService.getPersonCvrgInfo4Other(bean);
				if (!list.isEmpty() && list.size() > 0) {
					insuranceVO.setCbzt(Constants.CYT_CBZT_STATUS.get(list.get(0).getCbzt()));// 参保状态
					insuranceVO.setXm(list.get(0).getXm());// 姓名；
					insuranceVO.setSfzh(bean.getSfzh());
				}
			}
			// 生育保险缴费合计
			List<TsInsuranceVO> sumList = secQueryService.getPaySum4Other(bean);
			double count = 0;
			String grjf = "0";
			String dwjf = "0";
			if (!sumList.isEmpty() && sumList.size() > 0) {
				for (int i = 0; i < sumList.size(); i++) {
					if (Constants.DW_CODE.equals(sumList.get(i).getKx())) {
						dwjf = CommUtil.isEmptyStr(sumList.get(i).getSjje()) ? "0" : sumList.get(i)
								.getSjje();// 单位缴纳
					} else if (Constants.GR_CODE.equals(sumList.get(i).getKx())) {
						grjf = CommUtil.isEmptyStr(sumList.get(i).getSjje()) ? "0" : sumList.get(i)
								.getSjje();// 个人缴纳
					}

				}
				insuranceVO.setDwjnhj(dwjf);
				insuranceVO.setGrjnhj(grjf);
				count = Double.parseDouble(dwjf) + Double.parseDouble(grjf);
				insuranceVO.setJnze(String.valueOf(count));// 缴纳总额
				message = "查询成功";
				result = Constants.RESULT_MESSAGE_SUCCESS;
			}

			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：", e);
		}
		return result(result, message, insuranceVO);
	}

	/**
	 * 获取生育保险(BI)缴纳记录
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBIPaymentRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getBIPaymentRecord(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------BIQueryController: getBIPaymentRecord---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");

		Page<FiveInsuranceRecordVO> page = new Page<FiveInsuranceRecordVO>(bean.getPageno(),
				bean.getPagesize());
		List<FiveInsuranceRecordVO> list = new ArrayList<FiveInsuranceRecordVO>();
		try {

			// bean.setPage(page);
			bean.setAac002(bean.getSfzh());
			bean.setAae140("'51'");// 生育保险险种
			List<TsInsuranceVO> grbhList = secQueryService.getPersonCount4Other(bean);
			if (grbhList.size() <= 1) {
				bean.setPage(page);
				List<TsInsuranceVO> gslist = secQueryService.getIncureyList4Other(bean);
				if (!gslist.isEmpty()) {
					for (TsInsuranceVO vo : gslist) {
						FiveInsuranceRecordVO recordVO = new FiveInsuranceRecordVO();
						recordVO.setSsq(vo.getSsq());
						recordVO.setJfjs(vo.getJfjs());
						recordVO.setJnze(vo.getSjje());
						recordVO.setCbdq(vo.getCbdq());
						recordVO.setXzlx(Constants.CBXZ_TYPE.get(vo.getXzlx()));
						recordVO.setDzrq(vo.getDzrq());
						recordVO.setSjje(vo.getSjje());
						list.add(recordVO);
					}
					message = "查询成功";
					statusCode = Constants.RESULT_MESSAGE_SUCCESS;
				}
			} else {
				statusCode = Constants.RESULT_MESSAGE_EXCEPTION;
				message = bean.getAac002() + "存在多个个人编号，请到窗口合并";
			}
			for (int i = 0; i < list.size(); i++) {
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}

			page.setData(list);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：", e);
		}
		return result(statusCode, message, page);
	}

	/**
	 * 获取工伤保险(EII)缴纳基本信息(缴费合计)
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEIIPaymentBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getEIIPaymentBasicInfo(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------EIIQueryController: getEIIPaymentBasicInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");

		FiveInsuranceVO insuranceVO = new FiveInsuranceVO();
		try {
			// 险别的基本信息；
			bean.setAac002(bean.getSfzh());
			bean.setAae140("'41'");// 工伤保险险种
			List<TsInsuranceVO> grbhList = secQueryService.getPersonCount4Other(bean);
			if (grbhList.size() == 1) {
				bean.setGrbh(grbhList.get(0).getGrbh());
				List<TsInsuranceVO> list = secQueryService.getPersonCvrgInfo4Other(bean);
				if (!list.isEmpty() && list.size() > 0) {
					insuranceVO.setCbzt(Constants.CYT_CBZT_STATUS.get(list.get(0).getCbzt()));// 参保状态
					insuranceVO.setXm(list.get(0).getXm());// 姓名；
					insuranceVO.setSfzh(bean.getSfzh());
				}
			}
			// 工伤保险缴费合计
			List<TsInsuranceVO> sumList = secQueryService.getPaySum4Other(bean);
			double count = 0;
			String grjf = "0";
			String dwjf = "0";
			if (!sumList.isEmpty() && sumList.size() > 0) {
				for (int i = 0; i < sumList.size(); i++) {
					if (Constants.DW_CODE.equals(sumList.get(i).getKx())) {
						dwjf = CommUtil.isEmptyStr(sumList.get(i).getSjje()) ? "0" : sumList.get(i)
								.getSjje();// 单位缴纳
					} else if (Constants.GR_CODE.equals(sumList.get(i).getKx())) {
						grjf = CommUtil.isEmptyStr(sumList.get(i).getSjje()) ? "0" : sumList.get(i)
								.getSjje();// 个人缴纳
					}

				}
				insuranceVO.setDwjnhj(dwjf);
				insuranceVO.setGrjnhj(grjf);
				count = Double.parseDouble(dwjf) + Double.parseDouble(grjf);
				insuranceVO.setJnze(String.valueOf(count));// 缴纳总额
				message = "查询成功";
				statusCode = Constants.RESULT_MESSAGE_SUCCESS;
			}
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：", e);
		}
		return result(statusCode, message, insuranceVO);
	}

	/**
	 * 获取工伤保险(EII)缴纳记录
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEIIPaymentRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getEIIPaymentRecord(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------EIIQueryController: getEIIPaymentRecord---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");

		Page<FiveInsuranceRecordVO> page = new Page<FiveInsuranceRecordVO>(bean.getPageno(),
				bean.getPagesize());
		List<FiveInsuranceRecordVO> list = new ArrayList<FiveInsuranceRecordVO>();
		try {
			//
			// bean.setPage(page);
			bean.setAac002(bean.getSfzh());
			bean.setAae140("'41'");// 工伤保险险种
			List<TsInsuranceVO> grbhList = secQueryService.getPersonCount4Other(bean);
			if (grbhList.size() <= 1) {
				bean.setPage(page);
				List<TsInsuranceVO> gslist = secQueryService.getIncureyList4Other(bean);
				if (!gslist.isEmpty()) {
					for (TsInsuranceVO vo : gslist) {
						FiveInsuranceRecordVO recordVO = new FiveInsuranceRecordVO();
						recordVO.setSsq(vo.getSsq());
						recordVO.setJfjs(vo.getJfjs());
						recordVO.setJnze(vo.getSjje());
						recordVO.setCbdq(vo.getCbdq());
						recordVO.setXzlx(Constants.CBXZ_TYPE.get(vo.getXzlx()));
						recordVO.setDzrq(vo.getDzrq());
						recordVO.setSjje(vo.getSjje());
						list.add(recordVO);
					}
					message = "查询成功";
					result = Constants.RESULT_MESSAGE_SUCCESS;
				}
			} else {
				result = Constants.RESULT_MESSAGE_EXCEPTION;
				message = bean.getAac002() + "存在多个个人编号，请到窗口合并";
			}

			for (int i = 0; i < list.size(); i++) {
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}

			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：", e);
		}
		return result(result, message, page);
	}

	/**
	 * 获取失业保险(UEI)缴纳基本信息(缴费合计)
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUEIPaymentBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getUEIPaymentBasicInfo(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------UEIQueryController: getUEIPaymentBasicInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		// insertTransLog(bean,
		// Constants.BUSINESSCODE.get("getUEIPaymentBasicInfo"));
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");

		FiveInsuranceVO insuranceVO = new FiveInsuranceVO();
		// 险别的基本信息；
		bean.setAac002(bean.getSfzh());
		bean.setAae140("'21'");
		insuranceVO.setSfzh(bean.getSfzh());
		double count = 0;// 缴纳总额
		String dwjn = "0";
		String grjn = "0";
		List<TsInsuranceVO> grbhList = secQueryService.getPersonCount4Other(bean);
		if (grbhList.size() == 1) {
			bean.setGrbh(grbhList.get(0).getGrbh());
			
			List<TsInsuranceVO> list = secQueryService.getPersonCvrgInfo4Other(bean);
			if (!list.isEmpty() && list.size() > 0) {
				
				insuranceVO.setCbzt(Constants.CYT_CBZT_STATUS.get(list.get(0).getCbzt()));// 参保状态
				insuranceVO.setXm(list.get(0).getXm());// 姓名；
				insuranceVO.setSfzh(bean.getSfzh());
			} 
		}
		try {
			// 失业保险缴费合计
			List<TsInsuranceVO> sumList = secQueryService.getPaySum4Other(bean);
			if (!sumList.isEmpty() && sumList.size() > 0) {
				for (int i = 0; i < sumList.size(); i++) {
					if (Constants.DW_CODE.equals(sumList.get(i).getKx())) {
						dwjn = CommUtil.isEmptyStr(sumList.get(i).getSjje()) ? "0" : sumList.get(i)
								.getSjje();
					} else if (Constants.GR_CODE.equals(sumList.get(i).getKx())) {
						grjn = CommUtil.isEmptyStr(sumList.get(i).getSjje()) ? "0" : sumList.get(i)
								.getSjje();// 个人缴纳
					}
				}
			}
			insuranceVO.setDwjnhj(dwjn);
			insuranceVO.setGrjnhj(grjn);
			count = Double.parseDouble(dwjn) + Double.parseDouble(grjn);
			insuranceVO.setJnze(String.valueOf(count));// 缴纳总额

			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：", e);
		}
		return result(statusCode, message, insuranceVO);
	}

	/**
	 * 获取失业保险(UEI)缴纳记录(失业缴费信息)
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUEIPaymentRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getUEIPaymentRecord(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------UEIQueryController: getUEIPaymentRecord---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");

		Page<FiveInsuranceRecordVO> page = new Page<FiveInsuranceRecordVO>(bean.getPageno(),
				bean.getPagesize());
		try {
			bean.setAac002(bean.getSfzh());
			// bean.setPage(page);
			bean.setAac002(bean.getAac002());
			bean.setAae140("'21'");
			List<FiveInsuranceRecordVO> firlist = new ArrayList<FiveInsuranceRecordVO>();
			List<TsInsuranceVO> grbhList = secQueryService.getPersonCount4Other(bean);
			if (grbhList.size() <= 1) {
				bean.setPage(page);
				List<TsInsuranceVO> list = secQueryService.getIncureyList4Other(bean);
				if (!list.isEmpty()) {
					for (TsInsuranceVO vo : list) {

						FiveInsuranceRecordVO firvo = new FiveInsuranceRecordVO();
						firvo.setCbdq(vo.getCbdq());
						firvo.setKx(Constants.MONEYITEMTYPE.get(vo.getKx()));
						firvo.setXzlx(Constants.CBXZ_TYPE.get(vo.getXzlx()));
						firvo.setSjje(vo.getSjje());
						firvo.setSsq(vo.getSsq());
						firvo.setJfjs(vo.getJfjs());
						firvo.setDzrq(vo.getDzrq());
						firlist.add(firvo);
					}

				}
				for (int i = 0; i < list.size(); i++) {
					BeanValidatorUtil.getFieldGetterNames(firlist.get(i));
				}
				message = "查询成功";
				result = Constants.RESULT_MESSAGE_SUCCESS;
				page.setData(firlist);
			} else {
				result = Constants.RESULT_MESSAGE_EXCEPTION;
				message = bean.getAac002() + "存在多个个人编号，请到窗口合并";
			}
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "失业保险列表查询信息异常：" + e.getMessage();
			e.printStackTrace();
			logger.error(message);
		}
		return result(result, message, page);
	}

	/**
	 * 获取城乡居民养老保险(URREI)缴纳基本信息
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getURREIPaymentBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getURREIPaymentBasicInfo(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------URREIQueryController: getURREIPaymentBasicInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		// insertTransLog(bean,
		// Constants.BUSINESSCODE.get("getURREIPaymentBasicInfo"));
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");

		URREIPayBasicVO insuranceVO = new URREIPayBasicVO();
		try {
			// insuranceVO.setXm("王胜波"); // 姓名
			// insuranceVO.setSfzh("422201198612142119"); // 身份证号码
			// insuranceVO.setCbzt("正在参保"); // 参保状态
			// insuranceVO.setJnze(1000.00); // 缴纳总额
			// insuranceVO.setDwjnhj(400.00); // 单位缴纳
			// insuranceVO.setGrjnhj(600.00); // 个人缴纳

			insuranceVO = secQueryService.getURREIPaymentBasicInfo4Other(bean);
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：", e);
		}
		return result(statusCode, message, insuranceVO);
	}

	/**
	 * 获取城乡居民养老保险(URREI)缴费历史纪录
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getURREIPaymentRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getURREIPaymentRecord(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------URREIQueryController: getURREIPaymentRecord---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");

		Page<URREIPayRecord> page = new Page<URREIPayRecord>(bean.getPageno(), bean.getPagesize());
		List<URREIPayRecord> list = new ArrayList<URREIPayRecord>();
		try {

			bean.setPage(page);
			list = secQueryService.getURREIPaymentRecord4Other(bean);

			for (int i = 0; i < list.size(); i++) {
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}

			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：", e);
		}
		return result(result, message, page);
	}

	/**
	 * 获取城乡居民养老保险(URREI)待遇发放基本信息
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getURREIIssueBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getURREIIssueBasicInfo(@RequestBody PaymentQueryBean bean) throws Exception {

		logger.info("---------------URREIQueryController: getURREIIssueBasicInfo---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		// insertTransLog(bean,
		// Constants.BUSINESSCODE.get("getURREIIssueBasicInfo"));
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");

		URREIIssueBasicVO insuranceVO = new URREIIssueBasicVO();
		try {
			// insuranceVO.setXm("王胜波"); // 姓名
			// insuranceVO.setSfzh("422201198612142119"); // 身份证号码
			// insuranceVO.setCbzt("正在参保"); // 参保状态
			// insuranceVO.setJnze(1000.00); // 缴纳总额
			// insuranceVO.setDwjnhj(400.00); // 单位缴纳
			// insuranceVO.setGrjnhj(600.00); // 个人缴纳

			insuranceVO = secQueryService.getURREIIssueBasicInfo4Other(bean);

			BeanValidatorUtil.getFieldGetterNames(insuranceVO);

		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：", e);
		}
		return result(statusCode, message, insuranceVO);
	}

	/**
	 * 城乡居民养老保险(URREI)待遇发放记录
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getURREIIssueRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getURREIIssueRecord(@RequestBody PaymentQueryBean bean) throws Exception {
		logger.info("-----getURREIIssueRecord-bean:{}-------", JsonHelper.javaBeanToJson(bean));
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";

		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(result, "姓名不能为空");
		bean.setAae140("'61'");
		Page<URREIIssueRecordVO> page = new Page<URREIIssueRecordVO>(bean.getPageno(),
				bean.getPagesize());
		List<URREIIssueRecordVO> urrlist = new ArrayList<URREIIssueRecordVO>();
		try {
			bean.setAac002(bean.getSfzh());
			// bean.setPage(page);
			List<TsInsuranceVO> grcountList = secQueryService.getQuitPayCode4Other(bean);
			if (!grcountList.isEmpty() && grcountList.size() > 0) {
				bean.setAac001(grcountList.get(0).getGrbh());
				bean.setGrbh(grcountList.get(0).getGrbh());
				bean.setPage(page);
				List<TsInsuranceVO> list = secQueryService.getQuitPayList4Other(bean);
				if (!list.isEmpty() && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						URREIIssueRecordVO urrvo = new URREIIssueRecordVO();
						urrvo.setFfsj(list.get(i).getSfny());
						urrvo.setFfyh(list.get(i).getFfjg());
						if (null != list.get(i).getFfzh()) {
							urrvo.setFfzh(this.getPersonAccount(list.get(i).getFfzh()));
						}
						urrvo.setFfzje(String.valueOf(list.get(i).getFfje()));
						urrlist.add(urrvo);
					}
				}
			}

			for (int i = 0; i < urrlist.size(); i++) {
				BeanValidatorUtil.getFieldGetterNames(urrlist.get(i));
			}
			message = "查询成功";
			result = Constants.RESULT_MESSAGE_SUCCESS;
			page.setData(urrlist);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：", e);
		}
		return result(result, message, page);
	}

	/**
	 * 医保账户余额查询
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBalanceOfMIAccount", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getBalanceOfMIAccount(@RequestBody SecQueryBean bean) throws Exception {

		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");

		BlanceOfMIAccountVO accountVO = new BlanceOfMIAccountVO();
		try {
			accountVO = secQueryService.getBalanceOfMIAccount4Other(bean.getSfzh(), bean.getXm());
			BeanValidatorUtil.getFieldGetterNames(accountVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("医保账户余额查询异常：", e);
			return result(statusCode, message);
		}
		return result(statusCode, message, accountVO);
	}

	public String getPersonAccount(String str) {
		String account = "";
		if (!str.isEmpty() && str.length() > 0) {
			int length = str.length();
			account = str.substring(0, length - 11) + "*******" + str.substring(length - 2);
		}
		return account;
	}
}
