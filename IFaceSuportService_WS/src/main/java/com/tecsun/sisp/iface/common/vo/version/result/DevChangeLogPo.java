package com.tecsun.sisp.iface.common.vo.version.result;

import java.util.Date;

/**
 * Description:设备信息变更记录
 * Created by zhuxiaokai on 15-12-24.
 */
public class DevChangeLogPo {
    private Long id;//主键ID
    private Long deviceId;//设备信息表ID
    private String optDescription;//操作说明
    private Date createTime;//添加时间
    private String createUser;//添加人
    private Double longitude;//经度
    private Double latitude;//纬度

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getOptDescription() {
        return optDescription;
    }

    public void setOptDescription(String optDescription) {
        this.optDescription = optDescription;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
