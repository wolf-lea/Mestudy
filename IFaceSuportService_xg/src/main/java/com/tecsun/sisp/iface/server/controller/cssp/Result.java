package com.tecsun.sisp.iface.server.controller.cssp;

/**
 * Created by hhl on 2016/6/20.
 */
public class Result<T> {
    private Integer statusCode;
    private T result;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
