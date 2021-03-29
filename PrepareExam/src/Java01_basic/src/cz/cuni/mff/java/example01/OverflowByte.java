package Java01_basic.src.cz.cuni.mff.java.example01;

public class OverflowByte {
    public static void main(String[] argv) {
        byte b = 127;
        System.out.println(b);
        b = (byte) (b + 1);
        System.out.println(b);
    }
}
