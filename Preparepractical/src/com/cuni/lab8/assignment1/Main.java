package com.cuni.lab8.assignment1;

import java.util.Arrays;
import java.util.Random;

class mergeSorter extends Thread
{
    private int low,high,array[];
    public mergeSorter(int plow, int phigh, int[] parray)
    {
        low = plow;
        high = phigh;
        array = parray;
    }

    private void merge(int s,int m, int e)
    {
        int[] left,right;
        int n1 = m - s + 1;
        int n2 = e - m;
        left = new int[n1];
        right = new int[n2];
        for(int i = 0; i < n1; i++)
        {
            left[i] = array[s+i];
        }
        for(int i = 0; i < n2; i++)
        {
            right[i] = array[(m+1)+i];
        }
        int i = 0, j = 0,k = s;

        while (i<n1 &&j<n2)
        {
            if(left[i] <= right[j])
            {
                array[k++] = left[i++];
            }
            else {
                array[k++] = right[j++];
            }
        }
        while (i < n1){
            array[k++] = left[i++];
        }
        while (j < n2){
            array[k++] = right[j++];
        }

    }

    public void mergeSort(int l, int h)
    {
        int m = (l+h)/2;
        if (l<h)
        {
            mergeSort(l,m);
            mergeSort(m+1,h);
            merge(l,m,h);
        }
    }

    @Override
    public void run() {

        mergeSort(low,high);

    }
}

public class Main {

    public static void main(String[] args) {
        int low, high,numProcessors;
        numProcessors = Runtime.getRuntime().availableProcessors();
        int[] numbers = new int[100];
        Random rnd = new Random(System.currentTimeMillis());
        for(int i = 0; i < numbers.length; i++)
        {
            numbers[i] = rnd.nextInt(10);
        }
        Thread[] threads = new Thread[numProcessors];
        mergeSorter t = new mergeSorter(0,numbers.length-1,numbers);
        //t.mergeSort(0,numbers.length-1);
        for(int i = 0; i < numProcessors;i++)
        {
            low = i * numbers.length/numProcessors;
            high = (i+1) * numbers.length/numProcessors-1;
            threads[i] = new mergeSorter(low,high,numbers);
            threads[i].start();
        }
        for(int i = 0; i < numProcessors;i++)
        {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Arrays.toString(numbers));
    }

}
