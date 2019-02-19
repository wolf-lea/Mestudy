package com.tecsun.sisp.iface.common.vo.cssp;

/**
 * Created by liu on 2016/8/16.
 */
public class ResultUpVO {
    private long id;
    private long boxid;
    private  Integer CARDSN;

    public Integer getCARDSN() {
        return CARDSN;
    }

    public void setCARDSN(Integer CARDSN) {
        this.CARDSN = CARDSN;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBoxid() {
        return boxid;
    }

    public void setBoxid(long boxid) {
        this.boxid = boxid;
    }
}
