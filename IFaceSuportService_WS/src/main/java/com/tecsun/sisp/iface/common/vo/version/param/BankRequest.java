package com.tecsun.sisp.iface.common.vo.version.param;

/**
 * Created by chenlicong on 16-4-14.
 */
public class BankRequest {

    private Long bankId;
    private Long distinctId;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(Long distinctId) {
        this.distinctId = distinctId;
    }
}
