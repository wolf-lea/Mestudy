package com.tecsun.sisp.fakamanagement.modules.entity.result.supervise;

/**
 * Created by chenlicong on 2017/12/28.
 */
public class UserFunctionVO {

    private String id;
    private String userId;//用户ID
    private String serial;//功能序号
    private String funcName;//功能名称
    private String sort;//排序

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
