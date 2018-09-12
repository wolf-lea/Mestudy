package com.tecsun.siboss.tsbm.common.bean;

/**
 * Created by zhangqingjie on 15-5-11.
 * Result
 */
public class Result {
    private String statusCode;//200 成功；0 失败
    private String message;
    private Object result;
    private Integer total;

    /**
     * @param statusCode 状态码
     * @param message   提示消息
     * @param result    数据对象
     * @param total     数据总数--用于分页
     */
    public Result(String statusCode, String message, Object result, Integer total) {
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
        this.total = total;
    }

    public Result(String statusCode, String message, Object result) {
        this.statusCode = statusCode;
        this.message = message;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
