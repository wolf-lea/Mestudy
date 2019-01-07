package com.tecsun.sisp.adapter.modules.finance.controller.onlinePay;

import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.finance.entity.request.AlipayRequest;
import com.tecsun.sisp.adapter.modules.finance.entity.response.WxpayResponse;
import com.tecsun.sisp.adapter.modules.finance.payUtils.AlipayUtils;
import com.tecsun.sisp.adapter.modules.finance.payUtils.model.ResultObject;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/*
*
 * 支付宝支付、微信支付相关接口
 * Created by tecsun on 2017/7/13.
 *
*/
@Controller
@RequestMapping({"/adapter/alipay"})
public class AlipayController extends BaseController {

    public static final Logger logger = Logger.getLogger(AlipayController.class);

    //调起接口生成预支付订单
    @RequestMapping(value = "/openAlipay", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result openAlipay(@RequestBody AlipayRequest req) {

        String body = "";//body就是预支付订单
        try {
            logger.info("传入的入参是：" + new ObjectMapper().writeValueAsString(req));
            logger.info("调用开始===");
            AlipayUtils alipayUtils = new AlipayUtils();
            WxpayResponse response = new WxpayResponse();
            if ("alipay".equals(req.getType())) {
                body = alipayUtils.openAlipay(req);//调用支付宝支付
                response.setBody(body);
            }else {
                ResultObject wxResult = alipayUtils.openWXpay(req);//调用微信支付
                if ("1".equals(wxResult.getResultCode())) {
                    if (wxResult.getData() != null) {
                        //body = new ObjectMapper().writeValueAsString(wxResult.getData());
                        //body = body.replaceAll("\"","\'");
                        response = (WxpayResponse) wxResult.getData();
                    }
                }else {
                    return error("调用失败:"+wxResult.getData());
                }
            }
            if (response != null) {
                //System.out.println("调用成功：" + body);//就是orderString 可以直接给客户端请求，无需再做处理。
                logger.info("调用结果：" + response);
                logger.info("调用结束===");
                return ok("调用成功！", response);
            } else {
                logger.error("调用失败！");
                return error("调用失败！");
            }
        } catch (Exception e) {
            logger.error("操作发生了异常：" + e);
        }
        return error("调用失败！");
    }

}
