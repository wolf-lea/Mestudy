package com.tecsun.sisp.iface.common.util.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.wsdl.Service;

import org.apache.axis.client.Call;

import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.server.entity.requestBean.personParam;

/**
 * @title
 * @author zengyunhua
 * @2018年12月29日-上午10:08:09
 * @version2018
 */
public class InterfaceKeyWS {
	

	
	public static String getPersonnelXML(personParam bean) {
		StringBuffer strBuf = new StringBuffer();
		//strBuf.append("<?xml version='1.0' encoding='utf-8'?>");
		strBuf.append("<USER>41990001A</USER>");
		strBuf.append("<m>");
		strBuf.append("<ID>人员查询</ID>");// 需要做哪方面查询固定
		strBuf.append("<PASS>41990001A</PASS>");// 密码
		strBuf.append("<AAC002>"+bean.getAac002()+"</AAC002>");// 社保卡号
		strBuf.append("<TYPEID>A00001</TYPEID>");// 姓名
		strBuf.append("</m>");
		return strBuf.toString();
		
	}


}
