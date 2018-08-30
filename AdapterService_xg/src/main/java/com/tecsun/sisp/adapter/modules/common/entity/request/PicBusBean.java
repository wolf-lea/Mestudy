package com.tecsun.sisp.adapter.modules.common.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by danmeng on 2017/3/14.
 */
public class PicBusBean extends SecQueryBean {
    private long picId;//
    private long perbusId;//
    private long perpicId;//
    private long personId;//
    private String picStatus;//图片状态
    private String picType;//图片类型
    private String picMessage;//图片信息

    public long getPicId() {
        return picId;
    }

    public void setPicId(long picId) {
        this.picId = picId;
    }

    public long getPerbusId() {
        return perbusId;
    }

    public void setPerbusId(long perbusId) {
        this.perbusId = perbusId;
    }

    public long getPerpicId() {
        return perpicId;
    }

    public void setPerpicId(long perpicId) {
        this.perpicId = perpicId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getPicStatus() {
        return picStatus;
    }

    public void setPicStatus(String picStatus) {
        this.picStatus = picStatus;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public String getPicMessage() {
        return picMessage;
    }

    public void setPicMessage(String picMessage) {
        this.picMessage = picMessage;
    }
}
