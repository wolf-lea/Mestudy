package com.tecsun.sisp.adapter.modules.card.entity.response;

import com.tecsun.sisp.adapter.modules.common.entity.response.ExtVO;

/**卡基础数据
 * Created by danmeng on 2017/8/9.
 */
public class CardBasicVO extends ExtVO {
    private String sfzh;//身份证
    private String xm;//姓名
    private String sbkh;// 社保卡号
    private String markNo;// 卡识别码
    private String cardType;//卡类别
    private String certValidity;//卡有效期
    private String grantTime;//发卡时间
    private String bankAccount;//银行卡号
    private String bankName;//银行名称
    private String status;//卡片状态
    private String issueDistrict;//发卡地行政区域代码
    private String sex;//性别
    private String nation;//民族
    private String birthplace;//出生地
    private String birthday;//出生日期
    private String result1;//卡片复位信息
    private String result2;//卡规范版本
    private String result3;//初始化机构编号

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
        this.xm = xm;
    }

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getMarkNo() {
        return markNo;
    }

    public void setMarkNo(String markNo) {
        this.markNo = markNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCertValidity() {
        return certValidity;
    }

    public void setCertValidity(String certValidity) {
        this.certValidity = certValidity;
    }

    public String getGrantTime() {
        return grantTime;
    }

    public void setGrantTime(String grantTime) {
        this.grantTime = grantTime;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssueDistrict() {
        return issueDistrict;
    }

    public void setIssueDistrict(String issueDistrict) {
        this.issueDistrict = issueDistrict;
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

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getResult3() {
        return result3;
    }

    public void setResult3(String result3) {
        this.result3 = result3;
    }
}
