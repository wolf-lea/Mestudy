package com.tecsun.sisp.fun.modules.entity.role;

import java.sql.Timestamp;

public class AppRoleIfaceVO {
	/** 主键 */
	private long id;
	/** 功能ID */
	private long appid;
	/** 角色ID */
	private long roleid;
	/** 创建时间 */
	private Timestamp createtime;
	/** 更新时间 */
	private Timestamp updatetime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAppid() {
		return appid;
	}
	public void setAppid(long appid) {
		this.appid = appid;
	}
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
}
