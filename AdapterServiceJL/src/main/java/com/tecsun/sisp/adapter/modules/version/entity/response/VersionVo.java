package com.tecsun.sisp.adapter.modules.version.entity.response;

/**
 * Created by xumaohao on 2017/11/16.
 */
public class VersionVo {
    private long id;                //主键
    private String sortwareCode;    //软件编码
    private String sortwareName;    //软件名称
    private String sortwareVersion; //版本号
    private String downloadLink;    //下载链接
    private String appType;         //app种类
    private String remark;          //更新说明
    private String planUpdateTime;  //计划更新时间
    private String forceUpdate;     //是否强制更新（默认0  0：不强制更新 1：强制更新）
    private long downloadNumber;    //下载量	默认0
    private String createPeople;    //发布人
    private String createTime;      //发布时间
    private String updatePeople;    //修改人
    private String updateTime;      //修改时间
    private String status;          //状态（默认0  0：正常 1：删除）

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSortwareCode() {
        return sortwareCode;
    }

    public void setSortwareCode(String sortwareCode) {
        this.sortwareCode = sortwareCode;
    }

    public String getSortwareName() {
        return sortwareName;
    }

    public void setSortwareName(String sortwareName) {
        this.sortwareName = sortwareName;
    }

    public String getSortwareVersion() {
        return sortwareVersion;
    }

    public void setSortwareVersion(String sortwareVersion) {
        this.sortwareVersion = sortwareVersion;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlanUpdateTime() {
        return planUpdateTime;
    }

    public void setPlanUpdateTime(String planUpdateTime) {
        this.planUpdateTime = planUpdateTime;
    }

    public String getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(String forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public long getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(long downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    public String getCreatePeople() {
        return createPeople;
    }

    public void setCreatePeople(String createPeople) {
        this.createPeople = createPeople;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdatePeople() {
        return updatePeople;
    }

    public void setUpdatePeople(String updatePeople) {
        this.updatePeople = updatePeople;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
