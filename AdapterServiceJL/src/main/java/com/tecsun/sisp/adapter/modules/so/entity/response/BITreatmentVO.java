package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年2月1日 上午11:32:30
* 说明：生育待遇发放信息
*/

public class BITreatmentVO {
	
	private String grbh;//个人编号
	private String dwbh;//单位编号
	@Field(empty="-")
	private String jsq;//结算期
	private String syfssj;//生育发生时间
	private String sydylb;//生育待遇类别
	@Field(empty="0")
	private String dyje;//待遇金额
	private String dyzfzt;//待遇支付状态
	private String jbrq;//经办日期
	
	public String getGrbh() {
		return grbh;
	}
	public void setGrbh(String grbh) {
		this.grbh = grbh;
	}
	public String getDwbh() {
		return dwbh;
	}
	public void setDwbh(String dwbh) {
		this.dwbh = dwbh;
	}
	public String getJsq() {
		return jsq;
	}
	public void setJsq(String jsq) {
		this.jsq = jsq;
	}
	public String getSyfssj() {
		return syfssj;
	}
	public void setSyfssj(String syfssj) {
		this.syfssj = syfssj;
	}
	public String getSydylb() {
		return sydylb;
	}
	public void setSydylb(String sydylb) {
		this.sydylb = sydylb;
	}
	public String getDyje() {
		return dyje;
	}
	public void setDyje(String dyje) {
		this.dyje = dyje;
	}
	public String getDyzfzt() {
		return dyzfzt;
	}
	public void setDyzfzt(String dyzfzt) {
		this.dyzfzt = dyzfzt;
	}
	public String getJbrq() {
		return jbrq;
	}
	public void setJbrq(String jbrq) {
		this.jbrq = jbrq;
	}
	
	
	

}
