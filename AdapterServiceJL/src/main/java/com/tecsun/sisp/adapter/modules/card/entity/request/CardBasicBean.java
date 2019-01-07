package com.tecsun.sisp.adapter.modules.card.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**卡基础数据
 * Created by danmeng on 2017/8/9.
 */
public class CardBasicBean extends SecQueryBean {
    private String sbkh; // 社保卡号
    private String markNo; // 卡识别码
    private String issueDistrict; // 发卡地行政区域代码
    private Object ext1;//备用1
    private Object ext2;//备用2
    private Object ext3;//备用3

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getMarkNo() {
        return markNo;
    }

    public void setMarkNo(String markNo) {
        this.markNo = markNo;
    }

    public String getIssueDistrict() {
        return issueDistrict;
    }

    public void setIssueDistrict(String issueDistrict) {
        this.issueDistrict = issueDistrict;
    }

    public Object getExt1() {
        return ext1;
    }

    public void setExt1(Object ext1) {
        this.ext1 = ext1;
    }

    public Object getExt2() {
        return ext2;
    }

    public void setExt2(Object ext2) {
        this.ext2 = ext2;
    }

    public Object getExt3() {
        return ext3;
    }

    public void setExt3(Object ext3) {
        this.ext3 = ext3;
    }
}
