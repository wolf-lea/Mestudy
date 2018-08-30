package com.tecsun.sisp.adapter.modules.card.entity.request;

import java.util.Date;

/**
 * 滞留卡——箱子
 */
public class RetentionBinBean {
	private Long id;
	private String binNo;
	private int status;
	private int count;
	private Long addUserNo;
	private Date addDate;
	private Long modUserNo;
	private Date modDate;
	private String remark;
	private String storeOrgType;
	private long storeOrgId;
	private int streamStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBinNo() {
		return binNo;
	}

	public void setBinNo(String binNo) {
		this.binNo = binNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Long getAddUserNo() {
		return addUserNo;
	}

	public void setAddUserNo(Long addUserNo) {
		this.addUserNo = addUserNo;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Long getModUserNo() {
		return modUserNo;
	}

	public void setModUserNo(Long modUserNo) {
		this.modUserNo = modUserNo;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStoreOrgType() {
		return storeOrgType;
	}

	public void setStoreOrgType(String storeOrgType) {
		this.storeOrgType = storeOrgType;
	}

	public long getStoreOrgId() {
		return storeOrgId;
	}

	public void setStoreOrgId(long storeOrgId) {
		this.storeOrgId = storeOrgId;
	}

	public int getStreamStatus() {
		return streamStatus;
	}

	public void setStreamStatus(int streamStatus) {
		this.streamStatus = streamStatus;
	}

	private String orgAddress;

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
}
