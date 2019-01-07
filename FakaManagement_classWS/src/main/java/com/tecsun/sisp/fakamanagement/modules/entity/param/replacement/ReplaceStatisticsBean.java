package com.tecsun.sisp.fakamanagement.modules.entity.param.replacement;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * Created by chenlicong on 2018/10/24.
 */
public class ReplaceStatisticsBean extends BaseVO{

    private String userid;//用户ID
    private String distinctId;//区县ID
    private String bank;//银行ID
    private String rkwd;//入库网点ID

    private String beginTime;//起始时间
    private String endTime;//截止时间

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
}
