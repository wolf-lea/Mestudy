package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;
import com.tecsun.sisp.adapter.common.util.CommUtil;

public class URREIPayRecord {

	@Field(empty="无")  
	private String ssq;                      // 所属期
	@Field(empty="-")  
	private String grjn;                     // 个人缴纳             
	@Field(empty="-")  
	private String zfbt;                     // 政府补贴
	@Field(empty="-")  
	private String qtbz;                     // 其他补助 
	@Field(empty="-")  
	private String dj;           	         // 代缴 
	
	public String getSsq() {
		return ssq;
	}
	public void setSsq(String ssq) {
		this.ssq = ssq;
	}
	public String getGrjn() {
		if(grjn == null || grjn.isEmpty())
			return grjn;
		
		return CommUtil.formatNumberic(grjn);
	}
	public void setGrjn(String grjn) {
		this.grjn = grjn;
	}
	public String getZfbt() {
		if(zfbt == null || zfbt.isEmpty())
			return zfbt;
		
		return CommUtil.formatNumberic(zfbt);
	}
	public void setZfbt(String zfbt) {
		this.zfbt = zfbt;
	}
	public String getQtbz() {
		if(qtbz == null || qtbz.isEmpty())
			return qtbz;
		
		return CommUtil.formatNumberic(qtbz);
	}
	public void setQtbz(String qtbz) {
		this.qtbz = qtbz;
	}
	public String getDj() {
		if(dj == null || dj.isEmpty())
			return dj;
		
		return CommUtil.formatNumberic(dj);
	}
	public void setDj(String dj) {
		this.dj = dj;
	}
}
