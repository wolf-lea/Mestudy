package com.tecsun.sisp.iface.common.vo.faceverify;

/**
 * Created by Sandwich on 2015/12/12.
 */
public class UploadPhoto {
    private String base64String1;
    private String base64String2;
    private String base64String3;
    private String idCard;
    private String name;
    private String token;
    private String justCheck;//00只人工，01人工+人脸
    private String aac001;
    private String aab001;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase64String1() {
        return base64String1;
    }

    public void setBase64String1(String base64String1) {
        this.base64String1 = base64String1;
    }

    public String getBase64String2() {
        return base64String2;
    }

    public void setBase64String2(String base64String2) {
        this.base64String2 = base64String2;
    }

    public String getBase64String3() {
        return base64String3;
    }

    public void setBase64String3(String base64String3) {
        this.base64String3 = base64String3;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJustCheck() {
        return justCheck;
    }

    public void setJustCheck(String justCheck) {
        this.justCheck = justCheck;
    }

    public String getAac001() {
        return aac001;
    }

    public void setAac001(String aac001) {
        this.aac001 = aac001;
    }

    public String getAab001() {
        return aab001;
    }

    public void setAab001(String aab001) {
        this.aab001 = aab001;
    }
}
