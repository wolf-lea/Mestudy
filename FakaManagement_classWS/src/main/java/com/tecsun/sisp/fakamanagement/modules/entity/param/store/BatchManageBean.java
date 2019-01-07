package com.tecsun.sisp.fakamanagement.modules.entity.param.store;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

public class BatchManageBean extends BaseVO{
    private Integer id;
    private Integer[] ids;
    private String  userid;//发卡机构
    private String batchNo;
    private String beginDate;
    private String endDate;

    private String bankId;
    private String status;//下载状态
//    private String[] batchArray;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
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

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public String[] getBatchArray() {
//        return batchArray;
//    }
//
//    public void setBatchArray(String[] batchArray) {
//        this.batchArray = batchArray;
//    }
}
