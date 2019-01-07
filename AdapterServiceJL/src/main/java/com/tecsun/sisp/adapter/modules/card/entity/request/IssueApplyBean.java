package com.tecsun.sisp.adapter.modules.card.entity.request;

import java.util.Date;

/**精准发卡申领
 * Created by danmeng on 2017/5/15.
 */
public class IssueApplyBean {
    private String agentName; //代领人姓名
    private String name;//本人姓名
    private String certNum;//本人身份证号码
    private String agentcertNum;//代办人身份证号码
    private String rphotoPath;//领卡人照片
    private  String qmphotoPath;//领卡人签名照片
    private String soCardNum;//社保卡号
    private  String lkwd;//领卡网点地址
    private  String soname;//网点名字
    private Date applytime;//领卡时间

    public String getLkwd() {
        return lkwd;
    }

    public void setLkwd(String lkwd) {
        this.lkwd = lkwd;
    }

    public String getRphotoPath() {
        return rphotoPath;
    }

    public void setRphotoPath(String rphotoPath) {
        this.rphotoPath = rphotoPath;
    }

    public String getQmphotoPath() {
        return qmphotoPath;
    }

    public void setQmphotoPath(String qmphotoPath) {
        this.qmphotoPath = qmphotoPath;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getAgentcertNum() {
        return agentcertNum;
    }

    public void setAgentcertNum(String agentcertNum) {
        this.agentcertNum = agentcertNum;
    }

    public String getSoCardNum() {
        return soCardNum;
    }

    public void setSoCardNum(String soCardNum) {
        this.soCardNum = soCardNum;
    }

    public String getSoname() {
        return soname;
    }

    public void setSoname(String soname) {
        this.soname = soname;
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }
}
