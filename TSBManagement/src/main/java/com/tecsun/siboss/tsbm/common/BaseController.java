package com.tecsun.siboss.tsbm.common;


import com.tecsun.siboss.tsbm.common.bean.Result;

public abstract class BaseController {

    public Result result(String result, String message) {
        return result(result, message, null);
    }

    public Result result(String result, String message, Object obj) {
        return new Result(result, message, obj);
    }

    public Result result(String result, String message, Object obj, Integer total) {
        return new Result(result, message, obj, total);
    }

}
