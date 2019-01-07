package com.tecsun.sisp.adapter.modules.appoint.controller;

import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.appoint.entity.request.*;
import com.tecsun.sisp.adapter.modules.appoint.service.impl.BusinessServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by xumaohao on 2017/10/25.
 */
@RestController
@RequestMapping("/adapter/appoint")
public class BusinessController extends BaseController {
    Logger logger = Logger.getLogger(BusinessController.class);

    @Autowired
    BusinessServiceImpl businessService;


    /**
     * 获取可办理业务
     * @param bean
     * @return
     */
    @RequestMapping("/getService")
    public Result getService(@RequestBody ServiceBean bean){
        //入参校验
        if(StringUtils.isEmpty(bean.getParentCode())) return error("业务编码parentCode不能为空");
        try {
            List list = businessService.getService4cssp(bean);
            return ok("查询成功",list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取可办理业务" + e);
        }
        return error("查询失败");
    }

    /**
     * 获取区域
     * @param bean
     * @return
     */
    @RequestMapping("/getDistrict")
    public Result getDistrict(@RequestBody DistrictBean bean){
        //入参校验
        if(bean == null) return error("入参不能为空");
        try {
            List list = businessService.getDistrict4cssp(bean);
            return ok("获取区域成功",list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取区域" + e);
        }
        return error("获取区域失败");
    }

    /**
     * 获取办事网点
     * @param bean
     * @return
     */
    @RequestMapping("/getBranch")
    public Result getBranch(@RequestBody BranchBean bean){
        //入参校验
        if(StringUtils.isEmpty(bean.getServiceCode())) return error("业务编码serviceCode不能为空");
        try {
            List list = businessService.getBranch4cssp(bean);
            return ok("查询成功",list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取办事网点" + e);
        }
        return error("查询失败");
    }

    /**
     * 获取预约时间
     * @param bean
     * @return
     */
    @RequestMapping("/getTime")
    public Result getTime(@RequestBody TimeBean bean){
        //入参校验
        if(StringUtils.isEmpty(bean.getServiceCode())) return error("业务编码serviceCode不能为空");
        try {
            List list = businessService.getTime4cssp(bean);
            return ok("查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取办事网点" + e);
        }
        return error("查询失败");
    }

    /**
     * 预约
     * @param bean
     * @return
     */
    @RequestMapping("/insertRecord")
    public Result insertRecord(@RequestBody RecordBean bean) {
        //入参校验
        String check = checkInsertRecord(bean);
        if (StringUtils.isNotBlank(check))
            return error(check);
        try {
            Map<String, Object> map = businessService.insertRecord4cssp(bean);
            return new Result((String) map.get("statusCode"),(String) map.get("message"), map.get("appointNum"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("预约业务" + e);
        }
        return error("当前系统预约人数过多，请重新选择");
    }

    private String checkInsertRecord(RecordBean bean) {
        if(bean == null)
            return "入参不能为空";
        if(StringUtils.isEmpty(bean.getXm()))
            return "姓名xm不能为空";
        if(StringUtils.isEmpty(bean.getSfzh()))
            return "身份证号sfzh不能为空";
        if(StringUtils.isEmpty(bean.getServiceCode()))
            return "业务编码serviceCode不能为空";
        if(StringUtils.isEmpty(bean.getAppointTime()))
            return "预约时间appointTime不能为空";
        if(StringUtils.isEmpty(bean.getTimeInterval()))
            return "时间段timeInterval不能为空";
        if(StringUtils.isEmpty(bean.getBranchCode()))
            return "网点编码branchCode不能为空";

        return "";
    }
}
