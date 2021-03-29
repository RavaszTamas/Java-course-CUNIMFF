package Java08.src.cz.cuni.mff.java.threads;

public class SimpleThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++)
            System.out.println(this.getName() + " : " + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread t = new SimpleThread();
            t.start();
        }
    }
}
