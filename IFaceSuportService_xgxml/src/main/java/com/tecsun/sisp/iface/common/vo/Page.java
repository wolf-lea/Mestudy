package com.tecsun.sisp.iface.common.vo;

import java.util.ArrayList;
import java.util.List;

public class Page<T>{
    private int pageNo; // 当前页码
    private int pageSize; // 页面大小，设置为“-1”表示不进行分页（分页无效）
    private long count;// 总记录数，设置为“-1”表示不查询总数
    //private int first;// 首页索引
   // private int last;// 尾页索引
    private List<T> data = new ArrayList<T>();
//    private String orderBy = ""; // 标准查询有效， 实例： updatedate desc, name asc

    public Page() {

    }

    public Page(int pageNo, int pageSize) {
        this(pageNo, pageSize, 0);
    }

    public Page(int pageNo, int pageSize, long count) {
        this(pageNo, pageSize, count, new ArrayList<T>());
    }

    public Page(int pageNo, int pageSize, long count, List<T> data) {
        this.count = count;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.data = data;
    }

    public void setCount(long count) {
        this.count = count;
        if (pageSize >= count) {
            pageNo = 1;
        }
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;
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

    public long getCount() {
        return count;
    }

//    public int getFirst() {
//        return first;
//    }
//
//    public void setFirst(int first) {
//        this.first = first;
//    }
//
//    public int getLast() {
//        return last;
//    }
//
//    public void setLast(int last) {
//        this.last = last;
//    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

//    public String getOrderBy() {
//        return orderBy;
//    }
//
//    public void setOrderBy(String orderBy) {
//        this.orderBy = orderBy;
//    }

//    public int getFirstResult() {
//        int firstResult = (getPageNo() - 1) * getPageSize();
//        if (firstResult >= getCount()) {
//            firstResult = 0;
//        }
//        return firstResult;
//    }

//    public int getMaxResults() {
//        return getPageSize();
//    }
}
