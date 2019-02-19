package com.tecsun.sisp.net.modules.entity;

import com.tecsun.sisp.net.common.Page;

public class BaseVO<T> {

    protected Page<T> page;

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}