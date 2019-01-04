package com.tecsun.sisp.iface.server.controller.collect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.iface.server.controller.BaseController;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年4月21日 上午9:58:55
 * 类说明
 */

@Controller
@RequestMapping(value = "/collect/card")
public class CollectController  extends BaseController {
	
	 //测试
    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json")
   	@ResponseBody
   	public void test() throws Exception{
 	   System.out.println("111111111111111111");
    }

}
