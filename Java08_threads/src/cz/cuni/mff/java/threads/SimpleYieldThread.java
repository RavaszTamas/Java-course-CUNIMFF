package cz.cuni.mff.java.threads;

public class SimpleYieldThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + " : " + i);
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread t = new SimpleYieldThread();
            t.start();
        }
    }

}
