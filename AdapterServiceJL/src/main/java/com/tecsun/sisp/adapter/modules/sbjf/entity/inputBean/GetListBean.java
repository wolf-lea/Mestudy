package com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by linzetian on 2017/6/12.
 * 获取缴费清单入参bean
 */
public class GetListBean extends SecQueryBean {
    private String channelcode;//渠道编码
    private String deviceid;//标识Id（标识Id,传设备编码或用户ID）
    private String tokenid;//tokenId
    private String xm;//姓名
    private String sfzh;//身份证号
    private String payNum;//缴费单号
    private String insureCode;//险种编码
    private String version;//版本号

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

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getInsureCode() {
        return insureCode;
    }

    public void setInsureCode(String insureCode) {
        this.insureCode = insureCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
