package com.tecsun.sisp.adapter.modules.cyt.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by xumaohao on 2017/7/21.
 */
public class ChargeInformationBean extends SecQueryBean {

    private long id;        //结算表id
    private String jzlsh;   //就诊流水号
    private String jslsh;   //结算流水号
    private String hzlsh;   //汇总流水号
    private String bxfs;    //报销方式
    private String password;//密码
    private String sbkh;    //社保卡号
    private String jbbm;    //疾病编码
    private String jbmc;    //疾病名称
    private String num;     //数量
    private String bxje;    //报销金额
    private String zfje;    //自费金额
    private String tcye;    //统筹余额
    private String zje;     //总金额
    private String zh;      //账号
    private String jssj;    //结算时间

    private MedicineBean[] medicine;    //药费明细


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJzlsh() {
        return jzlsh;
    }

    public void setJzlsh(String jzlsh) {
        this.jzlsh = jzlsh;
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

    public String getBxfs() {
        return bxfs;
    }

    public void setBxfs(String bxfs) {
        this.bxfs = bxfs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getJbbm() {
        return jbbm;
    }

    public void setJbbm(String jbbm) {
        this.jbbm = jbbm;
    }

    public String getJbmc() {
        return jbmc;
    }

    public void setJbmc(String jbmc) {
        this.jbmc = jbmc;
    }

    public MedicineBean[] getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicineBean[] medicine) {
        this.medicine = medicine;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getZje() {
        return zje;
    }

    public void setZje(String zje) {
        this.zje = zje;
    }

    public String getBxje() {
        return bxje;
    }

    public void setBxje(String bxje) {
        this.bxje = bxje;
    }

    public String getZfje() {
        return zfje;
    }

    public void setZfje(String zfje) {
        this.zfje = zfje;
    }

    public String getTcye() {
        return tcye;
    }

    public void setTcye(String tcye) {
        this.tcye = tcye;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }
}
