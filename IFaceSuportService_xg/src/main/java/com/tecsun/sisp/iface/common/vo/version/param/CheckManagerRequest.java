package com.tecsun.sisp.iface.common.vo.version.param;


/**
 * Created by zhongzhiyong on 16-1-12.
 */
public class CheckManagerRequest {
    private String certNum;
    private String phone;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
