package com.tecsun.sisp.adapter.modules.finance.payUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.modules.finance.entity.request.AlipayRequest;
import com.tecsun.sisp.adapter.modules.finance.entity.response.WxpayResponse;
import com.tecsun.sisp.adapter.modules.finance.payUtils.model.ResultObject;
import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by tecsun on 2017/7/21.
 */
public class AlipayUtils {

    private static final Logger logger = LoggerFactory.getLogger(AlipayUtils.class);

    //调起支付宝接口生成预支付订单
    public String openAlipay(AlipayRequest req)throws Exception{

        String alipayUrl = Config.getInstance().get("zfb.alipay_url");
        String appId = Config.getInstance().get("zfb.app_id");
        String appPrivateKey = Config.getInstance().get("zfb.app_private_key");
        String alipayPublicKey = Config.getInstance().get("zfb.alipay_public_key");

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(alipayUrl, appId, appPrivateKey, "json", "utf-8", alipayPublicKey, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

        //String outTradeNo = getOutTradeNo();//生成唯一订单号
        model.setBody(req.getBody());
        model.setSubject(req.getSubject());
        model.setOutTradeNo(req.getOutTradeNo());
        model.setTimeoutExpress(req.getTimeoutExpress());
        model.setTotalAmount(req.getTotalAmount());
        model.setProductCode(req.getProductCode());
        request.setBizModel(model);
        //request.setNotifyUrl(req.getNotifyUrl());

        AlipayTradeAppPayResponse response = null;
        try {    //这里和普通的接口调用不同，使用的是sdkExecute
            response = alipayClient.sdkExecute(request);
            System.out.println("调用成功：" + response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            logger.info("统一订单接口返回信息："+response);
        } catch (AlipayApiException e) {
            logger.error("调用失败："+e);
            return response.getBody();
        }
        return response.getBody();
    }

    //调起支付宝接口生成预支付订单
    public ResultObject openWXpay(AlipayRequest request)throws Exception{

        // 微信统一下单接口路径
        String UNIFORMORDER = Config.getInstance().get("wx.uniformorder");
        // 微信商户号
        String MCHID = Config.getInstance().get("wx.mchid");
        // 微信回调地址
        String NOTIFYURL = "http://127.0.0.1:81";
        // 微信交易类型
        String TRADETYPE = "APP";
        //微信APPID
        String APPID = Config.getInstance().get("wx.appid");

        ResultObject result = new ResultObject();// 返回数据结果集合
        try {
            if(StringUtils.isEmpty(request.getOrderNum())){
                result.setMsg("参数：orderNum 为空");
                result.setResultCode("-1");
                //return JSON.toJSONString(result);
                return result;
            }
            if(StringUtils.isEmpty(request.getMoney())){
                result.setMsg("参数：money 为空");
                result.setResultCode("-1");
                return result;
            }
            if(StringUtils.isEmpty(request.getSubject())){
                result.setMsg("参数：subject 为空");
                result.setResultCode("-1");
                return result;
            }

            logger.info("开始组装入参===");
            SortedMap<Object,Object> parame = new TreeMap<Object,Object>();
            parame.put("appid", APPID);
            parame.put("mch_id", MCHID);// 商家账号。
            String randomStr = getRandomString(18).toUpperCase();
            parame.put("nonce_str", randomStr);// 随机字符串
            parame.put("body", new String(request.getSubject().getBytes(),"UTF-8"));// 商品描述
            parame.put("out_trade_no", request.getOrderNum());// 商户订单编号
            //支付金额
            parame.put("total_fee", BigDecimal.valueOf(Long.parseLong(request.getMoney())).multiply(new BigDecimal(1)).toString());// 消费金额
            parame.put("notify_url", NOTIFYURL);// 回调地址
            parame.put("trade_type", TRADETYPE);// 交易类型APP
            //parame.put("spbill_create_ip","192.168.7.235");

            String sign =createSign(parame);
            parame.put("sign", sign);// 数字签证

            String xml = getRequestXML(parame);
            String content = HttpUtil.sendPost(UNIFORMORDER, xml);
            try {
                content = new String(content.getBytes("gbk"), "utf-8");
                System.out.println("统一订单接口返回信息：" + content);

                JSONObject jsonObject = JSONObject.parseObject(XmltoJsonUtil.xml2JSON(content));
                JSONObject return_xml = jsonObject.getJSONObject("xml");
                JSONArray return_code = return_xml.getJSONArray("return_code");
                String code = (String) return_code.get(0);
                logger.info("返回信息解析完毕===");

                WxpayResponse response = new WxpayResponse();
                if (code.equalsIgnoreCase("FAIL")) {
                    result.setMsg("微信统一订单下单失败");
                    result.setResultCode("-1");
                } else if (code.equalsIgnoreCase("SUCCESS")) {
                    JSONArray prepay_id = return_xml.getJSONArray("prepay_id");
                    JSONArray json_appid = return_xml.getJSONArray("appid");
                    JSONArray mch_id = return_xml.getJSONArray("mch_id");
                    JSONArray nonce_str = return_xml.getJSONArray("nonce_str");

                    String timestamp = String.valueOf(System.currentTimeMillis()/1000);

                    SortedMap<Object,Object> param = new TreeMap<Object,Object>();
                    param.put("appid",(String)json_appid.get(0));
                    param.put("noncestr",(String)nonce_str.get(0));
                    param.put("package","Sign=WXPay");
                    param.put("partnerid",(String)mch_id.get(0));
                    param.put("prepayid",(String)prepay_id.get(0));
                    param.put("timestamp",timestamp);
                    String sign2 =createSign(param);// 2次数字签证

                    response.setPrepay_id((String)prepay_id.get(0));
                    response.setAppid((String)json_appid.get(0));
                    response.setSign(sign2);
                    response.setMch_id((String)mch_id.get(0));
                    response.setNonce_str((String)nonce_str.get(0));
                    response.setTimestamp(timestamp);
                    response.setPackageValue("Sign=WXPay");

                    logger.info("调用成功！");
                    result.setMsg("微信统一订单下单成功");
                    result.setResultCode("1");
                    result.setData(response);
                }
            }catch (Exception e){
                logger.error("调用失败:"+e);
                result.setMsg(e.getMessage());
                result.setResultCode("-1");
                result.setData(content);
                return result;
            }
            return result;

        } catch (Exception e) {
            logger.error("调用失败！"+e);
            result.setMsg(e.getMessage());
            result.setResultCode("-1");
            return result;
        }
    }

    // 随机字符串生成
    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    //拼接xml 请求路径
    public static String getRequestXML(SortedMap<Object, Object> parame){
        StringBuffer buffer = new StringBuffer();
        buffer.append("<xml>");
        Set set = parame.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String)entry.getKey();
            String value = (String)entry.getValue();
            //过滤相关字段sign
            if("sign".equalsIgnoreCase(key)){
                buffer.append("<"+key+">"+"<![CDATA["+value+"]]>"+"</"+key+">");
            }else{
                buffer.append("<"+key+">"+value+"</"+key+">");
            }
        }
        buffer.append("</xml>");
        return buffer.toString();
    }

    //创建md5 数字签证
    public static String createSign(SortedMap<Object, Object> parame){

        //微信APIKEY
        String APIKEY = Config.getInstance().get("wx.api.key");

        StringBuffer buffer = new StringBuffer();
        Set set = parame.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String)entry.getKey();
            Object value = (String)entry.getValue();
            if(null != value && !"".equals(value) && !"sign".equals(key) && !"key".equals(key)){
                buffer.append(key+"="+value+"&");
            }
        }
        buffer.append("key="+APIKEY);
        String sign = MyMd5Util.md5(buffer.toString()).toUpperCase();
        System.out.println("签名参数："+sign);
        return sign;
    }

}
