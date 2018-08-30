package com.tecsun.sisp.adapter.modules.intelligence.entity.response;

/**捷通华声-智能问答
 * Created by danmeng on 2017/8/29.
 */
public class SinoVoiceAnswerVO {
    private int protocolId;//问答接口返回协议ID
    private int result;//返回结果
    private long sendTime;//协议发送时间sendTime
    private int answerTypeId;//回答类型
    private SingleNode singleNode;//单一回答节点
    private VagueNode vagueNode;//多选回答节点

    public int getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(int protocolId) {
        this.protocolId = protocolId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public int getAnswerTypeId() {
        return answerTypeId;
    }

    public void setAnswerTypeId(int answerTypeId) {
        this.answerTypeId = answerTypeId;
    }

    public SingleNode getSingleNode() {
        return singleNode;
    }

    public void setSingleNode(SingleNode singleNode) {
        this.singleNode = singleNode;
    }

    public VagueNode getVagueNode() {
        return vagueNode;
    }

    public void setVagueNode(VagueNode vagueNode) {
        this.vagueNode = vagueNode;
    }
}
