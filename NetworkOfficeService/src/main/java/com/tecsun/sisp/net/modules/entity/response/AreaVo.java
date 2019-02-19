package com.tecsun.sisp.net.modules.entity.response;

import java.util.Date;

public class AreaVo {
	
	private long distinctId;
	private String name;
	private String code;
	private long parentId;
	private Date createTime;
	private Date updateTime;
	public long getDistinctId() {
		return distinctId;
	}
	public void setDistinctId(long distinctId) {
		this.distinctId = distinctId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
	  
	         
	 
	 

}
