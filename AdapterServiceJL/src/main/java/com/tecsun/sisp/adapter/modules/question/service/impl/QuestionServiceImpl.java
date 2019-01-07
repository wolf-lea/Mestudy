package com.tecsun.sisp.adapter.modules.question.service.impl;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.question.entity.request.QuestionBean;
import com.tecsun.sisp.adapter.modules.question.entity.response.ReplyVO;
import com.tecsun.sisp.adapter.modules.question.entity.response.SendVO;


/**
* @author  wunuanchan
* @version 
* 创建时间：2018年3月14日 上午10:11:06
* 说明：投诉、建议、表扬点赞
*/
@Service("questionServiceImpl")
public class QuestionServiceImpl extends BaseService {
	
	
	public static final Logger logger = Logger
			.getLogger(QuestionServiceImpl.class);

	private static String NAMESPACE = "com.tecsun.sisp.adapter.modules.question.service.impl.QuestionServiceImpl.";

	/* 添加发送信息 */
	public int addSendInfo4sisp(QuestionBean bean) throws Exception{
		int count = getSqlSessionTemplate().insert(NAMESPACE + "addSendInfo", bean);
		return count;
	}
	
	/* 查询发送信息列表 */
	public List<SendVO> getSendList4sisp(QuestionBean bean) throws Exception{
		List<SendVO> sendVO = getSqlSessionTemplate().selectList(NAMESPACE + "getSendList", bean);
		return sendVO;
	}
	
	/* 查询回复信息列表 */
	public List<ReplyVO> getReplyList4sisp(QuestionBean bean) throws Exception{
		List<ReplyVO> replyVO = getSqlSessionTemplate().selectList(NAMESPACE + "getReplyList", bean);
		return replyVO;
	}
	
	/* 查询发送信息详情 */
	public SendVO getSendDetail4sisp(QuestionBean bean) throws Exception{
		SendVO sendVO = getSqlSessionTemplate().selectOne(NAMESPACE + "getSendDetail", bean);
		return sendVO;
	}

}
