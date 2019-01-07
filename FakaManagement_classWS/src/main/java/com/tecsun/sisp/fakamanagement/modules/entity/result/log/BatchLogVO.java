package com.tecsun.sisp.fakamanagement.modules.entity.result.log;

import java.util.Date;

/**
 * @Description:
 * @author: liang
 * @date 2018/1/29 14:11
 **/
public class BatchLogVO {
    private Integer logId;
    private Integer batchId;
    private Integer userId;
    private String busType;
    private Integer batchBinBoxId;
    private Date createTime;
    private Date updateTime;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public Integer getBatchBinBoxId() {
        return batchBinBoxId;
    }

    public void setBatchBinBoxId(Integer batchBinBoxId) {
        this.batchBinBoxId = batchBinBoxId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
