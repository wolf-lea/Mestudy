package com.tecsun.sisp.adapter.modules.his.entity.response;

/**
 * Created by danmeng on 2017/7/17.
 */
public class HisOrderVO {

    private long hisbusId;
    private String orderId;//订单号

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
}
