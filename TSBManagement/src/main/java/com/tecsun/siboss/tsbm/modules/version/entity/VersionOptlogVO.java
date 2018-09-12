package com.tecsun.siboss.tsbm.modules.version.entity;

import com.tecsun.siboss.tsbm.common.bean.BaseVO;

import java.util.Date;

/**
 * Created by zhuxiaokai on 15-12-17.
 */
public class VersionOptlogVO extends BaseVO{
    private Long id;//主键
    private Long versionId;//版本表主键
    private String optType;//操作类型
    private String optUser;//操作人
    private Date optTime;//操作时间
    private String optionDescription;//操作说明
    private String name;//版本名称
    private String version;//版本编号

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
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

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
