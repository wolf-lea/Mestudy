package com.tecsun.sisp.adapter.modules.question.controller;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.adapter.common.util.CommUtil;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.IdCardUtil;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.common.util.SensitivewordFilter;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.evaluate.entity.response.ContentVo;
import com.tecsun.sisp.adapter.modules.family.controller.FamilyController;
import com.tecsun.sisp.adapter.modules.family.entity.request.MyFamilyBean;
import com.tecsun.sisp.adapter.modules.family.service.impl.MyFamilyServiceImpl;
import com.tecsun.sisp.adapter.modules.question.entity.request.QuestionBean;
import com.tecsun.sisp.adapter.modules.question.entity.response.ReplyVO;
import com.tecsun.sisp.adapter.modules.question.entity.response.SendVO;
import com.tecsun.sisp.adapter.modules.question.service.impl.QuestionServiceImpl;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年3月14日 上午10:08:11
* 说明：投诉、建议、表扬点赞
*/
@Controller
@RequestMapping(value = "/adapter/questions")
public class QuestionController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(QuestionController.class);
	@Autowired
	QuestionServiceImpl questionServiceImpl;

	
	/* 添加发送信息 */
	@RequestMapping(value = "/addSendInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result addSendInfo(@RequestBody QuestionBean bean) {

		logger.info("------------addSendInfo------------");
		
		if (CommUtil.isEmptyStr(bean.getContext())) {
			return error("发送信息不能为空");
		} else if (CommUtil.isEmptyStr(bean.getType())) {
			return error("问题类型不能为空");
		} else if (CommUtil.isEmptyStr(bean.getOrgan())) {
			return error("机构不能为空");
		}else if (CommUtil.isEmptyStr(bean.getSendName())) {
			return error("姓名不能为空");
		}else if (CommUtil.isEmptyStr(bean.getSendPhone())) {
			return error("联系方式不能为空");
		}else if(CommUtil.isEmptyStr(bean.getQuestionType())){
			return error("请选择问题类型");
		}else if(bean.getSendName().length()>25){
			return error("姓名长度过长,需少于25个字");
		}
		
		try {
			//判断发送内容是否含敏感词汇
			SensitivewordFilter filter = new SensitivewordFilter();
			Set<String> set = filter.getSensitiveWord(bean.getContext(), 1);
			if(set.size()>0){
				String type = Constants.INFO_TYPE.get(bean.getType());
				String message = type+"中有敏感词汇"+set+"请确认后重新填写！";
			    return result("-300",message);
			}
            int count = questionServiceImpl.addSendInfo4sisp(bean);
            if(count > 0){
                return ok("新增发送信息成功",null);
            }
        } catch (Exception e) {
            logger.error("新增发送信息失败：" + e);
        }
        return error("新增发送信息失败");
	}
	
	/* 查询本微信ID发送列表 */
	@RequestMapping(value = "/getSendList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getSendList(@RequestBody QuestionBean bean) {

		logger.info("------------getSendList------------");
		
		 if (CommUtil.isEmptyStr(bean.getType())) {
			return error("问题类型不能为空");
		}
		
		try {
			List<SendVO> sendVO = questionServiceImpl.getSendList4sisp(bean);
			
            return ok("查询发送信息列表成功",sendVO);
            
        } catch (Exception e) {
            logger.error("查询发送信息列表失败：" + e);
        }
        return error("查询发送信息列表失败");
	}
	
	/* 查询回复信息列表 */
	@RequestMapping(value = "/getReplyList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getReplyList(@RequestBody QuestionBean bean) {

		logger.info("------------getReplyList------------");
		
		 if (CommUtil.isEmptyStr(bean.getId())) {
			return error("发送信息id不能为空");
		}
		
		try {
			List<ReplyVO> replyVO = questionServiceImpl.getReplyList4sisp(bean);
			
            return ok("查询回复信息列表成功",replyVO);
            
        } catch (Exception e) {
            logger.error("查询回复信息列表失败：" + e);
        }
        return error("查询回复信息列表失败");
	}
	
	/* 查询发送信息详情 */
	@RequestMapping(value = "/getSendDetail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getSendDetail(@RequestBody QuestionBean bean) {

		logger.info("------------getSendDetail------------");
		
		 if (CommUtil.isEmptyStr(bean.getId())) {
			return error("发送信息id不能为空");
		}
		
		try {
			SendVO sendVO = questionServiceImpl.getSendDetail4sisp(bean);
			
            return ok("查询发送信息详情成功",sendVO);
            
        } catch (Exception e) {
            logger.error("查询发送信息详情失败：" + e);
        }
        return error("查询发送信息详情失败");
	}
	
	

}
