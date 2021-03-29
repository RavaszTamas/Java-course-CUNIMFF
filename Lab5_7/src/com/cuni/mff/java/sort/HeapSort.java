package com.cuni.mff.java.sort;

public class HeapSort {
    private static void heapify(int[] a, int length)
    {

    }
    private  static void siftDown(int[] a, int start, int end)
    {

    }
    public static void HeapSort(int[] a)
    {
        heapify(a,a.length);
        int end = a.length-1;
        while(end>0)
        {
            int empty = a[end];
            a[end] = a[0];
            a[0] = empty;
            siftDown(a,0,end-1);
            end--;
        }
    }
}
