package com.tecsun.sisp.iface.common.vo.employment.param;

/**
 * Created by pear on 2015/8/5.
 */
public class queryParamBean {
    private String deviceid;//设备编码

    private String channelcode;// 接口类型型(如：大众端、网办、德生宝)

    private String channelType;//接口类型(如：大众端-02、德生宝-01)

    private String acc0m1;  //就业失业登记证编号

    private String aac002;  //身份证号码

    public String getAcc0m1() {
        return acc0m1;
    }

    public void setAcc0m1(String acc0m1) {
        this.acc0m1 = acc0m1;
    }

    public String getAac002() {
        return aac002;
    }

    public void setAac002(String aac002) {
        this.aac002 = aac002;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }
}
