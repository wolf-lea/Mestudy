package com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard;

/**
 * Created by chenlicong on 2018/3/22.
 */
public class TemporaryFakaVO {

    private String cardNo;//临时卡编号
    private String idCard;//社会保障号码
    private String cardId;//社保卡号
    private String fkrq;//发卡日期
    private String kyxq;//有效期至
    private String AZ01LID;//AZ01LID 用卡（卡信息表）
    private String AZ01ID;//AZ01ID 预制卡、临时卡
    private String AZ02ID;//AZ02ID 业务经办

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getFkrq() {
        return fkrq;
    }

    public void setFkrq(String fkrq) {
        this.fkrq = fkrq;
    }

    public String getKyxq() {
        return kyxq;
    }

    public void setKyxq(String kyxq) {
        this.kyxq = kyxq;
    }

    public String getAZ01LID() {
        return AZ01LID;
    }

    public void setAZ01LID(String AZ01LID) {
        this.AZ01LID = AZ01LID;
    }

    public String getAZ01ID() {
        return AZ01ID;
    }

    public void setAZ01ID(String AZ01ID) {
        this.AZ01ID = AZ01ID;
    }

    public String getAZ02ID() {
        return AZ02ID;
    }

    public void setAZ02ID(String AZ02ID) {
        this.AZ02ID = AZ02ID;
    }
}
