package com.tecsun.sisp.fun.modules.controller.common.param;

/**
 * 根据格格纬度统计所需要的字段
 * Created by zhangqingjie on 15-5-13.
 */
public class TransLogParam {
    private String channelcode;//渠道编码
    private String deviceid;//设备编码
    private String businesscode;//业务类型
    private String result;//1成功，0失败
    private Integer count;//总记录数

    //查询条件
    private String startTime;
    private String endTime;
    private String searchType;
    private Integer managerid;//负责人编码

    private Integer allbusicount;//业务类型总数

    public TransLogParam() {
    }

    public Integer getAllbusicount() {
        return allbusicount;
    }

    public void setAllbusicount(Integer allbusicount) {
        this.allbusicount = allbusicount;
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public String getChannelcode() {
        return channelcode;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getBusinesscode() {
        return businesscode;
    }

    public void setBusinesscode(String businesscode) {
        this.businesscode = businesscode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
