package com.tecsun.sisp.fun.modules.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.fun.common.Page;
import com.tecsun.sisp.fun.modules.dao.impl.UserDAO;
import com.tecsun.sisp.fun.modules.entity.role.RoleIfaceVO;
import com.tecsun.sisp.fun.modules.entity.user.InterfaceUserBean;
import com.tecsun.sisp.fun.modules.entity.user.RoleUserIfaceVO;
import com.tecsun.sisp.fun.modules.entity.user.UserIfaceVO;

@Service("UserService")
public class UserServiceImpl {
	@Autowired
	private UserDAO userDAO;

	public Page<UserIfaceVO> getUserList(Page<UserIfaceVO> page, UserIfaceVO vo) {
		vo.setPage(page);
		page.setData(userDAO.getUserList(vo));
		return page;
	}

	public boolean insert(UserIfaceVO vo) {
		return userDAO.insertUser(vo) > 0;
	}

	public boolean update(UserIfaceVO vo) {
		return userDAO.updateUser(vo) > 0;
	}

	public List<UserIfaceVO> isExitCodeOrName(UserIfaceVO vo) {
		return userDAO.isExitCodeOrName(vo);
	}

	public List<RoleIfaceVO> getRoleList() {
		return userDAO.getRoleList();
	}

	public long getUserId(String userName) {
		return userDAO.getUserId(userName);
	}

	public boolean insertRoleUser(RoleUserIfaceVO vo) {
		return userDAO.insertRoleUser(vo) > 0;
	}

	public UserIfaceVO getUserInfo(long id) {
		return userDAO.getUserInfo(id);
	}

	public List<RoleUserIfaceVO> getUserRoleId(long id) {
		return userDAO.getUserRoleId(id);
	}
	public long deleteUser(long id) {
		return userDAO.deleteUser(id);
	}
	public long deleteRoleUser(long id) {
		return userDAO.deleteRoleUser(id);
	}
	
	public boolean updateUserPwd(UserIfaceVO vo) {
		return userDAO.updateUserPwd(vo) > 0;
	}
	
	public InterfaceUserBean checkInterfaceUserRoleFunc(Map map){
		return userDAO.checkInterfaceUserRoleFunc(map);
	}
	
	public UserIfaceVO userLogin(UserIfaceVO vo){
		return userDAO.userLogin(vo);
	}
}
