package com.tecsun.sisp.adapter.modules.card.entity.response;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年1月20日 下午3:05:51
* 说明：
*/

public class FastCardVO {
	
	
		
	     @JsonProperty(value = "AAE010")
		 private String AAE010;//
	     
	     @JsonProperty(value = "REMARKS")
		 private String REMARKS;// 
	     
		 private String 时间;//
		 
		 @JsonProperty(value = "AAB301")
		 private String AAB301;//
		 
		 @JsonProperty(value = "CITYSENDADDR")
		 private String CITYSENDADDR;//
		 
		 @JsonProperty(value = "ORGANID")
		 private String ORGANID;//
		 
		 @JsonProperty(value = "BANKTIME0")
		 private String BANKTIME0;//
		 
		 @JsonProperty(value = "VALIDTAG")
		 private String VALIDTAG;//
		 
		 @JsonProperty(value = "APPLYTIME")
		 private String APPLYTIME;// 
		 
		 @JsonProperty(value = "ERR")
		 private String ERR;//
		 
		 @JsonProperty(value = "CARDTYPE")
		 private String CARDTYPE;// 
		 
		 @JsonProperty(value = "AAZ500")
		 private String AAZ500;// 
		 
		 @JsonProperty(value = "AAE010B")
		 private String AAE010B;// 
		 
		 @JsonProperty(value = "BHYY")
		 private String BHYY;// 
		 
		 @JsonProperty(value = "TRANSACTTYPE")
		 private String TRANSACTTYPE;//
		 
		 @JsonProperty(value = "AAC002")
		 private String AAC002;//
		 
		 @JsonProperty(value = "KS")
		 private String KS;// 
		 
		 @JsonProperty(value = "BANKFINISHTIME0")
		 private String BANKFINISHTIME0;//
		 
		 @JsonProperty(value = "AAE010A")
		 private String AAE010A;// 
		 
		 @JsonProperty(value = "INSUREFINISHTIME")
		 private String INSUREFINISHTIME;// 
		 
		 @JsonProperty(value = "BATCHNO")
		 private String BATCHNO;// 
		 
		 @JsonProperty(value = "CITYSENDTIME")
		 private String CITYSENDTIME;//
		 
		 @JsonProperty(value = "CITYTIME")
		 private String CITYTIME;//
		 
		 @JsonProperty(value = "AAE008B")
		 private String AAE008B;//
		 
		 @JsonProperty(value = "INSURETIME")
		 private String INSURETIME;//
		 
		 @JsonProperty(value = "AAC003")
		 private String AAC003;// 
		 
		 @JsonProperty(value = "ZXWZ")
		 private String ZXWZ;//
		 
		 @JsonProperty(value = "AAE008")
		 private String AAE008;//
		 
		 @JsonProperty(value = "GETTIME")
		 private String GETTIME;// 
		 
		 @JsonProperty(value = "PROVINCETIME")
		 private String PROVINCETIME;//
		 
		 
	     private String rkwz;//入库位置
	     private String netName;//领卡网点
	     private String netAdress;//领卡地址
	     private Integer status;//入库状态；1：待入柜、2：已入柜、3：待发卡、4：滞留卡、5：问题卡、6：待激活、7：已发卡、8：待找卡; 
	     private String isTake;//是否可领卡
	     private String isPrint;//是否打印凭条；1：是 0：否
	     private String isPush;//是否推送；1：是 0：否
	     private Integer id;//发行系统卡id
	     private String telephone;//联系电话
	     private String bankServiceInfo;//银行服务综合窗口信息
	     private String ccid;//柜名
	     
	     private String solveWay;	// 解决制卡失败方法
	     
	     private String longitude;//经度
	 	private String latitude;//纬度
	     
	     @JsonIgnore 
		public String getAAE010() {
			return AAE010;
		}
	     @JsonIgnore
		public void setAAE010(String aAE010) {
			AAE010 = aAE010;
		}
	     @JsonIgnore
		public String getREMARKS() {
			return REMARKS;
		}
	     @JsonIgnore
		public void setREMARKS(String rEMARKS) {
			REMARKS = rEMARKS;
		}
		public String get时间() {
			return 时间;
		}
		public void set时间(String 时间) {
			this.时间 = 时间;
		}
		@JsonIgnore
		public String getAAB301() {
			return AAB301;
		}
		@JsonIgnore
		public void setAAB301(String aAB301) {
			AAB301 = aAB301;
		}
		@JsonIgnore
		public String getCITYSENDADDR() {
			return CITYSENDADDR;
		}
		@JsonIgnore
		public void setCITYSENDADDR(String cITYSENDADDR) {
			CITYSENDADDR = cITYSENDADDR;
		}
		@JsonIgnore
		public String getORGANID() {
			return ORGANID;
		}
		@JsonIgnore
		public void setORGANID(String oRGANID) {
			ORGANID = oRGANID;
		}
		@JsonIgnore
		public String getBANKTIME0() {
			return BANKTIME0;
		}
		@JsonIgnore
		public void setBANKTIME0(String bANKTIME0) {
			BANKTIME0 = bANKTIME0;
		}
		@JsonIgnore
		public String getVALIDTAG() {
			return VALIDTAG;
		}
		@JsonIgnore
		public void setVALIDTAG(String vALIDTAG) {
			VALIDTAG = vALIDTAG;
		}
		@JsonIgnore
		public String getAPPLYTIME() {
			return APPLYTIME;
		}
		@JsonIgnore
		public void setAPPLYTIME(String aPPLYTIME) {
			APPLYTIME = aPPLYTIME;
		}
		@JsonIgnore
		public String getERR() {
			return ERR;
		}
		@JsonIgnore
		public void setERR(String eRR) {
			ERR = eRR;
		}
		@JsonIgnore
		public String getCARDTYPE() {
			return CARDTYPE;
		}
		@JsonIgnore
		public void setCARDTYPE(String cARDTYPE) {
			CARDTYPE = cARDTYPE;
		}
		@JsonIgnore
		public String getAAZ500() {
			return AAZ500;
		}
		@JsonIgnore
		public void setAAZ500(String aAZ500) {
			AAZ500 = aAZ500;
		}
		@JsonIgnore
		public String getAAE010B() {
			return AAE010B;
		}
		@JsonIgnore
		public void setAAE010B(String aAE010B) {
			AAE010B = aAE010B;
		}
		@JsonIgnore
		public String getBHYY() {
			return BHYY;
		}
		@JsonIgnore
		public void setBHYY(String bHYY) {
			BHYY = bHYY;
		}
		@JsonIgnore
		public String getTRANSACTTYPE() {
			return TRANSACTTYPE;
		}
		@JsonIgnore
		public void setTRANSACTTYPE(String tRANSACTTYPE) {
			TRANSACTTYPE = tRANSACTTYPE;
		}
		@JsonIgnore
		public String getAAC002() {
			return AAC002;
		}
		@JsonIgnore
		public void setAAC002(String aAC002) {
			AAC002 = aAC002;
		}
		@JsonIgnore
		public String getKS() {
			return KS;
		}
		@JsonIgnore
		public void setKS(String kS) {
			KS = kS;
		}
		@JsonIgnore
		public String getBANKFINISHTIME0() {
			return BANKFINISHTIME0;
		}
		@JsonIgnore
		public void setBANKFINISHTIME0(String bANKFINISHTIME0) {
			BANKFINISHTIME0 = bANKFINISHTIME0;
		}
		@JsonIgnore
		public String getAAE010A() {
			return AAE010A;
		}
		@JsonIgnore
		public void setAAE010A(String aAE010A) {
			AAE010A = aAE010A;
		}
		@JsonIgnore
		public String getINSUREFINISHTIME() {
			return INSUREFINISHTIME;
		}
		@JsonIgnore
		public void setINSUREFINISHTIME(String iNSUREFINISHTIME) {
			INSUREFINISHTIME = iNSUREFINISHTIME;
		}
		@JsonIgnore
		public String getBATCHNO() {
			return BATCHNO;
		}
		@JsonIgnore
		public void setBATCHNO(String bATCHNO) {
			BATCHNO = bATCHNO;
		}
		@JsonIgnore
		public String getCITYSENDTIME() {
			return CITYSENDTIME;
		}
		@JsonIgnore
		public void setCITYSENDTIME(String cITYSENDTIME) {
			CITYSENDTIME = cITYSENDTIME;
		}
		@JsonIgnore
		public String getCITYTIME() {
			return CITYTIME;
		}
		@JsonIgnore
		public void setCITYTIME(String cITYTIME) {
			CITYTIME = cITYTIME;
		}
		@JsonIgnore
		public String getAAE008B() {
			return AAE008B;
		}
		@JsonIgnore
		public void setAAE008B(String aAE008B) {
			AAE008B = aAE008B;
		}
		@JsonIgnore
		public String getINSURETIME() {
			return INSURETIME;
		}
		@JsonIgnore
		public void setINSURETIME(String iNSURETIME) {
			INSURETIME = iNSURETIME;
		}
		@JsonIgnore
		public String getAAC003() {
			return AAC003;
		}
		@JsonIgnore
		public void setAAC003(String aAC003) {
			AAC003 = aAC003;
		}
		@JsonIgnore
		public String getZXWZ() {
			return ZXWZ;
		}
		@JsonIgnore
		public void setZXWZ(String zXWZ) {
			ZXWZ = zXWZ;
		}
		@JsonIgnore
		public String getAAE008() {
			return AAE008;
		}
		@JsonIgnore
		public void setAAE008(String aAE008) {
			AAE008 = aAE008;
		}
		@JsonIgnore
		public String getGETTIME() {
			return GETTIME;
		}
		@JsonIgnore
		public void setGETTIME(String gETTIME) {
			GETTIME = gETTIME;
		}
		@JsonIgnore
		public String getPROVINCETIME() {
			return PROVINCETIME;
		}
		@JsonIgnore
		public void setPROVINCETIME(String pROVINCETIME) {
			PROVINCETIME = pROVINCETIME;
		}
		public String getRkwz() {
			return rkwz;
		}
		public void setRkwz(String rkwz) {
			this.rkwz = rkwz;
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
		
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getIsTake() {
			return isTake;
		}
		public void setIsTake(String isTake) {
			this.isTake = isTake;
		}
		public String getIsPrint() {
			return isPrint;
		}
		public void setIsPrint(String isPrint) {
			this.isPrint = isPrint;
		}
		public String getIsPush() {
			return isPush;
		}
		public void setIsPush(String isPush) {
			this.isPush = isPush;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getBankServiceInfo() {
			return bankServiceInfo;
		}
		public void setBankServiceInfo(String bankServiceInfo) {
			this.bankServiceInfo = bankServiceInfo;
		}
		public String getCcid() {
			return ccid;
		}
		public void setCcid(String ccid) {
			this.ccid = ccid;
		}
		
		
		/**
		 * @return the solveWay
		 */
		public String getSolveWay() {
			return solveWay;
		}
		/**
		 * @param solveWay the solveWay to set
		 */
		public void setSolveWay(String solveWay) {
			this.solveWay = solveWay;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		
		 
		
		
		
		 
}
