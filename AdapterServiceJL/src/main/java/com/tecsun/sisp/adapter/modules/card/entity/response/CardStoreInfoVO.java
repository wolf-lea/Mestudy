package com.tecsun.sisp.adapter.modules.card.entity.response;

import java.util.Date;

import com.tecsun.sisp.adapter.modules.common.entity.request.BaseVO;


/**
* @author  wunuanchan
* @version 
* 创建时间：2018年1月5日 下午3:36:07
* 说明：
*/

public class CardStoreInfoVO extends BaseVO{
	
	
	private Integer  id;//唯一编码 主键
	private String  name;//姓名
	private String  idcard;//身份证号码
	private String  cardid;//社会保障卡卡号
	private String  batchno;//批次号
	private String  companycode;//单位编码
	private String  companyname;//单位名称
	private String  angent;//联系人
	private String  phone;//联系电话
	private String  address;//联系地址
	private String  oldcfwz;//旧装箱位置
	private Integer  status;//状态
	private String fkwd;//发卡机构
	private String  cfwz;//存放位置
	private Integer  receivenum;//领卡流水号
	private String  ccid;//柜名
	private Integer  cfid;//层号
	private Integer  cbid;//盒号
	private Integer  csid;//位置
	private String bank;//所属银行
	private String bankacount;//银行账户
	private String reginalcode;//区域编码
	private String inputtime;//导入时间
	private String retentiontime;//滞留卡时间
	private String retentionuser;//滞留卡经办人
	private Integer retentionnum;//滞留次数
	private Date activetime;//滞留次数
	private String netName;//网点名称
	private String netAdress;//网点地址
	
	    private String 	channelcode;	//渠道编码
	    private String 	deviceid;		//设备编码
	    private String 	tokenid;		//权限验证码
	    private String	loginuserid;
	    
	    private String fkwdName;//发卡网点名称
	    
	    private String userType;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getAngent() {
		return angent;
	}
	public void setAngent(String angent) {
		this.angent = angent;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOldcfwz() {
		return oldcfwz;
	}
	public void setOldcfwz(String oldcfwz) {
		this.oldcfwz = oldcfwz;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFkwd() {
		return fkwd;
	}
	public void setFkwd(String fkwd) {
		this.fkwd = fkwd;
	}
	public String getCfwz() {
		return cfwz;
	}
	public void setCfwz(String cfwz) {
		this.cfwz = cfwz;
	}
	public Integer getReceivenum() {
		return receivenum;
	}
	public void setReceivenum(Integer receivenum) {
		this.receivenum = receivenum;
	}
	public String getCcid() {
		return ccid;
	}
	public void setCcid(String ccid) {
		this.ccid = ccid;
	}
	public Integer getCfid() {
		return cfid;
	}
	public void setCfid(Integer cfid) {
		this.cfid = cfid;
	}
	public Integer getCbid() {
		return cbid;
	}
	public void setCbid(Integer cbid) {
		this.cbid = cbid;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankacount() {
		return bankacount;
	}
	public void setBankacount(String bankacount) {
		this.bankacount = bankacount;
	}
	public String getReginalcode() {
		return reginalcode;
	}
	public void setReginalcode(String reginalcode) {
		this.reginalcode = reginalcode;
	}
	public String getInputtime() {
		return inputtime;
	}
	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}
	public String getRetentiontime() {
		return retentiontime;
	}
	public void setRetentiontime(String retentiontime) {
		this.retentiontime = retentiontime;
	}
	public String getRetentionuser() {
		return retentionuser;
	}
	public void setRetentionuser(String retentionuser) {
		this.retentionuser = retentionuser;
	}
	public Integer getRetentionnum() {
		return retentionnum;
	}
	public void setRetentionnum(Integer retentionnum) {
		this.retentionnum = retentionnum;
	}
	public Date getActivetime() {
		return activetime;
	}
	public void setActivetime(Date activetime) {
		this.activetime = activetime;
	}
	public String getNetName() {
		return netName;
	}
	public void setNetName(String netName) {
		this.netName = netName;
	}
	public String getNetAdress() {
		return netAdress;
	}
	public void setNetAdress(String netAdress) {
		this.netAdress = netAdress;
	}
	public String getChannelcode() {
		return channelcode;
	}
	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getTokenid() {
		return tokenid;
	}
	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
	public String getLoginuserid() {
		return loginuserid;
	}
	public void setLoginuserid(String loginuserid) {
		this.loginuserid = loginuserid;
	}
	public String getFkwdName() {
		return fkwdName;
	}
	public void setFkwdName(String fkwdName) {
		this.fkwdName = fkwdName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	

}
