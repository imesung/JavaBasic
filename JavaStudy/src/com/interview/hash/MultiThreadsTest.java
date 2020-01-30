package com.interview.hash;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadsTest {

    private static final int MAX_THREADS = 10;

    private static Hashtable<String, Integer> hashTable = new Hashtable<>();
    private static HashMap<String, Integer> hashMap = new HashMap<>();
    private static HashMap<String, Integer> hashMapSync = new HashMap<>();

    //hashMapSync2는 Collections의 SynchronizedMap을 활용하여 HashMap을 담고, put 혹은 get 등을 할 때 synchronized 키워드를 사용한다.
    //Collections의 SynchronizedMap 클래는 Map 인터페이스를 구현하고 있으므로, Map으로 참조가 가능하다.
    private static Map<String, Integer> hashMapSync2 = Collections.synchronizedMap(new HashMap<String, Integer>());
    private static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(MAX_THREADS); //10개의 스레드 풀 사

        for( int j = 0 ; j < MAX_THREADS; j++ ){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    for( int i = 0; i < 1000; i++ ){    //각 스레드 1000번 반

                        String key = String.valueOf(i);

                        hashTable.put(key, i);
                        hashMap.put(key, i);
                        concurrentHashMap.put(key, i);
                        hashMapSync2.put(key, i);

                        //일반 hashMap인데 put하는 순간에 synchronized로 접
                        synchronized (hashMapSync) {
                            hashMapSync.put(key, i);
                        }
                    }
                }
            });
        }

        es.shutdown();
        try {
            //지정 시간동안 대기하며 모든 작업이 중지되었는지 확인한다.
            es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Hashtable size is "+ hashTable.size());
        System.out.println("HashMap size is "+ hashMap.size());
        System.out.println("ConcurrentHashMap size is "+ concurrentHashMap.size());
        System.out.println("HashMap(synchronized) size is "+ hashMapSync.size());
        System.out.println("synchronizedMap size is "+ hashMapSync2.size());

		/*
		for( String s : hm.keySet() ){
			System.out.println("["+s+"] " + hm.get(s));
		}
		*/
    }
}
