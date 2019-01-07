package com.tecsun.sisp.adapter.modules.appoint.entity.response;

import com.tecsun.sisp.adapter.common.util.Config;

import java.util.List;

/**
 * Created by xumaohao on 2017/10/25.
 */
public class ServiceVo {
    private String serviceCode;         //业务编码
    private String serviceName;         //业务名
    private String parentCode;          //父级编码（没有则为0）
    private String isUse;               //是否使用
    private String link;                //链接
    private String iconLink;            //图标链接
    private String standby1;            //备用1
    private String standby2;            //备用2
    private String standby3;            //备用3
    private List<ServiceVo> data;       //子级

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIconLink() {
        return iconLink = iconLink == null ? iconLink : Config.getInstance().get("appoint.icon.address") + iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getStandby1() {
        return standby1;
    }

    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    public String getStandby2() {
        return standby2;
    }

    public void setStandby2(String standby2) {
        this.standby2 = standby2;
    }

    public String getStandby3() {
        return standby3;
    }

    public void setStandby3(String standby3) {
        this.standby3 = standby3;
    }

    public List<ServiceVo> getData() {
        return data;
    }

    public void setData(List<ServiceVo> data) {
        this.data = data;
    }
}
