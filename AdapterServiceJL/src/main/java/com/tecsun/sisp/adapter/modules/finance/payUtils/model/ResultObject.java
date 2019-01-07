package com.tecsun.sisp.adapter.modules.finance.payUtils.model;

/**
 * Created by chenlicong on 2017/9/18.
 */
public class ResultObject {

    private String resultCode;
    private String msg;
    private Object data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
