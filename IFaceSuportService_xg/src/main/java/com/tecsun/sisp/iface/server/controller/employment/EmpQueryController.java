package com.tecsun.sisp.iface.server.controller.employment;

import com.tecsun.sisp.iface.common.vo.Result;
import com.tecsun.sisp.iface.common.vo.employment.param.queryParamBean;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.model.service.employment.impl.EmpQueryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by pear on 2015/8/5.
 */

@Controller
@RequestMapping(value = "/iface/emp")
public class EmpQueryController extends BaseController{
    private static Logger logger = LoggerFactory.getLogger(EmpQueryController.class);

    @Autowired
    public EmpQueryServiceImpl empQueryServiceImpl;

//    就业失业登记证查询
    @RequestMapping(value = "/getRegistrationInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getRegistrationInfo(@RequestBody queryParamBean bean) throws Exception {

        Result result = empQueryServiceImpl.getRegistrationInfo(bean);
        return result;
    }

//    就业信息查询
    @RequestMapping(value = "/getEmploymentInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getEmploymentInfo(@RequestBody queryParamBean bean) throws Exception {

        Result result = empQueryServiceImpl.getEmploymentInfo(bean);
        return result;
    }

//    失业信息查询
    @RequestMapping(value = "/getUnEmploymentInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getUnEmploymentInfo(@RequestBody queryParamBean bean) throws Exception {

        Result result = empQueryServiceImpl.getUnEmploymentInfo(bean);
        return result;
    }

//    就业援助信息查询
    @RequestMapping(value = "/getEmployAssistInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getEmployAssistInfo(@RequestBody queryParamBean bean) throws Exception {

        Result result = empQueryServiceImpl.getEmployAssistInfo(bean);
        return result;
    }

    //    就业扶持政策信息查询
    @RequestMapping(value = "/getEmployPolicyInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getEmployPolicyInfo(@RequestBody queryParamBean bean) throws Exception {

        Result result = empQueryServiceImpl.getEmployPolicyInfo(bean);
        return result;
    }

    //    公共就业服务信息查询
    @RequestMapping(value = "/getPublicServiceInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getPublicServiceInfo(@RequestBody queryParamBean bean) throws Exception {

        Result result = empQueryServiceImpl.getPublicServiceInfo(bean);
        return result;
    }

    //    失业保险待遇信息查询
    @RequestMapping(value = "/getUnEmpInsTreatInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getUnEmpInsTreatInfo(@RequestBody queryParamBean bean) throws Exception {

        Result result = empQueryServiceImpl.getUnEmpInsTreatInfo(bean);
        return result;
    }

    //    检信息查询
    @RequestMapping(value = "/getInspectionInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getInspectionInfo(@RequestBody queryParamBean bean) throws Exception {

        Result result = empQueryServiceImpl.getInspectionInfo(bean);
        return result;
    }

    //    其他记载事项查询
    @RequestMapping(value = "/getOtherMattersInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getOtherMattersInfo(@RequestBody queryParamBean bean) throws Exception {

        Result result = empQueryServiceImpl.getOtherMattersInfo(bean);
        return result;
    }
}
