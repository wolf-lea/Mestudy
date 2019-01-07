package com.tecsun.sisp.fakamanagement.modules.entity.param.store;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

import java.util.List;

/**
 * 发行服务-分拨入参
 * Created by xumaohao on 2018/1/23.
 */
public class DispatchBean extends BaseVO{
    private List id;            //批次id,用于分拨时允许多选分拨
    private String userid;      //用户（网点）id
    private String batchNo;     //批次号
    private String bank;        //银行（编码)
    private String fbStatus;    //分拨状态（编码）


    public List getId() {
        return id;
    }

    public void setId(List id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getFbStatus() {
        return fbStatus;
    }

    public void setFbStatus(String fbStatus) {
        this.fbStatus = fbStatus;
    }
}
