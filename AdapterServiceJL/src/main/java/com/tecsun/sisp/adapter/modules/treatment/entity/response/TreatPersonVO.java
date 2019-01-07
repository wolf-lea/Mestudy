package com.tecsun.sisp.adapter.modules.treatment.entity.response;

/**
 * Created by danmeng on 2017/3/13.
 */
public class TreatPersonVO {
    private String sfzh;//身份证号码
    private String xm;//姓名
    private String sex;//性别
    private String nation;//民族
    private String birthday;//出生日期
    private String sbkh;//社保卡号
    private String address;//通讯地址
    private String phone;//联系电话
    private String company;//单位名称
    private long personPicId;//个人信息头像(上传图片uploadPicture返回picId)
    private long personId;//
    private long treatId;//待遇资格认证信息表id T_YTH_TREAT_INFO
    private String personPicPhoto;//个人信息头像

    public String getPersonPicPhoto() {
        return personPicPhoto;
    }

    public void setPersonPicPhoto(String personPicPhoto) {
        this.personPicPhoto = personPicPhoto;
    }

    public long getTreatId() {
        return treatId;
    }

    public void setTreatId(long treatId) {
        this.treatId = treatId;
    }

    public long getPersonPicId() {
        return personPicId;
    }

    public void setPersonPicId(long personPicId) {
        this.personPicId = personPicId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}
