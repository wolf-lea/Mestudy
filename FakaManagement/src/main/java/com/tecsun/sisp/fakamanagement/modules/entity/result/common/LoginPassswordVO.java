package com.tecsun.sisp.fakamanagement.modules.entity.result.common;

/**
 * Created by chenlicong on 2018/5/21.
 */
public class LoginPassswordVO {

    private String userid;//用户ID
    private String logName;//账号
    private String oldPassword;//旧密码
    private String newPassword;//新密码

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
