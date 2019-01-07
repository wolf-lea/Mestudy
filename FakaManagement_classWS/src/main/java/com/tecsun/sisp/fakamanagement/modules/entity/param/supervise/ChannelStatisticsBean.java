package com.tecsun.sisp.fakamanagement.modules.entity.param.supervise;

/**
 * Created by chenlicong on 17-12-26.
 */
public class ChannelStatisticsBean {

    private String channelcode;//渠道编码
    private String channelName;//渠道名称
    private String count;//总记录数
    //private String year;//年份

    //查询条件
    //private String startTime;
    //private String endTime;
    private String timeType;//时间类型： 1：日  2：月

    public ChannelStatisticsBean() {
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }
}
