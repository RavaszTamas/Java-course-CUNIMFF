package com.cuni;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class ForkJoinCheckPrime extends RecursiveTask<Long>
{
    final long TRESHOLD = 10;
    long low, high;
    ForkJoinCheckPrime(long lo, long hi)
    {
        low = lo;
        high = hi;
    }
    @Override
    protected Long compute() {
        if(high - low < TRESHOLD)
        {
            return countPrimes(low,high);
        }
        long mid = (high + low)/2;
        ForkJoinCheckPrime cnt1 =  new ForkJoinCheckPrime(low,mid);
        ForkJoinCheckPrime cnt2 =  new ForkJoinCheckPrime(mid,high);
        cnt1.fork();
        cnt2.fork();
        return cnt1.join() + cnt2.join();
    }
    public static boolean checkIfPrime(long num)
    {
        if (num <= 1) {
            return false;
        }

        for(int i = 2; i <= Math.sqrt(num);i++)
        {
            if(num % i == 0)
                return false;
        }
        return true;
    }
    public static long countPrimes(long start, long stop)
    {
        long cnt = 0;
        for(long num = start; num < stop; ++num)
        {
            if(checkIfPrime(num)) {
                ++cnt;
                //System.out.println(num);
            }
        }
        //System.out.println();
        return cnt;
    }

}

public class Main {



    public static void main(String[] args) {
        if(args.length != 2)
        {
            System.out.println("Invalid number of arguments!");
            return;
        }
        long start, end;

        try
        {
            start = Long.parseLong(args[0]);
            end = Long.parseLong(args[1]);
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Invalid input! Not a number");
            return;
        }
        if(start < 0 || end < 0)
        {
            System.out.println("No neagtive numbers!");
            return;
        }
        if(end < start)
        {
            long emp = end;
            end = start;
            start = emp;
        }
        //System.out.println(ForkJoinCheckPrime.countPrimes(start,end));
        //System.out.println();
        System.out.println(ForkJoinPool.commonPool().invoke(new ForkJoinCheckPrime(start,end)));

    }
}
