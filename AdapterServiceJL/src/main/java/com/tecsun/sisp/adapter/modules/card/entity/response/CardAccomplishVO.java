package com.tecsun.sisp.adapter.modules.card.entity.response;

/**
 * Created by xumaohao on 2017/10/16.
 */
public class CardAccomplishVO {
    private String xm;                  //姓名
    private String sfzh;                //身份证号码
    private String addrType;            //领卡方式：1：快递邮寄；2：到网点领取；3：德生宝所在地领取
    private String cardAddress;         //领卡地址（邮寄地址、网点地址）
    private String cardAddressShort;    //地址简称或网点
    private String addrPhone;           //收件人手机号
    private String addressee;           //收件人姓名
    private String expressage;          //快递公司

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    public String getCardAddress() {
        return cardAddress;
    }

    public void setCardAddress(String cardAddress) {
        this.cardAddress = cardAddress;
    }

    public String getCardAddressShort() {
        return cardAddressShort;
    }

    public void setCardAddressShort(String cardAddressShort) {
        this.cardAddressShort = cardAddressShort;
    }

    public String getAddrPhone() {
        return addrPhone;
    }

    public void setAddrPhone(String addrPhone) {
        this.addrPhone = addrPhone;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getExpressage() {
        return expressage;
    }

    public void setExpressage(String expressage) {
        this.expressage = expressage;
    }
}
