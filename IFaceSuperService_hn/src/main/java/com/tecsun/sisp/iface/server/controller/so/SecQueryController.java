package com.tecsun.sisp.iface.server.controller.so;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tecsun.sisp.iface.common.vo.SecQueryBean;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.entity.card.FlexEmpVO;
import com.tecsun.sisp.iface.server.model.service.FlexEmpQueryServiceImpl;


/**
 * 社保查询业务接口、社保卡业务接口 Created by jerry on 2015/5/31.
 */
public class SecQueryController extends BaseController {
	
	
	@Autowired
    private FlexEmpQueryServiceImpl flexEmpQueryService;	
	
	
	public void intActivityByDate() throws Exception {
		System.out.println(new Date().toLocaleString() + "111111111111111");
		SecQueryBean bean  = new SecQueryBean();
    	List<FlexEmpVO> list = flexEmpQueryService.getFlexEmpRecodByIDcard(bean);
    	System.out.println(list.size());
	}
}
