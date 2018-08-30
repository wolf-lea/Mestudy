package com.tecsun.sisp.adapter.modules.common.entity.response;


/**
 * Created by danmeng on 2017/7/17.
 */
public class BusConfigVO {
    private String configCode;//所属编码
    private String configName;//名称
    private String configValue;//内容
    private String orderNum;//排序
    private String remark;//备注

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
