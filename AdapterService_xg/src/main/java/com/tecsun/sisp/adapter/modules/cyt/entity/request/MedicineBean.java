package com.tecsun.sisp.adapter.modules.cyt.entity.request;

import java.text.DecimalFormat;

/**
 * Created by xumaohao on 2017/7/26.
 */
public class MedicineBean {

    private long Id;        //药物表id
    private String jzlsh;   //就诊流水号
    private String fyxh;    //费用序号
    private String sfxmbm;  //收费项目编码
    private String sfxmmc;  //收费项目名称
    private double dj;      //单价(精确到小数点后四位)
    private double sl;      //数量(精确到小数点后四位)
    private double je;      //金额(精确到小数点后四位)

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getJzlsh() {
        return jzlsh;
    }

    public void setJzlsh(String jzlsh) {
        this.jzlsh = jzlsh;
    }

    public String getFyxh() {
        return fyxh;
    }

    public void setFyxh(String fyxh) {
        this.fyxh = fyxh;
    }

    public String getSfxmbm() {
        return sfxmbm;
    }

    public void setSfxmbm(String sfxmbm) {
        this.sfxmbm = sfxmbm;
    }

    public String getSfxmmc() {
        return sfxmmc;
    }

    public void setSfxmmc(String sfxmmc) {
        this.sfxmmc = sfxmmc;
    }

    public double getDj() {
        String num = new DecimalFormat("######0.0000").format(dj);
        dj = Double.valueOf(num);
        return dj;
    }

    public void setDj(double dj) {
        String num = new DecimalFormat("######0.0000").format(dj);
        dj = Double.valueOf(num);
        this.dj = dj;
    }

    public double getSl() {
        String num = new DecimalFormat("######0.0000").format(sl);
        sl = Double.valueOf(num);
        return sl;
    }

    public void setSl(double sl) {
        String num = new DecimalFormat("######0.0000").format(sl);
        sl = Double.valueOf(num);
        this.sl = sl;
    }

    public double getJe() {
        String num = new DecimalFormat("######0.0000").format(je);
        je = Double.valueOf(num);
        return je;
    }

    public void setJe(double je) {
        String num = new DecimalFormat("######0.0000").format(je);
        je = Double.valueOf(num);
        this.je = je;
    }
}
