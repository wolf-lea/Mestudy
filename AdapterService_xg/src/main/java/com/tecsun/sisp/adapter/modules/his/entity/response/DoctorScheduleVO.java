package com.tecsun.sisp.adapter.modules.his.entity.response;

/**排班
 * Created by danmeng on 2017/7/11.
 */
public class DoctorScheduleVO {
    private String hospitalId;//医院编码
    private String deptCode;//科室编码
    private String deptName;//科室名称
    private String doctorNo;//医生编号
    private String clinicDate;//就诊时间
    private String timeInterval;//时间段
    private String registrationFee;//挂号费
    private String clinicFee;//诊疗费
    private String clinicLabel;//号别
    private String clinicLabelFlag;//状态 1-可挂 2-已满
    private long registrationlimits;//限号数
    private long appointAmount;//已挂号数
    private long remainAmount;//剩余号数
    private Object ext1;//备用1
    private Object ext2;//备用2
    private Object ext3;//备用3

    public long getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(long remainAmount) {
        this.remainAmount = remainAmount;
    }


    public long getRegistrationlimits() {
        return registrationlimits;
    }

    public void setRegistrationlimits(long registrationlimits) {
        this.registrationlimits = registrationlimits;
    }

    public long getAppointAmount() {
        return appointAmount;
    }

    public void setAppointAmount(long appointAmount) {
        this.appointAmount = appointAmount;
    }

    public String getClinicLabelFlag() {
        return clinicLabelFlag;
    }

    public void setClinicLabelFlag(String clinicLabelFlag) {
        this.clinicLabelFlag = clinicLabelFlag;
    }

    public String getClinicLabel() {
        return clinicLabel;
    }

    public void setClinicLabel(String clinicLabel) {
        this.clinicLabel = clinicLabel;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDoctorNo() {
        return doctorNo;
    }

    public void setDoctorNo(String doctorNo) {
        this.doctorNo = doctorNo;
    }

    public String getClinicDate() {
        return clinicDate;
    }

    public void setClinicDate(String clinicDate) {
        this.clinicDate = clinicDate;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(String registrationFee) {
        this.registrationFee = registrationFee;
    }

    public String getClinicFee() {
        return clinicFee;
    }

    public void setClinicFee(String clinicFee) {
        this.clinicFee = clinicFee;
    }

    public Object getExt1() {
        return ext1;
    }

    public void setExt1(Object ext1) {
        this.ext1 = ext1;
    }

    public Object getExt2() {
        return ext2;
    }

    public void setExt2(Object ext2) {
        this.ext2 = ext2;
    }

    public Object getExt3() {
        return ext3;
    }

    public void setExt3(Object ext3) {
        this.ext3 = ext3;
    }
}
