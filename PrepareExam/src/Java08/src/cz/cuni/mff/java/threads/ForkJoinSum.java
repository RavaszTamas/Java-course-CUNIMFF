package Java08.src.cz.cuni.mff.java.threads;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSum extends RecursiveTask<Long>{
    public static final int THRESHOLD = 5;
    private final int[] array;
    private final int lo, hi;
    int i;
    public ForkJoinSum(int[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected Long compute() {
        if (hi - lo < THRESHOLD) {
            long ans = 0;
            for (int i = lo; i < hi; i++) {
                ans += array[i];
            }
            return ans;
        } else {
            int mid = (lo + hi) >>> 1;
            ForkJoinSum sum1 = new ForkJoinSum(array, lo, mid);
            sum1.fork();
            ForkJoinSum sum2 = new ForkJoinSum(array, mid, hi);
            return sum2.compute() + sum1.join();
        }
    }
    private static final Object lock = new Object();
    void asd()
    {
        // (lock)
        //{

        i++;
        System.out.println(i);
        //}
    }

    public static void main(String[] argv) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

        System.out.println(ForkJoinPool.commonPool().invoke(new ForkJoinSum(arr, 0, arr.length)));
        ForkJoinSum obj = new ForkJoinSum(arr, 0, arr.length);
        new Thread(()->{obj.asd();
            System.out.println(Thread.currentThread().getName());}).start();
        new Thread(()->{obj.asd();
            System.out.println(Thread.currentThread().getName());}).start();
        new Thread(()->{obj.asd();
            System.out.println(Thread.currentThread().getName());}).start();
        new Thread(()->{obj.asd();
            System.out.println(Thread.currentThread().getName());}).start();
        new Thread(()->{obj.asd();
            System.out.println(Thread.currentThread().getName());}).start();
        int[][][ ] a = new int[1][2][3];
        System.out.println(a);
        String as = new String("as");
        String ds = new String("as");
        System.out.println(as == ds);
        var pityu = new ArrayList<>();
    }
}
