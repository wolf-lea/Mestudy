package com.tecsun.sisp.adapter.modules.appoint.entity.response;

import java.util.List;

/**
 * Created by xumaohao on 2017/10/30.
 */
public class TimeVo {
    private String appointTime;          //预约时间
    private String serviceCode;          //预约业务编码
    private List<Detail> details;        //预约时间详细

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

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public static class Detail{
        private String timeInterval;         //预约时间段
        private long appointAmount;          //预约量
        private long appointAmountLimit;     //预约限量
        private String appointStatus;        //预约状态（0可预约  1已满）


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
}
