package com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean;

/**
 * Created by linzetian on 2017/6/19.
 * 交易反馈请求bean
 */
public class FeedbackBean {
    private String channelcode;//渠道编码
    private String deviceid;//设备Id
    private String tokenid;//tokenId
    private String tranNum;//交易后返回的单号（唯一标识）
    private String flowNum;//交易流水号
    private String message;//金融交易返回信息
    private String status;//交易状态 1：成功，2：失败
    private String payType;//支付方式

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

    public String getTranNum() {
        return tranNum;
    }

    public void setTranNum(String tranNum) {
        this.tranNum = tranNum;
    }

    public String getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(String flowNum) {
        this.flowNum = flowNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
