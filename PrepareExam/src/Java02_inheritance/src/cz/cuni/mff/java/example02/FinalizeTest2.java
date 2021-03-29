package Java02_inheritance.src.cz.cuni.mff.java.example02;

public class FinalizeTest2 {
    public static int i = 0;
    public int num;

    public FinalizeTest2() {
        num = i;
        i++;
    }

    protected void finalize() {
        System.out.println("Finalizing: " + num);
    }

    public static void main(String[] argv) throws Exception {
        FinalizeTest2[] ar = new FinalizeTest2[10];

        for (int i = 0; i < 10; i++) {
            ar[i] = new FinalizeTest2();
        }

        for (int i = 0; i < 10; i++) {
            ar[i] = null;
        }

        System.out.println("Konec");
    }
}
