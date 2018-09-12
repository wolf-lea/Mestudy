package com.tecsun.sisp.iface.server.util.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang.StringUtils;
import com.tecsun.sisp.iface.common.util.Config;

/*import com.wei.server.CardServiceServiceLocator;
import com.wei.server.CardServiceSoapBindingStub;*/

/**
 * Created by DG on 2015/10/19.
 */
public class InvokeCardService {

	public static boolean DEBUG_MODE = true;//调试标记
	
    public static String invoke(String method,String idCard,String name,String socialCard,String cityId)throws ServiceException,MalformedURLException,RemoteException{
    	
    	Call call=(Call)(new Service()).createCall();
        call.setTargetEndpointAddress(new URL(Config.getInstance().get("card_endpoint")));
        call.setOperationName(new QName(Config.getInstance().get("card_namespace"),method));
        call.setTimeout(30000);
        String re;
        /*if(StringUtils.isEmpty(cityId))
            cityId = Config.getInstance().get("card_area");*/
        if(StringUtils.isEmpty(socialCard)) {
            re = (String) call.invoke(new String[]{Config.getInstance().get("card_user"), Config.getInstance().get("card_pwd"), idCard, name,cityId});
        }else{
            re = (String) call.invoke(new String[]{Config.getInstance().get("card_user"), Config.getInstance().get("card_pwd"), socialCard,idCard, name,cityId});
        }
        re = "<root>" + re + "</root>";
        
        if( DEBUG_MODE ){
        	System.out.println( "method:" + method );
        	System.out.println( re );
        }
        return re;
    }

    public static void main(String [] args) throws Exception{
//        invoke("old getAC01", "410181199011075026", "白萌", "", "");
//       invoke("getData", "410181199011075026", "白萌", "", "");
//        invoke("getAZ03", "410181199011075026", "白萌", "", "");
        
        //1.1	制卡进度查询
//        invoke("getZkjd", "410181199011075026", "白萌", "", "");
        
//       1.2	人员数据查询
//        invoke("getRysj", "410181199011075026", "白萌", "", "");
//        1.3	相片查询
//        invoke("getPhoto", "410181199011075026", "白萌", "", "");
//      1.4	指纹查询
//        invoke("getFinger", "410181199011075026", "白萌", "", "");
////    1.5	银行帐户查询（新）
//        invoke("getBank01", "410181199011075026", "白萌", "", "");
//
//        System.out.println(result);*/
//        //System.out.println(DocumentHelper.parseText(result).getRootElement().getText());
//
//        StringBuilder param = new StringBuilder();
//        param.append("<操作*>临时挂失</操作*><用户名*>" + "szytj" + "</用户名*><密码*>" +
//                "szytj12333" + "</密码*><城市代码*>" +
//                Config.getInstance().get("card_area") + "</城市代码*><社会保障卡卡号*>");
//        param.append("A55662979");
//        param.append("</社会保障卡卡号*><社会保障号码*>");
//        param.append("412829198906262818");
//        param.append("</社会保障号码*><姓名*>");
//        param.append("张崇太");
//        param.append("</姓名*><开户银行></开户银行><银行卡号></银行卡号>");
//        System.out.println(param.toString());
//        String re = "";
//        try {
//            Call call=(Call)(new Service()).createCall();
//            call.setTargetEndpointAddress(new URL(Config.getInstance().get("card_endpoint")));
//            call.setOperationName(new QName(Config.getInstance().get("card_namespace"),"allDsjk"));
//            call.setTimeout(30000);
//            re = (String) call.invoke(new String[]{param.toString()});
//            System.out.println(re);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    	
//    	String aac002 = "4101811990110750726";
//    	String aac003 = "白萌";
//    	
//    	CardServiceSoapBindingStub stub = (CardServiceSoapBindingStub) new CardServiceServiceLocator().getCardService();
//    	
//    	String ac01 = stub.getAC01(Config.getInstance().get("card_user"), Config.getInstance().get("card_pwd"), aac002, aac003, "");
//    	System.out.println(ac01);
//    	String rysj = stub.getRysj(Config.getInstance().get("card_user"), Config.getInstance().get("card_pwd"), aac002, aac003, "");
//    	System.out.println(rysj);
//    	
    }

}
