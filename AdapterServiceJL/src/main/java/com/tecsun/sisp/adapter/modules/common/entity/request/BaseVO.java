package com.tecsun.sisp.adapter.modules.common.entity.request;

import com.tecsun.sisp.adapter.common.util.Page;

public class BaseVO<T> {

    protected Page<T> page;

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}