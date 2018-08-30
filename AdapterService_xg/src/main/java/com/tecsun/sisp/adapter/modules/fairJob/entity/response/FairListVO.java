package com.tecsun.sisp.adapter.modules.fairJob.entity.response;

/**招聘会
 * Created by Administrator on 2017/11/3 0003.
 */
public class FairListVO  {
    private String id;//企业ID
    private String name;//企业名称
    private String fairName;//招聘会名称
    private String hireNumTotal;//企业招聘总人数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFairName() {
        return fairName;
    }

    public void setFairName(String fairName) {
        this.fairName = fairName;
    }

    public String getHireNumTotal() {
        return hireNumTotal;
    }

    public void setHireNumTotal(String hireNumTotal) {
        this.hireNumTotal = hireNumTotal;
    }
}
