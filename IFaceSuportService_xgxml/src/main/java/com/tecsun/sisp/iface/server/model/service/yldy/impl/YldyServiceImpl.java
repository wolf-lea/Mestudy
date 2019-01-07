package com.tecsun.sisp.iface.server.model.service.yldy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.iface.common.vo.Result;
import com.tecsun.sisp.iface.server.model.dao_dr.YldyDao;
import com.tecsun.sisp.iface.server.model.dao_yldygxhcs.YldygxhcsDao;
import com.tecsun.sisp.iface.server.model.service.yldy.YldyService;

@Service
public class YldyServiceImpl implements YldyService{
	
	@Autowired
	private YldyDao yldyDao;
	
	@Autowired
	private YldygxhcsDao yldygxhcsDao;

	@Override
	public Result getGxhcs(String idcard, String aic161, String aic162, String jfdc) {

		//先根据身份证号查询个人编号,取养老保险的那个个人编号
		List<String> aac001 = yldyDao.getAAC001(idcard);
		if(aac001!=null && aac001.size()>1){
			//2.若有多个养老保险的个人编号,直接提示用户做账户合并
			return new Result("309","查询到有多个基本养老保险,请先合并账户!",null);
		}else if(aac001==null  || aac001.isEmpty()){
			return new Result("0","未查到养老保险记录!",null);
		}else{
			//只有一条个人编号
			//拿到唯一的grbh 个人编号后去hbxgcs用户下调用存储过程,查询出测算结果,返回给页面
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("aac001", aac001.get(0));
			paramMap.put("aic161", aic161);
			paramMap.put("aic162", aic162);
			paramMap.put("jfdc", jfdc);
			
			paramMap.put("PRM_AIC142_101", "");
			paramMap.put("PRM_AIC142_102", "");
			paramMap.put("PRM_AIC142_201", "");
			paramMap.put("PRM_AIC142_202", "");
			paramMap.put("PRM_AIC142_203", "");
			paramMap.put("PRM_APPCODE", "");
			paramMap.put("PRM_ERRORMSG", "");
			
			yldygxhcsDao.getGxhcs(paramMap);
			return new Result("200","success",paramMap);
		}
		
	}

}
