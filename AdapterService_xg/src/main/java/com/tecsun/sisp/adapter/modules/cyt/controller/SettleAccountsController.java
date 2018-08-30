package com.tecsun.sisp.adapter.modules.cyt.controller;

import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.ParamUtil;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.AccountQueryBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.ChargeInformationBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.AccountQueryOutBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.ClaimBean;
import com.tecsun.sisp.adapter.modules.cyt.service.impl.SettleServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xumaohao on 2017/7/21.
 */

@RestController
@RequestMapping("/adapter/cyt")
public class SettleAccountsController extends BaseController {
    Logger logger = Logger.getLogger(SettleAccountsController.class);


    @Autowired
    SettleServiceImpl settleService;


    /**
     * 门诊结算
     * @param bean
     * @return
     */
    @RequestMapping("/account")
    public Result account(@RequestBody ChargeInformationBean bean) {

        String check = checkapply(bean);
        if(StringUtils.isNotEmpty(check)){
            return error(check);
        }
        try {
            ClaimBean claim = settleService.insertSettleAccounts4cssp(bean);
            return ok("结算成功",claim);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("结算失败：",e);
        }

        return error("结算失败");
    }

    /**
     * 门诊结算查询
     * @param bean
     * @return
     */
    @RequestMapping("/accountQuery")
    public Result accountQuery(@RequestBody AccountQueryBean bean) {

        String check = checkquery(bean);
        if(StringUtils.isNotEmpty(check)){
            return error(check);
        }
        try {
            Page<AccountQueryOutBean> accountQueryOut = settleService.selectSettleAccounts4cssp(bean);
            return ok(accountQueryOut.getCount(),"查询成功",accountQueryOut);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询失败：",e);
        }
        return error("查询失败");
    }

    /**
     * 门诊结算取消
     * @param bean
     * @return
     */
    @RequestMapping("/accountCancel")
    public Result accountCancel(@RequestBody Map<String,Object> bean) {

        String check = ParamUtil.checkNullOrEmplty(bean, "jslsh", "zh");
        if(StringUtils.isNotEmpty(check)){
            return error(check);
        }
        try {
            boolean cancel = settleService.cancelSettleAccounts4cssp(bean);
            if(cancel){
                return ok("取消成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("取消失败：",e);
        }
        return error("取消失败");
    }

    /**
     * 门诊结算入参校验
     * @param bean
     * @return
     */
    private String checkapply(ChargeInformationBean bean) {
        if(bean == null)
        return "入参不能为空";
        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
//        if(StringUtils.isEmpty(bean.getDeviceid()))
//            return  "标识Id deviceid不能为空";
//        if(StringUtils.isEmpty(bean.getTokenid()))
//            return "权限校验码tokenid不能为空";
        if(StringUtils.isEmpty(bean.getZh()))
            return  "医生账号zh不能为空";
        if(StringUtils.isEmpty(bean.getSfzh()))
            return "身份证号sfzh不能为空";
        if(StringUtils.isEmpty(bean.getXm()))
            return "姓名xm不能为空";
        if(StringUtils.isEmpty(bean.getJbbm()))
            return  "疾病编码jbbm不能为空";
        if(StringUtils.isEmpty(bean.getJbmc()))
            return  "疾病名称jbmc不能为空";
        if(bean.getMedicine().length == 0)
            return  "药物信息medicine不能为空";

        return "";
    }


    /**
     * 门诊结算查询入参校验
     * @param bean
     * @return
     */
    private String checkquery(AccountQueryBean bean) {
        if(bean == null)
            return "入参不能为空";
        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
//        if(StringUtils.isEmpty(bean.getDeviceid()))
//            return  "标识Id deviceid不能为空";
//        if(StringUtils.isEmpty(bean.getTokenid()))
//            return "权限校验码tokenid不能为空";

        return "";
    }

}
