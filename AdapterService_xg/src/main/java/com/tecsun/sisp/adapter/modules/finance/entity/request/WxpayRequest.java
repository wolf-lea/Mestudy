package com.tecsun.sisp.adapter.modules.finance.entity.request;

/**
 * Created by chenlicong on 2017/9/18.
 */
public class WxpayRequest {

    private String orderNum;//商户订单号
    private String money;//金额
    private String subject;//支付说明

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
