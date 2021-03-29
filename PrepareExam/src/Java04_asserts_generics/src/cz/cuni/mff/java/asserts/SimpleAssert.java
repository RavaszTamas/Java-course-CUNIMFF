package Java04_asserts_generics.src.cz.cuni.mff.java.asserts;

import java.util.ArrayList;
import java.util.List;

public class SimpleAssert {
    public static final int MAX_INTERVAL = 10;

    private int interval;

    public static <E,T> void printer(E one, T two)
    {
        System.out.println(one + " " + two);
    }

    public <E> void doer(E does)
    {

    }

    private void setInterval(int i) {
//    assert i>0 && i<=MAX_INTERVAL;
        assert false : "lol";
        assert i > 0 && i <= MAX_INTERVAL : "Bad interval: " + i;
        interval = i;
    }

    public static void main(String[] args) {
        SimpleAssert sa = new SimpleAssert();
        sa.setInterval(5);
        sa.setInterval(20);
        List<String> ls = new ArrayList<String>();

    }
}
