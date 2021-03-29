package cz.cuni.mff.java.threads;

public class SynchroTest extends Thread {

    private static java.util.Random rnd = new java.util.Random();

    public SynchroTest() {
        start();
    }

    public static synchronized void print(String id) {
        try {
            System.out.println("Hello world! " + id);
            sleep(rnd.nextInt(1000));
            System.out.println("Hello world! " + id);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run() {
        print(getName());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new SynchroTest();
        }
    }

}
