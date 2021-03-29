package Java02_inheritance.src.cz.cuni.mff.java.example02;

public class FinalizeTest3 {

    private static int i = 0;
    private int num;
    private double array[] = new double[500000];

    public FinalizeTest3() {
        num = i;
        i++;
        System.out.println("Creating: " + num);
    }

    protected void finalize() {
        System.out.println("Finalizing: " + num);
    }

    public static void main(String[] argv) {
        for (int i = 0; i < 100; i++) {
            new FinalizeTest3();
        }
    }
}
