package com.tecsun.sisp.net.common.vo.faceverify;

import java.util.ArrayList;
import java.util.List;

public class Page<T>{
    private int pageNo; // 当前页码
    private int pageSize; // 页面大小，设置为“-1”表示不进行分页（分页无效）
    private long count;// 总记录数，设置为“-1”表示不查询总数
    private List<T> data = new ArrayList<T>();
	public Page(int pageno, int pagesize, long count) {
        this(pageno, pagesize, count, new ArrayList<T>());
    }
    public Page(int pageno, int pagesize) {
        this(pageno, pagesize, 0);
    }
    public Page() {
    	
    }
    public Page(int pageno, int pagesize, long count, List<T> data) {
        this.count = count;
        this.pageNo = pageno <= 0 ? 1 : pageno;
        this.pageSize = pagesize <= 0 ? 30 : pagesize;
        this.data = data;
    }
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
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
    
}
