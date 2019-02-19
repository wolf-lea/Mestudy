package com.tecsun.sisp.net.modules.entity.request;

public class AuditBean {
	
	private String id;
	private String   auditor;  //审核人
	private String   status;//状态
	private long   applyNum;//审核编号
	private String   reason;//审核通过与否原因
	private int   userId;//用户id
	private long   aId;//主键自增  
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(long applyNum) {
		this.applyNum = applyNum;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getaId() {
		return aId;
	}
	public void setaId(long aId) {
		this.aId = aId;
	}
	
	

}
