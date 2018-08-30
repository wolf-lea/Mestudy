package com.tecsun.sisp.adapter.modules.his.entity.request;

/**取消单号信息
 * Created by danmeng on 2017/7/14.
 */
public class CancelOrderBean {
    private long hiscancelId;
    private long hisbusId;
    private String orderId;//订单号
    private String cancelTime;//取消时间
    private String cancelStatus;//状态
    private String failReason;//失败原因
    private String channelcode;//渠道

    public long getHisbusId() {
        return hisbusId;
    }

    public long getHiscancelId() {
        return hiscancelId;
    }

    public void setHiscancelId(long hiscancelId) {
        this.hiscancelId = hiscancelId;
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

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
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
