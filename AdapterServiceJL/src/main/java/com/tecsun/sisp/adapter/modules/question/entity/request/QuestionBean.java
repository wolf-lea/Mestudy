package com.tecsun.sisp.adapter.modules.question.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年3月14日 上午10:16:51
* 说明：投诉建议、表扬点赞
*/

public class QuestionBean extends SecQueryBean{
	
	private String id;//主键ID
	private String wechatid;//微信ID
	private String context;//内容
	private String status;//状态(默认0)
	private String type;//问题类型
	private String organ;//机构
	private String beginTime;//创建时间
	private String endTime;//修改时间
	private String nickname;//微信昵称
	private String sendName;//发送人姓名
	private String sendPhone;//发送人联系方式
	private String bankCode;//银行编码
	private String distinctCode;//区县编码
	private String netCode;//支行编码
	private String orgCode;//人社机构编码
	private String questionType;//问题类型编码
	
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
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getDistinctCode() {
		return distinctCode;
	}
	public void setDistinctCode(String distinctCode) {
		this.distinctCode = distinctCode;
	}
	public String getNetCode() {
		return netCode;
	}
	public void setNetCode(String netCode) {
		this.netCode = netCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	
	
	

}
