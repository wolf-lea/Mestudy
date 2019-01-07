package com.tecsun.sisp.adapter.modules.treatment.entity.response;

public class VerifyResult {
    private long verifyId;// 认证ID
    private long operationId;// 操作id
    private String verifyTime;//认证时间
    private String score;//分数
    private String threshold;//标准

    public long getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(long verifyId) {
        this.verifyId = verifyId;
    }
    public long getOperationId() {
        return operationId;
    }

    public void setOperationId(long operationId) {
        this.operationId = operationId;
    }

    public String getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }
}
