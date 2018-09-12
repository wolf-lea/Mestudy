package com.tecsun.sisp.fakamanagement.modules.entity.param.supervise;

import oracle.sql.DATE;

/**
 * Created by chenlicong on 2018/3/15.
 */
public class DayMontCradCountBean {

    private String time;//当前时间 yyyy-mm-dd
    private String reginalcode;//区域编码

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReginalcode() {
        return reginalcode;
    }

    public void setReginalcode(String reginalcode) {
        this.reginalcode = reginalcode;
    }
}
