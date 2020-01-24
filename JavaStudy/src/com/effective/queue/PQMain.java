package com.effective.queue;

import java.util.PriorityQueue;

public class PQMain {
    public static PriorityQueue<Student> getPriorityQueueOfStudents() {
        PriorityQueue<Student> priorityQueue = new PriorityQueue<>();

        priorityQueue.offer(new Student("임혜성", 30));
        priorityQueue.offer(new Student("최준우", 30));
        priorityQueue.offer(new Student("손원락", 29));
        priorityQueue.offer(new Student("이상훈", 31));
        priorityQueue.offer(new Student("채유진", 27));

        return priorityQueue;
    }

    public static void main(String [] args) {
        PriorityQueue<Student> priorityQueue = getPriorityQueueOfStudents();

        while(!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }


        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(1);
        pq.offer(3);
        pq.offer(2);
        pq.offer(14);
        while(!pq.isEmpty()) {
            System.out.println(pq.poll());
        }


    }
}
