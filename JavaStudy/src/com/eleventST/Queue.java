package com.eleventST;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*
* implement a class Veterinarian that will be used as a part of a larger simulation of a veterinarian office.

the class Veterinarian needs to bo efficient with respect to time used and contain the following methods.

puts the specified pet at the end of the line.
removes the pet's name from the queue and returns it. If no pets are in the queue, null should be returned.

For example, the following code snippet should write "Barkley", "Mittens":
* */
public class Queue {
    public static List<String> queue = new ArrayList<>();
    public static void main(String[] args) {
        Queue.accept("Barkley");
        Queue.accept("Mittens");
        System.out.println(Queue.heal());
        System.out.println(Queue.heal());
        ArrayList
    }

    public static void accept(String petName) {
        queue.add(petName);
    }

    public static String heal() {
        String expos = queue.get(queue.size() - 1);
        queue.remove(queue.size() - 1);
        return expos;
    }
}
