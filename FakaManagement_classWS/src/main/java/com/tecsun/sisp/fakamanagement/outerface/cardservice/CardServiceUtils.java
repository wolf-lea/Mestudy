package com.tecsun.sisp.fakamanagement.outerface.cardservice;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.Dom4JUtil;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang.StringUtils;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Map;

public class CardServiceUtils {
	
	public static String invoke(String param)throws ServiceException,MalformedURLException,RemoteException{
        String re = "";
        try {
            Call call=(Call)(new Service()).createCall();
//            String url="http://192.168.1.178:9005/jlCardService/services/CardService";
            String url=Config.getInstance().get("card_endpoint");
            call.setTargetEndpointAddress(new URL(url));
            call.setOperationName(new QName(Config.getInstance().get("card_namespace"),"allDsjk"));
            call.setTimeout(30000);
            System.out.println(url);
            System.out.println(param);

            re = (String) call.invoke(new String[]{param});
            re = "<root>" + re + "</root>";
            System.out.println(re);
            Map<String, String> map=Dom4JUtil.readXmlToMap(re);
            if(map.containsKey("ERR")){
            	re=map.get("ERR");
            }else{
            	re="";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return re;
    }
	
	public static String invoke(String method,String idCard,String name,String socialCard,String cityId)throws ServiceException,MalformedURLException,RemoteException{
        Call call=(Call)(new Service()).createCall();
        call.setTargetEndpointAddress(new URL(Config.getInstance().get("card_endpoint")));
        call.setOperationName(new QName(Config.getInstance().get("card_namespace"),method));
        call.setTimeout(30000);
        String re;
        if(StringUtils.isEmpty(socialCard)) {
            re = (String) call.invoke(new String[]{Config.getInstance().get("card_user"), Config.getInstance().get("card_pwd"), idCard, name,cityId});
        }else{
            re = (String) call.invoke(new String[]{Config.getInstance().get("card_user"), Config.getInstance().get("card_pwd"), socialCard,idCard, name,cityId});
        }
        re = "<root>" + re + "</root>";
        return re;
    }

    public static String allDsjk(String xml) throws Exception{
	    String url=Config.getInstance().get("card_endpoint");
	    String qName=Config.getInstance().get("card_namespace");
//        System.out.println("入参："+xml);
//	    System.out.println("地址："+url);
        Service service = new Service();
        Call call;
        String wsResult = "";
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress( new URL(url));
                    call.setOperationName( new QName(qName,"allDsjk"));// 设置要调用哪个方法
                            call.setSOAPActionURI(qName + "allDsjk");
                                    call.addParameter(new QName(qName, "xml"), XMLType.SOAP_STRING, ParameterMode.IN);//设置传入参数的属性
            call.setTimeout(60000);
            call.setReturnType(XMLType. SOAP_STRING); // 要返回的数据类型（自定义类型）
            wsResult = (String) call.invoke(new String[]{xml});/// 调用方法并传递参数
            return wsResult;
        } catch (Exception e) {
            throw new Exception("调用allDsjk接口异常！",e);
        }
    }

    //临时卡发放--获取省厅数据
    public static String fakaAllDsjk(String xml) throws Exception{
        String url=Config.getInstance().get("card_endpoint");
        String qName=Config.getInstance().get("card_namespace");
        Service service = new Service();
        Call call;
        String wsResult = "";
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress( new URL(url));
            call.setOperationName( new QName(qName,"allDsjk"));// 设置要调用哪个方法
            call.setSOAPActionURI(qName + "allDsjk");
            call.addParameter(new QName(qName, "xml"), XMLType.SOAP_STRING, ParameterMode.IN);//设置传入参数的属性
            call.setTimeout(60000);
            call.setReturnType(XMLType. SOAP_STRING); // 要返回的数据类型（自定义类型）
            wsResult = (String) call.invoke(new String[]{xml});/// 调用方法并传递参数
            return wsResult;
        } catch (Exception e) {
            throw new Exception("调用allDsjk接口异常！",e);
        }
    }


    public static void main(String[] args) throws Exception{
        String param="<操作*>制卡统计数据查询</操作*><用户名*>DS96666</用户名*><密码*>123456</密码*><批次号*>4446</批次号*>";
//        String param= "<操作*>制卡明细数据查询</操作*><用户名*>DS96666</用户名*>" + "<密码*>123456</密码*><批次号*>4446</批次号*><页号*>1</页号*>";
        String  res=CardServiceUtils.allDsjk(param);
        /*Set<String> set= map.keySet();
        for(String key:set){
            String value=map.get(key);
            System.out.println(key+":"+value);
        }*/
        System.out.println("返回结果："+res);
    }

}

