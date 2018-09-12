package com.tecsun.sisp.fakamanagement.modules.entity.result.store;

import java.util.List;

/**
 * Created by chenlicong on 2018/1/24.
 */
public class CardStoreTotalVO {

    private int cardTotal;//卡总量合计
    private int fakaTotal;//发卡量合计
    private int notfakaTotal;//为发卡量合计

    private List<CardStoreCountVO> list;

    public int getCardTotal() {
        return cardTotal;
    }

    public void setCardTotal(int cardTotal) {
        this.cardTotal = cardTotal;
    }

    public int getFakaTotal() {
        return fakaTotal;
    }

    public void setFakaTotal(int fakaTotal) {
        this.fakaTotal = fakaTotal;
    }

    public int getNotfakaTotal() {
        return notfakaTotal;
    }

    public void setNotfakaTotal(int notfakaTotal) {
        this.notfakaTotal = notfakaTotal;
    }

    public List<CardStoreCountVO> getList() {
        return list;
    }

    public void setList(List<CardStoreCountVO> list) {
        this.list = list;
    }
}
