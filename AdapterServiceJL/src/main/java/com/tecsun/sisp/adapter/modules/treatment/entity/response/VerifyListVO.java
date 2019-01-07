package com.tecsun.sisp.adapter.modules.treatment.entity.response;


/**认证结果
 * Created by danmeng on 2017/3/16.
 */
public class VerifyListVO {
    private String verifyTime;//认证时间
    private String verifyResult;//认证结果
    private String failReason;//失败原因
    private String type;//认证业务类型
    private String verifyTypeName;//认证业务类型名称
    private String channel;//认证渠道

    public String getVerifyTypeName() {
        return verifyTypeName;
    }

    public void setVerifyTypeName(String verifyTypeName) {
        this.verifyTypeName = verifyTypeName;
    }

    public String getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
