package Test1;

import Test1.Sorter.MergeSort;
import Test1.Sorter.MergeSortParallel;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        Random rnd = new Random(System.currentTimeMillis());
        int[] arrayToSort = rnd.ints(100_000_000,0,1_000_000_000).toArray();
        long bef = System.nanoTime();
        MergeSort.sort(arrayToSort);
        long aft = System.nanoTime();
        System.out.println(aft-bef);
        bef = System.nanoTime();
        ForkJoinPool.commonPool().invoke(new MergeSortParallel(arrayToSort));
        aft = System.nanoTime();
        System.out.println(aft-bef);
        //System.out.println(Arrays.toString(arrayToSort));
    }
}
