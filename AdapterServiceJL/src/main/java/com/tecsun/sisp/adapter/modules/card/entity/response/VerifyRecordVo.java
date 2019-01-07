package com.tecsun.sisp.adapter.modules.card.entity.response;

import java.util.Date;

/**
 *@author sipeng
 *@email zsp_banyue@163.com
 *@date 2017年11月21日
 * 
 */
public class VerifyRecordVo {
	
	private long recordId;//审核记录id
	private long applyId;//申领记录id
	private String xm;
	private String sfzh;
	private String reason;//审核失败原因
	private Date verifyTime;//审核时间
	private String verifyStatus;//审核状态
	private Date createTime;//创建时间
	private String remark;//备注
	private long picId;//申领图片id
	private Boolean isDealed;//是否处理
	public long getRecordId() {
		return recordId;
	}
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	public String getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getPicId() {
		return picId;
	}
	public void setPicId(long picId) {
		this.picId = picId;
	}
	public long getApplyId() {
		return applyId;
	}
	public void setApplyId(long applyId) {
		this.applyId = applyId;
	}
	public Boolean getIsDealed() {
		return isDealed;
	}
	public void setIsDealed(Boolean isDealed) {
		this.isDealed = isDealed;
	}
	
	
}
