package com.tecsun.sisp.fun.modules.dao.impl;

import java.util.List;
import java.util.Map;

import com.tecsun.sisp.fun.common.MyBatisDao;
import com.tecsun.sisp.fun.modules.entity.role.RoleIfaceVO;
import com.tecsun.sisp.fun.modules.entity.user.InterfaceUserBean;
import com.tecsun.sisp.fun.modules.entity.user.RoleUserIfaceVO;
import com.tecsun.sisp.fun.modules.entity.user.UserIfaceVO;


@MyBatisDao
public interface UserDAO {
	public List<UserIfaceVO> getUserList(UserIfaceVO vo);
	
	public long insertUser(UserIfaceVO vo);
	
	public long updateUser(UserIfaceVO vo);
	
	public List<UserIfaceVO> isExitCodeOrName(UserIfaceVO vo);
	
	public long deleteUser(long id);
	
	public List<RoleIfaceVO> getRoleList();
	
	public long getUserId(String userName);
	
	public long insertRoleUser(RoleUserIfaceVO vo);
	
	public UserIfaceVO getUserInfo(long id);
	
	public List<RoleUserIfaceVO> getUserRoleId(long id);
	
	public long deleteRoleUser(long id);
	
	public long updateUserPwd(UserIfaceVO vo);
	
	public InterfaceUserBean checkInterfaceUserRoleFunc(Map map);
	
	public UserIfaceVO userLogin(UserIfaceVO vo);
}
