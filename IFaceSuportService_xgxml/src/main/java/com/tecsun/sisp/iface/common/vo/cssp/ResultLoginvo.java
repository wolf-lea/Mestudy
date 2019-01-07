package com.tecsun.sisp.iface.common.vo.cssp;

/**
 * 登录出参
 * Created by liu on 2016/8/2.
 */
public class ResultLoginvo {
    private String LOGIN_NAME;
    private String PASSWORD;

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public void setLOGIN_NAME(String LOGIN_NAME) {
        this.LOGIN_NAME = LOGIN_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
