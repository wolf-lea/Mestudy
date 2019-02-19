package com.tecsun.sisp.net.modules.entity.response;

import java.util.List;

/**
 * Created by Administrator on 2018/8/9 0009.
 */
public class ItemMatters {
    private long  id;
    private String  sxmc;
    private String  sxbm;
    private String  sxlx;
    private String  blbm;
    private String  blsj;
    private String  sfsf;
    private String  bltj;
    private String  sxsm;
    private String  bslc;
    private List<MatterFile> matterFileList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSxmc() {
        return sxmc;
    }

    public void setSxmc(String sxmc) {
        this.sxmc = sxmc;
    }

    public String getSxbm() {
        return sxbm;
    }

    public void setSxbm(String sxbm) {
        this.sxbm = sxbm;
    }

    public String getSxlx() {
        return sxlx;
    }

    public void setSxlx(String sxlx) {
        this.sxlx = sxlx;
    }

    public String getBlbm() {
        return blbm;
    }

    public void setBlbm(String blbm) {
        this.blbm = blbm;
    }

    public String getBlsj() {
        return blsj;
    }

    public void setBlsj(String blsj) {
        this.blsj = blsj;
    }

    public String getSfsf() {
        return sfsf;
    }

    public void setSfsf(String sfsf) {
        this.sfsf = sfsf;
    }

    public String getBltj() {
        return bltj;
    }

    public void setBltj(String bltj) {
        this.bltj = bltj;
    }

    public String getSxsm() {
        return sxsm;
    }

    public void setSxsm(String sxsm) {
        this.sxsm = sxsm;
    }

    public String getBslc() {
        return bslc;
    }

    public void setBslc(String bslc) {
        this.bslc = bslc;
    }

    public List<MatterFile> getMatterFileList() {
        return matterFileList;
    }

    public void setMatterFileList(List<MatterFile> matterFileList) {
        this.matterFileList = matterFileList;
    }
}
