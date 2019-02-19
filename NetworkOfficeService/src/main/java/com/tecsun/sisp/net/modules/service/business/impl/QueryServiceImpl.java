package com.tecsun.sisp.net.modules.service.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.net.common.ResponseCode;
import com.tecsun.sisp.net.exception.ServiceException;
import com.tecsun.sisp.net.modules.common.XZEnum;
import com.tecsun.sisp.net.modules.dao.dr.QueryDao;
import com.tecsun.sisp.net.modules.entity.business.QueryVo;
import com.tecsun.sisp.net.modules.service.business.QueryService;

@Service
public class QueryServiceImpl implements QueryService{
	
	@Autowired
	private QueryDao queryDao;

	@Override
	public Map<String, Object> getGrsjjl(QueryVo qv) {
		//通过个人编号查询个人信息(为什么要单独拆开查询,因为会有许多业务要用到个人信息,单独出来,比较好)
		Map<String,Object> map = queryDao.getGrxxByGrbh(qv.getGrbh());
		//查询参保日期
		String cbrq = queryDao.getCbrq(qv.getGrbh());
		//查询缴至年月
		String jzny = queryDao.getJzny(qv.getGrbh());
		if(map==null || map.isEmpty()){
			//抛出异常,让exceptionHandler捕获
			throw new ServiceException(ResponseCode.BUSINESS,"没有查到个人参保信息");
		}
		map.put("cbrq", cbrq);
		map.put("jzny", jzny);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		XZEnum xzEnum = XZEnum.valueOf(qv.getXz());//此处应该不会出现转换失败的情况,因为在拦截器中已经判断过了
		switch (xzEnum) {
			case xz11:
				list = queryDao.getGrsjjl(qv);
				break;
			case xz12:
				
				break;
			case xz14:
				
				break;
			case xz15:
				
				break;
			case xz21:
				
				break;
			case xz31:
				
				break;	
			case xz32:
				
				break;
			case xz33:
				
				break;
			case xz34:
				
				break;
			case xz35:
				
				break;
			case xz36:
				
				break;
			case xz37:
				
				break;
			case xz41:
				
				break;
			case xz42:
				
				break;
			case xz51:
				
				break;
			case xz61:
				
				break;
			default:
				
				break;
		}

		map.put("detail", list);
		return map;
	}

	@Override
	public List<Map<String, Object>> getGrcbzm(String grbh) {
		return queryDao.getGrcbzm(grbh);
	}


	@Override
	public Map<String, Object> getLhjyryjfjs(QueryVo qv) {
		List<Map<String, Object>> mapList = queryDao.getGrxxByIdcard(qv.getIdcard());
		if(mapList==null || mapList.isEmpty()){
			//抛出异常,让exceptionHandler捕获
			throw new ServiceException(ResponseCode.BUSINESS,"没有查到个人参保信息");
		}else if(mapList.size()>1){
			throw new ServiceException(ResponseCode.BUSINESS,"查询到多个账户,请合并");
		}else{
			Map<String, Object> map = mapList.get(0);
			qv.setGrbh((String)map.get("AAC001"));
			List<Map<String, Object>> list = queryDao.getLhjyryjfjs(qv);
			map.put("detail", list);
			return map;
		}
		
	}

	@Override
	public List<String> getGrbh(String idcard,String xz) {
		return queryDao.getGrbh(idcard,xz);
	}

	@Override
	public Map<String, Object> getGrylbxnzhxx(QueryVo qv) {
		//通过个人编号查询个人信息(为什么要单独拆开查询,因为会有许多业务要用到个人信息,单独出来,比较好)
		Map<String,Object> map = queryDao.getGrxxByGrbh(qv.getGrbh());
		if(map==null || map.isEmpty()){
			//抛出异常,让exceptionHandler捕获
			throw new ServiceException(ResponseCode.BUSINESS,"没有查到个人参保信息");
		}
		//在查询年账户信息
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		XZEnum xzEnum = XZEnum.valueOf(qv.getXz());//此处应该不会出现转换失败的情况,因为在拦截器中已经判断过了
		switch (xzEnum) {
			case xz11:
				list = queryDao.getGrylbxnzhxx(qv);
				break;
			case xz12:
				
				break;
			case xz14:
				
				break;
			case xz15:
				
				break;
			case xz21:
				
				break;
			case xz31:
				
				break;	
			case xz32:
				
				break;
			case xz33:
				
				break;
			case xz34:
				
				break;
			case xz35:
				
				break;
			case xz36:
				
				break;
			case xz37:
				
				break;
			case xz41:
				
				break;
			case xz42:
				
				break;
			case xz51:
				
				break;
			case xz61:
				
				break;
			default:
				
				break;
		}
		
		map.put("detail", list);
		return map;
	}

	
}
