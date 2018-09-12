package com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;
import com.tecsun.sisp.fakamanagement.modules.entity.TSBVO;

/**
 * 临时卡盒 入参
 */
public class TempCardBoxBean extends BaseVO {
    private String box;//盒号
    private String cardBegin;//临时卡编号起
    private String cardEnd;//临时卡编号止
    private Integer cardnoSum;//总数
    private String status;//下发状态 00-	未下发  01-	已接收    02-	已下发
    private Integer bankId;//下发银行ID
    private String bankName;//下发银行名称
    private String bankCode;//下发银行编码
    private Integer userId;//用户id
    private Integer id;//id
    private String ids;//id
    private Integer rkwd;//	"入库网点(唯一)"

    public String getBox() {
        return box;
    }

    public String getCardBegin() {
        return cardBegin;
    }

    public String getCardEnd() {
        return cardEnd;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public void setCardBegin(String cardBegin) {
        this.cardBegin = cardBegin;
    }

    public void setCardEnd(String cardEnd) {
        this.cardEnd = cardEnd;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getRkwd() {
        return rkwd;
    }

    public void setRkwd(Integer rkwd) {
        this.rkwd = rkwd;
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

    public Integer getCardnoSum() {
        return cardnoSum;
    }

    public void setCardnoSum(Integer cardnoSum) {
        this.cardnoSum = cardnoSum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
