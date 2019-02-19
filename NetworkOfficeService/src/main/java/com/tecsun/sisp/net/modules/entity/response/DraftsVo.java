package com.tecsun.sisp.net.modules.entity.response;

import com.tecsun.sisp.net.modules.entity.BaseVO;

import java.util.Date;




//我的办件出参
public class DraftsVo extends BaseVO{
	private long mid;//事项id
	private String oid;//办件详细id
	private String tid;//办件表id
	private String xm;//姓名
	private String sfzh;//身份证号
	private String officeId;//办件编号
	private long businessId; //事项编码
	private String matterName;//事项名称
	private Date applytime;//申请时间
	private String status;//办理状态
	private String auditor;//审核人
	private Date updatetime;//审核时间
	private Date auditTime;//审核时间
	private String remarks;//备注
	private long applyNum;//审核编号
	private String reason; //原因
	private String name;//审核人姓名
	 
	private String submitter;//提交人ID
	
	private int    		pageno;		//页码
    private int    		pagesize;		//页数

	private String sfcode;	//身份编码
	private String sfname;	//身份名称
	private String dycode;	//待遇编码
	private String dyname;	//待遇名称
	private String sxmc;
	private String address;
	private String phone;
	private String pstatus;
	private String worktime;
	private String insuredplace;
	private String insuredunit;
	private String ylbxtime;
	private String ybtime;
	private String deyltime;
	private String filename;
	private String insuredaddress; 	//参保地区名称
	private String insuredcode;		//	参保单位编码

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPstatus() {
		return pstatus;
	}

	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	public String getInsuredplace() {
		return insuredplace;
	}

	public void setInsuredplace(String insuredplace) {
		this.insuredplace = insuredplace;
	}

	public String getInsuredunit() {
		return insuredunit;
	}

	public void setInsuredunit(String insuredunit) {
		this.insuredunit = insuredunit;
	}

	public String getYlbxtime() {
		return ylbxtime;
	}

	public void setYlbxtime(String ylbxtime) {
		this.ylbxtime = ylbxtime;
	}

	public String getYbtime() {
		return ybtime;
	}

	public void setYbtime(String ybtime) {
		this.ybtime = ybtime;
	}

	public String getDeyltime() {
		return deyltime;
	}

	public void setDeyltime(String deyltime) {
		this.deyltime = deyltime;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getInsuredaddress() {
		return insuredaddress;
	}

	public void setInsuredaddress(String insuredaddress) {
		this.insuredaddress = insuredaddress;
	}

	public String getInsuredcode() {
		return insuredcode;
	}

	public void setInsuredcode(String insuredcode) {
		this.insuredcode = insuredcode;
	}

	public String getSxmc() {
		return sxmc;
	}

	public void setSxmc(String sxmc) {
		this.sxmc = sxmc;
	}

	public String getSfcode() {
		return sfcode;
	}

	public void setSfcode(String sfcode) {
		this.sfcode = sfcode;
	}

	public String getSfname() {
		return sfname;
	}

	public void setSfname(String sfname) {
		this.sfname = sfname;
	}

	public String getDycode() {
		return dycode;
	}

	public void setDycode(String dycode) {
		this.dycode = dycode;
	}

	public String getDyname() {
		return dyname;
	}

	public void setDyname(String dyname) {
		this.dyname = dyname;
	}
	
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
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
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public String getMatterName() {
		return matterName;
	}
	public void setMatterName(String matterName) {
		this.matterName = matterName;
	}
	public Date getApplytime() {
		return applytime;
	}
	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
