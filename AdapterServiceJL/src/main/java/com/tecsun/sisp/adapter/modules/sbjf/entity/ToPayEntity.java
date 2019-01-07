package com.tecsun.sisp.adapter.modules.sbjf.entity;

import java.util.List;

/**
 * Created by linzetian on 2017/6/6.
 * 养老保险
 */
public class ToPayEntity {
    private long payId;//主键Id
    private String xm;//姓名
    private String relation;//与父子的关系
    private String sfzh;//身份证号
    private String year;//缴费年份
    private String subInsureCode;//子险种编码
    private String subInsureName;//子险种名称
    private List<GradeEntity> grades;//档次信息

    public long getPayId() {
        return payId;
    }

    public void setPayId(long payId) {
        this.payId = payId;
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

    public List<GradeEntity> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeEntity> grades) {
        this.grades = grades;
    }
}
