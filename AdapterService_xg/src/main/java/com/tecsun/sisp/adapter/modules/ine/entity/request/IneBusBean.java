package com.tecsun.sisp.adapter.modules.ine.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 *@author sipeng
 *@email zsp_banyue@163.com
 *@date 2017年11月2日
 * 
 */
public class IneBusBean extends SecQueryBean{
	
	private long reId; //主键uid
	private long infoCode; // 信息编码
	private String infoType; //信息类型 分为报名 -0  1邀请
	private String tel; //
	public long getInfoCode() {
		return infoCode;
	}
	public void setInfoCode(long infoCode) {
		this.infoCode = infoCode;
	}
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public long getReId() {
		return reId;
	}
	public void setReId(long reId) {
		this.reId = reId;
	}
	
	
}
