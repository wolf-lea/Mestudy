package com.tecsun.sisp.net.modules.entity.response;

/**
 * Created by Administrator on 2018/8/7 0007.
 */
public class TcqVo {
    private String tcqname; //统筹区名称
    private String tcqcode; //统筹区编码
    private String unitname;    //单位名称
    private String unitcode;    // 单位编码

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getTcqcode() {
        return tcqcode;
    }

    public void setTcqcode(String tcqcode) {
        this.tcqcode = tcqcode;
    }

    public String getTcqname() {
        return tcqname;
    }

    public void setTcqname(String tcqname) {
        this.tcqname = tcqname;
    }
}
