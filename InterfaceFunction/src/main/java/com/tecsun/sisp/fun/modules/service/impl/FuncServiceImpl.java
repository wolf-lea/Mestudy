package com.tecsun.sisp.fun.modules.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.fun.modules.dao.impl.FuncDAO;
import com.tecsun.sisp.fun.modules.entity.func.AppIfaceVO;
import com.tecsun.sisp.fun.modules.entity.role.AppRoleIfaceVO;


@Service("FuncService")
public class FuncServiceImpl {

    @Autowired
    private FuncDAO funcDAO;

    public List<AppIfaceVO> getAppList(AppIfaceVO vo){
    	return funcDAO.getAppList(vo);
    }
    
    public boolean insert(AppIfaceVO vo){
    	return funcDAO.insertApp(vo)>0;
    }
    
    public boolean update(AppIfaceVO vo){
    	return funcDAO.updateApp(vo)>0;
    }
    
    public List<AppIfaceVO> isExitCodeOrName(AppIfaceVO vo){
    	return funcDAO.isExitCodeOrName(vo);
    }
    
    public boolean delete(long id){
    	return funcDAO.deleteApp(id)>0;
    }
}
