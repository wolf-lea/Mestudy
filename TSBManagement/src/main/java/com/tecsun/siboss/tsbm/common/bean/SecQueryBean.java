package com.tecsun.siboss.tsbm.common.bean;

/**
 * 社保卡请求对象
 * Created by jerry on 2015/5/31.
 */
public class SecQueryBean {

    private String channelcode;//渠道编码
    private String deviceid;//设备编码
    private String tokenId;//权限验证码

    public SecQueryBean() {
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

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

}
