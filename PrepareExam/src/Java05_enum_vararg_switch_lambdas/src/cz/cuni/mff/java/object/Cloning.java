package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.object;

public class Cloning implements Cloneable {

    private String s = new String("hello");

    public static void main(String[] argv) throws Exception {
        Cloning a = new Cloning();

        Cloning b = (Cloning) a.clone();

        System.out.println(a == b);
        System.out.println(a.s == b.s);
    }
}
