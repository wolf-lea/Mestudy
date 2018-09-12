package com.tecsun.sisp.fun.modules.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.fun.common.Page;
import com.tecsun.sisp.fun.modules.dao.impl.RoleDAO;
import com.tecsun.sisp.fun.modules.entity.role.AppRoleIfaceVO;
import com.tecsun.sisp.fun.modules.entity.role.RoleIfaceVO;


@Service("RoleService")
public class RoleServiceImpl {
	@Autowired
    private RoleDAO roleDAO;
	
	public Page<RoleIfaceVO> getRoleList(Page<RoleIfaceVO> page , RoleIfaceVO vo){
		vo.setPage(page);
		page.setData(roleDAO.getRoleList(vo));
    	return page;
    }
	
	public List<AppRoleIfaceVO> getAppRoleList(long id){
    	return roleDAO.getAppRoleList(id);
    }
	
	public boolean insert(RoleIfaceVO vo){
    	return roleDAO.insertRole(vo)>0;
    }
	
    public boolean update(RoleIfaceVO vo){
    	return roleDAO.updateRole(vo)>0;
    }
    
    public List<RoleIfaceVO> isExitCodeOrName(RoleIfaceVO vo){
    	return roleDAO.isExitCodeOrName(vo);
    }
    
    public boolean delete(long id){
    	return roleDAO.deleteRole(id)>0;
    }
    
    public boolean insertAppRole(AppRoleIfaceVO vo){
    	return roleDAO.insertAppRole(vo)>0;
    }
    public boolean deleteAppRole(long id){
    	return roleDAO.deleteAppRole(id)>0;
    }
}
