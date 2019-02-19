package com.tecsun.sisp.net.modules.entity.request;

import com.tecsun.sisp.net.modules.entity.BaseVO;


//角色入参
public class UserRoleBean  extends BaseVO{
	private String name;//姓名
	private int    		pageno;		//页码
    private int    		pagesize;		//页数
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
