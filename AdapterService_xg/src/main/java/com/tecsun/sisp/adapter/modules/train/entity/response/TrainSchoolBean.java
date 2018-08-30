package com.tecsun.sisp.adapter.modules.train.entity.response;

/**
 * Created by xumaohao on 2017/8/28.
 */
public class TrainSchoolBean extends TrainMessageBean{

    private String organName;  //学校名称
    private String address;     //地点
    private String phone;       //电话
    private String synopsis;    //简介


    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

}
