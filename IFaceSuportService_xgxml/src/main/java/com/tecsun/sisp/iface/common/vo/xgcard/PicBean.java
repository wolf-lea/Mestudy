package com.tecsun.sisp.iface.common.vo.xgcard;

/**
 * Created by danmeng on 2017/3/14.
 */
public class PicBean {
    private long picId; // 当图片id
    private String picBase64; // 图片base64编码
    private String picPath; // 图片路径
    private String 	channelcode;	//渠道编码
    private String 	deviceid;		//设备编码
    private String 	tokenid;		//权限验证码
    private String picType;//图片类型

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
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

    public long getPicId() {
        return picId;
    }

    public void setPicId(long picId) {
        this.picId = picId;
    }

    public String getPicBase64() {
        return picBase64;
    }

    public void setPicBase64(String picBase64) {
        this.picBase64 = picBase64;
    }
}
