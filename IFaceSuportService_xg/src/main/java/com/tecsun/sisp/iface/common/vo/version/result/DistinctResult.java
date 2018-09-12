package com.tecsun.sisp.iface.common.vo.version.result;

/**
 * ClassName:
 * Description:
 * Author:zhengmingmin
 * CreateTime: 16-1-5
 */
public class DistinctResult {
    private String statusCode;//200 成功；0 失败
    private String message;//提示消息
    private Object result;//返回结果
    public DistinctResult(String statusCode) {
        this.statusCode = statusCode;

    }


    public DistinctResult() {
    }

    public DistinctResult(String statusCode, String message, Object result) {
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }

    public Object getResult() {
        return result;
    }



    public void setResult(Object result) {
        this.result = result;
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

}
