package com.tecsun.sisp.net.modules.entity.request;

import com.tecsun.sisp.net.common.vo.faceverify.SecQueryBean;

/** 事项文件表
 * Created by Administrator on 2018/8/9 0009.
 */
public class OfficeFileBean extends SecQueryBean{
    private long id;
    private String name;    //办件文件名称
    private String officeid;    //办件编码

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeid() {
        return officeid;
    }

    public void setOfficeid(String officeid) {
        this.officeid = officeid;
    }
}
