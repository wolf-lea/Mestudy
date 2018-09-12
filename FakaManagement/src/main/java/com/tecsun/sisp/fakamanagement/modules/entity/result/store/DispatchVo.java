package com.tecsun.sisp.fakamanagement.modules.entity.result.store;

/**
 * 发行服务-分拨出参
 * Created by xumaohao on 2018/1/23.
 */
public class DispatchVo {
    private String id;          //批次主键id
    private String batchNo;     //批次号
    private String bank;        //所属银行（编码）
    private String bankName;    //所属银行（中文）
    private String batchSum;    //批次数量（卡数量）
    private String fbStatus;    //分拨状态（编码）
    private String fbStatusName;//分拨状态（中文）
    private String updateTime;  //更新时间 仅当分拨状态为01（或已分拨）时，此时间代表分拨时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBatchSum() {
        return batchSum;
    }

    public void setBatchSum(String batchSum) {
        this.batchSum = batchSum;
    }

    public String getFbStatus() {
        return fbStatus;
    }

    public void setFbStatus(String fbStatus) {
        this.fbStatus = fbStatus;
    }

    public String getFbStatusName() {
        return fbStatusName;
    }

    public void setFbStatusName(String fbStatusName) {
        this.fbStatusName = fbStatusName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
