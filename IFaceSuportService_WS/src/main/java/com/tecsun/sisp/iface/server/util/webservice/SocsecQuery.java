package com.tecsun.sisp.iface.server.util.webservice;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tecsun.sisp.iface.common.util.Config;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;

/**
 * 社保养老查询和工伤查询WS调用类
 */
public class SocsecQuery {
	private static Logger logger = LoggerFactory.getLogger(SocsecQuery.class);

//	public static void main(String[] args)  {
//		SecQueryBean bean=new SecQueryBean();
//		bean.setBusiness("GSBXDYXXXX");
//		bean.setAae080("200909");
		//（工伤待遇详情）安军晓 411081197301210181
		//（工伤医疗报销）安乐  62262719791119307X
		//bean.setAac003("薛峰");
		//bean.setAac002("410102196812290552");
		//bean.setAac003("辛怀增");
		//bean.setAac002("410602195007150533");
		//bean.setAac003("安乐");
		//bean.setAac002("62262719791119307X");
		//bean.setAac003("安军晓");
		//bean.setAac002("411081197301210181");
//		bean.setAac002("411081196306130151");
//		bean.setAac003("曹元平");
		//String xml = getEndowmentXML(bean);
//		String xml= getWorkInjuryXML(bean);
//		invokeWS(xml);
//	}

	public static String invokeWS(String xml) {
		String result="";
		Object[] response = null;
		String method = "funcInterface";
		try {
			Object[] paras = { xml };
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			options.setTimeOutInMilliSeconds(60000L);// 根据实际情况设置超时时间
			EndpointReference targetEPR = new EndpointReference(Config.getInstance().get("secquery_endpoint"));
			options.setTo(targetEPR);
			QName qName = new QName(Config.getInstance().get("secquery_namespace"), method);
			Class<?>[] returnTypes = new Class[] { String.class };
			response = serviceClient.invokeBlocking(qName, paras, returnTypes);
			if(response!=null && response.length > 0){
				result=response[0].toString();
//				System.out.println(response.length);
//				System.out.println(response[0]);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("----查询失败---", e.getCause());
		}		
		return result;
	}

	// 养老保险信息查询
	public static String getEndowmentXML(SecQueryBean bean) {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("<?xml version='1.0' encoding='utf-8'?>");
		strBuf.append("<ROOT>");
		strBuf.append("<INPUT>");
		strBuf.append("<BUSINESS>"+bean.getBusiness()+"</BUSINESS>");// 业务编码
		strBuf.append("<AAC002>"+bean.getAac002()+"</AAC002>");// 身份证
		strBuf.append("<AAE140>11</AAE140>");// 险种编码
		strBuf.append("<AAC003>"+bean.getAac003()+"</AAC003>");// 姓名
		// 实付年月
		if("YLJFFXX".equals(bean.getBusiness())){
			strBuf.append("<AAE080>"+bean.getAae080()+"<AAE080>");
		}else{
			strBuf.append("<AAE080>200308</AAE080>");
		}
		strBuf.append("<AAE117>1</AAE117>");//支付状态
		strBuf.append("</INPUT>");
		strBuf.append("</ROOT>");
		return strBuf.toString();
	}

	// 工伤保险信息查询
	public static String getWorkInjuryXML(SecQueryBean bean) {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("<?xml version='1.0' encoding='utf-8'?>");
		strBuf.append("<ROOT>");
		strBuf.append("<INPUT>");
		strBuf.append("<BUSINESS>"+bean.getBusiness()+"</BUSINESS>");// 业务编码
		strBuf.append("<AAC002>"+bean.getAac002()+"</AAC002>");// 身份证
		strBuf.append("<AAE140>41</AAE140>");// 险种编码
		strBuf.append("<AAC003>"+bean.getAac003()+"</AAC003>");// 姓名
		strBuf.append("<AAE080>200308</AAE080>");// 实付年月
		strBuf.append("<AAE117>1</AAE117>");//支付状态
		//strBuf.append("<AAE114>"+bean.getAae114()+"</AAE114>");//缴费标志
		strBuf.append("</INPUT>");
		strBuf.append("</ROOT>");
		return strBuf.toString();
	}
	
	


}
