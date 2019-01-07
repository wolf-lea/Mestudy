package com.tecsun.sisp.fakamanagement.modules.entity.result.card;

/**
 * Created by chenlicong on 2018/1/12.
 */
public class FindCardInfoVO {

    private String id;
    private String name;//姓名
    private String idcard;//身份证号码
    private int status;//状态
    private String inputTime;//时间

    private String userId;//用户ID
    private String fkwd;//发卡网点
    private String startTime;//开始时间
    private String endTime;//结束时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFkwd() {
        return fkwd;
    }

    public void setFkwd(String fkwd) {
        this.fkwd = fkwd;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

