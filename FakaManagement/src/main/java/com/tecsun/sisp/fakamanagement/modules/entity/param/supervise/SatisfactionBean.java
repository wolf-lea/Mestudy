package com.tecsun.sisp.fakamanagement.modules.entity.param.supervise;

/**
 * Created by chenlicong on 2018/3/20.
 */
public class SatisfactionBean {

    private String reginalcode;//城市代码

    private String count;//总记录数
    private String bank;//银行
    private String satisfaction;//满意类型

    public String getReginalcode() {
        return reginalcode;
    }

    public void setReginalcode(String reginalcode) {
        this.reginalcode = reginalcode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }
}
