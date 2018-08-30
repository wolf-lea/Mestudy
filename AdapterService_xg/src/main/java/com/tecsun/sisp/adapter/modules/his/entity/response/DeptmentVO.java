package com.tecsun.sisp.adapter.modules.his.entity.response;

/**科室
 * Created by danmeng on 2017/7/11.
 */
public class DeptmentVO {
    private String hospitalId;//医院编码
    private String hospitalName;//医院名称
    private String deptCode;//科室编码
    private String deptName;//科室名称
    private String deptDes;//科室简介
    private String parentCode;//上级科室
    private String location;//楼层位置
     private String deptPicture;//图片地址
    private String deptPictureBase64;//图片Base64
    private String orderNum;//排序数字
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

    public void setExt3(Object ext3) {
        this.ext3 = ext3;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public Object getExt3() {
        return ext3;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getDeptPictureBase64() {
        return deptPictureBase64;
    }

    public void setDeptPictureBase64(String deptPictureBase64) {
        this.deptPictureBase64 = deptPictureBase64;
    }

    public String getDeptPicture() {
        return deptPicture;
    }

    public void setDeptPicture(String deptPicture) {
        this.deptPicture = deptPicture;
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

    public String getDeptDes() {
        return deptDes;
    }

    public void setDeptDes(String deptDes) {
        this.deptDes = deptDes;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
