package com.tecsun.siboss.tsbm.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * 使用说明：
 * 1、在控制层（controller）实例化；
 * 2、model层的第一个参数必须是Page对象（注：结合mybatis的分页插件）
 * @param <T>
 */
public class Page<T> {
    private List<T> lists = new ArrayList<T>();
    private int pageSize;//每页记录数
    private int pageIndex;//当前页码
    private int pageCount;//总页数
    private int rowCount;//总记录数

    public Page() {}

    public Page(int pageSize, int pageIndex) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize<=0?10:pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;

    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}
