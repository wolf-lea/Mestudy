package com.tecsun.sisp.iface.common.vo.employment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pear on 2015/8/4.
 */
public class ResultBean {
    private List<RowBean> result = new ArrayList<RowBean>();

    public List<RowBean> getResult() {
        return result;
    }

    public void setResult(List<RowBean> result) {
        this.result = result;
    }
}
