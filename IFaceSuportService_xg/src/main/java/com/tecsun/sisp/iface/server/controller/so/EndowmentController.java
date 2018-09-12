package com.tecsun.sisp.iface.server.controller.so;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.iface.common.util.Dom4JUtil;
import com.tecsun.sisp.iface.common.util.GlobalVariable;
import com.tecsun.sisp.iface.common.util.PageBean;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.common.result.ListAndPageResult;
import com.tecsun.sisp.iface.common.vo.so.po.endow.YlcbxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.YljbxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.YljffxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.YljfjsxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.YljfxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.YljzhxxPO;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.util.YljfjsxxComparator;
import com.tecsun.sisp.iface.server.util.webservice.SocsecQuery;

/**
 * 养老保险基本信息查询
 * 
 * @author liang
 *
 */
@Controller
@RequestMapping(value="/iface/so")
public class EndowmentController extends BaseController {
	public static final Logger logger = Logger.getLogger(EndowmentController.class);

	@RequestMapping(value = "/yljbxx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object yljbxx(@RequestBody SecQueryBean bean) /*throws ParseException*/ {
		if (StringUtils.isEmpty(bean.getAac002()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getAac003()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "姓名不能为空");
		bean.setBusiness("YLJBXX");
		String xml = SocsecQuery.getEndowmentXML(bean);
		String result = SocsecQuery.invokeWS(xml);
		YljbxxPO po = new YljbxxPO();
		List<YljbxxPO> lists = null;
		try {
			lists = Dom4JUtil.socsecReadXML(po, result);
			if (lists != null && lists.size() > 0) {
//				String date = lists.get(0).getCSRQ();
//				System.out.println(date);
				/*
				 * Date d=new Date(Long.parseLong(date)); SimpleDateFormat
				 * sdf=new SimpleDateFormat("yyyy-MM-dd");
				 * System.out.println(sdf.format(d));
				 */
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, lists);
			} else {
				return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR, GlobalVariable.STRING_QUERY_WEBSERVICE_ERROR);
		}
	}

	/**
	 * 养老参保信息
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/ylcbxx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object ylcbxx(@RequestBody SecQueryBean bean) {
		if (StringUtils.isEmpty(bean.getAac002()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getAac003()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "姓名不能为空");
		bean.setBusiness("YLCBXX");
		int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
		int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
		int totals = 0;
		ListAndPageResult pageResult = new ListAndPageResult();
		String xml = SocsecQuery.getEndowmentXML(bean);
		String result = SocsecQuery.invokeWS(xml);
		YlcbxxPO po = new YlcbxxPO();
		List<YlcbxxPO> lists = null;
		List<YlcbxxPO> resultList=new ArrayList<YlcbxxPO>();
		try {
			lists = Dom4JUtil.socsecReadXML(po, result);
			if (lists != null && lists.size() > 0) {
				totals = lists.size();
			    System.out.println(totals);
				PageBean page = new PageBean(totals, pageSize, pageNo);
				for(int i=page.getStart();i<=page.getEnd();i++){
					resultList.add(lists.get(i-1));
				}
				pageResult.setLists(resultList);
				pageResult.setPageNo(pageNo);
				pageResult.setPageSize(pageSize);
				pageResult.setPageTotal(page.getPageNum());
				pageResult.setRowTotal(totals);
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, pageResult);
			} else {
				logger.info("查无数据");
				return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR, GlobalVariable.STRING_QUERY_WEBSERVICE_ERROR);
		}
	}

	/**
	 * 养老缴费基数信息
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/yljfjsxx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object yljfjsxx(@RequestBody SecQueryBean bean) {
		if (StringUtils.isEmpty(bean.getAac002()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getAac003()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "姓名不能为空");
		bean.setBusiness("YLJFJSXX");
		int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
		int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
		int totals = 0;
		ListAndPageResult pageResult = new ListAndPageResult();
		String xml = SocsecQuery.getEndowmentXML(bean);
		String result = SocsecQuery.invokeWS(xml);
		YljfjsxxPO po = new YljfjsxxPO();
		List<YljfjsxxPO> lists = null;
		List<YljfjsxxPO> resultList=new ArrayList<YljfjsxxPO>();
		try {
			lists = Dom4JUtil.socsecReadXML(po, result);
			if (lists != null && lists.size() > 0) {
				/*System.out.println(lists.size());
				for (int i = 0; i < lists.size(); i++) {
					System.out.println("排序前" + i + "个：" + lists.get(i).getKSNY());
				}
				// 按照开始年月排序
				Collections.sort(lists, new Comparator<YljfjsxxPO>() {
					@Override
					public int compare(YljfjsxxPO po1, YljfjsxxPO po2) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
						Date d1 = null;
						Date d2 = null;
						try {
							d1 = sdf.parse(po1.getKSNY());
							d2 = sdf.parse(po2.getKSNY());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						return d2.compareTo(d1);
					}
				});*/
				Collections.sort(lists, new YljfjsxxComparator());
				/*for (int i = 0; i < lists.size(); i++) {
					System.out.println("第" + i + "个：" + lists.get(i).getKSNY());
				}*/
				totals = lists.size();
				PageBean page = new PageBean(totals, pageSize, pageNo);
				for(int i=page.getStart();i<=page.getEnd();i++){
					resultList.add(lists.get(i-1));
				}
				/*for (int i = 0; i <resultList.size(); i++) {
					System.out.println("分页后，第" + i + "个：" + resultList.get(i).getKSNY());
				}*/
				pageResult.setLists(resultList);
				pageResult.setPageNo(pageNo);
				pageResult.setPageSize(pageSize);
				pageResult.setPageTotal(page.getPageNum());
				pageResult.setRowTotal(totals);
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, pageResult);
			} else {
				return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR, GlobalVariable.STRING_QUERY_WEBSERVICE_ERROR);
		}
	}

	/**
	 * 养老缴费明细信息
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/yljfxx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object yljfxx(@RequestBody SecQueryBean bean) {
		if (StringUtils.isEmpty(bean.getAac002()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getAac003()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "姓名不能为空");
		bean.setBusiness("YLJFXX");
		int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
		int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
		int totals = 0;
		ListAndPageResult pageResult = new ListAndPageResult();
		String xml = SocsecQuery.getEndowmentXML(bean);
		String result = SocsecQuery.invokeWS(xml);
		YljfxxPO po = new YljfxxPO();
		List<YljfxxPO> lists = null;
		List<YljfxxPO> resultList=new ArrayList<YljfxxPO>();
		try {
			lists = Dom4JUtil.socsecReadXML(po, result);
			if (lists != null && lists.size() > 0) {
				/*System.out.println(lists.size());
				for(int i=0;i<lists.size();i++){
					System.out.println("倒序前"+i+"："+lists.get(i).getSSNY());
				}*/
				Collections.reverse(lists);
				/*for(int i=0;i<lists.size();i++){
					System.out.println("倒序后"+i+"："+lists.get(i).getSSNY());
				}*/
				/*Collections.sort(lists, new Comparator<YljfxxPO>() {
					@Override
					public int compare(YljfxxPO po1, YljfxxPO po2) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
						Date d1 = null;
						Date d2 = null;
						try {
							d1 = sdf.parse(po1.getSSNY());
							d2 = sdf.parse(po2.getSSNY());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						return d2.compareTo(d1);
					}
				});*/
				
				totals = lists.size();
				PageBean page = new PageBean(totals, pageSize, pageNo);
				for(int i=page.getStart();i<=page.getEnd();i++){
					resultList.add(lists.get(i-1));
				}
				/*for (int i = 0; i < resultList.size(); i++) {
					System.out.println("分页后"+i+"个："+resultList.get(i).getSSNY());
				}*/
				pageResult.setLists(resultList);
				pageResult.setPageNo(pageNo);
				pageResult.setPageSize(pageSize);
				pageResult.setPageTotal(page.getPageNum());
				pageResult.setRowTotal(totals);
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, pageResult);
			} else {
				return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR, GlobalVariable.STRING_QUERY_WEBSERVICE_ERROR);
		}
	}

	/**
	 * 养老金发放信息
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/yljffxx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object yljffxx(@RequestBody SecQueryBean bean) {
		if (StringUtils.isEmpty(bean.getAac002()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getAac003()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "姓名不能为空");
		if (StringUtils.isEmpty(bean.getAae080()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "实付年月不能为空");
		bean.setBusiness("YLJFFXX");
		int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
		int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
		int totals = 0;
		ListAndPageResult pageResult = new ListAndPageResult();
		String xml = SocsecQuery.getEndowmentXML(bean);
		String result = SocsecQuery.invokeWS(xml);
		YljffxxPO po = new YljffxxPO();
		List<YljffxxPO> lists = null;
		List<YljffxxPO> resultList=new ArrayList<YljffxxPO>();
		try {
			lists = Dom4JUtil.socsecReadXML(po, result);
			if (lists != null && lists.size() > 0) {
				totals = lists.size();
				// System.out.println(totals);
				// String s = JSON.toJSONString(lists.get(0));
				// System.out.println(s);
				PageBean page = new PageBean(totals, pageSize, pageNo);
				for(int i=page.getStart();i<=page.getEnd();i++){
					resultList.add(lists.get(i-1));
				}
				pageResult.setLists(resultList);
				pageResult.setPageNo(pageNo);
				pageResult.setPageSize(pageSize);
				pageResult.setPageTotal(page.getPageNum());
				pageResult.setRowTotal(totals);
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, pageResult);
			} else {
				logger.info("查无数据");
				return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR, GlobalVariable.STRING_QUERY_WEBSERVICE_ERROR);
		}
	}

	/**
	 * 养老保险账户信息查询
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/yljzhxx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object yljzhxx(@RequestBody SecQueryBean bean) {
		if (StringUtils.isEmpty(bean.getAac002()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "身份证号不能为空");
		if (StringUtils.isEmpty(bean.getAac003()))
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, "姓名不能为空");
		bean.setBusiness("YLJZHXX");
		int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
		int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
		int totals = 0;
		ListAndPageResult pageResult = new ListAndPageResult();
		String xml = SocsecQuery.getEndowmentXML(bean);
		String result = SocsecQuery.invokeWS(xml);
		YljzhxxPO po = new YljzhxxPO();
		List<YljzhxxPO> lists = null;
		List<YljzhxxPO> resultList=new ArrayList<YljzhxxPO>();
		try {
			lists = Dom4JUtil.socsecReadXML(po, result);
			if (lists != null && lists.size() > 0) {
				Collections.reverse(lists);

				/*System.out.println(lists.size());
				for (int i = 0; i <lists.size(); i++) {
					System.out.println("排序前，第" + i + "个：" + lists.get(i).getSSNF());
				}*/
				/*Collections.sort(lists, new Comparator<YljzhxxPO>() {
					@Override
					public int compare(YljzhxxPO po1, YljzhxxPO po2) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
						Date d1 = null;
						Date d2 = null;
						try {
							d1 = sdf.parse(po1.getSSNF());
							d2 = sdf.parse(po2.getSSNF());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						return d2.compareTo(d1);
					}
				});*/
				totals = lists.size();
				/*for (int i = 0; i <totals; i++) {
					System.out.println("排序后，第" + i + "个：" + lists.get(i).getSSNF());
				}*/
				// System.out.println(lists.size());
				// String s = JSON.toJSONString(lists.get(0));
				// System.out.println(s);
				PageBean page = new PageBean(totals, pageSize, pageNo);
				for(int i=page.getStart();i<=page.getEnd();i++){
					resultList.add(lists.get(i-1));
				}
				/*for (int i = 0; i <resultList.size(); i++) {
					System.out.println("分页后，第" + i + "个：" + resultList.get(i).getSSNF());
				}*/
				pageResult.setLists(resultList);
				pageResult.setPageNo(pageNo);
				pageResult.setPageSize(pageSize);
				pageResult.setPageTotal(page.getPageNum());
				pageResult.setRowTotal(totals);
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, pageResult);
			} else {
				return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			return this.result(GlobalVariable.RESULT_WEBSERVICE_ERROR, GlobalVariable.STRING_QUERY_WEBSERVICE_ERROR);
		}

	}

	/*public static void main(String[] args) throws ParseException {
		EndowmentController ec = new EndowmentController();
		SecQueryBean bean = new SecQueryBean();
		bean.setAac002("410102196812290552");
		bean.setAac003("薛峰");
//		bean.setPageSize(5);
//		bean.setPageNo(145);
		ec.yljfjsxx(bean);
	}*/
}
