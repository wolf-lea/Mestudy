package com.tecsun.sisp.fakamanagement.modules.untie.entity.response;

import java.util.List;

/**
 * 
 * @Title: 微信绑定解绑列表Data
 * @company :TECSUN
 * @developer: ZENGYUNHUA
 *	@date 2018年9月5日 下午4:58:01
 *	@version 1.0
 */
public class UnbindOrBindResponseBean {
	
	private List<PageResponse>data;
	private Integer pageno;
	private Integer pagesize;
	private Integer count;

	
	public List<PageResponse> getData() {
		return data;
	}
	public void setData(List<PageResponse> data) {
		this.data = data;
	}
	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	

}
