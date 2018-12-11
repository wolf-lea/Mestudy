package com.tecsun.sisp.iface.common.vo.version.result;

/**
 * Created by zhuxiaokai on 16-1-7.
 */
public class VerUpdateListParam {
    private Long versionId;
    private String deviceNo;

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
