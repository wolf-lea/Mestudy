package com.tecsun.sisp.iface.common.vo;

import java.util.Date;


public class PersonVO{
    private long id;
	private String networkName; //网点名
	private String name; // 用户名
	private String idCard; // 身份证
    private Date createTime; // 创建时间
    private Integer personState; //人员状态
	private long networkId; //网点编号
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNetworkName() {
		return networkName;
	}
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getPersonState() {
		return personState;
	}
	public void setPersonState(Integer personState) {
		this.personState = personState;
	}
	public long getNetworkId() {
		return networkId;
	}
	public void setNetworkId(long networkId) {
		this.networkId = networkId;
	}
	
	

	
	
}
	
	
	
	
