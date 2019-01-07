package com.tecsun.sisp.adapter.modules.card.entity.request;
/**
* @author  wunuanchan
* @version 
* 创建时间：2017年12月26日 下午6:57:55
* 说明：
*/

public class EvaAndGrantBean {
	
	private String serviceNumber;//评价业务唯一编码
	private String cardReceiveId;////发卡记录id
	
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public String getCardReceiveId() {
		return cardReceiveId;
	}
	public void setCardReceiveId(String cardReceiveId) {
		this.cardReceiveId = cardReceiveId;
	}
	
	
	
	

}
