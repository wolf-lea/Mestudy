package com.tecsun.sisp.iface.common.vo.version.param;



/**
 * Created by huanghailiang on 15-12-24.
 */
public class VerCheckRecordBean {
    private Integer deviceId;//设备表主键
    private String deviceType;//设备类型 1 德生宝
    private Integer areaId;//区域ID
    private String currVersion;//当前版本
    private Integer latestVersionId;//最新版本ID
    private String latestVersion;//最新版本号
    private String needUpdate;//是否需要更新
    private String optUser;//操作人
    private String optTime;//操作时间
    private String description;//操作说明

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getCurrVersion() {
        return currVersion;
    }

    public void setCurrVersion(String currVersion) {
        this.currVersion = currVersion;
    }

    public Integer getLatestVersionId() {
        return latestVersionId;
    }

    public void setLatestVersionId(Integer latestVersionId) {
        this.latestVersionId = latestVersionId;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public String getNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(String needUpdate) {
        this.needUpdate = needUpdate;
    }

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser;
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
