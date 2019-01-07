package com.tecsun.sisp.adapter.modules.question.entity.response;
/**
* @author  wunuanchan
* @version 
* 创建时间：2018年3月14日 上午10:55:04
* 说明：投诉建议、表扬点赞
*/

public class SendVO {
	
	
	private String id;//主键ID
	private String wechatid;//微信ID
	private String context;//内容
	private String status;//状态(默认0)
	private String type;//问题类型
	private String organ;//机构
	private String createtime;//创建时间
	private String updatetime;//修改时间
	private String nickname;//微信昵称
	private String sendName;//发送人姓名
	private String sendPhone;//发送人联系方式
	private String bankName;//银行名称
	private String distinctName;//区县名称
	private String netName;//支行名称
	private String orgName;//人社机构名称
	private String questionType;//问题类型名称
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWechatid() {
		return wechatid;
	}
	public void setWechatid(String wechatid) {
		this.wechatid = wechatid;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getSendPhone() {
		return sendPhone;
	}
	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getDistinctName() {
		return distinctName;
	}
	public void setDistinctName(String distinctName) {
		this.distinctName = distinctName;
	}
	public String getNetName() {
		return netName;
	}
	public void setNetName(String netName) {
		this.netName = netName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	
	
	

}
