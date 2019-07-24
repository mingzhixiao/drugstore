package com.wzw.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.List;
import java.util.ArrayList;


/**
 * @Description 工具类，利用fastjson转换ArrayList为一个新的ArrayList
 * @Date 2019/4/6 13:12
 * @Created by wzw
 */
public class MyArrayListPageConvert {
    public  <T, V> Page<V> myPageConvert(int page, int limit, int count, ArrayList<T> arrayList){
        Page<V> myPage = new Page<>(page, limit, count);
        String s = JSON.toJSONString(arrayList);
        List<V> vArrayList = JSON.parseObject(s, new TypeReference<List<V>>() {});
        myPage.setItems(vArrayList);
        return myPage;
    }
}





