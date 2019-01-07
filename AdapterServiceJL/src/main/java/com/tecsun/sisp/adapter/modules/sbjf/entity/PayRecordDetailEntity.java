package com.tecsun.sisp.adapter.modules.sbjf.entity;

/**
 * Created by linzetian on 2017/6/9.
 *缴费记录详情信息
 */
public class PayRecordDetailEntity {
    private String status;//状态
    private long payId;//主键Id
    private String insureCode;//险种
    private String xm;//姓名
    private String relation;//与父子的关系
    private String sfzh;//身份证号
    private String year;//缴费年份
    private String subInsureCode;//险种编码(自由人员就业缴费才有）
    private String subInsureName;//险种名称(自由人员就业缴费才有）

    private long prdId;//记录Id
    private long prId;//记录Id
    private String gradeCode;//档次编码
    private String gradeName;//档次名称
    private float gradeAmount;//金额
    private String payNum;//缴费单号

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPayId() {
        return payId;
    }

    public void setPayId(long payId) {
        this.payId = payId;
    }

    public String getInsureCode() {
        return insureCode;
    }

    public void setInsureCode(String insureCode) {
        this.insureCode = insureCode;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubInsureCode() {
        return subInsureCode;
    }

    public void setSubInsureCode(String subInsureCode) {
        this.subInsureCode = subInsureCode;
    }

    public String getSubInsureName() {
        return subInsureName;
    }

    public void setSubInsureName(String subInsureName) {
        this.subInsureName = subInsureName;
    }

    public long getPrdId() {
        return prdId;
    }

    public void setPrdId(long prdId) {
        this.prdId = prdId;
    }

    public long getPrId() {
        return prId;
    }

    public void setPrId(long prId) {
        this.prId = prId;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public float getGradeAmount() {
        return gradeAmount;
    }

    public void setGradeAmount(float gradeAmount) {
        this.gradeAmount = gradeAmount;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }


}
