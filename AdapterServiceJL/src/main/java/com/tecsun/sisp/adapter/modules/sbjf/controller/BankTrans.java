package com.tecsun.sisp.adapter.modules.sbjf.controller;

import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.response.DictionaryVO;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean.CreateFlowNumBean;
import com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean.FeedbackBean;
import com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean.SbBankTransBean;
import com.tecsun.sisp.adapter.modules.sbjf.service.impl.BankTransServiceImpl;
import com.tecsun.sisp.adapter.modules.sbjf.service.impl.PayRecordServiceImpl;
import com.tecsun.sisp.adapter.modules.sbjf.service.impl.bank.BankResMsg;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by linzetian on 2017/6/19.
 * 调银行报文接口
 */
@RestController
@RequestMapping("/adapter/sbjf")
public class BankTrans extends BaseController {

    @Autowired
    PayRecordServiceImpl payRecordService;
    @Autowired
    BankTransServiceImpl bankTransService;
    @Autowired
    CommServiceImpl commService;

    Logger logger = Logger.getLogger(BankTrans.class);

    /**
     * 生成交易流水号
     * @param bean
     * @return
     */
    @RequestMapping("/createFlowNum")
    public Result createFlowNum(@RequestBody CreateFlowNumBean bean){
        try {

            //校验
            String err = checkCreateFlowNum(bean);
            if(StringUtils.isNotEmpty(err)){
                return error(err);
            }

            //生成
            String flowNum = bankTransService.createFlowNum(bean);
            return ok("生成成功", flowNum);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取交易流水号失败",e);
        }

        return error("获取交易流水号失败");
    }

    /**
     * 调银行接口
     * @param bean
     * @return
     */
    @RequestMapping("/sbBankTrans")
    public Result sbBankTrans(@RequestBody SbBankTransBean bean ,HttpServletRequest req){
        BankResMsg resMsg = null;
        try {
            //校验
            String err = checkSbBankTrans(bean);
            if(StringUtils.isNotEmpty(err)){
                return error(err);
            }
            bean.setAdaptertokenId(req.getParameter("adaptertokenId"));
            resMsg = bankTransService.bankTrans(bean);
//            if(ResponseCode.SUCCESS.equals(resMsg.getBankResult())){
//                return ok("缴费成功",resMsg);
//            }

            return ok("缴费接口调用成功", resMsg);
//            return error("缴费失败",resMsg);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("缴费接口调用失败",e);
        }
        return error("缴费接口调用失败");
    }

    /**
     * 交易反馈接口
     * @param bean
     * @return
     */
    @RequestMapping("/feedback")
    public Result feedback(@RequestBody FeedbackBean bean){
        try {

            //校验
            String err = checkFeedback(bean);
            if(StringUtils.isNotEmpty(err)){
                return error(err);
            }

            //生成
            bankTransService.feedback(bean);
            return ok("反馈成功");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("反馈失败",e);
        }

        return error("反馈失败");
    }


    /**
     *=========校验***************
     */
    private String checkCreateFlowNum(CreateFlowNumBean bean) throws Exception{
        if(bean == null)
            return "入参不能为空";

        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
        if(StringUtils.isEmpty(bean.getDeviceid()))
            return  "标识Id deviceid不能为空";
        if(StringUtils.isEmpty(bean.getTokenid()))
            return "权限校验码tokenid不能为空";
        if(StringUtils.isEmpty(bean.getPayNum()))
            return "缴费单号payNum不能为空";
        if(StringUtils.isEmpty(bean.getInsureCode()))
            return  "险种编码insureCode不能为空";
        if(bean.getRecords() == null || bean.getRecords().length ==0)
            return "缴费记录records不能为空";
//        if(StringUtils.isEmpty(bean.getSfzh()))
//            return "身份证号sfzh不能为空";
//        if(StringUtils.isEmpty(bean.getXm()))
//            return "姓名xm不能为空";
        if(bean.getAllAmount() == 0)
            return "总额allAmount不能为空";



//        //缴费情况处理
//        PayRecordEntity prInfo = payRecordService.payRecordInfo4cssp(bean.getPayNum());
//
//        if( prInfo != null){
//            if(Constants.SBJF_STATUS_OF_IS_PAY.equals(prInfo.getStatus())){
//                //已缴费
//                return "该缴费单已缴费成功，请勿重复缴费";
//            }else if(Constants.SBJF_STATUS_OF_ON_PAY.equals(prInfo.getStatus())){
//                //在途缴费
//                return "您好，您已缴过费，请勿重复缴费";
//            }
//        }

        return "";
    }
    private String checkSbBankTrans(SbBankTransBean bean) throws Exception{
        if(bean == null)
            return "入参不能为空";

        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
        if(StringUtils.isEmpty(bean.getDeviceid()))
            return  "标识Id deviceid不能为空";
        if(StringUtils.isEmpty(bean.getTokenid()))
            return "权限校验码tokenid不能为空";
        if(StringUtils.isEmpty(bean.getHexStr()))
            return "报文HexStr不能为空";
        if(StringUtils.isEmpty(bean.getFinancialType()))
            return "financialType,缺失或值有误";
        if(bean.getCodeType() == 0)
            return "编码类型codeType 缺失或值有误";

//        //缴费情况处理 判断是否允许重复缴费（测试用）
//        if("0".equals(Config.getInstance().get("sbjf.is_repetition"))){
//            //根据交易流水号获取缴费单号
//            List<PayRecordDetailEntity> resList = payRecordService.payRecordDetailList4cssp(bean.getFlowNum());
//
//            if(!(resList.size() > 0)){
//                return "该流水号有误，请确认后再缴费";
//            }
//            PayRecordEntity prInfo = payRecordService.payRecordInfo4cssp(resList.get(0).getPayNum());
//            if( prInfo != null){
//                if(Constants.SBJF_STATUS_OF_IS_PAY.equals(prInfo.getStatus())){
//                    //已缴费
//                    return "该缴费单已缴费成功，请勿重复缴费";
//                }else if(Constants.SBJF_STATUS_OF_ON_PAY.equals(prInfo.getStatus())){
//                    //在途缴费
//                    return "您好，您已缴过费，请勿重复缴费";
//                }
//            }
//        }

        return "";
    }
    private String checkFeedback(FeedbackBean bean) throws Exception{
        if(bean == null)
            return "入参不能为空";

        //渠道为德生宝，默认支付方式为POS机
        if((Constants.TSB.equals(bean.getChannelcode()))){
            bean.setPayType("POS-Machine");
        }

        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
        if(StringUtils.isEmpty(bean.getDeviceid()))
            return  "标识Id deviceid不能为空";
        if(StringUtils.isEmpty(bean.getTokenid()))
            return "权限校验码tokenid不能为空";
        if(StringUtils.isEmpty(bean.getFlowNum()))
            return "交易流水号flowNum不能为空";
        if(StringUtils.isEmpty(bean.getStatus()))
            return "状态status不能为空";
        if(StringUtils.isEmpty(bean.getPayType()))
            return "支付方式不能为空";

        //支付方式转换处理
        List<DictionaryVO> list = commService.getDictionaryById4Sisp(Constants.PAYMENT_TYPE_GROUP);
        if (list != null && list.size() > 0) {
            for (int i=0;i<list.size();i++){
                if(bean.getPayType().equals(list.get(i).getCode())){
                    bean.setPayType(list.get(i).getName());
                    break;
                }
            }
        }else return "支付方式不存在";
        return "";
    }
}
