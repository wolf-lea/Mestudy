package com.tecsun.sisp.fun.bean;

import java.sql.Timestamp;

/**
 * 字典表
 * Created by zhangqingjie on 15-5-15.
 */
public class DictionaryBean {

    private long diction_id;
    private int status;
    private String name;
    private String groups;
    private Timestamp create_time;
    private Timestamp update_time;
    private int del;
    private String code;
    private String groupid;

    public DictionaryBean() {
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

    public long getDiction_id() {
        return diction_id;
    }

    public void setDiction_id(long diction_id) {
        this.diction_id = diction_id;
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

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }
}
