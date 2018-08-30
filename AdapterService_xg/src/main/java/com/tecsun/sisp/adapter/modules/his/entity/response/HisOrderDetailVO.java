package com.tecsun.sisp.adapter.modules.his.entity.response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**订单信息
 * Created by danmeng on 2017/7/14.
 */
public class HisOrderDetailVO {
    private String hisbusId;
    private String hospitalId;//医院代码
    private String hospitalName;//医院名称
    private String deptCode;//科室编码
    private String deptName;//科室名称
    private String doctorNo;//医生编号
    private String doctorName;//医生姓名
    private String doctorTitle;//医生职称
    private String clinicLabel;//号别
    private String clinicDate;//就诊时间
    private String timeInterval;//时间段
    private double registrationFee;//挂号费
    private double clinicFee;//诊疗费
    private double amountFee;//总费用
    private String patientIdcard;//证件号码
    private String patientName;//病人姓名
    private String orderId;//订单号
    private String orderStatus;//订单状态
    private String createTime;//预约时间

    public String getHisbusId() {
        return hisbusId;
    }

    public void setHisbusId(String hisbusId) {
        this.hisbusId = hisbusId;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getClinicLabel() {
        return clinicLabel;
    }

    public void setClinicLabel(String clinicLabel) {
        this.clinicLabel = clinicLabel;
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

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public double getClinicFee() {
        return clinicFee;
    }

    public void setClinicFee(double clinicFee) {
        this.clinicFee = clinicFee;
    }

    public double getAmountFee() {
        return amountFee;
    }

    public void setAmountFee(double amountFee) {
        this.amountFee = amountFee;
    }

    public String getPatientIdcard() {
        return patientIdcard;
    }

    public void setPatientIdcard(String patientIdcard) {
        this.patientIdcard = patientIdcard;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreateTime() throws Exception{
        return createTime == null ? createTime : changeTime(createTime);
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public String changeTime(String value) throws Exception {
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");

        String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date.parse(value));
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        String str = value.substring(11,13);
        if (Integer.parseInt(str) >= 12) {
            value = value.substring(0,10) + " " + weeks[week_index] + " " + "下午";
        } else {
            value = value.substring(0,10) + " " + weeks[week_index] + " " + "上午";
        }

        return value;
    }
}
