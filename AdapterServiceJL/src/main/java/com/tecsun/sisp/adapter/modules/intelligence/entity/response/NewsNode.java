package com.tecsun.sisp.adapter.modules.intelligence.entity.response;

/**捷通华声-单一回答图文回复
 * Created by danmeng on 2017/8/29.
 */
public class NewsNode {
//    private int itemId;//项目编号
    private String title;//图文标题
    private String description;//图文描述
    private String picUrl;//图文图片链接
    private String url;//图文链接
//    private String cmd;//指令
/*
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
/*
    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }*/
}
