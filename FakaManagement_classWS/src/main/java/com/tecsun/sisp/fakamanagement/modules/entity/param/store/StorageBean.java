package com.tecsun.sisp.fakamanagement.modules.entity.param.store;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

import java.util.List;

/**
 * 入库管理入参
 * Created by xumaohao on 2018/1/24.
 */
public class StorageBean extends BaseVO {
    private String id;          //id
    private String userid;      //用户id
    private String fkwd;        //发卡网点
    private String bank;        //银行编码
    private List<String> qy;    //区域编码
    private String ccaid;       //柜子id
    private String batchNo;     //批次号
    private String batchId;     //批次号ID
    private String bin;         //箱号
    private String box;         //盒号
    private String method;      //入库方式 00：按盒入库 01：按箱入库 02：按批次入库 / 帅选方式 00：未入库 01：已入库
    private String status;      //入库状态

    private String idcard;      //身份证号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFkwd() {
        return fkwd;
    }

    public void setFkwd(String fkwd) {
        this.fkwd = fkwd;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public List<String> getQy() {
        return qy;
    }

    public void setQy(List<String> qy) {
        this.qy = qy;
    }

    public String getCcaid() {
        return ccaid;
    }

    public void setCcaid(String ccaid) {
        this.ccaid = ccaid;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
