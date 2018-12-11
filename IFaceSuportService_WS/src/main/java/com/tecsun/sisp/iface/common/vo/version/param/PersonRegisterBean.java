package com.tecsun.sisp.iface.common.vo.version.param;

/**
 * Created by zhuxiaokai on 16-8-16.
 */
public class PersonRegisterBean {
    private String sbCard;//社保卡号
    private String idCard;//身份证号
    private Integer sex;//性别：1、男 2、女
    private String name;//名字
    private String mobile;//手机号码
    private String code;//手机验证码

    public String getSbCard() {
        return sbCard;
    }

    public void setSbCard(String sbCard) {
        this.sbCard = sbCard;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
