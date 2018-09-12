package com.tecsun.sisp.fakamanagement.modules.entity.param.supervise;

/**
 * Created by chenlicong on 2017/12/26.
 */
public class CardStatisticsBean {

    private String reginalcode;//城市代码
    private String bank;//银行代码
    private String startTime;
    private String endTime;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
