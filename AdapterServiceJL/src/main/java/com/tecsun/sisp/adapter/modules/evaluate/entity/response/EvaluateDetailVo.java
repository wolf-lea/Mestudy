package com.tecsun.sisp.adapter.modules.evaluate.entity.response;

/**
 * Created by xumaohao on 2017/10/19.
 */
public class EvaluateDetailVo {
    private long contentId;       //内容表id
    private String contentName;     //评价的内容名称
    private String contentProperty; //（1:星星 2:选择程度 3:中文输入）
    private String evaluateResult;  //评价结果
    private String standby1;        //备用字段1
    private String standby2;        //备用字段2
    private String standby3;        //备用字段3

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentProperty() {
        return contentProperty;
    }

    public void setContentProperty(String contentProperty) {
        this.contentProperty = contentProperty;
    }

    public String getEvaluateResult() {
        return evaluateResult;
    }

    public void setEvaluateResult(String evaluateResult) {
        this.evaluateResult = evaluateResult;
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
}
