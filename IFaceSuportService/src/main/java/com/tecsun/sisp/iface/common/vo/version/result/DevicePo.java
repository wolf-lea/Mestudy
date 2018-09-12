package com.tecsun.sisp.iface.common.vo.version.result;

import java.util.Date;

/**
 * Description:设备信息
 * Created by zhuxiaokai on 15-12-24.
 */
public class DevicePo {
    private Long id;//设备id
    private String flowNum;//工厂流水号
    private String cpu;//CPU编码
    private String moduleId;//3G模块ID
    private String model;//型号
    private String repairFlag;//返修标志
    private String hardwareVersion;//硬件版本
    private String androidVersion;//安卓版本
    private String softwareVersion;//软件版本
    private String keyboardCode;//密码键盘机器号
    private String keyboardVersion;//密码键盘密钥版本
    private String signLicense;//签到密钥
    private Long areaId;//使用区域
    private Long custId;//客户ID
    private Long merchantId;//商户ID
    private Integer status;//状态
    private Integer salesType;//销售类型
    private Integer warehouseId;//仓库ID
    private Date createTime;//添加时间
    private String createUser;//添加人
    private Date modTime;//修改时间
    private String modUser;//修改人
    private Double longitude;//经度
    private Double latitude;//纬度
    private String appType;//app类型

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(String flowNum) {
        this.flowNum = flowNum;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRepairFlag() {
        return repairFlag;
    }

    public void setRepairFlag(String repairFlag) {
        this.repairFlag = repairFlag;
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

    public String getKeyboardVersion() {
        return keyboardVersion;
    }

    public void setKeyboardVersion(String keyboardVersion) {
        this.keyboardVersion = keyboardVersion;
    }

    public String getSignLicense() {
        return signLicense;
    }

    public void setSignLicense(String signLicense) {
        this.signLicense = signLicense;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSalesType() {
        return salesType;
    }

    public void setSalesType(Integer salesType) {
        this.salesType = salesType;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
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

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public String getModUser() {
        return modUser;
    }

    public void setModUser(String modUser) {
        this.modUser = modUser;
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