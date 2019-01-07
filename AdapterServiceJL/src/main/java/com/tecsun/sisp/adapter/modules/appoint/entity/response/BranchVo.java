package com.tecsun.sisp.adapter.modules.appoint.entity.response;

/**
 * Created by xumaohao on 2017/10/25.
 */
public class BranchVo {
    private String branchId;            //网点Id
    private String branchName;          //地址编码
    private String branchAddress;       //网点地址
    private String branchPhone;         //网点联系电话
    private String addressCode;         //地址编码
    private String addressDistrict;     //地址所属区域
    private String latitude;	        //纬度
    private String longitude;	        //经度
    private double distanceTemp;        //距离临时字段
    private String distance;            //距离
    private String createTime;          //创建时间
    private String updateTime;          //更新时间
    private String standBy1;            //备用字段1
    private String standBy2;            //备用字段2
    private String standBy3;            //备用字段3

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchPhone() {
        return branchPhone;
    }

    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStandBy1() {
        return standBy1;
    }

    public void setStandBy1(String standBy1) {
        this.standBy1 = standBy1;
    }

    public String getStandBy2() {
        return standBy2;
    }

    public void setStandBy2(String standBy2) {
        this.standBy2 = standBy2;
    }

    public String getStandBy3() {
        return standBy3;
    }

    public void setStandBy3(String standBy3) {
        this.standBy3 = standBy3;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getDistanceTemp() {
        return distanceTemp;
    }

    public void setDistanceTemp(double distanceTemp) {
        this.distanceTemp = distanceTemp;
    }
}
