package com.tecsun.sisp.iface.common.vo.cssp;

/**
 * 登录实体
 * Created by liuguolin on 2016/8/2.
 */
public class LoginVO {
    private  String loginName; //账号
    private String password; //密码
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
