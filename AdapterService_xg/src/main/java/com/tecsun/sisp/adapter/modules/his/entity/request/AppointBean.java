package com.tecsun.sisp.adapter.modules.his.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**挂号
 * Created by danmeng on 2017/7/13.
 */
public class AppointBean extends SecQueryBean{
    private long hisbusId;
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
    private String cardType;//证件类型 1身份证，2社保卡
    private String patientIdcard;//证件号码
    private String patientName;//病人姓名
    private String patientSex;//病人性别 男 女
    private String patientBirthday;//出生日期 yyyy-mm-dd
    private String patientPhone;//联系电话
    private String orderId;//订单号
    private String orderStatus;//订单状态
    private String orderType;//订单类型：1-预约；2-挂号
    private Object ext1;//备用1
    private Object ext2;//备用2
    private Object ext3;//备用3

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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public double getAmountFee() {
        return amountFee;
    }

    public void setAmountFee(double amountFee) {
        this.amountFee = amountFee;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getHisbusId() {
        return hisbusId;
    }

    public void setHisbusId(long hisbusId) {
        this.hisbusId = hisbusId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(String patientBirthday) {
        this.patientBirthday = patientBirthday;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }
}
