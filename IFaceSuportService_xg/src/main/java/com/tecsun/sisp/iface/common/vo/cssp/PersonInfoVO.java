package com.tecsun.sisp.iface.common.vo.cssp;

/**
 * 参保人信息表
 * Created by liu on 2016/8/8.
 */
public class PersonInfoVO {
    private Long cardId;//社保卡物理ID
    private String certNum;//身份证号码
    private  Long personId;
    private String soCardNum;

    public String getSoCardNum() {
        return soCardNum;
    }

    public void setSoCardNum(String soCardNum) {
        this.soCardNum = soCardNum;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
