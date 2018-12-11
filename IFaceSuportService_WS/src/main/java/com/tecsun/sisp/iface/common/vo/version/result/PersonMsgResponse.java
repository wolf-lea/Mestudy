package com.tecsun.sisp.iface.common.vo.version.result;

/**
 * ClassName:PersonMsgResponse
 * Description:农户信息
 * Author:zhengmingmin
 * CreateTime: 16-1-14
 */
public class PersonMsgResponse {
    private long id;
    //private String name;
    private String mobile;
   // private String idCard;//身份证号
    private int heartIndex;
  //  private String picPath;
   // private  String sbCard;//社保卡号
  //  private  String sex;//

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getHeartIndex() {
        return heartIndex;
    }

    public void setHeartIndex(int heartIndex) {
        this.heartIndex = heartIndex;
    }


}
