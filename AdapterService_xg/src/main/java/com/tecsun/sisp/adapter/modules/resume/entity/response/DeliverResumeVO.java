package com.tecsun.sisp.adapter.modules.resume.entity.response;

/**投递简历
 * Created by Administrator on 2017/11/20 0020.
 */
public class DeliverResumeVO{
    private String id;
    private String resumeId;  //简历id
    private String jobId; //职位id
    private String deliverChannle; //投递渠道1、手机APP 2、德生宝 3、微信 4、支付宝生活号 5、大终端
    private String deliverChannleName;
    private String deliverTime;  // 投递时间
    private String type;  //简历状态1、未阅读2、已阅读
    private String typeName;
    private String name;
    private String education;
    private String educationName;
    private String workingSeniority;
    private String workingSeniorityName;
    private String phone;
    private String sfzh;

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorkingSeniority() {
        return workingSeniority;
    }

    public void setWorkingSeniority(String workingSeniority) {
        this.workingSeniority = workingSeniority;
    }

    public String getWorkingSeniorityName() {
        return workingSeniorityName;
    }

    public void setWorkingSeniorityName(String workingSeniorityName) {
        this.workingSeniorityName = workingSeniorityName;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliverChannleName() {
        return deliverChannleName;
    }

    public void setDeliverChannleName(String deliverChannleName) {
        this.deliverChannleName = deliverChannleName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getDeliverChannle() {
        return deliverChannle;
    }

    public void setDeliverChannle(String deliverChannle) {
        this.deliverChannle = deliverChannle;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
