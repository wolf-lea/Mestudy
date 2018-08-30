package com.tecsun.sisp.adapter.modules.card.service.impl;

import org.springframework.stereotype.Service;

import com.tecsun.sisp.adapter.modules.card.entity.request.ResultBusMakeDetal;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;

@Service("CssCardService")
public class CssCardServiceImpl extends BaseService {
	//private final static Logger log = LoggerFactory.getLogger(CssCardServiceImpl.class);
	public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.card.service.impl.CssCardServiceImpl.";
	
	/**
	 * 查卡位置
	 * @param certNum
	 * @return
	 */
	public  ResultBusMakeDetal getCardwz4Card(String certNum) {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getCardwz", certNum);
	}
    

}
