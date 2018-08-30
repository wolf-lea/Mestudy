package com.tecsun.sisp.adapter.modules.cyt.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by xumaohao on 2017/8/3.
 */
public class DiseaseBean extends SecQueryBean {
    private String jbcx;   //基本查询入参
    private String jbbm;  //疾病编码
    private String jbmc;  //疾病名称
    private String zjm;   //助记码

    public String getJbcx() {
        return jbcx;
    }

    public void setJbcx(String jbcx) {
        this.jbcx = jbcx;
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

    public String getZjm() {
        return zjm;
    }

    public void setZjm(String zjm) {
        this.zjm = zjm;
    }
}
