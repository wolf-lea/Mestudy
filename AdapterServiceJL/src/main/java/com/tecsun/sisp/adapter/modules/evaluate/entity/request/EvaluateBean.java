package com.tecsun.sisp.adapter.modules.evaluate.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

import java.util.Date;
import java.util.List;

/**
 * Created by xumaohao on 2017/10/18.
 */
public class EvaluateBean extends SecQueryBean{
    private long evaluateId;                //评价表ID
    private String evaluateXm;              //评价人姓名
    private String evaluateSfzh;            //评价人身份证号
    private String evaluatePhone;           //手机号
    private String evaluateService;         //评价的业务
    private String serviceNumber;           //业务编号
    private String evaluatedXm;             //被评价人姓名
    private String evaluatedSfzh;           //被评价人身份证号
    private String evaluatedNumber;         //被评价人编号
    private Date createTime;                //创建时间
    private Date updateTime;                //修改时间
    private List<EvaluateDetailBean> data;  //评价结果


    public long getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(long evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getEvaluateXm() {
        return evaluateXm;
    }

    public void setEvaluateXm(String evaluateXm) {
        this.evaluateXm = evaluateXm;
    }

    public String getEvaluateSfzh() {
        return evaluateSfzh;
    }

    public void setEvaluateSfzh(String evaluateSfzh) {
        this.evaluateSfzh = evaluateSfzh;
    }

    public String getEvaluatePhone() {
        return evaluatePhone;
    }

    public void setEvaluatePhone(String evaluatePhone) {
        this.evaluatePhone = evaluatePhone;
    }

    public String getEvaluateService() {
        return evaluateService;
    }

    public void setEvaluateService(String evaluateService) {
        this.evaluateService = evaluateService;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getEvaluatedXm() {
        return evaluatedXm;
    }

    public void setEvaluatedXm(String evaluatedXm) {
        this.evaluatedXm = evaluatedXm;
    }

    public String getEvaluatedSfzh() {
        return evaluatedSfzh;
    }

    public void setEvaluatedSfzh(String evaluatedSfzh) {
        this.evaluatedSfzh = evaluatedSfzh;
    }

    public String getEvaluatedNumber() {
        return evaluatedNumber;
    }

    public void setEvaluatedNumber(String evaluatedNumber) {
        this.evaluatedNumber = evaluatedNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<EvaluateDetailBean> getData() {
        return data;
    }

    public void setData(List<EvaluateDetailBean> data) {
        this.data = data;
    }
}
