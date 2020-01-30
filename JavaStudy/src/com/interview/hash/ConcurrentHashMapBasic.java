package com.interview.hash;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapBasic {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();

        // 값 입력
        chm.put("key", 0);

        /*
         * ConcurrentHashMap은 값에 null을 허용하지 않는다.
         */
        try{
            chm.put("key1", null); // error!
        } catch( Exception e ){
            e.printStackTrace();
        }

        /*
         * ConcurrentHashMap은 키값에 null을 허용하지 않는다.
         */
        try{
            chm.put(null, 0); // error!
        } catch( Exception e ){
            e.printStackTrace();
        }

        /*
         * putIfAbsent 메소드는 키값이 존재하면 기존의 값을 반환하고
         * 없다면 입력한 값을 저장한 뒤 반환한다.
         * 따라서 아래의 코드는 이미 key라는 값에 0이라는 값이 있으므로
         * key 값은 0을 반환한다.
         */
        chm.putIfAbsent("key", 1);

        /*
         * 아래 코드는 key2의 값이 없기 때문에 -1을 저장하고 반환한다.
         */
        chm.putIfAbsent("key2", -1);

        for( String s : chm.keySet() ){
            System.out.println(chm.get(s)); // print -1, 0
        }
    }
}
