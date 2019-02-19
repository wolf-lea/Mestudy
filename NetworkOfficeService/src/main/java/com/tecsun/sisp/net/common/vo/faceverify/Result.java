package com.tecsun.sisp.net.common.vo.faceverify;

/**
 * Created by zhangqingjie on 15-5-11.
 */
public class Result {
    private String statusCode;//200 成功；0 失败
    private String message;
    private Object data;


    public Result() {
    }

    public Result(String statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
