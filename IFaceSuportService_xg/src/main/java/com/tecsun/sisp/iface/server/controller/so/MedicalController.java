package com.tecsun.sisp.iface.server.controller.so;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.iface.common.util.GlobalVariable;
import com.tecsun.sisp.iface.common.util.PageBean;
import com.tecsun.sisp.iface.common.vo.common.param.PersonNoAndPageBean;
import com.tecsun.sisp.iface.common.vo.common.query.PersonNoAndPage;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.common.result.ListAndPageResult;
import com.tecsun.sisp.iface.common.vo.so.po.medical.MxbxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YdanzxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YlgrjbxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YlgrjsxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YlgrzhxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YljfxxcxPO;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.model.service.so.impl.MedicalServiceImpl;

/**
 * Created by DG on 2015/9/25.
 */
@Controller
@RequestMapping(value = "/iface/so")
public class MedicalController extends BaseController {

	public final static Logger logger = Logger.getLogger(MedicalController.class);

	@Autowired
	public MedicalServiceImpl medicalServiceImpl;

	/**
	 * 医疗保险个人参保信息
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/ylgrjbxx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object ylgrjbxx(@RequestBody SecQueryBean bean) {
		if (StringUtils.isNotEmpty(bean.getAac002())) {
			YlgrzhxxcxPO zhxxpo = medicalServiceImpl.smsgrbhzhye(bean);
			YlgrjbxxPO yljbpo = medicalServiceImpl.smsYlgrjbxxByperonsId(bean);
			if (yljbpo != null && !"".equals(yljbpo)) {
				if (null != zhxxpo && !"".equals(zhxxpo)) {
					yljbpo.setZhye(zhxxpo.getZhye());
				}
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, yljbpo);
			} else {
				if (!medicalServiceImpl.smsCheckoutPersonNo(bean))
					return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST,
							GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
				else
					return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
			}
		} else {
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, GlobalVariable.STRING_REQUEST_NOT_IDCARD);
		}
	}

	/**
	 * 医疗保险个人缴费信息查询
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/yljfxxcx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object yljfxxcx(@RequestBody SecQueryBean bean) {
		if (StringUtils.isNotEmpty(bean.getAac002())) {

			int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
			int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
			int totals = medicalServiceImpl.smsCountYljfxxcx(bean);

			if (totals == 0) {
				if (!medicalServiceImpl.smsCheckoutPersonNo(bean))
					return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST,
							GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
				else
					return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
			}

			PageBean page = new PageBean(totals, pageSize, pageNo);

			bean.setRowStart(page.getStart());
			bean.setRowEnd(page.getEnd());
			List<YljfxxcxPO> lists = medicalServiceImpl.smsYljfxxcx(bean);
			if (!lists.isEmpty()) {
				YljfxxcxPO po = new YljfxxcxPO();
				for (int i = 0; i < lists.size(); i++) {
					po.setPersonId(lists.get(i).getPersonId() == null ? "" : lists.get(i).getPersonId());
				}
				ListAndPageResult result = new ListAndPageResult();
				result.setLists(lists);
				result.setPageNo(pageNo);
				result.setPageSize(pageSize);
				result.setPageTotal(page.getPageNum());
				result.setRowTotal(totals);
				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, result);
			} else {
				return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
			}

		} else {
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, GlobalVariable.STRING_REQUEST_NOT_IDCARD);
		}
	}

	/**
	 * 医疗保险个人账户信息查询
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/ylgrzhxxcx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object ylgrzhxxcx(@RequestBody SecQueryBean bean) {
		if (StringUtils.isNotEmpty(bean.getAac002())) {

			int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
			int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
			int totals = medicalServiceImpl.smsCountYlgrzhxxcx(bean);

			if (totals == 0) {
				if (!medicalServiceImpl.smsCheckoutPersonNo(bean))
					return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST,
							GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
				else
					return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
			}

			PageBean page = new PageBean(totals, pageSize, pageNo);

			bean.setRowStart(page.getStart());
			bean.setRowEnd(page.getEnd());
			List<YlgrzhxxcxPO> lists = medicalServiceImpl.smsYlgrzhxxcx(bean);
			if (!lists.isEmpty()) {
				ListAndPageResult result = new ListAndPageResult();
				result.setLists(lists);
				result.setPageNo(pageNo);
				result.setPageSize(pageSize);
				result.setPageTotal(page.getPageNum());
				result.setRowTotal(totals);

				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, result);
			} else {
				return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
			}
		} else {
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, GlobalVariable.STRING_REQUEST_NOT_IDCARD);
		}
	}

	/**
	 * 医疗保险费用结算信息查询
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/ylgrjsxxcx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object ylgrjsxxcx(@RequestBody SecQueryBean bean) {
		if (StringUtils.isNotEmpty(bean.getAac002())) {

			int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
			int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
			int totals = medicalServiceImpl.smsCountYlgrjsxxcx(bean);

			if (totals == 0) {
				if (!medicalServiceImpl.smsCheckoutPersonNo(bean))
					return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST,
							GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
				else
					return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
			}

			PageBean page = new PageBean(totals, pageSize, pageNo);
			bean.setRowStart(page.getStart());
			bean.setRowEnd(page.getEnd());
			List<YlgrjsxxcxPO> lists = medicalServiceImpl.smsYlgrjsxxcx(bean);
			if (!lists.isEmpty()) {
				ListAndPageResult result = new ListAndPageResult();
				result.setLists(lists);
				result.setPageNo(pageNo);
				result.setPageSize(pageSize);
				result.setPageTotal(page.getPageNum());
				result.setRowTotal(totals);

				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, result);
			} else {
				return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
			}
		} else {
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, GlobalVariable.STRING_REQUEST_NOT_IDCARD);
		}
	}

	/**
	 * 慢性病审批信息查询
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/mxbxxcx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object mxbxxcx(@RequestBody SecQueryBean bean) {
		if (StringUtils.isNotEmpty(bean.getAac002())) {

			int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
			int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
			int totals = medicalServiceImpl.smsCountMxbxxcx(bean);

			if (totals == 0) {
				if (!medicalServiceImpl.smsCheckoutPersonNo(bean))
					return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST,
							GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
				else
					return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
			}

			PageBean page = new PageBean(totals, pageSize, pageNo);

			bean.setRowStart(page.getStart());
			bean.setRowEnd(page.getEnd());
			List<MxbxxcxPO> lists = medicalServiceImpl.smsMxbxxcx(bean);
			if (!lists.isEmpty()) {
				ListAndPageResult result = new ListAndPageResult();
				result.setLists(lists);
				result.setPageNo(pageNo);
				result.setPageSize(pageSize);
				result.setPageTotal(page.getPageNum());
				result.setRowTotal(totals);

				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, result);
			} else {
				return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
			}
		} else {
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, GlobalVariable.STRING_REQUEST_NOT_IDCARD);
		}
	}

	/**
	 * 异地安置审批信息查询
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/ydanzxxcx", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object ydanzxxcx(@RequestBody SecQueryBean bean) {
		if (StringUtils.isNotEmpty(bean.getAac002())) {

			int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
			int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
			int totals = medicalServiceImpl.smsCountYdanzxxcx(bean);

			if (totals == 0) {
				if (!medicalServiceImpl.smsCheckoutPersonNo(bean))
					return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST,
							GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
				else
					return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
			}

			PageBean page = new PageBean(totals, pageSize, pageNo);

			bean.setRowStart(page.getStart());
			bean.setRowEnd(page.getEnd());
			List<YdanzxxcxPO> lists = medicalServiceImpl.smsYdanzxxcx(bean);
			if (!lists.isEmpty()) {
				ListAndPageResult result = new ListAndPageResult();
				result.setLists(lists);
				result.setPageNo(pageNo);
				result.setPageSize(pageSize);
				result.setPageTotal(page.getPageNum());
				result.setRowTotal(totals);

				return this.result(GlobalVariable.RESULT_SUCCESS, GlobalVariable.STRING_QUERY_SUCCESS, result);
			} else {
				return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
			}
		} else {
			return this.result(GlobalVariable.RESULT_PARAM_WRONG, GlobalVariable.STRING_REQUEST_NOT_IDCARD);
		}
	}

	/**
	 * 账户余额
	 * 
	 * @param bean
	 * @return
	 */
	/*
	 * @RequestMapping(value="/grbhzhye", method= RequestMethod.POST , produces
	 * = "application/json")
	 * 
	 * @ResponseBody public Object grbhzhye(@RequestBody SecQueryBean bean){
	 * if(StringUtils.isNotEmpty(bean.getAac002())) { List<YlgrzhxxcxPO> result
	 * = medicalServiceImpl.smsgrbhzhye(bean); if(result != null &&
	 * result.size()>0){ return
	 * this.result(GlobalVariable.RESULT_SUCCESS,GlobalVariable.
	 * STRING_QUERY_SUCCESS,result.get(0)); } else{
	 * if(!medicalServiceImpl.smsCheckoutPersonNo(bean)) return
	 * this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST,GlobalVariable.
	 * STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD); else return
	 * this.result(GlobalVariable.RESULT_NOT_DATA,GlobalVariable.STRING_NOT_DATA
	 * ); } }else{ return
	 * this.result(GlobalVariable.RESULT_PARAM_WRONG,GlobalVariable.
	 * STRING_REQUEST_NOT_IDCARD); } }
	 */

}
