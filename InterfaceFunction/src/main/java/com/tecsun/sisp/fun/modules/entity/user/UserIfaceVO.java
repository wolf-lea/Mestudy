package com.tecsun.sisp.fun.modules.entity.user;

import java.sql.Timestamp;

import com.tecsun.sisp.fun.modules.entity.BaseVO;

public class UserIfaceVO extends BaseVO{
	private long id;

	/** 登录帐号 */
	private String userName;

	/** 登录密码 */
	private String userPwd;
	
	/** 状态 */
	private long status;

	/** 创建时间 */
	private Timestamp createTime;

	/** 更新时间 */
	private Timestamp updateTime;

	/** 描述 */
	private String description;
	
	/** 手机号码 */
	private String phone;
	
	/** 角色名称 */
	private String roleNames;
	//区域
	private String areaId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

}
