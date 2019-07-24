package com.wzw.vo;


import java.util.List;
/**
 * @Description TODO
 * @Date 2019/5/13 17:49
 * @Created by wzw
 */
public  class TablePage <T> {
    private int total;
    private List<T> rows;

    public TablePage() {
    }

    public TablePage(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
