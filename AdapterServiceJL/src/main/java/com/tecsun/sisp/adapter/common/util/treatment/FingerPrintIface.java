package com.tecsun.sisp.adapter.common.util.treatment;

import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.DictionaryUtil;
import com.tecsun.sisp.adapter.common.util.ImageChangeUtil;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.VerifyBean;
import com.tecsun.sisp.adapter.modules.treatment.entity.response.TreatPersonVO;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**指纹-认证云接口
 * Created by danmeng on 2017/3/28.
 */
public class FingerPrintIface {
    private static Logger logger = LoggerFactory.getLogger(FingerPrintIface.class);
    private static String RZY_URL = Config.getInstance().get("rzy.url");
    /**
     *判断指纹是否已采集
     * @param
     * @return
     */
    public  static Map<String, Object> isCollectFingerprint(String sfzh,String xm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", sfzh);
            jsonObject.put("name", xm);
             map =  DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_ISCOLLECTFINGERPRINT);
        } catch (Exception e) {
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "判断指纹是否已采集失败" ;
            map.put("statusCode", statusCode);
            map.put("message", message);
            logger.error("---判断指纹是否已采集 isCollectFingerprint接口失败---", e);
        }
        return map;
    }

    /**
     *指纹采集
     * @param
     * @return
     */
    public  static Map<String, Object> collectFingerprint(TreatPersonVO vo,String collectdata,String personPicPhoto,String collectPhoto) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", vo.getSfzh());
            jsonObject.put("name", vo.getXm());
            jsonObject.put("phone", vo.getPhone());
            jsonObject.put("nation", Constants.NATION.get(vo.getNation()));
            jsonObject.put("sex", vo.getSex());
            jsonObject.put("address", vo.getAddress());
            jsonObject.put("company", vo.getCompany());
            jsonObject.put("collectdata", collectdata);
            jsonObject.put("photo", personPicPhoto);
            jsonObject.put("collectPhoto", ImageChangeUtil.getImageStr(collectPhoto));
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_COLLECTFINGERPRINT);
        } catch (Exception e) {
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "指纹采集失败";
            map.put("statusCode", statusCode);
            map.put("message", message);
            logger.error("---指纹采集 collectFingerprint接口失败---", e);
        }
        return map;
    }

    /**
     *指纹认证
     * @param
     * @return
     */
    public  static Map<String, Object> fingerprintAuth(VerifyBean bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", bean.getSfzh());
            jsonObject.put("name", bean.getXm());
            jsonObject.put("collectdata", bean.getVerifyBase64());
            jsonObject.put("channel", bean.getVerifyChannel());
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_FINGERPRINTAUTH);
        } catch (Exception e) {
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "指纹认证失败" ;
            map.put("statusCode", statusCode);
            map.put("message", message);
            logger.error("---指纹认证fingerprintAuth接口失败---", e);
        }
        return map;
    }

    /**
     *指纹对比/1:1认证
     * @param
     * @return
     */
    public  static Map<String, Object> fingerOnlineAuth(VerifyBean bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("collectdata", bean.getVerifyBase64());
            jsonObject.put("comparisonData", bean.getComparisonBase64());
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_FINGERONLINEAUTH);
        } catch (Exception e) {
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "指纹对比/1:1认证失败" ;
            map.put("statusCode", statusCode);
            map.put("message", message);
            logger.error("---指纹对比/1:1认证 fingerOnlineAuth接口失败---", e);
        }
        return map;
    }
    //认证云尚未提供接口
    /**
     *保存指纹认证结果
     * 待完成
     * @param
     * @return
     */
    public  static Map<String, Object> saveFingerPrintVerifyResult(VerifyBean bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", bean.getSfzh());
            jsonObject.put("name", bean.getXm());
            jsonObject.put("channel", bean.getVerifyChannel());
            jsonObject.put("verifyResult", bean.getVerifyResult());
            jsonObject.put("failReason", bean.getFailReason());
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_UPLOADFINGERPRINTVERIFY);
        } catch (Exception e) {
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "保存指纹认证结果失败" ;
            map.put("statusCode", statusCode);
            map.put("message", message);
            logger.error("---保存指纹认证结果失败 saveFingerPrintVerifyResult接口失败---", e);
        }
        return map;
    }
}
