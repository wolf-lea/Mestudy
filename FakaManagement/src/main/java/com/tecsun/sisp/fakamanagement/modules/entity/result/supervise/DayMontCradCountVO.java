package com.tecsun.sisp.fakamanagement.modules.entity.result.supervise;

/**
 * Created by chenlicong on 2018/3/15.
 */
public class DayMontCradCountVO {

    private int dayFakaCount;//今日发卡量
    private int monthFakaCount;//本月发卡量

    public int getDayFakaCount() {
        return dayFakaCount;
    }

    public void setDayFakaCount(int dayFakaCount) {
        this.dayFakaCount = dayFakaCount;
    }

    public int getMonthFakaCount() {
        return monthFakaCount;
    }

    public void setMonthFakaCount(int monthFakaCount) {
        this.monthFakaCount = monthFakaCount;
    }
}
