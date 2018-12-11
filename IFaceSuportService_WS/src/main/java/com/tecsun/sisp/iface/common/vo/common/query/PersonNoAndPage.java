package com.tecsun.sisp.iface.common.vo.common.query;

/**
 * Created by DG on 2015/10/16.
 */
public class PersonNoAndPage {

    private String personNo;
    private String personId;
    private int rowStart;
    private int rowEnd;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    public int getRowStart() {
        return rowStart;
    }

    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public void setRowEnd(int rowEnd) {
        this.rowEnd = rowEnd;
    }

}
