package com.tecsun.sisp.fakamanagement.modules.entity.result.store;

/**
 * Created by xumaohao on 2018/1/24.
 */
public class StorageVo {
    private String id;          //id
    private String batchNo;     //批次号
    private String bank;        //银行
    private String qy;          //区域
    private String rkwd;        //入库网点
    private String bin;         //箱号/箱数
    private String box;         //盒号/盒数
    private String cardNum;     //卡数量
    private String updateTime;  //更新时间
    private String status;      //入库状态

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

    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }

    public String getRkwd() {
        return rkwd;
    }

    public void setRkwd(String rkwd) {
        this.rkwd = rkwd;
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

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
