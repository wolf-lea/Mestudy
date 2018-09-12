package com.tecsun.sisp.iface.server.controller.cssp;

import java.util.Date;

/**
 * Created by hhl on 2016/6/20.
 */
public class BankOrgInfo {
    private Integer id;
    private String bankName;
    private String bankCode;
    private String bankCode2;
    private String bankNum;
    private String address;
    private Integer parentId;
    private Integer isRoot;
    private Integer isEnd;
    private Integer isSend;
    private String isNet;
    private Integer regionalId;
    private String keyType;
    private String isEnabled;
    private String hasTerminal;
    private String terminalCode;
    private Date addDate;
    private Date modDate;
    private Integer addUserId;
    private Integer modUserId;
    private String remark;
    private Date terminalRecentlyTime;
    private Integer bankId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCode2() {
        return bankCode2;
    }

    public void setBankCode2(String bankCode2) {
        this.bankCode2 = bankCode2;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Integer isRoot) {
        this.isRoot = isRoot;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public String getIsNet() {
        return isNet;
    }

    public void setIsNet(String isNet) {
        this.isNet = isNet;
    }

    public Integer getRegionalId() {
        return regionalId;
    }

    public void setRegionalId(Integer regionalId) {
        this.regionalId = regionalId;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getHasTerminal() {
        return hasTerminal;
    }

    public void setHasTerminal(String hasTerminal) {
        this.hasTerminal = hasTerminal;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Integer getModUserId() {
        return modUserId;
    }

    public void setModUserId(Integer modUserId) {
        this.modUserId = modUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getTerminalRecentlyTime() {
        return terminalRecentlyTime;
    }

    public void setTerminalRecentlyTime(Date terminalRecentlyTime) {
        this.terminalRecentlyTime = terminalRecentlyTime;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }
}
