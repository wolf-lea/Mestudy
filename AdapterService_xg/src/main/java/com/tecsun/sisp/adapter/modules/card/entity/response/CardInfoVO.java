package com.tecsun.sisp.adapter.modules.card.entity.response;

/**
 * Created by danmeng on 2016/10/18.
 */
public class CardInfoVO {
    private String sbkh;//社保卡号
    private String sfzh;//身份证号码
    private String xm;//姓名
    private String status ; //状态

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
