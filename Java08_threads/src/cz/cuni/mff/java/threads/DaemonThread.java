package cz.cuni.mff.java.threads;

public class DaemonThread extends Thread {

    public DaemonThread() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Daemon thread");
        }
    }

    public static void main(String[] args) {
        new DaemonThread();
        for (int i = 0; i < 1000000; i++)
            if (i % 100 == 0)
                System.out.println("Regular thread");
    }
}
