package com.cuni.lab5.Asignment1;

import com.cuni.lab5.Asignment1.Sorter.Sorter;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = {1,3,12,3,12,5,14,3,2,3};
        Sorter.heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
