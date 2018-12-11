package com.tecsun.sisp.iface.server.controller.card;


import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.iface.common.util.Config;
import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.common.util.Dom4JUtil;
import com.tecsun.sisp.iface.common.util.ErrorResult;
import com.tecsun.sisp.iface.common.util.GlobalVariable;
import com.tecsun.sisp.iface.common.vo.card.param.IdCardAndNameBean;
import com.tecsun.sisp.iface.common.vo.common.TransBean;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.util.DictionaryUtil;
import com.tecsun.sisp.iface.server.util.ThreadPoolTask;
import com.tecsun.sisp.iface.server.util.ThreadPoolUtil;
import com.tecsun.sisp.iface.server.util.webservice.InvokeCardService;

@Controller
@RequestMapping(value = "/iface/card")
public class CardController extends BaseController {

    public final static Logger logger = Logger.getLogger(CardController.class);
    
    /**
     * 区域权限控制getAC01（new:getRysj）//20171027
     * @return invoke返回
     * err:ErrorResult
     */
    public Object getAC01( IdCardAndNameBean bean) {
        String aac002 = bean.getAac002();
        String aac003 = bean.getAac003();
        if(StringUtils.isEmpty(aac002)) return this.result(GlobalVariable.RESULT_PARAM_WRONG,"身份证号不能为空");
        if(StringUtils.isEmpty(aac003)) return this.result(GlobalVariable.RESULT_PARAM_WRONG,"姓名不能为空");
        
        try {
        	String ret = InvokeCardService.invoke("getRysj", aac002, aac003, null, null);//20171026 old(getAC01)
            Element rootElement = DocumentHelper.parseText(ret).getRootElement();
            Element err = rootElement.element("ERR");
            if(err != null) {
                if (err.getText().equals("OK")) {
                	bean.setAab301( rootElement.element("AAB301").getText() );
                }
                else
                    return this.result(GlobalVariable.RESULT_WEBSERVICE_NOT_DATA, GlobalVariable.STRING_REQUEST_WEBSERVICE_ERROR + err.getText());
            }
            
            return ret;
        }catch(Exception e){
            return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        } 
    }

    @RequestMapping(value="/cardInfo", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    /**
     * 2.17卡数据和相片cardInfo
     */
    public Object cardInfo(@RequestBody IdCardAndNameBean bean , HttpServletRequest request){
    	insertTransLog(bean,Constants.BUSINESSCODE.get("cardInfo"));
    	Object ac01Info = getAC01(bean);
    	if( ac01Info != null && ac01Info instanceof ErrorResult ){
    		return ac01Info;
    	}
        String aac002 = bean.getAac002();
        String aac003 = bean.getAac003();
        String aab301 = bean.getAab301();
        
        try {
        	aab301 = getThirdAreaId(request.getParameter("method"), request.getParameter("tokenId"), aab301);
        }catch (Exception e){
            return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }
        
        Map<String,String> map;
        try {
            map = Dom4JUtil.readXmlToMap((String) ac01Info);
        }catch (Exception e){
            return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }
        
        //getPhoto
        try {
        	String photo = InvokeCardService.invoke("getPhoto", aac002, aac003, "",aab301);//20171027
        	Map<String,String> mapPhoto = Dom4JUtil.readXmlToMap( photo);
        	map.put("PHOTO", mapPhoto.get("PHOTO"));
		} catch (Exception e) {
		}

        if(map.containsKey("ERR")){
            if("OK".equals(map.get("ERR"))){
            	String aac005 = map.get("AAC005");
            	aac005 = DictionaryUtil.getDictName(Constants.PARAM_NATION_GROUP, aac005);
                if ( aac005!=null && !aac005.isEmpty() ) {
                	map.remove("AAC005");
                	map.put("AAC005", aac005);
				}
                
                //20171027 兼容删除字段
                if (map.get("AAB001") == null)
                	map.put("AAB001", "");
                if (map.get("AAE005") == null)
					map.put("AAE005", "");
                if (map.get("AAC010") == null)
                	map.put("AAC010", "");
                if (map.get("AAC009") == null)
                	map.put("AAC009", "");
                if (map.get("AAC008") == null)
                	map.put("AAC008", "");
                if (map.get("AAC006") == null)
                	map.put("AAC006", "");
                
                return this.result(GlobalVariable.RESULT_SUCCESS,GlobalVariable.STRING_QUERY_SUCCESS,map);
            }else{
                return this.result(GlobalVariable.RESULT_WEBSERVICE_NOT_DATA,GlobalVariable.STRING_REQUEST_WEBSERVICE_ERROR + map.get("ERR"));
            }
        }
        return this.result(GlobalVariable.RESULT_SERVER_WRONG,GlobalVariable.STRING_SERVER_WRONG);
    }

    @RequestMapping(value="/cardStatus", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    /**
     * 2.18卡状态查询cardStatus
     */
    public Object cardStatus(@RequestBody IdCardAndNameBean bean ,  HttpServletRequest request){
    	insertTransLog(bean,Constants.BUSINESSCODE.get("cardStatus"));
    	Object ac01Info = getAC01(bean);
    	if( ac01Info != null && ac01Info instanceof ErrorResult ){
    		return ac01Info;
    	}
        String aac002 = bean.getAac002();
        String aac003 = bean.getAac003();
        String aab301 = bean.getAab301();
        
        String aaz500 = "";
        try {
            Element rootElement = DocumentHelper.parseText((String)ac01Info).getRootElement();
            Element err = rootElement.element("ERR");
            if(err != null) {
                if (err.getText().equals("OK")) {
                    aaz500 = rootElement.element("AAZ500").getText();
                    aab301 = rootElement.element("AAB301").getText();
                }
                else
                    return this.result(GlobalVariable.RESULT_WEBSERVICE_NOT_DATA, GlobalVariable.STRING_REQUEST_WEBSERVICE_ERROR + err.getText());
            }
        }catch(Exception e){
            return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }

        String result = "";
        try {
        	aab301 = getThirdAreaId(request.getParameter("method"), request.getParameter("tokenId"), aab301);
            result = InvokeCardService.invoke("getCard", aac002, aac003, aaz500,aab301);
        }catch (Exception e){
            return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }

        try {
            result = DocumentHelper.parseText(result).getRootElement().getText();
        }catch (DocumentException e){
            return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }

        if("OK".equals(result)) {
            return this.result(GlobalVariable.RESULT_SUCCESS,"卡状态正常");
        }else{
            return this.result(GlobalVariable.RESULT_WEBSERVICE_NOT_DATA,result);
        }

    }

    @RequestMapping(value="/cardProgress", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    /**
     * 2.19制卡进度查询cardProgress
     */
    public Object cardProgress(@RequestBody IdCardAndNameBean bean, HttpServletRequest request){
    	insertTransLog(bean,Constants.BUSINESSCODE.get("cardProgress"));
    	Object ac01Info = getAC01(bean);
    	if( ac01Info != null && ac01Info instanceof ErrorResult ){
    		return ac01Info;
    	}
        String aac002 = bean.getAac002();
        String aac003 = bean.getAac003();
        String aab301 = bean.getAab301();

        String cardInfo;
        try {
        	aab301 = getThirdAreaId(request.getParameter("method"), request.getParameter("tokenId"), aab301);
//            cardInfo = InvokeCardService.invoke("getAZ03", aac002, aac003, "",aab301);//old
            cardInfo = InvokeCardService.invoke("getZkjd", aac002, aac003, "",aab301);//20171027
        }catch (Exception e){
            return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }

        Map<String,String> map;
        try {
            Document doc = DocumentHelper.parseText(cardInfo);
            Element err = doc.getRootElement().element("ERR");
            if(err.elements().size() == 0){
                if("OK".equals(err.getText())) {
                    map = new HashMap<String, String>();
                    List<Element> list = doc.getRootElement().elements();
                    for (Iterator<Element> it = list.iterator(); it.hasNext(); ) {
                        Element e = it.next();
                        map.put(e.getName(), e.getText());
                    }
                    if(map.containsKey("AAE008")){
                        String AAE008 = map.get("AAE008");
                        AAE008 = DictionaryUtil.getDictName(Constants.BANK_GROUP, AAE008);
                        if ( AAE008!=null && !AAE008.isEmpty() ) {
                        	map.remove("AAE008");
                        	map.put("AAE008", AAE008);
						}
                    }
                    return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, map);
                }else{
                    return this.result(GlobalVariable.RESULT_WEBSERVICE_NOT_DATA,GlobalVariable.STRING_REQUEST_WEBSERVICE_ERROR + err.getText());
                }
            }else{
                map = new HashMap<String, String>();
                List<Element> list = err.elements();
                for(Iterator<Element> it= list.iterator();it.hasNext();){
                    Element e = it.next();
                    map.put(e.getName(),e.getText());
                }
                return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,"制卡失败",map);
            }
        }catch (Exception e){
            return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }
    }
    

    @RequestMapping(value="/cardLossReport", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Object cardLossReport(@RequestBody IdCardAndNameBean bean, HttpServletRequest request){
    	insertTransLog(bean,Constants.BUSINESSCODE.get("cardLossReport"));
    	Object ac01Info = getAC01(bean);
    	if( ac01Info != null && ac01Info instanceof ErrorResult ){
    		return ac01Info;
    	}
        String aac002 = bean.getAac002();
        String aac003 = bean.getAac003();
        String aab301 = bean.getAab301();

        String aaz500 = "";
        try {
            Element rootElement = DocumentHelper.parseText( aab301 ).getRootElement();
            Element err = rootElement.element("ERR");
            if(err != null) {
                if (err.getText().equals("OK")) {
                    aaz500 = rootElement.element("AAZ500").getText();
                    aab301 = rootElement.element("AAB301").getText();
                    aab301 = getThirdAreaId(request.getParameter("method"), request.getParameter("tokenId"), aab301);
                }
                else
                    return this.result(GlobalVariable.RESULT_WEBSERVICE_NOT_DATA, GlobalVariable.STRING_REQUEST_WEBSERVICE_ERROR + err.getText());
            }
        }catch(Exception e){
            return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }

        StringBuilder param = new StringBuilder();
        param.append("<操作*>临时挂失</操作*><用户名*>" + Config.getInstance().get("card_user") + "</用户名*><密码*>" +
                Config.getInstance().get("card_pwd") + "</密码*><城市代码*>" +
                aab301 + "</城市代码*><社会保障卡卡号*>");
        param.append(aaz500);
        param.append("</社会保障卡卡号*><社会保障号码*>");
        param.append(aac002);
        param.append("</社会保障号码*><姓名*>");
        param.append(aac003);
        param.append("</姓名*><开户银行></开户银行><银行卡号></银行卡号>");
        String re = "";
        try {
            Call call=(Call)(new Service()).createCall();
            call.setTargetEndpointAddress(new URL(Config.getInstance().get("card_endpoint")));
            call.setOperationName(new QName(Config.getInstance().get("card_namespace"),"allDsjk"));
            call.setTimeout(30000);
            re = (String) call.invoke(new String[]{param.toString()});
            re = "<root>" + re + "</root>";
        }catch (Exception e){
            return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }

        Map<String,String> map;
        try {
            map = Dom4JUtil.readXmlToMap(re);
        }catch (Exception e){
            return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }

        if(map.containsKey("ERR")){
            String err = map.get("ERR");
            if(err.contains("成功"))
                return this.result(GlobalVariable.RESULT_SUCCESS,map.get("ERR"));
            else{
                return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,map.get("ERR"));
            }
        }
        return this.result(GlobalVariable.RESULT_SERVER_WRONG,GlobalVariable.STRING_SERVER_WRONG);
    }

    public void insertTransLog(IdCardAndNameBean bean,String funcName){
        try {
            TransBean transBean=new TransBean();
            transBean.setUserid(bean.getAac002());
            transBean.setChannelcode(bean.getChannelcode());
            transBean.setBusinesscode(funcName);
            transBean.setDeviceid(bean.getDeviceid());
            ThreadPoolUtil.getThreadPool().execute(new ThreadPoolTask(transBean));
        } catch (Exception e) {
            logger.error("添加卡业务操作记录到业务分析子系统出错：" + e.getMessage());
        }
    }
}

