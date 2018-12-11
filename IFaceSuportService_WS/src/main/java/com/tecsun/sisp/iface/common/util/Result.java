package com.tecsun.sisp.iface.common.util;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private String statusCode;
    private String message;
    private Object data;


    public Result() {
    }

    public Result(String statusCode, String message, Object result) {
        this.statusCode = statusCode;
        this.message = message;
        if (result == null){
            this.data = "";
        }else{
            this.data = result;
        }
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
        List list = new ArrayList();
            this.data = data;
	}

   

    
    
}
