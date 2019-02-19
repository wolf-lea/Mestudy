package com.tecsun.sisp.npm.bean;

import bee.cloud.engine.db.Doe;
import bee.cloud.engine.db.annotation.Column;
import bee.cloud.engine.db.annotation.TableName;
import bee.cloud.engine.db.core.Table;

/**
 * created by fuweifeng on 2015-5-31.
 */
@TableName(name = "t_az01b")
public class NetUserInfoVO extends Table {
    public NetUserInfoVO(Doe doe) {
        super(doe);
    }

    public static final String CITYNO = "cityno";
    @Column(name = CITYNO)
    private String cityno;// 城市代码

    public String getCityno() {
        return cityno;
    }

    public void setCityno(String cityno) {
        this.cityno = cityno;
    }

    public static final String GRBH = "grbh";
    @Column(name = GRBH)
    private String grbh;// 个人编号

    public static final String CARDNO = "cardno";
    @Column(name = CARDNO)
    private String cardno;// 社保卡号

    public static final String NAME = "name";
    @Column(name = NAME)
    private String name;// 姓名

    public static final String CERNUM = "cernum";
    @Column(name = CERNUM, pk = true)
    private String cernum;// 身份证号

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCernum() {
        return cernum;
    }

    public void setCernum(String cernum) {
        this.cernum = cernum;
    }

    public static final String MOBILE_ADDRESS = "mobile_address";
    @Column(name = MOBILE_ADDRESS)
    private String mobile_address;// 通讯地址

    public static final String ADDRESS = "address";
    @Column(name = ADDRESS)
    private String address;// 户籍所在地

    public static final String MOBILE = "mobile";
    @Column(name = MOBILE)
    private String mobile;// 手机号码

    public static final String PHONE = "phone";
    @Column(name = PHONE)
    private String phone;// 联系电话

    public static final String UNITNO = "unitno";
    @Column(name = UNITNO)
    private String unitno;// 单位编号

    public static final String UNITNAME = "unitname";
    @Column(name = UNITNAME)
    private String unitname;// 单位名称


    public static final String IS_UPDATE = "is_update";
    @Column(name = IS_UPDATE)
    private String is_update;// 是否修改

    public String getIs_update() {
        return is_update;
    }

    public void setIs_update(String is_update) {
        this.is_update = is_update;
    }

    public String getMobile_address() {
        return mobile_address;
    }

    public void setMobile_address(String mobile_address) {
        this.mobile_address = mobile_address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUnitno() {
        return unitno;
    }

    public void setUnitno(String unitno) {
        this.unitno = unitno;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public static final String BIRTHDAY = "birthday";
    @Column(name = BIRTHDAY)
    private String birthday;// 出生日期

    public static final String NET_PASSWORD = "net_password";
    @Column(name = NET_PASSWORD)
    private String net_password;// 登录密码

    public static final String NATION = "nation";
    @Column(name = NATION)
    private String nation;// 民族

    public static final String SEX = "sex";
    @Column(name = SEX)
    private String sex;// 性别

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNet_password() {
        return net_password;
    }

    public void setNet_password(String net_password) {
        this.net_password = net_password;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static final String GJ = "gj";
    @Column(name = GJ)
    private String gj;// 国籍

    public static final String CARD_STATUS = "card_status";
    @Column(name = CARD_STATUS)
    private String card_status;// 社保卡状态

    public static final String FKRQ = "fkrq";
    @Column(name = FKRQ)
    private String fkrq;// 发卡日期

    public static final String YXQZ = "yxqz";
    @Column(name = YXQZ)
    private String yxqz;// 有效期至

    public static final String BANKID = "bankid";
    @Column(name = BANKID)
    private String bankid;// 开户银行

    public static final String WD_BANK = "wd_bank";
    @Column(name = WD_BANK)
    private String wd_bank;// 银行网点

    public static final String BANKNO = "bankno";
    @Column(name = BANKNO)
    private String bankno;// 银行卡号

    public static final String EMAIL = "email";
    @Column(name = EMAIL)
    private String email;// 邮箱地址

    public static final String REMARK = "remark";
    @Column(name = REMARK)
    private String remark;// 备注

    public static final String PERSON_STATUS = "person_status";
    @Column(name = PERSON_STATUS)
    private String person_status;// 人员状态

    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj;
    }

    public String getCard_status() {
        return card_status;
    }

    public void setCard_status(String card_status) {
        this.card_status = card_status;
    }

    public String getFkrq() {
        return fkrq;
    }

    public void setFkrq(String fkrq) {
        this.fkrq = fkrq;
    }

    public String getYxqz() {
        return yxqz;
    }

    public void setYxqz(String yxqz) {
        this.yxqz = yxqz;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getWd_bank() {
        return wd_bank;
    }

    public void setWd_bank(String wd_bank) {
        this.wd_bank = wd_bank;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPerson_status() {
        return person_status;
    }

    public void setPerson_status(String person_status) {
        this.person_status = person_status;
    }

}
