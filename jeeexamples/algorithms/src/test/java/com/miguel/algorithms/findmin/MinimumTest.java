package com.miguel.algorithms.findmin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MinimumTest {

    @Test
    void test() {
        Minimum s = new Minimum();
        s.initialize(new int[]{10, 5, 6, 9, 3});

        Assertions.assertEquals(5, s.findMin(5, 0, 2));
        Assertions.assertEquals(5, s.findMin(5, 0, 1));
        Assertions.assertEquals(3, s.findMin(5, 0, 4));
        Assertions.assertEquals(3, s.findMin(5, 3, 4));
        Assertions.assertEquals(5, s.findMin(5, 0, 3));
        Assertions.assertEquals(6, s.findMin(5, 2, 3));
        Assertions.assertEquals(10, s.findMin(5, 0, 0));
    }
}
