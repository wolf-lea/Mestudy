package com.tecsun.sisp.net.modules.entity.request;

import com.tecsun.sisp.net.common.vo.faceverify.SecQueryBean;

/** 事项文件表
 * Created by Administrator on 2018/8/9 0009.
 */
public class MatterFileBean extends SecQueryBean {
    private long id;
    private String name;    //事项文件名称
    private String matterid;    //事项编码
    private String filecode;       //文件编码

    public String getFilecode() {
        return filecode;
    }

    public void setFilecode(String filecode) {
        this.filecode = filecode;
    }

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

    public String getMatterid() {
        return matterid;
    }

    public void setMatterid(String matterid) {
        this.matterid = matterid;
    }
}
