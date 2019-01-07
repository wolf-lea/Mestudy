package com.tecsun.sisp.fakamanagement.modules.entity.result.receive;

import java.util.List;

/**
 * Created by chenlicong on 2017/12/25.
 */
public class CardReceiveReturnVO {

    private String reo;//发卡记录描述
    private List cardReceiveIdList;//发卡记录ID list
    private String status;//发卡状态

    public String getReo() {
        return reo;
    }

    public void setReo(String reo) {
        this.reo = reo;
    }

    public List getCardReceiveIdList() {
        return cardReceiveIdList;
    }

    public void setCardReceiveIdList(List cardReceiveIdList) {
        this.cardReceiveIdList = cardReceiveIdList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
