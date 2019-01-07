package com.tecsun.sisp.fakamanagement.modules.entity;

import com.tecsun.sisp.fakamanagement.common.Page;

public class BaseVO<T> extends TSBVO{

	protected Page<T> page;

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

}