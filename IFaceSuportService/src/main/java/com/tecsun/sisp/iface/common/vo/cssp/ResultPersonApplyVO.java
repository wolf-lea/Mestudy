package com.tecsun.sisp.iface.common.vo.cssp;

/**
 * 个人业务出参
 * Created by liu on 2016/8/8.
 */
public class ResultPersonApplyVO {
    private  String status;
    private  String bankOpenType;
    private  String channel;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankOpenType() {
        return bankOpenType;
    }

    public void setBankOpenType(String bankOpenType) {
        this.bankOpenType = bankOpenType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }


    private  String regisStatus;

    public String getRegisStatus() {

        return regisStatus;
    }

    public void setRegisStatus(String regisStatus) {
        this.regisStatus = regisStatus;
    }

    private String name;
    private String certNum;
    private String addDate;
    private String TsbAddress;

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

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getTsbAddress() {
        return TsbAddress;
    }

    public void setTsbAddress(String tsbAddress) {
        TsbAddress = tsbAddress;
    }
}
