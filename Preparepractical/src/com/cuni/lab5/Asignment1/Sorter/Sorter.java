package com.cuni.lab5.Asignment1.Sorter;

public class Sorter {

    public static void quickSort(int[] array)
    {
        if (array ==null || array.length==0){
            return;
        }
        sortQuick(array,0, array.length - 1);

    }
    static void sortQuick(int[] numbers, int start, int end)
    {
        int pivot,i,j;
        i = start;
        j = end;
        pivot = numbers[start+(start-end)/2];
        while (i <= j)
        {
            while (numbers[i] < pivot){
                i++;
            }
            while (numbers[j] > pivot) {
                j--;
            }

            if (i <= j) {
                swap(numbers,i, j);
                i++;
                j--;
            }

        }
        if (start < j)
            sortQuick(numbers,start, j);
        if (i < end)
            sortQuick(numbers,i, end);

    }

    static void heapify(int[] arrayHeapify)
    {
        int end = arrayHeapify.length;
        int start = end/2-1;
        while(start >= 0)
        {
            siftDown(arrayHeapify,start,end);
            start--;
        }
    }
    static void swap(int[]arrayToMakeChange, int posOriginal, int posTarget)
    {
        int emp = arrayToMakeChange[posOriginal];
        arrayToMakeChange[posOriginal] = arrayToMakeChange[posTarget];
        arrayToMakeChange[posTarget] = emp;
    }

    static void siftDown(int[] a, int start, int end)
    {
        int root,leftChild,rightChild;
        while (start < end)
        {
            root = start;
            leftChild = root*2+1;
            rightChild = root*2+2;
            if(leftChild < end && a[leftChild] > a[root])
                root = leftChild;
            if(rightChild < end && a[rightChild] > a[root])
                root = rightChild;

            if(root == start)
                return;
            swap(a,root,start);
            start = root;
        }
    }

    public static void heapSort(int []arrayToHeapify){
        if(arrayToHeapify == null || arrayToHeapify.length == 0)
            return;

        heapify(arrayToHeapify);
        int end = arrayToHeapify.length;
        while(end > 0){
            swap(arrayToHeapify,end-1,0);
            siftDown(arrayToHeapify,0,end-1);
            end--;
        }
    }
}
