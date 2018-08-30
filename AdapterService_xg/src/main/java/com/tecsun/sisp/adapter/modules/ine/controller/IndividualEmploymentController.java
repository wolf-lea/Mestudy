package com.tecsun.sisp.adapter.modules.ine.controller;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tecsun.sisp.adapter.common.util.CommUtil;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.response.AreaInfoVo;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.ine.entity.request.IneQueryBean;
import com.tecsun.sisp.adapter.modules.ine.entity.request.InfoBean;
import com.tecsun.sisp.adapter.modules.ine.entity.response.ApplicationListVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.ApplicationVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.PersonalApplicationsVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.PersonalRecruitsVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.PositionVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.RecordVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.RecruitInfoVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.RecruitListVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.RecruitRecordVo;
import com.tecsun.sisp.adapter.modules.ine.service.impl.IneServiceImpl;
import com.tecsun.sisp.adapter.modules.ine.util.IneUtil;

/**个人用工
 * @author sipeng
 * @email zsp_banyue@163.com
 * @date 2017年10月25日
 */

@RestController
@RequestMapping(value="/adapter/ine")
@ResponseBody
public class IndividualEmploymentController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(IndividualEmploymentController.class);
	
	@Autowired
	private IneServiceImpl ineService;
	
	@Autowired
    private CommServiceImpl commService;
	
	/**
	 * 获取记录列表  招工-求职
	 * @param <T>
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/getIneInfoList",method=RequestMethod.POST, produces = "application/json")
	public Result getIneInfoList(@RequestBody IneQueryBean ineQueryBean){
		logger.info("getIneInfoList"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "查询成功";
        if(StringUtils.isEmpty(ineQueryBean.getQueryType())) return result(Constants.RESULT_MESSAGE_PARAM, "queryType:参数错误");
        try {
			if(Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())){
				//当查询标志为0时  表示查询的是求职信息
				Page<ApplicationListVo> page = ineService.getApplicationList4Cssp(ineQueryBean);
				return result(statusCode, message,page);
			}else{
				//当查询标志为1  表示查询的招工信息
				Page<RecruitListVo> page = ineService.getRecruitList4Cssp(ineQueryBean);
				return result(statusCode, message,page);
			}
		} catch (Exception e) {
			logger.error("服务器异常，请稍后再试!", e);
			return result(Constants.RESULT_MESSAGE_ERROR, "操作失败");
		}
	}
	
	/**
	 * 获取单个记录详情 招工-求职
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/getIneInfo",method=RequestMethod.POST, produces = "application/json")
	public Result getIneInfo(@RequestBody IneQueryBean ineQueryBean){
		logger.info("getIneInfoList"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "查询成功";
        if(StringUtils.isEmpty(ineQueryBean.getQueryType())) return result(Constants.RESULT_MESSAGE_PARAM, "queryType:参数错误");
        try {
			if(Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())){
				//当查询标志为0时  表示查询的是求职信息
				ApplicationVo applicationVo = ineService.getApplicationInfoByIneCode4Cssp(ineQueryBean);
				statusCode = Constants.RESULT_MESSAGE_SUCCESS;
				return result(statusCode, message,applicationVo);
			}else{
				//当查询标志为1  表示查询的招工信息
				RecruitInfoVo recruitInfoVo = ineService.getRecruitInfoByIneCode4Cssp(ineQueryBean);
				statusCode = Constants.RESULT_MESSAGE_SUCCESS;
				return result(statusCode, message,recruitInfoVo);
			}
		} catch (Exception e) {
			logger.error("服务器异常，请稍后再试!", e);
			return result(Constants.RESULT_MESSAGE_ERROR, "操作失败");
		}
		
        
	}
	
	/**
	 * 获取岗位列表
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/getPositionList",method=RequestMethod.POST, produces = "application/json")
	public Result getPositionList(@RequestBody IneQueryBean ineQueryBean){
		logger.info("getIneInfoList"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "查询成功";
        try {
        	ineQueryBean.setQueryType(Constants.T_INFO_TYPE_INE);
			Page<PositionVo> poList = ineService.getPositionList4Cssp(ineQueryBean);
				return result(statusCode, message,poList);
		} catch (Exception e) {
			logger.error("服务器异常，请稍后再试!", e);
			return result(Constants.RESULT_MESSAGE_ERROR, "操作失败");
		}
	}
	
	/**
	 * 暂时无效
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/getSalaryList",method=RequestMethod.POST, produces = "application/json")
	public Result getSalaryList(@RequestBody IneQueryBean ineQueryBean){
		logger.info("getIneInfoList"+ CommUtil.getNowDateLongStr());
		
		return null;
	}
	
	/**
	 * 发布信息接口
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/createWork",method=RequestMethod.POST, produces = "application/json")
	public Result createWork(@RequestBody InfoBean infoBean){
		logger.info("getIneInfoList"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "发布失败";
        try {
			if(StringUtils.isEmpty(infoBean.getQueryType())) return result(Constants.RESULT_MESSAGE_PARAM, "queryType参数错误");
			if(Constants.Ine_QueryType_R.equals(infoBean.getQueryType())){
				if(StringUtils.isEmpty(infoBean.getWorkStartDate())) return result(Constants.RESULT_MESSAGE_PARAM, "工作开始日期不能为空");
				if(StringUtils.isEmpty(infoBean.getWorkEndDate())) return result(Constants.RESULT_MESSAGE_PARAM, "工作截止日期不能为空");
				if(StringUtils.isEmpty(infoBean.getWorkStartTime())) return result(Constants.RESULT_MESSAGE_PARAM, "工作开始时间不能为空");
				if(StringUtils.isEmpty(infoBean.getWorkEndTime())) return result(Constants.RESULT_MESSAGE_PARAM, "工作结束时间不能为空");
				if(StringUtils.isEmpty(infoBean.getAmounts())) return result(Constants.RESULT_MESSAGE_PARAM, "招工人数不能为空");
			}
			if(StringUtils.isEmpty(infoBean.getPositionCode())) return result(Constants.RESULT_MESSAGE_PARAM, "岗位不能为空");
			if(StringUtils.isEmpty(infoBean.getAccountMethod())) return result(Constants.RESULT_MESSAGE_PARAM, "结算不能为空");
			if(StringUtils.isEmpty(infoBean.getSal())) return result(Constants.RESULT_MESSAGE_PARAM, "薪水不能为空");
			if(StringUtils.isEmpty(infoBean.getAreaCode())) return result(Constants.RESULT_MESSAGE_PARAM, "工作地点不能为空");
			if(StringUtils.isEmpty(infoBean.getTel())) return result(Constants.RESULT_MESSAGE_PARAM, "电话号码不能为空");
			if(StringUtils.isEmpty(infoBean.getSfzh())) return result(Constants.RESULT_MESSAGE_PARAM, "身份证号不能为空");
			if(StringUtils.isEmpty(infoBean.getXm())) return result(Constants.RESULT_MESSAGE_PARAM, "姓名不能为空");
			
			long infoId = ineService.createWork4Cssp(infoBean);
			
			if(infoId>0){
				statusCode = Constants.RESULT_MESSAGE_SUCCESS;
				message = "发布成功";
			}
		} catch (Exception e) {
			logger.error("发布失败,信息异常bean:\t", JsonHelper.javaBeanToJson(infoBean));
			message = "发布失败";
		}
        return result(statusCode, message);
	}
	
	/**
	 * 报名、邀请接口 
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/prepareWork",method=RequestMethod.POST, produces = "application/json")
	public Result prepareWork(@RequestBody IneQueryBean ineQueryBean){
		logger.info("prepareWork"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "报名成功";
        if(StringUtils.isEmpty(ineQueryBean.getQueryType())) return result(Constants.RESULT_MESSAGE_PARAM, "queryType参数错误");
        //判断参数是否完整
	    if (StringUtils.isEmpty(ineQueryBean.getSfzh())) return result(Constants.RESULT_MESSAGE_PARAM, "身份证号不能为空");

		if (StringUtils.isEmpty(ineQueryBean.getXm())) return result(Constants.RESULT_MESSAGE_PARAM, "姓名不能为空");

		if (StringUtils.isEmpty(ineQueryBean.getTel())) return result(Constants.RESULT_MESSAGE_PARAM, "电话号码不能为空");
		
		if (StringUtils.isEmpty(String.valueOf(ineQueryBean.getInfoId()))) return result(Constants.RESULT_MESSAGE_PARAM, "请先选择目标");
        
        try {
        	//检查是否已报名或者已邀请
        	boolean fg = ineService.checkIsExistInHis4Cssp(ineQueryBean);
        	if(fg){
        		return result(statusCode, Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())?"您已经邀请过他了":"您已经报过名了");
        	}
        	//首先判断信息是够有效
			boolean flag = ineService.checkIsValid4Cssp(ineQueryBean);
			if(flag){
				long busId = ineService.Register4Cssp(IneUtil.coverIneQueryBeanToIneBusBean(ineQueryBean));
				if(busId>0) return result(Constants.RESULT_MESSAGE_SUCCESS, Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())?"邀请成功":message);
					return result(statusCode, Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())?"邀请失败":"报名失败");
			}else{
				return result(statusCode, "该记录已无效，请刷新后重试");
			}
		} catch (Exception e) {
			logger.error("服务器异常，请稍后再试!", e);
			return result(Constants.RESULT_MESSAGE_ERROR,"操作失败");
		}
		
	}
	
	/**
	 * 个人用工-删除发布的记录信息状态 将记录置为无效
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/deleteWork ",method=RequestMethod.POST, produces = "application/json")
	public Result deleteWork(@RequestBody IneQueryBean ineQueryBean){
		logger.info("deleteWork"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "删除成功";
        try {
			if(StringUtils.isEmpty(ineQueryBean.getQueryType())) return result(Constants.RESULT_MESSAGE_PARAM, "queryType参数错误");
			long infoId = ineService.deleteWork4Cssp(ineQueryBean);
			if(infoId>0){
				return result(Constants.RESULT_MESSAGE_SUCCESS, message);
			}else{
				return result(Constants.RESULT_MESSAGE_SUCCESS, "该记录不存在");
			}
		} catch (Exception e) {
			logger.error("服务器异常，请稍后再试!", e);
			return result(statusCode,"删除失败");
		}
	}
	/**
	 * 编辑发布的信息
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/editIneInfo",method=RequestMethod.POST, produces = "application/json")
	public Result editIneInfo(@RequestBody InfoBean infoBean){
		logger.info("editIneInfo"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作成功";
        try {
			if(StringUtils.isEmpty(infoBean.getQueryType())) return result(Constants.RESULT_MESSAGE_PARAM, "queryType参数错误");
			if(infoBean.getIneCode()<=0) return result(Constants.RESULT_MESSAGE_PARAM, "主键信息不能为空");
			
			if(Constants.Ine_QueryType_R.equals(infoBean.getQueryType())){
				if(StringUtils.isEmpty(infoBean.getWorkStartDate())) return result(Constants.RESULT_MESSAGE_PARAM, "工作开始日期不能为空");
				if(StringUtils.isEmpty(infoBean.getWorkEndDate())) return result(Constants.RESULT_MESSAGE_PARAM, "工作截止日期不能为空");
				if(StringUtils.isEmpty(infoBean.getWorkStartTime())) return result(Constants.RESULT_MESSAGE_PARAM, "工作开始时间不能为空");
				if(StringUtils.isEmpty(infoBean.getWorkEndTime())) return result(Constants.RESULT_MESSAGE_PARAM, "工作结束时间不能为空");
				if(StringUtils.isEmpty(infoBean.getAmounts())) return result(Constants.RESULT_MESSAGE_PARAM, "招工人数不能为空");
			}
			if(StringUtils.isEmpty(infoBean.getPositionCode())) return result(Constants.RESULT_MESSAGE_PARAM, "岗位不能为空");
			if(StringUtils.isEmpty(infoBean.getAccountMethod())) return result(Constants.RESULT_MESSAGE_PARAM, "结算不能为空");
			if(StringUtils.isEmpty(infoBean.getSal())) return result(Constants.RESULT_MESSAGE_PARAM, "薪水不能为空");
			if(StringUtils.isEmpty(infoBean.getAreaCode())) return result(Constants.RESULT_MESSAGE_PARAM, "工作地点不能为空");
			if(StringUtils.isEmpty(infoBean.getTel())) return result(Constants.RESULT_MESSAGE_PARAM, "电话号码不能为空");
			
			long infoId = ineService.editIneInfo4Cssp(infoBean);
			if(infoId>0){
				statusCode = Constants.RESULT_MESSAGE_SUCCESS;
				return result(statusCode, message);
			}else{
				return result(Constants.RESULT_MESSAGE_ERROR, "编辑失败");
			}
		} catch (Exception e) {
			logger.error("服务器异常，请稍后再试!", e);
			return result(statusCode,"操作失败");
		}
        
	}
	
	/**
	 * 获取地域信息
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/getArea",method=RequestMethod.POST, produces = "application/json")
	public Result getArea(@RequestBody IneQueryBean ineQueryBean , @Context HttpServletRequest request){
		logger.info("getArea"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作成功";
        try {
			if(StringUtils.isEmpty(ineQueryBean.getAreaCode())) return result(Constants.RESULT_MESSAGE_PARAM, "请传递一个有效的地区编码");
			if(StringUtils.isEmpty(ineQueryBean.getAreaLevel())) return result(Constants.RESULT_MESSAGE_PARAM, "地区级别不能为空");
			ineQueryBean.setDeviceid(request.getParameter("deviceid"));
			Page<AreaInfoVo> areaList = commService.getAreaList4Sisp(ineQueryBean);
			return result(Constants.RESULT_MESSAGE_SUCCESS, message,areaList);
		} catch (Exception e) {
			logger.error("服务器异常，请稍后再试!", e);
			return result(statusCode,"操作失败");
		}
		
	}
	
	
	/**
	 * 更新个人发布的招工/求职记录
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/updateIneInfo",method=RequestMethod.POST, produces = "application/json")
	public Result updateIneInfo(@RequestBody IneQueryBean ineQueryBean){
		logger.info("updateIneInfo"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作成功";
        try {
			if(StringUtils.isEmpty(ineQueryBean.getQueryType())) return result(Constants.RESULT_MESSAGE_PARAM, "queryType参数错误");
			long infoId = ineService.updateIneInfo4Cssp(ineQueryBean);
			if(infoId>0){
				statusCode = Constants.RESULT_MESSAGE_SUCCESS;
				return result(statusCode, message);
			}else{
				return result(statusCode, "暂无数据");
			}
		} catch (Exception e) {
			logger.error("服务器异常，请稍后再试!", e);
			return result(statusCode,"操作失败");
		}
	}
	
	/**
	 * 获取个人发布的招工/求职记录
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/getPersonalList",method=RequestMethod.POST, produces = "application/json")
	public Result getPersonalList(@RequestBody IneQueryBean ineQueryBean){
		logger.info("getPersonalList"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "查询成功";
        try {
			if(StringUtils.isEmpty(ineQueryBean.getQueryType())) return result(Constants.RESULT_MESSAGE_PARAM, "queryType参数错误");
			if(StringUtils.isEmpty(ineQueryBean.getSfzh())) return result(Constants.RESULT_MESSAGE_PARAM, "身份证号不能为空");
			if(Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())){
				//当查询标志为0时  表示查询的是求职信息
				Page<PersonalApplicationsVo> infoList1 = ineService.getApplicationListBySfzh4Cssp(ineQueryBean);
				statusCode = Constants.RESULT_MESSAGE_SUCCESS;
				return result(statusCode, message,infoList1);
			}else{
				//当查询标志为1  表示查询的招工信息
				Page<PersonalRecruitsVo> infoList2 = ineService.getRecruitListBySfzh4Cssp(ineQueryBean);
				statusCode = Constants.RESULT_MESSAGE_SUCCESS;
				return result(statusCode, message,infoList2);
				
			}
		} catch (Exception e) {
			logger.error("服务器异常，请稍后再试!", e);
			return result(statusCode,"操作失败");
		}
	}
	
	/**
	 * 查询个人报名/邀请记录
	 * @param ineQueryBean
	 * @return
	 */
	@RequestMapping(value="/getPersonalRecordList",method=RequestMethod.POST, produces = "application/json")
	public Result getPersonalRecordList(@RequestBody IneQueryBean ineQueryBean){
		logger.info("getPersonalRecordList"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "查询成功";
        try {
			if(StringUtils.isEmpty(ineQueryBean.getQueryType())) return result(Constants.RESULT_MESSAGE_PARAM, "queryType参数错误");
			if(StringUtils.isEmpty(ineQueryBean.getSfzh())) return result(Constants.RESULT_MESSAGE_PARAM, "身份证号不能为空");
			if(Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())){
				Page<RecordVo> list =ineService.getPersonalRecordsBySfzh4Cssp(ineQueryBean);
				return result(Constants.RESULT_MESSAGE_SUCCESS, message,list);
			}else{
				Page<RecruitRecordVo> list2 =ineService.getPersonalRecordsBySfzh4Cssp(ineQueryBean);
				return result(Constants.RESULT_MESSAGE_SUCCESS, message,list2);
			}
		} catch (Exception e) {
			logger.error("服务器异常，请稍后再试!", e);
			return result(statusCode,"操作失败");
		}
	}
	
	
	@RequestMapping(value="/delPersonalRecord ",method=RequestMethod.POST, produces = "application/json")
	public Result delPersonalRecord(@RequestBody IneQueryBean ineQueryBean){
		logger.info("delPersonalRecord"+ CommUtil.getNowDateLongStr());
		String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作成功";
        try {
			long infoId = ineService.delPersonalRecord4Cssp(ineQueryBean.getRecordId());
			if(infoId>0){
				return result(Constants.RESULT_MESSAGE_SUCCESS, message);
			}else{
				return result(Constants.RESULT_MESSAGE_SUCCESS, "该记录不存在");
			}
		} catch (Exception e) {
			logger.error("服务器异常，请稍后再试!", e);
			return result(statusCode,"操作失败");
		}
	}

}
