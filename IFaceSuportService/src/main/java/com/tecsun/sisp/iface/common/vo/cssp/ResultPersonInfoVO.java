package com.tecsun.sisp.iface.common.vo.cssp;

/**
 * Created by liu on 2016/8/8.
 */
public class ResultPersonInfoVO {
    private Long cardId;//社保卡物理ID
    private String certNum;//身份证号码

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
}
