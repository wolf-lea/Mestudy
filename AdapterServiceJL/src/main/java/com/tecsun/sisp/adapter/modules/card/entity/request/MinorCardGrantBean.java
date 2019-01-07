package com.tecsun.sisp.adapter.modules.card.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
* @author  tanzhiyong
* @version 
* 创建时间：2018年9月4日 
* 说明：未成年人卡发放--入参
*/
public class MinorCardGrantBean extends SecQueryBean{
	private String name;	//未成年人姓名
	private String idcard;	//未成年人身份证号码
	private String cardid;	//社会保障卡卡号
	private String batchNo;	//批次号
	private String companyCode;//单位编码
	private String companyName;//单位名称
	private String angent;	//联系人
	private String phone;	//联系电话
	private String address;	//联系地址
	private String oldCfwz;	//旧存放位置
	private Integer status; //卡状态  1：待入柜、2：已入柜、3：待发卡、4：滞留卡、5：问题卡、6：待激活、7：已发卡、8：待找卡
	private String fkwd;	//发卡网点
	private Integer receiveNum;//领卡流水号
	private String bank;	//所属银行
	private String bankAcount;//银行账户
	private String reginalCode;//城市代码
	private String inputTime;	//导入时间
	private String retentionTime;//标记滞留卡时间
	private String retentionUser;//标记滞留卡经办人
	private String retentionNum;//标记次数
	private String activeTime;	//激活时间
	private String updateTime;	//修改时间
	private String xtZxwz;	//系统--装箱位置
	private Integer syncStatus;	//领卡类型
	private String domiciliaryRegisterPhoto;//户口本照片
	private String cardidPhoto;//手持社保卡照片
	
	
	//card_agent表
	private String photo;	//个人照片
	private String idcardPhotoUp;//身份证正面
	private String idcardPhotoDown;//身份证反面
	private String signPhoto;//电子签字
	private String subName;	//代领人姓名
	private String subIdcard;//代领人身份证号码
	private String subPhone; //代领人手机号
	private String photoName;//照片名 
	private Integer personType;//人员类型1:个人领卡2:代个人领卡3:单位领卡4:代单位领卡5:未成年人领卡
	private Integer agentId;	//	插入到card_receivede跟card_agent表关联
	private Integer ciid;	//card_receivede跟card_info表关联
	
	
	
	public String getDomiciliaryRegisterPhoto() {
		return domiciliaryRegisterPhoto;
	}
	public void setDomiciliaryRegisterPhoto(String domiciliaryRegisterPhoto) {
		this.domiciliaryRegisterPhoto = domiciliaryRegisterPhoto;
	}
	public String getCardidPhoto() {
		return cardidPhoto;
	}
	public void setCardidPhoto(String cardidPhoto) {
		this.cardidPhoto = cardidPhoto;
	}
	public Integer getCiid() {
		return ciid;
	}
	public void setCiid(Integer ciid) {
		this.ciid = ciid;
	}
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getIdcardPhotoUp() {
		return idcardPhotoUp;
	}
	public void setIdcardPhotoUp(String idcardPhotoUp) {
		this.idcardPhotoUp = idcardPhotoUp;
	}
	public String getIdcardPhotoDown() {
		return idcardPhotoDown;
	}
	public void setIdcardPhotoDown(String idcardPhotoDown) {
		this.idcardPhotoDown = idcardPhotoDown;
	}
	public String getSignPhoto() {
		return signPhoto;
	}
	public void setSignPhoto(String signPhoto) {
		this.signPhoto = signPhoto;
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
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAngent() {
		return angent;
	}
	public void setAngent(String angent) {
		this.angent = angent;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOldCfwz() {
		return oldCfwz;
	}
	public void setOldCfwz(String oldCfwz) {
		this.oldCfwz = oldCfwz;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFkwd() {
		return fkwd;
	}
	public void setFkwd(String fkwd) {
		this.fkwd = fkwd;
	}
	public Integer getReceiveNum() {
		return receiveNum;
	}
	public void setReceiveNum(Integer receiveNum) {
		this.receiveNum = receiveNum;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAcount() {
		return bankAcount;
	}
	public void setBankAcount(String bankAcount) {
		this.bankAcount = bankAcount;
	}
	public String getReginalCode() {
		return reginalCode;
	}
	public void setReginalCode(String reginalCode) {
		this.reginalCode = reginalCode;
	}
	public String getInputTime() {
		return inputTime;
	}
	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}
	public String getRetentionTime() {
		return retentionTime;
	}
	public void setRetentionTime(String retentionTime) {
		this.retentionTime = retentionTime;
	}
	public String getRetentionUser() {
		return retentionUser;
	}
	public void setRetentionUser(String retentionUser) {
		this.retentionUser = retentionUser;
	}
	public String getRetentionNum() {
		return retentionNum;
	}
	public void setRetentionNum(String retentionNum) {
		this.retentionNum = retentionNum;
	}
	public String getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getXtZxwz() {
		return xtZxwz;
	}
	public void setXtZxwz(String xtZxwz) {
		this.xtZxwz = xtZxwz;
	}
	
	public Integer getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getSubIdcard() {
		return subIdcard;
	}
	public void setSubIdcard(String subIdcard) {
		this.subIdcard = subIdcard;
	}
	public String getSubPhone() {
		return subPhone;
	}
	public void setSubPhone(String subPhone) {
		this.subPhone = subPhone;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public Integer getPersonType() {
		return personType;
	}
	public void setPersonType(Integer personType) {
		this.personType = personType;
	}
}
