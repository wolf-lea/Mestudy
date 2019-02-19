package com.tecsun.sisp.net.export;

import java.util.Iterator;
import java.util.List;

import com.tecsun.sisp.net.common.Page;

public class PageIterator<T> implements Iterator<List<T>> {
	private final int PAGESIZE=1;
	private ExportService<T> service;
	private T obj;
	private Page<T> page;
	private int pageNo;
	private boolean isAll;

	public PageIterator(ExportService<T> service,T obj,int pageNo) {
		this.obj=obj;
		this.isAll=pageNo<=0;
		this.pageNo=(this.isAll?1:pageNo);
		this.service=service;
	}

	@Override
	public boolean hasNext() {
		return null==this.page||(!page.isLastPage()&&this.isAll);
	}

	@Override
	public List<T> next() {
		if (this.hasNext()) {
			try {
				if(this.isAll){
					pageNo=(null==this.page?pageNo:this.page.getNext());
				}
				page=service.getPageList(obj, new Page<T>(pageNo,PAGESIZE));
				return page.getData();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		return null;
	}

	@Override
	public void remove() {
	}

}
