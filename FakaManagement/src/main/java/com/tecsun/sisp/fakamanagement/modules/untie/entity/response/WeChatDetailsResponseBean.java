package com.tecsun.sisp.fakamanagement.modules.untie.entity.response;

/**
 * 
 * @Title: 微信详细出参
 * @company :TECSUN
 * @developer: ZENGYUNHUA
 *	@date 2018年9月5日 上午10:11:04
 *	@version 1.0
 */
public class WeChatDetailsResponseBean {
	private String accountId;//唯一ID
	private String accountName;//姓名
	private String sfzh;//身份证
	private String phone;//联系电话
	private String openid;//微信ID
	private String createTime;//时间
	private String isWechat;//状态:1:绑定成功，0：绑定失败
	private String cardPic;//持卡库照片base64
	private String comparePic;//采集照片base64
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getIsWechat() {
		return isWechat;
	}
	public void setIsWechat(String isWechat) {
		this.isWechat = isWechat;
	}
	public String getCardPic() {
		return cardPic;
	}
	public void setCardPic(String cardPic) {
		this.cardPic = cardPic;
	}
	public String getComparePic() {
		return comparePic;
	}
	public void setComparePic(String comparePic) {
		this.comparePic = comparePic;
	}
	
	

	


}
