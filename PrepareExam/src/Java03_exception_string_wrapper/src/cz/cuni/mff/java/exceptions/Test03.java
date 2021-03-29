package Java03_exception_string_wrapper.src.cz.cuni.mff.java.exceptions;


import java.util.ArrayList;
import java.util.List;

public class Test03 {

    public Test03(Object o) {
        System.out.println("ParamsTest(Object o)");
    }
    public Test03(long[] a) {
        System.out.println("ParamsTest(long[] a)");
    }
    public static void main(String[] argv) {
        new Test03(null);
        List a = new ArrayList<Integer>();
        a.add(1);
    }
}
