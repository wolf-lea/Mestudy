package com.tecsun.sisp.fakamanagement.modules.entity.param.statistics;

/**
 * Created by chenlicong on 2017/12/27.
 */
public class SettleStatisticsBean {

    private String count;//总量
    private String time;//时间
    private String district;//区县

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
