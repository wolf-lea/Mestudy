package com.tecsun.sisp.adapter.modules.evaluate.entity.response;

import java.util.Date;
import java.util.List;

/**
 * Created by xumaohao on 2017/10/18.
 */
public class ContentVo {
    private long contentId;       //内容id
    private String contentName;     //评价的内容名称
    private String contentProperty; //内容属性（1:星星 2:选择程度 3:中文输入）
    private String contentOption;   //内容选项（当属性为2时提供选择的值 eg: 1:满意|2:一般|3:不满意）
    private List contentOptionList; //将内容选项转为key_value类型
    private String contentDefault;  //内容默认值
    private String contentLimit;    //内容限制
    private Date createTime;        //创建时间
    private Date updateTime;        //修改时间
    private String noNull;          //是否必填（0：必填   1：非必填 默认0）
    private String standby1;        //备用字段1
    private String standby2;        //备用字段2
    private String standby3;        //备用字段3

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentProperty() {
        return contentProperty;
    }

    public void setContentProperty(String contentProperty) {
        this.contentProperty = contentProperty;
    }

    public String getContentOption() {
        return contentOption;
    }

    public void setContentOption(String contentOption) {
        this.contentOption = contentOption;
    }

    public List getContentOptionList() {
        return contentOptionList;
    }

    public void setContentOptionList(List contentOptionList) {
        this.contentOptionList = contentOptionList;
    }

    public String getContentDefault() {
        return contentDefault;
    }

    public void setContentDefault(String contentDefault) {
        this.contentDefault = contentDefault;
    }

    public String getContentLimit() {
        return contentLimit;
    }

    public void setContentLimit(String contentLimit) {
        this.contentLimit = contentLimit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNoNull() {
        return noNull;
    }

    public void setNoNull(String noNull) {
        this.noNull = noNull;
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

    public static class change{
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
