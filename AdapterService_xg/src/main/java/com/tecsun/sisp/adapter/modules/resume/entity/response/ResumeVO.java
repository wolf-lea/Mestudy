package com.tecsun.sisp.adapter.modules.resume.entity.response;

import java.util.List;

/**个人简历
 * Created by Administrator on 2017/10/31 0031.
 */
public class ResumeVO {
    private String id;
    private BasicMsgVO basicMsgVo;
    private List<WorkExpVO> workExpBeanList;          //工作经历
    private List<ProExpVO> proExpBeanList;            //项目经历
    private List<EduExpVO> eduExpBeanList;            //教育经历
    private ExceptJobVO exceptJobVo;                //期望工作
    private String createTime;                          //简历创建时间
    private String updateTime;                          //简历修改时间
    private String summary;         //自我描述
    private String skill;           //技能特长
    private String prizes;          //获奖情况

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getPrizes() {
        return prizes;
    }

    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }


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
    }
}
