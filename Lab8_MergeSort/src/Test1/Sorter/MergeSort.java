package Test1.Sorter;

import java.util.Arrays;

public class MergeSort {

    public static void sort(int[] arrayToSort)
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
    static void merge(int[] original,int[] left, int[] right)
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

}
