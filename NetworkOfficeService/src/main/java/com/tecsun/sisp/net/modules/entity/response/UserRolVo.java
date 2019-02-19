package com.tecsun.sisp.net.modules.entity.response;


//人社用户
public class UserRolVo {
	
	  private long roleId;
	  private String name;  
	  private String description;
	  
	  private long noDel;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getNoDel() {
		return noDel;
	}

	public void setNoDel(long noDel) {
		this.noDel = noDel;
	}
	  
	  

}
