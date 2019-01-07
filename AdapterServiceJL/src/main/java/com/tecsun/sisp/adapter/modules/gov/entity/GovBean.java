package com.tecsun.sisp.adapter.modules.gov.entity;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 */
public class GovBean extends SecQueryBean {
    private long id;                        //id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
