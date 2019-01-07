package com.tecsun.sisp.adapter.modules.evaluate.entity.response;

import java.util.Date;
import java.util.List;

/**
 * Created by xumaohao on 2017/10/18.
 */
public class EvaluateVo{
    private long evaluateId;                //评价表ID
    private String evaluateXm;              //评价人姓名
    private String evaluateSfzh;            //评价人身份证号
    private String evaluatePhone;           //评价人手机号
    private String evaluateService;         //评价的业务编码
    private String serviceName;             //业务名称
    private String serviceNumber;           //业务编号
    private String evaluatedXm;             //被评价人姓名
    private String evaluatedSfzh;           //被评价人身份证号
    private String evaluatedNumber;         //被评价人编号
    private Date createTime;                //创建时间
    private Date updateTime;                //修改时间
    private String isEvaluate;              //是否已评价
    private String standby1;        //备用字段1
    private String standby2;        //备用字段2
    private String standby3;        //备用字段3
    private List<EvaluateDetailVo> data;    //评价结果

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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getStandby1() {
        return standby1;
    }

    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    public String getStandby2() {
        return standby2;
    }

    public void setStandby2(String standby2) {
        this.standby2 = standby2;
    }

    public String getStandby3() {
        return standby3;
    }

    public void setStandby3(String standby3) {
        this.standby3 = standby3;
    }

    public List<EvaluateDetailVo> getData() {
        return data;
    }

    public void setData(List<EvaluateDetailVo> data) {
        this.data = data;
    }

    public String getIsEvaluate() {
        return isEvaluate;
    }

    public void setIsEvaluate(String isEvaluate) {
        this.isEvaluate = isEvaluate;
    }
}
