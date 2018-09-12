package com.tecsun.sisp.fun.modules.dao.impl;

import java.util.List;

import com.tecsun.sisp.fun.common.MyBatisDao;
import com.tecsun.sisp.fun.modules.entity.role.AppRoleIfaceVO;
import com.tecsun.sisp.fun.modules.entity.role.RoleIfaceVO;


@MyBatisDao
public interface RoleDAO {
	public List<RoleIfaceVO> getRoleList(RoleIfaceVO vo);
	
	public List<AppRoleIfaceVO> getAppRoleList(long id);
	
	public long insertRole(RoleIfaceVO vo);
	
	public long updateRole(RoleIfaceVO vo);
	
	public List<RoleIfaceVO> isExitCodeOrName(RoleIfaceVO vo);
	
	public long deleteRole(long id);
	
	public long insertAppRole(AppRoleIfaceVO vo);
	
	public long deleteAppRole(long id);
}
