package com.tecsun.siboss.tsbm.common.bean;

/**
 * Created by jerry on 2015/5/31.
 */
public class BaseVO<T> {

    protected Page<T> page;

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}
