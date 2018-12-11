package com.tecsun.sisp.iface.common.vo.version.param;

/**
 * Created by zhongzhiyong on 16-1-12.
 */
public class GetDeviceRequest {
    private Long custId;
    private Long deviceId;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
}
