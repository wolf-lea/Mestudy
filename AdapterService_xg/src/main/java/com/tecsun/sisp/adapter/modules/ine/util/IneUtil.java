package com.tecsun.sisp.adapter.modules.ine.util;


import com.tecsun.sisp.adapter.modules.ine.entity.request.IneBusBean;
import com.tecsun.sisp.adapter.modules.ine.entity.request.IneQueryBean;

/**
 *@author sipeng
 *@email zsp_banyue@163.com
 *@date 2017年11月2日
 * 
 */
public class IneUtil {
	
	/**
	 * 将查询条件的bean转换为业务操作bean
	 * @param ineQueryBean
	 * @return
	 */
	public static IneBusBean coverIneQueryBeanToIneBusBean(IneQueryBean ineQueryBean){
		IneBusBean bean = new IneBusBean();
		bean.setInfoCode(ineQueryBean.getInfoId());
		bean.setSfzh(ineQueryBean.getSfzh());
		bean.setXm(ineQueryBean.getXm());
		bean.setTel(ineQueryBean.getTel());
		bean.setInfoType(ineQueryBean.getQueryType());
		return  bean;
		
	}
	
}
