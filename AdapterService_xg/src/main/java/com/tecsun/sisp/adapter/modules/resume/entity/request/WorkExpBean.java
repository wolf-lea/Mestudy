package com.tecsun.sisp.adapter.modules.resume.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**工作经历类
 * Created by Administrator on 2017/10/31 0031.
 */
public class WorkExpBean extends SecQueryBean {
    private String id;
    private String resumeId;        //简历id
    private String name;            //公司名称
    private String position;        //您的职位
    private String beginTime;       //入职时间
    private String endTime;         //离职时间
    private String proSummary;      //工作内容

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getProSummary() {
        return proSummary;
    }

    public void setProSummary(String proSummary) {
        this.proSummary = proSummary;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
