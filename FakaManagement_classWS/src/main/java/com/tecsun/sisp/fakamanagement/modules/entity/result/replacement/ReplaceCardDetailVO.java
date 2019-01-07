package com.tecsun.sisp.fakamanagement.modules.entity.result.replacement;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * @author tanzhiyong
 * @version
 * 
 *          创建时间：2018年10月24日 上午11:30:12 说明：
 */
public class ReplaceCardDetailVO extends BaseVO{
	private Integer id;
	private Integer replaceCardPersonId;
	private String replaceCardNo;
	private String atr;
	private String bank;
	private String rkwd;
	private String status;
	private String fakaTime;
	private String cardid;
	
	private String name;
	private String idcard;
	private String mobile;
	private String replaceType;
	private String replaceReason;
	private String photoUrl;
	private String signPhoto;
	private String idcardPhotoUp;
	private String idcardPhotoDown;
	
	
	
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
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
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getSignPhoto() {
		return signPhoto;
	}
	public void setSignPhoto(String signPhoto) {
		this.signPhoto = signPhoto;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	
}
