package com.tecsun.sisp.adapter.modules.fairJob.entity.response;

/**职位收藏类
 * Created by Administrator on 2017/11/2 0002.
 */
public class CollectJobVO {
    private String id;
    private String sfzh;                 //身份证号
    private String name;                 //岗位名称
    private String jobId;               //岗位id
    private String coName;               //企业名称
    private String salaryMin;            //最低薪资
    private String salaryMax;            //最高薪资
    private String collectTime;         //收藏时间
    private String areaId;               //工作地点

    private String education;            //学历要求
    private String educationName;
    private String workingSeniorityName;
    private String workingSeniority;     //工作年限
    private String jobProperty;
    private String areaIdName;

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

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
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
