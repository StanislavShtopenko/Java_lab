package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args){
        Main mn = new Main();
        mn.run();
    }

    private void run() {
        ArrayProcessor ap = new ArrayProcessor();
        DoubleArrayReader dar = new DoubleArrayReader();
        double res51 = ap.calculate(dar.readOneDimensionalArray("lab51.txt"));
        double res52 = ap.calculate(dar.readTwoDimensionalArray(new File("lab52.txt")));
        System.out.println("5.1 Result = " + res51);
        System.out.println("5.2 Result = " + res52);
    }
}
