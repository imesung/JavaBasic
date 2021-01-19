package com.socar;

import java.util.*;

public class SocarTwo {

    public static void main(String[] args) {
        SocarTwo socarTwo = new SocarTwo();
        //int [][] links = {{3, 5}, {3, 2}, {6, 3}, {6, 1}, {4, 6}};
        int [][] links = {{4,5}, {4,3}, {4,2},{1,6}, {7,4}, {7,1}};
        System.out.println(socarTwo.solution(links));

    }

    public int solution(int [][] links) {
        int answer = 0;
        Map<Integer, List<Integer>> linksMap = new HashMap<>();

        Set<Integer> keyS = new HashSet<>();
        for(int i = 0; i < links.length; i++) {
            keyS.add(links[i][0]);
        }

        for(int i = 0; i < links.length; i++) {
            int key = links[i][0];
            if(!linksMap.containsKey(key)) {
                List<Integer> linksVal = new ArrayList<>();
                for(int j = i; j < links.length; j++) {
                    if(key == links[j][0]) {
                        //팀원이 팀장일 경우
                        if(keyS.contains(links[j][1])) {
                            linksVal.add(0, links[j][1]);
                        //팀원이 그냥 팀원일 경우
                        } else {
                            linksVal.add(links[j][1]);
                        }
                    }
                }
                linksMap.put(key, linksVal);
            }
        }



        for(Integer i : linksMap.keySet()){ //저장된 key값 확인
            System.out.println("[Key]:" + i + " [Value]:" + linksMap.get(i));
        }

        for(Integer i : linksMap.keySet()){
            //팀원이 팀장일 경우
            int listKey = 0;
            int curKey = i;
            int keyResult = 1;
            List<Integer> preKeyList = new ArrayList<>();
            while(true) {
                System.out.println(linksMap.get(i).get(listKey));
                while(keyS.contains(linksMap.get(i).get(listKey))) {
                    curKey = linksMap.get(i).get(listKey);
                    System.out.println(curKey);
                    preKeyList.add(linksMap.get(i).get(listKey));
                }

                //팀원이 팀장이 아닌 경우
                keyResult *= linksMap.get(i).subList(listKey, linksMap.get(i).size()).size();
                if(!preKeyList.isEmpty()) {
                    curKey = preKeyList.remove(0);
                } else {
                    break;
                }
            }
            answer = answer + keyResult;
        }
        return answer;
    }
}
