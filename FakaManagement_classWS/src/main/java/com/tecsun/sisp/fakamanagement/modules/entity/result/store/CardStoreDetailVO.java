package com.tecsun.sisp.fakamanagement.modules.entity.result.store;

/**
 * Created by chenlicong on 2018/1/24.
 */
public class CardStoreDetailVO {

    private String name;//姓名
    private String idcard;//身份证号
    private String phone;//联系电话
    private String fkwd;//网点名称
    private String status;//状态
    private String batchNo;//批次
    private String ccName;//柜子名称
    private String bin;//箱号
    private String box;//盒号
    private String csid;//卡序号

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFkwd() {
        return fkwd;
    }

    public void setFkwd(String fkwd) {
        this.fkwd = fkwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }
}
