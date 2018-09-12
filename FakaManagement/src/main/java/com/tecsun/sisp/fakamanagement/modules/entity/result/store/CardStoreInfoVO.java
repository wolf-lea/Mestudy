package com.tecsun.sisp.fakamanagement.modules.entity.result.store;

/**
 * Created by chenlicong on 2018/1/24.
 */
public class CardStoreInfoVO {

    private String ccName;//柜子名称
    private int ccid;//柜子ID
    private String batchNo;//批次
    private String bin;//箱号
    private String box;//盒号
    private int cardCount;//卡总量
    private int fakaCount;//发卡数量
    private int notfakaCount;//未发卡数量

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public int getCcid() {
        return ccid;
    }

    public void setCcid(int ccid) {
        this.ccid = ccid;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public int getCardCount() {
        return cardCount;
    }

    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    public int getFakaCount() {
        return fakaCount;
    }

    public void setFakaCount(int fakaCount) {
        this.fakaCount = fakaCount;
    }

    public int getNotfakaCount() {
        return notfakaCount;
    }

    public void setNotfakaCount(int notfakaCount) {
        this.notfakaCount = notfakaCount;
    }
}
