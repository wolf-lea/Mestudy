package com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard;

/**
 * Created by chenlicong on 2018/3/22.
 */
public class TempCardPersonVO {

    private String userid;//用户ID
    private String rkwd;//入库网点

    private int id;//主键
    private int tempcardDetailId;//临时卡入库明细表主键
    private String tempcardNo;//临时卡编号
    private String name;//姓名
    private String idcard;//社会保障号码
    private String cardid;//社会保障卡卡号
    private String agentIdCard;//代办人身份证
    private String agentName;//代办人姓名
    private String fkrq;//发卡日期
    private String kyxq;//卡有效期
    private String az01LID;//用卡（卡信息表）
    private String az01ID;//预制卡、临时卡
    private String az02ID;//业务经办
    private String personStatus;//人员状态

    private String cardStatus;//卡状态
    private String atr;//卡复位码
    private String wastePosition;//注销原因

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRkwd() {
        return rkwd;
    }

    public void setRkwd(String rkwd) {
        this.rkwd = rkwd;
    }

    public String getWastePosition() {
        return wastePosition;
    }

    public void setWastePosition(String wastePosition) {
        this.wastePosition = wastePosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTempcardDetailId() {
        return tempcardDetailId;
    }

    public void setTempcardDetailId(int tempcardDetailId) {
        this.tempcardDetailId = tempcardDetailId;
    }

    public String getTempcardNo() {
        return tempcardNo;
    }

    public void setTempcardNo(String tempcardNo) {
        this.tempcardNo = tempcardNo;
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

    public String getAgentIdCard() {
        return agentIdCard;
    }

    public void setAgentIdCard(String agentIdCard) {
        this.agentIdCard = agentIdCard;
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

    public String getAz01LID() {
        return az01LID;
    }

    public void setAz01LID(String az01LID) {
        this.az01LID = az01LID;
    }

    public String getAz01ID() {
        return az01ID;
    }

    public void setAz01ID(String az01ID) {
        this.az01ID = az01ID;
    }

    public String getAz02ID() {
        return az02ID;
    }

    public void setAz02ID(String az02ID) {
        this.az02ID = az02ID;
    }

    public String getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(String personStatus) {
        this.personStatus = personStatus;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getAtr() {
        return atr;
    }

    public void setAtr(String atr) {
        this.atr = atr;
    }
}
