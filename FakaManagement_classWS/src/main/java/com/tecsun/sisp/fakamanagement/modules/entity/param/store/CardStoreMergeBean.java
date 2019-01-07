package com.tecsun.sisp.fakamanagement.modules.entity.param.store;

/**
 * Created by chenlicong on 2018/1/29.
 */
public class CardStoreMergeBean {

    private int ccid;//柜子ID
    private String bin;//箱号
    private String box;//盒号
    private int csid;//卡序号
    private int ciid;//卡id
    private int notfakaCount;//未发卡数量
    private String type;//type=1时，表示用于合并的盒子
    private String batchNo;//批次号
    private String newZxwz;//合并后新的装箱位置

    public int getCcid() {
        return ccid;
    }

    public void setCcid(int ccid) {
        this.ccid = ccid;
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

    public int getCsid() {
        return csid;
    }

    public void setCsid(int csid) {
        this.csid = csid;
    }

    public int getCiid() {
        return ciid;
    }

    public void setCiid(int ciid) {
        this.ciid = ciid;
    }

    public int getNotfakaCount() {
        return notfakaCount;
    }

    public void setNotfakaCount(int notfakaCount) {
        this.notfakaCount = notfakaCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getNewZxwz() {
        return newZxwz;
    }

    public void setNewZxwz(String newZxwz) {
        this.newZxwz = newZxwz;
    }
}
