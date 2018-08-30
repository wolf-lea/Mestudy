package com.tecsun.sisp.adapter.modules.treatment.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**1:1对比
 * Created by danmeng on 2017/3/17.
 */
public class CompareBean extends SecQueryBean {
    private String colData;
    private String comparisonData;

    public String getColData() {
        return colData;
    }

    public void setColData(String colData) {
        this.colData = colData;
    }

    public String getComparisonData() {
        return comparisonData;
    }

    public void setComparisonData(String comparisonData) {
        this.comparisonData = comparisonData;
    }
}
