package com.tecsun.sisp.adapter.common.util;

/**
 * 
 * @author tan
 *
 */
public class ResultPage {
    private String statusCode;//200 成功；0 失败
    private String message;
    private Object result;
    private long total;

	public Result result(String result, String message, Object obj) {
		return new Result(result, message, obj);
	}
	
	public Result error(String message, Object obj) {
		return result(Constants.RESULT_MESSAGE_ERROR, message, obj);
	}
    public Result ok(String message, Object obj) {
		return result(Constants.RESULT_MESSAGE_SUCCESS, 0, message, obj);
	}
    
	public Result result(String result, long total, String message, Object obj) {
		return new Result(result, total, message, obj);
	}
	
	/**
	 * 返回成功的结果
	 * @param message
	 */
	public ResultPage success(String message){
		reset();
		this.statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		this.message=message;
		return this;
	}

	public ResultPage success(String message,Object result){
		reset();
		this.statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		this.message=message;
		this.result = result;
		return this;
	}

	public ResultPage success(String message,Object result,long total){
		reset();
		this.statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		this.message=message;
		this.result = result;
		this.total=total;
		return this;
	}


	
	/**
	 * 返回失败的结果
	 * @param message
	 */
	public ResultPage fail(String message){
		reset();
		this.message=message;
		this.statusCode = Constants.RESULT_MESSAGE_ERROR;
		return this;
	}

	public ResultPage fail(String message,Object result){
		reset();
		this.message=message;
		this.statusCode = Constants.RESULT_MESSAGE_ERROR;
		this.result = result;
		return this;
	}

	public ResultPage fail(String message,Object result,long total){
		reset();
		this.message=message;
		this.statusCode = Constants.RESULT_MESSAGE_ERROR;
		this.result = result;
		this.total=total;
		return this;
	}

	public void reset(){
		this.message="";
		this.statusCode="";
		this.result="";
		this.total=0;
	}

    public ResultPage() {
    }

    public ResultPage(String statusCode, String message, Object result) {
		reset();
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }

    public ResultPage(String statusCode, long total, String message, Object result) {
		reset();
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
        this.total=total;
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
