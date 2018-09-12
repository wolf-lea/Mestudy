package com.tecsun.sisp.iface.common.vo.card;

/**
 * 制卡进度查询
 * Created by jerry on 2015/5/30.
 */
public class SettingCardQuery {
    private String err;//是否错误
    private String restime;//操作时间
    private String sbkh;//社保卡号
    private String sfzh;//身份证号码
    private String name;//姓名
    private String cardtype;//卡类型
    private String transacttype; //申请类型
    private String batchno; //批次号
    private String citycode; //城市代码
    private String organid; //经办机构
    private String bank; //银行
    private String applytime; //申请时间
    private String banktime0; //导出预开户时间
    private String bankfinishtime0; //预开户回盘时间
    private String insuretime; //导出卡商制卡时间
    private String insurefinishtime0; //卡商制卡回盘时间
    private String insurefinishtime; //卡商制卡回盘时间
    private String provincetime; //省卡中心寄出时间
    private String citytime; //市中心接受时间
    private String gettime; //领卡时间
    private String gettime1; //领卡时间
    private String remarks; //备注
    private String validtag; //有效标识
    private String status ; //卡状态
    

    public SettingCardQuery() {
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getRestime() {
        return restime;
    }

    public void setRestime(String restime) {
        this.restime = restime;
    }

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getTransacttype() {
        return transacttype;
    }

    public void setTransacttype(String transacttype) {
        this.transacttype = transacttype;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getOrganid() {
        return organid;
    }

    public void setOrganid(String organid) {
        this.organid = organid;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    public String getBanktime0() {
        return banktime0;
    }

    public void setBanktime0(String banktime0) {
        this.banktime0 = banktime0;
    }

    public String getBankfinishtime0() {
        return bankfinishtime0;
    }

    public void setBankfinishtime0(String bankfinishtime0) {
        this.bankfinishtime0 = bankfinishtime0;
    }

    public String getInsuretime() {
        return insuretime;
    }

    public void setInsuretime(String insuretime) {
        this.insuretime = insuretime;
    }

    public String getInsurefinishtime0() {
        return insurefinishtime0;
    }

    public void setInsurefinishtime0(String insurefinishtime0) {
        this.insurefinishtime0 = insurefinishtime0;
    }

    public String getInsurefinishtime() {
        return insurefinishtime;
    }

    public void setInsurefinishtime(String insurefinishtime) {
        this.insurefinishtime = insurefinishtime;
    }

    public String getProvincetime() {
        return provincetime;
    }

    public void setProvincetime(String provincetime) {
        this.provincetime = provincetime;
    }

    public String getCitytime() {
        return citytime;
    }

    public void setCitytime(String citytime) {
        this.citytime = citytime;
    }

    public String getGettime() {
        return gettime;
    }

    public void setGettime(String gettime) {
        this.gettime = gettime;
    }

    public String getGettime1() {
        return gettime1;
    }

    public void setGettime1(String gettime1) {
        this.gettime1 = gettime1;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getValidtag() {
        return validtag;
    }

    public void setValidtag(String validtag) {
        this.validtag = validtag;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
