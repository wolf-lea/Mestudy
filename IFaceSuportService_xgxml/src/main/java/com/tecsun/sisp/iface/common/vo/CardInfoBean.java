package com.tecsun.sisp.iface.common.vo;

/**
 * 社保卡业务请求对象
 */
public class CardInfoBean extends SecQueryBean {

    /**
     * 请求体(包含社保所有业务入参) start
     */
    private String cardNo; //社保卡号
    private String bank;   //服务银行
    private String bankno; //银行卡号
    private String cityno; //城市代码
    private String server; //业务代码（2位，01临时挂失、02正式挂失、03解除挂失、04注销、11领卡启用）

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getCityno() {
        return cityno;
    }

    public void setCityno(String cityno) {
        this.cityno = cityno;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}

