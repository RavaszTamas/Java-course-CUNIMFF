package com.cuni.mff.java;

public class Main {

    public static void main(String[] args) {
        int START = 2_000_000_000;
        int count = 0;
        Integer i = 1;
        for (i = Integer.MAX_VALUE; i < Double.POSITIVE_INFINITY; i++)
        {
            System.out.println(i);
        }
       for(double f= START; f < START + 50; f++)
       {
           count++;
       }
        System.out.println(count);
    }
}
