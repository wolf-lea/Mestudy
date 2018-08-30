package com.tecsun.sisp.adapter.modules.sbjf.service.impl.bank;

import com.tecsun.sisp.adapter.common.https.HttpClientUtil;
import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Result;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Created by linzetian on 2017/6/15.
 * 请求银行接口
 */
public class ReqBankService {

    private static Logger logger = Logger.getLogger(ReqBankService.class);


    /**
     * 请求调用银行接口
     * @param bean 入参
     * @return
     * BankResMsg.bankResult != null 通讯正常
     * BankResMsg.bankResult == null 通讯异常
     */
    public static BankResMsg sendRequest(String adaptertokenId ,BankReqMsg bean){
        BankResMsg bankRes = new BankResMsg();
        try {
            //调用缴费接口
            String URL = String.format("%s?deviceid=%s&tokenid=%s&adaptertokenId=%s",
                    Config.getInstance().get("sbjf.req.bank"),
                    bean.getDeviceid(),bean.getTokenid(),adaptertokenId);

            String resMsg = HttpClientUtil.getData(URL, bean, true, false, 0);
            if(StringUtils.isEmpty(resMsg)){
                bankRes.setMessage("调用缴费接口返回数据为空");
                return bankRes;
            }else {
                JSONObject jsonObj = JSONObject.fromObject(resMsg);//获取json对象
                Result result = (Result)JSONObject.toBean(jsonObj,Result.class);
                bankRes = (BankResMsg) JSONObject.toBean(jsonObj.getJSONObject("data"),
                        BankResMsg.class);
                if(bankRes == null){
                    bankRes = new BankResMsg();
                }

                bankRes.setMessage(result.getMessage());


                return bankRes;
            }

        } catch (Exception e) {
            logger.error("请求银行接口出现异常", e);
            bankRes.setMessage("请求银行接口出现异常");
        }

        return  bankRes;
    }

}
