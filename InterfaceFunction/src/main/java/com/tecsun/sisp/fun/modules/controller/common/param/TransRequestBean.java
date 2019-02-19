package com.tecsun.sisp.fun.modules.controller.common.param;

/**
 * Created by zhangqingjie on 15-5-14.
 */
public class TransRequestBean {
    private String transid;
    private String channelcode;
    private String deviceid;
    private String businesscode;
    private String starttime;
    private String endtime;
    private String userid;
    private String transmsg;
    private Integer status;
    private Integer result;//1成功，0失败

    public TransRequestBean() {
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid;
    }

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

    public String getBusinesscode() {
        return businesscode;
    }

    public void setBusinesscode(String businesscode) {
        this.businesscode = businesscode;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTransmsg() {
        return transmsg;
    }

    public void setTransmsg(String transmsg) {
        this.transmsg = transmsg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
