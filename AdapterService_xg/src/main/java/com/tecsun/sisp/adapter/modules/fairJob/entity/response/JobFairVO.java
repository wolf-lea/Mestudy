package com.tecsun.sisp.adapter.modules.fairJob.entity.response;

/**招聘会类
 * Created by Administrator on 2017/11/2 0002.
 */
public class JobFairVO {
    private String id;          //招聘会id
    private String name;        //招聘会名称
    private String status;      //招聘会状态0：未开始，1：进行中，2：已结束
    private String address;     //举办地址
    private String tel;         //咨询电话
    private String beginTime;   //招聘会开始时间
    private String endTime;     //招聘会结束时间
    private String organizer;   //举办单位
    private String createTime;  //创建时间
    private String updateTime;  //修改时间
    private String areaCode;
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
