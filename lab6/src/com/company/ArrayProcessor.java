package com.company;

interface ArrayProcessorInterface {
    double calculate(double[] array);
    double calculate(double[][] array);
}

public class ArrayProcessor implements ArrayProcessorInterface{

    @Override
    public double calculate(double[] array) {
        double res = 1;
        for(double tmp : array)
            res *= tmp;
        return res;
    }

    @Override
    public double calculate(double[][] array) {
        double max = 0;
        boolean initialized = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i + j + 1 >= array.length) // Побочная диагональ, и нижестоящие от неё элементы
                {
                    if (!initialized)
                    {
                        max = array[i][j];
                        initialized = true;
                    }
                    if (array[i][j] > max)
                        max = array[i][j];
                }
            }
        }
        return max;
    }
}
