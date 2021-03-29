package Java01_basic.src.cz.cuni.mff.java.example01;

public class Overflow {
    public static void main(String[] argv) {
        int b = 2147483647;
        System.out.println(b);
        b = b + 1;
        System.out.println(b);
    }
}
