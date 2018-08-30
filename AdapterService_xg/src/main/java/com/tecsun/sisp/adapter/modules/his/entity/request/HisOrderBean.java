package com.tecsun.sisp.adapter.modules.his.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**订单信息
 * Created by danmeng on 2017/7/17.
 */
public class HisOrderBean extends SecQueryBean{
    private String hisbusId;
    private String hospitalId;//医院代码
    private String deptCode;//科室编码
    private String doctorNo;//医生编号
    private String cardType;//证件类型 1身份证，2社保卡
    private String patientIdcard;//证件号码
    private String patientName;//病人姓名
    private String clinicLabel;//号别
    private String orderId;//订单号
    private String orderStatus;//订单状态
    private String orderType;//订单类型：1-预约；2-挂号
    private String startDate;//就诊时间开始
    private String endDate;//就诊时间结束
    private String orderStatusArray;//订单状态集合

    public String getOrderStatusArray() {
        return orderStatusArray;
    }

    public void setOrderStatusArray(String orderStatusArray) {
        this.orderStatusArray = orderStatusArray;
    }

    public String getClinicLabel() {
        return clinicLabel;
    }

    public void setClinicLabel(String clinicLabel) {
        this.clinicLabel = clinicLabel;
    }

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

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDoctorNo() {
        return doctorNo;
    }

    public void setDoctorNo(String doctorNo) {
        this.doctorNo = doctorNo;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
