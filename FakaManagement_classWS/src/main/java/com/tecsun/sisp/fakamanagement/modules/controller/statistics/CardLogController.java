package com.tecsun.sisp.fakamanagement.modules.controller.statistics;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.StatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.log.LogInfoResultVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.StatisticsVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.log.LogInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.statistics.StatisticsServiceImpl;



@Controller
@RequestMapping(value = "/faka/logs")
public class CardLogController extends BaseController {
	
	public final static Logger logger = Logger.getLogger(CardLogController.class);
	
	@Autowired
    private LogInfoServiceImpl logInfoService;
	
	@RequestMapping(value = "/queryLogs", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryLogs(@RequestBody LogInfoResultVO bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
	    String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
		Page<LogInfoResultVO> page=null;
		try {
            //去除查询条件两端空格
            if (null!=bean.getIdcard() && !"".equals(bean.getIdcard())){
                bean.setIdcard(bean.getIdcard().trim());
            }
			if(null==bean.getIdcard()||bean.getIdcard().equals("")){
				return error("身份证号码为空");
			}

			page=new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
			page=logInfoService.queryLogs(page,bean);
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		if (page.getCount()<1) {
			return ok(page.getCount(),"查无数据！",page.getData());
		}else{
			return ok(page.getCount(),"查询成功！",page.getData());
		}
	}


}
