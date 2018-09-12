package com.tecsun.sisp.fun.bean;

import java.util.List;

public class TreeBean {

	private String id;
	private String text;
	private String value;
	private Boolean showcheck;
	private Boolean complete;
	private Boolean isexpand;
	private int checkstate;
	private Boolean hasChildren;
	private long parentId;
	private List<TreeBean> childNodes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getShowcheck() {
		return showcheck;
	}

	public void setShowcheck(Boolean showcheck) {
		this.showcheck = showcheck;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Boolean getIsexpand() {
		return isexpand;
	}

	public void setIsexpand(Boolean isexpand) {
		this.isexpand = isexpand;
	}

	public int getCheckstate() {
		return checkstate;
	}

	public void setCheckstate(int checkstate) {
		this.checkstate = checkstate;
	}

	public Boolean getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(Boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public List<TreeBean> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(List<TreeBean> childNodes) {
		this.childNodes = childNodes;
	}



}
