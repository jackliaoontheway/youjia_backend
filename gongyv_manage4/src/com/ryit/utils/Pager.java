package com.ryit.utils;


public class Pager {

    private int page = 1;   //当前页
    private int rows = 15;  //每页的数量
    private String orderBy="";
    private String start;
    private String end;
    private String filterFeild;
    private String filterValue;
    /**
     * 排序类型，1为asc 2为desc -1为不排序
     */
    private int orderType = -1;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getFilterFeild() {
        return filterFeild;
    }

    public void setFilterFeild(String filterFeild) {
        this.filterFeild = filterFeild;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }
}

