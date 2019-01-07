package com.tecsun.sisp.fakamanagement.modules.entity.param.replacement;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * Created by chenlicong on 2018/9/4.
 */
public class ReplaceCardBean  extends BaseVO {

    private Integer id;
    private String name;//姓名
    private String idcard;//身份证号码
    private String reginal;//所属城市
    private String cardid;//社会保障卡卡号
    private String status;//卡状态
    private String replaceType;//补卡类别
    private String replaceReason;//补卡原因
    private String bankCode;//制卡银行
    private Integer replacecardPersonId;
    private String mobile;//手机号码
    private String replacecardNo;//即制卡号
    private String rkwd;//入库网点编码

    private String district;//所属区县
    private String ks;//卡商
    private String personTotal;//人数
    private String batchNo;//批次号
    private String fkrq;//发卡日期
    private String kyxq;//卡有效期
    private String ksbm;//卡识别码
    private String atr;//卡复位码
    private String bankAccount;//银行卡号
    private String account;//个人帐户
    private String zxwz;//装箱位置
    private String failType;//失败环节(银行/制卡)
    private String failReason;//失败原因(50字节内)
    private String photoUrl;//照片路径
    private String userid;//用户id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getReginal() {
        return reginal;
    }

    public void setReginal(String reginal) {
        this.reginal = reginal;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReplaceType() {
        return replaceType;
    }

    public void setReplaceType(String replaceType) {
        this.replaceType = replaceType;
    }

    public String getReplaceReason() {
        return replaceReason;
    }

    public void setReplaceReason(String replaceReason) {
        this.replaceReason = replaceReason;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public Integer getReplacecardPersonId() {
        return replacecardPersonId;
    }

    public void setReplacecardPersonId(Integer replacecardPersonId) {
        this.replacecardPersonId = replacecardPersonId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReplacecardNo() {
        return replacecardNo;
    }

    public void setReplacecardNo(String replacecardNo) {
        this.replacecardNo = replacecardNo;
    }

    public String getRkwd() {
        return rkwd;
    }

    public void setRkwd(String rkwd) {
        this.rkwd = rkwd;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public String getPersonTotal() {
        return personTotal;
    }

    public void setPersonTotal(String personTotal) {
        this.personTotal = personTotal;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getFkrq() {
        return fkrq;
    }

    public void setFkrq(String fkrq) {
        this.fkrq = fkrq;
    }

    public String getKyxq() {
        return kyxq;
    }

    public void setKyxq(String kyxq) {
        this.kyxq = kyxq;
    }

    public String getKsbm() {
        return ksbm;
    }

    public void setKsbm(String ksbm) {
        this.ksbm = ksbm;
    }

    public String getAtr() {
        return atr;
    }

    public void setAtr(String atr) {
        this.atr = atr;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getZxwz() {
        return zxwz;
    }

    public void setZxwz(String zxwz) {
        this.zxwz = zxwz;
    }

    public String getFailType() {
        return failType;
    }

    public void setFailType(String failType) {
        this.failType = failType;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
