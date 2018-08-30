package com.tecsun.sisp.adapter.modules.train.entity.response;

/**
 * Created by xumaohao on 2017/8/28.
 */
public class TrainDetailBean extends TrainSchoolBean{

    private String goal;        //培养目标
    private String goalRemark;  //培养目标备注
    private String regine;      //学制
    private String regineRemark;//学制备注
    private String content;     //教学内容
    private String contentRemark;//教学内容备注
    private String cost;        //费用
    private String costRemark;  //费用备注


    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getGoalRemark() {
        return goalRemark;
    }

    public void setGoalRemark(String goalRemark) {
        this.goalRemark = goalRemark;
    }

    public String getRegine() {
        return regine;
    }

    public void setRegine(String regine) {
        this.regine = regine;
    }

    public String getRegineRemark() {
        return regineRemark;
    }

    public void setRegineRemark(String regineRemark) {
        this.regineRemark = regineRemark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentRemark() {
        return contentRemark;
    }

    public void setContentRemark(String contentRemark) {
        this.contentRemark = contentRemark;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCostRemark() {
        return costRemark;
    }

    public void setCostRemark(String costRemark) {
        this.costRemark = costRemark;
    }


}
