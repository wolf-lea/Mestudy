package com.tecsun.sisp.adapter.modules.card.entity.response;

import sun.misc.BASE64Encoder;

/**
 * 公安库图片
 */
public class CsspPolicePhotoVO {

    private String sfzh;//身份证号码
    private String xm;//姓名
    private String photo;//base64 编码图片

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
        this.xm =xm;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
       
    }
}
