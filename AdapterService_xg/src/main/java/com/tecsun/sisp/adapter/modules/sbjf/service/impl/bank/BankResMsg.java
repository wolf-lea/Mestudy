package com.tecsun.sisp.adapter.modules.sbjf.service.impl.bank;

/**
 * Created by linzetian on 2017/6/16.
 * 金融返回报文
 */
public class BankResMsg {
    private String bankResult ;//银行返回状态
    private String id;//金融交易Id
    private String hexStr;//报文
    private String message;//返回信息

    public String getBankResult() {
        return bankResult;
    }

    public void setBankResult(String bankResult) {
        this.bankResult = bankResult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHexStr() {
        return hexStr;
    }

    public void setHexStr(String hexStr) {
        this.hexStr = hexStr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
