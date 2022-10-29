package org.example;


//Sorts an array of strings using different sorting algorithms.
//This repository is only made to showcase the different sorting algorithms.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class Main {

    private String[] array;



    public String[] generateArray(int size) {
        array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = String.valueOf((int) (Math.random() * 100));
        }
        return array;

    }



   private String[] shuffleArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = (int) Math.floor(Math.random() * array.length);
            String temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
        return array;
    }

    private void printArray(String[] array) {
        //print array as table
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
            if (i % 10 == 0 && i != 0) {
                System.out.println();
            }
        }
    }




    public String[] bubbleSort() {
        long startTime = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("Bubble sort took " + duration + " nanoseconds");
        return array;

    }

    public String[] selectionSort() {
        long startTime = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[min]) < 0) {
                    min = j;
                }
            }
            String temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Selection sort took " + duration + " nanoseconds");
        return array;
    }

    public String[] insertionSort() {
        long startTime = System.nanoTime();
        for (int i = 1; i < array.length; i++) {
            String temp = array[i];
            int j = i - 1;
            while (j >= 0 && temp.compareTo(array[j]) < 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Insertion sort took " + duration + " nanoseconds");
        return array;
    }

    private void merge(String[] array, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        String[] leftArray = new String[leftSize];
        String[] rightArray = new String[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[left + i];
        }
        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = array[middle + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftSize && j < rightSize) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightArray[j];
            j++;
            k++;
        }

    }

    private String[] mergeSort(String[] array, int left, int right) {

        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }

        return array;
    }


    public void compareArrays(String[] array1, String[] array2) {
        if (Arrays.equals(array1, array2)) {
            System.out.println("Arrays are equal");
        } else {
            System.out.println("Arrays are not equal");
        }
    }




    public static void main(String[] args) throws IOException {

        //Generate an array of 1000 random numbers.
        //Shuffle the array.
        //Save the array in a variable.
        //Sort the array using different sorting algorithms.
        //Print a table with the results.

        Main main = new Main();


        String[] result;
        main.generateArray(1000);
        main.shuffleArray(main.array);
        String[] array = main.array;

       //save the array and give each sorting algorithm the same array.
        result = main.bubbleSort();
        main.compareArrays(result, main.array);
        main.printArrayAsTable(result,"Bubble sort");
        result = main.selectionSort();
        main.compareArrays(result, main.array);
        main.printArrayAsTable(result,"Selection sort");
        result =  main.insertionSort();
        main.compareArrays(result,   main.array);
        main.printArrayAsTable(result,"Insertion sort");
        result =  main.mergeSort(main.array, 0, main.array.length - 1);
        main.compareArrays(result, main.array);
        main.printArrayAsTable(result,"Merge sort");

        


    }

    public void printArrayAsTable(String[] array,String name){
        System.out.println("Below is the result of "+name);
        System.out.println("|");
        System.out.println("|");
        System.out.println("|");
        System.out.println("V");
        System.out.println(name+":");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
            if (i % 10 == 0 && i != 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
        //fill the console with - to distinguish between the results of different sorting algorithms.
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println("\n");







    }


    }