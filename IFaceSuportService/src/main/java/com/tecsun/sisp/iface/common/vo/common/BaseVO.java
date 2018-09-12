package com.tecsun.sisp.iface.common.vo.common;

import com.tecsun.sisp.iface.common.Page;

public class BaseVO<T> {

    protected Page<T> page;

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}