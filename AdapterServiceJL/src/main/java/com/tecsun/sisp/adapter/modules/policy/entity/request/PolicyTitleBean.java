package com.tecsun.sisp.adapter.modules.policy.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by danmeng on 2017/7/10.
 */
public class PolicyTitleBean extends SecQueryBean{
    private String policyType;//政策类型
    private String policyID;//政策ID
    private String pulishStartDate;//发布时间开始(yyyy-mm-dd hh24:mi:ss)
    private String pulishEndDate;//发布时间结束(yyyy-MM-dd hh24:mi:ss)
    private String ext1;//备用1
    private String ext2;//备用2
    private String ext3;//备用3

    public String getPolicyID() {
        return policyID;
    }

    public void setPolicyID(String policyID) {
        this.policyID = policyID;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getPulishStartDate() {
        return pulishStartDate;
    }

    public void setPulishStartDate(String pulishStartDate) {
        this.pulishStartDate = pulishStartDate;
    }

    public String getPulishEndDate() {
        return pulishEndDate;
    }

    public void setPulishEndDate(String pulishEndDate) {
        this.pulishEndDate = pulishEndDate;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}
