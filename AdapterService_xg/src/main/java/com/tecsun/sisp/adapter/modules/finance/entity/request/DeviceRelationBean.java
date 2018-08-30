package com.tecsun.sisp.adapter.modules.finance.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by danmeng on 2017/6/21.
 */
public class DeviceRelationBean extends SecQueryBean {
    private String projectNo;//项目编号
    private String bankCode;//银联/银行编码
    private String businessCode;//业务编码
    private String propertyCode;//设备编号(deviceid设备ID/CPU,IMEI号,SN出厂序列号任意一个
    private String propertyType;//设备编号类型(1:deviceid设备ID/CPU,2:IMEI号,3:SN出厂序列号)

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}
