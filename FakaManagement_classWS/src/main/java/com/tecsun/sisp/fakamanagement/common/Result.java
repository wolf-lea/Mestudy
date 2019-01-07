package com.tecsun.sisp.fakamanagement.common;

import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;

/**
 * 
 * @author tan
 *
 */
public class Result {
    private String statusCode;//200 成功；0 失败
    private String message;
    private Object data;
    private long total;

    public Result() {
    }

    public Result(String statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public Result(String statusCode,long total, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
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

	/**
	 * 重置函数
	 */
	private void reset(){
		this.statusCode ="";
		this.data="";
		this.total=0;
		this.message="";
	}
	/**
	 * 返回成功的结果
	 * @param message
	 */
	public Result ok(String message){
		Result result = new Result();
		reset();
		result.setStatusCode(BaseController.RESULT_MESSAGE_SUCCESS);
		result.setMessage(message);
		return result;
	}

	public Result ok(String message,Object data){
		Result result = new Result();
		reset();
		result.setMessage(message);
		result.setStatusCode(BaseController.RESULT_MESSAGE_SUCCESS);
		result.setData(data);
		return result;
	}

	public Result ok(String message,Object data,long total){
		Result result = new Result();
		reset();
		result.setMessage(message);
		result.setStatusCode(BaseController.RESULT_MESSAGE_SUCCESS);
		result.setData(data);
		result.setTotal(total);
		return result;
	}

	/**
	 * 返回失败的结果
	 * @param message
	 */
	public  Result error(String message){
		Result result = new Result();
		reset();
		result.setMessage(message);
		result.setStatusCode(BaseController.RESULT_MESSAGE_ERROR);
		return result;
	}

	public Result error(String message,Object data){
		Result result = new Result();
		reset();
		result.setMessage(message);
		result.setStatusCode(BaseController.RESULT_MESSAGE_ERROR);
		result.setData(data);
		return result;
	}

	public Result error(String message,Object data,long total){
		Result result = new Result();
		reset();
		result.setMessage(message);
		result.setStatusCode(BaseController.RESULT_MESSAGE_ERROR);
		result.setData(data);
		result.setTotal(total);
		return result;
	}
}
