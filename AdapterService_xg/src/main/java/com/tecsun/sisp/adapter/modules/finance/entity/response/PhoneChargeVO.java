package com.tecsun.sisp.adapter.modules.finance.entity.response;

import java.util.Date;

/**手机充值业务记录表
 * Created by danmeng on 2017/6/6.
 */
public class PhoneChargeVO {
    private long tradeId;//T_YTH_FINANCE_TRADE主键
    public String sfzh;//身份证
    public String xm;//姓名
    public String sbkh;//社保卡号
    public String phone;//手机号码
    public Date createTime;//创建时间

    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
