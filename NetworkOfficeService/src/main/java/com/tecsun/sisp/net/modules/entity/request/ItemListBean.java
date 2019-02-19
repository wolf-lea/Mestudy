package com.tecsun.sisp.net.modules.entity.request;

import com.tecsun.sisp.net.modules.entity.BaseVO;


public class ItemListBean extends BaseVO{
	
	
	private String sxmc;
	private String sxbm;
	
	private int    		pageno;		//页码
    private int    		pagesize;		//页数
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
    

}
