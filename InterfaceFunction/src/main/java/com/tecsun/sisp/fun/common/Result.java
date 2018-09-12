package com.tecsun.sisp.fun.common;

public class Result {
    private String statusCode;//200 成功；其他 失败
    private String message;
    private Object result;
    private long total;

    public Result() {
    }

    public Result(String statusCode, String message, Object result) {
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }
    
    public Result(String statusCode, String message, Object result , long total) {
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
        this.total = total;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
    
    
}
