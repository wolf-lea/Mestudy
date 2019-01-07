package com.tecsun.sisp.iface.server.controller.fee;

import com.tecsun.sisp.iface.common.vo.Result;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.outerface.fee.FeeService;
import com.tecsun.sisp.iface.server.outerface.fee.HandlerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/iface/fee")
public class FeeController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(FeeController.class);

    /**
     * 欠费查询
     * @param param
     * @return
     */
    @RequestMapping(value = "/debtsQuery", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result debtsQuery(@RequestParam("param") String param) {
    
        HandlerResult result = new FeeService("127.0.0.1",
                Integer.parseInt("8888"),
                Integer.parseInt("20")).debtsQuery(param);
        return null;
    }

    /**
     * 欠费缴费
     * @param param
     * @return
     */
    @RequestMapping(value = "/debtsPay", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result debtsPay(@RequestParam("param") String param) {
    
        HandlerResult result = new FeeService("127.0.0.1",
                Integer.parseInt("8888"),
                Integer.parseInt("20")).debtsPay(param);
        return null;
    }
    /**
     * 取消缴费
     * @param param
     * @return
     */
    @RequestMapping(value = "/cancelPay", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result cancelPay(@RequestParam("param") String param) {
    
        HandlerResult result = new FeeService("127.0.0.1",
                Integer.parseInt("8888"),
                Integer.parseInt("20")).cancelPay(param);
        return null;
    }
    /**
     * 缴费查询
     * @param param
     * @return
     */
    @RequestMapping(value = "/sendQuryInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result sendQuryInfo(@RequestParam("param") String param) {
    
        HandlerResult bkResult = new FeeService("127.0.0.1",
                Integer.parseInt("8888"),
                Integer.parseInt("20")).sendQuryInfo(param);
        return null;
    }
    
    /**
     * 缴费申报
     * @param param
     * @return
     */
    @RequestMapping(value = "/payApply", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result payApply(@RequestParam("param") String param) {
    
        HandlerResult bkResult = new FeeService("127.0.0.1",
                Integer.parseInt("8888"),
                Integer.parseInt("20")).payApply(param);
        return null;
    }
    /**
     * 缴费申报撤销
     * @param param
     * @return
     */
    @RequestMapping(value = "/revokeApply", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result revokeApply(@RequestParam("param") String param) {
    
        HandlerResult bkResult = new FeeService("127.0.0.1",
                Integer.parseInt("8888"),
                Integer.parseInt("20")).revokeApply(param);
        return null;
    }
}
