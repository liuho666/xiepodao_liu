package com.liu.entity;

public class CarRequest {
    private int pageNow;
    private int pageSize;
    private int start;
    //查询词
    private String searchKey;

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (pageNow-1)*pageSize;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

}
