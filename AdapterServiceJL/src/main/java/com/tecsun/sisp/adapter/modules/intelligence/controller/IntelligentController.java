package com.tecsun.sisp.adapter.modules.intelligence.controller;

import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.intelligence.entity.request.AnswerBean;
import com.tecsun.sisp.adapter.modules.intelligence.intellUtils.SinoVoiceUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**智能客服
 * @ClassName: CardDetectController
 * Created by danmeng on 2017/8/28.
 */
@Controller
@RequestMapping(value = "/adapter/intell")
public class IntelligentController extends BaseController{
    private static Logger logger = LoggerFactory.getLogger(IntelligentController.class);

    @RequestMapping(value = "answerQuestions",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result answerQuestions(@RequestBody AnswerBean bean) throws Exception{
        if(StringUtils.isBlank(bean.getUserId())||StringUtils.isBlank(bean.getIntelligentCorp())
                ||StringUtils.isBlank(bean.getChannelcode())){
            return   result(Constants.RESULT_MESSAGE_PARAM, "缺少查询信息");
        } if(StringUtils.isBlank(bean.getQuestion())&&bean.getQuestionVoice()==null){
            return   result(Constants.RESULT_MESSAGE_PARAM, "请输入需要咨询的问题");
        }
        if("SinoVoice".equals(bean.getIntelligentCorp())){
            return SinoVoiceUtils.answerQuestion(bean);
        }else{
            return  result(Constants.RESULT_MESSAGE_ERROR, "请输入正确智能客服公司");

        }

    }

}
