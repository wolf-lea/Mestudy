package com.tecsun.sisp.adapter.modules.ine.util;

import java.util.Date;
import java.util.List;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * 将参数bean对象转换为sql条件bean
 *@author sipeng
 *@email zsp_banyue@163.com
 *@date 2017年12月2日
 * 
 */
public class SqlQueryBean extends SecQueryBean{
	private String sfzh;//身份证号
	private String areaCode;//区域编码
	private List<String> positionCode;//岗位编码
	private Date workDate;//发布日期
	private String accountMethod;//结算方式
	private long infoId;//记录主键
	private String opType;//操作类型
	private String queryType;//查询标识 
	private String infoType;// 信息类型
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	
	public List<String> getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(List<String> positionCode) {
		this.positionCode = positionCode;
	}
	
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public String getAccountMethod() {
		return accountMethod;
	}
	public void setAccountMethod(String accountMethod) {
		this.accountMethod = accountMethod;
	}
	public long getInfoId() {
		return infoId;
	}
	public void setInfoId(long infoId) {
		this.infoId = infoId;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	
	
}
