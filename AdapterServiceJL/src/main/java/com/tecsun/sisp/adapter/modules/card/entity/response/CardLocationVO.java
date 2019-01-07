package com.tecsun.sisp.adapter.modules.card.entity.response;

/**
 * Created by danmeng on 2017/5/12.
 */
public class CardLocationVO {
    private long binId;
    private  long boxId;
    private String sfzh;
    private String xm;
    private  Integer cardsn;//卡片位置（张）
    private String boxNo;//盒号
    private  String binNo;//箱号
    private  String orgAddress;//网点名称
    private String batchno;//批次号
    private String cardLocation;//卡具体位置
    private String cardStatus;//卡状态
    private String isGetCard;//是否可领卡
    private String regisStatus;//1， 待发放 0已发

    public String getRegisStatus() {
        return regisStatus;
    }

    public void setRegisStatus(String regisStatus) {
        this.regisStatus = regisStatus;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getIsGetCard() {
        return isGetCard;
    }

    public void setIsGetCard(String isGetCard) {
        this.isGetCard = isGetCard;
    }

    public String getCardLocation() {
        return cardLocation;
    }

    public void setCardLocation(String cardLocation) {
        this.cardLocation = cardLocation;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public long getBinId() {
        return binId;
    }

    public void setBinId(long binId) {
        this.binId = binId;
    }

    public long getBoxId() {
        return boxId;
    }

    public void setBoxId(long boxId) {
        this.boxId = boxId;
    }

    public Integer getCardsn() {
        return cardsn;
    }

    public void setCardsn(Integer cardsn) {
        this.cardsn = cardsn;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getBinNo() {
        return binNo;
    }

    public void setBinNo(String binNo) {
        this.binNo = binNo;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
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
}
