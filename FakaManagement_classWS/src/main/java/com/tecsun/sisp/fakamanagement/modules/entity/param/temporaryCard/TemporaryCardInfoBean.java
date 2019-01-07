package com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * Created by chenlicong on 2018/3/21.
 */
public class TemporaryCardInfoBean extends BaseVO {

    private String userid;//用户ID
    private String rkwd;//入库网点ID
    private String tempcardNo;//临时卡编号
    private String cardid;//社保卡号
    private String idcard;//社会保障号
    private String status;//卡状态

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

    public String getTempcardNo() {
        return tempcardNo;
    }

    public void setTempcardNo(String tempcardNo) {
        this.tempcardNo = tempcardNo;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
