package com.wzw.utils;

import java.util.UUID;

/**
 * @Description TODO
 * @Date 2019/5/8 11:02
 * @Created by wzw
 */
public class MyUUId {
    public static String getMyUUid(){
/*        Integer id = null;
        UUID uuid = UUID.randomUUID();
        System.out.println("uuid = "+uuid);
        String idS = uuid.toString();
        id = idS.hashCode();
        id = id < 0 ? -id :id;
        System.out.println(id);
        return id;*/
        String id = null;
        UUID uuid = UUID.randomUUID();
        id = uuid.toString();
        return id;
    }
}
