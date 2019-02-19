package com.tecsun.sisp.iface.common.vo.cssp;

import java.util.Date;

/**
 * 领卡照片实体入参
 * Created by liu on 2016/8/9.
 */
public class BusApplyPicVO {
    private String agentName; //代领人姓名
    private String name;//本人姓名
    private String certNum;//本人身份证号码
    private String agentcertNum;//代办人身份证号码
    private String photoName;//本人照片
    private String signatrueName;//本人签名照片
    private String soCardNum;
    private  String orgid;
    private  String soname;
    private Date applytime;

    public Date getApplytime() {

        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    public String getSoname() {
        return soname;
    }

    public void setSoname(String soname) {
        this.soname = soname;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getSoCardNum() {
        return soCardNum;
    }

    public void setSoCardNum(String soCardNum) {
        this.soCardNum = soCardNum;
    }


    /*  ===========数据库不插入======*/
    private String no; //0:本人 ,1:他人
    private String base64String;//64位的相片
    private  String base64Stringqmz;//64位的签名


    private String rphotoPath;
    private  String qmphotoPath;

    public String getAgentcertNum() {
        return agentcertNum;
    }

    public void setAgentcertNum(String agentcertNum) {
        this.agentcertNum = agentcertNum;
    }

    public String getRphotoPath() {
        return rphotoPath;
    }

    public void setRphotoPath(String rphotoPath) {
        this.rphotoPath = rphotoPath;
    }

    public String getQmphotoPath() {
        return qmphotoPath;
    }

    public void setQmphotoPath(String qmphotoPath) {
        this.qmphotoPath = qmphotoPath;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getSignatrueName() {
        return signatrueName;
    }

    public void setSignatrueName(String signatrueName) {
        this.signatrueName = signatrueName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getBase64String() {
        return base64String;
    }

    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }

    public String getBase64Stringqmz() {
        return base64Stringqmz;
    }

    public void setBase64Stringqmz(String base64Stringqmz) {
        this.base64Stringqmz = base64Stringqmz;
    }
}
