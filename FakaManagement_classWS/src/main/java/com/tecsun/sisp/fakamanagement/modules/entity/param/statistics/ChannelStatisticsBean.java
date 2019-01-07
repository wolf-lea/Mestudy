package com.tecsun.sisp.fakamanagement.modules.entity.param.statistics;

import java.util.List;

/**
 * Created by chenlicong on 17-12-26.
 */
public class ChannelStatisticsBean {

    private String channelcode;//渠道编码
    private String count;//总记录数
    private String year;//年份

    //查询条件
    private String startTime;
    private String endTime;

    public ChannelStatisticsBean() {
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
