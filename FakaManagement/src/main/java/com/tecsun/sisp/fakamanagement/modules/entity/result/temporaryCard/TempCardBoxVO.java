package com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard;


import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;


/**
 * 临时卡盒列表 出参
 */
public class TempCardBoxVO{
    private Integer id;//id
    private String box;//盒号
    private String cardBegin;//临时卡编号起
    private String cardEnd;//临时卡编号止
    private String cardNo;//临时卡编号
    private String status;//下发状态 00-	未下发  01-	已接收    02-	已下发
    private Integer bankId;//下发银行id
    private String bankName;//下发银行名称
    private String bankCode;//下发银行编码
    private Integer cardNum;//临时卡总数

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getCardBegin() {
        return cardBegin;
    }

    public void setCardBegin(String cardBegin) {
        this.cardBegin = cardBegin;
    }

    public String getCardEnd() {
        return cardEnd;
    }

    public void setCardEnd(String cardEnd) {
        this.cardEnd = cardEnd;
    }
}
