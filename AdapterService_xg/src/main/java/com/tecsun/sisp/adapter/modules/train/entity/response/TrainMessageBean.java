package com.tecsun.sisp.adapter.modules.train.entity.response;

/**
 * Created by xumaohao on 2017/8/28.
 */
public class TrainMessageBean {
    private long trainId;       //培训序号
    private String profession;  //职业（工种）
    private String grade;       //级别
    private String classTime;   //开班时间
    private String pubDate;     //发布时间
    private String periods;     //期数
    private String organName;   //机构名称
    private String isApply = "报名";     //是否已报名


    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public String getIsApply() {
        return isApply;
    }

    public void setIsApply(String isApply) {
        this.isApply = isApply;
    }
}
