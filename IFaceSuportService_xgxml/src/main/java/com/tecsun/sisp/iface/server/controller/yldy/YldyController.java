package com.tecsun.sisp.iface.server.controller.yldy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.iface.common.vo.Result;
import com.tecsun.sisp.iface.server.model.service.yldy.YldyService;

@Controller
@RequestMapping("/iface/yldy")
public class YldyController {

	@Autowired
	private YldyService yldyService;
	
	@RequestMapping(value="/gxhcs",method=RequestMethod.GET)
	@ResponseBody
	public Result getGxhcs(@RequestParam(value="idcard",required=true)String idcard,
			@RequestParam(value="aic161",required=true)String aic161,
			@RequestParam(value="aic162",required=true)String aic162,
			@RequestParam(value="jfdc",required=false)String jfdc){
		
		//因为网页传过来的参数有百分比数字,所以用序号代替,也可以前台encode,服务端decode,但是好麻烦..
		if(StringUtils.isEmpty(jfdc)){
			jfdc = "60%";
		}else{
			if(jfdc.equals("1")){
				jfdc = "60%";
			}else if(jfdc.equals("2")){
				jfdc = "100%";
			}else if(jfdc.equals("3")){
				jfdc = "150%";
			}else if(jfdc.equals("4")){
				jfdc = "200%";
			}else if(jfdc.equals("5")){
				jfdc = "300%";
			}else{
				jfdc = "60%";
			}
		}
		
		Result result = yldyService.getGxhcs(idcard,aic161,aic162,jfdc);
		
		return result;
	}
}
