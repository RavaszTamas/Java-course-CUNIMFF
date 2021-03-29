package Test1.Sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MergeSortParallel extends RecursiveAction {

    int[] arrayToSort;
    private static final int THRESHOLD = 1024;

    public MergeSortParallel(int[] arrayToSortParam)
    {
        arrayToSort = arrayToSortParam;
    }

    public  void sort(int[] arrayToSort)
    {
        if(arrayToSort.length < 2)
            return;
        int[] arrayLeft = new int[arrayToSort.length/2];
        int[] arrayRight = new int[arrayToSort.length - arrayToSort.length/2];
        for(int i = 0; i < arrayToSort.length/2; i++)
            arrayLeft[i] = arrayToSort[i];
        for(int i = arrayToSort.length/2; i < arrayToSort.length; i++)
            arrayRight[i-arrayToSort.length/2] = arrayToSort[i];

        sort(arrayLeft);
        sort(arrayRight);
        merge(arrayToSort,arrayLeft,arrayRight);
    }

     void merge(int[] original,int[] left, int[] right)
    {
        int i = 0, j = 0, k = 0;
        while(i < left.length && j < right.length)
        {
            if(left[i] <= right[j])
            {
                original[k++] = left[i++];
            }
            else
            {
                original[k++] = right[j++];
            }
        }
        while (i < left.length)
            original[k++]= left[i++];
        while (j < right.length)
            original[k++] = right[j++];
    }

    @Override
    protected void compute() {
        if (arrayToSort.length < THRESHOLD)
        {
            Arrays.sort(arrayToSort);
        }
        else {
            int[] arrayLeft = new int[arrayToSort.length / 2];
            int[] arrayRight = new int[arrayToSort.length - arrayToSort.length / 2];
            for (int i = 0; i < arrayToSort.length / 2; i++)
                arrayLeft[i] = arrayToSort[i];
            for (int i = arrayToSort.length / 2; i < arrayToSort.length; i++)
                arrayRight[i - arrayToSort.length / 2] = arrayToSort[i];

            invokeAll(new MergeSortParallel(arrayLeft),new MergeSortParallel(arrayRight));
            merge(arrayToSort, arrayLeft, arrayRight);
        }

    }
}
