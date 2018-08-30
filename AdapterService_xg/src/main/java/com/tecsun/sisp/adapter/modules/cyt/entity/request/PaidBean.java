package com.tecsun.sisp.adapter.modules.cyt.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by xumaohao on 2017/8/3.
 */
public class PaidBean extends SecQueryBean {
    private String sfxmbm;  //收费项目编码
    private String sfxmmc;  //收费项目名称
    private String sfxmlb;   //收费项目类别

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

    public String getSfxmlb() {
        return sfxmlb;
    }

    public void setSfxmlb(String sfxmlb) {
        this.sfxmlb = sfxmlb;
    }
}
