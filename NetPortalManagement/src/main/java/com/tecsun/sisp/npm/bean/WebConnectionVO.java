package com.tecsun.sisp.npm.bean;


import java.sql.Timestamp;

import bee.cloud.engine.db.Doe;
import bee.cloud.engine.db.annotation.Column;
import bee.cloud.engine.db.annotation.TableName;
import bee.cloud.engine.db.core.Table;

/**
 * created by fuweifeng on 2015-6-1.
 */
@TableName(name = "T_WEB_CONNECTION_INFO", alias = "connectoininfo")
public class WebConnectionVO extends Table {

    public WebConnectionVO(Doe doe) {
        super(doe);
    }

    public static final String ID = "id";

    @Column(name = ID, pk = true)
    private long id;


    public static final String IP = "ip";
    @Column(name = IP)
    private String ip;


    public static final String MAC = "mac";
    @Column(name = MAC)
    private String mac;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public static final String CREATE_TIME = "create_time";
    @Column(name = CREATE_TIME)
    private String create_time;


}
