package com.tecsun.sisp.adapter.modules.fairJob.entity.response;

/**申请职位类
 * Created by Administrator on 2017/11/2 0002.
 */
public class ApplyJobVO {
    private String id;
    private String sfzh;                 //身份证号
    private String name;                 //岗位名称
    private String jobId;               //岗位id
    private String coName;               //企业名称
    private String salaryMin;            //最低薪资
    private String salaryMax;            //最高薪资
    private String applyTime;           //申请时间
    private String areaId;               //工作地点
    private String education;            //学历要求
    private String educationName;
    private String workingSeniorityName;
    private String workingSeniority;     //工作年限
    private String jobProperty;
    private String areaIdName;
    private String applyChannel;        //申请渠道
    private String type;            //简历状态1、未阅读2、已阅读
    private String typeName;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getApplyChannel() {
        return applyChannel;
    }

    public void setApplyChannel(String applyChannel) {
        this.applyChannel = applyChannel;
    }

    public String getAreaIdName() {
        return areaIdName;
    }

    public void setAreaIdName(String areaIdName) {
        this.areaIdName = areaIdName;
    }
    public String getJobProperty() {
        return jobProperty;
    }

    public void setJobProperty(String jobProperty) {
        this.jobProperty = jobProperty;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
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
}
