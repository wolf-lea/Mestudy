package com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard;

/**
 * 统计 出参
 * yangliu
 *
 */
public class CardCountVO {
    private Integer id;
    private String name;
    private String orgName;
    private String orgCode;
    private Integer makeCardCount;
    private Integer grantCardCount;
    private Integer tempGrantCardCount;
    private Integer nowGrantCardCount;
    private Integer cardCount;

    private String distinctId;
    private String bank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getCardCount() {
        return cardCount;
    }

    public void setCardCount(Integer cardCount) {
        this.cardCount = cardCount;
    }

    public Integer getNowGrantCardCount() {
        return nowGrantCardCount;
    }

    public void setNowGrantCardCount(Integer nowGrantCardCount) {
        this.nowGrantCardCount = nowGrantCardCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getMakeCardCount() {
        return makeCardCount;
    }

    public void setMakeCardCount(Integer makeCardCount) {
        this.makeCardCount = makeCardCount;
    }

    public Integer getGrantCardCount() {
        return grantCardCount;
    }

    public void setGrantCardCount(Integer grantCardCount) {
        this.grantCardCount = grantCardCount;
    }

    public Integer getTempGrantCardCount() {
        return tempGrantCardCount;
    }

    public void setTempGrantCardCount(Integer tempGrantCardCount) {
        this.tempGrantCardCount = tempGrantCardCount;
    }

    public String getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(String distinctId) {
        this.distinctId = distinctId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
