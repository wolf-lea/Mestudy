package com.tecsun.sisp.iface.common.vo.version.result;

import java.util.Date;

/**
 * Created by huanghailiang on 15-12-29.
 */
public class MerchantPo {
    private Long id;
    private Integer areaId;
    private Integer custId;//客户ID
    private Long apId;//维护公司id
    private String regname;//商户注册名
    private String location;//商户所在地
    private String shopName;//商户门头名称
    private String landmanagement;//经营用地性质
    private String businesslicense;//营业执照号
    private String representative;//法人代表
    private String representativeCert;//法人证件号码
    private String representativeContact;//法人联系方式
    private String taxregistration;//税务登记号
    private String maincontact;//主要联系人
    private String maincontactCert;//主要联系人证件号
    private String maincontactPhone;//主要联系人电话
    private String type;//商户类型
    private String property;//商户性质
    private Date createTime;
    private String createUser;
    private Date modTime;
    private String modUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Long getApId() {
        return apId;
    }

    public void setApId(Long apId) {
        this.apId = apId;
    }

    public String getRegname() {
        return regname;
    }

    public void setRegname(String regname) {
        this.regname = regname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLandmanagement() {
        return landmanagement;
    }

    public void setLandmanagement(String landmanagement) {
        this.landmanagement = landmanagement;
    }

    public String getBusinesslicense() {
        return businesslicense;
    }

    public void setBusinesslicense(String businesslicense) {
        this.businesslicense = businesslicense;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getRepresentativeCert() {
        return representativeCert;
    }

    public void setRepresentativeCert(String representativeCert) {
        this.representativeCert = representativeCert;
    }

    public String getRepresentativeContact() {
        return representativeContact;
    }

    public void setRepresentativeContact(String representativeContact) {
        this.representativeContact = representativeContact;
    }

    public String getTaxregistration() {
        return taxregistration;
    }

    public void setTaxregistration(String taxregistration) {
        this.taxregistration = taxregistration;
    }

    public String getMaincontact() {
        return maincontact;
    }

    public void setMaincontact(String maincontact) {
        this.maincontact = maincontact;
    }

    public String getMaincontactCert() {
        return maincontactCert;
    }

    public void setMaincontactCert(String maincontactCert) {
        this.maincontactCert = maincontactCert;
    }

    public String getMaincontactPhone() {
        return maincontactPhone;
    }

    public void setMaincontactPhone(String maincontactPhone) {
        this.maincontactPhone = maincontactPhone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public String getModUser() {
        return modUser;
    }

    public void setModUser(String modUser) {
        this.modUser = modUser;
    }
}
