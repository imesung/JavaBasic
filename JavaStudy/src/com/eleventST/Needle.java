package com.eleventST;

import java.io.*;
import java.util.*;

public class Needle {
    public static int count(String needle, InputStream hayStack) throws IOException {
        int count = 0;
        // 찾는 needle이 없다면 0
        if (hayStack.available() == 0) {
            return 0;
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(hayStack, "UTF-8"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(needle)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        String inMessage = "Hello, there!\nHow are you today?\nYes, you over there.";

        try (InputStream inputStream = new ByteArrayInputStream(inMessage.getBytes())) {
            System.out.println(Needle.count("there", inputStream));
        }
    }
}
