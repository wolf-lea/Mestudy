package com.tecsun.sisp.iface.common.vo.cssp;

import java.util.Date;

/**
 * Created by hhl on 2016/6/17.
 */
public class BasicPeronBizHisBean {
    private Integer id;
    private Integer personId;
    private String busType;
    private Integer busId;
    private String busContent;
    private Date busStartTime;
    private Date busEndTime;
    private String busStatus;
    private Date addDate;
    private Integer operatorId;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public String getBusContent() {
        return busContent;
    }

    public void setBusContent(String busContent) {
        this.busContent = busContent;
    }

    public Date getBusStartTime() {
        return busStartTime;
    }

    public void setBusStartTime(Date busStartTime) {
        this.busStartTime = busStartTime;
    }

    public Date getBusEndTime() {
        return busEndTime;
    }

    public void setBusEndTime(Date busEndTime) {
        this.busEndTime = busEndTime;
    }

    public String getBusStatus() {
        return busStatus;
    }

    public void setBusStatus(String busStatus) {
        this.busStatus = busStatus;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
