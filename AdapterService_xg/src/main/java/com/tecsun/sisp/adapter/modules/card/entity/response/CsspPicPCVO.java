package com.tecsun.sisp.adapter.modules.card.entity.response;

/**
 * Created by danmeng on 2016/9/12.
 */
public class CsspPicPCVO {
    private String picName;
    private String base64String;
    private String isCheck;

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getBase64String() {
        return base64String;
    }

    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }
}
