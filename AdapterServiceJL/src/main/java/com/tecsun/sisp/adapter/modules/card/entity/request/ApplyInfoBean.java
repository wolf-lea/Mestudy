package com.tecsun.sisp.adapter.modules.card.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * 社保卡业务
 *@author sipeng
 *@email zsp_banyue@163.com
 *@date 2017年12月4日
 * 
 */
public class ApplyInfoBean extends SecQueryBean {
	private long applyId;//申领记录id
	
	private long recordId;//审核记录id

	public long getApplyId() {
		return applyId;
	}

	public void setApplyId(long applyId) {
		this.applyId = applyId;
	}

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	
	
	
}
