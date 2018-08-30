package com.tecsun.sisp.adapter.modules.card.entity.response;

import java.util.List;

/**
 */
public class BankDataVO {
    private String bankName;//银行名称
    private String bankCode;//银行编码
    private String regionalCode;//银行区域编号
    private String parentCode;//上级编号
    private List<BankDataVO> childBanks;//子银行信息

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

    public String getRegionalCode() {
        return regionalCode;
    }

    public void setRegionalCode(String regionalCode) {
        this.regionalCode = regionalCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public List<BankDataVO> getChildBanks() {
        return childBanks;
    }

    public void setChildBanks(List<BankDataVO> childBanks) {
        this.childBanks = childBanks;
    }
}
