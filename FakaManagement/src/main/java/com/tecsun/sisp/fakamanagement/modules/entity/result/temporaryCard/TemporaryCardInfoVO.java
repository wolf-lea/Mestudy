package com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard;

/**
 * Created by chenlicong on 2018/3/21.
 */
public class TemporaryCardInfoVO {

    private int id;
    private String tempcardNo;//临时卡编号
    private String cardid;//社保卡号
    private String name;//姓名
    private String idcard;//社会保障号
    private String grantTime;//发卡时间
    private String recoveryTime;//回收时间
    private String status;//卡状态
    private String atr;//卡复位码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTempcardNo() {
        return tempcardNo;
    }

    public void setTempcardNo(String tempcardNo) {
        this.tempcardNo = tempcardNo;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
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

    public String getGrantTime() {
        return grantTime;
    }

    public void setGrantTime(String grantTime) {
        this.grantTime = grantTime;
    }

    public String getRecoveryTime() {
        return recoveryTime;
    }

    public void setRecoveryTime(String recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAtr() {
        return atr;
    }

    public void setAtr(String atr) {
        this.atr = atr;
    }
}
