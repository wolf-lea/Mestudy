package com.tecsun.sisp.adapter.modules.finance.entity.response;

/**设备信息
 * Created by danmeng on 2017/6/5.
 */
public class TermDeviceVO {
    private String termDeviceNo;//设备号
    private String termId;//终端号
    private String pinPadDeviceNo;//密码键盘号
    private String shh;//商户号
    private String zdh;//终端号

    public String getShh() {
        return shh;
    }

    public void setShh(String shh) {
        this.shh = shh;
    }

    public String getZdh() {
        return zdh;
    }

    public void setZdh(String zdh) {
        this.zdh = zdh;
    }

    public String getTermDeviceNo() {
        return termDeviceNo;
    }

    public void setTermDeviceNo(String termDeviceNo) {
        this.termDeviceNo = termDeviceNo;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getPinPadDeviceNo() {
        return pinPadDeviceNo;
    }

    public void setPinPadDeviceNo(String pinPadDeviceNo) {
        this.pinPadDeviceNo = pinPadDeviceNo;
    }
}
