package com.tecsun.sisp.adapter.modules.gov.entity;


import com.tecsun.sisp.adapter.modules.common.entity.request.BaseVO;

import java.util.Date;

public class GovernmentVO extends BaseVO{
	private String id;
	private String title;
	private String secondTitle;
	private String content;
	private String auth;
	private String publishTime;
	private int userId;
	private String type;
	private Date startTime;
	private Date endTime;
	private Date createTime;
	private String listUrl;
	private String url;
	
	private String startTimes;
	private String endTimes;
    private int pageno;
    private int pageSize;//页容量

    public int getPageno() {
        return pageno;
    }
    public void setPageno(int pageno) {
        this.pageno = pageno;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
	public String getStartTimes() {
		return startTimes;
	}
	public void setStartTimes(String startTimes) {
		this.startTimes = startTimes;
	}
	public String getEndTimes() {
		return endTimes;
	}
	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getListUrl() {
		return listUrl;
	}
	public void setListUrl(String listUrl) {
		this.listUrl = listUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSecondTitle() {
		return secondTitle;
	}
	public void setSecondTitle(String secondTitle) {
		this.secondTitle = secondTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
