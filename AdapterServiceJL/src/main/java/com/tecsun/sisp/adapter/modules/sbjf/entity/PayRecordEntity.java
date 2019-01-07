package com.tecsun.sisp.adapter.modules.sbjf.entity;

import com.tecsun.sisp.adapter.common.util.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by linzetian on 2017/6/7.
 * 缴费记录
 */
public class PayRecordEntity {
    private long prdId;//主键ID
    private String payNum;//缴费单号
    private String xm;//姓名
    private String sfzh;//身份证号
    private String insureCode;//险种编码
    private String insureType;//险种类型
    private String gradeName;//缴费档次名称
    private String gradeAmount;//缴费金额
    private String year;//缴费年份
    private String status;//状态

    public long getPrdId() {
        return prdId;
    }

    public void setPrdId(long prdId) {
        this.prdId = prdId;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getInsureType() {
        String _insureType = Constants.INSURE_TYPE.get(insureType);
        return StringUtils.isNotEmpty(_insureType) ? _insureType :insureType ;
    }

    public void setInsureType(String insureType) {
        this.insureType = insureType;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeAmount() {
        return gradeAmount;
    }

    public void setGradeAmount(String gradeAmount) {
        this.gradeAmount = gradeAmount;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInsureCode() {
        return insureCode;
    }

    public void setInsureCode(String insureCode) {
        this.insureCode = insureCode;
    }
}
