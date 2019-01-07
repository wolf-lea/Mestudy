package com.tecsun.sisp.adapter.modules.intelligence.intellUtils;

import com.google.gson.JsonObject;
import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.intelligence.entity.request.AnswerBean;
import com.tecsun.sisp.adapter.modules.intelligence.entity.response.*;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**捷通华声接口:智能问答、语音识别
 * Created by danmeng on 2017/8/28.
 */
public class SinoVoiceUtils {
    private static Logger logger = LoggerFactory.getLogger(SinoVoiceUtils.class);
    //智能问答
    public static String csrProtocolId = Config.getInstance().get("sinovoice.csr.protocolId");
    public static String csrRobotHashCode = Config.getInstance().get("sinovoice.csr.robotHashCode");
    public static String csrTalkerId = Config.getInstance().get("sinovoice.csr.talkerId");
    public static String csrReceiverId = Config.getInstance().get("sinovoice.csr.receiverId");
    public static String csrAppKey = Config.getInstance().get("sinovoice.csr.appKey");
    public static String csrIsNeedClearHistory = Config.getInstance().get("sinovoice.csr.isNeedClearHistory");
    public static String csrIsQuestionQuery = Config.getInstance().get("sinovoice.csr.isQuestionQuery");
    public static String csrUrl = Config.getInstance().get("sinovoice.csr.url");

    //语音识别
    public static String asrSdkVersion = Config.getInstance().get("sinovoice.asr.sdkVersion");
    public static String asrTaskConfig = Config.getInstance().get("sinovoice.asr.taskConfig");
    public static String asrUdid = Config.getInstance().get("sinovoice.asr.udid");
    public static String asrResultFormat = Config.getInstance().get("sinovoice.asr.resultFormat");
    public static String asrAppKey = Config.getInstance().get("sinovoice.asr.appKey");
    public static String asrDevKey = Config.getInstance().get("sinovoice.asr.devkey");
    public static String asrUrl = Config.getInstance().get("sinovoice.asr.url");

    public static Result answerQuestion(AnswerBean bean) {
        if (StringUtils.isBlank(bean.getUserId()) || StringUtils.isBlank(bean.getType())) {
            return new Result(Constants.RESULT_MESSAGE_PARAM, "缺少查询信息");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "查询失败，请重新输入";
        if (Constants.MESSAGE_VOICE.equals(bean.getType())) {
            String voiceText = recognise(bean);
            if (StringUtils.isBlank(voiceText)) {
                return new Result(statusCode, "语音识别失败，请重新输入");
            } else bean.setQuestion(voiceText);
        }
        if (StringUtils.isBlank(bean.getQuestion())) {
            return new Result(Constants.RESULT_MESSAGE_PARAM, "缺少查询信息");
        }
        String answer = queryAction(bean);
        if (StringUtils.isNotBlank(answer)) {
            if (!"error".equals(answer)) {
                Map map = getCsrAnswer(answer);
                if (map != null && map.size() > 0) {
                    String state = String.valueOf(map.get("state"));
                    if (Constants.RESULT_MESSAGE_SUCCESS.equals(state)) {
                        try {
                            AnswerVO answerVO = JsonMapper.buildNormalMapper().fromJson(
                                    JsonMapper.buildNormalMapper().toJson(map.get("answerVO")), AnswerVO.class);
                            answerVO.setQuestionMsg(bean.getQuestion());
                            return new Result(state, "查询成功", answerVO);
                        } catch (Exception e) {
                            logger.error("智能问答回复转换失败：" + answer, e);
                        }
                    }
                }
            }
        }
        return new Result(statusCode, message);
    }

    //捷通华声-智能问答
    public static String queryAction(AnswerBean bean) {
        String answer = null;
        try {
            JsonObject json = new JsonObject();
            json.addProperty("protocolId", csrProtocolId);
            json.addProperty("robotHashCode", csrRobotHashCode);
            json.addProperty("platformConnType", Constants.ROBOT_CONNTYPE.get(bean.getChannelcode()));
            json.addProperty("userId", bean.getUserId());
            json.addProperty("talkerId", csrTalkerId);
            json.addProperty("receiverId", csrReceiverId);
            json.addProperty("appKey", csrAppKey);
            json.addProperty("sendTime", new Date().getTime());
            json.addProperty("type", bean.getType());
            json.addProperty("isNeedClearHistory", csrIsNeedClearHistory);
            json.addProperty("query", bean.getQuestion());
            json.addProperty("isQuestionQuery", csrIsQuestionQuery);
            answer = DictionaryUtil.postClientRequest(String.valueOf(json), csrUrl);
        } catch (Exception e) {
            logger.error("queryAction出错", e);
            answer = "error";
        }
        return answer;
    }

    //解析
    public static Map<String, Object> getCsrAnswer(String answer) {
        Map<String, Object> map = new HashMap<String, Object>();
        AnswerVO answerVO = new AnswerVO();
        int state = 0;
        List<String> vagueMsgList = new ArrayList<String>();
        if (StringUtils.isNotBlank(answer)) {
            try {
                JSONObject jsonObject = JSONObject.fromObject(answer);
                //问答接口返回协议ID一定为6,返回结果0-成功 1-失败
                if (jsonObject.getInt("protocolId") == 6 && jsonObject.getInt("result") == 0) {
                    JsonMapper json = new JsonMapper(null);
                    SinoVoiceAnswerVO sinoVoiceAnswerVO = json.fromJson(answer, SinoVoiceAnswerVO.class);
                    //多选回答
                    if (sinoVoiceAnswerVO.getVagueNode() != null) {
                        if (sinoVoiceAnswerVO.getVagueNode().getItemList() != null &&
                                !sinoVoiceAnswerVO.getVagueNode().getItemList().isEmpty()) {
                            VagueNode vagueNode = sinoVoiceAnswerVO.getVagueNode();
                            if (StringUtils.isNotBlank(vagueNode.getPromptVagueMsg()))
                                answerVO.setPromptVagueMsg(vagueNode.getPromptVagueMsg());
                            // 获取推荐问题
                            for (ItemMsg itemMsg : vagueNode.getItemList()) {
                                vagueMsgList.add(itemMsg.getNum() + "、" + itemMsg.getQuestion());
                            }
                            if (StringUtils.isNotBlank(vagueNode.getEndVagueMsg()))
                                answerVO.setEndVagueMsg(vagueNode.getEndVagueMsg());
                        }
                    }
                    //单一回复
                    if (sinoVoiceAnswerVO.getSingleNode() != null) {
                        SingleNode singleNode = sinoVoiceAnswerVO.getSingleNode();
                        if(StringUtils.isNotBlank(singleNode.getCmd())){
                            answerVO.setCmd(singleNode.getCmd());
                        }
                        if (singleNode.getList() != null && singleNode.getList().size() > 0) {
                            //图文回复
                            if (singleNode.getIsRichText() != 0) {
                                answerVO.setNewsNodeList(singleNode.getList());
                            }
                        }
                        answerVO.setIsRichText(singleNode.getIsRichText());
                        if (StringUtils.isNotBlank(singleNode.getAnswerMsg())) {
                            answerVO.setAnswerMsg(singleNode.getAnswerMsg());
                        }
                        if (StringUtils.isNotBlank(singleNode.getStandardQuestion())) {
                            answerVO.setStandardQuestMsg(singleNode.getStandardQuestion());
                        }
                        answerVO.setScore(singleNode.getScore());
                    }
                    state = 200;//成功
                } else {
                    state = -2;//失败
                }
            } catch (Exception e) {
                logger.error("解析智能答复失败" + answer, e);
                state = -999;
            }
        } else {
            state = -1;//回复为空
        }
        if (vagueMsgList != null && vagueMsgList.size() > 0) answerVO.setVagueMsgList(vagueMsgList);
        map.put("state", state);
        map.put("answerVO", answerVO);
        return map;
    }

    //语音识别 POST请求时HTTP头信息
    public static Map<String, String> getAsrHeaders(String deviceid) {
        String requestDate = CommUtil.getNowDateLongStr();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-app-key", asrAppKey);
        headers.put("x-sdk-version", asrSdkVersion);
        headers.put("x-request-date", requestDate);
        headers.put("x-task-config", asrTaskConfig);
        headers.put("x-session-key", CommUtil.generateValue(requestDate + asrDevKey));//用md5加密字符串
     /*   if (StringUtils.isNotBlank(deviceid))
            headers.put("x-udid", deviceid);//无用
        else*/
            headers.put("x-udid", asrUdid);
        headers.put("x-result-format", asrResultFormat);
        return headers;
    }

    //捷通华声-语音识别
    public static String recognise(AnswerBean bean) {
        String voiceText = null;
        HttpClient httpClient = new HttpClient();
        PostMethod method = new PostMethod(asrUrl);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
        byte[] questionVoice = bean.getQuestionVoice();
        try {
            if (null != questionVoice) {
                // 这是设置语音文件二进制流 解决中文乱码问题
                RequestEntity entity = new ByteArrayRequestEntity(questionVoice, "UTF-8");
                method.setRequestEntity(entity);
                // 设置请求头
                Map<String, String> headers = getAsrHeaders(bean.getDeviceid());
                if (!headers.equals(null)) {
                    for (Map.Entry<String, String> map : headers.entrySet()) {
                        method.setRequestHeader(map.getKey(), map.getValue());
                    }
                }
            }
            int httpStatus = httpClient.executeMethod(method);
            /** 请求发送成功，并得到响应 **/
            if (httpStatus == HttpStatus.SC_OK) {
                /** 读取服务器返回过来的json字符串数据 **/
                byte[] b = method.getResponseBody();
                JSONObject jsonResult = JSONObject.fromObject(new String(b, "UTF-8"));
                jsonResult = jsonResult.getJSONObject("ResponseInfo");
                if (!jsonResult.isNullObject()) {
                    voiceText = jsonResult.getString("ResCode").equals("Success") ? jsonResult
                            .getJSONObject("Result").getString("Text") : "";
                }
            }

        } catch (Exception e) {
            logger.error("捷通华声-语音识别失败：", e);
        }
        return voiceText;
    }
}