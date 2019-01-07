package com.tecsun.sisp.adapter.modules.card.entity.response;

/**
 * 箱、盒、卡出参
 * Created by liu on 2016/8/10.
 */
public class ResultXhcVO {
    private Integer xcount;//箱子已装盒数
    private String boxno;//盒号
    private Integer hcount;//盒子已装卡数
    private Integer boxsn;//盒号顺序号
    private Integer nowCount;//盒中卡实时数量
    private long xid;
    private  long binId;
    private  long boxId;
    private  String binNo;
    private  Integer cardsn;

    public Integer getCardsn() {
        return cardsn;
    }

    public void setCardsn(Integer cardsn) {
        this.cardsn = cardsn;
    }

    public String getBinNo() {
        return binNo;
    }

    public void setBinNo(String binNo) {
        this.binNo = binNo;
    }

    public long getXid() {
        return xid;
    }

    public void setXid(long xid) {
        this.xid = xid;
    }

    public long getBinId() {
        return binId;
    }

    public void setBinId(long binId) {
        this.binId = binId;
    }

    public long getBoxId() {
        return boxId;
    }

    public void setBoxId(long boxId) {
        this.boxId = boxId;
    }

    public Integer getXcount() {
        return xcount;
    }

    public void setXcount(Integer xcount) {
        this.xcount = xcount;
    }

    public String getBoxno() {
        return boxno;
    }

    public void setBoxno(String boxno) {
        this.boxno = boxno;
    }

    public Integer getHcount() {
        return hcount;
    }

    public void setHcount(Integer hcount) {
        this.hcount = hcount;
    }

    public Integer getBoxsn() {
        return boxsn;
    }

    public void setBoxsn(Integer boxsn) {
        this.boxsn = boxsn;
    }

    public Integer getNowCount() {
        return nowCount;
    }

    public void setNowCount(Integer nowCount) {
        this.nowCount = nowCount;
    }
}
