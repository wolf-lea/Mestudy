package com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard;

/**
 * Created by chenlicong on 2018/3/23.
 */
public class FakaFailParamBean {

    private String cardid;//社保卡号
    private String AZ01LID;//用卡（卡信息表）
    private String AZ01ID;//预制卡、临时卡
    private String AZ02ID;//业务经办

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
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
