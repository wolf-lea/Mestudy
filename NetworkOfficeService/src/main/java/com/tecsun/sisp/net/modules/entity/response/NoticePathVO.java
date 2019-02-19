package com.tecsun.sisp.net.modules.entity.response;

/**
 * 附件地址
 * Created by danmeng on 2018/7/30.
 */
public class NoticePathVO {
    private String id;//
    private Long fileId;//
    private String newFilePath;//附件地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getNewFilePath() {
        return newFilePath;
    }

    public void setNewFilePath(String newFilePath) {
        this.newFilePath = newFilePath;
    }
}