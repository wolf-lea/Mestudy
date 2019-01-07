package com.tecsun.sisp.iface.common.vo;

/**
 * 物理卡表信息出参
 * Created by liu on 2016/8/8.
 */
public class ResultBasicCardVO {
    private Long batchId; //所属批次ID
    private Long personId;//

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
