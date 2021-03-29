package Java03_exception_string_wrapper.src.cz.cuni.mff.java.wrappers;

import java.util.Collection;
import java.util.HashSet;

public class Boxing {


    public static void printer(Collection<? extends Number> numbers)
    {
        for(Number num :numbers)
        {
            System.out.println(num.doubleValue());
        }
    }

    public static void main(String[] argv) {
        Integer i1 = 1;
        Integer i2 = 1;

        System.out.println(i1 == i2);

        Integer i3 = 1024;
        Integer i4 = 1024;

        System.out.println(i3 == i4);
        HashSet<Double> doubleSet = new HashSet<>();
        doubleSet.add(1.0);
        doubleSet.add(2.0);
        doubleSet.add(3.0);
        printer(doubleSet);
    }
}
