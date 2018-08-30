package com.tecsun.sisp.adapter.modules.sbjf.service.impl;

import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.UID;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.sbjf.entity.PayRecordDetailEntity;
import com.tecsun.sisp.adapter.modules.sbjf.entity.ToPayEntity;
import com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean.CreateFlowNumBean;
import com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean.FeedbackBean;
import com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean.SbBankTransBean;
import com.tecsun.sisp.adapter.modules.sbjf.service.impl.bank.BankReqMsg;
import com.tecsun.sisp.adapter.modules.sbjf.service.impl.bank.BankResMsg;
import com.tecsun.sisp.adapter.modules.sbjf.service.impl.bank.ReqBankService;
import com.tecsun.sisp.adapter.modules.sbjf.service.impl.bank.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linzetian on 2017/6/19.
 */
@Service("BankTransServiceImpl")
public class BankTransServiceImpl extends BaseService {

    @Autowired
    PayRecordServiceImpl payRecordService;
    @Autowired
    PayServiceImpl payService;
    @Autowired
    PayReverseRecordServiceImpl payReverseRecordService;

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.sbjf.service.impl.PayRecordServiceImpl.";

    /**
     * 生成缴费单号
     * @param bean
     * @return
     * @throws Exception
     */
    public String createFlowNum(CreateFlowNumBean bean) throws Exception{
        bean.setFlowNum(UID.dateTimes());
        payRecordService.insertPayRecord4cssp(bean);

        //插入缴费详情
        CreateFlowNumBean.Record[] records = bean.getRecords();
        PayRecordDetailEntity prd = null;
        for (CreateFlowNumBean.Record record : records) {
            //切换数据库
            ToPayEntity pl = payService.getToPayInfo4other(record.getPayId());

            prd = new PayRecordDetailEntity();
            prd.setGradeCode(record.getGradeCode());
            prd.setGradeName(record.getGradeName());
            prd.setGradeAmount(record.getGradeAmount());
            prd.setPrId(bean.getPrId());
            prd.setPayNum(bean.getPayNum());
            prd.setInsureCode(bean.getInsureCode());

            prd.setPayId(pl.getPayId());
            prd.setXm(pl.getXm());
            prd.setSfzh(pl.getSfzh());
            prd.setInsureCode(bean.getInsureCode());
            prd.setSubInsureCode(pl.getSubInsureCode());
            prd.setSubInsureName(pl.getSubInsureName());
            prd.setRelation(pl.getRelation());
            prd.setYear(pl.getYear());

            //切换数据库
            payRecordService.insertPayRecordDetail4cssp(prd);
        }

        return bean.getFlowNum();
    }

    /**
     * 银行接口
     * @param bean 入参
     * @throws Exception
     */
    public BankResMsg bankTrans(SbBankTransBean bean) throws Exception{
        String status = Constants.SBJF_STATUS_OF_FAIL_PAY;
        //入参报文
        BankReqMsg reqMsg = new BankReqMsg();
        reqMsg.setTokenid(bean.getTokenid());
        reqMsg.setDeviceid(bean.getDeviceid());
        reqMsg.setChannelcode(bean.getChannelcode());
        reqMsg.setHexStr(bean.getHexStr());
        reqMsg.setAuxiliaryType(bean.getAuxiliaryType());
        reqMsg.setFinancialType(bean.getFinancialType());
        reqMsg.setCodeType(bean.getCodeType());

        BankResMsg resMsg = ReqBankService.sendRequest(bean.getAdaptertokenId(), reqMsg);

        if(ResponseCode.SUCCESS.equals(resMsg.getBankResult())){
            //成功
            status = Constants.SBJF_STATUS_OF_IS_PAY;

            if(Constants.SBJF_OPERATE_REVERSE == bean.getAuxiliaryType()){
                //冲正操作
                status = Constants.SBJF_STATUS_OF_INIT;
                //插入冲正记录
                payReverseRecordService.insertReverseRecord4cssp(bean.getFlowNum(),
                        Constants.SBJF_STATUS_OF_IS_REVERSE);
            }
        }else {
            if(Constants.SBJF_OPERATE_REVERSE == bean.getAuxiliaryType()){
                //插入冲正记录
                payReverseRecordService.insertReverseRecord4cssp(bean.getFlowNum(),
                        Constants.SBJF_STATUS_OF_FAIL_REVERSE);
                return resMsg;
            }
        }

        //修改状态
        String payType = "POS机";
        updateAllStatus(status,resMsg.getId(),resMsg.getMessage(),bean.getFlowNum(),payType);

        return resMsg;
    }


    /**
     * 交易反馈
     * @param bean
     * @return
     * @throws Exception
     */
    public void feedback(FeedbackBean bean) throws Exception{
        updateAllStatus(bean.getStatus(),bean.getTranNum(),bean.getMessage(),bean.getFlowNum(),bean.getPayType());
    }

    /**
     * 修改状态
     * @param status
     * @param message
     * @param flowNum
     * @throws Exception
     */
    public void updateAllStatus(String status,String tranNum,String message,String flowNum,String payType) throws Exception{
        //修改缴费记录表状态
        Map<String,Object> upBean = new HashMap<>();
        upBean.put("status", status);
        upBean.put("message", message);
        upBean.put("flowNum", flowNum);
        upBean.put("tranNum", tranNum);
        upBean.put("payType", payType);
        payRecordService.updateRecordStatus4cssp(upBean);

        //修改待缴费清单表状态
        List<PayRecordDetailEntity> prdList = payRecordService.payRecordDetailList4cssp(flowNum);
        payService.updateToPayList4other(status, prdList);
    }
}
