package Java08.src.cz.cuni.mff.java.threads;

public class ParallelSum extends Thread {
    private int a;
    private int b;
    private int result;

    public ParallelSum(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        result = a + b;
    }

    public int getResult() {
        return result;
    }

    public static void main(String[] argv) {
        ParallelSum t1 = new ParallelSum(1, 2);
        ParallelSum t2 = new ParallelSum(1, 2);
        t1.start();
        t2.start();

        while (t1.isAlive()) {
            try {
                t1.join();
            } catch (InterruptedException ex) {}
        }
        while (t2.isAlive()) {
            try {
                t2.join();
            } catch (InterruptedException ex) {}
        }
        System.out.println(t1.getResult() + t2.getResult());
    }
}
