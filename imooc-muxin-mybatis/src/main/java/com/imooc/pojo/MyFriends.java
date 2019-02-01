package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "my_friends")
public class MyFriends {
    @Id
    private String id;

    /**
     * 我的个人id
     */
    @Column(name = "my_user_id")
    private String myUserId;

    /**
     * 我朋友的用户id
     */
    @Column(name = "my_friend_user_id")
    private String myFriendUserId;

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
     * 获取我的个人id
     *
     * @return my_user_id - 我的个人id
     */
    public String getMyUserId() {
        return myUserId;
    }

    /**
     * 设置我的个人id
     *
     * @param myUserId 我的个人id
     */
    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    /**
     * 获取我朋友的用户id
     *
     * @return my_friend_user_id - 我朋友的用户id
     */
    public String getMyFriendUserId() {
        return myFriendUserId;
    }

    /**
     * 设置我朋友的用户id
     *
     * @param myFriendUserId 我朋友的用户id
     */
    public void setMyFriendUserId(String myFriendUserId) {
        this.myFriendUserId = myFriendUserId;
    }
}