package com.tecsun.sisp.adapter.modules.appoint.entity.response;

/**
 * Created by xumaohao on 2017/10/25.
 */
public class TimeTempVo {
    private String serviceCode;          //预约业务编码
    private String appointTime;          //预约时间
    private String timeInterval;         //预约时间段
    private long appointAmount;          //预约量
    private long appointAmountLimit;     //预约限量
    private String appointStatus;        //预约状态（0可预约  1已满）

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public long getAppointAmount() {
        return appointAmount;
    }

    public void setAppointAmount(long appointAmount) {
        this.appointAmount = appointAmount;
    }

    public long getAppointAmountLimit() {
        return appointAmountLimit;
    }

    public void setAppointAmountLimit(long appointAmountLimit) {
        this.appointAmountLimit = appointAmountLimit;
    }

    public String getAppointStatus() {
        return appointStatus;
    }

    public void setAppointStatus(String appointStatus) {
        this.appointStatus = appointStatus;
    }
}
