package com.tecsun.sisp.iface.common.vo.cssp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *前端状态出参
 * Created by liu on 2016/8/17.
 */
public class ResultCardAndZgVO<T> {
    private  String kwz;
    private  String kzt;
    private  String kzg;

    private List<T> data = new ArrayList<T>();

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getKwz() {
        return kwz;
    }

    public void setKwz(String kwz) {
        this.kwz = kwz;
    }

    public String getKzt() {
        return kzt;
    }

    public void setKzt(String kzt) {
        this.kzt = kzt;
    }

    public String getKzg() {
        return kzg;
    }

    public void setKzg(String kzg) {
        this.kzg = kzg;
    }
}
