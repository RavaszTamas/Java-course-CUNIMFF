package Java01_basic.src.cz.cuni.mff.java.example01;

public class Swap {

    public static void main(String[] argv) {
        int x = 10;
        int y = 20;

        x ^= y ^= x ^= y;

        System.out.println(x);
        System.out.println(y);
    }

}
