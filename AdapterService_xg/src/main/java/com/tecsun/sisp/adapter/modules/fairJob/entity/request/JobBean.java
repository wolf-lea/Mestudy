package com.tecsun.sisp.adapter.modules.fairJob.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**企业职位类
 * Created by Administrator on 2017/11/2 0002.
 */
public class JobBean extends SecQueryBean {
    private String id;                   //工作岗位id
    private String name;                 //岗位名称
    private String coId;                 //企业id
    private String coName;               //企业名称
    private String salaryMin;            //最低薪资
    private List<String> salary;

    private String salaryMax;            //最高薪资
    private String updateTime;           //发布时间
    //private List<String> updateTimeList;
    private String areaId;               //工作地点
    private String area;
    private String industry;             //职能类别id
    private List<String> industryList;
    private String certificate;          //证书要求
    private String education;            //学历要求
    private String language;
    private List<String> educationList;
    private String workingSeniority;     //工作年限
    private List<String> workingSeniorityList;
    private String address;              //公司地址
    private String jobTypes;             //职位类型
    private String jobProperty;          //工作性质
    private List<String> jobPropertyList;   //
    private String hireNum;                 //招聘人数
    private String tel;
    private String phone;
    private String contact;
    private String summary;
    private String welfare;
    private String publishChannel;
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private String jobStrengths;

    public String getJobStrengths() {
        return jobStrengths;
    }

    public void setJobStrengths(String jobStrengths) {
        this.jobStrengths = jobStrengths;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getHireNum() {
        return hireNum;
    }

    public void setHireNum(String hireNum) {
        this.hireNum = hireNum;
    }

    public List<String> getSalary() {
        return salary;
    }

    public void setSalary(List<String> salary) {
        this.salary = salary;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<String> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List<String> industryList) {
        this.industryList = industryList;
    }

    public List<String> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<String> educationList) {
        this.educationList = educationList;
    }

    public List<String> getWorkingSeniorityList() {
        return workingSeniorityList;
    }

    public void setWorkingSeniorityList(List<String> workingSeniorityList) {
        this.workingSeniorityList = workingSeniorityList;
    }

    public String getPublishChannel() {
        return publishChannel;
    }

    public void setPublishChannel(String publishChannel) {
        this.publishChannel = publishChannel;
    }

    public List<String> getJobPropertyList() {
        return jobPropertyList;
    }

    public void setJobPropertyList(List<String> jobPropertyList) {
        this.jobPropertyList = jobPropertyList;
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
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        if("0".equals(updateTime)) return "";
        if("1".equals(updateTime)){
            rightNow.add(Calendar.DAY_OF_MONTH,0);
        }else if("2".equals(updateTime)) {
            rightNow.add(Calendar.DAY_OF_MONTH,-10);
        }else if("3".equals(updateTime)) {
            rightNow.add(Calendar.MONTH,-1);
        }else if("4".equals(updateTime)) {
            rightNow.add(Calendar.MONTH,-2);
        }else if("5".equals(updateTime)) {
            rightNow.add(Calendar.MONTH,-3);
        }else{
            return null;
        }
        return df.format(rightNow.getTime());
    }
    public String returnUpdateTime2(){
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(String jobTypes) {
        this.jobTypes = jobTypes;
    }

    public String getJobProperty() {
        return jobProperty;
    }

    public void setJobProperty(String jobProperty) {
        this.jobProperty = jobProperty;
    }

}
