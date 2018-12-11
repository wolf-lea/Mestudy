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
import com.tecsun.sisp.iface.common.vo.common.query.PersonNoAndPage;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.common.result.ListAndPageResult;
import com.tecsun.sisp.iface.common.vo.so.po.birth.SyjfxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.birth.SyjtxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YlgrjbxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YljfxxcxPO;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.model.service.so.impl.BirthServiceImpl;

/**
 * Created by DG on 2015/10/16.
 */
@Controller
@RequestMapping(value = "/iface/so")
public class BirthController extends BaseController {

    public final static Logger logger = Logger.getLogger(BirthController.class);

    @Autowired
    public BirthServiceImpl birthServiceImpl;

    /**
     * 生育保险个人参保信息
     * @param bean
     * @return
     */
    @RequestMapping(value="/sygrjbxx", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Object sygrjbxx(@RequestBody SecQueryBean bean){
        if(StringUtils.isNotEmpty(bean.getAac002())) {
            List<YlgrjbxxPO> result = birthServiceImpl.smsSygrjbxxByPersonNO(bean);
            if(result!=null && !result.isEmpty()){
                return this.result(GlobalVariable.RESULT_SUCCESS,GlobalVariable.STRING_QUERY_SUCCESS,result.get(0));
            } else{
                if(!birthServiceImpl.smsCheckoutPersonNo(bean))
                    return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST,GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
                else
                    return this.result(GlobalVariable.RESULT_NOT_DATA,GlobalVariable.STRING_NOT_DATA);
            }
        }else{
            return this.result(GlobalVariable.RESULT_PARAM_WRONG,GlobalVariable.STRING_REQUEST_NOT_IDCARD);
        }
    }

    /**
     * 生育保险个人缴费信息查询
     * @param bean
     * @return
     */
    @RequestMapping(value="/syjfxxcx", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Object syjfxxcx(@RequestBody SecQueryBean bean){
        if(StringUtils.isNotEmpty(bean.getAac002())) {

            int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
            int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
            int totals = birthServiceImpl.smsCountSyjfxxcx(bean);

            if(totals == 0) {
                if (!birthServiceImpl.smsCheckoutPersonNo(bean))
                    return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
                else
                    return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
            }

            PageBean page = new PageBean(totals,pageSize,pageNo);
            bean.setRowStart(page.getStart());
            bean.setRowEnd(page.getEnd());
            List<YljfxxcxPO> lists = birthServiceImpl.smsSyjfxxcx(bean);
            if(!lists.isEmpty()){
            ListAndPageResult result = new ListAndPageResult();
            result.setLists(lists);
            result.setPageNo(pageNo);
            result.setPageSize(pageSize);
            result.setPageTotal(page.getPageNum());
            result.setRowTotal(totals);

            return this.result(GlobalVariable.RESULT_SUCCESS,GlobalVariable.STRING_QUERY_SUCCESS,result);
            }else{
            	return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
            }
        }else{
            return this.result(GlobalVariable.RESULT_PARAM_WRONG,GlobalVariable.STRING_REQUEST_NOT_IDCARD);
        }
    }

    /**
     * 生育保险医疗费用信息查询
     * @param bean
     * @return
     */
    @RequestMapping(value="/syylfyxxcx", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Object syylfyxxcx(@RequestBody SecQueryBean bean){
        if(StringUtils.isNotEmpty(bean.getAac002())) {

            int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
            int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
            int totals = birthServiceImpl.smsCountSyylfyxxcx(bean);

            if(totals == 0) {
                if (!birthServiceImpl.smsCheckoutPersonNo(bean))
                    return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
                else
                    return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
            }

            PageBean page = new PageBean(totals,pageSize,pageNo);

            bean.setRowStart(page.getStart());
            bean.setRowEnd(page.getEnd());
            List<SyjfxxcxPO> lists = birthServiceImpl.smsSyylfyxxcx(bean);
            if(!lists.isEmpty()){
            ListAndPageResult result = new ListAndPageResult();
            result.setLists(lists);
            result.setPageNo(pageNo);
            result.setPageSize(pageSize);
            result.setPageTotal(page.getPageNum());
            result.setRowTotal(totals);

            return this.result(GlobalVariable.RESULT_SUCCESS,GlobalVariable.STRING_QUERY_SUCCESS,result);
            }else{
            	return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
            }
        }else{
            return this.result(GlobalVariable.RESULT_PARAM_WRONG,GlobalVariable.STRING_REQUEST_NOT_IDCARD);
        }
    }

    /**
     * 生育保险生育津贴查询
     * @param bean
     * @return
     */
    @RequestMapping(value="/syjtxxcx", method= RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public Object syjtxxcx(@RequestBody SecQueryBean bean){
        if(StringUtils.isNotEmpty(bean.getAac002())) {

            int pageNo = bean.getPageNo() == 0 ? 1 : bean.getPageNo();
            int pageSize = bean.getPageSize() == 0 ? GlobalVariable.PARAM_PAGE_SIZE : bean.getPageSize();
            int totals = birthServiceImpl.smsCountSyjtxxcx(bean);

            if(totals == 0) {
                if (!birthServiceImpl.smsCheckoutPersonNo(bean))
                    return this.result(GlobalVariable.RESULT_QUERY_NOT_EXIST, GlobalVariable.STRING_NOT_DATA + "," + GlobalVariable.STRING_DB_NOT_IDCARD);
                else
                    return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
            }

            PageBean page = new PageBean(totals,pageSize,pageNo);
            bean.setRowStart(page.getStart());
            bean.setRowEnd(page.getEnd());
            List<SyjtxxcxPO> lists = birthServiceImpl.smsSyjtxxcx(bean);
            if(!lists.isEmpty()){
            ListAndPageResult result = new ListAndPageResult();
            result.setLists(lists);
            result.setPageNo(pageNo);
            result.setPageSize(pageSize);
            result.setPageTotal(page.getPageNum());
            result.setRowTotal(totals);

            return this.result(GlobalVariable.RESULT_SUCCESS,GlobalVariable.STRING_QUERY_SUCCESS,result);
            }else{
            	return this.result(GlobalVariable.RESULT_NOT_DATA, GlobalVariable.STRING_NOT_DATA);
            }
        }else{
            return this.result(GlobalVariable.RESULT_PARAM_WRONG,GlobalVariable.STRING_REQUEST_NOT_IDCARD);
        }
    }

}
