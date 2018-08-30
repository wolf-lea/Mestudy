package com.tecsun.sisp.adapter.modules.cyt.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by xumaohao on 2017/7/31.
 */
public class AccountQueryBean extends SecQueryBean {
    private String startDate;  //起始时间
    private String endDate;    //结束时间
    private String zh;       //账号
    private String jslsh;    //就诊流水号
    private String hzlsh;    //汇总流水号
    private String jgbm;     //机构编码
    private int flag = 3;        //0：已结算查询 1：未汇总查询

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getJslsh() {
        return jslsh;
    }

    public void setJslsh(String jslsh) {
        this.jslsh = jslsh;
    }

    public String getHzlsh() {
        return hzlsh;
    }

    public void setHzlsh(String hzlsh) {
        this.hzlsh = hzlsh;
    }

    public String getJgbm() {
        return jgbm;
    }

    public void setJgbm(String jgbm) {
        this.jgbm = jgbm;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
