package com.tecsun.sisp.fakamanagement.modules.entity.result.supervise;

/**
 * Created by chenlicong on 2017/12/22.
 */
public class CardStatisticsVO {

    private double cardCount;//总卡量
    private double fakaCount;//已发卡量
    private double notfakaCount;//未发卡量

    //private String reginalcode;//城市代码
    private String bank;//银行代码
    //private String year;//年份
    private String time;//时间


    public double getCardCount() {
        return cardCount;
    }

    public void setCardCount(double cardCount) {
        this.cardCount = cardCount;
    }

    public double getFakaCount() {
        return fakaCount;
    }

    public void setFakaCount(double fakaCount) {
        this.fakaCount = fakaCount;
    }

    public double getNotfakaCount() {
        return notfakaCount;
    }

    public void setNotfakaCount(double notfakaCount) {
        this.notfakaCount = notfakaCount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
