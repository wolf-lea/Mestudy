package com.tecsun.sisp.iface.common.vo.employment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pear on 2015/8/4.
 */
public class RowBean {
    private List<ColumnBean> row = new ArrayList<ColumnBean>();

    public List<ColumnBean> getRow() {
        return row;
    }

    public void setRow(List<ColumnBean> row) {
        this.row = row;
    }
}
