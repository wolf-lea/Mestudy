package com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard;

/**
 * Created by chenlicong on 2018/3/22.
 */
public class FakaParamBean {

    private String userid;//用户ID
    private String rkwd;//入库网点

    private String city;//所属城市
    private String orgCode;//经办机构
    private String tempcardNo;//临时卡编号
    private String atr;//卡复位码
    private String idcard;//社会保障号码
    private String name;//姓名
    private String agentIdCard;//代办人身份证号
    private String agentName;//代办人姓名

    private String cancelType;//注销类别

    private String personStatus;//人员状态
    private String cardStatus;//卡状态
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getTempcardNo() {
        return tempcardNo;
    }

    public void setTempcardNo(String tempcardNo) {
        this.tempcardNo = tempcardNo;
    }

    public String getAtr() {
        return atr;
    }

    public void setAtr(String atr) {
        this.atr = atr;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCancelType() {
        return cancelType;
    }

    public void setCancelType(String cancelType) {
        this.cancelType = cancelType;
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

    public String getWastePosition() {
        return wastePosition;
    }

    public void setWastePosition(String wastePosition) {
        this.wastePosition = wastePosition;
    }
}
