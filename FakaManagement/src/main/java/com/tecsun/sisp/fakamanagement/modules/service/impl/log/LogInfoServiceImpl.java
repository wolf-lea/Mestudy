package com.tecsun.sisp.fakamanagement.modules.service.impl.log;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.result.log.BatchLogVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.log.LogInfoResultVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.log.LogInfoVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("logInfoService")
public class LogInfoServiceImpl extends BaseService {
	
	/**
	 * mapper文件namespace属性值
	 */
	private final static String packageName="com.tecsun.sisp.fakamanagement.modules.service.impl.log.LogInfoServiceImpl.";
	
	
	public Integer addLog(LogInfoVO vo) {
		return this.getFakaSqlSessionTemplate().insert(packageName+"addLog",vo);
	}


	public void addLog(int ciid,String cardid, int loginuserid, String loginname, String content) {
		LogInfoVO vo=new LogInfoVO();
		vo.setCiid(ciid);
		vo.setLoginid(loginuserid);
		vo.setName(loginname);
		vo.setContent(content);
		vo.setCardid(cardid);
		addLog(vo);
	}


	public Page<LogInfoResultVO> queryLogs(Page<LogInfoResultVO> page, LogInfoResultVO bean) {
		bean.setPage(page);
		List<LogInfoResultVO> list=this.getFakaSqlSessionTemplate().selectList(packageName+"queryLogs", bean);
		page.setData(list);
		return page;
	}

	/**
	 * 插入批次操作日志表
	 */
	public int addBatchLog(BatchLogVO vo){
		return this.getFakaSqlSessionTemplate().insert("addBatchLog",vo);
	}

}
