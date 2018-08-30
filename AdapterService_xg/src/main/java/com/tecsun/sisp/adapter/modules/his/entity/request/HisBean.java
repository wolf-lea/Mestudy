package com.tecsun.sisp.adapter.modules.his.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by danmeng on 2017/7/11.
 */
public class HisBean extends SecQueryBean {
    private String hospitalId;//医院编码
    private String hospitalName;//医院名称
    private String deptCode;//科室编码
    private String doctorNo;//医生编号
    private String clinicDate;//预约日期(不填则默认今天起七日内)
    private String location;//当前位置
    private String hospitalArea;//医院所属区域


    public String getClinicDate() {
        return clinicDate;
    }

    public void setClinicDate(String clinicDate) {
        this.clinicDate = clinicDate;
    }

    public String getDoctorNo() {
        return doctorNo;
    }

    public void setDoctorNo(String doctorNo) {
        this.doctorNo = doctorNo;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHospitalArea() {
        return hospitalArea;
    }

    public void setHospitalArea(String hospitalArea) {
        this.hospitalArea = hospitalArea;
    }
}
