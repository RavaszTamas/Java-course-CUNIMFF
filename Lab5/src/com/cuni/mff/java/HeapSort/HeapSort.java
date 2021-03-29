package com.cuni.mff.java.HeapSort;

public class HeapSort {

    void heapify(int[] arrayHeapify)
    {
        int end =arrayHeapify.length;
        int start = (end)/2-1;
        while(start >= 0)
        {
            siftDown(arrayHeapify,start,end);
            start--;
        }
    }
    void swap(int[]arrayToMakeChange, int posOriginal, int posTarget)
    {
        int empty = arrayToMakeChange[posOriginal];
        arrayToMakeChange[posOriginal] = arrayToMakeChange[posTarget];
        arrayToMakeChange[posTarget] = empty;
    }

    void siftDown(int[] a, int start, int end)
    {
        int index, leftChild,rightChild;
        while (start < end)
        {
            index = start;
            leftChild = 2*start+1;
            rightChild = leftChild+1;
            if (leftChild < end && a[leftChild] > a[index]) {
                index = leftChild;
            }

            if (rightChild < end && a[rightChild] > a[index]) {
                index = rightChild;
            }
            if(index == start)
                return;

            swap(a,start,index);
            start = index;
        }
    }

    public void heapSort(int []arrayToHeapify){
        heapify(arrayToHeapify);
        int end = arrayToHeapify.length;
        while(end > 0){
            swap(arrayToHeapify,end-1,0);
            siftDown(arrayToHeapify,0,end-1);
            end--;
        }
    }
}
