package com.tecsun.sisp.adapter.modules.evaluate.entity.request;

/**
 * Created by xumaohao on 2017/10/18.
 */
public class EvaluateDetailBean{

    private long evaluateId;        //用户评价表id
    private long contentId;         //内容表id
    private String evaluateResult;  //评价结果

    public long getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(long evaluateId) {
        this.evaluateId = evaluateId;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public String getEvaluateResult() {
        return evaluateResult;
    }

    public void setEvaluateResult(String evaluateResult) {
        this.evaluateResult = evaluateResult;
    }
}
