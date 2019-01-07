package com.tecsun.sisp.adapter.modules.so.controller;

import com.tecsun.sisp.adapter.common.util.BeanValidatorUtil;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.so.entity.request.DrugCatalogQueryBean;
import com.tecsun.sisp.adapter.modules.so.entity.request.HospitalQueryBean;
import com.tecsun.sisp.adapter.modules.so.entity.request.PaymentQueryBean;
import com.tecsun.sisp.adapter.modules.so.entity.response.*;
import com.tecsun.sisp.adapter.modules.so.service.impl.TestSecQueryServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danmeng on 2016/8/9. 社保查询业务接口、社保卡业务接口
 */
@Controller
@RequestMapping(value = "/adapter/rest")
public class SecQueryController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(SecQueryController.class);

	@Autowired
	private TestSecQueryServiceImpl testSecQueryService;
	
	/*
	 * 参保个人信息
	 */
	@RequestMapping(value = "/getBasicInsuredInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getBasicInsuredInfo(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------SecQueryController: getInsuranceInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
       // if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		List<InsuranceVO> insuranceVO = new ArrayList<InsuranceVO>();
		try {
			//insuranceVO.setXm("王印博");	
			//insuranceVO.setSfzh("220103201605022736");
			insuranceVO = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVO.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVO.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			BeanValidatorUtil.getFieldGetterNames(insuranceVO.get(0));
			
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：",e);
			return result(result, message);
		}
		return result(result, message, insuranceVO.get(0));
	}

	/*
	 * 查询参保信息险种
	 */
	@RequestMapping(value = "/getIncureTypeList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getIncureTypeList(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------SecQueryController: getIncureTypeList---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));

		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
      //  if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<IncureTypeVO> page = new Page(bean.getPageno(), bean.getPagesize());
		List<IncureTypeVO> list = new ArrayList<IncureTypeVO>();
		List<InsuranceVO> insuranceVO = new ArrayList<InsuranceVO>();
		try {
			insuranceVO = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVO.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVO.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			bean.setPage(page);
			list = testSecQueryService.getIncureTypeList4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：",e);
		}
		return result(result, message, page);
	}


	@RequestMapping(value = "/getUserCBInfoForApp", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getUserCBInfoForApp(@RequestBody PaymentQueryBean bean) throws Exception{
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");

		Page<UserCBInfoVO> page = new Page(bean.getPageno(), bean.getPagesize());
		List<UserCBInfoVO> list = new ArrayList<UserCBInfoVO>();
		try {
			bean.setPage(page);

			list = testSecQueryService.getUserCBInfo4other(bean);

			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}

			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：",e);
		}
		return result(result, message, page);

	}

	/**
     * 医疗保险
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMIPaymentBasicInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getMIPaymentBasicInfo(@RequestBody PaymentQueryBean bean) throws Exception {
    	
		logger.info("---------------SecQueryController: getMIPaymentBasicInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
    	
		// insertTransLog(bean, Constants.BUSINESSCODE.get("getMIPaymentBasicInfo"));
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
       // if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		FiveInsuranceVO insuranceVO = new FiveInsuranceVO();
		List<InsuranceVO> insuranceVOs = new ArrayList<InsuranceVO>();
		try {
			insuranceVOs = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVOs.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVOs.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			insuranceVO = testSecQueryService.getMIPaymentBasicInfo4Other(bean);
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询医疗保险缴费统计信息异常：",e);
		}
		return result(result, message, insuranceVO);
    }

    /**
     * 医疗保险
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMIPaymentRecord", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getMIPaymentRecord(@RequestBody PaymentQueryBean bean) throws Exception {
    	
		logger.info("---------------SecQueryController: getMIPaymentRecord---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
    	
    	String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
        //if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<MIRecordVO> page = new Page<MIRecordVO>(bean.getPageno(), bean.getPagesize());
		List<MIRecordVO> list = new ArrayList<MIRecordVO>();
		List<InsuranceVO> insuranceVO = new ArrayList<InsuranceVO>();
		try {
			insuranceVO = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVO.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVO.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			bean.setPage(page);
			list = testSecQueryService.getMIPaymentRecord4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询医疗保险缴费记录信息异常：",e);
		}
		return result(result, message, page);
    }
    
    /**
	 * 获取医疗账户(MA)基本信息
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMABasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMABasicInfo(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------SecQueryController: getMABasicInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		
		// insertTransLog(bean, Constants.BUSINESSCODE.get("getMABasicInfo"));
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		/*if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");*/
		
		MAIncureVO insuranceVO = new MAIncureVO();
		List<InsuranceVO> insuranceVOs = new ArrayList<InsuranceVO>();
		try {
			insuranceVOs = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVOs.size()==0){
				statusCode = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(statusCode, message);
			}
			if(insuranceVOs.size()>1){
				statusCode = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(statusCode, message);
			}
			insuranceVO = testSecQueryService.getMABasicInfo4Other(bean);
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询医疗账户(MA)基本信息异常：",e);
		}
		return result(statusCode, message, insuranceVO);
	}

	/**
	 * 获取医疗账户(MA)明细
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMADetail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMADetail(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------SecQueryController: getMADetail---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
			
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
		//if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<MARecordVO> page = new Page<MARecordVO>(bean.getPageno(), bean.getPagesize());
		List<MARecordVO> list = new ArrayList<MARecordVO>();
		List<InsuranceVO> insuranceVO = new ArrayList<InsuranceVO>();
		try {
			insuranceVO = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVO.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVO.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			bean.setPage(page);
			list = testSecQueryService.getMADetail4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询医疗账户(MA)明细异常：", e);
		}
		return result(result, message, page);
	}
	
	/**
	 * 个人医疗费用信息
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMACost", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMACost(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------SecQueryController: getMACost---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
			
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
		//if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<MACostVO> page = new Page<MACostVO>(bean.getPageno(), bean.getPagesize());
		List<MACostVO> list = new ArrayList<MACostVO>();
		List<InsuranceVO> insuranceVO = new ArrayList<InsuranceVO>();
		try {
			insuranceVO = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVO.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVO.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			bean.setPage(page);
			list = testSecQueryService.getMACost4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人医疗费用信息异常：", e);
		}
		return result(result, message, page);
	}


	/**
	 * 获取养老保险(EI)缴费基本信息
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEIPaymentBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getEIPaymentBasicInfo(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
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
//			insuranceVO.setXm("王胜波");						// 姓名
//			insuranceVO.setSfzh("422201198612142119");		// 身份证号码
//			insuranceVO.setCbzt("正在参保");					// 参保状态
//			insuranceVO.setJnze(1000.00);					// 缴纳总额
//			insuranceVO.setDwjnhj(400.00);					// 单位缴纳
//			insuranceVO.setGrjnhj(600.00);					// 个人缴纳
			
			insuranceVO = testSecQueryService.getEIPaymentBasicInfo4Other(bean);
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：",e);
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
	public Result getEIPaymentRecord(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------EIQueryController: getEIPaymentRecord---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<EIPayRecordVO> page = new Page<EIPayRecordVO>(bean.getPageno(), bean.getPagesize());
		List<EIPayRecordVO> list = new ArrayList<EIPayRecordVO>();
		try {
//			EIPayRecordVO recordVO = new EIPayRecordVO();
//			recordVO.setSsq("201609");
//			recordVO.setXz("城镇职工基本养老保险");
//			recordVO.setJfjs("6000.00");
//			recordVO.setDwjn("100.00");
//			recordVO.setGrjn("50.00");
//			recordVO.setJnze("150.00");
//			list.add(recordVO);
//			
//			recordVO = new EIPayRecordVO();
//			recordVO.setSsq("201609");
//			recordVO.setXz("城镇职工基本养老保险");
//			recordVO.setJfjs("6000.00");
//			recordVO.setDwjn("100.00");
//			recordVO.setGrjn("50.00");
//			recordVO.setJnze("150.00");
//			list.add(recordVO);
//			
//			recordVO = new EIPayRecordVO();
//			recordVO.setSsq("201609");
//			recordVO.setXz("城镇职工基本养老保险");
//			recordVO.setJfjs("6000.00");
//			recordVO.setDwjn("100.00");
//			recordVO.setGrjn("50.00");
//			recordVO.setJnze("150.00");
//			list.add(recordVO);

			bean.setPage(page);
			list = testSecQueryService.getEIPaymentRecord4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：",e);
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
	public Result getEIIssueBasicInfo(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
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
//			insuranceVO.setXm("王胜波");						// 姓名
//			insuranceVO.setSfzh("422201198612142119");		// 身份证号码
//			insuranceVO.setYljffzje("5000.00");				// 养老金发放总金额
			insuranceVO = testSecQueryService.getEIIssueBasicInfo4Other(bean);
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询养老基本信息异常：",e);
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
	public Result getEIIssueRecord(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------EIQueryController: getEIIssueRecord---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
				
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<EIIssueRecordVO> page = new Page<EIIssueRecordVO>(bean.getPageno(), bean.getPagesize());
		List<EIIssueRecordVO> list = new ArrayList<EIIssueRecordVO>();
		try {
//			EIIssueRecordVO recordVO = new EIIssueRecordVO();
//			recordVO.setFfsj("2016-08");       //发放时间          
//			recordVO.setFfyh("光大银行");   	//发放银行          
//			recordVO.setFfzh("621489******2540");   	//发放账号          
//			recordVO.setFfzje("500.00");  	//发放总金额        
//			recordVO.setJcylj("300.00");  	//基础养老金        
//			recordVO.setGrzhylj("200.00");	//个人账户养老金  
//			recordVO.setGdxylj("0.00"); 	//过渡性养老金    
//			recordVO.setBfje("0.00");   	//补发金额          
//			recordVO.setTfje("0.00");   	//退发金额       
//			list.add(recordVO);
//			
//			recordVO = new EIIssueRecordVO();
//			recordVO.setFfsj("2016-08");       //发放时间          
//			recordVO.setFfyh("光大银行");   	//发放银行          
//			recordVO.setFfzh("621489******2540");   	//发放账号          
//			recordVO.setFfzje("500.00");  	//发放总金额        
//			recordVO.setJcylj("300.00");  	//基础养老金        
//			recordVO.setGrzhylj("200.00");	//个人账户养老金  
//			recordVO.setGdxylj("0.00"); 	//过渡性养老金    
//			recordVO.setBfje("0.00");   	//补发金额          
//			recordVO.setTfje("0.00");   	//退发金额       
//			list.add(recordVO);
			bean.setPage(page);
			list = testSecQueryService.getEIIssueRecord4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 获取生育保险(BI)缴纳基本信息
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBIPaymentBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getBIPaymentBasicInfo(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------SecQueryController: getBIPaymentBasicInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
       // if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		FiveInsuranceVO insuranceVO = new FiveInsuranceVO();
		List<InsuranceVO> insuranceVOs = new ArrayList<InsuranceVO>();
		try {
			insuranceVOs = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVOs.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVOs.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			insuranceVO = testSecQueryService.getBIPaymentBasicInfo4Other(bean);
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询生育保险缴费统计信息异常：",e);
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
	public Result getBIPaymentRecord(@RequestBody PaymentQueryBean bean)
			throws Exception {		
		
		logger.info("---------------SecQueryController: getBIPaymentRecord---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
        //if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<MIRecordVO> page = new Page<MIRecordVO>(bean.getPageno(), bean.getPagesize());
		List<MIRecordVO> list = new ArrayList<MIRecordVO>();
		List<InsuranceVO> insuranceVO = new ArrayList<InsuranceVO>();
		try {
			insuranceVO = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVO.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVO.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			bean.setPage(page);
			list = testSecQueryService.getBIPaymentRecord4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询生育保险缴费记录信息异常：",e);
		}
		return result(result, message, page);
    }
    
	
	
	/**
	 * 获取工伤保险(EII)缴纳基本信息
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEIIPaymentBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getEIIPaymentBasicInfo(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------SecQueryController: getEIIPaymentBasicInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		
		// insertTransLog(bean, Constants.BUSINESSCODE.get("getEIIPaymentBasicInfo"));
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
       // if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		FiveInsuranceVO insuranceVO = new FiveInsuranceVO();
		List<InsuranceVO> insuranceVOs = new ArrayList<InsuranceVO>();
		try {
			insuranceVOs = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVOs.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVOs.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			insuranceVO = testSecQueryService.getEIIPaymentBasicInfo4Other(bean);
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询工伤保险统计信息异常：",e);
		}
		return result(result, message, insuranceVO);
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
	public Result getEIIPaymentRecord(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------SecQueryController: getEIIPaymentRecord---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
				
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
        //if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<MIRecordVO> page = new Page<MIRecordVO>(bean.getPageno(), bean.getPagesize());
		List<MIRecordVO> list = new ArrayList<MIRecordVO>();
		List<InsuranceVO> insuranceVO = new ArrayList<InsuranceVO>();
		try {
			insuranceVO = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVO.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVO.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			bean.setPage(page);
			list = testSecQueryService.getEIIPaymentRecord4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询工伤保险缴费记录信息异常：",e);
		}
		return result(result, message, page);
    }
	
	/**
	 * 工伤医疗结算信息 
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEIISettlementInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getEIISettlementInfo(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------SecQueryController: getEIISettlementInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
				
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
        //if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<EIISettlementVO> page = new Page<EIISettlementVO>(bean.getPageno(), bean.getPagesize());
		List<EIISettlementVO> list = new ArrayList<EIISettlementVO>();
		List<InsuranceVO> insuranceVO = new ArrayList<InsuranceVO>();
		try {
			insuranceVO = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVO.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVO.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			bean.setPage(page);
			list = testSecQueryService.getEIISettlementInfo4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询工伤医疗结算信息 异常：",e);
		}
		return result(result, message, page);
    }
	
	/**
	 * 获取失业保险(UEI)缴纳基本信息
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUEIPaymentBasicInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getUEIPaymentBasicInfo(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------UEIQueryController: getUEIPaymentBasicInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		
		// insertTransLog(bean, Constants.BUSINESSCODE.get("getUEIPaymentBasicInfo"));
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");
		
		FiveInsuranceVO insuranceVO = new FiveInsuranceVO();
		try {
//			insuranceVO.setXm("王胜波");						// 姓名
//			insuranceVO.setSfzh("422201198612142119");		// 身份证号码
//			insuranceVO.setCbzt("正在参保");					// 参保状态
//			insuranceVO.setJnze(1000.00);					// 缴纳总额
//			insuranceVO.setDwjnhj(400.00);					// 单位缴纳
//			insuranceVO.setGrjnhj(600.00);					// 个人缴纳
			
			insuranceVO = testSecQueryService.getUEIPaymentBasicInfo4Other(bean);
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：",e);
		}
		return result(statusCode, message, insuranceVO);
	}

	/**
	 * 获取失业保险(UEI)缴纳记录
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUEIPaymentRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getUEIPaymentRecord(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------UEIQueryController: getUEIPaymentRecord---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
    			
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<FiveInsuranceRecordVO> page = new Page<FiveInsuranceRecordVO>(bean.getPageno(), bean.getPagesize());
		List<FiveInsuranceRecordVO> list = new ArrayList<FiveInsuranceRecordVO>();
		try {
//			FiveInsuranceRecordVO recordVO = new FiveInsuranceRecordVO();
//			recordVO.setSsq("201609");
//			recordVO.setJfjs("6000");
//			recordVO.setDwjn("30");
//			recordVO.setGrjn("0");
//			recordVO.setJnze("30");
//			list.add(recordVO);
//			
//			recordVO = new FiveInsuranceRecordVO();
//			recordVO.setSsq("201608");
//			recordVO.setJfjs("6000");
//			recordVO.setDwjn("30");
//			recordVO.setGrjn("0");
//			recordVO.setJnze("30");
//			list.add(recordVO);
//			
//			recordVO = new FiveInsuranceRecordVO();
//			recordVO.setSsq("201607");
//			recordVO.setJfjs("6000");
//			recordVO.setDwjn("30");
//			recordVO.setGrjn("0");
//			recordVO.setJnze("30");
//			list.add(recordVO);
			bean.setPage(page);
			list = testSecQueryService.getUEIPaymentRecord4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：",e);
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
	public Result getURREIPaymentBasicInfo(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------URREIQueryController: getURREIPaymentBasicInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
				
		//insertTransLog(bean, Constants.BUSINESSCODE.get("getURREIPaymentBasicInfo"));		
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh()))
			return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm()))
			return this.result(statusCode, "姓名不能为空");
		
		URREIPayBasicVO insuranceVO = new URREIPayBasicVO();
		try {
//			insuranceVO.setXm("王胜波");						// 姓名
//			insuranceVO.setSfzh("422201198612142119");		// 身份证号码
//			insuranceVO.setCbzt("正在参保");					// 参保状态
//			insuranceVO.setJnze(1000.00);					// 缴纳总额
//			insuranceVO.setDwjnhj(400.00);					// 单位缴纳
//			insuranceVO.setGrjnhj(600.00);					// 个人缴纳
			
			insuranceVO = testSecQueryService.getURREIPaymentBasicInfo4Other(bean);
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：",e);
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
	public Result getURREIPaymentRecord(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------URREIQueryController: getURREIPaymentRecord---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<URREIPayRecord> page = new Page<URREIPayRecord>(bean.getPageno(), bean.getPagesize());
		List<URREIPayRecord> list = new ArrayList<URREIPayRecord>();
		try {
//			FiveInsuranceRecordVO recordVO = new FiveInsuranceRecordVO();
//			recordVO.setSsq("201609");
//			recordVO.setJfjs("6000");
//			recordVO.setDwjn("30");
//			recordVO.setGrjn("0");
//			recordVO.setJnze("30");
//			list.add(recordVO);
//			
//			recordVO = new FiveInsuranceRecordVO();
//			recordVO.setSsq("201608");
//			recordVO.setJfjs("6000");
//			recordVO.setDwjn("30");
//			recordVO.setGrjn("0");
//			recordVO.setJnze("30");
//			list.add(recordVO);
//			
//			recordVO = new FiveInsuranceRecordVO();
//			recordVO.setSsq("201607");
//			recordVO.setJfjs("6000");
//			recordVO.setDwjn("30");
//			recordVO.setGrjn("0");
//			recordVO.setJnze("30");
//			list.add(recordVO);

			bean.setPage(page);
			list = testSecQueryService.getURREIPaymentRecord4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：",e);
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
	public Result getURREIIssueBasicInfo(@RequestBody PaymentQueryBean bean)
			throws Exception {
		
		logger.info("---------------URREIQueryController: getURREIIssueBasicInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
			
		//insertTransLog(bean, Constants.BUSINESSCODE.get("getURREIIssueBasicInfo"));
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		if (StringUtils.isEmpty(bean.getSfzh())) return this.result(statusCode, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm())) return this.result(statusCode, "姓名不能为空");
		
		URREIIssueBasicVO insuranceVO = new URREIIssueBasicVO();
		try {
//			insuranceVO.setXm("王胜波");						// 姓名
//			insuranceVO.setSfzh("422201198612142119");		// 身份证号码
//			insuranceVO.setCbzt("正在参保");					// 参保状态
//			insuranceVO.setJnze(1000.00);					// 缴纳总额
//			insuranceVO.setDwjnhj(400.00);					// 单位缴纳
//			insuranceVO.setGrjnhj(600.00);					// 个人缴纳
			
			insuranceVO = testSecQueryService.getURREIIssueBasicInfo4Other(bean);
			
			BeanValidatorUtil.getFieldGetterNames(insuranceVO);
			
		} catch (Exception e) {
			statusCode = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：",e);
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
	public Result getURREIIssueRecord(@RequestBody PaymentQueryBean bean)
			throws Exception {
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<URREIIssueRecordVO> page = new Page<URREIIssueRecordVO>(bean.getPageno(), bean.getPagesize());
		List<URREIIssueRecordVO> list = new ArrayList<URREIIssueRecordVO>();
		try {
//			FiveInsuranceRecordVO recordVO = new FiveInsuranceRecordVO();
//			recordVO.setSsq("201609");
//			recordVO.setJfjs("6000");
//			recordVO.setDwjn("30");
//			recordVO.setGrjn("0");
//			recordVO.setJnze("30");
//			list.add(recordVO);
//			
//			recordVO = new FiveInsuranceRecordVO();
//			recordVO.setSsq("201608");
//			recordVO.setJfjs("6000");
//			recordVO.setDwjn("30");
//			recordVO.setGrjn("0");
//			recordVO.setJnze("30");
//			list.add(recordVO);
//			
//			recordVO = new FiveInsuranceRecordVO();
//			recordVO.setSsq("201607");
//			recordVO.setJfjs("6000");
//			recordVO.setDwjn("30");
//			recordVO.setGrjn("0");
//			recordVO.setJnze("30");
//			list.add(recordVO);
			
			bean.setPage(page);
			list = testSecQueryService.getURREIIssueRecord4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询参保险种信息异常：",e);
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
    public Result getBalanceOfMIAccount(@RequestBody SecQueryBean bean)  throws Exception {


        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "查询成功";
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(statusCode, "身份证号不能为空");
        if (StringUtils.isEmpty(bean.getXm())) return this.result(statusCode, "姓名不能为空");

        BlanceOfMIAccountVO accountVO = new BlanceOfMIAccountVO();
        try {
            accountVO = testSecQueryService.getBalanceOfMIAccount4Other(bean.getSfzh(), bean.getXm());
            BeanValidatorUtil.getFieldGetterNames(accountVO);
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "查询失败";
            logger.error("医保账户余额查询异常：", e);
            return  result(statusCode, message);
        }
        return result(statusCode, message, accountVO);
    }
    
    /**
     * 生育待遇发放信息
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getBITreatmentInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getBITreatmentInfo(@RequestBody PaymentQueryBean bean) throws Exception {
    	
		logger.info("---------------SecQueryController: getBITreatmentInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
    	
    	String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
        //if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<BITreatmentVO> page = new Page<BITreatmentVO>(bean.getPageno(), bean.getPagesize());
		List<BITreatmentVO> list = new ArrayList<BITreatmentVO>();
		List<InsuranceVO> insuranceVO = new ArrayList<InsuranceVO>();
		try {
			insuranceVO = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVO.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVO.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			bean.setPage(page);
			list = testSecQueryService.getBITreatmentInfo4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询生育待遇发放信息异常：",e);
		}
		return result(result, message, page);
    }
    
    /**
     * 生育医疗结算信息
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getBISettlementInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getBISettlementInfo(@RequestBody PaymentQueryBean bean) throws Exception {
    	
		logger.info("---------------SecQueryController: getBISettlementInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
    	
    	String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
        //if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<BISettlementVO> page = new Page<BISettlementVO>(bean.getPageno(), bean.getPagesize());
		List<BISettlementVO> list = new ArrayList<BISettlementVO>();
		List<InsuranceVO> insuranceVO = new ArrayList<InsuranceVO>();
		try {
			insuranceVO = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVO.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVO.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			bean.setPage(page);
			list = testSecQueryService.getBISettlementInfo4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询生育医疗结算信息异常：",e);
		}
		return result(result, message, page);
    }
    
    /**
     * 城镇居民缴费信息
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getTRPaymentInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getTRPaymentInfo(@RequestBody PaymentQueryBean bean) throws Exception {
    	
		logger.info("---------------SecQueryController: getTRPaymentInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
    	
    	String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(bean.getSfzh())) return this.result(result, "身份证号不能为空");
        //if (StringUtils.isEmpty(bean.getXm())) return this.result(result, "姓名不能为空");
		
		Page<TRPaymentVO> page = new Page<TRPaymentVO>(bean.getPageno(), bean.getPagesize());
		List<TRPaymentVO> list = new ArrayList<TRPaymentVO>();
		List<InsuranceVO> insuranceVO = new ArrayList<InsuranceVO>();
		try {
			insuranceVO = testSecQueryService.getBasicInsuredInfo4Other(bean);
			if(insuranceVO.size()==0){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			if(insuranceVO.size()>1){
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "对不起，您存在多个参保账号，请联系柜台工作人员进行查询！";
				return this.result(result, message);
			}
			bean.setPage(page);
			list = testSecQueryService.getTRPaymentInfo4Other(bean);
			
			for(int i=0; i<list.size(); i++){
				BeanValidatorUtil.getFieldGetterNames(list.get(i));
			}
			
			page.setData(list);
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询城镇居民缴费信息异常：",e);
		}
		return result(result, message, page);
    }
    
    
    /**
     * 定点医疗机构信息
     * @return
     */
    @RequestMapping(value = "/getDesignatedHospitalInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getDesignatedHospitalInfo(@RequestBody HospitalQueryBean bean) {
    	logger.info("---------------SecQueryController: getDesignatedHospitalInfo---------------");
    	logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
    	
    	String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		Page<DesignatedHospitalVO> page = new Page<DesignatedHospitalVO>(bean.getPageno(), bean.getPagesize());
		List<DesignatedHospitalVO> data = new ArrayList<DesignatedHospitalVO>();
		try{
			bean.setPage(page);
			data = testSecQueryService.getDesignatedHospitals4Other(bean);
			// 医疗机构 信息
			if (data!=null && data.size()!=0) {
				for(int i=0; i<data.size(); i++) {
					BeanValidatorUtil.getFieldGetterNames(data.get(i));
				}
				page.setData(data);
			}
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询定点医疗机构信息异常：", e);
		}
		return result(result, message, page);
    }
    
    
    /**
     * 吉林市三级甲等医疗机构
     */
    @RequestMapping(value = "/getThirdClassDesignatedHospitalInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getThirdClassDesignatedHospitalInfo(@RequestBody PaymentQueryBean bean) {
    	logger.info("---------------SecQueryController: getThirdClassDesignatedHospitalInfo---------------");
    	
    	String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		Page<DesignatedHospitalVO> page = new Page<DesignatedHospitalVO>(bean.getPageno(), bean.getPagesize());
		List<DesignatedHospitalVO> data = new ArrayList<DesignatedHospitalVO>();
		try{
			bean.setPage(page);
			data = testSecQueryService.getThirdClassDesignatedHospitals4Other(bean);
			// 三级甲等医疗机构 信息
			if (data!=null && data.size()!=0) {
				for(int i=0; i<data.size(); i++) {
					BeanValidatorUtil.getFieldGetterNames(data.get(i));
				}
				page.setData(data);
			}
		}catch(Exception e){
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询三级甲等医疗机构信息异常：", e);
		}
		return result(result, message, page);
    }
    
    
    /**
     * 药品目录信息
     */
    @RequestMapping(value = "/getDrugCatalogInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getDrugCatalogInfo(@RequestBody DrugCatalogQueryBean bean) {
    	logger.info("---------------SecQueryController: getDrugCatalogInfo---------------");
    	logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(bean));
    	
    	String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		Page<DrugCatalogInformationVO> page = new Page<DrugCatalogInformationVO>(bean.getPageno(), bean.getPagesize());
		List<DrugCatalogInformationVO> data = new ArrayList<DrugCatalogInformationVO>();
		try{
			bean.setPage(page);
			data = testSecQueryService.getDrugCatalogInformations4Other(bean);
			// 三级甲等医疗机构 信息
			if (data!=null && data.size()!=0) {
				for(int i=0; i<data.size(); i++) {
					BeanValidatorUtil.getFieldGetterNames(data.get(i));
				}
				page.setData(data);
			}
		}catch(Exception e){
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询药品目录信息异常：", e);
		}
		return result(result, message, page);
    }
}
