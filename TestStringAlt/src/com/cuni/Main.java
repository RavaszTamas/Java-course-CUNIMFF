package com.cuni;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

interface malac
{
    void print1();

}
interface pityu
{
    void print();
}

interface asd extends malac,pityu
{

}

interface budi
{

}

abstract class je
{
     abstract void ferenc();
     public final void done()
     {

     }
}

class A extends je implements asd, malac, pityu, budi {
    int val;
    static int i;
    static {
        i = 1;
    }
    public int j;
    {
        j = 1;
    }
    {
        j = 3;
    }
    public A(int p)
    {
        val = p;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if(o instanceof  A)
        {
            return true;
        }
        return false;
    }


    @Override
    public void print1() {
        done();
    }

    @Override
    public void print() {

    }

    @Override
    void ferenc() {

    }
}
/*
class String {
    private final java.lang.String s;
    public String(java.lang.String s) {
        this.s = s;
    }
    public java.lang.String toString() {
        return s;
    }
}*/

public class Main {

    public static void main(java.lang.String[] args) {

        new Thread(()-> System.out.println(42)).start();

        A a= new A(1);
        A aa= new A(1);
        System.out.println(a.j);
        System.out.println(a.equals("aa"));
        String s = new String("Hello world");
        System.out.println(s);
        Integer arr[] = {1,8,2,9,5,4,11,123,123,42,4,35,9};
        Arrays.sort(arr,((o1, o2) -> o1-o2));
        System.out.println(Arrays.toString(arr));
        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in));PrintStream os = new PrintStream(new BufferedOutputStream(new FileOutputStream("asd.txt")))) {
            String line;
            System.setOut(os);
            while (!(line = in.readLine()).equals("")){
                os.write(line.getBytes());
                os.write('\n');
                os.flush();
            }

        }
        catch (IOException e)
        {

        }
    }

}
