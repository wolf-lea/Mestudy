package com.tecsun.sisp.adapter.common.util.treatment;

import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.DictionaryUtil;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.CollectBean;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.VerifyListBean;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**调用认证云接口
 * Created by danmeng on 2017/3/27.
 */
public class TreatmentIface {
    private static Logger logger = LoggerFactory.getLogger(TreatmentIface.class);
    private static String RZY_URL = Config.getInstance().get("rzy.url");
    /**
     *查询个人基本信息
     * @param
     * @return
     */
    public  static Map<String, Object> selectPersonInfo(String sfzh,String xm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", sfzh);
            jsonObject.put("name", xm);
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_PERSONINFO);
        } catch (Exception e) {
            String result = Constants.RESULT_MESSAGE_ERROR;
            String message = "查询个人基本信息失败" ;
            map.put("result", result);
            map.put("message", message);
            logger.error("---查询个人基本信息selectPersonInfo接口失败---", e);
        }
        return map;
    }

    /**
     * 查询个人已采集项
     * @param bean
     * @return
     */
    public  static Map<String, Object> collectList( SecQueryBean bean){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", bean.getSfzh());
            jsonObject.put("isCross", Config.getInstance().get("rzy.is_cross"));
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_COLLECTLIST);
        } catch (Exception e) {
            String result =  Constants.RESULT_MESSAGE_ERROR;
            String message = "查询个人已采集项目接口失败";
            map.put("result", result);
            map.put("message", message);
            logger.error("---查询个人已采集项目collectList接口失败---",e);
        }
        return map;
    }
    /**
     * 查询认证结果
     * @param bean
     * @return
     */
    public  static Map<String, Object> verifyResult( VerifyListBean bean){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", bean.getSfzh());
            jsonObject.put("pageNo", bean.getPageno());
            jsonObject.put("pageSize", bean.getPagesize());
            jsonObject.put("channel", bean.getChannel());
            jsonObject.put("type", bean.getType());
            jsonObject.put("verifyResult", bean.getVerifyResult());
            jsonObject.put("beginTime", bean.getBeginTime());
            jsonObject.put("endTime", bean.getEndTime());
            jsonObject.put("isCross", Config.getInstance().get("rzy.is_cross"));
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_VERIFYRESULT);
        } catch (Exception e) {
            String result =  Constants.RESULT_MESSAGE_ERROR;
            String message = "查询个人认证记录失败";
            map.put("result", result);
            map.put("message", message);
            logger.error("---查询个人认证记录verifyResult接口失败---",e);
        }
        return map;
    }
    /**
     * 查询个人采集数据
     * @param sfzh
     * @return
     */
    public  static Map<String, Object> queryCollectInfo(String sfzh,String xm,String coltype){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", sfzh);
            jsonObject.put("name", xm);
            jsonObject.put("type", coltype);
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_QUERYCOLLECTION);
        } catch (Exception e) {
            String result =  Constants.RESULT_MESSAGE_ERROR;
            String message = "查询个人采集数据失败";
            map.put("result", result);
            map.put("message", message);
            logger.error("---查询个人采集数据verifyResult接口失败---",e);
        }
        return map;
    }



}
