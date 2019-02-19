package com.tecsun.sisp.fun.modules.controller.common.param;

/**
 * Created by zhangqingjie on 15-5-19.
 */
public class ManagerParam {

    private Integer manager_id;//负责人编码
    private String name;//负责人名称
    private String idcard;//身份证

    public ManagerParam() {
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
