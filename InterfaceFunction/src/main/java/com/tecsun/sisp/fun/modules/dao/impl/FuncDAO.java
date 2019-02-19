package com.tecsun.sisp.fun.modules.dao.impl;

import java.util.List;

import com.tecsun.sisp.fun.common.MyBatisDao;
import com.tecsun.sisp.fun.modules.entity.func.AppIfaceVO;
import com.tecsun.sisp.fun.modules.entity.role.AppRoleIfaceVO;



@MyBatisDao
public interface FuncDAO {

	public List<AppIfaceVO> getAppList(AppIfaceVO vo);
	
	public long insertApp(AppIfaceVO vo);
	
	public long updateApp(AppIfaceVO vo);
	
	public List<AppIfaceVO> isExitCodeOrName(AppIfaceVO vo);
	
	public long deleteApp(long id);
}
