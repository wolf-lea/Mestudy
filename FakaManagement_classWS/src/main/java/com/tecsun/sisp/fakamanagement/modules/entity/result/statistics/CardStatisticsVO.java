package com.tecsun.sisp.fakamanagement.modules.entity.result.statistics;

/**
 * Created by chenlicong on 2017/12/22.
 */
public class CardStatisticsVO {

    private String cardCount;//总卡量
    private String fakaCount;//已发卡量
    private String notfakaCount;//未发卡量

    private String reginalcode;//城市代码
    private String bank;//银行代码
    private String year;//年份

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

    public String getNotfakaCount() {
        return notfakaCount;
    }

    public void setNotfakaCount(String notfakaCount) {
        this.notfakaCount = notfakaCount;
    }

    public String getReginalcode() {
        return reginalcode;
    }

    public void setReginalcode(String reginalcode) {
        this.reginalcode = reginalcode;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
