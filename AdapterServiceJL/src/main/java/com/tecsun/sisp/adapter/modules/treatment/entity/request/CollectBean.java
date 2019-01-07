package com.tecsun.sisp.adapter.modules.treatment.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**采集入参
 * Created by danmeng on 2017/3/17.
 */
public class CollectBean extends SecQueryBean {
    private long colId;
    private long personId;//个人基本信息表ID 来自T_YTH_BASIC_PERSON_INFO
    private String colAddress;//采集地点
    private String colTime;//采集时间
    private String colType;//采集方式01：照片（人脸）； 02：声纹； 03：指纹；04：指静脉
    private String colChannel;//采集渠道类型
    private long colData;//采集图片id
    private String colBase64;//生物特征数据(BASE64)	指纹、指静脉（认证必填）
     private String colHand;//1:左手；2：右手
    private String failReason;//失败原因
    private String colResult;//认证结果
    private String colBus;//认证业务：002-生存认证；

    public String getColBus() {
        return colBus;
    }

    public void setColBus(String colBus) {
        this.colBus = colBus;
    }

    public long getColId() {
        return colId;
    }

    public void setColId(long colId) {
        this.colId = colId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getColAddress() {
        return colAddress;
    }

    public void setColAddress(String colAddress) {
        this.colAddress = colAddress;
    }

    public String getColTime() {
        return colTime;
    }

    public void setColTime(String colTime) {
        this.colTime = colTime;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public String getColChannel() {
        return colChannel;
    }

    public void setColChannel(String colChannel) {
        this.colChannel = colChannel;
    }

    public long getColData() {
        return colData;
    }

    public void setColData(long colData) {
        this.colData = colData;
    }

    public String getColBase64() {
        return colBase64;
    }

    public void setColBase64(String colBase64) {
        this.colBase64 = colBase64;
    }

    public String getColHand() {
        return colHand;
    }

    public void setColHand(String colHand) {
        this.colHand = colHand;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getColResult() {
        return colResult;
    }

    public void setColResult(String colResult) {
        this.colResult = colResult;
    }
}
