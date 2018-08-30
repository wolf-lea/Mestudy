package com.tecsun.sisp.adapter.modules.finance.entity.request;

import java.util.Date;

/**
 * Created by danmeng on 2017/6/6.
 */
public class FinanceTradeBean {
    private long tradeId;//主键
    private int transType;//交易类型 : 1-消费；2-转账；3-取款;4-缴费；5-查询
    private String msgtype;//请求报文类型
    private int auxiliaryType;// 辅助操作：0-无（业务本身）;1001-签到;1002-银行卡账户验证;1003-账单费用查询;1004-下载主密钥;1005-冲正;1006-缴费（查询）;1009-其他透传操作
    private String busType;//业务类型:remaining-余额查询;credit-信用卡还款;mobile-手机充值;payment-缴费;sb_payment-社保缴费;sh_payment-生活缴费;zn_withdrawals-助农取款;transfer-转账;consume-消费;
    private String terminalId;//终端号
    private String merchantId;//商户号
    private String patchNo;//批次号
    private String payDate;//交易日期
    private String payTime;//交易时间
    private Long amount;//交易金额（单位：分）
    private String seqZd;//终端交易流水号
    private String seqBank;//银行交易流水号
    private Date timeZd;//终端交易时间
    private Date timeBk;//银行处理时间
    private String cardNoPay;//付款的账号
    private String cardNoRec;//收款的账号
    private int transResult;// 银行返回结果：1-成功；2-失败；3-超时;4-冲正。 当结果为 3 或 空 时，需要查看对账结果
    private String correctReason;// 冲正原因码
    private int reconciliationResult;//对账结果 : 1-交易成功；2-交易失败；
    private Date bankRtTime;//银行结果返回时间
    private Date reconciliationTime;//对账结果更新时间
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private Date printTime;//打印时间
    private int printCount;//打印次数
    private String printStatus;//打印状态
    private String payType;//业务渠道
    private String bankMsg;//报文信息
    private String 	deviceid;		//设备编码

    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }

    public int getTransType() {
        return transType;
    }

    public void setTransType(int transType) {
        this.transType = transType;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public int getAuxiliaryType() {
        return auxiliaryType;
    }

    public void setAuxiliaryType(int auxiliaryType) {
        this.auxiliaryType = auxiliaryType;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPatchNo() {
        return patchNo;
    }

    public void setPatchNo(String patchNo) {
        this.patchNo = patchNo;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getSeqZd() {
        return seqZd;
    }

    public void setSeqZd(String seqZd) {
        this.seqZd = seqZd;
    }

    public String getSeqBank() {
        return seqBank;
    }

    public void setSeqBank(String seqBank) {
        this.seqBank = seqBank;
    }

    public Date getTimeZd() {
        return timeZd;
    }

    public void setTimeZd(Date timeZd) {
        this.timeZd = timeZd;
    }

    public Date getTimeBk() {
        return timeBk;
    }

    public void setTimeBk(Date timeBk) {
        this.timeBk = timeBk;
    }

    public String getCardNoPay() {
        return cardNoPay;
    }

    public void setCardNoPay(String cardNoPay) {
        this.cardNoPay = cardNoPay;
    }

    public String getCardNoRec() {
        return cardNoRec;
    }

    public void setCardNoRec(String cardNoRec) {
        this.cardNoRec = cardNoRec;
    }

    public int getTransResult() {
        return transResult;
    }

    public void setTransResult(int transResult) {
        this.transResult = transResult;
    }

    public String getCorrectReason() {
        return correctReason;
    }

    public void setCorrectReason(String correctReason) {
        this.correctReason = correctReason;
    }

    public int getReconciliationResult() {
        return reconciliationResult;
    }

    public void setReconciliationResult(int reconciliationResult) {
        this.reconciliationResult = reconciliationResult;
    }

    public Date getBankRtTime() {
        return bankRtTime;
    }

    public void setBankRtTime(Date bankRtTime) {
        this.bankRtTime = bankRtTime;
    }

    public Date getReconciliationTime() {
        return reconciliationTime;
    }

    public void setReconciliationTime(Date reconciliationTime) {
        this.reconciliationTime = reconciliationTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    public int getPrintCount() {
        return printCount;
    }

    public void setPrintCount(int printCount) {
        this.printCount = printCount;
    }

    public String getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(String printStatus) {
        this.printStatus = printStatus;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getBankMsg() {
        return bankMsg;
    }

    public void setBankMsg(String bankMsg) {
        this.bankMsg = bankMsg;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
}
