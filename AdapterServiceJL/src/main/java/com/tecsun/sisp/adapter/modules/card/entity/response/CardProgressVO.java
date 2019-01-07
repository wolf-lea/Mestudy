package com.tecsun.sisp.adapter.modules.card.entity.response;

/**制卡进度查询出参
 * Created by danmeng on 2016/8/9.
 */
public class CardProgressVO {
    private String sbkh;//社保卡号
    private String sfzh;//身份证号码
    private String xm;//姓名
    private String applytime; //申请时间
    private String banktime; //导出预开户时间
    private String insuretime; //导出卡商制卡时间
    private String citytime; //市中心接受时间
    private String gettime; //领卡时间
    private String status ; //卡状态

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

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    public String getBanktime() {
        return banktime;
    }

    public void setBanktime(String banktime) {
        this.banktime = banktime;
    }

    public String getInsuretime() {
        return insuretime;
    }

    public void setInsuretime(String insuretime) {
        this.insuretime = insuretime;
    }

    public String getCitytime() {
        return citytime;
    }

    public void setCitytime(String citytime) {
        this.citytime = citytime;
    }

    public String getGettime() {
        return gettime;
    }

    public void setGettime(String gettime) {
        this.gettime = gettime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
