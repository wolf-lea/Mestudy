package com.tecsun.sisp.iface.server.model.service.employment.impl;

import com.tecsun.sisp.iface.common.vo.Result;
import com.tecsun.sisp.iface.common.vo.employment.ColumnBean;
import com.tecsun.sisp.iface.common.vo.employment.RowBean;
import com.tecsun.sisp.iface.common.vo.employment.param.queryParamBean;
import com.tecsun.sisp.iface.common.vo.employment.result.*;
import com.tecsun.sisp.iface.server.model.service.employment.EmpQueryService;
import com.tecsun.sisp.iface.common.util.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pear on 2015/8/5.
 */
@Service("empQueryServiceImpl")
public class EmpQueryServiceImpl implements EmpQueryService{
    private static Logger logger = LoggerFactory.getLogger(EmpQueryServiceImpl.class);

    
    public Result getRegistrationInfo(queryParamBean bean) {
        Result result = new Result();
        result.setStatusCode(Constants.RESULT_MESSAGE_ERROR);
        String queryXml = getQueryXml(bean);

        if(queryXml.equals("")){
            result.setMessage("参数不能为null");
            result.setData("");
            return result;
        }
        String o = commomFun(Constants.Cc0mUrl,"query",queryXml);
        String check = CheckXmlError(o);

        if(!check.equals("")){
            result.setMessage(check);
            result.setData("");
            return result;
        }

        List<RowBean> list = getValueFromXml(o);
        List<RegistrationInfoBean> registrationInfoBeanList = new ArrayList<RegistrationInfoBean>();

        for (RowBean r : list){
            RegistrationInfoBean registrationInfoBean = new RegistrationInfoBean();
            ChangeData(registrationInfoBean, r);
            registrationInfoBeanList.add(registrationInfoBean);
        }

        result.setStatusCode(Constants.RESULT_MESSAGE_SUCCESS);
        result.setMessage("查询成功");
        result.setData(registrationInfoBeanList);
        return result;
    }

    
    public Result getEmploymentInfo(queryParamBean bean) {
        Result result = new Result();
        result.setStatusCode(Constants.RESULT_MESSAGE_ERROR);
        String queryXml = getQueryXml(bean);

        if(queryXml.equals("")){
            result.setMessage("参数不能为null");
            result.setData("");
            return result;
        }
        String o = commomFun(Constants.Cc03Url,"query",queryXml);
        String check = CheckXmlError(o);

        if(!check.equals("")){
            result.setMessage(check);
            result.setData("");
            return result;
        }

        List<RowBean> list = getValueFromXml(o);
        List<EmploymentInfoBean> employmentInfoBeanList = new ArrayList<EmploymentInfoBean>();

        for (RowBean r : list){
            EmploymentInfoBean employmentInfoBean = new EmploymentInfoBean();
            ChangeData(employmentInfoBean, r);
            employmentInfoBeanList.add(employmentInfoBean);
        }

        result.setStatusCode(Constants.RESULT_MESSAGE_SUCCESS);
        result.setMessage("查询成功");
        result.setData(employmentInfoBeanList);
        return result;
    }

    
    public Result getUnEmploymentInfo(queryParamBean bean) {
        Result result = new Result();
        result.setStatusCode(Constants.RESULT_MESSAGE_ERROR);
        String queryXml = getQueryXml(bean);

        if(queryXml.equals("")){
            result.setMessage("参数不能为null");
            result.setData("");
            return result;
        }
        String o = commomFun(Constants.Cc02Url,"query",queryXml);
        String check = CheckXmlError(o);

        if(!check.equals("")){
            result.setMessage(check);
            result.setData("");
            return result;
        }

        List<RowBean> list = getValueFromXml(o);
        List<UnEmploymentInfoBean> unEmploymentInfoBeanList = new ArrayList<UnEmploymentInfoBean>();

        for (RowBean r : list){
            UnEmploymentInfoBean unEmploymentInfoBean = new UnEmploymentInfoBean();
            ChangeData(unEmploymentInfoBean, r);
            unEmploymentInfoBeanList.add(unEmploymentInfoBean);
        }

        result.setStatusCode(Constants.RESULT_MESSAGE_SUCCESS);
        result.setMessage("查询成功");
        result.setData(unEmploymentInfoBeanList);
        return result;
    }

    
    public Result getEmployAssistInfo(queryParamBean bean) {
        Result result = new Result();
        result.setStatusCode(Constants.RESULT_MESSAGE_ERROR);
        String queryXml = getQueryXml(bean);

        if(queryXml.equals("")){
            result.setMessage("参数不能为null");
            result.setData("");
            return result;
        }
        String o = commomFun(Constants.Ac13Url,"query",queryXml);
        String check = CheckXmlError(o);

        if(!check.equals("")){
            result.setMessage(check);
            result.setData("");
            return result;
        }

        List<RowBean> list = getValueFromXml(o);
        List<EmployAssistInfoBean> employAssistInfoBeanList = new ArrayList<EmployAssistInfoBean>();

        for (RowBean r : list){
            EmployAssistInfoBean employAssistInfoBean = new EmployAssistInfoBean();
            ChangeData(employAssistInfoBean, r);
            employAssistInfoBeanList.add(employAssistInfoBean);
        }

        result.setStatusCode(Constants.RESULT_MESSAGE_SUCCESS);
        result.setMessage("查询成功");
        result.setData(employAssistInfoBeanList);
        return result;
    }

    
    public Result getEmployPolicyInfo(queryParamBean bean) {
        Result result = new Result();
        result.setStatusCode(Constants.RESULT_MESSAGE_ERROR);
        String queryXml = getQueryXml(bean);

        if(queryXml.equals("")){
            result.setMessage("参数不能为null");
            result.setData("");
            return result;
        }
        String o = commomFun(Constants.Ac14Url,"query",queryXml);
        String check = CheckXmlError(o);

        if(!check.equals("")){
            result.setMessage(check);
            result.setData("");
            return result;
        }

        List<RowBean> list = getValueFromXml(o);
        List<EmployPolicyInfoBean> employPolicyInfoBeanList = new ArrayList<EmployPolicyInfoBean>();

        for (RowBean r : list){
            EmployPolicyInfoBean employPolicyInfoBean = new EmployPolicyInfoBean();
            ChangeData(employPolicyInfoBean, r);
            employPolicyInfoBeanList.add(employPolicyInfoBean);
        }

        result.setStatusCode(Constants.RESULT_MESSAGE_SUCCESS);
        result.setMessage("查询成功");
        result.setData(employPolicyInfoBeanList);
        return result;

    }

    
    public Result getPublicServiceInfo(queryParamBean bean) {
        Result result = new Result();
        result.setStatusCode(Constants.RESULT_MESSAGE_ERROR);
        String queryXml = getQueryXml(bean);

        if(queryXml.equals("")){
            result.setMessage("参数不能为null");
            result.setData("");
            return result;
        }
        String o = commomFun(Constants.Ac15Url,"query",queryXml);
        String check = CheckXmlError(o);

        if(!check.equals("")){
            result.setMessage(check);
            result.setData("");
            return result;
        }

        List<RowBean> list = getValueFromXml(o);
        List<PublicServiceInfoBean> publicServiceInfoBeanList = new ArrayList<PublicServiceInfoBean>();

        for (RowBean r : list){
            PublicServiceInfoBean publicServiceInfoBean = new PublicServiceInfoBean();
            ChangeData(publicServiceInfoBean, r);
            publicServiceInfoBeanList.add(publicServiceInfoBean);
        }

        result.setStatusCode(Constants.RESULT_MESSAGE_SUCCESS);
        result.setMessage("查询成功");
        result.setData(publicServiceInfoBeanList);
        return result;
    }

    
    public Result getUnEmpInsTreatInfo(queryParamBean bean) {
        Result result = new Result();
        result.setStatusCode(Constants.RESULT_MESSAGE_ERROR);
        String queryXml = getQueryXml(bean);

        if(queryXml.equals("")){
            result.setMessage("参数不能为null");
            result.setData("");
            return result;
        }
        String o = commomFun(Constants.Ac16Url,"query",queryXml);
        String check = CheckXmlError(o);

        if(!check.equals("")){
            result.setMessage(check);
            result.setData("");
            return result;
        }

        List<RowBean> list = getValueFromXml(o);
        List<UnEmpInsTreatInfoBean> unEmpInsTreatInfoBeanList = new ArrayList<UnEmpInsTreatInfoBean>();

        for (RowBean r : list){
            UnEmpInsTreatInfoBean unEmpInsTreatInfoBean = new UnEmpInsTreatInfoBean();
            ChangeData(unEmpInsTreatInfoBean, r);
            unEmpInsTreatInfoBeanList.add(unEmpInsTreatInfoBean);
        }

        result.setStatusCode(Constants.RESULT_MESSAGE_SUCCESS);
        result.setMessage("查询成功");
        result.setData(unEmpInsTreatInfoBeanList);
        return result;
    }

    
    public Result getInspectionInfo(queryParamBean bean) {
        Result result = new Result();
        result.setStatusCode(Constants.RESULT_MESSAGE_ERROR);
        String queryXml = getQueryXml(bean);

        if(queryXml.equals("")){
            result.setMessage("参数不能为null");
            result.setData("");
            return result;
        }
        String o = commomFun(Constants.Ac21Url,"query",queryXml);
        String check = CheckXmlError(o);

        if(!check.equals("")){
            result.setMessage(check);
            result.setData("");
            return result;
        }

        List<RowBean> list = getValueFromXml(o);
        List<InspectionInfoBean> inspectionInfoBeanList = new ArrayList<InspectionInfoBean>();

        for (RowBean r : list){
            InspectionInfoBean inspectionInfoBean = new InspectionInfoBean();
            ChangeData(inspectionInfoBean, r);
            inspectionInfoBeanList.add(inspectionInfoBean);
        }

        result.setStatusCode(Constants.RESULT_MESSAGE_SUCCESS);
        result.setMessage("查询成功");
        result.setData(inspectionInfoBeanList);
        return result;
    }

    
    public Result getOtherMattersInfo(queryParamBean bean) {
        Result result = new Result();
        result.setStatusCode(Constants.RESULT_MESSAGE_ERROR);
        String queryXml = getQueryXml(bean);

        if(queryXml.equals("")){
            result.setMessage("参数不能为null");
            result.setData("");
            return result;
        }
        String o = commomFun(Constants.Ac19Url,"query",queryXml);
        String check = CheckXmlError(o);

        if(!check.equals("")){
            result.setMessage(check);
            result.setData("");
            return result;
        }

        List<RowBean> list = getValueFromXml(o);
        List<OtherMattersInfoBean> otherMattersInfoBeanList = new ArrayList<OtherMattersInfoBean>();

        for (RowBean r : list){
            OtherMattersInfoBean otherMattersInfoBean = new OtherMattersInfoBean();
            ChangeData(otherMattersInfoBean, r);
            otherMattersInfoBeanList.add(otherMattersInfoBean);
        }

        result.setStatusCode(Constants.RESULT_MESSAGE_SUCCESS);
        result.setMessage("查询成功");
        result.setData(otherMattersInfoBeanList);
        return result;
    }

    public String commomFun(String url, String method, String condition){
        org.apache.axis.client.Service service = new org.apache.axis.client.Service();
        String result = "";
        try {
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(url);
            call.setOperationName(new QName(Constants.soapaction, method)); // 设置要调用哪个方法
            call.setSOAPActionURI(Constants.soapaction + method);

            call.addParameter(new QName(Constants.soapaction, "xml"), XMLType.SOAP_STRING, ParameterMode.IN);
            call.setReturnType(new QName(Constants.soapaction, method), String.class); //要返回的数据类型（自定义类型）
            result = (String) call.invoke(new Object[]{condition});
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public String CheckXmlError(String xml){

        String str = "";
        SAXReader reader = new SAXReader();
        Document document = null;

        try {
            document = reader.read(new StringReader(xml));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        Element root = document.getRootElement();

        if( root.getName() != null  && root.getName().equals("error")){
            str = root.getText();
        }
        return str;
    }

    public List<RowBean> getValueFromXml(String o) {
        SAXReader reader = new SAXReader();
        Document document = null;

        try {
            document = reader.read(new StringReader(o));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        Element root = document.getRootElement();

        Iterator it = root.elementIterator();
//        ResultBean resultBean = new ResultBean();
        List<RowBean> rowBeanList = new ArrayList<RowBean>();
        while (it.hasNext()) {
            RowBean rowBean = new RowBean();
            List<ColumnBean> columnBeanList = new ArrayList<ColumnBean>();
            Element element = (Element) it.next();
            Iterator attrIt = element.elementIterator();
            while (attrIt.hasNext()) {
                ColumnBean columnBean = new ColumnBean();
                Element element2 = (Element) attrIt.next();
                columnBean.setType(element2.attributeValue("type"));
                columnBean.setName(element2.attributeValue("name"));
                columnBean.setValue(element2.getText());
                columnBeanList.add(columnBean);
            }
            rowBean.setRow(columnBeanList);
            rowBeanList.add(rowBean);
        }
//        resultBean.setResult(rowBeanList);
        return  rowBeanList;
    }


    public String getQueryXml(queryParamBean bean){
        String aac0m1 = bean.getAcc0m1();
        String aac002 = bean.getAac002();
        if(aac0m1 != null && aac0m1.equals("")){
            aac0m1 = null;
        }
        if(aac002 != null && aac002.equals("")){
            aac002 = null;
        }
        String returnStr = "";

        if(aac0m1 != null && aac002 != null){
            returnStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<query type=\"0\">	"
                    + "<column name=\"acc0m1\" type=\"string\">"+aac0m1+"</column>"
                    + "<column name=\"aac002\" type=\"string\">"+aac002+"</column>"
                    + "</query>";
        }else if(aac0m1 != null && aac002 == null){
            returnStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<query type=\"0\">	"
                    + "<column name=\"acc0m1\" type=\"string\">"+aac0m1+"</column>"
                    + "</query>";
        }else if (aac0m1 == null && aac002 != null){
            returnStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<query type=\"0\">	"
                    + "<column name=\"aac002\" type=\"string\">"+aac002+"</column>"
                    + "</query>";
        }else{}
        return returnStr;
    }


    public <T>T ChangeData(T bean, RowBean r){
        List<ColumnBean> list = r.getRow();

        Class c = bean.getClass();
        Field[] fields = c.getDeclaredFields();
        for (int i=0; i<fields.length; i++){
            String filenName = fields[i].getName();
            fields[i].setAccessible(true);
            for(ColumnBean columnBean : list){
                String tmp = columnBean.getName().toLowerCase();
                String value = columnBean.getValue();
                if(filenName.equals(tmp)){
                    try {
                        fields[i].set(bean,value);
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

}
