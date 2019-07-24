package com.wzw.utils;

import java.util.ArrayList;

/**
 * @Description TODO
 * @Date 2019/4/6 23:20
 * @Created by wzw
 */
public class  MyRespon {
    private int code = 0;
    private String msg = "";
    private int  count;
    private ArrayList data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    public MyRespon() {
    }

    public MyRespon(int count, ArrayList data) {
        this.code = 0;
        this.count = count;
        this.data = data;
        this.msg = "";
    }
}
