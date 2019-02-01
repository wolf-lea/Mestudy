package com.imooc.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "chat_msg")
public class ChatMsg {
    @Id
    private String id;

    /**
     * 发送者id
     */
    @Column(name = "send_user_id")
    private String sendUserId;

    /**
     * 接收者id
     */
    @Column(name = "accept_user_id")
    private String acceptUserId;

    /**
     * 发送的消息
     */
    private String msg;

    /**
     * 收到状态
     */
    @Column(name = "sign_flag")
    private String signFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取发送者id
     *
     * @return send_user_id - 发送者id
     */
    public String getSendUserId() {
        return sendUserId;
    }

    /**
     * 设置发送者id
     *
     * @param sendUserId 发送者id
     */
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    /**
     * 获取接收者id
     *
     * @return accept_user_id - 接收者id
     */
    public String getAcceptUserId() {
        return acceptUserId;
    }

    /**
     * 设置接收者id
     *
     * @param acceptUserId 接收者id
     */
    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    /**
     * 获取发送的消息
     *
     * @return msg - 发送的消息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置发送的消息
     *
     * @param msg 发送的消息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取收到状态
     *
     * @return sign_flag - 收到状态
     */
    public String getSignFlag() {
        return signFlag;
    }

    /**
     * 设置收到状态
     *
     * @param signFlag 收到状态
     */
    public void setSignFlag(String signFlag) {
        this.signFlag = signFlag;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}