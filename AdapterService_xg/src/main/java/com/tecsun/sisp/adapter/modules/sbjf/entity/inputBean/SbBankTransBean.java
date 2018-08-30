package com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean;

/**
 * Created by linzetian on 2017/6/19.
 * 社保缴费银行报文请求bean
 */
public class SbBankTransBean {
    private String channelcode;//渠道编码
    private String deviceid;//设备Id
    private String tokenid;//tokenId
    private String adaptertokenId;
    private String hexStr;//报文
    private int auxiliaryType;//0-无（业务本身）
    private String financialType;//sb_payment-社保缴费：
    private  int codeType;//编码类型64-64域、128-128域
    private String flowNum;//交易流水号

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getAdaptertokenId() {
        return adaptertokenId;
    }

    public void setAdaptertokenId(String adaptertokenId) {
        this.adaptertokenId = adaptertokenId;
    }

    public String getHexStr() {
        return hexStr;
    }

    public void setHexStr(String hexStr) {
        this.hexStr = hexStr;
    }

    public int getAuxiliaryType() {
        return auxiliaryType;
    }

    public void setAuxiliaryType(int auxiliaryType) {
        this.auxiliaryType = auxiliaryType;
    }

    public String getFinancialType() {
        return financialType;
    }

    public void setFinancialType(String financialType) {
        this.financialType = financialType;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }

    public String getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(String flowNum) {
        this.flowNum = flowNum;
    }
}
