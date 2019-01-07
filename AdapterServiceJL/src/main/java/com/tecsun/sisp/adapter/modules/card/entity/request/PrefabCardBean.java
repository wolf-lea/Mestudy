package com.tecsun.sisp.adapter.modules.card.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
* @author  tanzhiyong
* @version 
* 创建时间：2018年9月13日 
* 说明：预制卡卡发放入参
*/
public class PrefabCardBean extends SecQueryBean{
	//replacecard_detail表
	private Integer replaceCardPersonId;	//补换卡人员ID
	private String replaceCardNo;	//预制卡唯一编号
	private String atr;			//卡复位码
	private String bank;		//银行编码
	private String rkwd;		//入库网点
	private String status;		//状态 00-初始（入库） 01-已发放  02-发放失败03-待激活
	private String fakaTime;	//制卡发放时间
	private String detailCreateTime;	//创建时间
	private String detailUpdateTime;	//修改时间
	
	//replacecard_person_info
	private String name;
	private String idcard;
	private String cardid;
	private String mobile;
	private String replaceType;
	private String replaceReason;
	private String personCreateTime;
	private String personUpdateTime;
	private String photoUrl;
	private String idcardPhotoUp;
	private String idcardPhotoDown;
	private String signPhoto;
	
	
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getPersonCreateTime() {
		return personCreateTime;
	}
	public void setPersonCreateTime(String personCreateTime) {
		this.personCreateTime = personCreateTime;
	}
	public String getPersonUpdateTime() {
		return personUpdateTime;
	}
	public void setPersonUpdateTime(String personUpdateTime) {
		this.personUpdateTime = personUpdateTime;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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
	public Integer getReplaceCardPersonId() {
		return replaceCardPersonId;
	}
	public void setReplaceCardPersonId(Integer replaceCardPersonId) {
		this.replaceCardPersonId = replaceCardPersonId;
	}
	public String getReplaceCardNo() {
		return replaceCardNo;
	}
	public void setReplaceCardNo(String replaceCardNo) {
		this.replaceCardNo = replaceCardNo;
	}
	public String getAtr() {
		return atr;
	}
	public void setAtr(String atr) {
		this.atr = atr;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getRkwd() {
		return rkwd;
	}
	public void setRkwd(String rkwd) {
		this.rkwd = rkwd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFakaTime() {
		return fakaTime;
	}
	public void setFakaTime(String fakaTime) {
		this.fakaTime = fakaTime;
	}
	public String getDetailCreateTime() {
		return detailCreateTime;
	}
	public void setDetailCreateTime(String detailCreateTime) {
		this.detailCreateTime = detailCreateTime;
	}
	public String getDetailUpdateTime() {
		return detailUpdateTime;
	}
	public void setDetailUpdateTime(String detailUpdateTime) {
		this.detailUpdateTime = detailUpdateTime;
	}
	
}
