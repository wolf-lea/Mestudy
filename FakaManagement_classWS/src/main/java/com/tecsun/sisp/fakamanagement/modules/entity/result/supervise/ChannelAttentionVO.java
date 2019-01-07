package com.tecsun.sisp.fakamanagement.modules.entity.result.supervise;

/**
 * Created by chenlicong on 2018/3/22.
 */
public class ChannelAttentionVO {

    private String channelType;//渠道类型
    private String channelName;//渠道名称
    private String count;//关注量

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
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
}
