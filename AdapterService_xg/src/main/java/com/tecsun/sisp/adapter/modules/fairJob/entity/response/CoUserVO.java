package com.tecsun.sisp.adapter.modules.fairJob.entity.response;

/**企业用户类
 * Created by Administrator on 2017/11/2 0002.
 */
public class CoUserVO {
    private String id;              //企业id
    private String logName;         //用户名
    private String password;        //密码
    private String name;            //公司名称
    private String industry;         //行业类别
    private String industyTemp;         //行业类别
    private String scale;           //公司规模编码
    private String scaleName;
    private String email;           //电子邮箱
    private String contact;         //联系人
    private String phone;           //手机号
    private String tel;             //公司电话
    private String fax;             //传真
    private String address;         //公司详细地址

    private String summary;         //公司简介
    private String licence;         //营业执照
    private String status;          //账号状态：0为未审核，1为审核不通过，2为启用，3为停用
    private String statusName;
    private String logo;            //公司logo
    private String createTime;      //创建时间
    private String updateTime;      //修改时间
    private String checkMsg;        //审核意见

    public String getScaleName() {
        return scaleName;
    }

    public void setScaleName(String scaleName) {
        this.scaleName = scaleName;
    }

    public String getStatusName() {
        if(this.getStatus().equals("0")){
            this.statusName="未审核";
        }else if(this.getStatus().equals("1")){
            this.statusName="审核不通过";
        }else if(this.getStatus().equals("3")){
            this.statusName="启用";
        }else if(this.getStatus().equals("2")){
            this.statusName="停用";
        }
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

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

    public String getIndustyTemp() {
        return industyTemp;
    }

    public void setIndustyTemp(String industyTemp) {
        this.industyTemp = industyTemp;
    }
}
