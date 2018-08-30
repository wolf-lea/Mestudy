package com.tecsun.sisp.adapter.modules.common.entity.request;

/**
 * Created by danmeng on 2017/7/17.
 */
public class BusConfigBean {

    private String 	channelcode;	//渠道编码
    private String 	deviceid;		//设备编码
    private String 	tokenid;		//权限验证码
    private String configCode;//所属编码

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
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

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }
}
