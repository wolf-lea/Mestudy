package com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * Created by chenlicong on 2018/3/26.
 */
public class TempStatisticsBean extends BaseVO{

    private String userid;//用户ID
    private String distinctId;//区县ID
    private String bank;//银行ID
    private String rkwd;//入库网点ID

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getRkwd() {
        return rkwd;
    }

    public void setRkwd(String rkwd) {
        this.rkwd = rkwd;
    }
}
