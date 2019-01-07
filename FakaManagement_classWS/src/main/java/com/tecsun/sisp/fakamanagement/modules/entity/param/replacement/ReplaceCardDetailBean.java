package com.tecsun.sisp.fakamanagement.modules.entity.param.replacement;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * @author tanzhiyong
 * @version
 * 
 *          创建时间：2018年10月23日 上午10:30:02 说明：
 */
public class ReplaceCardDetailBean extends BaseVO{
	private Integer id;
	private Integer replaceCardPersonId;	//
	private String replaceCardNo;		//
	private String atr;
	private String bank;
	private String rkwd;
	private String status;
	private String cardid;		//社保卡号
	private String userid;
	
	private String idcard;		//身份证号
	
	
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
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
	
	
}
