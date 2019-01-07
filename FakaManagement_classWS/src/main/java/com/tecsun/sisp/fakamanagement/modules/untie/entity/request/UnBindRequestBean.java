package com.tecsun.sisp.fakamanagement.modules.untie.entity.request;

/**
 * 
 * @Title: 微信解绑入参
 * @company :TECSUN
 * @developer: ZENGYUNHUA
 *	@date 2018年9月5日 上午10:07:12
 *	@version 1.0
 */
public class UnBindRequestBean {
	private String accountName;//姓名
	private String sfzh;//身份证号
	private String accountId;//唯一id
	private String isSuccessBind;//标志   绑定：1，解绑：0（isSuccessBind）
	private String channelcode;
	private Integer pageno;
	private Integer pagesize;
	
	
	public String getChannelcode() {
		return channelcode;
	}
	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}
	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
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
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getIsSuccessBind() {
		return isSuccessBind;
	}
	public void setIsSuccessBind(String isSuccessBind) {
		this.isSuccessBind = isSuccessBind;
	}
	
	
   
}
