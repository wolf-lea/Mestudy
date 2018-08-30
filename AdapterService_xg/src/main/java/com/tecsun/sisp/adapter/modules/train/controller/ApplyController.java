package com.tecsun.sisp.adapter.modules.train.controller;

import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.train.entity.request.ApplyBean;
import com.tecsun.sisp.adapter.modules.train.entity.response.TrainApplyBean;
import com.tecsun.sisp.adapter.modules.train.service.impl.ApplyServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by xumaohao on 2017/8/30.
 */
@RestController
@RequestMapping("/adapter/train")
public class ApplyController extends BaseController{

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    ApplyServiceImpl applyService;

    /**
     * 新增就业培训报名
     * @param bean
     * @return
     */
    @RequestMapping("/addApply")
    public Result addApply(@RequestBody ApplyBean bean) {
        try {
            //入参校验
            String err = checkApply(bean);
            if(StringUtils.isNotEmpty(err))
                return error(err);
            //判断是否重复报名
            if(applyService.isApply4cssp(bean))
                return error("抱歉！请不要重复报名哦！");
            //新增报名记录
            boolean is = applyService.addApply4cssp(bean);
            if(is)
                return ok("恭喜您！报名成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return error("报名失败");
    }

    /**
     * 查询就业培训报名记录
     * @param bean
     * @return
     */
    @RequestMapping("/selectApply")
    public Result selectApply(@RequestBody ApplyBean bean) {
        try {
            //入参校验
            String err = checkSelect(bean);
            if(StringUtils.isNotEmpty(err))
                return error(err);
            //查询记录
            Page<TrainApplyBean> apply = applyService.seleceApply4cssp(bean);
            return ok("查询成功", apply);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询报名信息失败：" + e);
        }
        return error("查询失败");
    }

    /**
     * 新增报名记录入参校验
     * @param bean
     * @return
     * @throws Exception
     */
    private String checkApply(ApplyBean bean) throws Exception{
        if(bean == null)
            return "入参不能为空";

        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码不能为空";
        if(StringUtils.isEmpty(bean.getXm()))
            return "姓名不能为空";
        if(StringUtils.isEmpty(bean.getSfzh()))
            return "身份证号码不能为空";
        if(StringUtils.isEmpty(bean.getPhone()))
            return "手机号码不能为空";
        if(!(isChinaPhoneLegal(bean.getPhone()) || isHKPhoneLegal(bean.getPhone())))
            return "手机号码有误";

        return "";
    }

    /**
     * 查询报名记录入参校验
     * @param bean
     * @return
     * @throws Exception
     */
    private String checkSelect(ApplyBean bean) throws Exception{
        if(bean == null)
            return "入参不能为空";

        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码不能为空";
        if(StringUtils.isEmpty(bean.getXm()))
            return "姓名不能为空";
        if(StringUtils.isEmpty(bean.getSfzh()))
            return "身份证号码不能为空";

        return "";
    }

    /****************************手机号码校验******************************/
    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /****************************校验结束******************************/
}
