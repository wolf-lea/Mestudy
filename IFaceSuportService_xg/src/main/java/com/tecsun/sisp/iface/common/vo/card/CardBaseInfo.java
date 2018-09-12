package com.tecsun.sisp.iface.common.vo.card;

/**
 * 卡基础数据（不包含个人照片）
 * Created by jerry on 2015/5/30.
 */
public class CardBaseInfo {
    private String err;//是否错误
    private String restime;//操作时间
    private String sbkh;//社保卡号
    private String sfzh;//身份证号码
    private String name;//姓名
    private String sex;//性别
    private String ethnic;//名族
    private String birthday;//出生日期
    private String personstatus;//人员状态
    private String registtype;//户口性质
    private String regaddr;//户口地址
    private String telno;//电话
    private String mailaddr;//通讯地址
    private String zipcode;//邮编
    private String bank;//银行
    private String bankno;//银行卡号
    private String phoneno;//手机
    private String email;//邮箱
    private String dwbh;//单位编号
    private String dwmc;//单位名称
    
   
	public CardBaseInfo() {
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getRestime() {
        return restime;
    }

    public void setRestime(String restime) {
        this.restime = restime;
    }

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPersonstatus() {
        return personstatus;
    }

    public void setPersonstatus(String personstatus) {
        this.personstatus = personstatus;
    }

    public String getRegisttype() {
        return registtype;
    }

    public void setRegisttype(String registtype) {
        this.registtype = registtype;
    }

    public String getRegaddr() {
        return regaddr;
    }

    public void setRegaddr(String regaddr) {
        this.regaddr = regaddr;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getMailaddr() {
        return mailaddr;
    }

    public void setMailaddr(String mailaddr) {
        this.mailaddr = mailaddr;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }
}
