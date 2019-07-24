package com.wzw.utils;

/**
 * @Description TODO
 * @Date 2019/5/14 15:12
 * @Created by wzw
 */
public class MySplitJudge {
    public static String [] judge(String ids){
        String []  id_s =new String[ids.length()];
        if (ids.length() > 1){
            id_s = ids.split(",");
        } else {
            id_s[0] =ids;
        }
        return id_s;

    }
}
