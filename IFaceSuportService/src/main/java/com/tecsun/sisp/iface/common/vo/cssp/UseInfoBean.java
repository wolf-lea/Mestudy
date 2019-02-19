package com.tecsun.sisp.iface.common.vo.cssp;


/**
 * Created by hhl on 2016/6/16.
 */
public class UseInfoBean {

   private Integer orgId;
   private Integer csspBankId;
    private  String devcode;

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
