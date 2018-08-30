package com.tecsun.sisp.adapter.modules.his.entity.response;

/**所有医院信息
 * Created by danmeng on 2017/7/11.
 */
public class AllHisInfoVO {
    private String hospitalId;//医院代码
    private String hospitalName;//医院名称
    private String hospitalAddr;//医院地址
    private String hospitalPhone;//联系电话
    private String hospitalPicture;//医院图片地址
    private String hospitalPictureBase64;//医院图片Base64
    private String orderNum;//排序
    private String hospitalLevel;//医院等级
    private String distance;//距离

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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

    public String getHospitalAddr() {
        return hospitalAddr;
    }

    public void setHospitalAddr(String hospitalAddr) {
        this.hospitalAddr = hospitalAddr;
    }

    public String getHospitalPhone() {
        return hospitalPhone;
    }

    public void setHospitalPhone(String hospitalPhone) {
        this.hospitalPhone = hospitalPhone;
    }

    public String getHospitalPicture() {
        return hospitalPicture;
    }

    public void setHospitalPicture(String hospitalPicture) {
        this.hospitalPicture = hospitalPicture;
    }

    public String getHospitalPictureBase64() {
        return hospitalPictureBase64;
    }

    public void setHospitalPictureBase64(String hospitalPictureBase64) {
        this.hospitalPictureBase64 = hospitalPictureBase64;
    }

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
