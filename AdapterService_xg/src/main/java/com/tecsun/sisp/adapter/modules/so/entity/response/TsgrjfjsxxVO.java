package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsgrjfjsxxVO {

    private final String TABLE_NAME = Constants.TS_V_BP_GRJFJSXX;

    private 	String	grbh	;//	个人编号
    private 	String	dwbh	;//	单位编号
    private 	String	dwmc	;//	单位名称
    private 	String	ksny	;//	开始年月
    private 	String	zzny	;//	终止年月
    private 	String	gz	;//	工资
    private 	String	yljs	;//	基本养老保险缴费基数
    private 	String	ybjs	;//	基本医疗保险缴费基数
    private 	String	syjs	;//	失业保险缴费基数
    private 	String	gsjs	;//	工伤保险缴费基数
    private 	String	yujs	;//	生育保险缴费基数

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getKsny() {
        return ksny==null?"":ksny;
    }

    public void setKsny(String ksny) {
        this.ksny = ksny;
    }

    public String getZzny() {
        return zzny==null?"":zzny;
    }

    public void setZzny(String zzny) { this.zzny = zzny; }

    public String getGz() {
        return gz;
    }

    public void setGz(String gz) {
        this.gz = gz;
    }

    public String getYljs() {
        return yljs;
    }

    public void setYljs(String yljs) {
        this.yljs = yljs;
    }

    public String getYbjs() {
        return ybjs;
    }

    public void setYbjs(String ybjs) {
        this.ybjs = ybjs;
    }

    public String getSyjs() {
        return syjs;
    }

    public void setSyjs(String syjs) {
        this.syjs = syjs;
    }

    public String getGsjs() {
        return gsjs;
    }

    public void setGsjs(String gsjs) {
        this.gsjs = gsjs;
    }

    public String getYujs() {
        return yujs;
    }

    public void setYujs(String yujs) {
        this.yujs = yujs;
    }
}
