package com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard;

/**
 * Created by chenlicong on 2018/3/26.
 */
public class TempStatisticsVO {

    private String cardInstoreNum;//卡入库数量
    private String cardGrantNum;//卡发放数量
    private String cardSurplusNum;//卡剩余数量
    private String recoveryNum;//回收数量
    private String notRecoveryNum;//未回收数量
    private String wasteCardNum;//废卡数量
    private String cancelNum;//注销数量
    private String parent_org_id;//父级银行ID
    private String name;//银行名称

    public String getCardInstoreNum() {
        return cardInstoreNum;
    }

    public void setCardInstoreNum(String cardInstoreNum) {
        this.cardInstoreNum = cardInstoreNum;
    }

    public String getCardGrantNum() {
        return cardGrantNum;
    }

    public void setCardGrantNum(String cardGrantNum) {
        this.cardGrantNum = cardGrantNum;
    }

    public String getCardSurplusNum() {
        return cardSurplusNum;
    }

    public void setCardSurplusNum(String cardSurplusNum) {
        this.cardSurplusNum = cardSurplusNum;
    }

    public String getRecoveryNum() {
        return recoveryNum;
    }

    public void setRecoveryNum(String recoveryNum) {
        this.recoveryNum = recoveryNum;
    }

    public String getNotRecoveryNum() {
        return notRecoveryNum;
    }

    public void setNotRecoveryNum(String notRecoveryNum) {
        this.notRecoveryNum = notRecoveryNum;
    }

    public String getWasteCardNum() {
        return wasteCardNum;
    }

    public void setWasteCardNum(String wasteCardNum) {
        this.wasteCardNum = wasteCardNum;
    }

    public String getCancelNum() {
        return cancelNum;
    }

    public void setCancelNum(String cancelNum) {
        this.cancelNum = cancelNum;
    }

    public String getParent_org_id() {
        return parent_org_id;
    }

    public void setParent_org_id(String parent_org_id) {
        this.parent_org_id = parent_org_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
