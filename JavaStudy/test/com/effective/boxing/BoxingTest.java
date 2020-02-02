package com.effective.boxing;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoxingTest {
    @Test
    public void primitive() {
        final Integer intObj = 34;
        assert(intObj == 34);

        try {
            final Integer newInt = methodReturnNull(intObj);
            System.out.println("null 할당 가능");
            fail("null to primitive");
        } catch(NullPointerException e) {

        }

    }

    private Integer methodReturnNull(Integer intVal) {
        return null;
    }

}