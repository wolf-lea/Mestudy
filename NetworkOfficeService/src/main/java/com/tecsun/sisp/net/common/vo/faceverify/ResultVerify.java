package com.tecsun.sisp.net.common.vo.faceverify;

public class ResultVerify extends Result{

	private String statusCode;//200 成功；0 失败
    private String message;
    private Object data;
    private long total;
    
    public ResultVerify(){}
	
	public ResultVerify(String statusCode, String message, Object data) {
		this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
	
	public ResultVerify(String statusCode, String message, Object data , long total) {
		this.statusCode = statusCode;
        this.message = message;
        this.data = data;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	
}
