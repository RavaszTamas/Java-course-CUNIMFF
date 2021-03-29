package cz.cuni.mff.java.threads;

public class InterruptThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.print("Sleeping...");
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted");
        }

    }

    public static void main(String[] args) {
        Thread t = new InterruptThread();
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}
        t.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}
        System.out.println("Finished");
    }
}
