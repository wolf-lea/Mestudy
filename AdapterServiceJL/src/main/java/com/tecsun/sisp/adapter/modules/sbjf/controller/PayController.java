package com.tecsun.sisp.adapter.modules.sbjf.controller;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.sbjf.entity.PayRecordEntity;
import com.tecsun.sisp.adapter.modules.sbjf.entity.ToPayEntity;
import com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean.GetListBean;
import com.tecsun.sisp.adapter.modules.sbjf.service.impl.GradeServiceImpl;
import com.tecsun.sisp.adapter.modules.sbjf.service.impl.PayRecordServiceImpl;
import com.tecsun.sisp.adapter.modules.sbjf.service.impl.PayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by linzetian on 2017/6/6.
 * 缴费记录
 */
@RestController
@RequestMapping("/adapter/sbjf")
public class PayController extends BaseController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    PayRecordServiceImpl payRecordService;

    @Autowired
    GradeServiceImpl gradeService;

    @Autowired
    PayServiceImpl payService;

    @RequestMapping("/getToPayList")
    /**
     * 获取社保卡缴费清单
     */
    public Result getToPayList(@RequestBody GetListBean bean){
        String err = "";
        try {
            err = checkGetToPayList(bean);
            if(StringUtils.isNotEmpty(err))
                return error(err);

            Page<ToPayEntity> pages = payService.getToPayList4other(bean);
            if(pages.getData() !=null){
                Map<String,Object> res = new HashMap<>();
                res.put("payNum",bean.getPayNum());
                res.put(Constants.PAGE_NO,pages.getPageno());
                res.put(Constants.PAGE_SIZE,pages.getPagesize());
                res.put(Constants.DATA_COUNT,pages.getCount());
                res.put("data",pages.getData());
                if(pages.getData().size() == 0){
                    return ok("您好，您没有待缴费记录", res);
                }
                return ok("操作成功", res);
            }else {
                return ok("您好，您没有待缴费记录", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("", e);
            return error("出现异常，请联系管理员");
        }
    }

    private String checkGetToPayList(GetListBean bean)throws Exception {
        if (bean == null)
            return "入参不能为空";

        if (StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
        if (StringUtils.isEmpty(bean.getDeviceid()))
            return "标识Id deviceid不能为空";
        if (StringUtils.isEmpty(bean.getTokenid()))
            return "权限校验码tokenid不能为空";
        if (StringUtils.isEmpty(bean.getInsureCode()))
            return "险种编码insureCode不能为空";
        if (StringUtils.isEmpty(bean.getPayNum())) {
            if (StringUtils.isEmpty(bean.getXm())
                    || StringUtils.isEmpty(bean.getSfzh())) {
                return "姓名xm,身份证sfzh不完整";
            }
        }

        //获取缴费单号（缴费单号错误）
        String payNum = bean.getPayNum();
        if (StringUtils.isEmpty(payNum)) {
            payNum = payService.getPayNum4other(bean);
            if (StringUtils.isEmpty(payNum)) {
                return "您好，您没有待缴费记录";
            }
            bean.setPayNum(payNum);
        }

        //缴费情况处理 判断是否允许重复缴费（测试用）
        if ("0".equals(Config.getInstance().get("sbjf.is_repetition"))) {
            //缴费情况处理
            Map<String, Object> map = new HashMap<>();

            map.put("payNum", bean.getPayNum());

            PayRecordEntity prInfo = payRecordService.payRecordInfo4cssp(bean.getPayNum());
            if (prInfo != null) {
                if (Constants.SBJF_STATUS_OF_IS_PAY.equals(prInfo.getStatus())) {
                    Page<PayRecordEntity> pages = payRecordService.payRecordList4cssp(map);
                    for (int i=0;i<pages.getData().size();i++) {
                        //判断已缴费的险种
                        if(bean.getInsureCode().equals(pages.getData().get(i).getInsureCode())){
                            //已缴费
                            return "您好，您没有待缴费记录";
                        }
                    }
                } else if (Constants.SBJF_STATUS_OF_ON_PAY.equals(prInfo.getStatus())) {
                    Page<ToPayEntity> pages = payService.getToPayList4other(bean);
                    if (pages.getData() != null || pages.getData().size() != 0){
                        //在途缴费
                        return "您好，您已缴过费，请勿重复缴费";
                    }
                }
            }
        }
        return "";
    }

    /**
     * 获取缴费记录清单
     * @param bean
     * @return
     */
    @RequestMapping("/getPayRecordList")
    public Result getPayRecordList(@RequestBody Map<String,Object> bean){
        String err ="";
        try {
            err = checkGetPayRecordList(bean);
            if(StringUtils.isNotEmpty(err)){
                return error(err);
            }

            String sfzh = bean.get("sfzh")!= null ? bean.get("sfzh").toString() : "";
            String payNum = bean.get("payNum") != null ? bean.get("payNum").toString() : "";

            Page<PayRecordEntity> pages = payRecordService.payRecordList4cssp(bean);
            if(pages.getData() != null){
                Map<String,Object> res = new HashMap<>();
                res.put(Constants.PAGE_NO, pages.getPageno());
                res.put(Constants.PAGE_SIZE,pages.getPagesize());
                res.put(Constants.DATA_COUNT,pages.getCount());
                res.put("data",pages.getData());


                Map<String,Object> map = payRecordService.getPrintCount4cssp(sfzh,payNum);

                res.put("printCount",map.get("printCount"));
                res.put("printId",map.get("printId"));

                return ok("操作成功", res);
            }

            return error("操作失败", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("",e);
            return error("出现异常，请联系管理员");
        }
    }

    private String checkGetPayRecordList(Map<String,Object> bean) throws Exception{
        if(bean == null){
            return "入参不能为空";
        }
        String errXS =  ParamUtil.checkNullOrEmplty(bean, "xm", "sfzh");
        String errPN = ParamUtil.checkNullOrEmplty(bean,"payNum");
        if(!"".equals(errXS) &&
                !"".equals(errPN)){
            return  "xm,sfzh和payNum 必须二选1";
        }

        return "";
    }

    /**
     * 减少一次缴费打印次数
     * @param bean
     * @return
     */
    @RequestMapping("/printSubtract")
    public Result printSubtract(@RequestBody Map<String,Object> bean){
        String err ="";
        try {
            err = checkPrintSubtract(bean);
            if(StringUtils.isNotEmpty(err)){
                return error(err);
            }

            boolean isOk = payRecordService.subtractPrintCount(bean.get("printId")+"");
            if(isOk){
                return ok("操作成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("",e);
            return error("出现异常，请联系管理员");
        }

        return error("操作失败");
    }

    private String checkPrintSubtract(Map<String,Object> bean) throws Exception{
        if(bean == null){
            return "入参不能为空";
        }
        String errXS =  ParamUtil.checkNullOrEmplty(bean,"channelcode","deviceid","printId");
        if(!"".equals(errXS)){
            return  errXS;
        }
        return "";
    }
}
