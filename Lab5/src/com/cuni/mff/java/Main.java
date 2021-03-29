package com.cuni.mff.java;

import com.cuni.mff.java.HashTable.MyHashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
	// write your code here
        /*
        HeapSort hsorter = new HeapSort();
        Quicksorter  qsorter = new Quicksorter();
        Random rnd = new Random();
        int n = rnd.nextInt(1000);
        int[] array = new int[n];
        for(int i = 0;i < array.length; i++)
        {
            array[i] = rnd.nextInt(1000);
        }

        int[] toSorth = Arrays.copyOf(array,array.length);

        long before = System.nanoTime();
        hsorter.heapSort(toSorth);
        long after = System.nanoTime();
        System.out.println(after-before);
        for(int elem : toSorth)
            System.out.print(elem + " ");
        System.out.println("");

        int[] toSortq = Arrays.copyOf(array,array.length);

        before = System.nanoTime();
        hsorter.heapSort(toSortq);
        after = System.nanoTime();
        System.out.println(after-before);
        for(int elem : toSortq)
            System.out.print(elem + " ");
        System.out.println("");

        System.out.println(Arrays.equals(toSorth,toSortq));
        */
        MyHashTable<String,Integer> table = new MyHashTable<>();
        table.set("asd",1);
        table.set("bsa",2);
        table.set("lol",4);
        table.set("asd",5);
        table.set("bsa",6);
        table.set("lol",7);

        table.forEachValue(x -> x * 2);
        for(String elem : table)
        {
            System.out.println(elem+ " " +table.get(elem));
        }
        try(BufferedReader input = new BufferedReader( new InputStreamReader(System.in)))
        {
            PolishNotationEvaluator asd = new PolishNotationEvaluator();

            while (true) {
                String inString = input.readLine();
                System.out.println(asd.calculate(inString));
            }
        }
        catch (IOException e)
        {

        }
    }
}
