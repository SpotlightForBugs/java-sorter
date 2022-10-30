package org.example;

import java.io.IOException;
import java.util.Arrays;

public class Main
{
    public String[][] speed;
    private String[] array;
    
    public Main() {
        this.speed = new String[5][2];
    }
    
    public String[] generateArray(final int size) {
        this.array = new String[size];
        for (int i = 0; i < size; ++i) {
            this.array[i] = String.valueOf((int)(Math.random() * 100.0));
        }
        return this.array;
    }
    
    private String[] shuffleArray(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            final int randomIndexToSwap = (int)Math.floor(Math.random() * array.length);
            final String temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
        return array;
    }
    
    public String[] bubbleSort() {
        final long startTime = System.nanoTime();
        for (int i = 0; i < this.array.length; ++i) {
            for (int j = 0; j < this.array.length - 1; ++j) {
                if (this.array[j].compareTo(this.array[j + 1]) > 0) {
                    final String temp = this.array[j];
                    this.array[j] = this.array[j + 1];
                    this.array[j + 1] = temp;
                }
            }
        }
        final long endTime = System.nanoTime();
        final long duration = endTime - startTime;
        System.out.println(invokedynamic(makeConcatWithConstants:(J)Ljava/lang/String;, duration));
        this.speed[0][0] = "Bubble sort";
        this.speed[0][1] = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, String.valueOf(duration));
        return this.array;
    }
    
    public String[] selectionSort() {
        final long startTime = System.nanoTime();
        for (int i = 0; i < this.array.length; ++i) {
            int min = i;
            for (int j = i + 1; j < this.array.length; ++j) {
                if (this.array[j].compareTo(this.array[min]) < 0) {
                    min = j;
                }
            }
            final String temp = this.array[i];
            this.array[i] = this.array[min];
            this.array[min] = temp;
        }
        final long endTime = System.nanoTime();
        final long duration = endTime - startTime;
        System.out.println(invokedynamic(makeConcatWithConstants:(J)Ljava/lang/String;, duration));
        this.speed[1][0] = "Selection sort";
        this.speed[1][1] = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, String.valueOf(duration));
        return this.array;
    }
    
    public String[] insertionSort() {
        final long startTime = System.nanoTime();
        for (int i = 1; i < this.array.length; ++i) {
            String temp;
            int j;
            for (temp = this.array[i], j = i - 1; j >= 0 && temp.compareTo(this.array[j]) < 0; --j) {
                this.array[j + 1] = this.array[j];
            }
            this.array[j + 1] = temp;
        }
        final long endTime = System.nanoTime();
        final long duration = endTime - startTime;
        System.out.println(invokedynamic(makeConcatWithConstants:(J)Ljava/lang/String;, duration));
        this.speed[2][0] = "Insertion sort";
        this.speed[2][1] = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, String.valueOf(duration));
        return this.array;
    }
    
    private void merge(final String[] array, final int left, final int middle, final int right) {
        final int leftSize = middle - left + 1;
        final int rightSize = right - middle;
        final String[] leftArray = new String[leftSize];
        final String[] rightArray = new String[rightSize];
        System.arraycopy(array, left + 0, leftArray, 0, leftSize);
        for (int i = 0; i < rightSize; ++i) {
            rightArray[i] = array[middle + 1 + i];
        }
        int i = 0;
        int j = 0;
        int k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                ++i;
            }
            else {
                array[k] = rightArray[j];
                ++j;
            }
            ++k;
        }
        while (i < leftSize) {
            array[k] = leftArray[i];
            ++i;
            ++k;
        }
        while (j < rightSize) {
            array[k] = rightArray[j];
            ++j;
            ++k;
        }
    }
    
    private String[] mergeSort(final String[] array, final int left, final int right) {
        if (left < right) {
            final int middle = (left + right) / 2;
            this.mergeSort(array, left, middle);
            this.mergeSort(array, middle + 1, right);
            this.merge(array, left, middle, right);
        }
        return array;
    }
    
    public void compareArrays(final String[] array1, final String[] array2) {
        if (Arrays.equals(array1, array2)) {
            System.out.println("Arrays are equal");
        }
        else {
            System.out.println("Arrays are not equal");
        }
    }
    
    public static void main(final String[] args) throws IOException {
        final Main main = new Main();
        main.generateArray(1000);
        main.shuffleArray(main.array);
        final String[] array = main.array;
        String[] result = main.bubbleSort();
        main.compareArrays(result, main.array);
        main.printArrayAsTable(result, "Bubble sort");
        result = main.selectionSort();
        main.compareArrays(result, main.array);
        main.printArrayAsTable(result, "Selection sort");
        result = main.insertionSort();
        main.compareArrays(result, main.array);
        main.printArrayAsTable(result, "Insertion sort");
        result = main.mergeSort(main.array, 0, main.array.length - 1);
        final long startTime = System.nanoTime();
        main.compareArrays(result, main.array);
        final long endTime = System.nanoTime();
        final long duration = endTime - startTime;
        System.out.println(invokedynamic(makeConcatWithConstants:(J)Ljava/lang/String;, duration));
        main.speed[3][0] = "Merge sort";
        main.speed[3][1] = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, String.valueOf(duration));
        main.printArrayAsTable(result, "Merge sort");
        main.printOverviewTable(main.speed);
    }
    
    private void printOverviewTable(final String[][] speed) {
        System.out.println("Overview of the sorting algorithms");
        int longestString = 0;
        for (int i = 0; i < speed.length; ++i) {
            for (int j = 0; j < speed[i].length; ++j) {
                if (speed[i][j] != null && speed[i][j].length() > longestString) {
                    longestString = speed[i][j].length();
                }
            }
        }
        for (int i = 0; i < speed.length; ++i) {
            for (int j = 0; j < speed[i].length; ++j) {
                if (speed[i][j] != null) {
                    System.out.print(speed[i][j]);
                    for (int k = 0; k < longestString - speed[i][j].length(); ++k) {
                        System.out.print(" ");
                    }
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }
    
    public void printArrayAsTable(final String[] array, final String name) {
        System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, name));
        System.out.println("|");
        System.out.println("|");
        System.out.println("|");
        System.out.println("V");
        System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, name));
        for (int i = 0; i < array.length; ++i) {
            System.out.print(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, array[i]));
            if (i % 10 == 0 && i != 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
        for (int i = 0; i < 100; ++i) {
            System.out.print("-");
        }
        System.out.println("\n");
    }
}
