package com.tecsun.sisp.adapter.modules.resume.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**教育经历
 * Created by Administrator on 2017/10/31 0031.
 */
public class EduExpBean extends SecQueryBean{
    private String id;
    private String resumeId;        //关联的简历id
    private String name;            //学校名称
    private String major;           //所学专业
    private String graduateTime;    //毕业时间
    private String education;       //学历

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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGraduateTime() {
        return graduateTime;
    }

    public void setGraduateTime(String graduateTime) {
        this.graduateTime = graduateTime;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
