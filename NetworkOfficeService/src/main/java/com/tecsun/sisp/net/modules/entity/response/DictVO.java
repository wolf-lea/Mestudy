package com.tecsun.sisp.net.modules.entity.response;

import java.sql.Timestamp;

/**
 * Created by danmeng on 2017/7/17.
 */
public class DictVO {
    private long dictionId;
    private int status;//可用状态：0可用   1不可用
    private String name;
    private Timestamp createTime;
    private Timestamp updateTime;
    private int del;//可否删除：0为不可删，1为可删除
    private String code;//编码
    private String groupid;//组名（父级编码）

    public long getDictionId() {
        return dictionId;
    }

    public void setDictionId(long dictionId) {
        this.dictionId = dictionId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
}
