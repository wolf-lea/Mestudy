package com.tecsun.sisp.adapter.modules.card.entity.response;

/**
 * Created by danmeng on 2017/5/18.
 */
public class IssueCardVO {
    private String sbkh;//社保卡号
    private String agentSfzh;//代办人身份证
    private String isPerson;//是否本人  0：本人，1：代他人办理
    private String agentXm;//代办姓名
    private String 	sfzh; //待领卡人身份证号
    private String 	xm; //待领卡人
    private String issueAddress; //领卡地址

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getAgentSfzh() {
        return agentSfzh;
    }

    public void setAgentSfzh(String agentSfzh) {
        this.agentSfzh = agentSfzh;
    }

    public String getIsPerson() {
        return isPerson;
    }

    public void setIsPerson(String isPerson) {
        this.isPerson = isPerson;
    }

    public String getAgentXm() {
        return agentXm;
    }

    public void setAgentXm(String agentXm) {
        this.agentXm = agentXm;
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

    public String getIssueAddress() {
        return issueAddress;
    }

    public void setIssueAddress(String issueAddress) {
        this.issueAddress = issueAddress;
    }
}
