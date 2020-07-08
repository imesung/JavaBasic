package com.pattern.ch19_flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FlyWeightFactory {
    private Map<String, FlyWeight> pool;

    public FlyWeightFactory() {
        this.pool = new HashMap<>();
    }

    public FlyWeight getFlyWeight(String key) {
        FlyWeight flyWeight = pool.get(key);
        if(flyWeight != null && pool.containsKey(key)) {
            System.out.println("재 사용" +  key.getClass().getName());
        } else {
            flyWeight = new FlyWeight(key);
            pool.put(key, flyWeight);
            System.out.println("새로 생성" + key.getClass().getName());
        }
        return flyWeight;
    }
}
