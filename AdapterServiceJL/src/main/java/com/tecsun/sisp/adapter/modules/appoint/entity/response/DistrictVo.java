package com.tecsun.sisp.adapter.modules.appoint.entity.response;

import java.util.List;

/**
 * Created by xumaohao on 2017/10/25.
 */
public class DistrictVo {
    private String districtName;        //区域名称
    private String districtCode;        //区域编码
    private String parentCode;          //父级区域编码
    private List<DistrictVo> data;      //子级区域

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public List<DistrictVo> getData() {
        return data;
    }

    public void setData(List<DistrictVo> data) {
        this.data = data;
    }
}
