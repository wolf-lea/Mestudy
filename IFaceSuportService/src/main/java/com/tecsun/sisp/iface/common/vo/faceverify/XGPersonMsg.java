package com.tecsun.sisp.iface.common.vo.faceverify;

/**
 * Created by Sandwich on 2015/12/11.
 */
public class XGPersonMsg {
    private String name;
    private String sex;
    private String photo;
    private String idCard;
    private String xg_token;
    private String sys_token;
    private String addr;
    private String company;
    private String aac001;//个人编号
    private String aab001;//单位编号

    public String getAab001() {
        return aab001;
    }

    public void setAab001(String aab001) {
        this.aab001 = aab001;
    }

    public String getAac001() {
        return aac001;
    }

    public void setAac001(String aac001) {
        this.aac001 = aac001;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSys_token() {
        return sys_token;
    }

    public void setSys_token(String sys_token) {
        this.sys_token = sys_token;
    }

    public String getXg_token() {
        return xg_token;
    }

    public void setXg_token(String xg_token) {
        this.xg_token = xg_token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if (sex == null) {
            this.sex = "";
        }else{
            this.sex = sex;
            if (sex.equals("01"))
                this.sex = "男";
            if (sex.equals("02")){
                this.sex = "女";
            }
        }
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
