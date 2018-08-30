package com.tecsun.sisp.adapter.modules.cyt.controller;

import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.ParamUtil;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.AccountQueryBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.AccountQueryOutBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.CollectBean;
import com.tecsun.sisp.adapter.modules.cyt.service.impl.CollectServiceImpl;
import com.tecsun.sisp.adapter.modules.cyt.service.impl.SettleServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xumaohao on 2017/8/1.
 */

@RestController
@RequestMapping("/adapter/cyt")
public class CollectController extends BaseController {

    Logger logger = Logger.getLogger(CollectController.class);

    @Autowired
    CollectServiceImpl collectService;
    @Autowired
    SettleServiceImpl settleService;

    /**
     * 结算汇总
     * @param bean
     * @return
     */
    @RequestMapping("/collect")
    public Result collect(@RequestBody Map<String,Object> bean){

        String check = ParamUtil.checkNullOrEmplty(bean, "jslsh");
        if(StringUtils.isNotEmpty(check)){
            return error(check);
        }

        try {
            CollectBean collect = collectService.collectSummarizing4cssp(bean);
            return ok("汇总成功",collect);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return error("汇总失败");
    }

    /**
     * 结算汇总取消
     * @param bean
     * @return
     */
    @RequestMapping("/collectCancel")
    public Result collectCancel(@RequestBody Map<String,Object> bean){

        String check = ParamUtil.checkNullOrEmplty(bean, "hzlsh", "zh");
        if(StringUtils.isNotEmpty(check)){
            return error(check);
        }

        try {
            //校验汇总信息
            check = checkHzlsh(bean.get("hzlsh"));
            if(StringUtils.isNotEmpty(check)){
                return error(check);
            }
            CollectBean collect = collectService.cancelSummarizing4cssp(bean);
            return ok("汇总取消成功", collect);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return error("汇总取消失败");
    }

    /**
     * 汇总查询
     * @param bean
     * @return
     */
    @RequestMapping("/collectQuery")
    public Result collectQuery(@RequestBody AccountQueryBean bean){
        //入参校验
        String check = checkquery(bean);
        if(StringUtils.isNotEmpty(check)){
            return error(check);
        }

        try {
            Page<CollectBean> collect = collectService.selectSummarizing4cssp(bean);
            return ok(collect.getCount(),"查询成功", collect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error("查询失败");
    }

    private String checkquery(AccountQueryBean bean) {
        if(bean == null)
            return "入参不能为空";
        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
//        if(StringUtils.isEmpty(bean.getDeviceid()))
//            return  "标识Id deviceid不能为空";
//        if(StringUtils.isEmpty(bean.getTokenid()))
//            return "权限校验码tokenid不能为空";
        if(StringUtils.isEmpty(bean.getZh()) &&
                StringUtils.isEmpty(bean.getHzlsh()) &&
                StringUtils.isEmpty(bean.getJgbm()))
            return "登录账号zh不能为空";
        return "";
    }

    //校验汇总信息
    private String checkHzlsh(Object hzlsh) throws Exception{
        AccountQueryBean accountQueryBean = new AccountQueryBean();
        accountQueryBean.setHzlsh((String) hzlsh);
        accountQueryBean.setFlag(3);
        Page<AccountQueryOutBean> accountQueryOutBeanPage = settleService.selectSettleAccounts4cssp(accountQueryBean);
        if(accountQueryOutBeanPage.getData() == null || accountQueryOutBeanPage.getData().size() == 0) {
            return "该汇总流水号不存在";
        }

        if(!accountQueryOutBeanPage.getData().get(0).getHzzt().equals(Constants.CYT_COLLECT_STATUS.get("1"))) {
            return "操作失败：该流水号处于" + accountQueryOutBeanPage.getData().get(0).getHzzt() + "状态";
        }
        return "";
    }

}
