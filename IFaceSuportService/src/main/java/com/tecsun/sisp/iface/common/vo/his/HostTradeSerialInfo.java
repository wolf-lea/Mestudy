package com.tecsun.sisp.iface.common.vo.his;

public class HostTradeSerialInfo {
	
	private String id;//
	private String hostCode;//医院编码
	private String terminalNo;//终端号
	private String payType;//缴费类型(0缴费 1 挂号 2预约挂号)
	private String hostVisitCode;//医院就诊流水号
	private String hostTradeCode;//医院交易流水号
	private String hostDepart;//科室号
	private String cardTypeCode;//卡类型编码
	private String status;//状态
	private String bankCardCode;//银联卡号
	private String cardNo;//身份证编号;
	private String userName;//用户名
	private String sex;//性别
	private String age;//年龄
	private String updateTime;//操作时间
	private String regTime;//'AM 上午 PM 下午
	private String channelCode;//渠道代码
	public String getBuessId() {
		return buessId;
	}
	public void setBuessId(String buessId) {
		this.buessId = buessId;
	}
	private String buessId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHostCode() {
		return hostCode;
	}
	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}
	public String getTerminalNo() {
		return terminalNo;
	}
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getHostVisitCode() {
		return hostVisitCode;
	}
	public void setHostVisitCode(String hostVisitCode) {
		this.hostVisitCode = hostVisitCode;
	}
	public String getHostTradeCode() {
		return hostTradeCode;
	}
	public void setHostTradeCode(String hostTradeCode) {
		this.hostTradeCode = hostTradeCode;
	}
	public String getHostDepart() {
		return hostDepart;
	}
	public void setHostDepart(String hostDepart) {
		this.hostDepart = hostDepart;
	}
	public String getCardTypeCode() {
		return cardTypeCode;
	}
	public void setCardTypeCode(String cardTypeCode) {
		this.cardTypeCode = cardTypeCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBankCardCode() {
		return bankCardCode;
	}
	public void setBankCardCode(String bankCardCode) {
		this.bankCardCode = bankCardCode;
	}

	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
}