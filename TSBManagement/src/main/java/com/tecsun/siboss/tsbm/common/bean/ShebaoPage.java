package com.tecsun.siboss.tsbm.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mervin on 2015/11/18.
 */
public class ShebaoPage<T> {

    private String CPAGE;//第几页
    private String PAGES;//总共几页
    private String ROWCOUNT;//总记录数
    private List<T> DATA;//结果集

    private String FAULTCODE;//错误编码
    private String MSG;//错误消息

    public ShebaoPage() {
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public String getFAULTCODE() {
        return FAULTCODE;
    }

    public void setFAULTCODE(String FAULTCODE) {
        this.FAULTCODE = FAULTCODE;
    }

    public String getCPAGE() {
        return CPAGE;
    }

    public void setCPAGE(String CPAGE) {
        this.CPAGE = CPAGE;
    }

    public String getPAGES() {
        return PAGES;
    }

    public void setPAGES(String PAGES) {
        this.PAGES = PAGES;
    }

    public String getROWCOUNT() {
        return ROWCOUNT;
    }

    public void setROWCOUNT(String ROWCOUNT) {
        this.ROWCOUNT = ROWCOUNT;
    }

    public List<T> getDATA() {
        return DATA;
    }

    public void setDATA(List<T> DATA) {
        this.DATA = DATA;
    }
}


