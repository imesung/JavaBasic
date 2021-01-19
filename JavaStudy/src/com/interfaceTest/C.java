package com.interfaceTest;

import java.util.HashSet;
import java.util.Set;

public class C implements Inter{

    int aa = AA.mm();

    private static class AA {
        private static int mm() {
            HashSet set = new HashSet();
            set.add("s");
            return 0;
        }
    }
}
