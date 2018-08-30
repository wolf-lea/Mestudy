package com.tecsun.sisp.adapter.modules.cyt.entity.response;

/**
 * Created by xumaohao on 2017/8/3.
 */
public class DiseaseDirectoryBean {
    private String jbbm;  //疾病编码
    private String jbmc;  //疾病名称
    private String zjm;   //助记码
    private String xbxz;   //性别限制
    private String lxxz;   //疗效限制

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

    public String getZjm() {
        return zjm;
    }

    public void setZjm(String zjm) {
        this.zjm = zjm;
    }

    public String getXbxz() {
        return xbxz;
    }

    public void setXbxz(String xbxz) {
        this.xbxz = xbxz;
    }

    public String getLxxz() {
        return lxxz;
    }

    public void setLxxz(String lxxz) {
        this.lxxz = lxxz;
    }
}
