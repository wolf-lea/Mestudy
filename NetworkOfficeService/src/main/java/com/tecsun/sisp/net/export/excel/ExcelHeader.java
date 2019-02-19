package com.tecsun.sisp.net.export.excel;

public class ExcelHeader {
	
	private String title;//表头单元格要显示的名称
	private int id;//单元格ID（每一个单元格都有一个id标识）
	private int level;//单元格所在层级
	private int parentId;//父单元格ID（用来标识关联关系）
	private int myLeafCount;//当前节点的叶子节点数量（叶子节点为没有子节点的节点）
	private boolean isLeaf;//是否是叶子节点
	private int frontLeafCount;//当前节点之前叶子结点的数量
	
	public ExcelHeader() {
		super();
	}
	public ExcelHeader(String title, int id, int level, int parentId,
			int myLeafCount, boolean isLeaf, int frontLeafCount) {
		super();
		this.title = title;
		this.id = id;
		this.level = level;
		this.parentId = parentId;
		this.myLeafCount = myLeafCount;
		this.isLeaf = isLeaf;
		this.frontLeafCount = frontLeafCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getMyLeafCount() {
		return myLeafCount;
	}
	public void setMyLeafCount(int myLeafCount) {
		this.myLeafCount = myLeafCount;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public int getFrontLeafCount() {
		return frontLeafCount;
	}
	public void setFrontLeafCount(int frontLeafCount) {
		this.frontLeafCount = frontLeafCount;
	}
	
}
