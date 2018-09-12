package com.tecsun.sisp.iface.common.vo.common.param;

/**
 * Created by DG on 2015/10/15.
 */
public class IdCardAndPageBean {

    /**
     * 身份证号码
     */
    private String aac002;

    private int pageNo;

    private int pageSize;

    public String getAac002() {
        return aac002;
    }

    public void setAac002(String aac002) {
        this.aac002 = aac002;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
