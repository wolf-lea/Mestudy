package com.tecsun.sisp.net.modules.entity.response;

import java.util.List;

//添加修改草稿箱未完成
public class DraftsListVo {
	
	private String tId;//办件主键
	private String remarks;//备注
	private String officeId;//办件编号
	private long businessId;//事项编号
	private List<TmatterVo> tmatters ;//事项列表集合
	private List<OfficeVo> offices ;//办件集合
	private String submitter;//提交人id
	private String updatetime; //修改时间
	private String state;
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<TmatterVo> getTmatters() {
		return tmatters;
	}
	public void setTmatters(List<TmatterVo> tmatters) {
		this.tmatters = tmatters;
	}
	public List<OfficeVo> getOffices() {
		return offices;
	}
	public void setOffices(List<OfficeVo> offices) {
		this.offices = offices;
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	
	
	
	

}
