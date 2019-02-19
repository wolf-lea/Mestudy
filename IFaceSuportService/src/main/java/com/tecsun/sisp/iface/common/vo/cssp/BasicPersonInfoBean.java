package com.tecsun.sisp.iface.common.vo.cssp;

import java.util.Date;

/**
 * Created by hhl on 2016/7/18.
 */
public class BasicPersonInfoBean {
    private long id;
    private String name;
    private String sex;
    private String certNum;//cert_num
    private String cert_type;
    private String nation;
    private String birthday;
    private String photo_url;
    private String address;
    private String mobile;
    private String email;
    private String preferred_bank;
    private String phone;
    private String status;
    private String persontype;//人员类别 1：城镇职工 2：城镇居民 3：农村居民 9：其他
    private String person_status;//人员状态 1:就业 2:退休 3: 离休 4:待业 5:无业 6:从未就业 7:务农 8:退职 9:失业 10:就读 11:外出务工 99:其他
    private String account_proties;//户口性质 1:本省农业 2:本省非农业 3:外省农业 4:外省非农业 5:本省居民 6:外省居民 7:港澳台 8:外籍
    private String tsbaddress;//申领人通讯地址(德生宝所在地地址)
    private String zipcode;//邮编
    private String company_name;//单位名称
    private String company_no;//单位编号
    private Date addDate;//申领时间

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getCert_type() {
        return cert_type;
    }

    public void setCert_type(String cert_type) {
        this.cert_type = cert_type;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferred_bank() {
        return preferred_bank;
    }

    public void setPreferred_bank(String preferred_bank) {
        this.preferred_bank = preferred_bank;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPersontype() {
        return persontype;
    }

    public void setPersontype(String persontype) {
        this.persontype = persontype;
    }

    public String getPerson_status() {
        return person_status;
    }

    public void setPerson_status(String person_status) {
        this.person_status = person_status;
    }

    public String getAccount_proties() {
        return account_proties;
    }

    public void setAccount_proties(String account_proties) {
        this.account_proties = account_proties;
    }

    public String getTsbaddress() {
        return tsbaddress;
    }

    public void setTsbaddress(String tsbaddress) {
        this.tsbaddress = tsbaddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_no() {
        return company_no;
    }

    public void setCompany_no(String company_no) {
        this.company_no = company_no;
    }
}
