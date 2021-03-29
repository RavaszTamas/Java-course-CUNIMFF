package Java03_exception_string_wrapper.src.cz.cuni.mff.java.exceptions;

public class NoCatch2 {

    void m() {
        int[] array = {1, 2};
        array[3] = 4;
    }

    public static void main(String[] argv) {
        NoCatch2 nc = new NoCatch2();
        nc.m();
    }
}
