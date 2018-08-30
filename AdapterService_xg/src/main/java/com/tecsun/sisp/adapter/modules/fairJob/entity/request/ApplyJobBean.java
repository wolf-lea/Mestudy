package com.tecsun.sisp.adapter.modules.fairJob.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**职位申请类
 * Created by Administrator on 2017/11/2 0002.
 */
public class ApplyJobBean extends SecQueryBean{

    private String jobId;               //岗位id

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
