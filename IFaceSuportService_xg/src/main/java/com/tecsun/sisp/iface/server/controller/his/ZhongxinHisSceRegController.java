package com.tecsun.sisp.iface.server.controller.his;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tecsun.sisp.iface.common.vo.Page;
import com.tecsun.sisp.iface.common.vo.PageModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.common.util.ZxHisUtil;
import com.tecsun.sisp.iface.common.vo.HisZxRequstBean;
import com.tecsun.sisp.iface.common.vo.Result;
import com.tecsun.sisp.iface.common.vo.his.zx.ZhongxinHisSceRegPO;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.outerface.his.ZXHisIface;
import com.tecsun.sisp.iface.server.util.Dom4JUtil;

/*
 * 孝感中心医院现场挂号controller类；
 * 
 */
@Controller
@RequestMapping(value = "/iface/his/sce")
public class ZhongxinHisSceRegController extends BaseController{
	public static final Logger logger = Logger.getLogger(ZhongxinHisSceRegController.class);
	
    static ZxHisUtil zhHis = new ZxHisUtil();
    static ZXHisIface iface = new ZXHisIface();
    

    
    /**
     * 获取代缴费的单据
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getOutPatientPayBills_zx", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getOutPatientPayBills_zx(@RequestBody HisZxRequstBean bean){
    	
    	 String mxl = zhHis.getOutPatientPayBillsXml(bean);
         String zxHosXml = null;
         try {
             zxHosXml = zhHis.setBase64EncoderXml_Zx(mxl.getBytes("utf-8"));
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         String result = Constants.RESULT_MESSAGE_SUCCESS;
         String message = "";
         String wsResult = "";  //iface上不做任何的业务处理
         Map<String, String> map = new HashMap<String, String>();
         map = iface.getOutPatientPayBills_zx(zxHosXml, result, message);
         wsResult = map.get("wsResult");
         String xml = null;
         try {
             xml = new String(zhHis.getBase64DecoderXml_Zx(wsResult), "utf-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         result = map.get("result");
         message = map.get("message");
         List<ZhongxinHisSceRegPO> list = new ArrayList<ZhongxinHisSceRegPO>(0);
         try {
             if (xml != null && !"".equals(xml)) {
             	int returncod = Dom4JUtil.checkWsReturnCode(xml);
             	message  = Dom4JUtil.getMessage(xml);
             	if(result.equals(Constants.RESULT_MESSAGE_SUCCESS)){
             		if(returncod ==1){
             			list = Dom4JUtil.readXMLToJavaBean_Zx(new ZhongxinHisSceRegPO(), xml);
                		    result = Constants.RESULT_MESSAGE_SUCCESS;
             		}else{
             			 result = Constants.RESULT_MESSAGE_ERROR;
             		}
             	}else{
                      logger.error("获取【中心医院】现场挂号-获取代缴费的单据getOutPatientPayBills_zx失败！");
                      result = Constants.RESULT_MESSAGE_ERROR;
             	}
             }
         } catch (Exception e) {
             result = Constants.RESULT_MESSAGE_ERROR;
             logger.error("获取【中心医院】现场挂号-获取代缴费的单据getOutPatientPayBills_zx失败！");
         }
         return this.result(result, message, list);
    }

    /**
     * 获取单据详情
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getOutPatientPayBillsDetial_zx", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getOutPatientPayBillsDetial(@RequestBody HisZxRequstBean bean){
    	
    	 String mxl = zhHis.getOutPatientPayBillsDetialXml(bean);
         String zxHosXml = null;
         try {
             zxHosXml = zhHis.setBase64EncoderXml_Zx(mxl.getBytes("utf-8"));
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         String result = Constants.RESULT_MESSAGE_SUCCESS;
         String message = "";
         String wsResult = "";  //iface上不做任何的业务处理
         Map<String, String> map = new HashMap<String, String>();
         map = iface.getOutPatientPayBillsDetial_zx(zxHosXml, result, message);
         wsResult = map.get("wsResult");
         String xml = null;
         try {
             xml = new String(zhHis.getBase64DecoderXml_Zx(wsResult), "utf-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         result = map.get("result");
         message = map.get("message");
         List<ZhongxinHisSceRegPO> list = new ArrayList<ZhongxinHisSceRegPO>(0);
         try {
             if (xml != null && !"".equals(xml)) {
             	int returncod = Dom4JUtil.checkWsReturnCode(xml);
             	message  = Dom4JUtil.getMessage(xml);
             	if(result.equals(Constants.RESULT_MESSAGE_SUCCESS)){
             		if(returncod ==1){
             			list = Dom4JUtil.readXMLToJavaBean_Zx(new ZhongxinHisSceRegPO(), xml);
                		    result = Constants.RESULT_MESSAGE_SUCCESS;
             		}else{
             			 result = Constants.RESULT_MESSAGE_ERROR;
             		}
             	}else{
                      logger.error("获取【中心医院】现场挂号-获取单据详情getOutPatientPayBillsDetial失败！");
                      result = Constants.RESULT_MESSAGE_ERROR;
             	}
             }
         } catch (Exception e) {
             result = Constants.RESULT_MESSAGE_ERROR;
             logger.error("获取【中心医院】现场挂号-获取单据详情：getOutPatientPayBillsDetial_zx失败");
         }
         return this.result(result, message, list);
    }
    
    /**
     * 单据缴费
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getOutpatientPayBills_zx", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getOutpatientPayBills_zx(@RequestBody HisZxRequstBean bean){
    	
    	 String mxl = zhHis.getOutPatientPayBillsDetialXml(bean);
         String zxHosXml = null;
         try {
             zxHosXml = zhHis.setBase64EncoderXml_Zx(mxl.getBytes("utf-8"));
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         String result = Constants.RESULT_MESSAGE_SUCCESS;
         String message = "";
         String wsResult = "";  //iface上不做任何的业务处理
         Map<String, String> map = new HashMap<String, String>();
         map = iface.getOutPatientPayBills_zx(zxHosXml, result, message);
         wsResult = map.get("wsResult");
         String xml = null;
         try {
             xml = new String(zhHis.getBase64DecoderXml_Zx(wsResult), "utf-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         result = map.get("result");
         message = map.get("message");
         List<ZhongxinHisSceRegPO> list = new ArrayList<ZhongxinHisSceRegPO>(0);
         try {
             if (xml != null && !"".equals(xml)) {
             	int returncod = Dom4JUtil.checkWsReturnCode(xml);
             	message  = Dom4JUtil.getMessage(xml);
             	if(result.equals(Constants.RESULT_MESSAGE_SUCCESS)){
             		if(returncod ==1){
             			list = Dom4JUtil.readXMLToJavaBean_Zx(new ZhongxinHisSceRegPO(), xml);
                		    result = Constants.RESULT_MESSAGE_SUCCESS;
             		}else{
             			 result = Constants.RESULT_MESSAGE_ERROR;
             		}
             	}else{
                      logger.error("获取【中心医院】现场挂号-单据缴费getOutpatientPayBills_zx失败！");
                      result = Constants.RESULT_MESSAGE_ERROR;
             	}
             }
         } catch (Exception e) {
             result = Constants.RESULT_MESSAGE_ERROR;
             logger.error("获取【中心医院】现场挂号-单据缴费getOutpatientPayBills_zx失败！");
         }
         return this.result(result, message, list);
    }
    
    /**
     * 取消订单
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getRegisterCancelOrder_zx", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getRegisterCancelOrder_zx(@RequestBody HisZxRequstBean bean){
    	
    	 String mxl = zhHis.getRegisterCancelOrderXml(bean);
         String zxHosXml = null;
         try {
             zxHosXml = zhHis.setBase64EncoderXml_Zx(mxl.getBytes("utf-8"));
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         String result = Constants.RESULT_MESSAGE_SUCCESS;
         String message = "";
         String wsResult = "";  //iface上不做任何的业务处理
         Map<String, String> map = new HashMap<String, String>();
         map = iface.getRegisterCancelOrder_zx(zxHosXml, result, message);
         wsResult = map.get("wsResult");
         String xml = null;
         try {
             xml = new String(zhHis.getBase64DecoderXml_Zx(wsResult), "utf-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         result = map.get("result");
         message = map.get("message");
         List<ZhongxinHisSceRegPO> list = new ArrayList<ZhongxinHisSceRegPO>(0);
         try {
             if (xml != null && !"".equals(xml)) {
             	int returncod = Dom4JUtil.checkWsReturnCode(xml);
             	message  = Dom4JUtil.getMessage(xml);
             	if(result.equals(Constants.RESULT_MESSAGE_SUCCESS)){
             		if(returncod ==1){
             			list = Dom4JUtil.readXMLToJavaBean_Zx(new ZhongxinHisSceRegPO(), xml);
                		    result = Constants.RESULT_MESSAGE_SUCCESS;
             		}else{
             			 result = Constants.RESULT_MESSAGE_ERROR;
             		}
             	}else{
                      logger.error("获取【中心医院】现场挂号-取消订单：getRegisterCancelOrder_zx失败！");
                      result = Constants.RESULT_MESSAGE_ERROR;
             	}
             }
         } catch (Exception e) {
             result = Constants.RESULT_MESSAGE_ERROR;
             logger.error("获取【中心医院】现场挂号-取消订单：getRegisterCancelOrder_zx失败");
         }
         return this.result(result, message, list);
    }
    
    /**
     * 中心医院现场挂号-提交订单
     * RegisterConfirmOrder
     *
     * @param bean
     * @return Result    返回类型
     * @throws
     * @Title:
     */

  /*  @RequestMapping(value = "/getRegisterConfirmOrder_zx", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody*/
    public  static String getRegisterConfirmOrder_zx(@RequestBody HisZxRequstBean bean){
    	
    	 String mxl = zhHis.getRegisterConfirmOrderXml(bean);
         String zxHosXml = null;
         try {
             zxHosXml = zhHis.setBase64EncoderXml_Zx(mxl.getBytes("utf-8"));
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         String result = Constants.RESULT_MESSAGE_SUCCESS;
         String message = "";
         String wsResult = "";  //iface上不做任何的业务处理
         Map<String, String> map = new HashMap<String, String>();
         map = iface.getRegisterConfirmOrder_zx(zxHosXml, result, message);
         wsResult = map.get("wsResult");
         String xml = null;
         try {
             xml = new String(zhHis.getBase64DecoderXml_Zx(wsResult), "utf-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         result = map.get("result");
         message = map.get("message");
         List<ZhongxinHisSceRegPO> list = new ArrayList<ZhongxinHisSceRegPO>(0);
         try {
             if (xml != null && !"".equals(xml)) {
             	int returncod = Dom4JUtil.checkWsReturnCode(xml);
             	message  = Dom4JUtil.getMessage(xml);
             	if(result.equals(Constants.RESULT_MESSAGE_SUCCESS)){
             		if(returncod ==1){
             			list = Dom4JUtil.readXMLToJavaBean_Zx(new ZhongxinHisSceRegPO(), xml);
                		    result = Constants.RESULT_MESSAGE_SUCCESS;
             		}else{
             			 result = Constants.RESULT_MESSAGE_ERROR;
             		}
             	}else{
                      logger.error("获取【中心医院】现场挂号-提交订单getRegisterConfirmOrder_zx失败！");
                      result = Constants.RESULT_MESSAGE_ERROR;
             	}
             }
         } catch (Exception e) {
             result = Constants.RESULT_MESSAGE_ERROR;
             logger.error("获取【中心医院】现场挂号-提交订单getRegisterConfirmOrder_zx失败！");
         }
         //return this.result(result, message, list);
         return result;
    }
    
    
    /**
     * 2.2.4中心医院现场挂号-创建订单
     * RegisterNewOrder
     *
     * @param bean
     * @return Result    返回类型
     * @throws
     * @Title:
     */
    @RequestMapping(value = "/getRegisterNewOrder_zx", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getRegisterNewOrder_zx(@RequestBody HisZxRequstBean bean) {
    	 String mxl = zhHis.getRegisterNewOrderXml(bean);
         String zxHosXml = "";
         try {
             zxHosXml = zhHis.setBase64EncoderXml_Zx(mxl.getBytes("utf-8"));
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         String result = Constants.RESULT_MESSAGE_SUCCESS;
         String message = "";
         String wsResult = "";  //iface上不做任何的业务处理
         Map<String, String> map = new HashMap<String, String>();
         map = iface.getRegisterNewOrder_zx(zxHosXml, result, message);
         wsResult = map.get("wsResult");
         result = map.get("result");
         message = map.get("message");
         String xml = null;
         try {
             xml = new String(zhHis.getBase64DecoderXml_Zx(wsResult), "utf-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         List<ZhongxinHisSceRegPO> list = new ArrayList<ZhongxinHisSceRegPO>(0);
         try {
             if (xml != null && !"".equals(xml)) {
             	int returncode = Dom4JUtil.checkWsReturnCode(xml);
             	message  = Dom4JUtil.getMessage(xml);
             	if(result.equals(Constants.RESULT_MESSAGE_SUCCESS) && returncode ==1){
             		list = Dom4JUtil.readXMLToJavaBean_Zx(new ZhongxinHisSceRegPO(), xml);
             	}else{
             		 logger.error("获取【中心医院】现场挂号-创建订单getRegisterNewOrder_zx失败！");
             		result = Constants.RESULT_MESSAGE_ERROR;
             	}
             }
         } catch (Exception e) {
             result = Constants.RESULT_MESSAGE_ERROR;
             logger.error("获取【中心医院】现场挂号-创建订单getRegisterNewOrder_zx失败！");
         }
         return this.result(result, message, list);
    }
    
    
    /**
     * 中心医院现场挂号-获取科室列表
     * GetDeptmentList
     * @param bean
     * @return Result    返回类型
     * @throws
     * @Title:
     */

    @RequestMapping(value = "/getGetDeptmentList_zx", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public  Result getGetDeptmentList_zx(@RequestBody HisZxRequstBean bean){
    	
    	 String mxl = zhHis.getGetDeptmentListXml(bean);
         String zxHosXml = null;
         try {
             zxHosXml = zhHis.setBase64EncoderXml_Zx(mxl.getBytes("utf-8"));
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         String result = Constants.RESULT_MESSAGE_SUCCESS;
         String message = "";
         String wsResult = "";  //iface上不做任何的业务处理
         Map<String, String> map = new HashMap<String, String>();
         map = iface.getGetDeptmentList_zx(zxHosXml, result, message);
         wsResult = map.get("wsResult");
         String xml = null;
        int count=0;
         try {
             xml = new String(zhHis.getBase64DecoderXml_Zx(wsResult), "utf-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         result = map.get("result");
         message = map.get("message");
         List<ZhongxinHisSceRegPO> list = new ArrayList<ZhongxinHisSceRegPO>(0);
         Page<ZhongxinHisSceRegPO> page = new Page(bean.getPageno(), bean.getPagesize());

        try {
             if (xml != null && !"".equals(xml)) {
             	int returncod = Dom4JUtil.checkWsReturnCode(xml);
             	message  = Dom4JUtil.getMessage(xml);
             	if(result.equals(Constants.RESULT_MESSAGE_SUCCESS)){
             		if(returncod ==1){
             			list = Dom4JUtil.readXMLToJavaBean_Zx(new ZhongxinHisSceRegPO(), xml);
                        if(!list.isEmpty() && list.size()>0){
                            count = Dom4JUtil.checkDeptmentCont(xml);
                            page.setCount(count);
                            page.setData(list);
                        }
                		result = Constants.RESULT_MESSAGE_SUCCESS;
             		}else{
             			 result = Constants.RESULT_MESSAGE_ERROR;
             		}
             	}else{
                      logger.error("获取【中心医院】现场挂号-获取科室列表getGetDeptmentList_zx失败！");
                      result = Constants.RESULT_MESSAGE_ERROR;
             	}
             }
         } catch (Exception e) {
             result = Constants.RESULT_MESSAGE_ERROR;
             logger.error("获取【中心医院】现场挂号-获取科室列表getGetDeptmentList_zx失败！");
         }
         return this.result(result, message, page);
    }
    
    /**
     * 中心医院现场挂号-获取当日挂号科室下医生 
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getXCDoctorList_zx", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getXCDoctorList_zx(@RequestBody HisZxRequstBean bean){
    	
    	 String mxl = zhHis.getXCDoctorListXml(bean);
         String zxHosXml = null;
         try {
             zxHosXml = zhHis.setBase64EncoderXml_Zx(mxl.getBytes("utf-8"));
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         String result = Constants.RESULT_MESSAGE_SUCCESS;
         String message = "";
         String wsResult = "";  //iface上不做任何的业务处理
         Map<String, String> map = new HashMap<String, String>();
         map = iface.getDoctorList_zx(zxHosXml, result, message);
         wsResult = map.get("wsResult");
         String xml = null;
         try {
             xml = new String(zhHis.getBase64DecoderXml_Zx(wsResult), "utf-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         result = map.get("result");
         message = map.get("message");
         List<ZhongxinHisSceRegPO> list = new ArrayList<ZhongxinHisSceRegPO>(0);
         try {
             if (xml != null && !"".equals(xml)) {
             	int returncod = Dom4JUtil.checkWsReturnCode(xml);
             	message  = Dom4JUtil.getMessage(xml);
             	if(result.equals(Constants.RESULT_MESSAGE_SUCCESS)){
             		if(returncod ==1){
             			list = Dom4JUtil.readXMLToJavaBean_Zx(new ZhongxinHisSceRegPO(), xml);
                        message = list.size()+"";
                        if(!list.isEmpty() && list.size()>0){
                            int pageRecorders = bean.getPagesize()==0?list.size():bean.getPagesize();
                            int pageno = bean.getPageno()==0?list.size():bean.getPageno();
                            PageModel<ZhongxinHisSceRegPO> page = new PageModel<ZhongxinHisSceRegPO>(list , pageRecorders);
                            list = page.getObjects(pageno);
                            page.setData(list);
                        }
                		result = Constants.RESULT_MESSAGE_SUCCESS;
             		}else{
             			 result = Constants.RESULT_MESSAGE_ERROR;
             		}
             	}else{
                      logger.error("获取【中心医院】现场挂号-获取当日挂号科室下医生 ：getXCDoctorList_zx失败！");
                      result = Constants.RESULT_MESSAGE_ERROR;
             	}
             }
         } catch (Exception e) {
             result = Constants.RESULT_MESSAGE_ERROR;
             logger.error("获取【中心医院】现场挂号-获取当日挂号科室下医生 ：getXCDoctorList_zx失败");
         }
         return this.result(result, message, list);
    }
}
