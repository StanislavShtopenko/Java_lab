package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayProcessorTest {

    private ArrayProcessor ap;
    private double[] testArr = { 47, 50, 10, 20, 30 }; // 14100000
    private double[][] testArr2 = { {47, 50, 60, 20, 30}, {47, 65, 23, 87, 23}, {32, 67, 34, 87, 34}, {23, 98, 34, 65, 74}, {12, 35, 96, 75, 45} }; // 98

    @BeforeEach
    void setUp() {
        ap = new ArrayProcessor();
    }

    @Test
    void testCalculate() {
        double expected = 14100000;
        double actual = ap.calculate(testArr);
        assertEquals(expected, actual, "Wrong calculate");
    }

    @Test
    void testCalculate2() {
        double expected = 98;
        double actual = ap.calculate(testArr2);
        assertEquals(expected, actual, "Wrong calculate");
    }
}