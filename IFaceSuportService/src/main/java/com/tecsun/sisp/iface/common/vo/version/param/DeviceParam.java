package com.tecsun.sisp.iface.common.vo.version.param;


import java.io.Serializable;

/**
 * ClassName:VersionParam
 * Description:软件版本校验参数
 * Created by zhuxiaokai on 15-12-24.
 */
public class DeviceParam implements Serializable{
    private String cpu;//设备CPU编号
    private String model;//设备型号
    private String hardwareVersion;//设备硬件版本
    private String androidVersion;//设备安卓版本
    private String softwareVersion;//设备软件版本
    private String keyboardCode;//设备密码键盘机器号
    private String encryptionString;//
    private String moduleId;//设备3G模块ID
    private Double longitude;//经度
    private Double latitude;//纬度
    private String appType;//app种类

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getKeyboardCode() {
        return keyboardCode;
    }

    public void setKeyboardCode(String keyboardCode) {
        this.keyboardCode = keyboardCode;
    }

    public String getEncryptionString() {
        return encryptionString;
    }

    public void setEncryptionString(String encryptionString) {
        this.encryptionString = encryptionString;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
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
