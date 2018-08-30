package com.tecsun.sisp.adapter.modules.cyt.entity.response;

/**
 * Created by xumaohao on 2017/8/3.
 */
public class CancelBean {
    private String jsqxsj;  //结算取消时间
    private String xm;      //姓名
    private String jslsh;   //结算流水号
    private String czyxm;   //操作员姓名

    public String getJsqxsj() {
        return jsqxsj;
    }

    public void setJsqxsj(String jsqxsj) {
        this.jsqxsj = jsqxsj;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getJslsh() {
        return jslsh;
    }

    public void setJslsh(String jslsh) {
        this.jslsh = jslsh;
    }

    public String getCzyxm() {
        return czyxm;
    }

    public void setCzyxm(String czyxm) {
        this.czyxm = czyxm;
    }
}
