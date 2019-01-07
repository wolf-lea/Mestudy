package com.tecsun.sisp.adapter.modules.version.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by xumaohao on 2017/11/16.
 */
public class VersionBean extends SecQueryBean {
    private long id;                        //id
    private String sortwareCode;            //软件编码
    private String sortwareVersion;         //软件版本
    private String updateMethod;            //更新方式
    private String appType;                 //app类型

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSortwareCode() {
        return sortwareCode;
    }

    public void setSortwareCode(String sortwareCode) {
        this.sortwareCode = sortwareCode;
    }

    public String getSortwareVersion() {
        return sortwareVersion;
    }

    public void setSortwareVersion(String sortwareVersion) {
        this.sortwareVersion = sortwareVersion;
    }

    public String getUpdateMethod() {
        return updateMethod;
    }

    public void setUpdateMethod(String updateMethod) {
        this.updateMethod = updateMethod;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}
