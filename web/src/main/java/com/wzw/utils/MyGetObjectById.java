package com.wzw.utils;

import java.util.ArrayList;

/**
 * @Description TODO
 * @Date 2019/4/8 13:52
 * @Created by wzw
 */
public class MyGetObjectById {
    public static <T> MyRespon myRespon(T t){
        if (t != null){
            ArrayList<T> ts = new ArrayList<>();
            ts.add(t);
            return new MyRespon(1,ts);
        }
        return new MyRespon(0,null);
    }
}
