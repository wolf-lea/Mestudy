package com.tecsun.sisp.fun.modules.entity.role;

import java.sql.Timestamp;

import com.tecsun.sisp.fun.modules.entity.BaseVO;

  
public class RoleIfaceVO extends BaseVO{
	
	private long id;

	/** 姓名 */
	private String name;

	/** 状态 */
	private long nodel;

	/** 创建时间 */
	private Timestamp createTime;

	/** 更新时间 */
	private Timestamp updateTime;

	/** 描述 */
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNodel() {
		return nodel;
	}

	public void setNodel(long nodel) {
		this.nodel = nodel;
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

}
