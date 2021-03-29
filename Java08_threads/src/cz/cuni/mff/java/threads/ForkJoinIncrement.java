package cz.cuni.mff.java.threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinIncrement extends RecursiveAction {
    public static final int THRESHOLD = 5;
    private final long[] array;
    private final int lo, hi;

    public ForkJoinIncrement(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected void compute() {
        if (hi - lo < THRESHOLD) {
            for (int i = lo; i < hi; ++i)
                array[i]++;
        } else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new ForkJoinIncrement(array, lo, mid), new ForkJoinIncrement(array, mid, hi));
        }
    }

    public static void main(String[] argv) {
        long[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

        ForkJoinPool.commonPool().invoke(new ForkJoinIncrement(arr, 0, arr.length));

        for (long i : arr) {
            System.out.println(i);
        }
    }
}