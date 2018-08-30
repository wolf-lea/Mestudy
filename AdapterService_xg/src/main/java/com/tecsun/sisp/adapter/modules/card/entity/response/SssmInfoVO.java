package com.tecsun.sisp.adapter.modules.card.entity.response;



/**
*
* @author 作者名  付伟锋
* @date 日期   2017- 03- 30 15:22
* @version 版本标识 V.1.0.0
* @补换卡表出参Bean
*/
public class SssmInfoVO {
    
	private String name;
	private String certNum;
	private String addDate;//申领时间
	private String applyChannel;//申领渠道
	
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public String getApplyChannel() {
		return applyChannel;
	}
	public void setApplyChannel(String applyChannel) {
		this.applyChannel = applyChannel;
	}
	
}