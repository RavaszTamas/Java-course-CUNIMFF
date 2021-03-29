package Java02_inheritance.src.cz.cuni.mff.java.example02;

public class FinalizeTest1 {
    public static int i = 0;
    public int num;

    public FinalizeTest1() {
        num = i;
        i++;
    }

    protected void finalize() {
        System.out.println("Finalizing: " + num);
    }

    public static void main(String[] argv) throws Exception {
        FinalizeTest1[] ar = new FinalizeTest1[10];

        for (int i = 0; i < 10; i++) {
            ar[i] = new FinalizeTest1();
        }

        for (int i = 0; i < 10; i++) {
            ar[i] = null;
        }

        System.gc();
        Thread.sleep(5000);

        System.out.println("Konec");
    }
}
