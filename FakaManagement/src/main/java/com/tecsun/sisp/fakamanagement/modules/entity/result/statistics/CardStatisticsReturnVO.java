package com.tecsun.sisp.fakamanagement.modules.entity.result.statistics;

import java.util.List;

/**
 * Created by chenlicong on 2017/12/26.
 */
public class CardStatisticsReturnVO {

    private Integer index;//序号
    private List list;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
