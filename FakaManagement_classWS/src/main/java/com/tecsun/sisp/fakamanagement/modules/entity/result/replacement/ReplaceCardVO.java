package com.tecsun.sisp.fakamanagement.modules.entity.result.replacement;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * Created by chenlicong on 2018/9/4.
 */
public class ReplaceCardVO extends BaseVO {

    private Integer id;//唯一编码 主键
    private String name;//姓名
    private String idcard;//身份证号码
    private String cardid;//社会保障卡卡号
    private String bankAccount;//银行卡号
    private String bank;//制卡银行编码
    private String bankName;//制卡银行名称
    private String companyName;//单位名称
    private String status;//卡状态
    private String cardStatus;//卡应用状态
    private String personType;//人员类别
    private String reginal;//所属城市编码
    private String reginalName;//所属城市名称
    private String replaceNum;//补换卡次数
    private String receiveTime;//最近领卡时间

    private String rkwd;//入库网点编码
    private String batchNo;//批次号
    private String errMsg;//错误信息
    private String makeCardData;//制卡数据集

    private String replaceType;//补卡类别
    private String replaceReason;//补卡原因
    private String bankCode;//制卡银行
    private String fkrq;//发卡日期
    private String kyxq;//卡有效期
    private String birth;//出生日期
    private String sex;//性别
    private String nation;//民族
    private String mobile;//手机号码
    private String photo;//相片base64
    private String district;//所属区县
    private String replacecardNo;//预制卡编号

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

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getReginal() {
        return reginal;
    }

    public void setReginal(String reginal) {
        this.reginal = reginal;
    }

    public String getReginalName() {
        return reginalName;
    }

    public void setReginalName(String reginalName) {
        this.reginalName = reginalName;
    }

    public String getReplaceNum() {
        return replaceNum;
    }

    public void setReplaceNum(String replaceNum) {
        this.replaceNum = replaceNum;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getRkwd() {
        return rkwd;
    }

    public void setRkwd(String rkwd) {
        this.rkwd = rkwd;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getMakeCardData() {
        return makeCardData;
    }

    public void setMakeCardData(String makeCardData) {
        this.makeCardData = makeCardData;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getReplacecardNo() {
        return replacecardNo;
    }

    public void setReplacecardNo(String replacecardNo) {
        this.replacecardNo = replacecardNo;
    }
}
