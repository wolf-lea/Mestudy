package com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * @Description:
 * @author: liang
 * @date 2018/3/21 11:08
 **/
public class BankNetVO extends BaseVO {

    private Integer userid;
    private Integer tempCardBoxId;
    private String boxNO;
    private String cardNOPerBox;
    private String cardNOStart;
    private String cardNOEnd;
    private Integer cardNOSum;
    private String tempCardNO;
    private String bank;
    private Integer rkwd;//入库网点
    private String rkwdName;
    private String status;
    private String statusValue;

    private Integer cardInstoreNum;//卡入库数量
    private Integer cardGrantNum;//卡发放数量
    private Integer cardSurplusNum;//卡剩余数量

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTempCardBoxId() {
        return tempCardBoxId;
    }

    public void setTempCardBoxId(Integer tempCardBoxId) {
        this.tempCardBoxId = tempCardBoxId;
    }

    public String getBoxNO() {
        return boxNO;
    }

    public void setBoxNO(String boxNO) {
        this.boxNO = boxNO;
    }

    public String getCardNOPerBox() {
        return cardNOPerBox;
    }

    public void setCardNOPerBox(String cardNOPerBox) {
        this.cardNOPerBox = cardNOPerBox;
    }

    public String getCardNOStart() {
        return cardNOStart;
    }

    public void setCardNOStart(String cardNOStart) {
        this.cardNOStart = cardNOStart;
    }

    public String getCardNOEnd() {
        return cardNOEnd;
    }

    public void setCardNOEnd(String cardNOEnd) {
        this.cardNOEnd = cardNOEnd;
    }

    public Integer getCardNOSum() {
        return cardNOSum;
    }

    public void setCardNOSum(Integer cardNOSum) {
        this.cardNOSum = cardNOSum;
    }

    public String getTempCardNO() {
        return tempCardNO;
    }

    public void setTempCardNO(String tempCardNO) {
        this.tempCardNO = tempCardNO;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getRkwd() {
        return rkwd;
    }

    public void setRkwd(Integer rkwd) {
        this.rkwd = rkwd;
    }

    public String getRkwdName() {
        return rkwdName;
    }

    public void setRkwdName(String rkwdName) {
        this.rkwdName = rkwdName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public Integer getCardInstoreNum() {
        return cardInstoreNum;
    }

    public void setCardInstoreNum(Integer cardInstoreNum) {
        this.cardInstoreNum = cardInstoreNum;
    }

    public Integer getCardGrantNum() {
        return cardGrantNum;
    }

    public void setCardGrantNum(Integer cardGrantNum) {
        this.cardGrantNum = cardGrantNum;
    }

    public Integer getCardSurplusNum() {
        return cardSurplusNum;
    }

    public void setCardSurplusNum(Integer cardSurplusNum) {
        this.cardSurplusNum = cardSurplusNum;
    }
}
