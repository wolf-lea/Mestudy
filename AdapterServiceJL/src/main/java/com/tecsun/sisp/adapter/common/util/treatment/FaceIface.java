package com.tecsun.sisp.adapter.common.util.treatment;

import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.DictionaryUtil;
import com.tecsun.sisp.adapter.common.util.ImageChangeUtil;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.VerifyBean;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**人脸-认证云接口
 * Created by danmeng on 2017/3/27.
 */
public class FaceIface {
    @Autowired
    private CommServiceImpl commService;
    private static Logger logger = LoggerFactory.getLogger(FaceIface.class);
    private static String RZY_URL = Config.getInstance().get("rzy.url");
    /**
     *照片采集
     * @param
     * @return
     */
    public  static Map<String, Object> collectPhoto( String gfzh,String xm,String picPath,String phone) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", gfzh);
            jsonObject.put("name", xm);
            jsonObject.put("photo", ImageChangeUtil.getImageStr(picPath));
            jsonObject.put("phone",phone);
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_COLLECTPHOTO);
        } catch (Exception e) {
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "照片采集失败" ;
            map.put("statusCode", statusCode);
            map.put("message", message);
            logger.error("---照片采集collectPhoto接口失败---", e);
        }
        return map;
    }

    /**
     *人脸检测（红外活体）
     * @param
     * @return
     */
    public  static Map<String, Object> liveDetection(String picPath,String redpicPath) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("photo1", ImageChangeUtil.getImageStr(picPath));
            jsonObject.put("photo2", ImageChangeUtil.getImageStr(redpicPath));
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_LIVEDETECTION);
        } catch (Exception e) {
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "人脸检测失败" ;
            map.put("statusCode", statusCode);
            map.put("message", message);
            logger.error("---人脸检测liveDetection接口失败---", e);
        }
        return map;
    }

    /**
     *人脸认证（只传一张图片）
     * @param
     * @return
     */
    public  static Map<String, Object> onlineauth( String sfzh,String picPath,String channel) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", sfzh);
            jsonObject.put("photo", ImageChangeUtil.getImageStr(picPath));
            jsonObject.put("channel",channel);
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_FACEVERIFICATION);
        } catch (Exception e) {
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "人脸认证失败" ;
            map.put("statusCode", statusCode);
            map.put("message", message);
            logger.error("---人脸认证onlineauth接口失败---", e);
        }
        return map;
    }

    /**
     * 提交活体检测结果
     * @param
     * @return
     */
    public  static Map<String, Object> uploadLiveDetectResult( VerifyBean bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", bean.getSfzh());
            jsonObject.put("type", bean.getVerifyType());
            jsonObject.put("channel", bean.getVerifyChannel());
            jsonObject.put("verifyResult", bean.getVerifyResult());
            jsonObject.put("verifyTime", bean.getVerifyTime());
            jsonObject.put("failReason", bean.getFailReason());
             map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_UPLOADLIVEDETECTRESULT);
        } catch (Exception e) {
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "提交活体检测结果失败" ;
            map.put("statusCode", statusCode);
            map.put("message", message);
            logger.error("---提交活体检测结果uploadLiveDetectResult接口失败---", e);
        }
        return map;
    }

    /**
     *人脸对比/1:1认证
     * @param
     * @return
     */
    public  static Map<String, Object> comparePhoto(String picPath,String comPicPath) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("photo1", ImageChangeUtil.getImageStr(picPath));
            jsonObject.put("photo2", ImageChangeUtil.getImageStr(comPicPath));
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL+ Constants.RZY_COMPAREPHOTO);
        } catch (Exception e) {
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "对比失败" ;
            map.put("statusCode", statusCode);
            map.put("message", message);
            logger.error("---人脸对比/1:1认证 comparePhoto接口失败---", e);
        }
        return map;
    }

    /**
     *国政通人像比对
     * @param
     * @return
     */
    public  static Map<String, Object> gztFaceCompare(String gfzh,String xm,String picPath,String verifyChannel) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", gfzh);
            jsonObject.put("name", xm);
            jsonObject.put("photo", ImageChangeUtil.getImageStr(picPath));
            jsonObject.put("channel", verifyChannel);
            map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL+ Constants.RZY_GZTFACECOMPARE);
        } catch (Exception e) {
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "国政通人像比对失败" ;
            map.put("statusCode", statusCode);
            map.put("message", message);
            logger.error("---国政通人像比对 gztFaceCompare---", e);
        }
        return map;
    }



}
