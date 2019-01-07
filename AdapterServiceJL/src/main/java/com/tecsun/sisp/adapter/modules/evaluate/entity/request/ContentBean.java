package com.tecsun.sisp.adapter.modules.evaluate.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by xumaohao on 2017/10/18.
 */
public class ContentBean extends SecQueryBean{
    private String serviceCode;     //服务编码

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}
