package com.tecsun.sisp.adapter.modules.treatment.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**认证入参
 * Created by danmeng on 2017/3/16.
 */
public class VerifyBean extends SecQueryBean{
    private long verifyId;//认证id
    private long operationId;//操作id
    private long verifyoperaId;//认证操作关联id
    private long personId;
    private String verifyAddress;//认证地点
    private String verifyTime;//认证时间
    private String verifyType;//认证方式 01：照片（人脸）;02：声纹;03：指纹（云端）; 04：指静脉;05：活体检测（终端）;06：指纹（终端）;07：指静脉（终端）;08：活体检测（云端）;

    private String verifyChannel;//认证渠道类型(3.1渠道类型编码说明)
    private long verifyData;//认证图片id上传图片uploadPicture返回picId
    private String verifyBase64;//生物特征数据
    private String verifyHand;//1:左手；2：右手
    private String verifyResult;//认证结果
    private String verifyBus;//认证业务：001-实名支付认证；002-生存认证；003-社保卡申请；004-补换卡申请；
    private long infraredData;//活体检测图片ID
    private String comparisonBase64;//比对生物特征数据(BASE64)	指纹、指静脉（认证必填）
    private long comparisonData;//比对图片id
    private String failReason;//失败原因
    private String picType;//图片类型

    public long getVerifyoperaId() {
        return verifyoperaId;
    }

    public void setVerifyoperaId(long verifyoperaId) {
        this.verifyoperaId = verifyoperaId;
    }

    public long getOperationId() {
        return operationId;
    }

    public void setOperationId(long operationId) {
        this.operationId = operationId;
    }

    public long getInfraredData() {
        return infraredData;
    }

    public void setInfraredData(long infraredData) {
        this.infraredData = infraredData;
    }

    public long getComparisonData() {
        return comparisonData;
    }

    public void setComparisonData(long comparisonData) {
        this.comparisonData = comparisonData;
    }

    public String getComparisonBase64() {
        return comparisonBase64;
    }

    public void setComparisonBase64(String comparisonBase64) {
        this.comparisonBase64 = comparisonBase64;
    }

    public long getVerifyData() {
        return verifyData;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public String getVerifyBus() {
        return verifyBus;
    }

    public void setVerifyBus(String verifyBus) {
        this.verifyBus = verifyBus;
    }


    public String getVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(String verifyResult) {
        this.verifyResult = verifyResult;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public long getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(long verifyId) {
        this.verifyId = verifyId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getVerifyAddress() {
        return verifyAddress;
    }

    public void setVerifyAddress(String verifyAddress) {
        this.verifyAddress = verifyAddress;
    }

    public String getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }

    public String getVerifyChannel() {
        return verifyChannel;
    }

    public void setVerifyChannel(String verifyChannel) {
        this.verifyChannel = verifyChannel;
    }

    public void setVerifyData(long verifyData) {
        this.verifyData = verifyData;
    }

    public String getVerifyBase64() {
        return verifyBase64;
    }

    public void setVerifyBase64(String verifyBase64) {
        this.verifyBase64 = verifyBase64;
    }

    public String getVerifyHand() {
        return verifyHand;
    }

    public void setVerifyHand(String verifyHand) {
        this.verifyHand = verifyHand;
    }
}
