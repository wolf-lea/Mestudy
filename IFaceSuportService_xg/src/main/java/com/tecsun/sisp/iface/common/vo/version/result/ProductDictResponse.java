package com.tecsun.sisp.iface.common.vo.version.result;

/**
 * ClassName:ProductDictResponse
 * Description:获取产品字典所有大类
 * Author:zhengmingmin
 * CreateTime: 16-1-6
 */
public class ProductDictResponse {
    private Long id;//主键ID
    private Long code;//编码
    private String name;//产品大类名称
    private int unit;//单位
    private  int landType;//土地类型
    private Long deviceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getLandType() {
        return landType;
    }

    public void setLandType(int landType) {
        this.landType = landType;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
}
