package com.interview.hash;

import java.util.Hashtable;

public class HashTableBasic {
    public static void main(String[] args) {
        Hashtable<String, Integer> hashtable = new Hashtable<>();

        Object obj = hashtable.put("key", 1);
        Object obj2 = hashtable.put(null, 2);

        //System.out.println("obj : " + obj);
        System.out.println("objs2 : " + obj2);

        /*
         * Hashtable은 값에 null을 허용하지 않는다.
         */
        try{
            hashtable.put("key2", null); // error!
        } catch( Exception e ){
            e.printStackTrace();
        }

        /*
         * Hashtable은 키값에 null을 허용하지 않는다.
         */
        try{
            hashtable.put(null, 0); // error!
        } catch( Exception e ){
            e.printStackTrace();
        }

        // 해당 키 값을 가져온다.
        hashtable.get("key"); // 0
    }
}
