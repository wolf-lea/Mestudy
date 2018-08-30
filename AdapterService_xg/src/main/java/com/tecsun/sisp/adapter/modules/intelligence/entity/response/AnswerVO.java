package com.tecsun.sisp.adapter.modules.intelligence.entity.response;

import java.util.List;

/**
 * Created by danmeng on 2017/8/29.
 */
public class AnswerVO {
    private String questionMsg;//问题
    private String standardQuestMsg;//标准问题
    private double score;//分值
    private String answerMsg;//文本回复
    private int isRichText;//是否图文 0标准问答，1图文
    private List<NewsNode> newsNodeList;//图文回复
    private String promptVagueMsg;//前置提示语
    private List<String> vagueMsgList;//多选回答(推荐问题)
    private String endVagueMsg;//后置提示语
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStandardQuestMsg() {
        return standardQuestMsg;
    }

    public void setStandardQuestMsg(String standardQuestMsg) {
        this.standardQuestMsg = standardQuestMsg;
    }

    public String getPromptVagueMsg() {
        return promptVagueMsg;
    }

    public void setPromptVagueMsg(String promptVagueMsg) {
        this.promptVagueMsg = promptVagueMsg;
    }

    public String getEndVagueMsg() {
        return endVagueMsg;
    }

    public void setEndVagueMsg(String endVagueMsg) {
        this.endVagueMsg = endVagueMsg;
    }

    public List<String> getVagueMsgList() {
        return vagueMsgList;
    }

    public void setVagueMsgList(List<String> vagueMsgList) {
        this.vagueMsgList = vagueMsgList;
    }

    public int getIsRichText() {
        return isRichText;
    }

    public void setIsRichText(int isRichText) {
        this.isRichText = isRichText;
    }


    public List<NewsNode> getNewsNodeList() {
        return newsNodeList;
    }

    public void setNewsNodeList(List<NewsNode> newsNodeList) {
        this.newsNodeList = newsNodeList;
    }

    public String getQuestionMsg() {
        return questionMsg;
    }

    public void setQuestionMsg(String questionMsg) {
        this.questionMsg = questionMsg;
    }

    public String getAnswerMsg() {
        return answerMsg;
    }

    public void setAnswerMsg(String answerMsg) {
        this.answerMsg = answerMsg;
    }
}
