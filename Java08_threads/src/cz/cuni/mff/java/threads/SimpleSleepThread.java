package cz.cuni.mff.java.threads;

public class SimpleSleepThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread t = new SimpleSleepThread();
            t.start();
        }
    }

}
