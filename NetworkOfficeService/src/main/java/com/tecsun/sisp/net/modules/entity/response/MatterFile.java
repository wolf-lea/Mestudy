package com.tecsun.sisp.net.modules.entity.response;

/**
 * Created by Administrator on 2018/8/9 0009.
 */
public class MatterFile {
    private long id;
    private String name;
    private String matterid;
    private String filecode;

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
