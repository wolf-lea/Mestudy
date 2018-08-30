package com.tecsun.sisp.adapter.modules.card.entity.request;

public class PersonInfoVO {
	private Long cardId;// 社保卡物理ID
	private String certNum;// 身份证号码
	private Long personId;
	private String soCardNum;
	private String xm;// 姓名
	private String channelcode;// 渠道编码
	private String deviceid;// 设备ID
	private String tokenid;// tokenid

	public String getSoCardNum() {
		return soCardNum;
	}

	public void setSoCardNum(String soCardNum) {
		this.soCardNum = soCardNum;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getCertNum() {
		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getChannelcode() {
		return channelcode;
	}

	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getTokenid() {
		return tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
}
