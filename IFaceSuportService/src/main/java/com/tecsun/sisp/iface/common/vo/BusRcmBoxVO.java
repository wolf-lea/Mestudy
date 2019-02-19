package com.tecsun.sisp.iface.common.vo;

import java.util.Date;

/**
 * Created by hhl on 2016/8/6.
 */
public class BusRcmBoxVO {
    private Long id;
    private String boxNo;
    private int status;
    private int count;
    private int nowCount;
    private int boxSn;
    private Long binId;
    private Long addUserNo;
    private Date addDate;
    private long modUserNo;
    private Date modDate;
    private Long bankId;
    private Long regionalId;
    private String remark;
    private int streamStatus;
    private String storeOrgType;
    private long storeOrgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNowCount() {
        return nowCount;
    }

    public void setNowCount(int nowCount) {
        this.nowCount = nowCount;
    }

    public int getBoxSn() {
        return boxSn;
    }

    public void setBoxSn(int boxSn) {
        this.boxSn = boxSn;
    }

    public Long getBinId() {
        return binId;
    }

    public void setBinId(Long binId) {
        this.binId = binId;
    }

    public Long getAddUserNo() {
        return addUserNo;
    }

    public void setAddUserNo(Long addUserNo) {
        this.addUserNo = addUserNo;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public long getModUserNo() {
        return modUserNo;
    }

    public void setModUserNo(long modUserNo) {
        this.modUserNo = modUserNo;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getRegionalId() {
        return regionalId;
    }

    public void setRegionalId(Long regionalId) {
        this.regionalId = regionalId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStreamStatus() {
        return streamStatus;
    }

    public void setStreamStatus(int streamStatus) {
        this.streamStatus = streamStatus;
    }

    public String getStoreOrgType() {
        return storeOrgType;
    }

    public void setStoreOrgType(String storeOrgType) {
        this.storeOrgType = storeOrgType;
    }

    public long getStoreOrgId() {
        return storeOrgId;
    }

    public void setStoreOrgId(long storeOrgId) {
        this.storeOrgId = storeOrgId;
    }
    private  String orgAddress;

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }
}
