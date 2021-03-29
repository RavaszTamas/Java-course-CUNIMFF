package com.cuni.lab9.assignment2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Random rnd = new Random(System.currentTimeMillis());

        int[] array = rnd.ints(10000,0,100000).toArray();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int value : array) {
            arr.add(value);
        }
        //System.out.println(arr);
        System.out.println(arr.parallelStream().max(Integer::compareTo).get());
    }

}
