package com.tecsun.sisp.adapter.modules.resume.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**简历
 * Created by Administrator on 2017/10/31 0031.
 */
public class ResumeBean extends SecQueryBean{
    private String id;
    private String education;  //最高学历
    private String workingSeniority;       //工作年限
    private String address;         //所在地址
    private String salaryMin;          //最低薪资
    private String salaryMax;       // 最高薪资
    private String jobName;         //职位

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

   /* private BasicMsgVO basicMsgVo;
    private List<WorkExpVO> workExpBeanList;          //工作经历
    private List<ProExpVO> proExpBeanList;            //项目经历
    private List<EduExpVO> eduExpBeanList;            //教育经历
    private ExceptJobVO exceptJobVo;                //期望工作
    private String createTime;                          //简历创建时间
    private String updateTime;                          //简历修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public List<WorkExpVO> getWorkExpBeanList() {
        return workExpBeanList;
    }

    public void setWorkExpBeanList(List<WorkExpVO> workExpBeanList) {
        this.workExpBeanList = workExpBeanList;
    }

    public List<ProExpVO> getProExpBeanList() {
        return proExpBeanList;
    }

    public void setProExpBeanList(List<ProExpVO> proExpBeanList) {
        this.proExpBeanList = proExpBeanList;
    }

    public List<EduExpVO> getEduExpBeanList() {
        return eduExpBeanList;
    }

    public void setEduExpBeanList(List<EduExpVO> eduExpBeanList) {
        this.eduExpBeanList = eduExpBeanList;
    }

    public BasicMsgVO getBasicMsgVo() {
        return basicMsgVo;
    }

    public void setBasicMsgVo(BasicMsgVO basicMsgVo) {
        this.basicMsgVo = basicMsgVo;
    }

    public ExceptJobVO getExceptJobVo() {
        return exceptJobVo;
    }

    public void setExceptJobVo(ExceptJobVO exceptJobVo) {
        this.exceptJobVo = exceptJobVo;
    }*/
}
