package com.tecsun.sisp.adapter.modules.treatment.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by danmeng on 2017/3/13.
 */
public class TreatPersonBean extends SecQueryBean {
    private String sex;//性别01男性;02女性;03未知
    private String nation;//民族
    private String birthday;//出生日期
    private String sbkh;//社保卡号
    private String domicile;//户籍地址
    private String address;//通讯地址
    private String phone;//联系电话
    private String company;//单位名称
    private String picStatus;//图片状态
    private String picType;//图片类型
    private long personPicId;//个人信息头像(上传图片uploadPicture返回picId)
    private long personId;//个人基本信息表ID 来自T_YTH_BASIC_PERSON_INFO
    private long treatId;//待遇资格认证信息表id T_YTH_TREAT_INFO


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

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
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

    public String getPicStatus() {
        return picStatus;
    }

    public void setPicStatus(String picStatus) {
        this.picStatus = picStatus;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
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

    public long getTreatId() {
        return treatId;
    }

    public void setTreatId(long treatId) {
        this.treatId = treatId;
    }
}
