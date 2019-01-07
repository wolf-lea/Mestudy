package com.tecsun.sisp.adapter.modules.card.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年9月12日 下午4:19:25
* 说明：
*/

public class CardReplaceBean extends SecQueryBean{
	
	
	private String name;//姓名
	private String idcard;//身份证
	private String district;//所属区县
	private String bankCode;//银行编码
	private String ks;//卡商
	private String personTotal;//人数
	private String cardid;//社保卡号
	private String replaceType;//补卡类别
	private String replaceReason;//补卡原因
	private String replacecardNo;//即制卡号
	private String atr;//卡复位码
	private String rkwd;//入库网点编码
	private String batchNo;//批次号
	private String fkrq;//发卡日期
	private String kyxq;//卡有效期
	private String ksbm;//卡识别码
	private String bankAccount;//银行卡号
	private String account;//个人帐户
	private String zxwz;//装箱位置
	private String failType;//失败环节(银行/制卡)
	private String failReason;//失败原因(50字节内)
	private String photoUrl;//照片路径；格式：xxxx.jpg
	private String mobile;//电话号码
	private String reginal;//所属城市编码
	
	
	
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
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
	public String getReplacecardNo() {
		return replacecardNo;
	}
	public void setReplacecardNo(String replacecardNo) {
		this.replacecardNo = replacecardNo;
	}
	public String getAtr() {
		return atr;
	}
	public void setAtr(String atr) {
		this.atr = atr;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getReginal() {
		return reginal;
	}
	public void setReginal(String reginal) {
		this.reginal = reginal;
	}
	
	
	

}
