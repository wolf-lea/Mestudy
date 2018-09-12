package com.tecsun.sisp.iface.server.controller.so;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.iface.common.Page;
import com.tecsun.sisp.iface.common.util.GlobalVariable;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.common.result.ListAndPageResult;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrffPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrjfPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrzhPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.JfbzPO;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.model.service.so.impl.ResidentsInsuranceServiceImpl;

/**
 * Created by DG on 2015/9/25.
 */
@Controller
@RequestMapping(value = "/iface/so")
public class ResidentsInsuranceController extends BaseController{

    public final static Logger logger = Logger.getLogger(ResidentsInsuranceController.class);

    @Autowired
    public ResidentsInsuranceServiceImpl residentsInsuranceService;

    private GrxxPO getAac001(SecQueryBean bean){
    	if(StringUtils.isNotEmpty(bean.getAac002())) {
    		 GrxxPO po = residentsInsuranceService.simisGetEndowInfoPerson(bean);
    		 return po;
    	}else return null;
    }
    
    /**
     * 城乡养老保险个人信息
     * @param bean
     * @return
     */
    @RequestMapping(value="/getEndowInfoPerson", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Object getEndowInfoPerson(@RequestBody SecQueryBean bean, HttpServletRequest request){
    	try{
    		if(StringUtils.isNotEmpty(bean.getAac002())) {
    			String aaz001 = "";
    			if(StringUtils.isNotEmpty(bean.getAaz001())) aaz001 = bean.getAaz001();
            	aaz001 = getThirdAreaId(request.getParameter("method"), request.getParameter("tokenId"), aaz001);
            	bean.setAaz001(aaz001);
                GrxxPO result = residentsInsuranceService.simisGetEndowInfoPerson(bean);
                if(result != null){
                    return this.result(GlobalVariable.RESULT_SUCCESS,GlobalVariable.STRING_QUERY_SUCCESS,result);
                } else{
                    if(!residentsInsuranceService.simisCheckoutIdCard(bean))
                        return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST,GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
                    else
                        return this.result(GlobalVariable.RESULT_NOT_DATA,GlobalVariable.STRING_NOT_DATA);
                }
            }else{
                return this.result(GlobalVariable.RESULT_PARAM_WRONG,GlobalVariable.STRING_REQUEST_NOT_IDCARD);
            }
    	}catch(Exception e){
    		logger.error(GlobalVariable.RESULT_SERVER_WRONG+":"+GlobalVariable.STRING_SERVER_WRONG+e);
    		return this.result(GlobalVariable.RESULT_SERVER_WRONG, GlobalVariable.STRING_SERVER_WRONG);
    	}
        
    }

    /**
     * 城乡养老保险个人缴费
     * @param bean
     * @return
     */
    @RequestMapping(value="/getEndowPayPerson", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Object getEndowPayPerson(@RequestBody SecQueryBean bean, HttpServletRequest request){
    	try{
    		if(StringUtils.isNotEmpty(bean.getAac002())) {
    			String aaz001 = "";
    			if(StringUtils.isNotEmpty(bean.getAaz001())) aaz001 = bean.getAaz001();
            	aaz001 = getThirdAreaId(request.getParameter("method"), request.getParameter("tokenId"), aaz001);
            	bean.setAaz001(aaz001);
            	GrxxPO po = getAac001(bean);
            	if(po != null) bean.setAac001(po.getAac001());
            	int totals = residentsInsuranceService.simisCountEndowPayPerson(bean);
            	if(totals == 0) {
            		if (!residentsInsuranceService.simisCheckoutIdCard(bean)){
		            	return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
		            }else{
		            	return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
		            }
            	}
                int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
                int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();

                Page<GrjfPO> page = new Page<GrjfPO>(pageNo,pageSize);
                page = residentsInsuranceService.simisGetEndowPayPerson(bean, page);
                ListAndPageResult result = new ListAndPageResult();
                result.setLists(page.getData());
                result.setPageNo(pageNo);
                result.setPageSize(pageSize);
                result.setRowTotal(totals);
                
                return this.result(GlobalVariable.RESULT_SUCCESS,GlobalVariable.STRING_QUERY_SUCCESS,result);
            }else{
                return this.result(GlobalVariable.RESULT_PARAM_WRONG,GlobalVariable.STRING_REQUEST_NOT_IDCARD);
            }
    	}catch(Exception e){
    		logger.error(GlobalVariable.RESULT_SERVER_WRONG+":"+GlobalVariable.STRING_SERVER_WRONG+e);
    		return this.result(GlobalVariable.RESULT_SERVER_WRONG, GlobalVariable.STRING_SERVER_WRONG);
    	}
        
    }

    /**
     * 城乡养老保险个人账户信息
     * @param bean
     * @return
     */
    @RequestMapping(value="/getEndowAccountPerson", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Object getEndowAccountPerson(@RequestBody SecQueryBean bean, HttpServletRequest request){
    	try{
    		if(StringUtils.isNotEmpty(bean.getAac002())) {
    			String aaz001 = "";
    			if(StringUtils.isNotEmpty(bean.getAaz001())) aaz001 = bean.getAaz001();
            	aaz001 = getThirdAreaId(request.getParameter("method"), request.getParameter("tokenId"), aaz001);
            	bean.setAaz001(aaz001);
            	GrxxPO po = getAac001(bean);
            	if(po != null) bean.setAac001(po.getAac001());
            	int totals = residentsInsuranceService.simisCountEndowAccountPerson(bean);
                if(totals == 0) {
                     if (!residentsInsuranceService.simisCheckoutIdCard(bean))
                         return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
                     else
                         return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
                 }

                int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
                int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
               
                Page<GrzhPO> page = new Page<GrzhPO>(pageNo,pageSize);
                page = residentsInsuranceService.simisGetEndowAccountPerson(bean , page);

                ListAndPageResult result = new ListAndPageResult();
                result.setLists(page.getData());
                result.setPageNo(pageNo);
                result.setPageSize(pageSize);
                result.setRowTotal(totals);

                return this.result(GlobalVariable.RESULT_SUCCESS,GlobalVariable.STRING_QUERY_SUCCESS,result);
            }else{
                return this.result(GlobalVariable.RESULT_PARAM_WRONG,GlobalVariable.STRING_REQUEST_NOT_IDCARD);
            }
    	}catch(Exception e){
    		logger.error(GlobalVariable.RESULT_SERVER_WRONG+":"+GlobalVariable.STRING_SERVER_WRONG+e);
    		return this.result(GlobalVariable.RESULT_SERVER_WRONG, GlobalVariable.STRING_SERVER_WRONG);
    	}
        
    }

    /**
     * 城乡养老保险个人养老金发放
     * @param bean
     * @return
     */
    @RequestMapping(value="/getEndowAnnuityPerson", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Object getEndowAnnuityPerson(@RequestBody SecQueryBean bean, HttpServletRequest request){
    	try{
		   if(StringUtils.isNotEmpty(bean.getAac002())) {
			   	String aaz001 = "";
   				if(StringUtils.isNotEmpty(bean.getAaz001())) aaz001 = bean.getAaz001();
   				aaz001 = getThirdAreaId(request.getParameter("method"), request.getParameter("tokenId"), aaz001);
	           	bean.setAaz001(aaz001);
	           	GrxxPO po = getAac001(bean);
	           	if(po != null) bean.setAac001(po.getAac001());
	           	int totals = residentsInsuranceService.simisCountEndowAnnuityPerson(bean);
	            if(totals == 0) {
	                if (!residentsInsuranceService.simisCheckoutIdCard(bean))
	                    return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
	                else
	                    return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
	            }
	            int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
	            int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
	            Page<GrffPO> page = new Page<GrffPO>(pageNo,pageSize);
	            page = residentsInsuranceService.simisGetEndowAnnuityPerson(bean , page);

	            ListAndPageResult result = new ListAndPageResult();
	            result.setLists(page.getData());
	            result.setPageNo(pageNo);
	            result.setPageSize(pageSize);
	            result.setRowTotal(totals);

	            return this.result(GlobalVariable.RESULT_SUCCESS,GlobalVariable.STRING_QUERY_SUCCESS,result);
	        }else{
	            return this.result(GlobalVariable.RESULT_PARAM_WRONG,GlobalVariable.STRING_REQUEST_NOT_IDCARD);
	        }
    	}catch(Exception e){
    		logger.error(GlobalVariable.RESULT_SERVER_WRONG+":"+GlobalVariable.STRING_SERVER_WRONG+e);
    		return this.result(GlobalVariable.RESULT_SERVER_WRONG, GlobalVariable.STRING_SERVER_WRONG);
    	}
    }

    /**
     * 城乡养老保险缴费标准
     * @return
     */
    @RequestMapping(value="/getEndowPayStandardPerson", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Object getEndowPayStandardPerson(@RequestBody SecQueryBean bean, HttpServletRequest request){
    	try{
    		if(StringUtils.isNotEmpty(bean.getAac002())) {
    			String aaz001 = "";
   				if(StringUtils.isNotEmpty(bean.getAaz001())) aaz001 = bean.getAaz001();
   				aaz001 = getThirdAreaId(request.getParameter("method"), request.getParameter("tokenId"), aaz001);
	           	bean.setAaz001(aaz001);
	           	GrxxPO po = getAac001(bean);
	           	if(po != null) bean.setAac001(po.getAac001());
	        	int totals = residentsInsuranceService.simisCountEndowPayStandardPerson(bean);
	            if(totals == 0) {
	                if (!residentsInsuranceService.simisCheckoutIdCard(bean))
	                    return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
	                else
	                    return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
	            }
	            int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
	            int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
	            Page<JfbzPO> page = new Page<JfbzPO>(pageNo,pageSize);
	            page = residentsInsuranceService.simisEndowPayStandardPerson(bean , page);

	            ListAndPageResult result = new ListAndPageResult();
	            result.setLists(page.getData());
	            result.setPageNo(pageNo);
	            result.setPageSize(pageSize);
	            result.setRowTotal(totals);

	            return this.result(GlobalVariable.RESULT_SUCCESS,GlobalVariable.STRING_QUERY_SUCCESS,result);
    		}else{
	            return this.result(GlobalVariable.RESULT_PARAM_WRONG,GlobalVariable.STRING_REQUEST_NOT_IDCARD);
	        }
    	}catch(Exception e){
    		logger.error(GlobalVariable.RESULT_SERVER_WRONG+":"+GlobalVariable.STRING_SERVER_WRONG+e);
    		return this.result(GlobalVariable.RESULT_SERVER_WRONG, GlobalVariable.STRING_SERVER_WRONG);
    	}
    }
}
