package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

interface DoubleArrayReaderInterface {
    double[] readOneDimensionalArray(File file);
    double[] readOneDimensionalArray(String fileName);
    double[][] readTwoDimensionalArray(File file);
    double[][] readTwoDimensionalArray(String fileName);
}

public class DoubleArrayReader implements DoubleArrayReaderInterface{

    @Override
    public double[] readOneDimensionalArray(File file) {
        try (Scanner scanner = new Scanner(file)){
            int length = Integer.parseInt(scanner.nextLine());
            double[] array = new double[length];
            StringBuilder line = new StringBuilder();
            while(scanner.hasNextLine())
                line.append(scanner.nextLine());
            String[] numbersString = line.toString().trim().split(" +");
            int counter = 0;
            for(String tmp : numbersString)
                array[counter++] = Double.parseDouble(tmp);
            return array;
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
            return null;
        }
    }

    @Override
    public double[] readOneDimensionalArray(String fileName){
        try(BufferedReader br = new BufferedReader(Files.newBufferedReader(Paths.get(fileName)))){
            int length = Integer.parseInt(br.readLine());
            double[] array = new double[length];
            StringBuilder line = new StringBuilder();
            String str;
            while((str = br.readLine()) != null)
                line.append(str);
            String[] numbersString = line.toString().trim().split(" +");
            int counter = 0;
            for(String tmp : numbersString)
                array[counter++] = Double.parseDouble(tmp);
            return array;
        } catch (IOException e){
            System.out.println("Error: " + e.toString());
            return null;
        }
    }

    @Override
    public double[][] readTwoDimensionalArray(File file) {
        try (Scanner scanner = new Scanner(file)){
            int length = Integer.parseInt(scanner.nextLine());
            double[][] array = new double[length][length];
            String[] lines = new String[length];
            for (int i = 0; scanner.hasNext(); i++) {
                lines[i]= scanner.nextLine();
            }
            String[][] linesTwoDimensionalArray = new String[length][length];
            for (int i = 0; i < length; i++) {
                linesTwoDimensionalArray[i] = lines[i].trim().split(" +");
            }
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    array[i][j] = Double.parseDouble(linesTwoDimensionalArray[i][j]);
                }
            }
            return array;
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
            return null;
        }
         }

    @Override
    public double[][] readTwoDimensionalArray(String fileName) {
        try(BufferedReader br = new BufferedReader(Files.newBufferedReader(Paths.get(fileName)))){
            int length = Integer.parseInt(br.readLine());
            double[][] array = new double[length][length];
            String[] lines = new String[length];
            String str;
            for (int i = 0; ((str = br.readLine()) != null); i++)  {
                lines[i]= str;
            }
            String[][] linesTwoDimensionalArray = new String[length][length];
            for (int i = 0; i < length; i++) {
                linesTwoDimensionalArray[i] = lines[i].trim().split(" +");
            }
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    array[i][j] = Double.parseDouble(linesTwoDimensionalArray[i][j]);
                }
            }
            return array;
        } catch (IOException e){
            System.out.println("Error: " + e.toString());
            return null;
        }
    }
}
