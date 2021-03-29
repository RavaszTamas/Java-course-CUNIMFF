package Java03_exception_string_wrapper.src.cz.cuni.mff.java.strings;

public class StringEql {

    public static void main(String[] argv) {
        String a = new String("hello");
        String b = new String("hello");
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
