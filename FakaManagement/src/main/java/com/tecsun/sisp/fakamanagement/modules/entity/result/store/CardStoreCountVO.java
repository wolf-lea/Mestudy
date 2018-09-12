package com.tecsun.sisp.fakamanagement.modules.entity.result.store;

/**
 * Created by chenlicong on 2018/1/23.
 */
public class CardStoreCountVO {

    private int ccid;//卡柜ID
    private String ccName;//柜子名称
    private int boxCount;//盒数
    private int cardCount;//卡总量
    private int fakaCount;//发卡数量
    private int notfakaCount;//未发卡数量

    public int getCcid() {
        return ccid;
    }

    public void setCcid(int ccid) {
        this.ccid = ccid;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public int getBoxCount() {
        return boxCount;
    }

    public void setBoxCount(int boxCount) {
        this.boxCount = boxCount;
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
