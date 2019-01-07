package com.tecsun.sisp.adapter.modules.card.entity.request;

import java.util.Date;

/**
 * @author wunuanchan
 * @version 创建时间：2018年1月6日 下午4:47:30 说明：
 */

public class CardProblemBean {

	private String name;
	private Integer cbid;
	private Integer[] ids;
	private String channelcode; // 渠道编码
	private String deviceid; // 设备编码
	private String tokenid; // 权限验证码
	private String loginuserid;
	private Integer id;
	private Integer ciid;
	private String idcard;
	private String cardid;
	private String remark;// 原因
	private Date retentiontime;// 登记时间
	private String retentionuser;// 登记人
	private Date handletime;// 处理时间
	private String handle;// 处理方式
	private Integer status;// 处理状态
	private String userid;
	private String orgcode;
	private String parentid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	/**
	 * @return the cbid
	 */
	public Integer getCbid() {
		return cbid;
	}

	/**
	 * @param cbid
	 *            the cbid to set
	 */
	public void setCbid(Integer cbid) {
		this.cbid = cbid;
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

	public String getLoginuserid() {
		return loginuserid;
	}

	public void setLoginuserid(String loginuserid) {
		this.loginuserid = loginuserid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCiid() {
		return ciid;
	}

	public void setCiid(Integer ciid) {
		this.ciid = ciid;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getRetentiontime() {
		return retentiontime;
	}

	public void setRetentiontime(Date retentiontime) {
		this.retentiontime = retentiontime;
	}

	public String getRetentionuser() {
		return retentionuser;
	}

	public void setRetentionuser(String retentionuser) {
		this.retentionuser = retentionuser;
	}

	public Date getHandletime() {
		return handletime;
	}

	public void setHandletime(Date handletime) {
		this.handletime = handletime;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}
