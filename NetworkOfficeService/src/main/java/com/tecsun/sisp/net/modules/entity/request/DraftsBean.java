package com.tecsun.sisp.net.modules.entity.request;

import com.tecsun.sisp.net.common.vo.faceverify.SecQueryBean;
import com.tecsun.sisp.net.modules.entity.response.OfficeVo;


//办件入参
public class DraftsBean extends SecQueryBean{
	private long mId;
	private String id;//办件id
	private String tId;//办件编号
	private String  businessId;      //事项编码
	private String  matterName;    //事项名称
	private String  officeId;//办件编号关联办件表
	private String sfzh;
	private String status;//状态
	private String state;
	private String sxmc;
	private String submitter;

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

	private String remarks;

	private OfficeVo officeVo;  //办件信息

	public OfficeVo getOfficeVo() {
		return officeVo;
	}

	public void setOfficeVo(OfficeVo officeVo) {
		this.officeVo = officeVo;
	}

	public String getSxmc() {
		return sxmc;
	}

	public void setSxmc(String sxmc) {
		this.sxmc = sxmc;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	private String userId;//用户id
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public long getmId() {
		return mId;
	}
	public void setmId(long mId) {
		this.mId = mId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	/*public int getPageno() {
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
	}*/
	
	public String getMatterName() {
		return matterName;
	}
	public void setMatterName(String matterName) {
		this.matterName = matterName;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}


	

}
