package com.interview.hash;

import java.util.HashMap;
import java.util.Map.Entry;

public class HashMapBasic {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        // 값 입력
        hashMap.put("key", 0);

        // HashMap은 값에 null을 허용한다.
        hashMap.put("key2", null);
        System.out.println("key2 : " + hashMap.get("key2"));

        /*
         * HashMap은 키값에 null을 허용한다.
         */
        hashMap.put(null, 123);
        System.out.println("null key : " + hashMap.get(null));


        // 값 출력
        hashMap.get("key");

        // 반복 처리 with keySet
        for( String s : hashMap.keySet() ){
            System.out.println(hashMap.get(s));
        }

        // 반복 처리 with entrySet
        for( Entry<String, Integer> s : hashMap.entrySet() ){
            System.out.println(s.getKey()+" "+s.getValue());
        }
    }
}
