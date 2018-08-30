package com.tecsun.sisp.adapter.modules.cyt.entity.response;

import com.tecsun.sisp.adapter.common.util.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xumaohao on 2017/8/3.
 */
public class PaidDirectoryBean {
    private String sfxmbm;  //收费项目编码
    private String sfxmmc;  //收费项目名称
    private String sfxmlb;   //收费项目类别
    private String gg;   //规格
    private String jx;   //剂型
    private String xj;   //限价
    private String gxrq;   //更新日期
    private String yxbz;   //有效标志

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

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getJx() {
        return jx;
    }

    public void setJx(String jx) {
        this.jx = jx;
    }

    public String getXj() {
        return xj;
    }

    public void setXj(String xj) {
        this.xj = xj;
    }

    public String getGxrq() {
        return gxrq;
    }

    public void setGxrq(String gxrq) {
        this.gxrq = gxrq;
    }

    public String getYxbz() {
        String _hzzt = Constants.CYT_YXBZ_STATUS.get(yxbz);
        return StringUtils.isNotEmpty(_hzzt) ? _hzzt :yxbz ;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }
}
