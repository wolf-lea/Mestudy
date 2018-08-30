package com.tecsun.sisp.adapter.modules.account.entity.request;


import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**账户信息
 * Created by danmeng on 2017/5/4.
 */
public class AccountBean extends SecQueryBean {
    private String accountId;//用户账号
    private String accountName;//用户名
    private String accountPwd;//密码
    private String reAccountPwd;//确认新密码
    private String phone;//手机号
    private String sex;//性别01男性;02女性;03未知
    private String nation;//民族（中文，如：汉）
    private String address;//通讯地址
    private String company;//单位名称
    private String status;//状态:1=有效（默认），0=无效
    private String roleCode;//角色编码
    private String openid;//微信绑定号
    private String description;//描述
    private String pwdIsupdate;//是否修改密码0：否（默认）  1：是
    private String alipayId;//支付宝绑定号

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPwdIsupdate() {
        return pwdIsupdate;
    }

    public void setPwdIsupdate(String pwdIsupdate) {
        this.pwdIsupdate = pwdIsupdate;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getReAccountPwd() {
        return reAccountPwd;
    }

    public void setReAccountPwd(String reAccountPwd) {
        this.reAccountPwd = reAccountPwd;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

