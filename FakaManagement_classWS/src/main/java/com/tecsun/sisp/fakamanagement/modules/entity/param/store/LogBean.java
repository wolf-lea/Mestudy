package com.tecsun.sisp.fakamanagement.modules.entity.param.store;

/**
 * Created by xumaohao on 2018/1/24.
 */
public class LogBean {
    private long id;                //主键
    private long batchId;           //批次主键
    private long userid;            //操作人
    private String busType;         //业务类型
    private String batchBinBoxId;   //批次箱盒主键

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getBatchBinBoxId() {
        return batchBinBoxId;
    }

    public void setBatchBinBoxId(String batchBinBoxId) {
        this.batchBinBoxId = batchBinBoxId;
    }
}
