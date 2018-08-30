package com.tecsun.sisp.adapter.modules.card.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by xumaohao on 2017/10/10.
 */
public class CardGetBean extends SecQueryBean {

    private String phone;           //联系方式
    private String addrOld;         //卡原位置
    private String addrNew;         //现选择的网点或邮寄地址
    private String addrType;        //领卡方式(1：快递邮寄；2：到网点领取)


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddrOld() {
        return addrOld;
    }

    public void setAddrOld(String addrOld) {
        this.addrOld = addrOld;
    }

    public String getAddrNew() {
        return addrNew;
    }

    public void setAddrNew(String addrNew) {
        this.addrNew = addrNew;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }
}
