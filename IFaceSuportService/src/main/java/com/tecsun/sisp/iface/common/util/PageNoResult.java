package com.tecsun.sisp.iface.common.util;

public class PageNoResult {
    private String statusCode;
    private String message;
    private String total;

    public PageNoResult() {
    }

    public PageNoResult(String statusCode, String message,int result) {
        this.statusCode = statusCode;
        this.message = message;
        this.total = result+"";
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
