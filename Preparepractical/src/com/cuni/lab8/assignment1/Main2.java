package com.cuni.lab8.assignment1;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class MergeSortParallel extends RecursiveAction
{
    int TRESHOLD = 1024;
    int[] arrayToSort;
    public MergeSortParallel(int[] arrayToSortParam)
    {
        arrayToSort = arrayToSortParam;
    }

    private void sortFunction(int[] array)
    {
        if(array.length < 2)
            return;
        int[] left,right;
        left = new int[array.length/2];
        right = new int[array.length -array.length/2];

        for(int i = 0; i < array.length/2;i++)
        {
            left[i] = array[i];
        }
        for(int i = array.length/2; i < array.length;i++)
        {
            right[i-array.length/2] = array[i];
        }
        sortFunction(left);
        sortFunction(right);
        merge(array,left,right);
    }

    private void merge(int[] array, int[] left, int[] right) {
        int i=0,j=0,k=0;
        while (i<left.length && j<right.length)
        {
            if(left[i] <= right[j])
            {
                array[k++] = left[i++];
            }
            else
            {
                array[k++] = right[j++];
            }
        }
        while (i<left.length)
        {
            array[k++] = left[i++];
        }
        while (j< right.length)
        {
            array[k++] = right[j++];
        }

    }


    @Override
    protected void compute() {
        if(arrayToSort.length < TRESHOLD)
        {
            sortFunction(arrayToSort);
        }
        else
        {
            int[] left = new int[arrayToSort.length/2];
            int[] right = new int[arrayToSort.length -arrayToSort.length/2];
            for(int i = 0; i < arrayToSort.length/2; i++)
                left[i] = arrayToSort[i];
            for(int i = arrayToSort.length/2; i < arrayToSort.length; i++)
                right[i-arrayToSort.length/2] = arrayToSort[i];
            RecursiveAction one = new MergeSortParallel(left);
            RecursiveAction two = new MergeSortParallel(right);
            invokeAll(one,two);
            one.join();
            two.join();

        }
    }
}

public class Main2 {

    public static void main(String[] args) {
        Random rnd = new Random(System.currentTimeMillis());
        int[] arrayToSort = rnd.ints(100,0,1_000).toArray();
        ForkJoinPool.commonPool().invoke(new MergeSortParallel(arrayToSort));

        System.out.println(Arrays.toString(arrayToSort));
    }
}
