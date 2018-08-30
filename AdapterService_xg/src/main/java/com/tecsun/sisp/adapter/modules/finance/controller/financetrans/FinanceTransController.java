package com.tecsun.sisp.adapter.modules.finance.controller.financetrans;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.finance.bankUtils.BKUtils;
import com.tecsun.sisp.adapter.modules.finance.bankUtils.CCBBankService;
import com.tecsun.sisp.adapter.modules.finance.bankUtils.CUPBankService;
import com.tecsun.sisp.adapter.modules.finance.bankUtils.HandlerResult;
import com.tecsun.sisp.adapter.modules.finance.bankUtils.decoder.CCBBankPkgDecoder;
import com.tecsun.sisp.adapter.modules.finance.bankUtils.decoder.CUPBankPkgDecoder;
import com.tecsun.sisp.adapter.modules.finance.entity.request.DeviceRelationBean;
import com.tecsun.sisp.adapter.modules.finance.entity.request.FinanceTradeBean;
import com.tecsun.sisp.adapter.modules.finance.entity.request.FinancialMsgBean;
import com.tecsun.sisp.adapter.modules.finance.entity.response.DeviceRelationVO;
import com.tecsun.sisp.adapter.modules.finance.entity.response.PhoneChargeVO;
import com.tecsun.sisp.adapter.modules.finance.service.impl.FinanceServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**金融业务
 * Created by danmeng on 2017/6/5.
 */
@Controller
@RequestMapping(value = "/adapter/finance")
public class FinanceTransController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(FinanceTransController.class);
    @Autowired
    private FinanceServiceImpl financeService;

    /**
     * 根据设备编码获取设备信息（商户号，终端号）,将设备编码转为小写对比
     * @param bean 设备编码
     * @return
     */
    @RequestMapping(value = "/getMerchantInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getMerchantInfo(@RequestBody DeviceRelationBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getPropertyCode())||StringUtils.isBlank(bean.getPropertyType())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入设备编号和编号类型");
        }
        if(!"1".equals(bean.getPropertyType())&&!"2".equals(bean.getPropertyType())&&!"3".equals(bean.getPropertyType())){
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入正确的设备编号类型(1:deviceid设备ID/CPU,2:IMEI号,3:SN出厂序列号)");

        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "查询失败";
        try {
            bean.setPropertyCode((bean.getPropertyCode()).toLowerCase());
            List<DeviceRelationVO> list=financeService.selectDeviceRelation4Sisp(bean);
            if(list!=null&&list.size()>0)return result(Constants.RESULT_MESSAGE_SUCCESS,"查询成功",list);
            else message="未查到相关信息";
        } catch (Exception e) {
            message = "获取设备信息失败：" + e.getMessage();
            logger.error("金融业务-获取设备信息失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 银行报文传输
     * 记录日志：业务类型2001-社保缴费;3001-助农取款;9995-转账;9996-消费;9997-生活缴费;9998-信用卡还款;9999-手机充值;
     * 辅助操作 1005-冲正 修改对应数据
     * @return
     */
    @RequestMapping(value = "/bankTrans", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result bankTrans(@RequestBody FinancialMsgBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getHexStr()) ||StringUtils.isBlank(bean.getFinancialType()) || (bean.getCodeType() != 64 && bean.getCodeType() != 128)) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        String hexStr = bean.getHexStr();
        String financialType = bean.getFinancialType();
        FinanceTradeBean financeTradeBean = new FinanceTradeBean();
        HandlerResult bkResult = new HandlerResult();
        int status = 0;
        try {
            if (StringUtils.isNotEmpty(hexStr)) {
                financeTradeBean.setBusType(financialType);
                financeTradeBean.setAuxiliaryType(bean.getAuxiliaryType());
                if (bean.getAuxiliaryType() == 0) {
                    financeTradeBean.setTransType( Constants.TRANS_TYPE.get(financialType));
                }
                //记录日志
                try {
                    financeTradeBean.setPayType(bean.getChannelcode());
                    financeTradeBean.setDeviceid(bean.getDeviceid());
                    status = insertFinanceTradeLog(financeTradeBean, hexStr, bean.getCodeType());
                } catch (Exception e) {
                    logger.error("新增或查询金融交易信息失败:编码" + bean.getCodeType() + " 报文:" + hexStr);
                    status = -999;
                }
                bkResult = sendReqOfType(hexStr, financialType, bean.getCodeType());
                Map<String, Object> valueMap = new HashMap<String, Object>();
                valueMap.put("bankResult", bkResult.valueMap.get("39"));
                valueMap.put("id", financeTradeBean.getTradeId());
                valueMap.put("hexStr", bkResult.hexStr);
                statusCode = bkResult.result + "";
                message = bkResult.message;
                return result(statusCode, message, valueMap);
            }
        } catch (Exception e) {
            message = "操作失败" + e.getMessage();
            logger.error("银行报文传输bankTrans操作失败：", e);
        } finally {
            if (financeTradeBean.getTradeId() != 0) {
                financeTradeBean.setBankMsg(message + "\n status：" + status +
                        "\n入参：" + JsonHelper.javaBeanToJson(bean) + "\n出参" + JsonHelper.javaBeanToJson(bkResult));
                updateFinanceTradeLog(financeTradeBean, bkResult, statusCode);
            }
        }
        return result(statusCode, message);
    }

    //按业务将报文发送对应银行
    public HandlerResult sendReqOfType(String bodyHexStr,String financialType,int codeType) throws Exception {
        HandlerResult bkResult=new HandlerResult();
        String bankIP="bank."+ financialType+"."+codeType+".ip";
        String bankPort="bank."+ financialType+"."+codeType+".port";
        if (codeType==64) {
            bkResult = new CCBBankService(Config.getInstance().get(bankIP),
                    Integer.parseInt(Config.getInstance().get(bankPort)),
                    Integer.parseInt(Config.getInstance().get("bank.timeout"))).sendReq4Hex(bodyHexStr);
        } else if ((codeType==128)) {
            bkResult = new CUPBankService(Config.getInstance().get(bankIP),
                    Integer.parseInt(Config.getInstance().get(bankPort)),
                    Integer.parseInt(Config.getInstance().get("bank.timeout"))).sendReq4Hex(bodyHexStr);
        }
        return bkResult;
    }

        /**
         * 添加交易记录到金融交易信息表中，用于和银行进行消费对账，适用对象：消费，转账
         *
         * @param bean
         * @param
         */
    public int insertFinanceTradeLog(FinanceTradeBean bean, String hexStr,int codeType){
        int status = 0;
        try {
            byte[] reqs = BKUtils.str2Bcd(hexStr);
            // 1 在数据库中生成 交易 信息，以便对账使用
            Map<String, String> valueMap = new HashMap<String, String>();
            if (codeType==64) {
                ChannelBuffer msg = ChannelBuffers.directBuffer(reqs.length - 2);
                msg.writeBytes(reqs, 2, reqs.length - 2);
                CCBBankPkgDecoder decoder = new CCBBankPkgDecoder();
                decoder.decode(msg, valueMap);

            } else if (codeType==128) {
                ChannelBuffer msg = ChannelBuffers.directBuffer(reqs.length - 4);
                msg.writeBytes(reqs, 4, reqs.length - 4);
                CUPBankPkgDecoder decoder = new CUPBankPkgDecoder();
                decoder.decode(msg, valueMap);
            }
            bean.setMsgtype(valueMap.get("msgtype"));//报文类型
            bean.setMerchantId(valueMap.get("42"));//商户号
            bean.setTerminalId(valueMap.get("41"));//终端号
            bean.setSeqZd(valueMap.get("11"));//终端交易流水号
            bean.setPatchNo(valueMap.get("60.2"));//批次号
            bean.setCardNoPay(valueMap.get("2"));//付款账号
            if(bean.getAuxiliaryType()==0&& bean.getTransType()==2 &&codeType==64) {
                bean.setCardNoPay(valueMap.get("62"));//收款账号
            }
            if (bean.getAuxiliaryType()==1005) {//冲正
                bean.setCorrectReason(valueMap.get("39"));
            }
            bean.setTimeZd(new Date());//终端交易时间
            if (StringUtils.isNotEmpty(valueMap.get("7"))) {
                bean.setTimeZd(CommUtil.parseDate(CommUtil.getNowDateLongStr("yyyy") + valueMap.get("7"), "yyyyMMddHHmmss"));//终端交易时间
            }
            if (StringUtils.isNotEmpty(valueMap.get("4"))) {
                bean.setAmount(Long.parseLong(valueMap.get("4")));//交易金额
            }
            bean.setCreateTime(new Date());
            bean.setPrintCount(0);
            bean.setPrintStatus(Constants.DYZT_0);
            // 保存交易记录
            status = financeService.insertFinanceTradeInfo4Cssp(bean);
            if (status > 0) {
                // 判断是否手机充值
                if ((Constants.BUS_TYPE_MOBILE).equals(bean.getBusType())&&bean.getAuxiliaryType()==0) {
                    status = 0;
                    bean.setAmount(Long.parseLong(valueMap.get("4")));//交易金额
                    PhoneChargeVO vo = new PhoneChargeVO();
                    vo.setTradeId(bean.getTradeId());
                    vo.setSbkh("");
                    vo.setSfzh("");
                    vo.setXm("");
                    vo.setPhone(checkPhone(valueMap.get("48")));
                    vo.setCreateTime(new Date());
                    status = financeService.insertPhoneCharge4Cssp(vo);
                }
            }else {
                logger.error("新增金融交易信息失败:报文" + valueMap);
            }
        }catch (Exception e){
            logger.error("新增金融交易信息失败:编码"+codeType+",原始报文" + hexStr,e);
            status = -2;
        }
        return status;
    }

    /**
     * 接收到银行的返回报文 更新交易记录
     */
    public int updateFinanceTradeLog(FinanceTradeBean bean, HandlerResult bkResult,String statusCode) {
        int status = 0;
        try {
            if(StringUtils.isNotBlank(statusCode)) {
                if(!Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)){
                    bean.setTransResult(3);
                }else {
                    // 1 在数据库中生成 交易 信息，以便对账使用
                    bean.setSeqBank(bkResult.valueMap.get("11"));//银行交易流水号
                    bean.setTimeBk(new Date());//银行处理时间
                    if (StringUtils.isNotEmpty(bkResult.valueMap.get("13")) && StringUtils.isNotEmpty(bkResult.valueMap.get("12"))) {
                        bean.setTimeBk(CommUtil.parseDate(CommUtil.getNowDateLongStr("yyyy") + bkResult.valueMap.get("13") + bkResult.valueMap.get("12"), "yyyyMMddHHmmss"));//银行处理时间
                        bean.setPayDate(CommUtil.getNowDateLongStr("yyyy") + bkResult.valueMap.get("13"));
                        bean.setPayTime(bkResult.valueMap.get("12"));
                    }
                    if (StringUtils.isNotEmpty(bkResult.valueMap.get("39"))) {
                        //如果是冲正 0430 0410
                        if ("0430".equals(bkResult.valueMap.get("msgtype"))||"0410".equals(bkResult.valueMap.get("msgtype"))) {
                            bean.setTransResult(4);
                            bean.setCorrectReason(bkResult.valueMap.get("39"));
                        } else {
                            bean.setTransResult(bkResult.valueMap.get("39").equals("00") ? 1 : 2);
                        }
                    } else {
                        bean.setTransResult(3);
                    }
                    bean.setUpdateTime(new Date());
                    bean.setBankRtTime(new Date());
                }
                status=financeService.updateFinanceTradeInfo4Cssp(bean);
            }
        } catch (Exception e) {
            logger.error("更新交易记录失败:原始报文" + bkResult.bodyHexStr, e);
            status = -2;
        }
        return status;
    }

    public String checkPhone(String phone) throws  Exception{
        if(StringUtils.isNotEmpty(phone) && phone.length()>20){
            return phone.substring(20,31);
        }
        return "";
    }



}
