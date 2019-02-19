package com.tecsun.sisp.net.modules.entity.request;


import com.tecsun.sisp.net.common.vo.faceverify.SecQueryBean;

//事项查看通用bean
public class ItemBean extends SecQueryBean{
	private int id;
	private String sxmc;
	private String sxbm;
	private String sxlx;//事项类型
	
	
    

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSxlx() {
		return sxlx;
	}
	public void setSxlx(String sxlx) {
		this.sxlx = sxlx;
	}
	public String getSxmc() {
		return sxmc;
	}
	public void setSxmc(String sxmc) {
		this.sxmc = sxmc;
	}
	public String getSxbm() {
		return sxbm;
	}
	public void setSxbm(String sxbm) {
		this.sxbm = sxbm;
	}
	

}
