package com.tecsun.sisp.fakamanagement.modules.entity.result.replacement;

/**
 * Created by chenlicong on 2018/10/24.
 */
public class ReplaceStatisticsVO {

    private String cardCount;//入库数量
    private String fakaCount;//发卡数量
    private String surplusCount;//剩余数量
    private String badCount;//坏卡数量
    private String name;//银行网点名称

    public String getCardCount() {
        return cardCount;
    }

    public void setCardCount(String cardCount) {
        this.cardCount = cardCount;
    }

    public String getFakaCount() {
        return fakaCount;
    }

    public void setFakaCount(String fakaCount) {
        this.fakaCount = fakaCount;
    }

    public String getSurplusCount() {
        return surplusCount;
    }

    public void setSurplusCount(String surplusCount) {
        this.surplusCount = surplusCount;
    }

    public String getBadCount() {
        return badCount;
    }

    public void setBadCount(String badCount) {
        this.badCount = badCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
