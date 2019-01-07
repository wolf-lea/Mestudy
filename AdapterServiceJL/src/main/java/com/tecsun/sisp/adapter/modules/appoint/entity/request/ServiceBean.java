package com.tecsun.sisp.adapter.modules.appoint.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by xumaohao on 2017/10/25.
 */
public class ServiceBean extends SecQueryBean {
    private String parentCode;         //父级编码

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
