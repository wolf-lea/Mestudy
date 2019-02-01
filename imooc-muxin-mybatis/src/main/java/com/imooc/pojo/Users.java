package com.imooc.pojo;

import javax.persistence.*;

public class Users {
    @Id
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 小头像
     */
    @Column(name = "face_image")
    private String faceImage;

    /**
     * 大头像
     */
    @Column(name = "face_image_big")
    private String faceImageBig;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 二维码
     */
    private String qrcode;

    private String cid;

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
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取小头像
     *
     * @return face_image - 小头像
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * 设置小头像
     *
     * @param faceImage 小头像
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * 获取大头像
     *
     * @return face_image_big - 大头像
     */
    public String getFaceImageBig() {
        return faceImageBig;
    }

    /**
     * 设置大头像
     *
     * @param faceImageBig 大头像
     */
    public void setFaceImageBig(String faceImageBig) {
        this.faceImageBig = faceImageBig;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取二维码
     *
     * @return qrcode - 二维码
     */
    public String getQrcode() {
        return qrcode;
    }

    /**
     * 设置二维码
     *
     * @param qrcode 二维码
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * @return cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }
}