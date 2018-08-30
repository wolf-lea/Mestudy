package com.tecsun.sisp.adapter.modules.resume.entity.response;

/**期望工作
 * Created by Administrator on 2017/10/31 0031.
 */
public class ExceptJobVO {
    private String id;
    private String sfzh;        //简历sfzh
    private String jobName;         //职位
    private String types;           //类型1、全职 2、实习 3、兼职
    private String typesName;
    private String address;         //期望城市
    private String salary;          //期望薪资
    private String salaryName;
    private String summary;         //补充说明
    private String style;           //求职状态 1、在职 2、离职
    private String styleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypesName() {
        return typesName;
    }

    public void setTypesName(String typesName) {
        this.typesName = typesName;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getSalaryName() {
        return salaryName;
    }

    public void setSalaryName(String salaryName) {
        this.salaryName = salaryName;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStyle() { return style; }

    public void setStyle(String style) { this.style = style; }
}
