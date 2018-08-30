package com.tecsun.sisp.adapter.modules.his.entity.response;


/**
 * Created by danmeng on 2017/7/11.
 */
public class HisDetailVO extends AllHisInfoVO {
     private String hospitalLevel;//医院等级
     private String hospitalDes;//医院简介
    private String appointment;//预约时间

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public String getHospitalDes() {
        return hospitalDes;
    }

    public void setHospitalDes(String hospitalDes) {
        this.hospitalDes = hospitalDes;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }
}
