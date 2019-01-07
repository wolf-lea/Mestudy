package com.tecsun.sisp.iface.common.vo.faceverify;

/**
 * Created by Sandwich on 2015/12/11.
 */
public class XGLoginBean {
    private String userName;
    private String netpassword;
    private String type;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNetpassword() {
        return netpassword;
    }

    public void setNetpassword(String netpassword) {
        this.netpassword = netpassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
