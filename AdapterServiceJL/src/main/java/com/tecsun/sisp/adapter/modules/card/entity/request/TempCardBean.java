package com.tecsun.sisp.adapter.modules.card.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
/**
* @author  tanzhiyong
* @version 
* 创建时间：2018年9月4日 
* 说明：临时卡卡发放入参
*/
public class TempCardBean extends SecQueryBean{
	
	private String tempCardNo;	//临时卡编号
	private String atr;			//卡复位码
	private Integer rkwd;		//入库网点
	private String detailstatus;//状态 00-  初始（入库）01-  已发放02-  已注销（遗失）03-  已作废（损坏）04-  已回收05-  注销（不在使用）
	private String wasteTime;//作废时间
	private String wastePosition;//作废位置
	private String cancelTime;//注销时间
	private String recoveryTime;//回收时间
	private String detailCreateTime;//表TEMPCARD_DETAIL创建时间字段
	private String detailUpdateTime;//表TEMPCARD_DETAIL修改时间字段
	private Integer tempCardDetailId;	//临时卡入库明细表主键
	private String name;	//姓名
	private String idcard;	//身份证号码
	private String cardid;	//社会保障卡卡号
	private String agentIdcard;	//代办人身份证
	private String agentName;	//代办人姓名
	private String fkrq;	//发卡日期
	private String kyxq;	//卡有效期
	private String az01lid;	//用卡（卡信息表）
	private String az01id;	//预制卡、临时卡
	private String az02id;	//业务经办
	private String peopleInfoStatus;	//状态: 00-	正在使用01-	发放失败02-	卡已注销03-	卡已作废04-	卡已回收
	private String grant_time;//发放时间
	private String peopleInfoCreateTime;//表TEMPCARD_PERSON_INFO创建时间字段
	private String peopleInfoUpdateTime;//表TEMPCARD_PERSON_INFO修改时间字段
	private String photo;	//个人照片
	private String idcardPhotoUp;//身份证正面
	private String idcardPhotoDown;//身份证反面
	private String signPhoto;//电子签字
	
	

	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getIdcardPhotoUp() {
		return idcardPhotoUp;
	}
	public void setIdcardPhotoUp(String idcardPhotoUp) {
		this.idcardPhotoUp = idcardPhotoUp;
	}
	public String getIdcardPhotoDown() {
		return idcardPhotoDown;
	}
	public void setIdcardPhotoDown(String idcardPhotoDown) {
		this.idcardPhotoDown = idcardPhotoDown;
	}
	public String getSignPhoto() {
		return signPhoto;
	}
	public void setSignPhoto(String signPhoto) {
		this.signPhoto = signPhoto;
	}
	public String getTempCardNo() {
		return tempCardNo;
	}
	public void setTempCardNo(String tempCardNo) {
		this.tempCardNo = tempCardNo;
	}
	public String getAtr() {
		return atr;
	}
	public void setAtr(String atr) {
		this.atr = atr;
	}
	public Integer getRkwd() {
		return rkwd;
	}
	public void setRkwd(Integer rkwd) {
		this.rkwd = rkwd;
	}	
	public String getDetailstatus() {
		return detailstatus;
	}
	public void setDetailstatus(String detailstatus) {
		this.detailstatus = detailstatus;
	}
	public String getWasteTime() {
		return wasteTime;
	}
	public void setWasteTime(String wasteTime) {
		this.wasteTime = wasteTime;
	}
	public String getWastePosition() {
		return wastePosition;
	}
	public void setWastePosition(String wastePosition) {
		this.wastePosition = wastePosition;
	}
	public String getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}
	public String getRecoveryTime() {
		return recoveryTime;
	}
	public void setRecoveryTime(String recoveryTime) {
		this.recoveryTime = recoveryTime;
	}
	public String getDetailCreateTime() {
		return detailCreateTime;
	}
	public void setDetailCreateTime(String detailCreateTime) {
		this.detailCreateTime = detailCreateTime;
	}
	public String getDetailUpdateTime() {
		return detailUpdateTime;
	}
	public void setDetailUpdateTime(String detailUpdateTime) {
		this.detailUpdateTime = detailUpdateTime;
	}
	public Integer getTempCardDetailId() {
		return tempCardDetailId;
	}
	public void setTempCardDetailId(Integer tempCardDetailId) {
		this.tempCardDetailId = tempCardDetailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getAgentIdcard() {
		return agentIdcard;
	}
	public void setAgentIdcard(String agentIdcard) {
		this.agentIdcard = agentIdcard;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getFkrq() {
		return fkrq;
	}
	public void setFkrq(String fkrq) {
		this.fkrq = fkrq;
	}
	public String getKyxq() {
		return kyxq;
	}
	public void setKyxq(String kyxq) {
		this.kyxq = kyxq;
	}
	public String getAz01lid() {
		return az01lid;
	}
	public void setAz01lid(String az01lid) {
		this.az01lid = az01lid;
	}
	public String getAz01id() {
		return az01id;
	}
	public void setAz01id(String az01id) {
		this.az01id = az01id;
	}
	public String getAz02id() {
		return az02id;
	}
	public void setAz02id(String az02id) {
		this.az02id = az02id;
	}
	public String getPeopleInfoStatus() {
		return peopleInfoStatus;
	}
	public void setPeopleInfoStatus(String peopleInfoStatus) {
		this.peopleInfoStatus = peopleInfoStatus;
	}
	public String getGrant_time() {
		return grant_time;
	}
	public void setGrant_time(String grant_time) {
		this.grant_time = grant_time;
	}
	public String getPeopleInfoCreateTime() {
		return peopleInfoCreateTime;
	}
	public void setPeopleInfoCreateTime(String peopleInfoCreateTime) {
		this.peopleInfoCreateTime = peopleInfoCreateTime;
	}
	public String getPeopleInfoUpdateTime() {
		return peopleInfoUpdateTime;
	}
	public void setPeopleInfoUpdateTime(String peopleInfoUpdateTime) {
		this.peopleInfoUpdateTime = peopleInfoUpdateTime;
	}
	
	
}
