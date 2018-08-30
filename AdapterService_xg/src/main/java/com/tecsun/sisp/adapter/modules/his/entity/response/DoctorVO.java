package com.tecsun.sisp.adapter.modules.his.entity.response;

/**医生
 * Created by danmeng on 2017/7/11.
 */
public class DoctorVO {
    private String hospitalId;//医院编码
    private String hospitalName;//医院名称
    private String deptCode;//科室编码
    private String deptName;//科室名称
    private String doctorNo;//医生编号
    private String doctorName;//医生姓名
    private String doctorDes;//医生简介
    private String scheduleflag;//预约状态 后面几天 出诊情况的综合预约状态： 1： 有号可约 0： 无号可约（停诊\约满\未开发等）
    private String doctorTitle;//职务职称
    private String doctorPictBase64;//医生图片Base64
    private String doctorPicture;//医生图片
    private String orderNum;//排序数字
    private String clinicFee;//诊疗费

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getDoctorPicture() {
        return doctorPicture;
    }

    public void setDoctorPicture(String doctorPicture) {
        this.doctorPicture = doctorPicture;
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

    public String getDoctorDes() {
        return doctorDes;
    }

    public void setDoctorDes(String doctorDes) {
        this.doctorDes = doctorDes;
    }

    public String getScheduleflag() {
        return scheduleflag;
    }

    public void setScheduleflag(String scheduleflag) {
        this.scheduleflag = scheduleflag;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public String getDoctorPictBase64() {
        return doctorPictBase64;
    }

    public void setDoctorPictBase64(String doctorPictBase64) {
        this.doctorPictBase64 = doctorPictBase64;
    }

    public String getClinicFee() {
        return clinicFee = clinicFee == null ? "" : clinicFee + "元";
    }

    public void setClinicFee(String clinicFee) {
        this.clinicFee = clinicFee;
    }
}
