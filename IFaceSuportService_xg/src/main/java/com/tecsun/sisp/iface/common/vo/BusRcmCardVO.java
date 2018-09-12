package com.tecsun.sisp.iface.common.vo;

import java.util.Date;

/**
 * Created by hhl on 2016/8/6.
 */
public class BusRcmCardVO {
    private Long id;
    private String certNum;
    private String name;
    private String soCardNum;
    private String bankAccount;
    private String bankName;
    private int cardSn;//卡片位置
    private int status;
    private Date grantTime;
    private Long grantUserId;
    private String grantNetType;//发卡网点类型 01是社保 02是银行
    private int getGrantNetId;
    private Long boxId;
    private Long cardId;
    private Long addUserNo;
    private Date addDate;
    private long modUserNo;
    private Date modDate;
    private Date retenTime;
    private String reason;
    private String otherReason;
    private String regisStatus;//1， 待发放 0已发
    private long orgid;
    private String makeCardTime;//制卡时间


	public String getMakeCardTime() {
		return makeCardTime;
	}

	public void setMakeCardTime(String makeCardTime) {
		this.makeCardTime = makeCardTime;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoCardNum() {
        return soCardNum;
    }

    public void setSoCardNum(String soCardNum) {
        this.soCardNum = soCardNum;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getCardSn() {
        return cardSn;
    }

    public void setCardSn(int cardSn) {
        this.cardSn = cardSn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getGrantTime() {
        return grantTime;
    }

    public void setGrantTime(Date grantTime) {
        this.grantTime = grantTime;
    }

    public Long getGrantUserId() {
        return grantUserId;
    }

    public void setGrantUserId(Long grantUserId) {
        this.grantUserId = grantUserId;
    }

    public String getGrantNetType() {
        return grantNetType;
    }

    public void setGrantNetType(String grantNetType) {
        this.grantNetType = grantNetType;
    }

    public int getGetGrantNetId() {
        return getGrantNetId;
    }

    public void setGetGrantNetId(int getGrantNetId) {
        this.getGrantNetId = getGrantNetId;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getAddUserNo() {
        return addUserNo;
    }

    public void setAddUserNo(Long addUserNo) {
        this.addUserNo = addUserNo;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public long getModUserNo() {
        return modUserNo;
    }

    public void setModUserNo(long modUserNo) {
        this.modUserNo = modUserNo;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Date getRetenTime() {
        return retenTime;
    }

    public void setRetenTime(Date retenTime) {
        this.retenTime = retenTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOtherReason() {
        return otherReason;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason;
    }

    public String getRegisStatus() {
        return regisStatus;
    }

    public void setRegisStatus(String regisStatus) {
        this.regisStatus = regisStatus;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    private String soName;//网点名称
    private  String phone;
    private String binno;
    private String boxno;
    private  String nowcount;
    private  String orgAddress;
    private String ADDUSER;

    public String getADDUSER() {

        return ADDUSER;
    }

    public void setADDUSER(String ADDUSER) {
        this.ADDUSER = ADDUSER;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getBinno() {
        return binno;
    }

    public void setBinno(String binno) {
        this.binno = binno;
    }

    public String getBoxno() {
        return boxno;
    }

    public void setBoxno(String boxno) {
        this.boxno = boxno;
    }

    public String getNowcount() {
        return nowcount;
    }

    public void setNowcount(String nowcount) {
        this.nowcount = nowcount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSoName() {
        return soName;
    }

    public void setSoName(String soName) {
        this.soName = soName;
    }


}
