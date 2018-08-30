package com.tecsun.sisp.adapter.modules.account.entity.response;

import java.util.List;

/** 账号功能
 * Created by danmeng on 2017/5/5.
 */
public class FunctionVO {
    private long funcId;//功能ID
    private String funcName;//功能名字
    private String funcStatus;//状态 0=不可用,1=可用
    private long parentFuncId;//父功能ID
    private List<FunctionVO> childFunctions;//子功能信息

    public long getFuncId() {
        return funcId;
    }

    public void setFuncId(long funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncStatus() {
        return funcStatus;
    }

    public void setFuncStatus(String funcStatus) {
        this.funcStatus = funcStatus;
    }

    public long getParentFuncId() {
        return parentFuncId;
    }

    public void setParentFuncId(long parentFuncId) {
        this.parentFuncId = parentFuncId;
    }

    public List<FunctionVO> getChildFunctions() {
        return childFunctions;
    }

    public void setChildFunctions(List<FunctionVO> childFunctions) {
        this.childFunctions = childFunctions;
    }
}
