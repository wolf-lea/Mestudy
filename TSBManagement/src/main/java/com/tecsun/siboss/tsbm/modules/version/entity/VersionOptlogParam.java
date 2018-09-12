package com.tecsun.siboss.tsbm.modules.version.entity;

import com.tecsun.siboss.tsbm.common.bean.BaseVO;

import java.util.Date;

/**
 * Created by zhuxiaokai on 15-12-21.
 */
public class VersionOptlogParam extends BaseVO{
    private Long id;
    private String optType;
    private String optUser;
    private Long versionId;
    private Date beginTime;
    private Date endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
