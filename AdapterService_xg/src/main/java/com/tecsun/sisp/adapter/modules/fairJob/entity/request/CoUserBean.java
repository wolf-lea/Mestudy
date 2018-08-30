package com.tecsun.sisp.adapter.modules.fairJob.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**企业用户类
 * Created by Administrator on 2017/11/2 0002.
 */
public class CoUserBean extends SecQueryBean {
    private String id;              //企业id
    private String logName;         //用户名
    private String password;        //密码
    private String name;            //公司名称
    private String industry;         //行业类别
    private String scale;           //公司规模
    private String email;           //电子邮箱
    private String contact;         //联系人
    private String phone;           //手机号
    private String tel;             //公司电话
    private String fax;             //传真
    private String address;         //公司详细地址
    private String summary;         //公司简介
    private String licence;         //营业执照
    private String status;          //状态
    private String logo;            //公司logo
    private String checkMsg;        //审核意见
    private String licenseB64;      //转为BASE64编码的图片         
    private String createTime;      //创建时间
    private String updateTime;      //修改时间
    private long userId; //关联sisp_public的t_user表

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLicenseB64() {
        return licenseB64;
    }

    public void setLicenseB64(String licenseB64) {
        this.licenseB64 = licenseB64;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCheckMsg() {
        return checkMsg;
    }

    public void setCheckMsg(String checkMsg) {
        this.checkMsg = checkMsg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
    
}
