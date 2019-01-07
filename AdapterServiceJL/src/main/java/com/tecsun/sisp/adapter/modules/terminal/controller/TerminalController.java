package com.tecsun.sisp.adapter.modules.terminal.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.common.util.qrcode.FileUtil;
import com.tecsun.sisp.adapter.common.util.qrcode.QRCodeFactory;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.terminal.entity.request.DrugCatalogQueryBean;
import com.tecsun.sisp.adapter.modules.terminal.entity.request.InsuredCertificationQueryBean;
import com.tecsun.sisp.adapter.modules.terminal.entity.request.MedicalInstitutionQueryBean;
import com.tecsun.sisp.adapter.modules.terminal.entity.request.MedicalInsuranceQueryBean;
import com.tecsun.sisp.adapter.modules.terminal.entity.request.MedicalTreatmentQueryBean;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.DrugCatalogInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.InsuredCertificationInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.OccupationalInjuryHospitalVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.LocalMedicalInstitutionVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.MajorIllnessApprovalVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.MaternityMedicalSettlementVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.MedicalTreatmentProjectVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.OffsiteMedicalInstitutionVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalAccountInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalBlockadeInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalInsuranceInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalMedicalFeeInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalMedicalSettlementInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PersonalPaymentInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.RemoteApprovalInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.ResidentPaymentInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.TransferApprovalInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.UninsuredCertificationInfoVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.PrintCertificationFrequencyVO;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.CompanyBlockadeInfoVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.CompanyInfoVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.CompanyInsuranceInfoVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.CompanyPaymentInfoVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.UnitPersonsVo;
import com.tecsun.sisp.adapter.modules.terminal.entity.response.OccupationalInjuryMedicalSettlementVO;
import com.tecsun.sisp.adapter.modules.terminal.service.impl.TerminalQueryServiceImpl;

@Controller
@RequestMapping(value = "/adapter/terminal")
public class TerminalController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(TerminalController.class);
	
	@Autowired
	TerminalQueryServiceImpl terminalQueryServiceImpl ;
	
	
	/**
	 * 单位信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/getCompanyInfo", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result getCompanyInfo(@RequestBody MedicalInsuranceQueryBean bean) throws Exception {
    	   String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
           String message = "操作成功";
    	if (StringUtils.isBlank(bean.getCompanyNo())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入单位编号!");
        }
        try {
           CompanyInfoVo ufvo = terminalQueryServiceImpl.getCompanyInfo4Other(bean);
           logger.info("打印出参=======》》》" + JsonHelper.javaBeanToJson(ufvo));
            if (ufvo != null && !"".equals(ufvo)) {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询成功";
                return result(statusCode, message, ufvo);
            } else {
            	message = "暂无相关信息!";
            }
        } catch (Exception e) {
            logger.error("获取单位基本信息出错：" + e.getMessage());
        }
        
        return result(statusCode, message);
    }
    
    
    /**
     * 单位参保信息
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCompanyInsuranceInfo", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result getCompanyInsuranceInfo(@RequestBody MedicalInsuranceQueryBean bean) throws Exception {
    	  String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
          String message = "操作成功";
    	if (StringUtils.isBlank(bean.getCompanyNo())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入单位编号!");
        }
        try {
           List<CompanyInsuranceInfoVo> list = terminalQueryServiceImpl.getCompanyInsuranceInfo4Other(bean);
           logger.info("打印出参=======》》》" + JsonHelper.javaBeanToJson(list));
            if (list != null && list.size() > 0) {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询成功";
                return result(statusCode, message, list);
            } else {
            	message = "暂无相关信息!";
            }
        } catch (Exception e) {
            logger.error("获取单位参保信息出错：" + e.getMessage());
        }
        
        return result(statusCode, message);
    }
    
    
    /**
     * 单位人员信息
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUnitPersons", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
	public Result getUnitPersons(@RequestBody MedicalInsuranceQueryBean bean) throws Exception {
	  String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
      String message = "操作成功";
    	if (StringUtils.isBlank(bean.getCompanyNo())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入单位编号!");
    	}
        try {
        	logger.info("正在调用单位人员信息接口...");
            Page<UnitPersonsVo> page = terminalQueryServiceImpl.queryUnitPersons4other(bean);
           logger.info("打印出参=======》》》" + JsonHelper.javaBeanToJson(page));
            if (page.getData() != null && page.getData().size() > 0) {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询成功";
                return result(statusCode, message, page);
            } else {
            	message = "暂未获取到相关信息!";
            }
        } catch (Exception e) {
            logger.error("获取单位人员信息出错：" + e.getMessage());
        }
        
        return result(statusCode, message);
	 }
    
    
    /**
     * 单位缴费信息
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCompanyPaymentInfo", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
	public Result getCompanyPaymentInfo(@RequestBody MedicalInsuranceQueryBean bean) throws Exception {
	  String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
      String message = "操作成功";
    	if (StringUtils.isBlank(bean.getCompanyNo())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入单位编号!");
    	}
        try {
        	logger.info("正在调用单位缴费信息接口...");
            Page<CompanyPaymentInfoVo> page = terminalQueryServiceImpl.getCompanyPaymentInfo4Other(bean);
           logger.info("打印出参=======》》》" + JsonHelper.javaBeanToJson(page));
            if (page.getData() != null && page.getData().size() > 0) {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询成功";
                return result(statusCode, message, page);
            } else {
            	message = "暂未获取到相关信息!";
            }
        } catch (Exception e) {
            logger.error("获取单位缴费信息出错：" + e.getMessage());
        }
        
        return result(statusCode, message);
	}
    
    
    /**
     * 单位封锁信息
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCompanyBlockadeInfo", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
	public Result getCompanyBlockadeInfo(@RequestBody MedicalInsuranceQueryBean bean) throws Exception {
    	String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
    	String message = "操作成功";
    	if (StringUtils.isBlank(bean.getCompanyNo())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入单位编号!");
    	}
        try {
        	logger.info("正在调用单位封锁信息接口...");
            Page<CompanyBlockadeInfoVo> page = terminalQueryServiceImpl.getCompanyBlockadeInfo4Other(bean);
           logger.info("打印出参=======》》》" + JsonHelper.javaBeanToJson(page));
            if (page.getData() != null && page.getData().size() > 0) {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询成功";
                return result(statusCode, message, page);
            } else {
            	message = "暂未获取到相关信息!";
            }
        } catch (Exception e) {
            logger.error("获取单位封锁信息出错：" + e.getMessage());
        }
        
        return result(statusCode, message);
	 }
    
    
    /**
     * 工伤定点医院
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getOccupationalInjuryHospitalInfo", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
	public Result getOccupationalInjuryHospitalInfo(@RequestBody MedicalInsuranceQueryBean bean) throws Exception {
    	String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
    	String message = "操作成功";
    	if (StringUtils.isBlank(bean.getPersonalNo())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入个人编号!");
    	}
        try {
        	logger.info("正在调用工伤定点医院信息接口...");
            Page<OccupationalInjuryHospitalVo> page = terminalQueryServiceImpl.getOccupationalInjuryHospitalInfo4Other(bean);
           logger.info("打印出参=======》》》" + JsonHelper.javaBeanToJson(page));
            if (page.getData() != null && page.getData().size() > 0) {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询成功";
                return result(statusCode, message, page);
            } else {
            	message = "暂未获取到相关信息!";
            }
        } catch (Exception e) {
            logger.error("获取工伤定点医院信息出错：" + e.getMessage());
        }
        
        return result(statusCode, message);
	 }
    
    
	

	/**
	 * 个人基本信息
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getPersonalInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getPersonalInfo(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getBasicPersonalInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
        if (StringUtils.isEmpty(queryBean.getIdCard())) {
        	return this.result(result, "身份证号不能为空");
        }
        	
        if (StringUtils.isEmpty(queryBean.getName())) {
        	return this.result(result, "姓名不能为空");
        }
        	
        List<PersonalInfoVO> info = new ArrayList<>();
		try {
			info = terminalQueryServiceImpl.getPersonalInfo4Other(queryBean);
			// 判断当前查询者是否参保
			if (info == null || info.size() == 0) {
				result = Constants.RESULT_MESSAGE_ERROR;
				message = "没有此用户参保信息";
				return this.result(result, message);
			}
			
		} catch (Exception e) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "查询失败";
			logger.error("查询个人基本信息异常：",e);
			return result(result, message);
		}
		return result(result, message, info);
	}
	
	
	/**
	 * 个人缴费信息
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getPersonalPaymentInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getPersonalPaymentInfo(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getPersonalPaymentInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		List<PersonalPaymentInfoVO> datas = new ArrayList<>();
		Page<PersonalPaymentInfoVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getPersonalPaymentInfo4Other(queryBean);
			if (datas != null && datas.size() != 0) {
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询个人缴费信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 居民缴费信息
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getResidentPaymentInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getResidentPaymentInfo(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getResidentPaymentInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		List<ResidentPaymentInfoVO> datas = new ArrayList<>();
		Page<ResidentPaymentInfoVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getResidentPaymentInfo4Other(queryBean);
			if (datas != null && datas.size() != 0) {
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询居民缴费信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 个人账户信息
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getPersonalAccountInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getPersonalAccountInfo(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getPersonalAccountInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		PersonalAccountInfoVO info = new PersonalAccountInfoVO();
		
		try {
			info = terminalQueryServiceImpl.getPersonalAccountInfo4Other(queryBean);
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询个人账户信息异常：",e);
		}
		return result(result, message, info);
	}
	
	
	/**
	 * 个人封锁信息
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getPersonalBlockadeInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getPersonalBlockadeInfo(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getPersonalBlockadeInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		// 创建集合、分页对象
		List<PersonalBlockadeInfoVO> datas = new ArrayList<>();
		Page<PersonalBlockadeInfoVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getPersonalBlockadeInfo4Other(queryBean);
			// 对返回数据判空处理
			if (datas != null && datas.size() != 0) {
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询个人封锁信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 个人参保信息
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getPersonalInsuranceInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getPersonalInsuranceInfo(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getPersonalInsuranceInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		// 创建集合、分页对象
		List<PersonalInsuranceInfoVO> datas = new ArrayList<>();
		Page<PersonalInsuranceInfoVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getPersonalInsuranceInfo4Other(queryBean);
			// 对返回数据判空处理
			if (datas != null && datas.size() != 0) {
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询个人参保信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 个人医疗费用列表信息
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getPersonalMedicalFeeOverview", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getPersonalMedicalFeeOverview(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getPersonalMedicalFeeOverview---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<PersonalMedicalSettlementInfoVO> datas = new ArrayList<>();
		Page<PersonalMedicalSettlementInfoVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getPersonalMedicalFeeOverview4Other(queryBean);
			if (datas != null && datas.size() != 0) {
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询个人医疗费用信息列表异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 个人医疗费用明细
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getPersonalMedicalFeeDetail", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Result getPersonalMedicalFeeDetail(HttpServletRequest request) {
		// 获取当前列表中记录的单据号
		String documentNo = request.getParameter("documentNo");
		logger.info("---------------TerminalController: getPersonalMedicalFeeDetail---------------");  
		logger.info("---------------documentNo"+ documentNo +"---------------");
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<PersonalMedicalFeeInfoVO> data = new ArrayList<>();
		
		try {
			data = terminalQueryServiceImpl.getPersonalMedicalFeeDetail4Other(documentNo);
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询个人医疗费用详情信息异常：",e);
		}
		return result(result, message, data);
	}
	
	
	/**
	 * 药品目录信息
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getDrugCatalogInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getDrugCatalogInfo(@RequestBody DrugCatalogQueryBean queryBean) {
		logger.info("---------------TerminalController: getDrugCatalogInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<DrugCatalogInfoVO> datas = new ArrayList<>();
		Page<DrugCatalogInfoVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getDrugCatalogInfo4Other(queryBean);
			if (datas != null) {
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询药品目录信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 诊疗项目信息
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getMedicalTreatmentProjectInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMedicalTreatmentProjectInfo(@RequestBody MedicalTreatmentQueryBean queryBean) {
		logger.info("---------------TerminalController: getMedicalTreatmentProjectInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<MedicalTreatmentProjectVO> datas = new ArrayList<>();
		Page<MedicalTreatmentProjectVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getMedicalTreatmentProjectInfo4Other(queryBean);
			if (datas != null && datas.size() != 0) {
				page.setData(datas);
			}
 		} catch (Exception e) {
 			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询药品目录信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 查询所有省份
	 * @return
	 */
	@RequestMapping(value = "/getAllProvinceInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getAllProvinceInfo() {
		logger.info("---------------TerminalController: getAllProvinceInfo---------------");
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<OffsiteMedicalInstitutionVO> provinces = new ArrayList<>();
		
		try {
			provinces = terminalQueryServiceImpl.getAllProvinceInfo4Other();
			if (provinces != null && provinces.size() != 0) {
				result = Constants.RESULT_MESSAGE_SUCCESS;
				message = "查询成功";
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询省份信息异常：",e);
		}
		return result(result, message, provinces);
	}
	
	
	/**
	 * 查询省份下面的市
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getCityInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getCityInfo(@RequestBody MedicalInstitutionQueryBean queryBean) {
		logger.info("---------------TerminalController: getCityInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<OffsiteMedicalInstitutionVO> citys = new ArrayList<>();
		
		try {
			citys = terminalQueryServiceImpl.getCityInfo4Other(queryBean);
			if (citys != null && citys.size() != 0) {
				result = Constants.RESULT_MESSAGE_SUCCESS;
				message = "查询成功";
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询城市信息异常：",e);
		}
		return result(result, message, citys);
	}
	
	
	/**
	 * 定点医疗机构
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getFixedMedicalInstitutionInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getFixedMedicalInstitutionInfo(@RequestBody MedicalInstitutionQueryBean queryBean) {
		logger.info("---------------TerminalController: getFixedMedicalInstitutionInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<LocalMedicalInstitutionVO> localDatas = new ArrayList<>();
		List<OffsiteMedicalInstitutionVO> offsiteDatas = new ArrayList<>();
		
		Page<LocalMedicalInstitutionVO> localPage = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		Page<OffsiteMedicalInstitutionVO> offsitePage = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			if (queryBean.getAreaFlag() != null) {
				// 根据区域标志判断是查询省内定点医疗机构还是查询省外定点医疗机构
				if ( "local".equals(queryBean.getAreaFlag()) ) {
					queryBean.setPage(localPage);
					localDatas = terminalQueryServiceImpl.getLocalMedicalInstitutionInfo4Other(queryBean);
					if (localDatas != null && localDatas.size() != 0) {
						localPage.setData(localDatas);
					}
					// 返回省内定点医疗机构信息
					return result(result, message, localPage);
				} else if ( "offSite".equals(queryBean.getAreaFlag()) ){
					queryBean.setPage(offsitePage);
					offsiteDatas = terminalQueryServiceImpl.getOffsiteMedicalInstitutionInfo4Other(queryBean);
					if (offsiteDatas != null && offsiteDatas.size() != 0) {
						offsitePage.setData(offsiteDatas);
					}
					// 返回省外定点医疗机构信息
					return result(result, message, offsitePage);
				}
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询定点医疗机构信息异常：",e);
		}
		return result(result, message);
	}
	
	
	/**
	 * 工伤医疗结算
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getOccupationalInjuryMedicalSettlementInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getOccupationalInjuryMedicalSettlementInfo(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getOccupationalInjuryMedicalSettlementInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<OccupationalInjuryMedicalSettlementVO> datas = new ArrayList<>();
		Page<OccupationalInjuryMedicalSettlementVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getOccupationalInjuryMedicalSettlementInfo4Other(queryBean);
			if (datas != null && datas.size() != 0) {
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询工伤医疗结算信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 生育医疗结算
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getMaternityMedicalSettlementInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMaternityMedicalSettlementInfo(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getMaternityMedicalSettlementInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<MaternityMedicalSettlementVO> datas = new ArrayList<>();
		Page<MaternityMedicalSettlementVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getMaternityMedicalSettlementInfo4Other(queryBean);
			if (datas != null && datas.size() != 0) {
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询生育医疗结算信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 大病慢病审批
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getMajorIllnessApprovalInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMajorIllnessApprovalInfo(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getMajorIllnessApprovalInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<MajorIllnessApprovalVO> datas = new ArrayList<>();
		Page<MajorIllnessApprovalVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getMajorIllnessApprovalInfo4Other(queryBean);
			if (datas != null && datas.size() != 0) {
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询大病慢病审批信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 异地审批
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getRemoteApprovalInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getRemoteApprovalInfo(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getRemoteApprovalInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<RemoteApprovalInfoVO> datas = new ArrayList<>();
		Page<RemoteApprovalInfoVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getRemoteApprovalInfo4Other(queryBean);
			if (datas != null && datas.size() != 0) {
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询异地审批信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 转外审批
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getTransferApprovalInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getTransferApprovalInfo(@RequestBody MedicalInsuranceQueryBean queryBean) {
		logger.info("---------------TerminalController: getTransferApprovalInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		List<TransferApprovalInfoVO> datas = new ArrayList<>();
		Page<TransferApprovalInfoVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		try {
			queryBean.setPage(page);
			datas = terminalQueryServiceImpl.getTransferApprovalInfo4Other(queryBean);
			if (datas != null && datas.size() != 0) {
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询转外审批信息异常：",e);
		}
		return result(result, message, page);
	}
	
	
	/**
	 * 参保人参保记录列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getInsuredCertificationOverview", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getInsuredCertificationOverview(@RequestBody InsuredCertificationQueryBean queryBean) {
		logger.info("---------------TerminalController: getInsuredCertificationOverview---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		InsuredCertificationInfoVO  info = new InsuredCertificationInfoVO();
		List<InsuredCertificationInfoVO> datas = new ArrayList<>();
		Page<InsuredCertificationInfoVO> page = new Page<>(queryBean.getPageno(), queryBean.getPagesize());
		
		// 参保证明打印时间
		SimpleDateFormat printDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String printTime = printDateFormat.format(new Date(System.currentTimeMillis()));
		
		try {
			datas = terminalQueryServiceImpl.getInsuredCertificationOverview4Other(queryBean);
			if (datas != null && !datas.isEmpty()) {
				for (int index = 0; index < datas.size(); index++) {
					info = datas.get(index);
					info.setPrintTime(printTime);
				}
				page.setData(datas);
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询参保记录列表异常：", e);
		}
		
		return result(result, message, page);
	}
	
	
	/**
	 * 扫二维码获取参保证明
	 * @param queryBean
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getInsuredCertificationInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getInsuredCertficationInfo(@RequestBody InsuredCertificationQueryBean queryBean) {
		logger.info("---------------TerminalController: getInsuredCertificationInfo---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "查询成功";
		
		// 装载参保信息，用以生成html
		Map<String, String> datas = new HashMap<>();
		
		
		// 初始化参保证明
		InsuredCertificationInfoVO insured = new InsuredCertificationInfoVO();
		UninsuredCertificationInfoVO uninsured = new UninsuredCertificationInfoVO();
		
		// 二维码
		String qrCode = "";
		
		try {
			if ("未参保".equals(queryBean.getInsuredStatus())) {
				uninsured = terminalQueryServiceImpl.getUninsuredCertificationDetail4Other(queryBean);
				if (uninsured != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String[] currentTime = dateFormat.format(new Date()).split("-");
					datas.put("isInsure", "false");
					datas.put("name", uninsured.getName());
					datas.put("idNumber", uninsured.getIdNumber());
					datas.put("currentYear", currentTime[0]);
					datas.put("currentMonth", currentTime[1]);
					datas.put("currentDay", currentTime[2]);
					datas.put("printTime", queryBean.getPrintTime());
				}
			} else if ("没有此用户参保信息".equals(queryBean.getMessage())) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String[] currentTime = dateFormat.format(new Date()).split("-");
				datas.put("isInsure", "false");
				datas.put("name", queryBean.getName());
				datas.put("idNumber", queryBean.getIdCard());
				datas.put("currentYear", currentTime[0]);
				datas.put("currentMonth", currentTime[1]);
				datas.put("currentDay", currentTime[2]);
				datas.put("printTime", queryBean.getPrintTime());
			} else {
				insured = terminalQueryServiceImpl.getInsuredCertificationDetail4Other(queryBean);
				if (insured != null) {
					datas.put("isInsure", "true");
					datas.put("name", insured.getName());
					datas.put("gender", insured.getGender());
					datas.put("idNumber", insured.getIdNumber());
					datas.put("birthDate", insured.getBirthDate());
					datas.put("companyName", insured.getCompanyName());
					datas.put("insuredStatus", insured.getInsuredStatus());
					datas.put("personnelStatus", insured.getPersonnelStatus());
					datas.put("medicalInsuranceNo", insured.getMedicalInsuranceNo());
					datas.put("medicalInsuranceType", insured.getMedicalInsuranceType());
					datas.put("insuredTimeOfBMI",
							insured.getInsuredTimeOfBMI() == null ? "" : insured.getInsuredTimeOfBMI());
					datas.put("paymentStartTimeOfBMI",
							insured.getPaymentStartTimeOfBMI() == null ? "" : insured.getPaymentStartTimeOfBMI());
					datas.put("paymentEndTimeOfBMI",
							insured.getPaymentEndTimeOfBMI() == null ? "" : insured.getPaymentEndTimeOfBMI());
					datas.put("paymentMonthOfBMI",
							insured.getPaymentMonthOfBMI() == null ? "" : insured.getPaymentMonthOfBMI());
					datas.put("treatmentFlagOfBMI",
							insured.getTreatmentFlagOfBMI() == null ? "" : insured.getTreatmentFlagOfBMI());
					datas.put("insuredTimeOfMI",
							insured.getInsuredTimeOfMI() == null ? "" : insured.getInsuredTimeOfMI());
					datas.put("paymentStartTimeOfMI",
							insured.getPaymentStartTimeOfMI() == null ? "" : insured.getPaymentStartTimeOfMI());
					datas.put("paymentEndTimeOfMI",
							insured.getPaymentEndTimeOfMI() == null ? "" : insured.getPaymentEndTimeOfMI());
					datas.put("paymentMonthOfMI",
							insured.getPaymentMonthOfMI() == null ? "" : insured.getPaymentMonthOfMI());
					datas.put("treatmentFlagOfMI",
							insured.getTreatmentFlagOfMI() == null ? "" : insured.getTreatmentFlagOfMI());
					datas.put("insuredTimeOfEII",
							insured.getInsuredTimeOfEII() == null ? "" : insured.getInsuredTimeOfEII());
					datas.put("paymentStartTimeOfEII",
							insured.getPaymentStartTimeOfEII() == null ? "" : insured.getPaymentStartTimeOfEII());
					datas.put("paymentEndTimeOfEII",
							insured.getPaymentEndTimeOfEII() == null ? "" : insured.getPaymentEndTimeOfEII());
					datas.put("paymentMonthOfEII",
							insured.getPaymentMonthOfEII() == null ? "" : insured.getPaymentMonthOfEII());
					datas.put("treatmentFlagOfEII",
							insured.getTreatmentFlagOfEII() == null ? "" : insured.getTreatmentFlagOfEII());
					datas.put("insuredTimeOfLMI",
							insured.getInsuredTimeOfLMI() == null ? "" : insured.getInsuredTimeOfLMI());
					datas.put("paymentStartTimeOfLMI",
							insured.getPaymentStartTimeOfLMI() == null ? "" : insured.getPaymentStartTimeOfLMI());
					datas.put("paymentEndTimeOfLMI",
							insured.getPaymentEndTimeOfLMI() == null ? "" : insured.getPaymentEndTimeOfLMI());
					datas.put("paymentMonthOfLMI",
							insured.getPaymentMonthOfLMI() == null ? "" : insured.getPaymentMonthOfLMI());
					datas.put("treatmentFlagOfLMI",
							insured.getTreatmentFlagOfLMI() == null ? "" : insured.getTreatmentFlagOfLMI());
					datas.put("insuredTimeOfRMI",
							insured.getInsuredTimeOfRMI() == null ? "" : insured.getInsuredTimeOfRMI());
					datas.put("paymentStartTimeOfRMI",
							insured.getPaymentStartTimeOfRMI() == null ? "" : insured.getPaymentStartTimeOfRMI());
					datas.put("paymentEndTimeOfRMI",
							insured.getPaymentEndTimeOfRMI() == null ? "" : insured.getPaymentEndTimeOfRMI());
					datas.put("paymentMonthOfRMI",
							insured.getPaymentMonthOfRMI() == null ? "" : insured.getPaymentMonthOfRMI());
					datas.put("treatmentFlagOfRMI",
							insured.getTreatmentFlagOfRMI() == null ? "" : insured.getTreatmentFlagOfRMI());
					datas.put("printTime", queryBean.getPrintTime());
				}
			}
			// 设置生成二维码长宽，可变参。
			int[] size = {430, 430};
//			qrCode = createQrCode("", datas, "jpg", "F:\\CertificationQrCode\\" + datas.get("idNumber") + ".jpg", "", size);
			qrCode = createQrCode("", datas, "jpg", Config.getInstance().get("picture_path_certification_qrcode") + datas.get("idNumber") + ".jpg", "", size);
			if ("".equals(qrCode) && qrCode != null) {
				message = "查询成功";
				result =  Constants.RESULT_MESSAGE_SUCCESS;
			}
		} catch (Exception e) {
			message = "查询失败";
			result = Constants.RESULT_MESSAGE_ERROR;
			logger.error("查询参保证明信息异常：", e);
		}
		
		return result(result, message, qrCode);
	}
	
	
	/*@RequestMapping(value = "/getPrintFrequency", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getPrintFrequency(@RequestBody InsuredCertificationQueryBean queryBean) {
		logger.info("---------------TerminalController: getPrintFrequency---------------");  
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));
		
		List<CertificationPrintFrequencyVO> frequency = new ArrayList<>();
		
		String result = Constants.RESULT_MESSAGE_ERROR;
		String message = "插入查询记录成功";
		
		
		frequency = terminalQueryServiceImpl.getPrintFrequency4Cssp(queryBean);
		
		if (frequency.size() <= 3) {
			i = terminalQueryServiceImpl.savePrintFrequency4Cssp(queryBean);
			result = Constants.RESULT_MESSAGE_SUCCESS;
			message = "查询成功";
			return result(result, message, i);
		}
		if (frequency.size() >= 3) {
			result = Constants.RESULT_MESSAGE_SUCCESS;
			message = "本月打印超过三次";
			int total = frequency.size();
			return result(result, message, total);
		}
		int i = 0;
		i = terminalQueryServiceImpl.savePrintFrequency4Cssp(queryBean);
		return result(result, message, i);
	}*/
	
	/**
	 * 限制当月每个参保人只能打印三次参保证明
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "/getPrintCertificationFrequency", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getPrintCertificationFrequency(@RequestBody InsuredCertificationQueryBean queryBean) {
		logger.info("---------------TerminalController: getPrintCertificationFrequency---------------");
		logger.info("---------------bean:\t" + JsonHelper.javaBeanToJson(queryBean));

		String result = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "插入参保证明打印次数记录成功";

		int i = terminalQueryServiceImpl.insertOrUpdateFrequency4Cssp(queryBean);
		if (i >= 3) {
			result = Constants.RESULT_MESSAGE_ERROR;
			message = "本月您已打印三次参保证明";
			return result(result, message, i);
		}

		return result(result, message, i);
	}
	   
	
	
	/**
    *
    * @param url              页面访问地址
    * @param format           生成二维码的格式
    * @param map              参保信息
    * @param outFileUri       二维码的生成地址
    * @param logUri           二维码中间logo的地址
    * @param size             用于设定图片大小（可变参数，宽，高）
    * @return
    */
   public String createQrCode(String url ,Map<String,String> map,String format,String outFileUri,String logoUri, int ...size){
       //Map<String,Object> map = new HashMap<String, Object>();
       String contents = null;
       try {
           contents = createQrHtml(url,map);
       } catch (IOException e) {
           e.printStackTrace();
       }
       String qrBase64 = "";
       try {
           qrBase64 = new QRCodeFactory().CreatQrImage(contents, format, outFileUri, logoUri,size);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return qrBase64;
   }

   
   /**
    * 将查询出的数据放入电子表格中
    * @param url  生成html页面服务器地址
    * @param map  查询到的数据
    * @return
    * @throws IOException
    */
   public String createQrHtml(String url,Map<String, String> map) throws IOException {
           //String idCard = "0794";//表单内容由map传递  idCard应从map中取出，此处模拟数据
           String htmlStr2 = "";
       if( "true".equals(map.get("isInsure")) ) {//表示打印参保页面
        htmlStr2="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
               "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
               "\t<head>\n" +
               "\t\t<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
               "\t</head>\n" +
               "\t<body>\n" +
               "\t<div>\n" +
               "\t<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\" align=\"center\"><span style=\"font-weight:bold;font-size:24px;\">医疗保险参保证明</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\"><span style=\"font-weight:bold;font-size:20px;\">个人基本信息</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr style=\"text-align:center;\">\n" +
               "\t\t\t<td style=\"width:160px;\"><span>姓名</span></td>\n" +
               "\t\t\t<td><span>" + map.get("name") + "</span></td>\n" +
               "\t\t\t<td style=\"width:150px;\"><span>身份证号</span></td>\n" +
               "\t\t\t<td style=\"width:150px;\"><span>" + map.get("idNumber") + "</span></td>\n" +
               "\t\t\t<td  style=\"width:150px;\"><span>出生日期</span></td>\n" +
               "\t\t\t<td><span>" + map.get("birthDate") + "</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr style=\"text-align:center;\">\n" +
               "\t\t\t<td><span>性别</span></td>\n" +
               "\t\t\t<td><span>" + map.get("gender") + "</span></td>\n" +
               "\t\t\t<td><span>医保编号</span></td>\n" +
               "\t\t\t<td><span>" + map.get("medicalInsuranceNo") + "</span></td>\n" +
               "\t\t\t<td><span>医疗类别</span></td>\n" +
               "\t\t\t<td><span>" + map.get("medicalInsuranceType") + "</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr style=\"text-align:center;\">\n" +
               "\t\t\t<td><span>参保状态</span></td>\n" +
               "\t\t\t<td><span>" + map.get("insuredStatus") + "</span></td>\n" +
               "\t\t\t<td><span>人员状态</span></td>\n" +
               "\t\t\t<td><span>" + map.get("personnelStatus") + "</span></td>\n" +
               "\t\t\t<td><span></span></td>\n" +
               "\t\t\t<td><span></span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td><span>当前所在单位（社区）</span></td>\n" +
               "\t\t\t<td colspan=\"5\"><span>" + map.get("companyName") + "</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\"><span style=\"font-weight:bold;font-size:20px;\">参保缴费情况</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr style=\"text-align:center;\">\n" +
               "\t\t\t<td><span>险种</span></td>\n" +
               "\t\t\t<td><span>参保时间</span></td>\n" +
               "\t\t\t<td><span>缴费记录开始时间</span></td>\n" +
               "\t\t\t<td><span>缴费记录结束时间</span></td>\n" +
               "\t\t\t<td><span>实际缴费（年）月数</span></td>\n" +
               "\t\t\t<td><span>是否享受待遇</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr style=\"text-align:center;\">\n" +
               "\t\t\t<td><span>基本医疗保险</span></td>\n" +
               "\t\t\t<td><span>" + map.get("insuredTimeOfBMI") + "</span></td>\n" +
               "\t\t\t<td><span>" + map.get("paymentStartTimeOfBMI") + "</span></td>\n" +
               "\t\t\t<td><span>" + map.get("paymentEndTimeOfBMI") + "</span></td>\n" +
               "\t\t\t<td style=\"text-align:right\"><span></span><span>" + map.get("paymentMonthOfBMI") + "（月）</span></td>\n" +
               "\t\t\t<td><span>" + map.get("treatmentFlagOfBMI") + "</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr style=\"text-align:center;\">\n" +
               "\t\t\t<td><span>生育保险</span></td>\n" +
               "\t\t\t<td><span>" + map.get("insuredTimeOfMI") + "</span></td>\n" +
               "\t\t\t<td><span>" + map.get("paymentStartTimeOfMI") + "</span></td>\n" +
               "\t\t\t<td><span>" + map.get("paymentEndTimeOfMI") + "</span></td>\n" +
               "\t\t\t<td style=\"text-align:right\"><span style=\"text-align:center;\"></span><span>" + map.get("paymentMonthOfMI") + "（月）</span></td>\n" +
               "\t\t\t<td><span>" + map.get("treatmentFlagOfMI") + "</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr style=\"text-align:center;\">\n" +
               "\t\t\t<td><span>工伤保险</span></td>\n" +
               "\t\t\t<td><span>" + map.get("insuredTimeOfEII") + "</span></td>\n" +
               "\t\t\t<td><span>" + map.get("paymentStartTimeOfEII") + "</span></td>\n" +
               "\t\t\t<td><span>" + map.get("paymentEndTimeOfEII") + "</span></td>\n" +
               "\t\t\t<td style=\"text-align:right\"><span style=\"text-align:center;\"></span><span>" + map.get("paymentMonthOfEII") + "（月）</span></td>\n" +
               "\t\t\t<td><span>" + map.get("treatmentFlagOfEII") + "</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr style=\"text-align:center;\">\n" +
               "\t\t\t<td><span>大额医疗保险</span><span></span></td>\n" +
               "\t\t\t<td><span>" + map.get("insuredTimeOfLMI") + "</span></td>\n" +
               "\t\t\t<td><span>" + map.get("paymentStartTimeOfLMI") + "</span></td>\n" +
               "\t\t\t<td><span>" + map.get("paymentEndTimeOfLMI") + "</span></td>\n" +
               "\t\t\t<td style=\"text-align:right\"><span style=\"text-align:center;\"></span><span>" + map.get("paymentMonthOfLMI") + "（月）</span></td>\n" +
               "\t\t\t<td><span>" + map.get("treatmentFlagOfLMI") + "</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr style=\"text-align:center;\">\n" +
               "\t\t\t<td><span>居民医疗保险</span></td>\n" +
               "\t\t\t<td><span>" + map.get("insuredTimeOfRMI") + "</span></td>\n" +
               "\t\t\t<td><span>" + map.get("paymentStartTimeOfRMI") + "</span></td>\n" +
               "\t\t\t<td><span>" + map.get("paymentEndTimeOfRMI") + "</span></td>\n" +
               "\t\t\t<td style=\"text-align:right\"><span style=\"text-align:center;\"></span><span>" + map.get("paymentMonthOfRMI") + "（月）</span></td>\n" +
               "\t\t\t<td><span>" + map.get("treatmentFlagOfRMI") + "</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr></tr>\n" +
               "\t\t\n" +
               "\t</table>\n" +
               "\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td style=\"width:160px;\">&nbsp;</td>\n" +
               "\t\t\t<td>&nbsp;</td>\n" +
               "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
               "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
               "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
               "\t\t\t<td>&nbsp;</td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"4\" ></td>\n" +
               "\t\t\t<td colspan=\"2\" ><span style=\"text-align:center;font-weight:bold;font-size:20px;display:block;\">特此证明</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"4\" ></td>\n" +
               "\t\t\t<td colspan=\"2\" ><span style=\"text-align:center;font-weight:bold;font-size:20px;display:block;\">吉林市社会医疗保险管理局</span></td>\n" +
               "\t\t</tr>\n" +
               "\t</table>\n" +
               "\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td style=\"width:160px;\">&nbsp;</td>\n" +
               "\t\t\t<td>&nbsp;</td>\n" +
               "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
               "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
               "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
               "\t\t\t<td>&nbsp;</td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td style=\"width:160px;\">&nbsp;</td>\n" +
               "\t\t\t<td>&nbsp;</td>\n" +
               "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
               "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
               "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
               "\t\t\t<td>&nbsp;</td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\" ><span style=\"font-weight:bold;font-size:20px;display:block;padding-left:20px\">备注：</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">1、实际缴费月数证明在吉林市社会医疗保险管理局实际累计缴费时间。</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">2、以上信息均截止打印时间为止。</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">3、此表不加盖公章，有效期为31天，可以通过移动终端扫描二维码验证真伪。</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">4、如存在表单数据与实际不符，请联系吉林市社会医疗保险管理局。</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">5、咨询电话0432-62489814  0432-62489816。</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">6、吉林市社会医疗保险管理局负责最终解释。</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">7、每个医保账户每月只能打印三份参保证明。</span></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\" ></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"6\" ></td>\n" +
               "\t\t</tr>\n" +
               "\t\t<tr>\n" +
               "\t\t\t<td colspan=\"2\" ><span style=\"padding-left:20px;display:block;\">自助服务终端打印</span></td>\n" +
               "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
               "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
               "\t\t\t<td colspan=\"2\" style=\"vertical-align:top;\"><span style=\"float:right;\"><span style=\"display:inline-block;\">打印时间：</span><span style=\"display:inline-block;\">" + map.get("printTime") + "</span></span></td>\n" +
               "\t\t</tr>\n" +
               "\t</table>\n" +
               "\t</div>\n" +
               "\t</body>\n" +
               "</html>";
       }else if ( "false".equals(map.get("isInsure")) ) {
               htmlStr2 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                       "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                       "\t<head>\n" +
                       "\t\t<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
                       "\t</head>\n" +
                       "\t<body>\n" +
                       "\t<div>\n" +
                       "\t<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\" align=\"center\"><span style=\"font-weight:bold;font-size:24px;\">医疗保险未参保证明</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\"><span style=\"font-weight:bold;font-size:20px;\">个人基本信息</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr style=\"text-align:center;\">\n" +
                       "\t\t\t<td style=\"width:160px;\"><span>姓名</span></td>\n" +
                       "\t\t\t<td colspan=\"5\"><span>" + map.get("name") + "</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr style=\"text-align:center;\">\n" +
                       "\t\t\t<td><span>身份证号</span></td>\n" +
                       "\t\t\t<td colspan=\"5\"><span>" + map.get("idNumber") + "</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\"><span style=\"font-weight:bold;font-size:20px;\">参保缴费情况</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t\n" +
                       "\t\t<tr style=\"text-align:center;\">\n" +
                       "\t\t\t\n" +
                       "\t\t\t<td colspan=\"6\"><span>截止至&nbsp;" + map.get("currentYear") + "&nbsp;年&nbsp;" + map.get("currentMonth") + "&nbsp;月&nbsp;" + map.get("currentDay") + "&nbsp;日，该人员未参加吉林市基本医疗、工伤、生育保险，不享受吉林市医疗保险待遇。</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr></tr>\n" +
                       "\t</table>\n" +
                       "\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td style=\"width:160px;\">&nbsp;</td>\n" +
                       "\t\t\t<td>&nbsp;</td>\n" +
                       "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
                       "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
                       "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
                       "\t\t\t<td>&nbsp;</td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"4\" ></td>\n" +
                       "\t\t\t<td colspan=\"2\" ><span style=\"text-align:center;font-weight:bold;font-size:20px;display:block;\">特此证明</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"4\" ></td>\n" +
                       "\t\t\t<td colspan=\"2\" ><span style=\"text-align:center;font-weight:bold;font-size:20px;display:block;\">吉林市社会医疗保险管理局</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t</table>\n" +
                       "\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td style=\"width:160px;\">&nbsp;</td>\n" +
                       "\t\t\t<td>&nbsp;</td>\n" +
                       "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
                       "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
                       "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
                       "\t\t\t<td>&nbsp;</td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td style=\"width:160px;\">&nbsp;</td>\n" +
                       "\t\t\t<td>&nbsp;</td>\n" +
                       "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
                       "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
                       "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
                       "\t\t\t<td>&nbsp;</td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\" ><span style=\"font-weight:bold;font-size:20px;display:block;padding-left:20px\">备注：</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">1、实际缴费月数证明在吉林市社会医疗保险管理局实际累计缴费时间。</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">2、以上信息均截止打印时间为止。</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">3、此表不加盖公章，有效期为31天，可以通过移动终端扫描二维码验证真伪。</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">4、如存在表单数据与实际不符，请联系吉林市社会医疗保险管理局。</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">5、咨询电话0432-62489814  0432-62489816。</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">6、吉林市社会医疗保险管理局负责最终解释。</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\" ><span style=\"display:block;padding-left:20px\">7、每个医保账户每月只能打印三份参保证明。</span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\" ></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"6\" ></td>\n" +
                       "\t\t</tr>\n" +
                       "\t\t<tr>\n" +
                       "\t\t\t<td colspan=\"2\" ><span style=\"padding-left:20px;display:block;\">自助服务终端打印</span></td>\n" +
                       "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
                       "\t\t\t<td style=\"width:150px;\">&nbsp;</td>\n" +
                       "\t\t\t<td colspan=\"2\" style=\"vertical-align:top;\"><span style=\"float:right;\"><span style=\"display:inline-block;\">打印时间：</span><span style=\"display:inline-block;\">" + map.get("printTime") + "</span></span></td>\n" +
                       "\t\t</tr>\n" +
                       "\t</table>\n" +
                       "\t</div>\n" +
                       "\t</body>\n" +
                       "</html>\n";
       }   
       	   // 读取配置文件中生成电子表格html页面路径
//       	   File file = new File("F:\\CertificationHtml\\" + map.get("idNumber") + ".html");
           File file = new File(Config.getInstance().get("picture_path_certification_html") + map.get("idNumber") + ".html");
           if(file.exists()){
               file.delete();
           }
           file.createNewFile();
           boolean res = FileUtil.writeFileContent(file.getPath(),htmlStr2);
           if(res){
                   System.out.println("生成html成功");
           }else{
                   System.out.println("生成html失败");
           }
           //扫二维码转发到对应的html页面（电子参保证明页面）
           String contents = Config.getInstance().get("certification_url") + map.get("idNumber") + ".html";
//           String contents = "http://192.168.4.43:80/sisp/certification/" + map.get("idNumber") + ".html";
       return contents;
   }

}
