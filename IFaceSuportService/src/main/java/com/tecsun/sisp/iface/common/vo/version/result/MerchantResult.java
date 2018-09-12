package com.tecsun.sisp.iface.common.vo.version.result;

/**
 * 德生宝签到返回商户相关信息
 * Created by huanghailiang on 15-12-29.
 */
public class MerchantResult {
    private long merchantId;
    private String merchantCert;
    private long areaId;
    private long deviceId;
    //private long custId;
   // private long
    private int userType;//用户类型  1 商户 2 客户

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantCert() {
        return merchantCert;
    }

    public void setMerchantCert(String merchantCert) {
        this.merchantCert = merchantCert;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
