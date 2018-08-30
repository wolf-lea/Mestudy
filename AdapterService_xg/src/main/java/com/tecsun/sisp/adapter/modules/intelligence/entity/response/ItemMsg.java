package com.tecsun.sisp.adapter.modules.intelligence.entity.response;

/**捷通华声-多选答案问题列表详情
 * Created by danmeng on 2017/8/29.
 */
public class ItemMsg {
    private String question;//标准问题
    private double score;//分值
    private int num;//序号
    private long id;//标准问题id
    private int type;//类别 0是模式，1是知识

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
