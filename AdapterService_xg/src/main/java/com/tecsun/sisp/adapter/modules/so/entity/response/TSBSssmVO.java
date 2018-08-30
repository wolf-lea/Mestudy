package com.tecsun.sisp.adapter.modules.so.entity.response;

/**德生宝和社保网点关联信息
 * Created by danmeng on 2017/5/12.
 */
public class TSBSssmVO {
    private Integer orgId;//社保网点id
    private Integer csspBankId;//社保银行机构id
    private  String devcode;//设备编码（区域代码+四位数的编号）
    private  String orgName;//社保机构名称
    private  String orgAddress;//社保机构地址
    private  String cardmaxsize;//每盒装卡数量

    public String getCardmaxsize() {
        return cardmaxsize;
    }

    public void setCardmaxsize(String cardmaxsize) {
        this.cardmaxsize = cardmaxsize;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getDevcode() {
        return devcode;
    }

    public void setDevcode(String devcode) {
        this.devcode = devcode;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getCsspBankId() {
        return csspBankId;
    }

    public void setCsspBankId(Integer csspBankId) {
        this.csspBankId = csspBankId;
    }
}
