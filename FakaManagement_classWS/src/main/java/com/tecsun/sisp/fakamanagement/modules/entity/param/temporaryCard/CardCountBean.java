package com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

import java.util.List;

/**
 * 统计入参
 *
 * yangliu
 */
public class CardCountBean  extends BaseVO {
    private Integer userId;//用户登录id
    private String bankId;//银行id
    private String branchId;//网点id
    private String countryId;//区县id
    private String beginTime;
    private String endTime;
    private String orgId;
    private String orgCode;
    private String parentid;
    private List orgCodes;
    private List orgIds;

    private String distinctId;
    private String bank;

    public String getBankId() {
        return bankId;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getParentid() {
        return parentid;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public List getOrgCodes() {
        return orgCodes;
    }

    public void setOrgCodes(List orgCodes) {
        this.orgCodes = orgCodes;
    }

    public List getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List orgIds) {
        this.orgIds = orgIds;
    }




    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(String distinctId) {
        this.distinctId = distinctId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
