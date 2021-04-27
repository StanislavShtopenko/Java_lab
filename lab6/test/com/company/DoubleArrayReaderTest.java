package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DoubleArrayReaderTest {

    private DoubleArrayReader dar;
    private double[] testArr = { 47, 50, 10, 20, 30 };
    private double[][] testArr2 = { {47, 50, 60, 20, 30}, {47, 65, 23, 87, 23}, {32, 67, 34, 87, 34}, {23, 98, 34, 65, 74}, {12, 35, 96, 75, 45} };

    @BeforeEach
    void setUp()
    {
        dar = new DoubleArrayReader();
    }

    @Test
    void readOneDimensionalArray() {
        double[] actual = dar.readOneDimensionalArray(new File("testArr.txt"));
        assertArrayEquals(testArr, actual, "Error of reading");
    }

    @Test
    void testReadOneDimensionalArray() {
        double[] actual = dar.readOneDimensionalArray("testArr.txt");
        assertArrayEquals(testArr, actual, "Error of reading");
    }

    @Test
    void readTwoDimensionalArray() {
        double[][] actual = dar.readTwoDimensionalArray(new File("testArr2.txt"));
        assertArrayEquals(testArr2, actual, "Error of reading");
    }

    @Test
    void testReadTwoDimensionalArray() {
        double[][] actual = dar.readTwoDimensionalArray("testArr2.txt");
        assertArrayEquals(testArr2, actual, "Error of reading");
    }
}