package com.tecsun.sisp.net.common.vo.faceverify;

/**
 * Created by jerry on 2015/5/31.
 */
public class BaseVO<T> {

    public Page<T> page;
    private int pageNo;
    private int pageSize;//页容量
    
    public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
   
    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}
