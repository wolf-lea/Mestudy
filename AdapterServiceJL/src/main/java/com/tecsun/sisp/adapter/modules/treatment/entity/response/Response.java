package com.tecsun.sisp.adapter.modules.treatment.entity.response;

/**
 * Created by danmeng on 2017/3/16.
 */
public class Response {
    private String signature;
    private Object secureData;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Object getSecureData() {
        return secureData;
    }

    public void setSecureData(Object secureData) {
        this.secureData = secureData;
    }
}
