package com.tecsun.siboss.tsbm.modules.version.entity;

import java.util.Date;

/**
 * Created by houguifa on 15-12-26.
 */
public class DeviceVO {
    private Long id;
    private String flowNum;
    private String CPU;
    private String model;
    private String repairflag;
    private String hardwareVersion;
    private String androidVersion;
    private String softwareVersion;
    private String keyboardCode;
    private String keyboardVersion;
    private String signLicense;
    private Long areaId;
    private Long custId;
    private Long merchantId;
    private Integer status;
    private Integer salesType;
    private Long warehouseId;
    private String warehouseName;
    private Date createTime;
    private String createUser;
    private Date modTime;
    private String modUser;
    private String moduleId;

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRepairflag() {
        return repairflag;
    }

    public void setRepairflag(String repairflag) {
        this.repairflag = repairflag;
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

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
}
