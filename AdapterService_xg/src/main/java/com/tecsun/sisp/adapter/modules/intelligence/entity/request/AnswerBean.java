package com.tecsun.sisp.adapter.modules.intelligence.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by danmeng on 2017/8/28.
 */
public class AnswerBean extends SecQueryBean{
//    private  int protocolId;//问答接口协议ID
//    private  String robotHashCode;//机器人识别码
//    private  String platformConnType;//用户问答机器人的渠道编号
    private  String userId;//用户每次问答机器人的用户唯一标识userId
//    private  String talkerId;//外部开发平台的自身编号talkerId
//    private  String receiverId;// 固定为本接口的版本号
//    private  String appKey;//灵云appKey
//    private  long sendTime;//用户问答问题的提问时间sendTime
    private  String type;//用户问答问题内容的类型type
//    private  int isNeedClearHistory;//多轮对话中，本次问答是否要使用上次问答的结果来问答引擎
    private  String question;//用户问答机器人的问题内容question
    private  byte[] questionVoice;//语音问题
//    private  int isQuestionQuery;//用户问答的问题内容是否通过问题ID来提问
    private  String intelligentCorp;//智能客服厂家

    public byte[] getQuestionVoice() {
        return questionVoice;
    }

    public void setQuestionVoice(byte[] questionVoice) {
        this.questionVoice = questionVoice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getIntelligentCorp() {
        return intelligentCorp;
    }

    public void setIntelligentCorp(String intelligentCorp) {
        this.intelligentCorp = intelligentCorp;
    }
}
