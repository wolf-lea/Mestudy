package com.tecsun.sisp.iface.common.vo.common.param;

/**
 * Created by songjiawei on 15-5-21.
 */
//{"status":1,"name":"32","device_id":202,"online_status":1}
public class DevBean {
    private String status;
    private String name;
    private String device_id;
    private String online_status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getOnline_status() {
        return online_status;
    }

    public void setOnline_status(String online_status) {
        this.online_status = online_status;
    }
}
