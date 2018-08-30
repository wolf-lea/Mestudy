package com.tecsun.sisp.adapter.modules.card.entity.response;

import java.util.Date;

/**滞留卡、零散卡
 * Created by danmeng on 2017/5/12.
 */
public class RetentionCardVO {
    private Long id;
    private String sbkh;//社保卡号
    private String bankAccount;//银行卡号
    private String bankName;//银行名称
    private int cardSn;//卡片位置
    private int status;//卡片状态	0在盒中1分散中2已发放
    private Date grantTime;//发卡时间
    private Long grantUserId;//发卡操作人
    private String grantNetType;//发卡网点类型 01是社保 02是银行
    private int getGrantNetId;//发卡网点ID
    private Long boxId;//所属盒的ID
    private Long cardId;//物理卡表中ID	若是我系统中的卡为相应ID。反之为0
    private Long addUserNo;//添加操作员
    private Date addDate;//添加日期
    private long modUserNo;//修改操作员
    private Date modDate;//修改日期
    private Date retenTime;//滞留时间
    private String reason;//滞留原因
    private String otherReason;//其他滞留原因
    private String regisStatus;//滞留状态1， 待发放 0已发
    private long orgid;
    private String makeCardTime;//制卡时间
    private String orgName;//网点名称
    private  String phone;
    private String boxNo;//盒号
    private  String binNo;//箱号
    private  int nowcount;//当前卡片数量	卡片的实时数量
    private  String orgAddress;//网点地址
    private String adduser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
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

    public String getMakeCardTime() {
        return makeCardTime;
    }

    public void setMakeCardTime(String makeCardTime) {
        this.makeCardTime = makeCardTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getBinNo() {
        return binNo;
    }

    public void setBinNo(String binNo) {
        this.binNo = binNo;
    }

    public int getNowcount() {
        return nowcount;
    }

    public void setNowcount(int nowcount) {
        this.nowcount = nowcount;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser;
    }
}
