package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;

/**
 * Created by linzetian on 2017/6/21.
 * 人员参保信息
 */
public class UserCBInfoVO {

    @Field(empty="无")
    private String	cbxz;	//	险种类型
    @Field(empty="无")
    private String  cbzt;	//	参保状态

    @Field(empty="无")
    private String title;//标题

    private String url;//请求url

    public String getCbxz() {
        return cbxz;
    }

    public void setCbxz(String cbxz) {
        this.cbxz = cbxz;
    }

    public String getCbzt() {
        return cbzt;
    }

    public void setCbzt(String cbzt) {
        this.cbzt = cbzt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
