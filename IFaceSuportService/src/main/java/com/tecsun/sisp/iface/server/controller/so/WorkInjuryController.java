package com.tecsun.sisp.iface.server.controller.so;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tecsun.sisp.iface.common.util.Dom4JUtil;
import com.tecsun.sisp.iface.common.util.GlobalVariable;
import com.tecsun.sisp.iface.common.util.PageBean;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.common.result.ListAndPageResult;
import com.tecsun.sisp.iface.common.vo.so.po.workinjury.GsbxdyxqxxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.workinjury.GsbxdyylbxxxxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.workinjury.GsbxxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.workinjury.GsdyffxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.workinjury.GsjbxxPO;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.util.webservice.SocsecQuery;
/**
 * 工伤保险查询
 * @author liang
 *
 */

@Controller
@RequestMapping(value="/iface/so")
public class WorkInjuryController extends BaseController{
	public static final Logger logger=Logger.getLogger(WorkInjuryController.class);
	
	/**
	 * 工伤基本信息
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/gsjbxx" ,method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Object gsjbxx(@RequestBody SecQueryBean bean){
		if(StringUtils.isEmpty(bean.getAac002())) return this.result(GlobalVariable.RESULT_PARAM_WRONG,"身份证号不能为空");
		if(StringUtils.isEmpty(bean.getAac003())) return this.result(GlobalVariable.RESULT_PARAM_WRONG, "姓名不能为空");
		bean.setBusiness("GSJBXX");
		String xml=SocsecQuery.getWorkInjuryXML(bean);
		String result=SocsecQuery.invokeWS(xml);
		GsjbxxPO po=new GsjbxxPO();
		List<GsjbxxPO> lists=null;		
		try {
			lists=Dom4JUtil.socsecReadXML(po, result);
			if(lists!=null && lists.size()>0){
//				System.out.println(lists.get(0).getCSRQ());
//				System.out.println(lists.get(0).getCJGZRQ());
//				System.out.println(lists.get(0).getAIC162());
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, lists);
			}else{
				return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_QUERY_WEBSERVICE_ERROR);
		}	
	}
	/**
	 * 工伤保险信息
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/gsbxxx" ,method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Object gsbxxx(@RequestBody SecQueryBean bean){
		if(StringUtils.isEmpty(bean.getAac002())) return this.result(GlobalVariable.RESULT_PARAM_WRONG,"身份证号不能为空");
		if(StringUtils.isEmpty(bean.getAac003())) return this.result(GlobalVariable.RESULT_PARAM_WRONG, "姓名不能为空");
		bean.setBusiness("GSBXXX");
		int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
        int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
        int totals =0;
        ListAndPageResult pageResult = new ListAndPageResult();
		String xml=SocsecQuery.getWorkInjuryXML(bean);
		String result=SocsecQuery.invokeWS(xml);
		GsbxxxPO po=new GsbxxxPO();
		List<GsbxxxPO> lists=null;
		List<GsbxxxPO> resultList=new ArrayList<GsbxxxPO>();
		try {
			lists=Dom4JUtil.socsecReadXML(po, result);
			if(lists!=null && lists.size()>0){
				totals=lists.size();
				//System.out.println(totals);
				//String s = JSON.toJSONString(lists.get(0));
				//System.out.println(s);
				PageBean page = new PageBean(totals,pageSize,pageNo);
				for(int i=page.getStart();i<=page.getEnd();i++){
					resultList.add(lists.get(i-1));
				}
//				System.out.println(page.getPageNum());
//				for(int i=0;i<resultList.size();i++){
//					System.out.println(resultList.get(i).getGSSJ());
//				}
				pageResult.setLists(resultList);
				pageResult.setPageNo(pageNo);
				pageResult.setPageSize(pageSize);
				pageResult.setPageTotal(page.getPageNum());
				pageResult.setRowTotal(totals);
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, pageResult);
			}else{
				return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA);
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_QUERY_WEBSERVICE_ERROR);
		}
	}
	/**
	 * 工伤保险待遇详情查询
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/gsbxdyxxxx" ,method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Object gsbxdyxxxx(@RequestBody SecQueryBean bean){
		if(StringUtils.isEmpty(bean.getAac002())) return this.result(GlobalVariable.RESULT_PARAM_WRONG,"身份证号不能为空");
		if(StringUtils.isEmpty(bean.getAac003())) return this.result(GlobalVariable.RESULT_PARAM_WRONG, "姓名不能为空");
		bean.setBusiness("GSBXDYXXXX");
		int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
        int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
        int totals =0;
        ListAndPageResult pageResult = new ListAndPageResult();
		String xml=SocsecQuery.getWorkInjuryXML(bean);
		String result=SocsecQuery.invokeWS(xml);
		GsbxdyxqxxxPO po=new GsbxdyxqxxxPO();
		List<GsbxdyxqxxxPO> lists=null;	
		List<GsbxdyxqxxxPO> resultList=new ArrayList<GsbxdyxqxxxPO>();
		try {
			lists=Dom4JUtil.socsecReadXML(po, result);
			if(lists!=null && lists.size()>0){
				totals=lists.size();
				/*System.out.println(totals);
				String s = JSON.toJSONString(lists.get(0));
				System.out.println(s);*/
				PageBean page = new PageBean(totals,pageSize,pageNo);
				for(int i=page.getStart();i<=page.getEnd();i++){
					resultList.add(lists.get(i-1));
				}
				pageResult.setLists(resultList);
				pageResult.setPageNo(pageNo);
				pageResult.setPageSize(pageSize);
				pageResult.setPageTotal(page.getPageNum());
				pageResult.setRowTotal(totals);
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, pageResult);
			}else{
				return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA);
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_QUERY_WEBSERVICE_ERROR);
		}
	}
	/**
	 * 工伤保险待遇医疗报销详情查询
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/gsbxdyylbxxxxx" ,method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Object gsbxdyylbxxxxx(@RequestBody SecQueryBean bean){
		if(StringUtils.isEmpty(bean.getAac002())) return this.result(GlobalVariable.RESULT_PARAM_WRONG,"身份证号不能为空");
		if(StringUtils.isEmpty(bean.getAac003())) return this.result(GlobalVariable.RESULT_PARAM_WRONG, "姓名不能为空");
		bean.setBusiness("GSBXDYYLBXXXXX");
		int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
        int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
        int totals =0;
        ListAndPageResult pageResult = new ListAndPageResult();
		String xml=SocsecQuery.getWorkInjuryXML(bean);
		String result=SocsecQuery.invokeWS(xml);
		GsbxdyylbxxxxxPO po=new GsbxdyylbxxxxxPO();
		List<GsbxdyylbxxxxxPO> lists=null;	
		List<GsbxdyylbxxxxxPO> resultList=new ArrayList<GsbxdyylbxxxxxPO>();
		try {
			lists=Dom4JUtil.socsecReadXML(po, result);
			if(lists!=null && lists.size()>0){
				totals=lists.size();
				/*System.out.println(totals);
				String s = JSON.toJSONString(lists.get(0));
				System.out.println(s);*/
				PageBean page = new PageBean(totals,pageSize,pageNo);
				for(int i=page.getStart();i<=page.getEnd();i++){
					resultList.add(lists.get(i-1));
				}
				pageResult.setLists(resultList);
				pageResult.setPageNo(pageNo);
				pageResult.setPageSize(pageSize);
				pageResult.setPageTotal(page.getPageNum());
				pageResult.setRowTotal(totals);
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, pageResult);
			}else{
				return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA);
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_QUERY_WEBSERVICE_ERROR);
		}
	}
	/**
	 * 工伤待遇发放查询（暂无数据）
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/gsdyffxx" ,method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Object gsdyffxx(@RequestBody SecQueryBean bean){
		if(StringUtils.isEmpty(bean.getAac002())) return this.result(GlobalVariable.RESULT_PARAM_WRONG,"身份证号不能为空");
		if(StringUtils.isEmpty(bean.getAac003())) return this.result(GlobalVariable.RESULT_PARAM_WRONG, "姓名不能为空");
		bean.setBusiness("GSDYFFXX");
		int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
        int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
        int totals =0;
        ListAndPageResult pageResult = new ListAndPageResult();
		String xml=SocsecQuery.getWorkInjuryXML(bean);
		String result=SocsecQuery.invokeWS(xml);
		GsdyffxxPO po=new GsdyffxxPO();
		List<GsdyffxxPO> lists=null;
		List<GsdyffxxPO> resultList=new ArrayList<GsdyffxxPO>();
		try {
			lists=Dom4JUtil.socsecReadXML(po, result);
			if(lists!=null && lists.size()>0){
				totals=lists.size();
				/*System.out.println(totals);
				String s = JSON.toJSONString(lists.get(0));
				System.out.println(s);*/
				PageBean page = new PageBean(totals,pageSize,pageNo);
				for(int i=page.getStart();i<=page.getEnd();i++){
					resultList.add(lists.get(i-1));
				}
				pageResult.setLists(resultList);
				pageResult.setPageNo(pageNo);
				pageResult.setPageSize(pageSize);
				pageResult.setPageTotal(page.getPageNum());
				pageResult.setRowTotal(totals);
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, pageResult);
			}else{
				logger.info("查无数据");
				return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA);
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR,GlobalVariable.STRING_QUERY_WEBSERVICE_ERROR);
		}
	}
	
//	public static void main(String[] args){
//		WorkInjuryController ec=new WorkInjuryController();
//		SecQueryBean bean=new SecQueryBean();
//		bean.setAac002("410602195007150533");
//		bean.setAac003("辛怀增");
//		bean.setPageSize(5);
//		bean.setPageNo(1);
//		ec.gsbxxx(bean);
//	}
		
}
