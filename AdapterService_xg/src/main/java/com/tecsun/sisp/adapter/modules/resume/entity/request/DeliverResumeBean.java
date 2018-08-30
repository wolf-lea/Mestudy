package com.tecsun.sisp.adapter.modules.resume.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**投递简历
 * Created by Administrator on 2017/11/20 0020.
 */
public class DeliverResumeBean extends SecQueryBean {
    private String id;
    private String resumeId;  //简历id
    private String jobId; //职位id
    private String deliverChannle; //投递时间
    private String deliverTime;  // 投递渠道
    private String type;  //简历状态1、未阅读2、已阅读

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
