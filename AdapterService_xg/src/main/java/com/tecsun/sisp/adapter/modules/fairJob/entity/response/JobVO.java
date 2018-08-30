package com.tecsun.sisp.adapter.modules.fairJob.entity.response;

import java.util.List;

import static com.tecsun.sisp.adapter.common.util.CommUtil.DateUtil;

/**企业职位类
 * Created by Administrator on 2017/11/2 0002.
 */
public class JobVO {
    private String id;                   //工作岗位id
    private String name;                 //岗位名称
    private String coId;                 //企业id
    private String coName;               //企业名称
    private String salaryMin;            //最低薪资
    private String salaryMax;            //最高薪资
    private String updateTime;           //发布时间
    private String areaId;               //工作地点编码
    private String areaIdName;
    private String industry;             //职能类别id
    private String welfare;              //福利编码
    private List<String> welfareName;
    private String hireNum;              //招聘人数
    private String certificate;          //证书要求
    private String education;            //学历要求编码
    private String educationName;
    private String workingSeniority;     //工作年限编码
    private String workingSeniorityName;
    private String language;             //语言要求
    private String summary;              //职位描述
    private String address;              //公司地址
    private String contact;              //联系人
    private String tel;                  //联系电话
    private String jobTypes;             //职位类型编码1、社招2、校招
    private String jobTypesName;
    private String jobProperty;          //工作性质编码1、全职 2.兼职。3.实习。4.临时工
    private String jobStrengths;         //职位亮点
    private String publishChannel;       //发布渠道
    private String department;           //所属部门
    private String applyType;            //是否申请
    private String area;
    private String phone;
    private String coSummary;       //公司介绍
    private String collectType;
    private String url;
    private Integer noRead;          //简历的未阅读量
    private Integer lookCounts;     //浏览量

    public Integer getLookCounts() {
        return lookCounts;
    }

    public void setLookCounts(Integer lookCounts) {
        this.lookCounts = lookCounts;
    }

    public Integer getNoRead() {
        return noRead;
    }

    public void setNoRead(Integer noRead) {
        this.noRead = noRead;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    public String getCoSummary() {
        return coSummary;
    }

    public void setCoSummary(String coSummary) {
        this.coSummary = coSummary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaIdName() {
        return areaIdName;
    }

    public void setAreaIdName(String areaIdName) {
        this.areaIdName = areaIdName;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getWorkingSeniorityName() {
        return workingSeniorityName;
    }

    public void setWorkingSeniorityName(String workingSeniorityName) {
        this.workingSeniorityName = workingSeniorityName;
    }

    public String getJobTypesName() {
        return jobTypesName;
    }

    public void setJobTypesName(String jobTypesName) {
        this.jobTypesName = jobTypesName;
    }

    public List<String> getWelfareName() {
        return welfareName;
    }

    public void setWelfareName(List<String> welfareName) {
        this.welfareName = welfareName;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    public String getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(String salaryMin) {
        this.salaryMin = salaryMin;
    }

    public String getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(String salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) throws Exception {

        this.updateTime=DateUtil(updateTime);
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getHireNum() {
        return hireNum;
    }

    public void setHireNum(String hireNum) {
        this.hireNum = hireNum;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkingSeniority() {
        return workingSeniority;
    }

    public void setWorkingSeniority(String workingSeniority) {
        this.workingSeniority = workingSeniority;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(String jobTypes) {
        this.jobTypes = jobTypes;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getJobProperty() {
        return jobProperty;
    }

    public void setJobProperty(String jobProperty) {
        this.jobProperty = jobProperty;
    }

    public String getJobStrengths() {
        return jobStrengths;
    }

    public void setJobStrengths(String jobStrengths) {
        this.jobStrengths = jobStrengths;
    }

    public String getPublishChannel() {
        return publishChannel;
    }

    public void setPublishChannel(String publishChannel) {
        this.publishChannel = publishChannel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
