package com.tecsun.sisp.adapter.modules.question.entity.response;
/**
* @author  wunuanchan
* @version 
* 创建时间：2018年3月14日 上午11:41:53
* 说明：回复表
*/

public class ReplyVO {
	
	private String id;//	主键ID
	private String sendId;//	发送信息主表ID
	private String context;//	回复内容
	private String userid;//	回复用户ID
	private String username;//	回复用户姓名
	private String createtime;//	回复创建时间
	private String updatetime;//	回复修改时间
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	

}
