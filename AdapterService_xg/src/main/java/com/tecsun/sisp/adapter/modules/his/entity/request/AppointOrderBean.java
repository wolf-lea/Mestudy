package com.tecsun.sisp.adapter.modules.his.entity.request;

/**预约单号信息
 * Created by danmeng on 2017/7/14.
 */
public class AppointOrderBean {
    private long hisappointId;
    private long hisbusId;
    private String orderId;//订单号
    private String appointTime;//预约时间
    private String appointStatus;//预约状态
    private String failReason;//失败原因
    private String channelcode;//渠道

    public long getHisappointId() {
        return hisappointId;
    }

    public void setHisappointId(long hisappointId) {
        this.hisappointId = hisappointId;
    }

    public long getHisbusId() {
        return hisbusId;
    }

    public void setHisbusId(long hisbusId) {
        this.hisbusId = hisbusId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public String getAppointStatus() {
        return appointStatus;
    }

    public void setAppointStatus(String appointStatus) {
        this.appointStatus = appointStatus;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }
}
