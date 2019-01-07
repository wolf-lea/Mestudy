package com.tecsun.sisp.adapter.modules.policy.entity.response;

/**
 * Created by danmeng on 2017/7/10.
 */
public class PolicyVO extends PolicyTitleVO {
    private String policyContext;//政策内容
    public String getPolicyContext() {
        return policyContext;
    }

    public void setPolicyContext(String policyContext) {
        this.policyContext = policyContext;
    }
}
