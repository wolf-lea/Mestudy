package com.tecsun.sisp.adapter.modules.resume.entity.response;

/**项目经验
 * Created by Administrator on 2017/10/31 0031.
 */
public class ProExpVO {
    private String id;
    private String resumeId;        //简历id
    private String name;            //项目名称
    private String position;        //所在职位
    private String beginTime;       //项目开始时间
    private String endTime;         //项目结束时间
    private String proSummary;      //项目简介

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProSummary() {
        return proSummary;
    }

    public void setProSummary(String proSummary) {
        this.proSummary = proSummary;
    }
}
