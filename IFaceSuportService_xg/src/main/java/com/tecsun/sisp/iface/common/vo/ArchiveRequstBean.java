package com.tecsun.sisp.iface.common.vo;

/**
 * Created by yangliu on 2016/9/13.
 */
public class ArchiveRequstBean extends SecQueryBean{
    private String  AAE017;  //机构编号
    private String  AAC002;//身份证号码
    private String ACC709;//接收日期开始
    private String  ACC709_1;//接受日期截止
    private String ACC70B;//入库状态
    private String  AAF036;//最后一次修改时间开始
    private String AAF036_1;//最后一次修改时间截止
    private String   ACE750;//数据更新标识

    public String getAAE017() {
        return AAE017;
    }

    public void setAAE017(String AAE017) {
        this.AAE017 = AAE017;
    }

    public String getAAC002() {
        return AAC002;
    }

    public void setAAC002(String AAC002) {
        this.AAC002 = AAC002;
    }

    public String getACC709() {
        return ACC709;
    }

    public void setACC709(String ACC709) {
        this.ACC709 = ACC709;
    }

    public String getACC709_1() {
        return ACC709_1;
    }

    public void setACC709_1(String ACC709_1) {
        this.ACC709_1 = ACC709_1;
    }

    public String getACC70B() {
        return ACC70B;
    }

    public void setACC70B(String ACC70B) {
        this.ACC70B = ACC70B;
    }

    public String getAAF036() {
        return AAF036;
    }

    public void setAAF036(String AAF036) {
        this.AAF036 = AAF036;
    }

    public String getAAF036_1() {
        return AAF036_1;
    }

    public void setAAF036_1(String AAF036_1) {
        this.AAF036_1 = AAF036_1;
    }

    public String getACE750() {
        return ACE750;
    }

    public void setACE750(String ACE750) {
        this.ACE750 = ACE750;
    }
}
